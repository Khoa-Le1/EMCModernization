package ca.ehealthsask.emc.demo.controllers;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import ca.ehealthsask.emc.demo.models.repository.MessageInquiryResultsRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Slf4j
public class MessageInquiryController {

//    private static Logger logger = LoggerFactory.getLogger(MessageInquiryController.class);
    @Autowired
    MessageInquiryResultsRepositoryImpl auditLogRepositoryImpl;

    @GetMapping("/messages")
    public String getResults(Model model, Pageable pageable) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(pageable));
        System.out.println(auditLogRepositoryImpl.toString());
        //d4ade223-9e9b-475d-b781-714c38c133c
        //        List<MessageInquiryResults> results = auditLogRepositoryImpl.getAuditLogs("d9a25327-a43d-401c-914f-02f4b7832169");
        List<MessageInquiryResults> results = auditLogRepositoryImpl.getJoin("41799469");
        log.info("Starting results fetching");
        model.addAttribute("messageInquiryResults", results);
        return "remediation/message_inquiry_results";
    }
//    @GetMapping({"/messages"})
//    public Page<AuditLog> getResults() {
//
//        Sort sort =  new Sort(new Sort.Order(Sort.Direction.ASC, "eventTs"));
//        Pageable pageable = new PageRequest(1,5);
//        System.out.println(auditLogRepository);
//        Page<AuditLog> log = auditLogRepository.findAllByMessageCorrelationUUID(pageable, "d9a25327-a43d-401c-914f-02f4b7832169");
//            Page<AuditLog> log = auditLogRepository.findAll(pageable);
//            ObjectMapper mapper = new ObjectMapper();
//            String pageAsString = mapper.writeValueAsString(log);
//            System.out.println(pageAsString);
//
//        logger.info("Starting results fetching");
//        return log;
//    }
}
