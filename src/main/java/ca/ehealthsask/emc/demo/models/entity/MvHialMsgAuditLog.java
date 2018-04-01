package ca.ehealthsask.emc.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MV_HIAL_MSG_AUDIT_LOG")
public class MvHialMsgAuditLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "HIAL_MESSAGE_ID")
    private Long hialMessageId;
    @Lob
    @Column(name = "MESSAGE_LOB")
    private String messageLob;
    @Column(name = "MESSAGE_CORRELATION_UUID")
    private String messageCorrelationUuid;
    @Column(name = "MESSAGE_DISCRIMINATOR_CDID")
    private Long messageDiscriminatorCdid;
    @Column(name = "MESSAGE_CONTROL_ID_TXT")
    private String messageControlId;
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageType;
    @Column(name = "ORDER_NUMBER_TXT")
    private String orderNumber;
    @Column(name = "SENDER_TXT")
    private String sender;
    @Column(name = "RECEIVER_TXT")
    private Long receiver;
    @Column(name = "MESSAGE_SUBTYPE_TXT")
    private Long messageSubtype;
    @Column(name = "RECORD_CREATED_TS")
    private LocalDateTime recordCreatedTs;
    @Column(name = "RECORD_CREATED_USERID")
    private String recordCreatedUserId;
    @Column(name = "RECORD_UPDATED_TS")
    private LocalDateTime recordUpdatedTs;
    @Column(name = "RECORD_UPDATED_USERID")
    private String recordUpdatedUserId;
    @Column(name = "RECORD_VERSION_NBR")
    private Integer recordVersionNumber;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mvHialMsgAuditLog")
    @OneToMany( mappedBy = "mvHialMsgAuditLog")
    private Collection<RemediationMessage> remediationMessages;

    //audit log stuff
    @Column(name = "WORKFLOW_PATH")
    private String alWorkflowPath;
    @Column(name = "AUDIT_LOG_ID")
    private Long alAuditLogId;
    @Column(name = "EVENT_MINOR_CDE")
    private String alEventMinorCode;
    @Column(name = "EVENT_MAJOR_CDE")
    private String alEventMajorCode;
    @Column(name = "EVENT_TS")
    private LocalDateTime alEventTs;
    @Column(name = "AL_MESSAGE_TYPE_TXT")
    private String alMessageType;
    @Column(name = "DETAIL_TXT")
    private String alDetail;
    @Column(name = "MESSAGE_ID_TXT")
    private String messageId;
    @Column(name = "SENDER_OID")
    private String alSender;
    @Column(name = "RECEIVER_OID")
    private String alReceiver;
    @Column(name = "AL_MESSAGE_CORRELATION_UUID")
    private String alMessageCorrelationUuid;
    @Column(name = "ALTERNATE_FLOW_CONTROL_TXT")
    private String alAlternateFlowControl;

}
