package br.com.andreas.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogController {

    private Logger logger = LoggerFactory.getLogger(LogController.class.getName());

    @GetMapping("/test")
    public String testLog(){

        logger.debug("testLog Debug");
        logger.info("testLog Info");
        logger.warn("testLog warn");
        logger.error("testLog error");

        return "Logs generated successfully";
    }

}
