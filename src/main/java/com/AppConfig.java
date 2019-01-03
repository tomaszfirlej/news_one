package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    private final AppProperties appProperties;

    @Autowired
    public AppConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(appProperties.getConnectionTimeout()))
                .setReadTimeout(Duration.ofMillis(appProperties.getResponseTimeout()))
                .build();
    }
}