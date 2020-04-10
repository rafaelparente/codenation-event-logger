package com.rafaelparente.eventlogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EventLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventLoggerApplication.class, args);
    }

}
