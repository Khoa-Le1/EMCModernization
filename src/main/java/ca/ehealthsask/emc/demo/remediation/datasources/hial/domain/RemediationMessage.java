package ca.ehealthsask.emc.demo.remediation.datasources.hial.domain;

import ca.ehealthsask.emc.demo.remediation.datasources.hial.domain.HialMessage;
import ca.ehealthsask.emc.demo.remediation.datasources.hial.domain.RemediationError;
import ca.ehealthsask.emc.demo.remediation.datasources.hialapp.domain.referencecode.ReferenceCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "REMEDIATION_MESSAGE", schema = "HIAL")
public class RemediationMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REMEDIATION_MESSAGE_ID")
    private Long remediationMessageId;
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
    @Basic(optional = false)
    @Column(name = "REMEDIATION_MESSAGE_STTS_CDID")
    private Long remediationMessageSttsCdid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remediationMessage")
    private Collection<RemediationError> remediationErrors;
    @JoinColumn(name = "HIAL_MESSAGE_ID", referencedColumnName = "HIAL_MESSAGE_ID")
    @ManyToOne
    private HialMessage hialMessage;
    @JoinColumn(name = "DOMAIN_TYPE_CDID", referencedColumnName = "REFERENCE_CDID")
    @ManyToOne
    private ReferenceCode domainType;
    @Column(name = "REMEDIATION_CATEGORY_CDID")
    private Long remediationCategory;
    @Column(name = "SENDER_TXT")
    private String sender;
    @Column(name = "SENDING_APPLICATION_TXT")
    private String sendingApplication;
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageType;
    @JoinColumn(name = "REMEDIATION_PRIORITY_CDID", referencedColumnName = "REFERENCE_CDID")
    @ManyToOne
    private ReferenceCode priority;
}
