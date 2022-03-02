package com.baeldung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * modelo de applicação utilizando o Spring boot
 * com a anotação @SpringBootApplication que é
 * equivalente ao uso das anotações @Configuration, @EnableAutoConfiguration e @ComponentScan.
 */
@SpringBootApplication
public class BaeldungApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaeldungApplication.class, args);
    }
}