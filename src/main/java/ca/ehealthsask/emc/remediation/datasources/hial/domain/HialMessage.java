package ca.ehealthsask.emc.remediation.datasources.hial.domain;

import ca.ehealthsask.emc.remediation.datasources.hialapp.domain.RemediationMessage;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "HIAL_MESSAGE", schema = "HIAL")
public class HialMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Long TRANSFORMED_MSG = 148L;
    public static final Long INBOUND_MSG = 149L;
    public static final Long MSG_REPONSE =151L;

    @Id
    @Basic(optional = false)
    @Column(name = "HIAL_MESSAGE_ID")
    private Long hialMessageId;
    @Column(name = "MESSAGE_CORRELATION_UUID")
    private String messageCorrelationUuid;
    @Column(name = "MESSAGE_DISCRIMINATOR_CDID")
    private Long messageDiscriminatorId;
    @Column(name = "MESSAGE_CONTROL_ID_TXT")
    private String messageControlId;
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageType;
    @Column(name = "ORDER_NUMBER_TXT")
    private String orderNumber;
    @Column(name = "SENDER_TXT")
    private String sender;
    @Column(name = "RECEIVER_TXT")
    private String receiver;
    @Column(name = "MESSAGE_SUBTYPE_TXT")
    private String messageSubType;
    @Lob
    @Column(name = "MESSAGE_LOB")
    private String messageLob;
    @OneToMany(mappedBy = "hialMessage")
    private Collection<RemediationMessage> remediationMessages;


}
