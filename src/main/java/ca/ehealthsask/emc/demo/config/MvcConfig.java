package ca.ehealthsask.emc.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login-page");
        registry.addViewController("/remediation/message-inquiry").setViewName("remediation/message_inquiry");
        registry.addViewController("/remediation/data-remediation").setViewName("remediation/remediation_queue");
    }
}
