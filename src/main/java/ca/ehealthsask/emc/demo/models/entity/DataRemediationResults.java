package ca.ehealthsask.emc.demo.models.entity;

import jooq.generated.hial.tables.RemediationNote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class DataRemediationResults {

    @Column(name = "MESSAGE_CONTROL_ID_TXT")
    private String messageControlId;
    @Column(name = "INDEX_VALUE_TXT")
    private String indexNumber;
    @Column(name = "SENDER_TXT")
    private String sender;
    @Column(name = "ERROR_DATE")
    private LocalDateTime errorDate;
    @Column(name = "MESSAGE_TYPE_TXT")
    private String messageType;
    @Column(name = "ERROR_TYPE")
    private String errorType;
    @Column(name = "ERROR_DESCRIPTION")
    private String errorDescription;
    @Column(name = "STATUS_TXT")
    private String status;
    @Column(name = "ASSIGNEE_TXT")
    private String assignee;
    @Column(name = "RESOLUTION_TYPE_CDID")
    private String resolutionType;
    @Column(name = "LAST_UPDATED_TS")
    private LocalDateTime lastUpdated;
    @Column(name = "NOTE_TXT")
    private String note;
    @Id
    @Column(name = "MESSAGE_CORRELATION_UUID")
    private String messageCorrelationUuid;
//    String getMessageControlId();
//    String getVisitNumber();
//    String getSource();
////    LocalDateTime getErrorDate();
////    String getErrorDescription();
////    String getStatus();
////    String getAssignee();
////    String getResolutionType();
////    String getLastUpdate();
////    String getNotes();
}
