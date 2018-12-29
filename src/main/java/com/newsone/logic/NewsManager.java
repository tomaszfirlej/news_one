package com.newsone.logic;

import com.newsone.client.NewsClient;
import com.newsone.client.InputArticle;
import com.newsone.server.OutputArticle;
import com.newsone.server.OutputArticles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsManager {

    private final NewsClient newsClient;

    @Autowired
    public NewsManager(NewsClient newsClient) {
        this.newsClient = newsClient;
    }

    public OutputArticles getForCategoryAndCountry(final String country, final String category) {
        List<InputArticle> inputArticles = newsClient.getForCountryAndCategory(country, category);
        List<OutputArticle> outputArticles = convertArticlesToOutputFormat(inputArticles);
        return new OutputArticles(country, category, outputArticles);
    }

    private List<OutputArticle> convertArticlesToOutputFormat(final List<InputArticle> inputArticles) {
        return inputArticles.stream()
                .map(OutputArticle::new)
                .collect(Collectors.toList());
    }
}