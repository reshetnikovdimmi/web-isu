var shop;
var shopT2m;
var nameSim;
var nameSimT2m;
var grop;
var gropT2m;
$(document).ready(function() {
    var tab;
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    nameSimShop();
    nameSimShopT2mult()
});

function nameSimShop() {
    $(document).find('.table_simT2 .btn').on('click', function() {
        nameSim = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_');
        $.get('/RemanSimCash/' + nameSim, {}, function(data) {
            $(".RemanSimCash").html(data);
            remanSimShop();


        });
    });
}
function nameSimShopT2mult() {
    $(document).find('.table_simT2mult .btn').on('click', function() {
        nameSimT2m = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_');
        $.get('/RemanSimCashT2mult/' + nameSimT2m, {}, function(data) {
            $(".RemanSimCashT2mult").html(data);
           remanSimShopT2mult();


        });
    });
}
function remanSimShop() {
$.get('/NameSimShop/' + nameSim + '/' + 't2', {}, function(data) {
                $(".NameSimShop").html(data);
               remanSaleSimShop();
});
}

function remanSimShopT2mult() {

$.get('/NameSimShopT2mult/' + nameSimT2m + '/' + 't2m', {}, function(data) {
                $(".NameSimShopT2mult").html(data);
              remanSaleSimShopT2m();
});
}
function remanSaleSimShopT2m() {
    $(document).find('.NameSimShopT2mult .btn').on('click', function() {
        shopT2m = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/RemanSaleSimShop/' + shopT2m, {}, function(data) {

            orderFromSimT2m(data, shopT2m);
        });
    });
}
function remanSaleSimShop() {
    $(document).find('.NameSimShop .btn').on('click', function() {
        shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/RemanSaleSimShop/' + shop, {}, function(data) {

            orderFromMinMatrixT2Warehouse(data, shop);
        });
    });
}
function updateRemanisCash(grop){
$.get('/UpdateRemanisCash/' + grop, {}, function(data) {
            $(".RemanSimCash").html(data);
            });
}
function updateRemanisCashT2m(gropT2m){
$.get('/UpdateRemanisCashT2mult/' + gropT2m, {}, function(data) {

            $(".RemanSimCashT2mult").html(data);
            });
}

function orderFromMinMatrixT2Warehouse(data, shop) {
    var elem = document.querySelector('#table_DistributionButton');
    var elem1 = document.querySelector('#tables_DistributionButton');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_DistributionButton';
    table.classList.add("table-borderless-1");
    table.classList.add("tables_DistributionButton");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = shop;
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "ОСТ";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "ПРОД6";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "ПРОД";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "ОСТСК";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "ЗАКАЗ";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels2");
    for (key in data) {
        if (view(key, data)) {
            let tbody1 = document.createElement('tbody');
            table.id = 'tables_DistributionButton';
            var tr = document.createElement('tr');
            for (var j = 0; j < 6; j++) {
                var td = document.createElement('td');
                if (j == 0) {
                    var button = document.createElement('button')
                    button.classList.add("minMatrix");
                    button.id = 'minMatrix';
                    button.innerHTML = key;
                    td.appendChild(button);
                } else if (j > 0) {
                    for (keys in data[key]) {
                        if (keys == "total") {
                            for (keyss in data[key][keys]) {
                                if (j == 1 && keyss === "totalRemanis") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 2 && keyss === "totalSale1") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 3 && keyss === "totalSale6") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 4 && keyss === "totalRemanisCash") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 5 && keyss === "orderCash") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                            }
                        }
                    }
                }
                tr.appendChild(td);
            }
            tbody1.appendChild(tr);
            tbody1.classList.add("labels");
            tbody.appendChild(tbody1);
            let tbody2 = document.createElement('tbody');
            for (keys in data[key]) {
                var tr = document.createElement('tr');
                for (var j = 0; j < 6; j++) {
                    var td = document.createElement('td');
                    if (keys != "total") {
                        if (j == 0) {
                            td.innerHTML = keys;
                            if (keys == nameSim.replaceAll('_', '/')) {

                                grop = key;
                            }
                        } else if (j > 0) {
                            for (keyss in data[key][keys]) {
                                if (j == 1 && keyss === "remanis") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 2 && keyss === "sale1") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 3 && keyss === "sale6") {
                                    td.innerHTML = data[key][keys][keyss]
                                }
                                if (j == 4 && keyss === "remanisCash") {
                                    td.innerHTML = data[key][keys][keyss]
                                }
                                if (j == 5 && keyss === "order") {
                                    var input = document.createElement('input');
                                    td.appendChild(input);
                                    input.classList.add("SKYPhone");
                                    input.value = data[key][keys][keyss]
                                }
                            }
                        }
                    }
                    tr.appendChild(td);
                }
                tbody2.appendChild(tr);
                tbody2.classList.add("hide_minMatrix");
            }
            tbody.appendChild(tbody2);
        }
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    var d;
    $(document).find('.minMatrix').on('click', function() {
        if (d != undefined) {
            d.parents().next('.hide_minMatrix').toggle();
        }
        d = $(this);
        d.parents().next('.hide_minMatrix').toggle();
    });
    var tds = document.querySelectorAll('.minMatrix');
    for (var i = 0; i < tds.length; i++) {
        if (tds[i].innerHTML == grop) {

            tds[i].click();
            tds[i].scrollIntoView();
        }
        tds[i].addEventListener('click', function func() {
            grop = this.innerHTML;

            //    accessoriesCategoryShop(grop);
               updateRemanisCash(grop);
            //  accessoriesCategoryMaxSale(grop);
        });
    }
    $(document).find('.SKYPhone').on('click', function() {
    nameSim = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_')
    remanSimShop();
    });

    $(document).find('.SKYPhone').on('change', function() {
        $('.btn-primary').attr('disabled', false);

        $.get('/tableUpDistributionSim/' + shop.trim() + '/' + nameSim + '/' + this.value + '/' + grop.trim(), {}, function(data) {

           tableUpDistributionButton(data);
            updateRemanisCash(grop);
        });
    });
}

function view(key, data) {
    var view = false;
    for (keys in data[key]) {
        for (keyss in data[key][keys]) {
            if (Object.values(data[key][keys]).includes('t2')) {
                view = true;
            }
        }
    }
    return view;
}
function tableUpDistributionButton(data) {

    var tds = document.querySelectorAll('table.tables_DistributionButton td');
    for (var i = 0; i < tds.length; i++) {
        for (key in data) {
            if (tds[i].lastElementChild != null && key == tds[i].lastElementChild.innerHTML) {
                for (keys in data[key]) {
                    if (keys == "total") {
                        for (keyss in data[key][keys]) {
                            if (keyss == "orderCash") {
                                tds[i + 5].innerHTML = data[key][keys][keyss];
                            }
                            if (keyss == "totalRemanisCash") {
                                tds[i + 4].innerHTML = data[key][keys][keyss];
                            }
                        }
                    }
                }
            }
            for (keys in data[key]) {
                if (keys == tds[i].innerHTML) {
                    for (keyss in data[key][keys]) {
                        if (keyss == "remanisCash" && tds[i + 4].innerHTML != data[key][keys][keyss]) {
                            tds[i + 4].innerHTML = data[key][keys][keyss];
                        }
                    }
                }
            }
        }
    }
}
function orderFromSimT2m(data, shop) {
    var elem = document.querySelector('#table_DistribSim');
    var elem1 = document.querySelector('#tables_DistribSim');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_DistribSim';
    table.classList.add("table-borderless-1");
    table.classList.add("tables_DistribSim");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = shop;
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "ОСТ";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "ПРОД6";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "ПРОД";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "ОСТСК";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "ЗАКАЗ";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels2");
    for (key in data) {
        if (viewT2m(key, data)) {
            let tbody1 = document.createElement('tbody');
            table.id = 'tables_DistribSim';
            var tr = document.createElement('tr');
            for (var j = 0; j < 6; j++) {
                var td = document.createElement('td');
                if (j == 0) {
                    var button = document.createElement('button')
                    button.classList.add("minMatrix");
                    button.id = 'minMatrix';
                    button.innerHTML = key;
                    td.appendChild(button);
                } else if (j > 0) {
                    for (keys in data[key]) {
                        if (keys == "total") {
                            for (keyss in data[key][keys]) {
                                if (j == 1 && keyss === "totalRemanis") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 2 && keyss === "totalSale1") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 3 && keyss === "totalSale6") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 4 && keyss === "totalRemanisCash") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 5 && keyss === "orderCash") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                            }
                        }
                    }
                }
                tr.appendChild(td);
            }
            tbody1.appendChild(tr);
            tbody1.classList.add("labels");
            tbody.appendChild(tbody1);
            let tbody2 = document.createElement('tbody');
            for (keys in data[key]) {
                var tr = document.createElement('tr');
                for (var j = 0; j < 6; j++) {
                    var td = document.createElement('td');
                    if (keys != "total") {
                        if (j == 0) {
                            td.innerHTML = keys;
                            if (keys == nameSimT2m.replaceAll('_', '/')) {

                                gropT2m = key;
                            }
                        } else if (j > 0) {
                            for (keyss in data[key][keys]) {
                                if (j == 1 && keyss === "remanis") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 2 && keyss === "sale1") {
                                    td.innerHTML = data[key][keys][keyss];
                                }
                                if (j == 3 && keyss === "sale6") {
                                    td.innerHTML = data[key][keys][keyss]
                                }
                                if (j == 4 && keyss === "remanisCash") {
                                    td.innerHTML = data[key][keys][keyss]
                                }
                                if (j == 5 && keyss === "order") {
                                    var input = document.createElement('input');
                                    td.appendChild(input);
                                    input.classList.add("SKYPhone");
                                    input.value = data[key][keys][keyss]
                                }
                            }
                        }
                    }
                    tr.appendChild(td);
                }
                tbody2.appendChild(tr);
                tbody2.classList.add("hide_minMatrix");
            }
            tbody.appendChild(tbody2);
        }
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    var d;
    $(document).find('.minMatrix').on('click', function() {
        if (d != undefined) {
            d.parents().next('.hide_minMatrix').toggle();
        }
        d = $(this);
        d.parents().next('.hide_minMatrix').toggle();
    });
    var tds = document.querySelectorAll('.minMatrix');
    for (var i = 0; i < tds.length; i++) {
        if (tds[i].innerHTML == gropT2m) {

            tds[i].click();
            tds[i].scrollIntoView();
        }
        tds[i].addEventListener('click', function func() {
            gropT2m = this.innerHTML;

            //    accessoriesCategoryShop(grop);
              updateRemanisCashT2m(gropT2m);
            //  accessoriesCategoryMaxSale(grop);
        });
    }
    $(document).find('.SKYPhone').on('click', function() {
    nameSimT2m = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_')
    remanSimShopT2mult();
    });

    $(document).find('.SKYPhone').on('change', function() {
        $('.btn-primary').attr('disabled', false);

        $.get('/tableUpDistributionSim/' + shopT2m.trim() + '/' + nameSimT2m + '/' + this.value + '/' + gropT2m.trim(), {}, function(data) {

           tableUpDistriSim(data);
           updateRemanisCashT2m(gropT2m);
        });
    });
}

function viewT2m(key, data) {
    var view = false;
    for (keys in data[key]) {
        for (keyss in data[key][keys]) {
            if (Object.values(data[key][keys]).includes('t2m')) {
                view = true;
            }
        }
    }
    return view;
}
function tableUpDistriSim(data) {

    var tds = document.querySelectorAll('table.tables_DistribSim td');
    for (var i = 0; i < tds.length; i++) {
        for (key in data) {
            if (tds[i].lastElementChild != null && key == tds[i].lastElementChild.innerHTML) {
                for (keys in data[key]) {
                    if (keys == "total") {
                        for (keyss in data[key][keys]) {
                            if (keyss == "orderCash") {
                                tds[i + 5].innerHTML = data[key][keys][keyss];
                            }
                            if (keyss == "totalRemanisCash") {
                                tds[i + 4].innerHTML = data[key][keys][keyss];
                            }
                        }
                    }
                }
            }
            for (keys in data[key]) {
                if (keys == tds[i].innerHTML) {
                    for (keyss in data[key][keys]) {
                        if (keyss == "remanisCash" && tds[i + 4].innerHTML != data[key][keys][keyss]) {
                            tds[i + 4].innerHTML = data[key][keys][keyss];
                        }
                    }
                }
            }
        }
    }
}