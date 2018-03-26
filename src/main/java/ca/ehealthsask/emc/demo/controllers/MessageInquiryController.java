package ca.ehealthsask.emc.demo.controllers;

import ca.ehealthsask.emc.demo.models.entity.HialMessage;
import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.model.MessageLob;
import ca.ehealthsask.emc.demo.models.model.MessageInquiryParameters;
import ca.ehealthsask.emc.demo.models.model.MessageWorkflow;
import ca.ehealthsask.emc.demo.models.repository.AuditLogRepository;
import ca.ehealthsask.emc.demo.models.repository.HialMessageRepository;
import ca.ehealthsask.emc.demo.models.repository.MessageInquiryResultsRepositoryImpl;
import ca.ehealthsask.emc.demo.utils.EMCDateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class MessageInquiryController {

    @Autowired
    MessageInquiryResultsRepositoryImpl auditLogRepositoryImpl;

    @Autowired
    HialMessageRepository hialMessageRepository;
    @Autowired
    AuditLogRepository auditLogRepository;

    @GetMapping("/messages")
    public String getResults(Model model,
                             Pageable pageable,
                             HttpServletResponse response,
                             @RequestParam (value = "message-control-id",required = false) String msgControlId,
                             @RequestParam (value = "order-number",required = false) String orderNumber,
                             @RequestParam (value = "from-date",required = false) String fromDate,
                             @RequestParam (value = "from-time",required = false) String fromTime,
                             @RequestParam (value = "to-date",required = false) String toDate,
                             @RequestParam (value = "to-time",required = false) String toTime
                             ) throws IOException {
        System.out.println(new ObjectMapper().writeValueAsString(pageable));
        System.out.println(auditLogRepositoryImpl.toString());
        //d4ade223-9e9b-475d-b781-714c38c133c
        //        List<MessageInquiryResults> results = auditLogRepositoryImpl.getAuditLogs("d9a25327-a43d-401c-914f-02f4b7832169");
//        List<MessageInquiryResults> results = auditLogRepositoryImpl.getByParameters("41799469");

        //check to see if request parameters are blank
        //if they're all blank, send an error
        //if the time fields are filled without their dates, send an error
        if (StringUtils.isAllBlank(msgControlId, orderNumber, fromDate, toDate, fromTime, toTime)) {
            response.sendError(400, "Oops! Enter at least one of the following: Message Control ID, Order Number, From Date, To Date");
            throw new IllegalArgumentException("ERR001");
        } else if (StringUtils.isBlank(fromDate) && !StringUtils.isBlank(fromTime) || StringUtils.isBlank(toDate) && !StringUtils.isBlank(toTime)) {
            throw new IllegalArgumentException("ERR002");
        }

        //parsing the localdatetime objects from the time and date fields supplied and create a new messageInquiryParameter object
        LocalDateTime fromDateTime = EMCDateUtils.parseFromUserInput(fromDate, fromTime);
        LocalDateTime toDateTime = EMCDateUtils.parseFromUserInput(toDate, toTime);
        MessageInquiryParameters params
                = MessageInquiryParameters
                .builder()
                .messageControlId(msgControlId)
                .orderId(orderNumber)
                .fromDate(fromDateTime)
                .toDate(toDateTime)
                .build();

        //pass messageInquiryParameters into search function for message inquiry result list
        List<MessageInquiryResults> results = auditLogRepositoryImpl.getByParameters(params, pageable);
        log.info("Starting results fetching");
        log.info(results.toString());
        model.addAttribute("messageInquiryResults", results);
        response.setHeader("page", pageable.getPageNumber() + "");
        return "remediation/message_inquiry_results";
    }

    /**
     * Retrieves message lobs by inbound and transformed messages
     * @param messageUuid
     * @return
     */
    @GetMapping("/messages/{messageUuid}/lob")
    @ResponseBody
    public List<MessageLob> getMessageLobs(@PathVariable("messageUuid") String messageUuid) {
        List<Long> cdids = Arrays.asList(new Long[]{HialMessage.TRANSFORMED_MSG,HialMessage.INBOUND_MSG});
        List<MessageLob> lobs = hialMessageRepository.findByMessageCorrelationUuidAndMessageDiscriminatorCdidIn(messageUuid, cdids);
        return lobs;
    }

    /**
     * Retrieves message workflows
     * @param messageUuid
     * @return
     */
    @GetMapping("/messages/{messageUuid}/workflow")
    public String getMessageWorkflow(@PathVariable("messageUuid") String messageUuid, Model model) {
        List<MessageWorkflow> workflows = auditLogRepository.findWorkflowsByMessageCorrelationUuid(messageUuid);
        model.addAttribute("messageInquiryWorkflow", workflows);
        return "remediation/message_inquiry_workflow_table";
    }

}
