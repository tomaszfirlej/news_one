package com.newsone.client;

import com.AppProperties;
import com.newsone.core.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class NewsClient {

    private final AppProperties appProperties;
    private final RestTemplate restTemplate;

    @Autowired
    public NewsClient(AppProperties appProperties, RestTemplate restTemplate) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
    }

    public List<InputArticle> getNewsForCountryAndCategory(final String country, final Category category) {

        InputArticles inputArticles;

        try {
            String targetUrl = getTargetUrl(country, category);
            inputArticles = restTemplate.getForObject(targetUrl, InputArticles.class);
        } catch (RestClientException e) {
            // log exception
            // etc.
            throw e;
        }

        return inputArticles != null ? inputArticles.getArticles() : Collections.emptyList();
    }

    private String getTargetUrl(String country, Category category) {
        return String.format(appProperties.getTargetUrl().concat(appProperties.getTargetSuffixTemplate()), country, category, appProperties.getApiKey());
    }
}