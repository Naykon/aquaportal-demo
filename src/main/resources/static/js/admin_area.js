$(document).on("click", '#adminOrdersBtn', function (e) {
    e.preventDefault();

    $.ajax({
        method:"GET",
        url:"/orders/all",
        success: function (allOrders) {
            $("#main-content-div").html('');
            //console.log(allOrders);
            // for (let recentItem of recentItems) {
            //     $("#main-content-div")
            // }
            for (let order of allOrders) {
                console.log(order)
                let table = `<table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>Item</th>
                        <th>QTY</th>
                        <th>Unit Price</th>
                        <th>Total Price
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="itemsTable">

                    </tbody>
                </table>`
            }
        },
        error: function (error) {
            $("#errorAlert").show("fade");
        }
    });
});