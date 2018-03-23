package ca.ehealthsask.emc.demo.models.entity;


//import ca.ehealthsask.emc.demo.utils.datasources.event.AuditLogIndex;
//import ca.ehealthsask.emc.demo.utils.datasources.event.AuditLogUser;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name = "AUDIT_LOG", schema = "EVENT")
public class AuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
//    @SequenceGenerator(name = "AuditLogSeq", sequenceName = "AUDIT_LOG_S", allocationSize = 1)
//    @GeneratedValue(generator = "AuditLogSeq")
    @Column(name = "AUDIT_LOG_ID")//
    private Long auditLogId;
    @Column(name = "EVENT_TS")//
    private String eventTs;
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
    private String eventMinorCode;
    @Column(name = "EVENT_MAJOR_CDE")//
    private String eventMajorCode;
    @Column(name = "MESSAGE_CORRELATION_UUID")//
    private String messageCorrelationUUID;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditLog")
//    private Collection<AuditLogUser> auditLogUsers = new ArrayList<AuditLogUser>();
//    @ManyToMany
//    @JoinTable(name = "HIAL_MESSAGE", joinColumns = {
//            @JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID")
//    }, inverseJoinColumns = {
//            @JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID")
//    })
//    private Collection<HialMessage> hialMessages;
//    @OneToOne
//    @JoinColumns({
//            @JoinColumn(name = "EVENT_MINOR_CDE", referencedColumnName = "EVENT_MINOR_CDE", insertable = false, updatable = false),
//            @JoinColumn(name = "EVENT_MAJOR_CDE", referencedColumnName = "EVENT_MAJOR_CDE", insertable = false, updatable = false)
//    })
//    private EventMinorCode eventMinorCodeReference;
    @Column(name = "ALTERNATE_FLOW_CONTROL_TXT")
    private String alternateFlow;

    public AuditLog(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Long auditLogId) {
        this.auditLogId = auditLogId;
    }

    public String getEventTs() {
        return eventTs;
    }

    public void setEventTs(String eventTs) {
        this.eventTs = eventTs;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderOid() {
        return senderOid;
    }

    public void setSenderOid(String senderOid) {
        this.senderOid = senderOid;
    }

    public String getReceiverOid() {
        return receiverOid;
    }

    public void setReceiverOid(String receiverOid) {
        this.receiverOid = receiverOid;
    }

    public String getEventMinorCode() {
        return eventMinorCode;
    }

    public void setEventMinorCode(String eventMinorCode) {
        this.eventMinorCode = eventMinorCode;
    }

    public String getEventMajorCode() {
        return eventMajorCode;
    }

    public void setEventMajorCode(String eventMajorCode) {
        this.eventMajorCode = eventMajorCode;
    }

    public String getMessageCorrelationUUID() {
        return messageCorrelationUUID;
    }

    public void setMessageCorrelationUUID(String messageCorrelationUUID) {
        this.messageCorrelationUUID = messageCorrelationUUID;
    }

    public String getAlternateFlow() {
        return alternateFlow;
    }

    public void setAlternateFlow(String alternateFlow) {
        this.alternateFlow = alternateFlow;
    }
}