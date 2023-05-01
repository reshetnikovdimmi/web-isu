var grop;
$(document).ready(function() {
    $(document).find('.btn').on('click', function() {
        grop = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/AccessoriesCategoryShop/' + grop, {}, function(data) {
            $(".AccessoriesCategoryShop").html(data);
            updateAccessoriesCategoryShop();
            accessoriesCategoryCash(grop);
            accessoriesCategoryMaxSale(grop);
        });

    });
});
function accessoriesCategoryShop(grop){

 $.get('/AccessoriesCategoryShop/' + grop, {}, function(data) {
            $(".AccessoriesCategoryShop").html(data);
updateAccessoriesCategoryShop();
        });

}
function accessoriesCategoryMaxSale(grop){
$.get('/AccessoriesCategoryMaxSale/' + grop, {}, function(data) {

            $(".AccessoriesCategoryMaxSale").html(data);
            $("#grad").html(grop);
        });
}

function accessoriesCategoryCash(grop){
 $.get('/AccessoriesCategoryCash/' + grop, {}, function(data) {
                    $(".RemanisCash").html(data);

                });
}


function updateAccessoriesCategoryShop() {
    $(document).find('.AccessoriesCategoryShop .btn').on('click', function() {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/AccessoriesCategoryNomenclatureShop/' + shop, {}, function(data) {
            orderFromMinMatrixT2Warehouse(data, shop);
            //  $(".AccessoriesCategoryShop").html(data);

        });
    });
}

function orderFromMinMatrixT2Warehouse(data, shop) {
    var elem = document.querySelector('#table_DistributionButton');
    var elem1 = document.querySelector('#tables_DistributionButton');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_DistributionButton';
    table.classList.add("table-borderless");
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
                    if (keys == "ИТОГО") {
                        for (keyss in data[key][keys]) {
                            if (j == 1 && keyss === "ОСТАТОК") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 2 && keyss === "ПРОД 1М") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 3 && keyss === "ПРОД 6/3М") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 4 && keyss === "ОСТСК") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 5 && keyss === "ЗАКАЗ") {
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
                if (keys != "ИТОГО") {
                    if (j == 0) {
                        td.innerHTML = keys;
                    } else if (j > 0) {
                        for (keyss in data[key][keys]) {
                            if (j == 1 && keyss === "ОСТАТОК") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 2 && keyss === "ПРОД 1М") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 3 && keyss === "ПРОД 6/3М") {
                                td.innerHTML = data[key][keys][keyss]
                            }
                            if (j == 4 && keyss === "ОСТСК") {
                                td.innerHTML = data[key][keys][keyss]
                            }
                            if (j == 5 && keyss === "ЗАКАЗ") {
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
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    var d;
    $(document).find('.minMatrix').on('click', function() {

    if(d!=undefined){
    d.parents().next('.hide_minMatrix').toggle();
    }

   d = $(this);
        d.parents().next('.hide_minMatrix').toggle();

    });
    var tds = document.querySelectorAll('.minMatrix');
    for (var i = 0; i < tds.length; i++) {
        if (tds[i].innerHTML == grop.trim()) {

            tds[i].click();
            tds[i].scrollIntoView();
        }
         tds[i].addEventListener('click', function func() {
                    grop = this.innerHTML;
                    accessoriesCategoryShop(grop);
                    accessoriesCategoryCash(grop);
                    accessoriesCategoryMaxSale(grop);
                });
    }
    //  table_DistributionButton(tds);
    $(document).find('.SKYPhone').on('change', function() {
        $('.btn-primary').attr('disabled', false);

        $.get('/tableUpDistributionShop/' + shop.trim() + '/' + $(this).parents('tr:first').find('td:eq(0)').text().trim() + '/' + this.value + '/' + grop.trim(), {}, function(data) {

            tableUpDistributionButton(data);
            accessoriesCategoryCash(grop);
        });
    });
}

function tableUpDistributionButton(data) {
console.log(data)
    var tds = document.querySelectorAll('table.tables_DistributionButton td');
    for (var i = 0; i < tds.length; i++) {
        for (key in data) {
            if (tds[i].lastElementChild != null && key == tds[i].lastElementChild.innerHTML) {
                for (keys in data[key]) {
                    if (keys == "ИТОГО") {
                        for (keyss in data[key][keys]) {
                            if (keyss == "ЗАКАЗ") {
                                tds[i + 5].innerHTML = data[key][keys][keyss];
                            }
                            if (keyss == "ОСТСК") {
                                tds[i + 4].innerHTML = data[key][keys][keyss];
                            }
                        }
                    }
                }
            }
            for (keys in data[key]) {
                if (keys == tds[i].innerHTML) {
                    for (keyss in data[key][keys]) {
                        if (keyss == "ОСТСК" && tds[i + 4].innerHTML != data[key][keys][keyss]) {
                            tds[i + 4].innerHTML = data[key][keys][keyss];
                        }
                    }
                }
            }
        }
    }
}