package com.newsone.server;

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

import static com.newsone.utils.InputArticleHelper.*;
import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.*;
import static com.xebialabs.restito.semantics.Condition.parameter;
import static com.xebialabs.restito.semantics.Condition.uri;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class NewsServerLiveTest {

    private StubServer newsServerMock = new StubServer();

    private static final String NEWS_ONE_URL_TEMPLATE = "http://localhost:8080/news/%s/%s";
    private static final String COUNTRY = "pl";
    private static final Category CATEGORY = Category.technology;

    @Autowired
    private AppProperties appProperties;

    @Before
    public void setUp()  {
        newsServerMock = new StubServer(appProperties.getTargetPort());
        newsServerMock.run();
        String apiKey = appProperties.getApiKey();

        //given
        whenHttp(newsServerMock)
                .match(uri("/"), parameter("country", COUNTRY), parameter("category", CATEGORY.toString()), parameter("apiKey", apiKey))
                .then(ok(), stringContent(prepareJsonResponse()), contentType("application/json"));
    }


    @Test
    public void shouldReturnGivenCountryAndCategoryAndCorrectArticlesList() {
        //when
        OutputArticles outputArticles = get(String.format(NEWS_ONE_URL_TEMPLATE, COUNTRY, CATEGORY)).getBody().as(OutputArticles.class);

        // then
        assertThat(outputArticles.getCountry()).isEqualTo(COUNTRY);
        assertThat(outputArticles.getCategory()).isEqualTo(CATEGORY.toString());
        assertThat(outputArticles.getArticles()).allMatch(checkIfMatches(INPUT_ARTICLE));
    }

    @After
    public void stop() {
        newsServerMock.stop();
    }
}