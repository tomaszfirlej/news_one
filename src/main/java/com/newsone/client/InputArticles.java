package com.newsone.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
class InputArticles {

    private List<InputArticle> articles;

    public InputArticles(List<InputArticle> inputArticles){
        this.articles = inputArticles;
    }
}