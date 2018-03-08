package ca.ehealthsask.emc.remediation.datasources.hialapp.domain;

import ca.ehealthsask.emc.remediation.datasources.hialapp.domain.referencecode.ReferenceCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "REMEDIATION_ERROR")
public class RemediationError implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "REMEDIATION_ERROR_ID")
    private Long remediationErrorId;
    @Column(name = "ERROR_DESCRIPTION_TXT")
    private String errorDescription;
    @Column(name = "ASSIGN_USER_NAME")
    private String assignUserName;
    @Column(name = "ASSIGN_USER_GUID")
    private String assignUserGuid;
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
    @JoinColumn(name = "REMEDIATION_MESSAGE_ID", referencedColumnName = "REMEDIATION_MESSAGE_ID")
    @ManyToOne(optional = false)
    private RemediationMessage remediationMessage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remediationError")
    @OrderBy
    private List<RemediationNote> remediationNotes;
    @JoinColumn(name = "REMEDIATION_ERROR_TYPE_ID", referencedColumnName = "REMEDIATION_ERROR_TYPE_ID")
    @ManyToOne
    private RemediationErrorType remediationErrorType;
    //these are kept around for fast lookups via the criteria api since we cant use objects comparisons with in clauses
    @Column(name = "RESOLUTION_TYPE_CDID", insertable=false, updatable=false)
    private Long resolutionTypeCdid;
    @Column(name = "REMEDIATION_PRIORITY_CDID", insertable=false, updatable=false)
    private Long remediationPriorityCdid;
    @Column(name = "REMEDIATION_STATUS_CDID", insertable=false, updatable=false)
    private Long remediationStatusCdid;
    @Column(name = "REMEDIATION_ERROR_TYPE_ID", insertable=false, updatable=false)
    private Long remediationErrorTypeCdid;    
    @Column(name = "REMEDIATION_MESSAGE_ID", insertable=false, updatable=false)
    private Long remediationMessageId;
    
    public RemediationError() {
    }

    public RemediationError(Long remediationErrorId) {
        this.remediationErrorId = remediationErrorId;
    }

    public Long getRemediationErrorId() {
        return remediationErrorId;
    }

    public void setRemediationErrorId(Long remediationErrorId) {
        this.remediationErrorId = remediationErrorId;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getAssignUserName() {
        return assignUserName;
    }

    public void setAssignUserName(String assignUserName) {
        this.assignUserName = assignUserName;
    }

    public String getAssignUserGuid() {
        return assignUserGuid;
    }

    public void setAssignUserGuid(String assignUserGuid) {
        this.assignUserGuid = assignUserGuid;
    }

    public String getRecordCreatedUserid() {
        return recordCreatedUserid;
    }

    public void setRecordCreatedUserid(String recordCreatedUserid) {
        this.recordCreatedUserid = recordCreatedUserid;
    }

    public String getRecordUpdatedUserid() {
        return recordUpdatedUserid;
    }

    public void setRecordUpdatedUserid(String recordUpdatedUserid) {
        this.recordUpdatedUserid = recordUpdatedUserid;
    }

    public List<RemediationNote> getRemediationNotes() {
        return remediationNotes;
    }

    public void setRemediationNotes(List<RemediationNote> remediationNoteCollection) {
        this.remediationNotes = remediationNoteCollection;
    }

    public RemediationErrorType getRemediationErrorType() {
        return remediationErrorType;
    }

    public void setRemediationErrorTypeId(RemediationErrorType remediationErrorType) {
        this.remediationErrorType = remediationErrorType;
    }

    public ReferenceCode getRemediationPriority() {
        return remediationPriority;
    }

    public void setRemediationPriority(ReferenceCode remediationPriority) {
        this.remediationPriority = remediationPriority;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (remediationErrorId != null ? remediationErrorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemediationError)) {
            return false;
        }
        RemediationError other = (RemediationError) object;
        return (this.remediationErrorId != null || other.remediationErrorId == null) && (this.remediationErrorId == null || this.remediationErrorId.equals(other.remediationErrorId));
    }

    @Override
    public String toString() {
        return "ca.ehealthsask.emc.remediation.datasources.hialapp.RemediationError[remediationErrorId=" + remediationErrorId + "]";
    }

    public ReferenceCode getRemediationStatus() {
        return remediationStatus;
    }

    public void setRemediationStatus(ReferenceCode remediationStatus) {
        this.remediationStatus = remediationStatus;
    }

    public ReferenceCode getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(ReferenceCode resolutionType) {
        this.resolutionType = resolutionType;
    }

    public RemediationMessage getRemediationMessage() {
        return remediationMessage;
    }

    public void setRemediationMessage(RemediationMessage remediationMessage) {
        this.remediationMessage = remediationMessage;
    }

    public Long getRemediationStatusCdid() {
        return remediationStatusCdid;
    }

    public void setRemediationStatusCdid(Long remediationStatusCdid) {
        this.remediationStatusCdid = remediationStatusCdid;
    }

    public Long getResolutionTypeCdid() {
        return resolutionTypeCdid;
    }

    public void setResolutionTypeCdid(Long resolutionTypeCdid) {
        this.resolutionTypeCdid = resolutionTypeCdid;
    }

    public Long getRemediationPriorityCdid() {
        return remediationPriorityCdid;
    }

    public void setRemediationPriorityCdid(Long remediationPriorityCdid) {
        this.remediationPriorityCdid = remediationPriorityCdid;
    }

    public Long getRemediationErrorTypeCdid() {
        return remediationErrorTypeCdid;
    }

    public void setRemediationErrorTypeCdid(Long remediationErrorTypeCdid) {
        this.remediationErrorTypeCdid = remediationErrorTypeCdid;
    }

    public Long getRemediationMessageId() {
        return remediationMessageId;
    }

    public void setRemediationMessageId(Long remediationMessageId) {
        this.remediationMessageId = remediationMessageId;
    }

}
