package ca.ehealthsask.emc.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileOutputStream;

/**
 * Partially for testing purposes
 *
 */
@Controller
public class HomeController {

    private static final String appName = "EMC";

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {

        model.addAttribute("name", appName);
        model.addAttribute("title", appName);
        return "home";
    }



//    @GetMapping("/remediation/messageInquiry")
//    public String remediationQueue(Model model,
//                       @RequestParam(value = "name", required = false,
//                               defaultValue = "Guest") String name) {
//
//        model.addAttribute("name", name);
//        model.addAttribute("title", appName);
//        return "remediation/message_inquiry";
//    }

    /**Testing
     *
     * @param model
     * @param name
     * @return
     */
    @GetMapping("/demo.html")
    public String demo(Model model,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {

        model.addAttribute("name", name);
        model.addAttribute("title", appName);
        return "remediation/demo";
    }
}