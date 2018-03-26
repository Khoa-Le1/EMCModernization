package ca.ehealthsask.emc.demo.models.entity;


//import ca.ehealthsask.emc.demo.utils.datasources.event.AuditLogIndex;
//import ca.ehealthsask.emc.demo.utils.datasources.event.AuditLogUser;
import ca.ehealthsask.emc.demo.models.model.MessageWorkflow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "AUDIT_LOG", schema = "EVENT")
@NamedNativeQuery(name="findWorkflowsByMessageCorrelationUuid", query = "SELECT" +
        "    al.event_ts, " +
        "    emc.business_name, " +
//        "    case emc.event_major_cde WHEN '1206' THEN re.error_description_txt  " +
//        "        else null " +
//        "    end, " +
        "    emc.event_major_cde,  " +
        "    emc.event_minor_cde, " +
//        "    rm.hial_message_id " +
        "     " +
        "from  " +
        "    AUDIT_LOG al " +
        "join  " +
        "    hial_message hm ON hm.message_correlation_uuid = al.message_correlation_uuid " +
        "join  " +
        "    event_minor_code emc ON emc.event_minor_cde = al.event_minor_cde " +
        "    and emc.event_major_cde = al.event_major_cde " +
        "join " +
        "    remediation_message rm ON hm.hial_message_id = rm.hial_message_id left outer " +
        "join remediation_error re ON rm.remediation_message_id = re.remediation_message_id ")
public class AuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
//    @SequenceGenerator(name = "AuditLogSeq", sequenceName = "AUDIT_LOG_S", allocationSize = 1)
//    @GeneratedValue(generator = "AuditLogSeq")
    @Column(name = "AUDIT_LOG_ID")//
    private Long auditLogId;
    @Column(name = "EVENT_TS")//
    private LocalDateTime eventTs;
    @Basic(optional = false)
    @Column(name = "MESSAGE_TYPE_TXT")//
    private String messageType;
    @Column(name = "DETAIL_TXT")//
    private String detail;
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID_TXT")//
    private String messageId;
    @Column(name = "SENDER_OID")//
    private String senderOid;
    @Column(name = "RECEIVER_OID")//
    private String receiverOid;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditLog")
//    private Collection<AuditLogIndex> auditLogIndexes = new ArrayList<AuditLogIndex>();
    @Column(name = "EVENT_MINOR_CDE")//
    private String eventMinorCde;
    @Column(name = "EVENT_MAJOR_CDE")//
    private String eventMajorCde;
    @Column(name = "MESSAGE_CORRELATION_UUID")//
    private String messageCorrelationUUID;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditLog")
//    private Collection<AuditLogUser> auditLogUsers = new ArrayList<AuditLogUser>();
    @ManyToMany
    @JoinTable(name = "HIAL_MESSAGE",
            joinColumns = {@JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID", insertable = false, updatable = false)}
    )
    private Collection<HialMessage> hialMessages;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "EVENT_MINOR_CDE",
                    referencedColumnName = "EVENT_MINOR_CDE",
                    insertable = false,
                    updatable = false),
            @JoinColumn(name = "EVENT_MAJOR_CDE",
                    referencedColumnName = "EVENT_MAJOR_CDE",
                    insertable = false,
                    updatable = false)
    })
    private EventMinorCode eventMinorCodeReference;
    @Column(name = "ALTERNATE_FLOW_CONTROL_TXT")
    private String alternateFlow;

}