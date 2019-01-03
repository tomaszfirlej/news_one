package com.newsone.core;

import com.newsone.client.InputArticle;
import com.newsone.client.NewsClient;
import com.newsone.core.enums.Category;
import com.newsone.server.OutputArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static com.newsone.utils.InputArticleHelper.INPUT_ARTICLE;
import static com.newsone.utils.InputArticleHelper.checkIfMatches;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class NewsManagerTest {

    private static final Category CATEGORY = Category.technology;
    private static final String COUNTRY = "pl";

    @Mock
    private NewsClient newsClient;

    @InjectMocks
    private NewsManager newsManager;

    @Test
    public void shouldCreateCorrectOutputArticle() {
        //given
        List<InputArticle> inputArticleList = Collections.singletonList(INPUT_ARTICLE);
        when(newsClient.getNewsForCountryAndCategory(COUNTRY, CATEGORY)).thenReturn(inputArticleList);

        //when
        List<OutputArticle> outputArticles = newsManager.getForCategoryAndCountry(COUNTRY, CATEGORY);

        //then
        assertThat(outputArticles).hasSize(inputArticleList.size());
        assertThat(outputArticles).allMatch(checkIfMatches(INPUT_ARTICLE));
    }
}