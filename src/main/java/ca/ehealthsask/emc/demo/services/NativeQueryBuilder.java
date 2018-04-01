package ca.ehealthsask.emc.demo.services;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.model.MessageInquiryParameters;
import ca.ehealthsask.emc.demo.models.repository.DomainMessageTypeXrefRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NativeQueryBuilder {

    public static String MESSAGE_INQUIRY_QUERY_MV = createQuery();
    public static String MESSAGE_INQUIRY_QUERY_REGULAR = createOriginalMIQuery();

    public static String DATA_REMEDIATION_QUERY_REGULAR = createOriginalDRQuery();


    private static String createOriginalMIQuery() {
        return  "WITH  messages as ("
                + "    SELECT hm.order_number_txt index_value_txt "
                + "          , hm.message_control_id_txt "
                + "          , CASE WHEN hm.message_type_txt IN ('ORU^R01', 'ORU^R03')"
                + "                 THEN hm.message_subtype_txt"
                + "                 ELSE hm.message_type_txt "
                + "                 END AS message_type_txt"
                + "          , hm.message_subtype_txt "
                + "          , al.sender_oid "
                + "          , al.event_ts "
                + "          , emc.business_name description_txt "
                + "          , al.message_correlation_uuid "
                + "          , ROW_NUMBER( ) OVER ( PARTITION BY al.message_correlation_uuid ORDER BY al.event_ts DESC) rown "
                + "    FROM audit_Log al "
                + "    JOIN hial_message hm ON hm.message_correlation_uuid = al.message_correlation_uuid "
                + "    JOIN event_minor_code emc ON emc.event_minor_cde = al.event_minor_cde AND emc.event_major_cde = al.event_major_cde "
                + "    JOIN message_type mt ON mt.interaction_type_txt = al.message_type_txt "
                + "    AND emc.privacy_ind = 'N' "
                + "    WHERE %s "
                + "    AND hm.message_discriminator_cdid = 149 "
                + "    AND hm.workflow_path != 'WRKP_CDM_CCD' "
                + "    AND mt.interaction_mode_txt != 'QUERY_RESPONSE' "
                + "    AND CASE "
                + "    WHEN (al.alternate_flow_control_txt is null) THEN 1 "
                + "    WHEN (al.alternate_flow_control_txt = 'SCI_RESEND_PREVIEW') THEN 0 "
                + "    ELSE 1 "
                + "    END = 1 "
                + ") "
                + "SELECT m.index_value_txt"
                + "    , m.message_control_id_txt"
                + "    , m.message_type_txt"
                + "    , m.sender_oid"
                + "    , m.description_txt"
                + "    , m.message_correlation_uuid "
                + "    , min(al.event_ts) AS initial_event_ts"
                + "    , max(al.event_ts) AS latest_event_ts"
                + "    , null AS hial_message_id "
                + "FROM messages m, audit_log al "
                + "WHERE m.rown = 1 AND al.message_correlation_uuid = m.message_correlation_uuid "
                + "GROUP BY m.index_value_txt, m.message_control_id_txt, m.message_type_txt, m.sender_oid, m.description_txt, m.message_correlation_uuid "
                + "ORDER BY min(al.event_ts) DESC "
                + "FETCH FIRST 10 ROWS ONLY ";
    }

    public static String createOriginalDRQuery(){
        return "select \n" +
                "    hm.message_control_id_txt,\n" +
                "    hm.order_number_txt as index_number,\n" +
                "    case \n" +
                "        when rm.REMEDIATION_PRIORITY_CDID = 136 then\n" +
                "        'Yes'\n" +
                "        else 'No'\n" +
                "    end as routable,\n" +
                "    rm.sender_txt,\n" +
                "    re.record_created_ts as error_date,\n" +
                "    hm.message_type_txt,\n" +
                "    ret.description_txt as error_type,\n" +
                "    re.error_description_txt as error_description,\n" +
                "    rc.short_description_txt as status_txt,\n" +
                "    re.assign_user_name as assigned_to_TXT,\n" +
                "    re.resolution_type_cdid,\n" +
                "    re.record_updated_ts as last_updated_ts,\n" +
                "    rn.note_txt,\n" +
                "    --not used in results list, but appears in query\n" +
                "    hm.message_correlation_uuid,\n" +
                "    rm.sending_application_txt,\n" +
                "    rc.reference_cdid\n" +
                "from remediation_message rm\n" +
                "JOIN hial_message hm ON hm.hial_message_id = rm.hial_message_id\n" +
                "JOIN remediation_error re ON rm.remediation_message_id = re.remediation_message_id\n" +
                "JOIN remediation_error_type ret ON ret.remediation_error_type_id = re.remediation_error_type_id\n" +
                "JOIN reference_code rc ON re.remediation_status_cdid = rc.reference_cdid\n" +
                "left outer join remediation_note rn ON rn.remediation_error_id = re.remediation_error_id\n" +
                "where %s\n" +
                "    and hm.message_discriminator_cdid = 149\n" +
                "order by re.record_created_ts desc";
    }

    /**
     * Materialized view query
     * @return
     */
    private static String createQuery(){
        return "WITH messages AS (" +
                "    SELECT" +
                "        hm_al_mv.order_number_txt index_value_txt," +
                "        hm_al_mv.message_control_id_txt," +
                "        CASE" +
                "                WHEN hm_al_mv.message_type_txt IN (" +
                "                    'ORU^R01'," +
                "                    'ORU^R03'" +
                "                ) THEN hm_al_mv.message_subtype_txt" +
                "                ELSE hm_al_mv.message_type_txt" +
                "            END" +
                "        AS message_type_txt," +
                "        hm_al_mv.message_subtype_txt," +
                "        hm_al_mv.sender_oid," +
                "        hm_al_mv.event_ts," +
                "        emc.business_name description_txt, " +
                "        hm_al_mv.message_correlation_uuid, " +
                "        ROW_NUMBER() OVER( " +
                "            PARTITION BY hm_al_mv.message_correlation_uuid " +
                "            ORDER BY " +
                "                hm_al_mv.event_ts DESC " +
                "        ) rown " +
                "    FROM " +
                "        mv_hial_msg_audit_log hm_al_mv " +
                "        JOIN event_minor_code emc ON emc.event_minor_cde = hm_al_mv.event_minor_cde " +
                "                                     AND emc.event_major_cde = hm_al_mv.event_major_cde " +
                "        JOIN message_type mt ON mt.interaction_type_txt = hm_al_mv.message_type_txt " +
                "                                AND emc.privacy_ind = 'N' " +
                "    WHERE " +
                "        %s " +
                "        AND   hm_al_mv.message_discriminator_cdid = 149 " +
                "        AND   hm_al_mv.workflow_path != 'WRKP_CDM_CCD' " +
                "        AND   mt.interaction_mode_txt != 'QUERY_RESPONSE' " +
                "        AND " +
                "        CASE " +
                "                WHEN ( hm_al_mv.alternate_flow_control_txt IS NULL ) THEN 1 " +
                "                WHEN ( hm_al_mv.alternate_flow_control_txt = 'SCI_RESEND_PREVIEW' ) THEN 0 " +
                "                ELSE 1 " +
                "            END " +
                "        = 1 " +
                ") SELECT " +
                "    m.index_value_txt, " +
                "    m.message_control_id_txt, " +
                "    m.message_type_txt, " +
                "    m.sender_oid, " +
                "    m.description_txt, " +
                "    m.message_correlation_uuid, " +
                "    MIN(hm_al_mv.event_ts) AS initial_event_ts, " +
                "    MAX(hm_al_mv.event_ts) AS latest_event_ts, " +
                "    NULL AS hial_message_id " +
                "  FROM " +
                "    messages m, " +
                "    HIALAPP.mv_hial_msg_audit_log hm_al_mv " +
                "  WHERE " +
                "    m.rown = 1 " +
                "    AND   hm_al_mv.message_correlation_uuid = m.message_correlation_uuid " +
                "  GROUP BY " +
                "    m.index_value_txt, " +
                "    m.message_control_id_txt, " +
                "    m.message_type_txt, " +
                "    m.sender_oid, " +
                "    m.description_txt, " +
                "    m.message_correlation_uuid " +
                "  ORDER BY " +
                "    MIN(hm_al_mv.event_ts) DESC " +
                "  OFFSET %d ROWS " +
                "  FETCH NEXT %d ROWS ONLY";
    }

    /**
     * Builds a query for message inquiry based on user-entered parameters using the HIAL-AUDIT_LOG materialized view.
     * Parameters are optional, so they must be examined if null or empty when creating the where condition that specify
     * what to look for the user
     * @param em
     * @param params
     * @return
     */
    public Query buildMessageInquiryQuery(EntityManager em, MessageInquiryParameters params, DomainMessageTypeXrefRepository repo, Pageable pageable) {
        String queryString = "";
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        //add parameters if they exist
        if (!StringUtils.isEmpty(params.getMessageControlId())){
            conditions.add("hm_al_mv.message_control_id_txt = ");
            parameters.add(params.getMessageControlId());
        }
        if (!StringUtils.isEmpty(params.getOrderId())){
            conditions.add("hm_al_mv.order_number_txt = ");
            parameters.add(params.getOrderId());
        }
        if (!StringUtils.isEmpty(params.getSource())){
            conditions.add("hm_al_mv.sender_txt = ");
            parameters.add(params.getSource());
        }
        if (params.getFromDate() != null){
            conditions.add("hm_al_mv.event_ts >= ");
            parameters.add(params.getFromDate());
        }
        if (params.getToDate() != null){
            conditions.add("hm_al_mv.event_ts <= ");
            parameters.add(params.getToDate());
        }
        //add pageable to parameters
//        parameters.add(pageable.getOffset());
//        parameters.add(pageable.getPageSize());

        System.out.println(repo);
        List<String> messageInteractionTypes = repo.findMessageInteractionTypesByReferenceCode(params.getDomain());
        log.debug("Interaction types: " + messageInteractionTypes);

        String whereClause = createConditions(conditions);
        parameters.addAll(messageInteractionTypes);

        whereClause =  StringUtils.isBlank(whereClause) ?
                createIn(1, messageInteractionTypes, params.getDomain())
                : whereClause + " AND " + createIn(conditions.size() + 1, messageInteractionTypes, params.getDomain());

        queryString = String.format(MESSAGE_INQUIRY_QUERY_MV, whereClause, pageable.getOffset(), pageable.getPageSize());
        System.out.println("SQL:" + queryString);
        Query query = em.createNativeQuery(queryString, MessageInquiryResults.class);

        setParameters(query,parameters);
        log.debug(query.toString());
        return query;
    }


    /**
     * Creates a string of positional parameters for the dynamic string query
     * @param conditions
     * @return
     */
    private String createConditions(final List<String> conditions) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < conditions.size(); ++i) {
            stringBuilder.append(conditions.get(i));
            stringBuilder.append('?');
            stringBuilder.append(i + 1);
            if (i + 1 < conditions.size()) {
                stringBuilder.append(" AND ");
            }
        }
        return stringBuilder.toString();
    }

    private void setParameters(Query query, List<Object> parameters) {
        for (int i = 0; i < parameters.size(); ++i) {
            Object value = parameters.get(i);
            int parameterIndex = i + 1;
            query.setParameter(parameterIndex, value);
        }
    }

    private String createIn(int startingIndex, List<String> messageTypes, String domainType) {

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hm_al_mv.message_type_txt IN(");
        for (int i = 0; i < messageTypes.size(); ++i) {
            stringBuilder.append('?');
            stringBuilder.append(startingIndex + i);
            if (i + 1 < messageTypes.size()) {
                stringBuilder.append(',');
            }
        }
        stringBuilder.append(')');
        return domainTypeInParam(stringBuilder, domainType);
    }

    private String domainTypeInParam(StringBuilder inParam, String domainType) {
        switch (domainType) {
            case "LAB":
                break;
            case "CDR":
                break;
            case "CE":
                break;
            case "CDM":
                break;
            case "MIR":
                inParam.append(" AND hm_al_mv.message_subtype_txt IN ('HNAM','XIRIS','SIRJ', 'CARESTREAM')");
                break;
            default:
                throw new RuntimeException("Unknown domainType[" + domainType + "]");
        }

        return inParam.toString();
    }

}
