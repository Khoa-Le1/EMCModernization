package ca.ehealthsask.emc.demo.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "REMEDIATION_ERROR_TYPE")
public class RemediationErrorType implements Serializable {
    public static final Long CLIENT_RESOLUTION_CATEGORY = 147L;
    public static final Long PROVIDER_REGISRTY_CATEGORY = 183L;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "remediationErrorType")
    private Collection<RemediationError> remediationErrors;
}
