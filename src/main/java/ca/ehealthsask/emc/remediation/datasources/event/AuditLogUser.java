package ca.ehealthsask.emc.remediation.datasources.event;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "AUDIT_LOG_USER", schema = "EVENT")
public class AuditLogUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "AuditLogUserSeq", sequenceName = "AUDIT_LOG_USER_S", allocationSize = 1)
    @GeneratedValue(generator = "AuditLogUserSeq")
    @Column(name = "AUDIT_LOG_USER_ID")
    private Long auditLogUserId;
    @Column(name = "USER_ROLE_CDID")
    private Long userRoleCdid;
    @Column(name = "USER_ID_TYPE_OID")
    private String userIdTypeOid;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "LOCATION_OID")
    private String locationOid;
    @Column(name = "ORGANIZATION_ID_TXT")
    private String organizationIdTxt;
    @JoinColumn(name = "AUDIT_LOG_ID", referencedColumnName = "AUDIT_LOG_ID")
    @ManyToOne(optional = false)
    private AuditLog auditLog;

    public AuditLogUser(){}
}
