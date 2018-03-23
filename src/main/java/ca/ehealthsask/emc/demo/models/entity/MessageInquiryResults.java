package ca.ehealthsask.emc.demo.models.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class MessageInquiryResults implements Serializable{

    private static final long serialVersionUID = 1L;

    /*HIAL MESSAGE COLUMNS*/
    @Column(name = "INDEX_VALUE_TXT")
    private String indexValue;
    @Column(name = "MESSAGE_CONTROL_ID_TXT")
    private String messageControlId;
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageType;


    /*AUDIT LOG COLUMNS*/
    @Column(name = "SENDER_OID")
    private String senderOid;
    /*EVENT MINOR CODE*/
    @Column(name = "DESCRIPTION_TXT")
    private String description;
    @Id
    @Column(name = "MESSAGE_CORRELATION_UUID")
    private String messageCorrelationUUID;
    @Column(name = "INITIAL_EVENT_TS")
    private LocalDateTime initialEventTs;
    @Column(name = "LATEST_EVENT_TS")
    private LocalDateTime latestEventTs;


    /*OTHER*/
    @Column(name = "HIAL_MESSAGE_ID")
    private String hialMessageId;


    public MessageInquiryResults() {
    }

    public MessageInquiryResults(String indexValue, String messageControlId, String messageType, String senderOid, String description, String messageCorrelationUUID, LocalDateTime initialEventTs, LocalDateTime latestEventTs, String hialMessageId) {
        this.indexValue = indexValue;
        this.messageControlId = messageControlId;
        this.messageType = messageType;
        this.senderOid = senderOid;
        this.description = description;
        this.messageCorrelationUUID = messageCorrelationUUID;
        this.initialEventTs = initialEventTs;
        this.latestEventTs = latestEventTs;
        this.hialMessageId = hialMessageId;
    }
}
