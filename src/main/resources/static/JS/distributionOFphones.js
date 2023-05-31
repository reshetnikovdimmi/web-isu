var matrixT2;
var shop;
var name;
$(document).ready(function() {
    $('#loader').removeClass('hidden')
    remanisPhoneSach();
$('#loader').addClass('hidden')
});

function remanisPhoneSach() {

    $(document).find('.tableDistributionModelPhone .btn').on('click', function() {
    $('#loader').removeClass('hidden')
        matrixT2 = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_');
        $.get('/RemanisPhoneSach/' + matrixT2, {}, function(data) {
        createRemanisPhoneShopT2();
            $(".RemanisPhoneSach").html(data);
        });

    });
}

function createRemanisPhoneShopT2() {
    $.get('/CreateRemanisPhonesShopT2', {}, function(data) {
        $(".RemanisPhoneShop").html(data);

        remanisPhoneShopT2();
        createMatrixT2();
        $('#loader').addClass('hidden')
    });
}
function createMatrixT2() {
    $.get('/CreateMatrixT2', {}, function(data) {


        $(".CreateMatrixT2").html(data);

    });
}

function remanisPhoneShopT2() {
    $(document).find('.RemanisPhoneShop .btn').on('click', function() {
        shop = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_');

            $.get('/TableDistributionPhone/' + shop, {}, function(data) {

                  tableDistributionPhone(data);

                  });

    });
}
function tableDistributionPhone(data) {
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
    heading_1.id = 'first';
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "ОСТ";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "ПРОД6";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "ПРОД";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "ОСТСК";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "ОСТСК2";
    let heading_7 = document.createElement('th');
    heading_7.innerHTML = "ЗАКАЗ";
    let heading_8 = document.createElement('th');
    heading_8.innerHTML = "МАТРИЦ";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    row_1.appendChild(heading_7);
    row_1.appendChild(heading_8);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels2");
    for (key in data) {

            let tbody1 = document.createElement('tbody');
            table.id = 'tables_DistributionRTK';
            var tr = document.createElement('tr');
            for (var j = 0; j < 8; j++) {
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
                                 if (j == 5 && keyss === "totalRemanisCash2") {
                                  td.innerHTML = data[key][keys][keyss];
                                  }
                                if (j == 6 && keyss === "orderCash") {
                                    td.innerHTML = data[key][keys][keyss];
                                }

                                 if (j == 7 && keyss === "matrix") {
                                 td.innerHTML = data[key][keys][keyss]
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
                for (var j = 0; j < 8; j++) {
                    var td = document.createElement('td');
                    if (keys != "total") {
                        if (j == 0) {
                            td.innerHTML = keys;
                            td.id = 'first';
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
                                if (j == 5 && keyss === "remanisCash2") {
                                 td.innerHTML = data[key][keys][keyss]
                                 }
                                if (j == 6 && keyss === "order") {
                                    var input = document.createElement('input');
                                    td.appendChild(input);
                                    input.classList.add("SKYPhone");
                                    input.value = data[key][keys][keyss]
                                }
                                 if (j == 7) {
                                     td.innerHTML = ''
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
        if (tds[i].innerHTML == matrixT2) {

            tds[i].click();
            tds[i].scrollIntoView();
        }
        tds[i].addEventListener('click', function func() {
            matrixT2 = this.innerHTML;


           // updateRemanisCash();
           // updateRemanisSaleRTKShop()
        });
    }
    $(document).find('.SKYPhone').on('click', function() {
  name = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_')

    });

    $(document).find('.SKYPhone').on('change', function() {
        $('.btn-primary').attr('disabled', false);
alert(name+"--"+matrixT2)
        $.get('/tableUpDistriPhone/' + shop.trim() + '/' + name + '/' + this.value + '/' + matrixT2.trim().replaceAll('/', '_'), {}, function(data) {
console.log(data)
          tableUpDistributionButton(data);
         //   updateRemanisCash(grop);
        });
    });
}
function tableUpDistributionButton(data) {

    var tds = document.querySelectorAll('table.tables_DistributionRTK td');
    for (var i = 0; i < tds.length; i++) {
        for (key in data) {
            if (tds[i].lastElementChild != null && key == tds[i].lastElementChild.innerHTML) {
                for (keys in data[key]) {
                    if (keys == "total") {
                        for (keyss in data[key][keys]) {
                            if (keyss == "orderCash") {
                                tds[i + 6].innerHTML = data[key][keys][keyss];
                            }
                            if (keyss == "totalRemanisCash2") {
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
                        if (keyss == "remanisCash2" && tds[i + 5].innerHTML != data[key][keys][keyss]) {
                            tds[i + 5].innerHTML = data[key][keys][keyss];
                        }

                    }
                }
            }
        }
    }
}