package ca.ehealthsask.emc.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditBase implements HasId{

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
