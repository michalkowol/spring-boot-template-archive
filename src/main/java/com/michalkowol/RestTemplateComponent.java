package com.michalkowol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestTemplateComponent {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
