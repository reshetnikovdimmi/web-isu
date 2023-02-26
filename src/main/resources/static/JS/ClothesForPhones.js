$(document).ready(function() {
    var tab;
    const requestURL = '/tableGlasShop'
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    tableGlasShop();
    tableCaseShop();
    tableCoverBookShop();
});

function tableGlasShop() {
    var tds = document.querySelectorAll('.table_Glass .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            var brend = this.innerHTML;
            $.post('/tableGlasShop', {
                text: brend
            }, function(data) {
                $(".GlassShop").html(data);
                tableGlasShops(brend);
                $.get('/orderЕable/' + brend + '/Glass', {}, function(data, status) {
                    orderЕablePhone(data);

                });
                var tds = document.querySelectorAll('.GlassShop .btn');
                $.get('/warehouseRemnants/' + tds[0].innerHTML + '/' + brend + '/Glass', {}, function(data, status) {

                    tableWarehouseRemnants(data);
                });
            });
        });
    }
}

function tableWarehouseRemnants(data) {
    var elem = document.querySelector('#table_warehouseRemnants');
    var elem1 = document.querySelector('#tables_warehouseRemnants');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_warehouseRemnants';
    table.classList.add("tables_warehouseRemnants");
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_0 = document.createElement('th');
    heading_0.innerHTML = "shopss";
    let heading_1 = document.createElement('th');
    row_1.appendChild(heading_0);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < data.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 3; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].brend;
            }
            if (j == 1) {
                td.innerHTML = data[i].remanis;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function tableGlasShops(brend) {
    var tds = document.querySelectorAll('.GlassShop .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            var shop = this.innerHTML;
            $.get('/tableGlasShops/' + this.innerHTML + '/' + brend + '/Glass', {}, function(data, status) {
                phoneTable(data, shop);
                cloterTable(data);
            });
        });
    }
}

function tableCaseShop() {
    var tds = document.querySelectorAll('.table_Case .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            $.post('/tableCaseShop', {
                text: this.innerHTML
            }, function(data) {
                $(".CaseShop").html(data);
            });
        });
    }
}

function tableCoverBookShop() {
    var tds = document.querySelectorAll('.table_CoverBook .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            $.post('/tableCoverBookShop', {
                text: this.innerHTML
            }, function(data) {
                $(".CoverBookShop").html(data);
            });
        });
    }
}

function orderЕablePhone(data) {
    var elem = document.querySelector('#table_clotherRemanisClass');
    var elem1 = document.querySelector('#tables_clotherRemanisClass');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_clotherRemanisClass';
    table.classList.add("tables_clotherRemanisClass");
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_0 = document.createElement('th');
    heading_0.innerHTML = "shopss";
    let heading_1 = document.createElement('th');
    row_1.appendChild(heading_0);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < data.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 3; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].brend;
            }
            if (j == 1) {
                td.innerHTML = data[i].remanis;
            }
            if (j == 2) {
                td.innerHTML = data[0].remanisCloters;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function phoneTable(data, shopss) {
    //console.log(data)
    var elem = document.querySelector('#table_phoneRemanisShop');
    var elem1 = document.querySelector('#tables_phoneRemanisShop');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_phoneRemanisShop';
    table.classList.add("tables_phoneRemanisShop");
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_0 = document.createElement('th');
    heading_0.innerHTML = shopss;
    let heading_1 = document.createElement('th');
    row_1.appendChild(heading_0);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < data[1].length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 2; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[1][i].brend;
            }
            if (j == 1) {
                td.innerHTML = data[1][i].remanis;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function cloterTable(data) {
    //console.log(data)
    var elem = document.querySelector('#table_clotherRemanisShop');
    var elem1 = document.querySelector('#tables_clotherRemanisShop');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_clotherRemanisShop';
    table.classList.add("tables_clotherRemanisShop");
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_0 = document.createElement('th');
    heading_0.innerHTML = "cash";
    let heading_1 = document.createElement('th');
    row_1.appendChild(heading_0);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < data[0].length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 3; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[0][i].brend;
            }
            if (j == 1) {
                td.innerHTML = data[0][i].remanis;
            }
            if (j == 2) {
                var input = document.createElement('input')
                input.classList.add("UPDATE");
                td.appendChild(input);
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.UPDATE').on('change', function() {
          var brend = $(this).parents('tr:first').find('td:eq(0)').text(), data;
          var kol = this.value;

               $.get('/movingWarehouse/'+ brend +'/'+ kol +'/Glass', {}, function(data, status) {

                             console.log(data);
                          });
    });
}