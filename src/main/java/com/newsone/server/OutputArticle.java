package com.newsone.server;

import com.newsone.client.InputArticle;
import lombok.Getter;

@Getter
public class OutputArticle {

    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;

    public OutputArticle(InputArticle inputArticle) {
        this.author = inputArticle.getAuthor();
        this.title = inputArticle.getTitle();
        this.description = inputArticle.getDescription();
        this.date = inputArticle.getPublishedAt();
        this.sourceName = inputArticle.getSource().getName();
        this.articleUrl = inputArticle.getUrl();
        this.imageUrl = inputArticle.getUrlToImage();
    }
}