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
orderFromAllWarehouse();
        }
    });

}
function orderFromAllWarehouse() {
 $.ajax({
        url: "/orderFromAllWarehouse",
        type: "GET",
        success: function(data) {
maxOrderNotFromWarehouseAll(data);
orderFromMatrixWarehouse();

        }
    });

}
function orderFromMatrixWarehouse() {
 $.ajax({
        url: "/orderFromMatrixWarehouse",
        type: "GET",
        success: function(data) {
orderFromMaxMatrixWarehouse(data);
orderFromMinT2Warehouse();


        }
    });

}
function orderFromMinT2Warehouse() {
 $.ajax({
        url: "/orderFromMinT2Warehouse",
        type: "GET",
        success: function(data) {
orderFromMinMatrixWarehouse(data);
console.log(data);
$('#loader').addClass('hidden')

        }
    });

}
function orderFromMinMatrixWarehouse(data) {
 var elem = document.querySelector('#table_minT2');
    var elem1 = document.querySelector('#tables_minT2');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_minT2';
    table.classList.add("table-borderless-1");
    table.classList.add("tables_minT2");
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
                button.classList.add("minT2");
                button.id = 'minT2';
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
            tbody2.classList.add("hide_minT2");
        }
        tbody.appendChild(tbody2);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.minT2').on('click', function() {
        $(this).parents().next('.hide_minT2').toggle();
    });


}
function orderFromMaxMatrixWarehouse(data) {
 var elem = document.querySelector('#table_maxMatrix');
    var elem1 = document.querySelector('#tables_maxMatrix');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_maxMatrix';
    table.classList.add("table-borderless-1");
    table.classList.add("tables_maxMatrix");
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
                button.classList.add("maxMatrix");
                button.id = 'maxMatrix';
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
            tbody2.classList.add("hide_maxMatrix");
        }
        tbody.appendChild(tbody2);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.maxMatrix').on('click', function() {
        $(this).parents().next('.hide_maxMatrix').toggle();
    });


}
function maxOrderNotFromWarehouseAll(data) {
 var elem = document.querySelector('#table_maxAll');
    var elem1 = document.querySelector('#tables_maxAll');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_maxAll';
    table.classList.add("table-borderless-1");
    table.classList.add("tables_maxAll");
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
                button.classList.add("maxAll");
                button.id = 'maxAll';
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
            tbody2.classList.add("hide_maxAll");
        }
        tbody.appendChild(tbody2);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.maxAll').on('click', function() {
        $(this).parents().next('.hide_maxAll').toggle();
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