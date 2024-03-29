var brend;
$(document).ready(function() {
    var tds = document.querySelectorAll('.table_graduation .btn');
    table_DistributionButton(tds);
    window.onload = function() {
        var tds1 = document.querySelectorAll('.table_Requirement .btn');
        orderFromWarehouse(tds1);
    }
});

function orderFromWarehouse(tds1) {
    for (var i = 1; i < tds1.length; i++) {
        tds1[i].addEventListener('click', function func() {
            var shop = this.innerHTML;
            $.get('/tableDistributionButton/' + shop, {}, function(data) {
                orderFromMinMatrixT2Warehouse(data, shop);

            });
        });
    }
}

function table_DistributionButton(tds) {
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            brend = this.innerHTML;
            $.get('/tableShopRemanisSele/' + brend, {}, function(data) {
                $(".graduation").html(data);
                var tds = document.querySelectorAll('.graduation .btn');
                orderFromWarehouse(tds);
                $('#grad').html(brend);
            });
            $.get('/tableShopRemanisCash/' + brend, {}, function(data) {
                $(".RemanisCash").html(data);

            });
        });
    }
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
                    for (keyss in data[key][keys]) {
                        if (j == 1 && keyss === "Remanis") {
                            td.innerHTML = data[key][keys][keyss];
                        }
                        if (j == 2 && keyss === "Sale 1m") {
                            td.innerHTML = data[key][keys][keyss];
                        }
                        if (j == 3 && keyss === "Sale 6_3m") {
                            td.innerHTML = data[key][keys][keyss];
                        }
                        if (j == 4 && keyss === "RemanisCash") {
                            td.innerHTML = data[key][keys][keyss];
                        }
                        if (j == 5 && keyss === "Order") {
                            td.innerHTML = data[key][keys][keyss];
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
                            if (j == 1 && keyss === "ОСТ") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 2 && keyss === "ПРОД1") {
                                td.innerHTML = data[key][keys][keyss];
                            }
                            if (j == 3 && keyss === "ПРОД6") {
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
    $(document).find('.minMatrix').on('click', function() {
        $(this).parents().next('.hide_minMatrix').toggle();
    });
    var tds = document.querySelectorAll('.minMatrix');
    table_DistributionButton(tds);
    $(document).find('.SKYPhone').on('change', function() {
        $('.btn-primary').attr('disabled', false);
        $.get('/tableUpDistributionButton/' + shop + '/' + $(this).parents('tr:first').find('td:eq(0)').text() + '/' + this.value + '/' + brend, {}, function(data) {
            $.get('/tableShopRemanisCash/' + brend, {}, function(data) {
                $(".RemanisCash").html(data);
                $('#grad').html(brend);
            });
            tableUpDistributionButton(data);

        });
    });

}
function tableUpDistributionButton(data) {
    var tds = document.querySelectorAll('table.tables_DistributionButton td');
    for (var i = 0; i < tds.length; i++) {
        for (key in data) {
            if (tds[i].lastElementChild != null && key == tds[i].lastElementChild.innerHTML) {
                for (keys in data[key]) {
                    if (keys == "ИТОГО") {
                        for (keyss in data[key][keys]) {
                            if (keyss == "Order") {
                                tds[i + 5].innerHTML = data[key][keys][keyss];
                            }
                            if (keyss == "RemanisCash") {
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