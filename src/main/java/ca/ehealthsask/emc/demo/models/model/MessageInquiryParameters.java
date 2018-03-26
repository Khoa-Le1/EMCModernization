package ca.ehealthsask.emc.demo.models.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageInquiryParameters {

    private String messageControlId;
    private String orderId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String source;
}
