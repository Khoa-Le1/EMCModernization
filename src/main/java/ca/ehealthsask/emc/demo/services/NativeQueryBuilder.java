package ca.ehealthsask.emc.demo.services;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.model.MessageInquiryParameters;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Slf4j
public class NativeQueryBuilder {

    public static String MESSAGE_INQUIRY_QUERY
            = "WITH  messages as ("
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

    public static Query buildMessageInquiryQuery(EntityManager em, MessageInquiryParameters params) {
        String queryString = "";
        return em.createNativeQuery(queryString, MessageInquiryResults.class);
    }


}
