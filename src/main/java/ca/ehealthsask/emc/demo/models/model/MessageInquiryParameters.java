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

    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String messageControlId;
    private String orderId;

//    public String createConditions(){
//        StringBuilder whereConditions = new StringBuilder();
//        List<String> params = new ArrayList<>();
//        if (messageControlId != null && !messageControlId.isEmpty()) {
//            whereConditions.append("hm.message_control_id = ?");
//        }
//        if (fromDate != null){
//
//        }
//        return whereConditions;
//    }
}
