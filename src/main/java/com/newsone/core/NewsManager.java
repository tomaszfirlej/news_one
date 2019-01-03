package com.newsone.core;

import com.newsone.client.InputArticle;
import com.newsone.client.NewsClient;
import com.newsone.core.enums.Category;
import com.newsone.server.OutputArticle;
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

    public List<OutputArticle> getForCategoryAndCountry(final String country, final Category category) {
        List<InputArticle> inputArticles = newsClient.getNewsForCountryAndCategory(country, category);
        return convertArticlesToOutputFormat(inputArticles);
    }

    private List<OutputArticle> convertArticlesToOutputFormat(final List<InputArticle> inputArticles) {
        return inputArticles.stream()
                .map(OutputArticle::new)
                .collect(Collectors.toList());
    }
}