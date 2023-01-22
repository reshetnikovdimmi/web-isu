const requestURL = '/requirementPhone'
const requestUpURL = '/requirementUpPhone'
const requestURLremanis = '/remanisWarehousePhone'
const requestURLmatrixT2 = '/matrixT2Phone'
const requestURLsky = '/skyPhone'
var cou = 0;
var body;
$(document).ready(function() {
    $('#loader').removeClass('hidden')
    requirementPhone(requestURL);
    remanisWarehousePhone(requestURLremanis);
    matrixT2Phone(requestURLmatrixT2);
});

function requirementPhone(requestURL) {
    $.get(requestURL, function(requirementPhoneArr, status) {
        requirementPhone_mono(requirementPhoneArr);
        requirementPhone_multi(requirementPhoneArr);
    });
}

function remanisWarehousePhone(requestURLremanis) {
    $.get(requestURLremanis, function(remanisWarehousePhone, status) {
        table_remanisWarehousePhone(remanisWarehousePhone);
    });
}

function matrixT2Phone(requestURLmatrixT2) {
    $.get(requestURLmatrixT2, function(matrixT2Phone, status) {

        table_matrixT2Phone(matrixT2Phone);
    });
}

function table_matrixT2Phone(matrixT2Phone) {

    var uniqueArrays = [];
    for (var i = 0; i < matrixT2Phone.length; i++) {
        uniqueArrays.push(matrixT2Phone[i].shop);
    }
    var row = uniqueArray(uniqueArrays);
    var uniqueArrays = [];
    uniqueArrays.push("МОДЕЛЬ РАСПРЕДЕЛЕНИЯ");
    for (var i = 0; i < matrixT2Phone.length; i++) {
        uniqueArrays.push(matrixT2Phone[i].distributionModel);
    }
    var cell = uniqueArray(uniqueArrays);
    var elem = document.querySelector('#table_MatrixT2');
    var elem1 = document.querySelector('#tables_MatrixT2');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_MatrixT2';
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let tbody = document.createElement('tbody');
    for (var i = 0; i < cell.length; i++) {
        let heading_1 = document.createElement('th');
        heading_1.innerHTML = cell[i].replaceAll("/", " ");
        row_1.appendChild(heading_1);
    }
    for (var i = 0; i < row.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < cell.length; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = row[i];
            }
            for (var k = 0; k < matrixT2Phone.length; k++) {
                if (row[i] == matrixT2Phone[k].shop && cell[j] == matrixT2Phone[k].distributionModel) {
                    td.innerHTML = matrixT2Phone[k].quantity+"%";
                    if(matrixT2Phone[k].quantity<100){
                    td.style.color = "#ff0000";
                    }


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

function table_remanisWarehousePhone(remanisWarehousePhone) {
    var elem = document.querySelector('#table_remanisWarehousePhone');
    var elem1 = document.querySelector('#tables_remanisWarehousePhone');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_remanisWarehousePhone';
    table.classList.add("table-borderless");
    table.classList.add("tables_remanisWarehousePhone");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "модель";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Осн склад";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Склад Т2";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < remanisWarehousePhone.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 3; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = remanisWarehousePhone[i].modelPhone;
            } else if (j == 1) {
                td.innerHTML = remanisWarehousePhone[i].remanisMainWarehouse;
            } else if (j == 2) {
                td.innerHTML = remanisWarehousePhone[i].remanisWarehouseT2;
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

function requirementPhone_mono(requirementPhoneArr) {
    var elem = document.querySelector('#table_requirement_t2');
    var elem1 = document.querySelector('#tables_requirement_t2');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_requirement_t2';
    table.classList.add("table-borderless");
    table.classList.add("tables_requirement_t2");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Магазин";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Остаток";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Потребность";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Ассортимент";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "%";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < requirementPhoneArr.length; i++) {
        if (requirementPhoneArr[i].cluster != '') {
            var tr = document.createElement('tr');
            for (var j = 0; j < 5; j++) {
                var td = document.createElement('td');
                if (j == 0) {
                    var button = document.createElement('button')
                    button.type = 'button';
                    td.appendChild(button);
                    button.classList.add("button");
                    button.classList.add("requirementPhone");
                    button.id = 'button';
                    button.innerHTML = requirementPhoneArr[i].shop;
                } else if (j == 1) {
                    td.innerHTML = requirementPhoneArr[i].remanis;
                } else if (j == 2) {
                    td.innerHTML = requirementPhoneArr[i].requirement;
                } else if (j == 3) {
                    td.innerHTML = requirementPhoneArr[i].representation;
                } else if (j == 4) {
                    td.innerHTML = requirementPhoneArr[i].percent;
                }
                tr.appendChild(td);
            }
            tbody.appendChild(tr);
        }
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function requirementPhone_multi(requirementPhoneArr) {
    var elem = document.querySelector('#table_requirement_t2_mult');
    var elem1 = document.querySelector('#tables_requirement_t2_mult');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_requirement_t2_mult';
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Магазин";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Остаток";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Потребность";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Ассортимент";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "%";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < requirementPhoneArr.length; i++) {
        if (requirementPhoneArr[i].cluster === '') {
            var tr = document.createElement('tr');
            for (var j = 0; j < 5; j++) {
                var td = document.createElement('td');
                if (j == 0) {
                    var button = document.createElement('button')
                    button.type = 'button';
                    td.appendChild(button);
                    button.classList.add("button");
                    button.classList.add("requirementPhone");
                    button.id = 'button';
                    button.innerHTML = requirementPhoneArr[i].shop;
                } else if (j == 1) {
                    td.innerHTML = requirementPhoneArr[i].remanis;
                } else if (j == 2) {
                    td.innerHTML = requirementPhoneArr[i].requirement;
                } else if (j == 3) {
                    td.innerHTML = requirementPhoneArr[i].representation;
                } else if (j == 4) {
                    td.innerHTML = requirementPhoneArr[i].percent;
                }
                tr.appendChild(td);
            }
            tbody.appendChild(tr);
        }
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.requirementPhone').on('click', function() {
        var shopSKY = $(this).parents('tr:first').find('td:eq(0)').text();

        const url = '/requirementPhone/' + shopSKY;

        $.get(url, function(requirementPhone, status) {
        var shop = requirementPhone[1].shop;
document.querySelector('#ShopDistribution').innerHTML = shop;
            distributionPhone1(requirementPhone, shopSKY);

        });
    });
    $('#loader').addClass('hidden')
}

function distributionPhone1(requirementPhone, shopSKY) {
    var elem = document.querySelector('#table_distributionPhone');
    var elem1 = document.querySelector('#tables_distributionPhone');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_distributionPhone';
    table.classList.add("tables_distributionPhone");
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Модель";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "SKY";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Остаток";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < requirementPhone.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 4; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = requirementPhone[i].modelPhone;
            } else if (j == 1 && requirementPhone[i].sky == false) {
                var input = document.createElement('input');
                td.appendChild(input);
                input.classList.add("SKYPhone");
                input.value = requirementPhone[i].skyPhone;
            } else if (j == 1 && requirementPhone[i].sky == true) {
                td.innerHTML = requirementPhone[i].skyPhone;
            } else if (j == 2) {
                td.innerHTML = requirementPhone[i].remanisPhone;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.SKYPhone').on('change', function() {
        body = [];
        $('.btn-primary').attr('disabled', false);
        body = {
            shop: shopSKY,
            modelPhone: $(this).parents('tr:first').find('td:eq(0)').text(),
            skyPhone: this.value
        }
        if (checkingStockSvailability()) {
            $('#loader').removeClass('hidden')

            sendRequest('POST', requestURLsky, body).then(data => updateSkyTables(data)).catch(err => console.log(err))
        }
    });
}

function checkingStockSvailability() {
    var stocks = true;
    var array = [];
    var tds = document.querySelectorAll('table.tables_requirement_t2 button');
    for (var i = 0; i < tds.length; i++) {
        array.push(tds[i].innerHTML);
    }
    if (array.includes(body.shop)) {
        tds = document.querySelectorAll('table.tables_remanisWarehousePhone td');
        array = [];
        for (var i = 0; i < tds.length; i += 3) {
            array.push(tds[i].innerHTML);
            array.push(tds[i + 1].innerHTML);
            array.push(tds[i + 2].innerHTML);
        }
        if (array.includes(body.modelPhone) && parseInt(array[array.indexOf(body.modelPhone) + 2]) >= parseInt(body.skyPhone)) {
            stocks = true;
        } else if (array.includes(body.modelPhone) && parseInt(array[array.indexOf(body.modelPhone) + 2]) < parseInt(body.skyPhone)) {
            stocks = false;
            modals("на складе Т2 нет данной модели. Использовать основной склад?");
            $('.btn-primary').on('click', function() {
                cou++;
                if (cou == 1) {
                    if (array.includes(body.modelPhone) && parseInt(array[array.indexOf(body.modelPhone) + 1]) >= parseInt(body.skyPhone) - parseInt(array[array.indexOf(body.modelPhone) + 2])) {
                        stocks = true;
                        $('#loader').removeClass('hidden')
                        sendRequest('POST', requestURLsky, body).then(data => updateSkyTables(data)).catch(err => console.log(err))
                        $('#myModal').modal('hide');
                    } else {
                        modals("нет на складах такого количества");
                        $('.btn-primary').attr('disabled', true);
                        stocks = false;
                    }
                }
            });
        } else {
            modals("нет такого модели на складе");
            $('.btn-primary').attr('disabled', true);
            stocks = false;
        }
    } else {
        tds = document.querySelectorAll('table.tables_remanisWarehousePhone td');
        array = [];
        for (var i = 0; i < tds.length; i += 3) {
            array.push(tds[i].innerHTML);
            array.push(tds[i + 1].innerHTML);
            array.push(tds[i + 2].innerHTML);
        }
        if (array.includes(body.modelPhone) && parseInt(array[array.indexOf(body.modelPhone) + 1]) >= parseInt(body.skyPhone)) {
            stocks = true;
        } else if (array.includes(body.modelPhone) && parseInt(array[array.indexOf(body.modelPhone) + 1]) < body.skyPhone) {
            stocks = false;
            modals("на складе нет данной модели. Использовать основной склад t2?");
            $('.btn-primary').on('click', function() {
                cou++;
                if (cou == 1) {
                    if (array.includes(body.modelPhone) && parseInt(array[array.indexOf(body.modelPhone) + 2]) >= parseInt(body.skyPhone)) {
                        stocks = true;
                        $('#loader').removeClass('hidden')
                        sendRequest('POST', requestURLsky, body).then(data => updateSkyTables(data)).catch(err => console.log(err))
                        $('#myModal').modal('hide');
                    } else {
                        modals("нет на складах такого количества");
                        $('.btn-primary').attr('disabled', true);
                        stocks = false;
                    }
                }
            });
        } else {
            modals("нет такого модели на складе");
            $('.btn-primary').attr('disabled', true);
            stocks = false;
        }
    }
    return stocks;
}

function uniqueArray(a) {
    function onlyUnique(value, index, self) {
        return self.indexOf(value) === index;
    }
    var unique = a.filter(onlyUnique);
    return unique;
}

function updateSkyTables(data) {
   // $('#loader').addClass('hidden')
        //matrixT2Phone(requestURLmatrixT2);
    var tds = document.querySelectorAll('table.tables_distributionPhone td');
    for (var i = 0; i < tds.length; i += 4) {
        $.each(data, function(key, value) {
            if (tds[i].innerHTML == value.modelPhone && tds[i + 2].innerHTML != value.remanisPhone) {
                tds[i + 2].innerHTML = value.remanisPhone;
            }
        });
    }
    // requirementPhone(requestURL);
    $.get(requestURLremanis, function(remanisWarehousePhone, status) {


        var tds = document.querySelectorAll('table.tables_remanisWarehousePhone td');
        for (var i = 0; i < tds.length; i += 3) {
            $.each(remanisWarehousePhone, function(key, value) {

                if (tds[i].innerHTML == value.modelPhone && tds[i + 2].innerHTML != value.remanisWarehouseT2) {

                    tds[i + 2].innerHTML = value.remanisWarehouseT2;
                }
                if (tds[i].innerHTML == value.modelPhone && tds[i + 1].innerHTML != value.remanisMainWarehouse) {
                    tds[i + 1].innerHTML = value.remanisMainWarehouse;
                }
            });
        }
    });
    matrixT2Phone(requestURLmatrixT2);
     $.get(requestUpURL, function(requestUpURL, status) {

          requirementPhone_mono(requestUpURL);
          requirementPhone_multi(requestUpURL);
        });
    $('#loader').addClass('hidden')
}

function sendRequest(method, url, body = null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()
        xhr.open(method, url)
        xhr.responseType = 'json'
        xhr.setRequestHeader('Content-Type', 'application/json')
        xhr.onload = () => {
            if (xhr.status >= 400) {
                reject(xhr.response)
            } else {
                resolve(xhr.response)
            }
        }
        xhr.onerror = () => {
            reject(xhr.response)
        }
        xhr.send(JSON.stringify(body))
    })
}

function modals(message) {
    cou = 0;
    $('#myModal').modal("show");
    document.querySelector('.modal-body').textContent = message;
    $('.btn-close').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-secondary').on('click', function() {
        $('#myModal').modal('hide');
    });
}