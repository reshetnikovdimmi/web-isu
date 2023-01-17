$(document).ready(function() {
$('#loader').removeClass('hidden')
    $.ajax({
        url: "/orderFromT2Warehouse",
        type: "GET",
        success: function(data) {
            maxOrderFromWarehouseT2(data);
            orderFromWarehouse();
        }
    });

    return false;
});
function orderFromWarehouse() {
 $.ajax({
        url: "/orderFromWarehouse",
        type: "GET",
        success: function(data) {
maxOrderNotFromWarehouseT2(data);
$('#loader').addClass('hidden')
        }
    });

}

function maxOrderNotFromWarehouseT2(data) {
 var elem = document.querySelector('#table_maxNeT2');
    var elem1 = document.querySelector('#tables_maxNeT2');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_maxNeT2';
    table.classList.add("table-borderless-1");
    table.classList.add("tables_maxNeT2");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Модель";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Количество";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels2");
    for (var i = 0; i < data.length; i++) {
        let tbody1 = document.createElement('tbody');
        table.id = 'toggle';
        var tr = document.createElement('tr');
        for (var j = 0; j < 2; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                var button = document.createElement('button')
                button.classList.add("maxNeT2");
                button.id = 'maxNeT2';
                button.innerHTML = data[i].distributionModel;
                td.appendChild(button);
            } else if (j == 1) {
                td.innerHTML = data[i].quantity;
            }
            tr.appendChild(td);
        }
        tbody1.appendChild(tr);
        tbody1.classList.add("labels");
        tbody.appendChild(tbody1);
        let tbody2 = document.createElement('tbody');
        for (let key in data[i].salePhone) {

            var tr = document.createElement('tr');
            var td = document.createElement('td');
            td.innerHTML = key;
            tr.appendChild(td);
            var td = document.createElement('td');
            td.innerHTML = data[i].salePhone[key];
            tr.appendChild(td);
            tbody2.appendChild(tr);
            tbody2.classList.add("hide_maxNeT2");
        }
        tbody.appendChild(tbody2);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.maxNeT2').on('click', function() {
        $(this).parents().next('.hide_maxNeT2').toggle();
    });


}
function maxOrderFromWarehouseT2(data) {
    var elem = document.querySelector('#table_unwrap');
    var elem1 = document.querySelector('#tables_unwrap');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_unwrap';
    table.classList.add("table-borderless-1");
    table.classList.add("tables_unwrap");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Модель";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Количество";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels2");
    for (var i = 0; i < data.length; i++) {
        let tbody1 = document.createElement('tbody');
        table.id = 'toggle';
        var tr = document.createElement('tr');
        for (var j = 0; j < 2; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                var button = document.createElement('button')
                button.classList.add("unwrap");
                button.id = 'unwrap';
                button.innerHTML = data[i].distributionModel;
                td.appendChild(button);
            } else if (j == 1) {
                td.innerHTML = data[i].quantity;
            }
            tr.appendChild(td);
        }
        tbody1.appendChild(tr);
        tbody1.classList.add("labels");
        tbody.appendChild(tbody1);
        let tbody2 = document.createElement('tbody');
        for (let key in data[i].salePhone) {

            var tr = document.createElement('tr');
            var td = document.createElement('td');
            td.innerHTML = key;
            tr.appendChild(td);
            var td = document.createElement('td');
            td.innerHTML = data[i].salePhone[key];
            tr.appendChild(td);
            tbody2.appendChild(tr);
            tbody2.classList.add("hide");
        }
        tbody.appendChild(tbody2);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.unwrap').on('click', function() {
        $(this).parents().next('.hide').toggle();
    });

}