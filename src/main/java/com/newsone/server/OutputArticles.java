package com.newsone.server;

import lombok.Getter;

import java.util.List;

@Getter
public class OutputArticles {

    private String country;
    private String category;
    private List<OutputArticle> articles;

    public OutputArticles(String country, String category, List<OutputArticle> articles) {
        this.country = country;
        this.category = category;
        this.articles = articles;
    }
}