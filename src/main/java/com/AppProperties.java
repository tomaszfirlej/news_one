package com;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("conf")
@Getter
@Setter
public class AppProperties {

    private String apiKey;
    private String targetUrl;
    private int targetPort;
    private String targetSuffixTemplate;
    private int connectionTimeout;
    private int responseTimeout;

}