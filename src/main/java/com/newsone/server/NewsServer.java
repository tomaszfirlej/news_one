package com.newsone.server;

import com.newsone.core.NewsManager;
import com.newsone.core.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Scope("session")
@RequestMapping("/news")
@RestController
public class NewsServer {

    private final NewsManager newsManager;

    @Autowired
    public NewsServer(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    @GetMapping(value = "/{country}/{category}")
    public OutputArticles findNews(final @PathVariable("country") String country, final @PathVariable("category") Category category) {
        List<OutputArticle> outputArticles = newsManager.getForCategoryAndCountry(country, category);
        return new OutputArticles(country, category, outputArticles);
    }

    @GetMapping(value = "/categories")
    public Category[] getAllCategories() {
        return Category.values();
    }

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError invalidRequestParameterHandler() {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "There was an error. Try again later");
    }
}