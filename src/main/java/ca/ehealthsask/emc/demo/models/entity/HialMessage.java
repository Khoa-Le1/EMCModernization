package ca.ehealthsask.emc.demo.models.entity;

//import ca.ehealthsask.emc.demo.utils.datasources.hial.domain.RemediationMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HIAL_MESSAGE", schema = "HIAL")
public class HialMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Long TRANSFORMED_MSG = 148L;
    public static final Long INBOUND_MSG = 149L;
    public static final Long MSG_RESPONSE =151L;

    @Id
    @Basic(optional = false)
    @Column(name = "HIAL_MESSAGE_ID")
    private Long hialMessageId;
    @Lob
    @Column(name = "MESSAGE_LOB")
    private String messageLob;
    @Column(name = "MESSAGE_CORRELATION_UUID")
    private String messageCorrelationUuid;
    @Column(name = "MESSAGE_DISCRIMINATOR_CDID")
    private Long messageDiscriminatorCdid;
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
    @OneToMany(mappedBy = "hialMessage")
    private Collection<RemediationMessage> remediationMessages;


}
