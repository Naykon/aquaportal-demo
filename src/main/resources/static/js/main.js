$(document).ready(() => {
    loadCategories();
});

function loadCategories() {
    $.ajax({
        method: "GET",
        url: "/categories",
        success: function (categories) {
            for (let category of categories["_embedded"]["categories"]) {
                //TODO: Fix links
                let html = '<a href="#" class="list-group-item">' + category["name"] + '</a>';
                $(".list-group").append(html);
            }
        },
        error: function (error) {
            //TODO: Error handling
            console.log(error);
        }
    });
}