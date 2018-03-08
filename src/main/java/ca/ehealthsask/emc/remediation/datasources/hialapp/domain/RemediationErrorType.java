package ca.ehealthsask.emc.remediation.datasources.hialapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "REMEDIATION_ERROR_TYPE", schema = "HIAL")
public class RemediationErrorType implements Serializable {
    public static final Long CLIENT_RESOLUTION_CATEGORY = 147L;
    public static final Long PROVIDER_REGISTRY_CATEGORY = 183L;
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "REMEDIATION_ERROR_TYPE_ID")
    private Long remediationErrorTypeId;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION_TXT")
    private String descriptionText;
    @Basic(optional = false)
    @Column(name = "ERROR_CATEGORY_CDID")
    private Long errorCategoryCdid;
    @Column(name = "RECORD_CREATED_TS")
    private LocalDateTime recordCreatedTs;
    @Column(name = "RECORD_CREATED_USERID")
    private String recordCreatedUserid;
    @Column(name = "RECORD_UPDATED_TS")
    private LocalDateTime recordUpdatedTs;
    @Column(name = "RECORD_UPDATED_USERID")
    private String recordUpdatedUserid;
    @Basic(optional = false)
    @Column(name = "RECORD_VERSION_NBR")
    private Long recordVersionNo;
    @OneToMany(mappedBy = "remediationErrorType")
    private Collection<RemediationError> remediationErrors;

    public RemediationErrorType(){}

    public RemediationErrorType(Long remediationErrorTypeId) {
        this.remediationErrorTypeId = remediationErrorTypeId;
    }
}
