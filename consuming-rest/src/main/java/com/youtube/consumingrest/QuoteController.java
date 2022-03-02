package com.youtube.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {
    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApp.class);
    private RestTemplateBuilder ctrlBuild;

    @GetMapping("/quote")
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
        return args -> {
            Quote quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
            ctrlBuild.build();
        };
    }
}