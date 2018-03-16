package ca.ehealthsask.emc.demo.remediation.datasources.event;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "AUDIT_LOG_INDEX")
public class AuditLogIndex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "AuditLogIndexSeq", sequenceName = "AUDIT_LOG_INDEX_S", allocationSize = 1)
    @GeneratedValue(generator = "AuditLogIndexSeq")
    @Column(name = "AUDIT_LOG_INDEX_ID")
    private Long auditLogIndexId;
    @Basic(optional = false)
    @Column(name = "INDEX_VALUE_TXT")
    private String indexValue;
    @JoinColumn(name = "AUDIT_LOG_ID", referencedColumnName = "AUDIT_LOG_ID")
    @ManyToOne(optional = false)
    private AuditLog auditLog;
    @JoinColumn(name = "AUDIT_LOG_INDEX_TYPE_ID", referencedColumnName = "AUDIT_LOG_INDEX_TYPE_ID")
    @ManyToOne(optional = false)
    private AuditLogIndexType auditLogIndexType;

}
