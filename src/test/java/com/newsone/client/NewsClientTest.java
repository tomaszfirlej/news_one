package com.newsone.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsClientTest {

    @Autowired
    private NewsClient testedClass;

    @Test
    public void shouldGetSomeNewsForCountryAndCategory() {
        List<InputArticle> inputArticleList = testedClass.getForCountryAndCategory("pl", "technology");
        assertThat(inputArticleList).isNotEmpty();
    }
}