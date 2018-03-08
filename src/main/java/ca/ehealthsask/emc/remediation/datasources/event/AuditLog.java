package ca.ehealthsask.emc.remediation.datasources.event;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "AUDIT_LOG", schema = "EVENT")
public class AuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "AuditLogSeq", sequenceName = "AUDIT_LOG_S", allocationSize = 1)
    @GeneratedValue(generator = "AuditLogSeq")
    @Column(name = "AUDIT_LOG_ID")
    private Long auditLogId;
    @Column(name = "EVENT_TS")
    private LocalDateTime eventTs;
    @Basic(optional = false)
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageType;
    @Column(name = "DETAIL_TXT")
    private String detail;
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID_TXT")
    private String messageId;
    @Column(name = "SENDER_OID")
    private String senderOid;
    @Column(name = "RECEIVER_OID")
    private String receiverOid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditLog")
    private Collection<AuditLogIndex> auditLogIndexes = new ArrayList<AuditLogIndex>();
    @Column(name = "EVENT_MINOR_CDE")
    private String eventMinorCode;
    @Column(name = "EVENT_MAJOR_CDE")
    private String eventMajorCode;
    @Column(name = "MESSAGE_CORRELATION_UUID")
    private String messageCorrelationUUID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditLog")
    private Collection<AuditLogUser> auditLogUsers = new ArrayList<AuditLogUser>();
    @ManyToMany
    @JoinTable(name = "HIAL_MESSAGE", joinColumns = {
            @JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "MESSAGE_CORRELATION_UUID", referencedColumnName = "MESSAGE_CORRELATION_UUID")
    })
    private Collection<AuditLogHialMessage> hialMessages;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "EVENT_MINOR_CDE", referencedColumnName = "EVENT_MINOR_CDE", insertable = false, updatable = false),
            @JoinColumn(name = "EVENT_MAJOR_CDE", referencedColumnName = "EVENT_MAJOR_CDE", insertable = false, updatable = false)
    })
//    private EventMinorCode eventMinorCodeReference;
    @Column(name = "ALTERNATE_FLOW_CONTROL_TXT")
    private String alternateFlow;
}