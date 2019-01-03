package com.newsone.utils;

import com.newsone.client.ArticleSource;
import com.newsone.client.InputArticle;
import com.newsone.client.InputArticleBuilder;
import com.newsone.server.OutputArticle;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.function.Predicate;

public class InputArticleHelper {

    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String URL = "url";
    private static final String URL_TO_IMAGE = "imageUrl";
    private static final String PUBLISHED_AT = "2017-06-01T15:15:15Z";
    private static final String CONTENT = "content";
    private static final String SOURCE_ID = "sourceId";
    private static final String SOURCE_NAME = "sourceName";
    private static final int EXPECTED_DATE_FORMAT_LENGTH = 10;

    public static final InputArticle INPUT_ARTICLE = createInputArticle();

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String prepareJsonResponse() {
        try {
            return "{\"status\":\"ok\",\"totalResults\":1,\"articles\":[" +
                    objectMapper.writeValueAsString(INPUT_ARTICLE) + "]}";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static InputArticle createInputArticle() {

        ArticleSource articleSource = new ArticleSource() {{
            setId(SOURCE_ID);
            setName(SOURCE_NAME);
        }};

        return InputArticleBuilder.anInputArticle()
                .withAuthor(AUTHOR)
                .withTitle(TITLE)
                .withDescription(DESCRIPTION)
                .withUrl(URL)
                .withUrlToImage(URL_TO_IMAGE)
                .withPublishedAt(PUBLISHED_AT)
                .withContent(CONTENT)
                .withSource(articleSource)
                .build();
    }

    public static Predicate<OutputArticle> checkIfMatches(InputArticle inputArticle) {
        return (ouptutArticle) -> ouptutArticle.getAuthor().equals(inputArticle.getAuthor()) &&
                ouptutArticle.getTitle().equals(inputArticle.getTitle()) &&
                ouptutArticle.getDescription().equals(inputArticle.getDescription()) &&
                ouptutArticle.getArticleUrl().equals(inputArticle.getUrl()) &&
                ouptutArticle.getImageUrl().equals(inputArticle.getUrlToImage()) &&
                ouptutArticle.getDate().equals(inputArticle.getPublishedAt().substring(0, EXPECTED_DATE_FORMAT_LENGTH)) &&
                ouptutArticle.getSourceName().equals(inputArticle.getSource().getName());
    }
}