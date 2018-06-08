$(document).ready(() => {
    loadCategories();
    loadRecentItems();
});

//Fix alerts so they can be hidden and not removed
$(document).on("click", '#alertCloseBtn', function (e) {
    e.preventDefault();
    $(this).parent().hide();
});

$(document).on("click", '.categoryBtn', function (e) {
    e.preventDefault();
    let itemsURL = $(e.target).attr("href");
    $.ajax({
        method: "GET",
        url: itemsURL,
        success: function (itemsInACategory) {
            //TODO: Add paging
            $("#main-content-div").html("");
            for (let oneItem of itemsInACategory["_embedded"]["items"]) {
                $("#main-content-div").append(drawOneItem(oneItem));
            }
        },
        error: function (error) {
            $(".alert").show("fade");
        }
    });
});

$(document).on("click", '.itemBtn', function (e) {
    e.preventDefault();
    let itemURL = $(e.target).attr("href");

    $.ajax({
        method: "GET",
        url: itemURL,
        success: function (item) {
            $("#modalItemName").html(item["name"]);
            $("#modalItemPrice").html('€ ' + item["price"]);
            $("#modalItemImageURL").attr("src", item["pictureURL"]);
            $("#modalItemDescription").html(item["description"]);
        },
        error: function (error) {
            $(".alert").show("fade");
        }
    });

    $(".modal").modal("show");
});

function loadCategories() {
    $.ajax({
        method: "GET",
        url: "/categories",
        success: function (categories) {

            for (let category of categories["_embedded"]["categories"]) {
                let name = category["name"];
                let relativeLink = formatURL(category["_links"]["items"]["href"]);
                let html = `<a href="${relativeLink}" class="list-group-item categoryBtn">${name}</a>`;

                $(".list-group").append(html);
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

    let link = '';

    try {
        link = formatURL(item ["_links"]["self"]["href"]);
    } catch (e) {
        link = `/items/${item["id"]}`;
    }

    let html =
           `<div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">
                <a><img class="card-img-top w-100 h-100" src="${item["pictureURL"]}" alt=""></a>
                <div class="card-body">
                  <h4 class="card-title">
                    <a href="${link}" class="itemBtn">${item["name"]}</a>
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

function formatURL(url) {
    //TODO: Find a way to handle this better. I can see no option to get relative URIs
    return url.substring(21);
}