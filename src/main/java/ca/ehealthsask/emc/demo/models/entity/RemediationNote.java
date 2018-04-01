package ca.ehealthsask.emc.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REMEDIATION_NOTE")
public class RemediationNote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "remediationNoteId")
    @SequenceGenerator(name = "remediationNoteId", sequenceName = "REMEDIATION_NOTE_S", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "REMEDIATION_NOTE_ID")
    private Long remediationNoteId;
    @Column(name = "NOTE_TXT")
    private String note;
    @Basic(optional = false)
    @Column(name = "RECORD_CREATED_TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordCreatedTs;
    @Basic(optional = false)
    @Column(name = "RECORD_CREATED_USERID")
    private String recordCreatedUserid;
    @Column(name = "RECORD_UPDATED_TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordUpdatedTs;
    @Column(name = "RECORD_UPDATED_USERID")
    private String recordUpdatedUserid;
    @Basic(optional = false)
    @Column(name = "RECORD_VERSION_NBR")
    private Long recordVersionNo;
    @JoinColumn(name = "REMEDIATION_ERROR_ID", referencedColumnName = "REMEDIATION_ERROR_ID")
    @ManyToOne(optional = false)
    private RemediationError remediationError;
}
