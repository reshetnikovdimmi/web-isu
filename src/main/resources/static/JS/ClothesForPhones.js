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
            $.get('/tableGlasShop/' + brend.replaceAll('/','_'), {}, function(data, status) {

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
                if (typeof data[i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
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

function tableWarehouseRemnantsCase(data) {
    var elem = document.querySelector('#table_warehouseRemnantsCase');
    var elem1 = document.querySelector('#tables_warehouseRemnantsCase');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_warehouseRemnantsCase';
    table.classList.add("tables_warehouseRemnantsCase");
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
                if (typeof data[i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
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
function tableWarehouseRemnantsCoverBook(data) {

    var elem = document.querySelector('#table_warehouseRemnantsCoverBook');
    var elem1 = document.querySelector('#tables_warehouseRemnantsCoverBook');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_warehouseRemnantsCoverBook';
    table.classList.add("tables_warehouseRemnantsCoverBook");
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
                if (typeof data[i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
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
                cloterTable(data, brend, shop);
            });
        });
    }
}

function tableCaseShops(brend) {
    var tds = document.querySelectorAll('.CaseShop .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            var shop = this.innerHTML;
            $.get('/tableGlasShops/' + this.innerHTML + '/' + brend + '/Case', {}, function(data, status) {
                phoneTableCase(data, shop);
                cloterTableCase(data, brend, shop);
            });
        });
    }
}

function tableCaseShop() {
    var tds = document.querySelectorAll('.table_Case .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            var brend = this.innerHTML;
            $.post('/tableCaseShop', {
                text: this.innerHTML
            }, function(data) {
                $(".CaseShop").html(data);
                tableCaseShops(brend);
                $.get('/orderЕable/' + brend + '/Case', {}, function(data, status) {
                    tablePhoneCase(data);
                });
                var tds = document.querySelectorAll('.CaseShop .btn');
                $.get('/warehouseRemnants/' + tds[0].innerHTML + '/' + brend + '/Case', {}, function(data, status) {
                    tableWarehouseRemnantsCase(data);
                });
            });
        });
    }
}

function tableCoverBookShop() {
    var tds = document.querySelectorAll('.table_CoverBook .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            var brend = this.innerHTML;
            $.post('/tableCoverBookShop', {
                text: brend
            }, function(data) {

                $(".CoverBookShop").html(data);
                tableCoverBookShops(brend);
                $.get('/orderЕable/' + brend + '/CoverBook', {}, function(data, status) {

                   tablePhoneCoverBook(data);
                });
                var tds = document.querySelectorAll('.CoverBookShop .btn');
                $.get('/warehouseRemnants/' + tds[0].innerHTML + '/' + brend + '/CoverBook', {}, function(data, status) {

                    tableWarehouseRemnantsCoverBook(data);
                });
            });
        });
    }
}
function tableCoverBookShops(brend) {
console.log(brend)
    var tds = document.querySelectorAll('.CoverBookShop .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            var shop = this.innerHTML;
            $.get('/tableGlasShops/' + this.innerHTML + '/' + brend + '/CoverBook', {}, function(data, status) {
            console.log(data)
                phoneTableCoverBook(data, shop);
                cloterTableCoverBook(data, brend, shop);
            });
        });
    }
}
function tablePhoneCoverBook(data) {
    var elem = document.querySelector('#table_clotherRemanisCoverBook');
    var elem1 = document.querySelector('#tables_clotherRemanisCoverBook');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_clotherRemanisCoverBook';
    table.classList.add("tables_clotherRemanisCoverBook");
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
                if (typeof data[0].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
            }
            if (j == 2) {
                td.innerHTML = data[0].remanisCloters;
                if (typeof data[0].remanisCloters === 'undefined') {
                    td.innerHTML = 0;
                }
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
function tablePhoneCase(data) {
    var elem = document.querySelector('#table_clotherRemanisCase');
    var elem1 = document.querySelector('#tables_clotherRemanisCase');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_clotherRemanisCase';
    table.classList.add("tables_clotherRemanisCase");
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
                if (typeof data[0].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
            }
            if (j == 2) {
                td.innerHTML = data[0].remanisCloters;
                if (typeof data[0].remanisCloters === 'undefined') {
                    td.innerHTML = 0;
                }
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
                if (typeof data[0].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
            }
            if (j == 2) {
                td.innerHTML = data[0].remanisCloters;
                if (typeof data[0].remanisCloters === 'undefined') {
                    td.innerHTML = 0;
                }
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

function phoneTableCase(data, shopss) {
    //console.log(data)
    var elem = document.querySelector('#table_phoneRemanisShopCase');
    var elem1 = document.querySelector('#tables_phoneRemanisShopCase');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_phoneRemanisShopCase';
    table.classList.add("tables_phoneRemanisShopCase");
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
                if (typeof data[1][i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
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

function cloterTableCase(data, brend, shop) {
    //console.log(data)
    var elem = document.querySelector('#table_clotherRemanisShopCase');
    var elem1 = document.querySelector('#tables_clotherRemanisShopCase');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_clotherRemanisShopCase';
    table.classList.add("tables_clotherRemanisShopCase");
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
                if (typeof data[0][i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
            }
            if (j == 2) {
                var input = document.createElement('input')
                input.classList.add("UPDATE");
                td.appendChild(input);
                input.value = data[0][i].remanisCloters;
                if (typeof data[0][i].remanisCloters === 'undefined') {
                    input.value = 0;
                }
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
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;
            console.log(models)
        var kol = this.value;
        $.get('/movingWarehouse/' + brend + '/' + models.replaceAll('/','_') + '/' + kol + '/Case/' + shop, {}, function(data, status) {
            tableWarehouseRemnantsCase(data);
        });
    });
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
                if (typeof data[1][i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
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

function cloterTable(data, brend, shop) {
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
                if (typeof data[0][i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
            }
            if (j == 2) {
                var input = document.createElement('input')
                input.classList.add("UPDATE");
                td.appendChild(input);
                input.value = data[0][i].remanisCloters;
                if (typeof data[0][i].remanisCloters === 'undefined') {
                    input.value = 0;
                }
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
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;
        var kol = this.value;
        $.get('/movingWarehouse/' + brend + '/' + models.replaceAll('/','_') + '/' + kol + '/Glass/' + shop, {}, function(data, status) {
            tableWarehouseRemnants(data);
        });
    });
}

function phoneTableCoverBook(data, shopss) {
    //console.log(data)
    var elem = document.querySelector('#table_phoneRemanisShopCoverBook');
    var elem1 = document.querySelector('#tables_phoneRemanisShopCoverBook');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_phoneRemanisShopCoverBook';
    table.classList.add("tables_phoneRemanisShopCoverBook");
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
                if (typeof data[1][i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
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

function cloterTableCoverBook(data, brend, shop) {
    //console.log(data)
    var elem = document.querySelector('#table_clotherRemanisShopCoverBook');
    var elem1 = document.querySelector('#tables_clotherRemanisShopCoverBook');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_clotherRemanisShopCoverBook';
    table.classList.add("tables_clotherRemanisShopCoverBook");
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
                if (typeof data[0][i].remanis === 'undefined') {
                    td.innerHTML = 0;
                }
            }
            if (j == 2) {
                var input = document.createElement('input')
                input.classList.add("UPDATE");
                td.appendChild(input);
                input.value = data[0][i].remanisCloters;
                if (typeof data[0][i].remanisCloters === 'undefined') {
                    input.value = 0;
                }
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
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;

        var kol = this.value;
        console.log(models)
        $.get('/movingWarehouse/' + brend + '/' + models.replaceAll('/','_') + '/' + kol + '/CoverBook/' + shop, {}, function(data, status) {
                   tableWarehouseRemnantsCoverBook(data);
                });
    });
}