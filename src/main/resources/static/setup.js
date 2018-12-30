const categoryDropDownId = "categoryDropdown";
const categoryDropDown = document.getElementById(categoryDropDownId);
const hostUrl = "http://localhost:8080";//window.location.host;
const categoriesUrl = hostUrl + "/news/categories";

$.get({
    url: categoriesUrl,
    success: appendCategoriesToDropdown
});

function appendCategoriesToDropdown(categories) {
    categories.forEach(function (category) {
        let option = document.createElement("option");
        option.text = category;
        option.value = category;
        categoryDropDown.options.add(option);
    });
}