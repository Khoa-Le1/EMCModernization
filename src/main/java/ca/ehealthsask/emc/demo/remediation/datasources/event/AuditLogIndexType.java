package ca.ehealthsask.emc.demo.remediation.datasources.event;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "AUDIT_LOG_INDEX_TYPE")
public class AuditLogIndexType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AUDIT_LOG_INDEX_TYPE_ID")
    private Long auditLogIndexTypeId;
    @Basic(optional = false)
    @Column(name = "AUDIT_LOG_INDEX_TYPE_TXT")
    private String auditLogIndexType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditLogIndexType")
    private Collection<AuditLogIndex> auditLogIndexes;

}
