var grop;
var shop;
var name;
$(document).ready(function() {
    remanisCash();
});

function remanisCash() {
    $(document).find('.table_RTK .btn').on('click', function() {
        grop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/RemanisCashRTK/' + grop, {}, function(data) {
            $(".RemanRTKCash").html(data);
            remanisSaleRTKShop();
        });
    });
}
function updateRemanisCash() {

        $.get('/RemanisCashRTK/' + grop, {}, function(data) {
            $(".RemanRTKCash").html(data);

        });

}

function remanisSaleRTKShop() {
        $.get('/RemanisSaleRTKShop/' + grop, {}, function(data) {
            $(".MatrixRTKShop").html(data);
            createTableMatrixRTK();
            createTableDistributionRTK();
        });
    }
    function updateRemanisSaleRTKShop() {
            $.get('/RemanisSaleRTKShop/' + grop, {}, function(data) {
                $(".MatrixRTKShop").html(data);

            });
        }

function createTableMatrixRTK() {
  $.get('/TableMatrixRTK/', {}, function(data) {
            $(".TableMatrixRTK").html(data);

        });

}
function createTableDistributionRTK() {
$(document).find('.MatrixRTKShop .btn').on('click', function() {
        shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/TableDistributionRTK/' + shop, {}, function(data) {

            tableDistributionRTK(data);
        });
    });
}
function tableDistributionRTK(data) {
    var elem = document.querySelector('#table_DistributionRTK');
    var elem1 = document.querySelector('#tables_DistributionRTK');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_DistributionRTK';
    table.classList.add("table-borderless");
    table.classList.add("tables_DistributionRTK");
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
            table.id = 'tables_DistributionRTK';
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
                         /*   if (keys == nameSim.replaceAll('_', '/')) {

                                grop = key;
                            }*/
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


            updateRemanisCash();
            updateRemanisSaleRTKShop()
        });
    }
    $(document).find('.SKYPhone').on('click', function() {
  name = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_')

    });

    $(document).find('.SKYPhone').on('change', function() {
        $('.btn-primary').attr('disabled', false);

        $.get('/tableUpDistributionSim/' + shop.trim() + '/' + name + '/' + this.value + '/' + grop.trim(), {}, function(data) {

        //   tableUpDistributionButton(data);
         //   updateRemanisCash(grop);
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