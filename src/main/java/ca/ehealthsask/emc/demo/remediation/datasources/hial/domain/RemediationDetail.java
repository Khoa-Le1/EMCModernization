package ca.ehealthsask.emc.demo.remediation.datasources.hial.domain;

import ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.referencecode.ReferenceCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "REMEDIATION_DETAIL")
public class RemediationDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "REMEDIATION_DETAILS_ID")
    private Long remediationDetailId;
    @Column(name = "HIAL_MESSAGE_ID")
    private Long hialMessageID;
    @Column(name = "MESSAGE_CORRELATION_UUID")
    private String messageCorrelationUUID;
    @Column(name = "MESSAGE_DISCRIMINATOR_CDID")
    private Long messageDiscriminatorCDID;
    @ManyToOne
    @JoinColumn(name = "RESOLUTION_TYPE_CDID")
    private ReferenceCode resolutionType;
    @Column(name = "SENDER_TXT")
    private String senderText;
    @Column(name = "MESSAGE_CONTROL_ID_TXT")
    private String messageControlIDText;
    @Column(name = "MESSAGE_SUBTYPE_TXT")
    private String messageSubTypeText;
    @Column(name = "REMEDIATION_ERROR_ID")
    private String remediationErrorID;
    @Column(name = "RECORD_CREATED_TS")
    private String recordCreatedTS;
    @Column(name = "ERROR_DESCRIPTION_TXT")
    private String errorDescriptionText;
    @Column(name = "ORDER_NUMBER_TXT")
    private String orderNumberText;
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageTypeText;
    @Column(name = "REMEDIATION_ERROR_ID")
    private String remediationErrorId;

    //More will be included later...

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

    public RemediationDetail() {
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
        if (!(object instanceof RemediationDetail)) {
            return false;
        }
        RemediationDetail other = (RemediationDetail) object;
        return (this.remediationErrorId != null || other.remediationErrorId == null) && (this.remediationErrorId == null || this.remediationErrorId.equals(other.remediationErrorId));
    }

    @Override
    public String toString() {
        return "ca.ehealthsask.emc.demo.remediation.datasources.hialapp.RemediationError[remediationErrorId=" + remediationErrorId + "]";
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
