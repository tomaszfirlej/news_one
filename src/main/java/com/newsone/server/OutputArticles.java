package com.newsone.server;

import com.newsone.core.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OutputArticles {

    private String country;
    private String category;
    private List<OutputArticle> articles;

    public OutputArticles(String country, Category category, List<OutputArticle> articles) {
        this.country = country;
        this.category = category.toString();
        this.articles = articles;
    }
}