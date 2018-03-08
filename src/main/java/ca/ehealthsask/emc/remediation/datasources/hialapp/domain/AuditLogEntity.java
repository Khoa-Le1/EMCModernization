package ca.ehealthsask.emc.remediation.datasources.hialapp.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="AUDIT_LOG", schema="EVENT")
public class AuditLogEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="AUDIT_LOG_ID")
    private long auditLogId;

    @Column(name="EVENT_MINOR_CDE")
    private String eventMinorCode;

    @Column(name="EVENT_MAJOR_CDE")
    private String eventMajorCode;

    @Column(name="EVENT_TS")
    private LocalDateTime eventTimestamp;

    @Column(name="MESSAGE_TYPE_TXT")
    private String messageType;

    @Column(name="DETAIL_TXT")
    private String detail;
}
