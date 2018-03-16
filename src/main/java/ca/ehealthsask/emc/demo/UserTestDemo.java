package ca.ehealthsask.emc.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.ehealthsask.emc.demo.Entity.User;
import ca.ehealthsask.emc.demo.Repository.UserRepository;


public class UserTestDemo {

    private static final Logger log = LoggerFactory.getLogger(UserTestDemo.class);

    @Autowired
    private UserRepository userRepository;

    //@Override
    public void run(String... args){
        User Frank = new User(0005, "Delly", "Frank", "SomeStreet", "REgina");
        User Jerry = new User(0006, "Thomson","Jerry", "SomeStreet", "CCC");
        userRepository.save(Jerry);
        userRepository.save(Frank);
        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : userRepository.findAll()) {
            log.info(user.toString());
        }
    }



}
