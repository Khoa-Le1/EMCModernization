package ca.ehealthsask.emc.demo.controllers;

import ca.ehealthsask.emc.demo.models.entity.DataRemediationResults;
import ca.ehealthsask.emc.demo.models.repository.DomainMessageTypeXrefRepository;
import ca.ehealthsask.emc.demo.models.repository.RemediationMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class DataRemediationController {

    @Autowired
    RemediationMessageRepository remediationMessageRepository;

    @Autowired
    DomainMessageTypeXrefRepository domainMessageTypeXrefRepository;

    @ResponseBody
    @GetMapping("/remediated_messages")
    public List<DataRemediationResults> getResults() {
        String orderNumber = "";
        String messageControlId = "00224837";
        List<DataRemediationResults> results = remediationMessageRepository.getByParametersMV(messageControlId, orderNumber);
//        List<DataRemediationResults> results = new ArrayList<>();

        return results;
    }

    @ResponseBody
    @GetMapping("/testing/some/{domain}")
    public List<String> getMessageTypes(@PathVariable("domain") String domain){
        if (domain.equals("LAB")) {
            return domainMessageTypeXrefRepository.findLabMessageTypes();
        } else {
            return domainMessageTypeXrefRepository.findCEAndMIRMessageTypes(domain);
        }
    }

}
