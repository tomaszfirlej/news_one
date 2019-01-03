package com.newsone.client;

import com.AppProperties;
import com.newsone.core.enums.Category;
import com.xebialabs.restito.server.StubServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.newsone.utils.InputArticleHelper.INPUT_ARTICLE;
import static com.newsone.utils.InputArticleHelper.prepareJsonResponse;
import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.*;
import static com.xebialabs.restito.semantics.Condition.parameter;
import static com.xebialabs.restito.semantics.Condition.uri;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class NewsClientIT {

    private static final String COUNTRY = "pl";
    private static final Category CATEGORY = Category.technology;
    private static String API_KEY;

    private StubServer newsServerMock = new StubServer();

    @Autowired
    private NewsClient testedClass;

    @Autowired
    private AppProperties appProperties;

    @Before
    public void setUp() {
        newsServerMock = new StubServer(appProperties.getTargetPort());
        newsServerMock.run();
        API_KEY = appProperties.getApiKey();
    }

    @Test
    public void shouldReturnCorrectInputArticle() {
        //given
        whenHttp(newsServerMock)
                .match(uri("/"), parameter("country", COUNTRY), parameter("category", CATEGORY.toString()), parameter("apiKey", API_KEY))
                .then(ok(), stringContent(prepareJsonResponse()), contentType("application/json"));

        //when
        List<InputArticle> response = testedClass.getNewsForCountryAndCategory(COUNTRY, CATEGORY);

        //then
        assertThat(response).hasSize(1);
        assertThat(response.get(0)).isEqualToComparingFieldByFieldRecursively(INPUT_ARTICLE);
    }

    @After
    public void stop() {
        newsServerMock.stop();
    }
}