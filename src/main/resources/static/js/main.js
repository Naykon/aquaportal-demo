$(document).ready(() => {
    loadCategories();
    loadRecentItems();
});

$(document).on("click", '.categoryBtn', function (e) {
    e.preventDefault();
    console.log("I was clicked");
    $(".alert").show("fade");
});

function loadCategories() {
    $.ajax({
        method: "GET",
        url: "/categories",
        success: function (categories) {
            //console.log(categories);
            for (let category of categories["_embedded"]["categories"]) {
                //TODO: Fix links
                let name = category["name"];
                let link = category["_links"]["self"]["href"];
                let html = `<a href="#" class="list-group-item categoryBtn">${name}</a>`;
                //html.data("category-id", link)
                //html.data('category-id');
                $(".list-group").append(html);
                //console.log(link);
            }
        },
        error: function (error) {
            $(".alert").show("fade");
        }
    });
}

function loadRecentItems() {
    $.ajax({
        method:"GET",
        url:"/items/latest",
        success: function (recentItems) {
            for (let recentItem of recentItems) {
                $("#main-content-div").append(drawOneItem(recentItem));
            }
        },
        error: function (error) {
            $(".alert").show("fade");
        }
    });
}

function drawOneItem(item) {

    let html =
        `            <div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">
                <a href="#"><img class="card-img-top" src="${item["pictureURL"]}" alt=""></a>
                <div class="card-body">
                  <h4 class="card-title">
                    <a href="#">${item["name"]}</a>
                  </h4>
                  <h5>${item["price"]}</h5>
                  <p class="card-text">${formatDescription(item["description"])}</p>
                </div>
              </div>
            </div>`;
    return html;
}

function formatDescription(description) {
    let limit = 200;
    if (description.length > limit) {
        return description.substring(0, limit) + " ...";
    }
    else {
        return description;
    }
}