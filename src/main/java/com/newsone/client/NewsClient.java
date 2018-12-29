package com.newsone.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class NewsClient {

    private static final String NEWS_API_KEY = "c1f7a5faf193437ea1319ccac00adc1d";
    private static final String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=%s&category=%s&apiKey=%s";

    public List<InputArticle> getForCountryAndCategory(final String country, final String category) {
        RestTemplate restTemplate = new RestTemplate();
        InputArticles inputArticles = restTemplate.getForObject(String.format(NEWS_URL, country, category, NEWS_API_KEY), InputArticles.class);
        return inputArticles != null ? inputArticles.getArticles() : Collections.emptyList();
    }
}