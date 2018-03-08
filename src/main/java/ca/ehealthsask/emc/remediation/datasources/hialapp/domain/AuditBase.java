package ca.ehealthsask.emc.remediation.datasources.hialapp.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class AuditBase {

    private static final long serialVersionUID = 1L;

    @Column(name = "RECORD_CREATED_TS")
    private LocalDateTime recordCreatedTimestamp;
    @Column(name = "RECORD_CREATED_USERID")
    private String recordCreatedBy;
    @Column(name = "RECORD_CREATED_GUID")
    private String recordCreatedByGuid;
    @Column(name = "RECORD_UPDATED_TS")
    private LocalDateTime recordUpdatedTimestamp;
    @Column(name = "RECORD_UPDATED_USERID")
    private String recordUpdatedBy;
    @Column(name = "RECORD_UPDATED_GUID")
    private String recordUpdatedByGuid;
    @Version
    @Column(name = "RECORD_VERSION_NBR")
    private int recordVersionNo;
}