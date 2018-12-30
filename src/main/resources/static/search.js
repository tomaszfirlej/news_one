const searchUrl = hostUrl + "/news";

function searchNews() {
    const categoryDropDownId = "categoryDropdown",
        categoryDropDown = document.getElementById(categoryDropDownId),
        country = "pl",
        currentCategory = categoryDropDown.value;

    $.ajax({
        url: searchUrl + "/" + country + "/" + currentCategory,
        context: this,
        scope: this,
        success: createArticlesViews
    });
}

function createArticlesViews(news) {
    const articlesArea = document.getElementById("article-list");
    articlesArea.innerHTML = "";
    news.articles.forEach(function (article) {
        let articleView = createArticleView(article);
        articlesArea.appendChild(articleView);
    });
}

function createArticleView(article) {
    const template = document.getElementById("mytemplate"),
        clone = document.importNode(template, true);

    clone.content.querySelector(".title").innerHTML = article.title;
    clone.content.querySelector(".author").innerHTML = article.author;
    clone.content.querySelector(".date").innerHTML = article.date;
    clone.content.querySelector(".source-link").href = article.articleUrl;
    if (article.description) {
        clone.content.querySelector(".description").innerHTML += article.description;
    }
    if (article.imageUrl) {
        clone.content.querySelector(".article-image").src = article.imageUrl;
    } else {
        clone.content.querySelector(".article-image").style.height = 0;
    }
    return clone.content.querySelector("div");
}