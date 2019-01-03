package com.newsone.client;

import com.AppProperties;
import com.newsone.utils.InputArticleHelper;
import com.newsone.core.enums.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class NewsClientTest {

    private static final String COUNTRY = "pl";
    private static final Category CATEGORY = Category.technology;
    private static final String API_KEY = "1234";
    private static final String TARGET_URL = "targetUrl";
    private static final String TARGET_SUFFIX_TEMPLATE = "?country=%s&category=%s&apiKey=%s";

    @Mock
    private AppProperties appProperties;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NewsClient newsClient;

    @Before
    public void setUp() {
        when(appProperties.getTargetUrl()).thenReturn(TARGET_URL);
        when(appProperties.getTargetSuffixTemplate()).thenReturn(TARGET_SUFFIX_TEMPLATE);
        when(appProperties.getApiKey()).thenReturn(API_KEY);
    }

    @Test
    public void shouldCallWithCorrectCountryAndCategory() {
        //when
        newsClient.getNewsForCountryAndCategory(COUNTRY, CATEGORY);
        String expectedTargetUrl = constructExpectedTargetUrl();

        //then
        verify(restTemplate).getForObject(expectedTargetUrl, InputArticles.class);
    }

    @Test
    public void shouldReturnArticlesPassedByRestResponse() {
        //given
        List<InputArticle> expectedInputArticleList = Collections.singletonList(InputArticleHelper.INPUT_ARTICLE);
        InputArticles expectedInput = new InputArticles(expectedInputArticleList);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(expectedInput);

        //when
        List<InputArticle> returnedInputArticles = newsClient.getNewsForCountryAndCategory(COUNTRY, CATEGORY);

        //then
        assertThat(returnedInputArticles).isSameAs(expectedInputArticleList);
    }

    @Test
    public void shouldRethrowRestClientException() {
        //given
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);

        //when
        assertThatThrownBy(() -> newsClient.getNewsForCountryAndCategory(COUNTRY, CATEGORY))
                .isInstanceOf(RestClientException.class);

        //then
        verify(restTemplate).getForObject(constructExpectedTargetUrl(), InputArticles.class);
    }

    private String constructExpectedTargetUrl() {
        return String.format(TARGET_URL + TARGET_SUFFIX_TEMPLATE, COUNTRY, CATEGORY, API_KEY);
    }
}