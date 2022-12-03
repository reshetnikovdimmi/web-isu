const requestURL = '/requirementPhone'
const requestURLremanis = '/remanisWarehousePhone'
$(document).ready(function() {
    requirementPhone(requestURL);
    remanisWarehousePhone(requestURLremanis);
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

function table_remanisWarehousePhone(remanisWarehousePhone) {
    var elem = document.querySelector('#table_remanisWarehousePhone');
    var elem1 = document.querySelector('#tables_remanisWarehousePhone');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_remanisWarehousePhone';
    table.classList.add("table-borderless");
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
        const url = '/requirementPhone/' + $(this).parents('tr:first').find('td:eq(0)').text();
        $.get(url, function(requirementPhone, status) {
            distributionPhone1(requirementPhone);
        });
    });
}

function distributionPhone1(requirementPhone) {
    console.log(requirementPhone);
    var elem = document.querySelector('#table_distributionPhone');
    var elem1 = document.querySelector('#tables_distributionPhone');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_distributionPhone';
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
}