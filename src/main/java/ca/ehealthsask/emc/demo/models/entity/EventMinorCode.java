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
@Table(name = "EVENT_MINOR_CODE")
public class EventMinorCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private EventMinorCodePK eventMinorCodePK;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION_TXT")
    private String descriptionTxt;
    @Basic(optional = false)
    @Column(name = "PRIVACY_IND")
    private Character privacyInd;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Basic(optional = false)
    @Column(name = "RECORD_CREATED_TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordCreatedTs;
    @Basic(optional = false)
    @Column(name = "RECORD_CREATED_USERID")
    private String recordCreatedUserId;
    @Column(name = "RECORD_UPDATED_TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordUpdatedTs;
    @Column(name = "RECORD_UPDATED_USERID")
    private String recordUpdatedUserid;
    @Basic(optional = false)
    @Column(name = "RECORD_VERSION_NBR")
    private Long recordVersionNbr;

    public EventMinorCode(EventMinorCodePK eventMinorCodePK) {
        this.eventMinorCodePK = eventMinorCodePK;
    }
}