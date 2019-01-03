package com.newsone.client;

public final class InputArticleBuilder {
    private ArticleSource source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    private InputArticleBuilder() {
    }

    public static InputArticleBuilder anInputArticle() {
        return new InputArticleBuilder();
    }

    public InputArticleBuilder withSource(ArticleSource source) {
        this.source = source;
        return this;
    }

    public InputArticleBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public InputArticleBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public InputArticleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public InputArticleBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public InputArticleBuilder withUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public InputArticleBuilder withPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public InputArticleBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public InputArticle build() {
        InputArticle inputArticle = new InputArticle();
        inputArticle.setSource(source);
        inputArticle.setAuthor(author);
        inputArticle.setTitle(title);
        inputArticle.setDescription(description);
        inputArticle.setUrl(url);
        inputArticle.setUrlToImage(urlToImage);
        inputArticle.setPublishedAt(publishedAt);
        inputArticle.setContent(content);
        return inputArticle;
    }
}
