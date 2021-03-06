package com.newsone.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputArticle {

    private ArticleSource source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

}