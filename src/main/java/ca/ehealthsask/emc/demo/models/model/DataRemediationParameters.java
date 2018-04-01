package ca.ehealthsask.emc.demo.models.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataRemediationParameters {
    private String messageControlId;
    private String orderNumber;
    private List<String> status;
    private String assignedTo;
    private String errorCategory;
    private List<String> errorType;
    private String errorDescription;
    private String routable;
    private List<String> resolutionType;
    private List<String> source;
    private List<String> messageType;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

}
