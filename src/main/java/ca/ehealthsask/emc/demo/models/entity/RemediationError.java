package ca.ehealthsask.emc.demo.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REMEDIATION_ERROR")
public class RemediationError implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "REMEDIATION_ERROR_ID")
    private Long remediationErrorId;
    @Column(name = "REMEDIATION_MESSAGE_ID", insertable=false, updatable=false)
    private Long remediationMessageId;
    @Column(name = "ERROR_DESCRIPTION_TXT")
    private String errorDescription;
    @Column(name = "ASSIGN_USER_NAME")
    private String assignUserName;
    @Column(name = "ASSIGN_USER_GUID")
    private String assignUserGuid;
    @Column(name = "REMEDIATION_ERROR_TYPE_ID", insertable=false, updatable=false)
    private Long remediationErrorTypeCdid;
    @Column(name = "RESOLUTION_TYPE_CDID", insertable=false, updatable=false)
    private Long resolutionTypeCdid;
    @Column(name = "REMEDIATION_STATUS_CDID", insertable=false, updatable=false)
    private Long remediationStatusCdid;
    @Column(name = "REMEDIATION_PRIORITY_CDID", insertable=false, updatable=false)
    private Long remediationPriorityCdid;
    @ManyToOne
    @JoinColumn(name = "RESOLUTION_TYPE_CDID")
    private ReferenceCode resolutionType;
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
    @OneToOne
    @JoinColumn(name = "REMEDIATION_STATUS_CDID")
    private ReferenceCode remediationStatus;
    @ManyToOne
    @JoinColumn(name = "REMEDIATION_PRIORITY_CDID")
    private ReferenceCode remediationPriority;

    @JsonBackReference
    @JoinColumn(name = "REMEDIATION_MESSAGE_ID", referencedColumnName = "REMEDIATION_MESSAGE_ID")
    @ManyToOne(optional = false)
    private RemediationMessage remediationMessage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remediationError")
    @OrderBy
    private List<RemediationNote> remediationNotes;


    @JsonBackReference
    @JoinColumn(name = "REMEDIATION_ERROR_TYPE_ID", referencedColumnName = "REMEDIATION_ERROR_TYPE_ID")
    @ManyToOne
    private RemediationErrorType remediationErrorType;
    //these are kept around for fast lookups via the criteria api since we cant use objects comparisons with in clauses
}
