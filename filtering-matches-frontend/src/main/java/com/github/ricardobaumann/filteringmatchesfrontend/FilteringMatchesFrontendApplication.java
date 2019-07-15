package com.github.ricardobaumann.filteringmatchesfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FilteringMatchesFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilteringMatchesFrontendApplication.class, args);
    }

}
