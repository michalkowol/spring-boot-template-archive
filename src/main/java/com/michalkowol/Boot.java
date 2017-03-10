package com.michalkowol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class Boot {
    public static void main(String[] args) {
        String profile = Optional.ofNullable(System.getProperty("environment")).orElse("default");
        SpringApplication springApplication = new SpringApplication(Boot.class);
        springApplication.setAdditionalProfiles(profile);
        springApplication.run(args);
    }
}
