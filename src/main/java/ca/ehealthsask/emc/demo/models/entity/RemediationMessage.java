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
@Table(name = "REMEDIATION_MESSAGE", schema = "HIAL")
public class RemediationMessage implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "REMEDIATION_MESSAGE_ID")
    private Long remediationMessageId;
    @Column(name = "REMEDIATION_MESSAGE_STTS_CDID")
    private Long remediationMessageSttsCdid;
    @Column(name = "RECORD_CREATED_TS")
    private LocalDateTime recordCreatedTs;
    @Column(name = "RECORD_CREATED_USERID")
    private String recordCreatedUserId;
    @Column(name = "RECORD_UPDATED_TS")
    private LocalDateTime recordUpdatedTs;
    @Column(name = "RECORD_UPDATED_USERID")
    private String recordUpdatedUserId;
    @Column(name = "RECORD_VERSION_NBR")
    private Integer recordVersionNumber;
    @Column(name = "HIAL_MESSAGE_ID")
    private Long hialMessageId;
    @Column(name = "DOMAIN_TYPE_CDID")
    private Long domainTypeCdid;
    @Column(name = "REMEDIATION_CATEGORY_CDID")
    private Long remediationCategoryCdid;
    @Column(name = "SENDER_TXT")
    private String sender;
    @Column(name = "SENDING_APPLICATION_TXT")
    private String sendingApplication;
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageType;
    @Column(name = "REMEDIATION_PRIORITY_CDID")
    private Long remediationPriorityCdid;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remediationMessage")
    private Collection<RemediationError> remediationErrors;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HialMessage hialMessage;

    //for materialized view
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MvHialMsgAuditLog mvHialMsgAuditLog;
}
