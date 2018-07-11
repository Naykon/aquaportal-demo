$(document).ready(() => {
    loadCategories();
    loadRecentItems();

    if (localStorage.getItem('itemQty')) {
        updateCartCount();
    }
    else {
        let cart = {};
        localStorage.setItem('itemQty', JSON.stringify(cart));
    }
});

//Fixes alerts so they can be hidden and not removed
$(document).on("click", '.alertCloseBtn', function (e) {
    e.preventDefault();
    $(this).parent().hide();
});

$(document).on("click", '.categoryBtn', function (e) {
    e.preventDefault();
    let itemsURL = $(e.target).attr("href");

    $('#categoryTitle').html($(e.target).text());

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
            $("#errorAlert").show("fade");
        }
    });
});

$(document).on("click", '.addToCart', function (e) {
    e.preventDefault();
    let name = $(e.target).attr("item-name");

    let cart = JSON.parse(localStorage.getItem('itemQty'));
    if (cart[name]) {
        cart[name] += 1;
    } else {
        cart[name] = 1;
    }

    localStorage.setItem('itemQty', JSON.stringify(cart));

    updateCartCount();
});

$(document).on("click", '.openCart', function (e) {
    e.preventDefault();

    let cart = JSON.parse(localStorage.getItem('itemQty'));

    loadCartModal(cart);
});

$(document).on("click", '.removeItem', function (e) {
    e.preventDefault();

    let cart = JSON.parse(localStorage.getItem('itemQty'));

    let itemName = $(e.target).attr("itemName");

    delete cart[itemName];

    localStorage.setItem('itemQty', JSON.stringify(cart));

    loadCartModal(cart);
    updateCartCount();
});

$(document).on("click", '#checkoutBtn', function (e) {
    e.preventDefault();

    let cart = JSON.parse(localStorage.getItem('itemQty'));

    let order = {};
    order['itemQty'] = cart;
    order['deliveryAddress'] = $('#deliveryAddress').val();

    order['username'] = $('#username').text();

    $.ajax({
        method: "POST",
        contentType : "application/json",
        url:"/orders/checkout",
        data: JSON.stringify(order),
        success: function (response) {
            localStorage.setItem('itemQty', JSON.stringify({}));
            $("#cartModal").modal("hide");
            updateCartCount();
            showNotification('Order sent and will be processed soon.');
        },
        error: function (error) {
            console.log(error)
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
            $("#errorAlert").show("fade");
        }
    });

    $("#itemModal").modal("show");
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
            $("#errorAlert").show("fade");
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
            $("#errorAlert").show("fade");
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
                  <button class="btn btn-dark pull-right addToCart" item-name="${item["name"]}">Add to cart</button>
                </div>
              </div>
            </div>`;
    return html;
}

function loadCartModal(cart) {
    $("#itemsTable").empty();

    let grandTotal = 0;
    for (let item of Object.keys(cart))  {

        let quantity = cart[item];

        let url = '/items/price/' + item;
        $.ajax({
            method:"GET",
            url:url,
            success: function (price) {

                let totalItem = quantity * price;

                grandTotal += totalItem;

                let row = `<tr>
                        <td>${item}</td>
                        <td>${quantity}</a></td>
                        <td>${price}</td>
                        <td>${totalItem}</td>
                        <td><a href="#" itemName="${item}" class="removeItem">
                            Remove
                        </a></td>
                    </tr>`;

                $('#itemsTable').append(row);
                $('#totalPrice').html(grandTotal);
            },
            error: function (error) {
                $("#errorAlert").show("fade");
            }
        });
    }

    $("#cartModal").modal("show");
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

function updateCartCount() {
    let cart = JSON.parse(localStorage.getItem('itemQty'));
    if (cart) {
        $("#itemsCount").html(Object.keys(cart).length);
    } else {
        $("#itemsCount").html(0);
    }
}

function formatURL(url) {
    //TODO: Find a way to handle this better. I can see no option to get relative URIs
    return url.substring(21);
}

function showNotification(text) {
    $('#notificationText').html(text);
    $("#notificationAlert").show("fade");
}

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});