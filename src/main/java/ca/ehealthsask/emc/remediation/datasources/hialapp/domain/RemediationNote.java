package ca.ehealthsask.emc.remediation.datasources.hialapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "REMEDIATION_NOTE")
public class RemediationNote extends AuditBase implements Serializable {
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
    private LocalDateTime recordCreatedTs;
    @Basic(optional = false)
    @Column(name = "RECORD_CREATED_USERID")
    private String recordCreatedUserid;
    @Column(name = "RECORD_UPDATED_TS")
    private LocalDateTime recordUpdatedTs;
    @Column(name = "RECORD_UPDATED_USERID")
    private String recordUpdatedUserid;
    @Basic(optional = false)
    @Column(name = "RECORD_VERSION_NBR")
    private Long recordVersionNo;
    @JoinColumn(name = "REMEDIATION_ERROR_ID", referencedColumnName = "REMEDIATION_ERROR_ID")
    @ManyToOne(optional = false)
    private RemediationError remediationError;

    public RemediationNote() {
        recordCreatedTs = LocalDateTime.now();
        //todo: get a context holder to hold username
//        recordCreatedUserid = SecurityContextHolder.getUsername();
    }
}
