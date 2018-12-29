package com.newsone.server;

import com.newsone.logic.NewsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public OutputArticles findNews(final @PathVariable("country") String country,
                                   final @PathVariable("category") String category) {

        return newsManager.getForCategoryAndCountry(country, category);
    }
}