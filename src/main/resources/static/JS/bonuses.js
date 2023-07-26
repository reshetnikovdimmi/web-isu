$(document).ready(function() {
    let bonuses = {
        shop: null,
        models: null,
        provider: null,
        phone: null,
        startDate: null,
        endDate: null,
    };
    const date = new Date();
    const firstDay = getFirstDayOfMonth(date.getFullYear(), date.getMonth(), );
    var day = ("0" + firstDay.getDate()).slice(-2);
    var month = ("0" + (firstDay.getMonth() + 1)).slice(-2);
    var today = firstDay.getFullYear() + "-" + (month) + "-" + (day);
    $("#start").val(today);
    bonuses.startDate = firstDay;
    sendRequest('POST', '/loadBonusesAll', bonuses).then(data => ceateTableAllBonus(data)).catch(err => console.log(err))
    sendRequest('POST', '/loadBonusesNoT2', bonuses).then(data => ceateTableNoT2Bonus(data)).catch(err => console.log(err))
    $('#dropDownListPhone').on('change', function() {
        $.get('/dropDownListModelGB/' + $('#dropDownListPhone').val().trim(), {}, function(data) {
            $(".dropDownListModelGB").html(data);
        });
    });
    $('#button-all').on('click', function() {
        let bonuses = {
            shop: null,
            models: null,
            provider: null,
            phone: null,
            startDate: null,
            endDate: null,
        };
        if ($('#start').val() != '') {
            bonuses.startDate = $('#start').val();
        }
        if ($('#stop').val() != '') {
            bonuses.endDate = $('#stop').val();
        }
        sendRequest('POST', '/loadBonusesAll', bonuses).then(data => ceateTableAllBonus(data)).catch(err => console.log(err))
        sendRequest('POST', '/loadBonusesNoT2', bonuses).then(data => ceateTableNoT2Bonus(data)).catch(err => console.log(err))
    });
     $('#search-Amount').on('click', function() {
      let bonusesPaid = {
                             id: null,
                             model: null,
                             startPromo: null,
                             endPromo: null,
                             amount: null,
                             suppliers: null,
                         };

             if ($('#dropDownListProviderAmount').val() != 'select option') {
                bonusesPaid.suppliers = $('#dropDownListProviderAmount').val();
             }
              if ($('#dropDownListPhoneAmount').val() != 'select option') {
                bonusesPaid.model = $('#dropDownListPhoneAmount').val();
              }
              if ($('#startDateAmount').val() != '') {
                bonusesPaid.startPromo = $('#startDateAmount').val();
              }
              if ($('#endDateAmount').val() != '') {
                bonusesPaid.endPromo = $('#endDateAmount').val();
              }

            var json = JSON.stringify(bonusesPaid);
            $.ajax({
                type: "POST",
                url: "searchAmount",
                data: json,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data) {
                console.log(data)
                ceateTableAmount(data)

                }
            });
            clear()
        });
    $('#save-Amount').on('click', function() {
        let bonusesPaid = {
            id: $('#IDupdate').val(),
            model: $('#dropDownListPhoneAmount').val(),
            startPromo: $('#startDateAmount').val(),
            endPromo: $('#endDateAmount').val(),
            amount: $('#amount').val(),
            suppliers: $('#dropDownListProviderAmount').val(),
        };
        if ($('#dropDownListProviderAmount').val() == 'select option') {
            modals("не заполнено поле поставщик")
        } else if ($('#dropDownListPhoneAmount').val() == 'select option') {
            modals("не заполнено поле модель")
        } else if ($('#startDateAmount').val() == '') {
            modals("не заполнено поле старт")
        } else if ($('#endDateAmount').val() == '') {
            modals("не заполнено поле конец")
        } else if ($('#amount').val() == '') {
            modals("не заполнено поле сумма")
        } else {
            sendRequest('POST', '/saveAmount', bonusesPaid).then(data => ceateTableAmount(data)).catch(err => console.log(err))
        }
    });
    $('#button-show-all').on('click', function() {
        let bonuses = {
            shop: null,
            models: null,
            provider: null,
            phone: null,
            startDate: null,
            endDate: null,
        };
        if ($('#dropDownListModelGB').val() != 'null') {
            bonuses.models = $('#dropDownListModelGB').val();
        }
        if ($('#dropDownListShop').val() != 'null') {
            bonuses.shop = $('#dropDownListShop').val();
        }
        if ($('#dropDownListProvider').val() != 'null') {
            bonuses.provider = $('#dropDownListProvider').val();
        }
        if ($('#dropDownListPhone').val() != 'null') {
            bonuses.phone = $('#dropDownListPhone').val();
        }
        if ($('#startDate').val() != '') {
            bonuses.startDate = $('#startDate').val();
        }
        if ($('#endDate').val() != '') {
            bonuses.endDate = $('#endDate').val();
        }
        sendRequest('POST', '/buttonShowAll', bonuses).then(data => ceateTableSearchBonus(data)).catch(err => console.log(err))
    });
    delet()
    update()
});

function sendRequest(method, url, bonuses = null) {
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
        xhr.send(JSON.stringify(bonuses))
    })
}

function ceateTableAllBonus(data) {

    var elem = document.querySelector('#table_TableAllBonus');
    var elem1 = document.querySelector('#tables_TableAllBonus');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_TableAllBonus';
    table.classList.add("table-borderless");
    table.classList.add("tables_TableAllBonus");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Компенс";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "Дистр";
    let heading_6 = document.createElement('th');;
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels");
    var sum = 0;
    for (var i = 0; i < data.length; i++) {
        table.id = 'tables_TableAllBonus';
        var tr = document.createElement('tr');
        for (var j = 0; j < 2; j++) {
            var td = document.createElement('td');
            if (j == 1) {
                sum = sum + data[i].compensation;
                td.innerHTML = data[i].compensation + " " + "РУБ";
            }
            if (j == 0) {
                td.innerHTML = data[i].provider;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    $("#sumAll").html("ИТОГО БОНУСОВ" + "  " + sum + "  " + "РУБ");
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function ceateTableSearchBonus(data) {
    var elem = document.querySelector('#table_TableSearchBonus');
    var elem1 = document.querySelector('#tables_TableSearchBonus');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_TableSearchBonus';
    table.classList.add("table-borderless");
    table.classList.add("tables_TableSearchBonus");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Модель";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "ИМЕИ";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Компенс";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "Дистр";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "Старт";
    let heading_7 = document.createElement('th');
    heading_7.innerHTML = "Конец";
    let heading_8 = document.createElement('th');
    heading_8.innerHTML = "Конец";
    let heading_9 = document.createElement('th');
    heading_9.innerHTML = "Магазин";
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    row_1.appendChild(heading_7);
    row_1.appendChild(heading_8);
    row_1.appendChild(heading_9);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels2");
    var sum = 0;
    for (var i = 0; i < data.length; i++) {
        table.id = 'tables_TableSearchBonus';
        var tr = document.createElement('tr');
        for (var j = 0; j < 8; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].models;
            }
            if (j == 1) {
                td.innerHTML = data[i].imei;
            }
            if (j == 2) {
                sum = sum + data[i].compensation;
                td.innerHTML = data[i].compensation;
            }
            if (j == 3) {
                td.innerHTML = data[i].provider;
            }
            if (j == 4) {

                td.innerHTML = dateFormat(data[i].startDate);
            }
            if (j == 5) {
                td.innerHTML = dateFormat(data[i].endDate);
            }
            if (j == 6) {
                td.innerHTML = dateFormat(data[i].dataSale);
            }
            if (j == 7) {
                td.innerHTML = data[i].shop;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    $("#sum").html("ИТОГО БОНУСОВ" + "  " + sum + "  " + "РУБ");
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}
function dateFormat(data) {
var mydate = new Date(data);
    return  new Intl.DateTimeFormat().format(mydate);
}
function ceateTableNoT2Bonus(data) {
    var elem = document.querySelector('#table_TableNoT2Bonus');
    var elem1 = document.querySelector('#tables_TableNoT2Bonus');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_TableNoT2Bonus';
    table.classList.add("table-borderless");
    table.classList.add("tables_TableNoT2Bonus");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Модель";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "ИМЕИ";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Компенс";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "Дистр";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "Старт";
    let heading_7 = document.createElement('th');
    heading_7.innerHTML = "Конец";
    let heading_8 = document.createElement('th');
    heading_8.innerHTML = "Конец";
    let heading_9 = document.createElement('th');
    heading_9.innerHTML = "Магазин";
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    row_1.appendChild(heading_7);
    row_1.appendChild(heading_8);
    row_1.appendChild(heading_9);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels");
    var sum = 0;
    for (var i = 0; i < data.length; i++) {
        table.id = 'tables_TableNoT2Bonus';
        var tr = document.createElement('tr');
        for (var j = 0; j < 8; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].models;
            }
            if (j == 1) {
                td.innerHTML = data[i].imei;
            }
            if (j == 2) {
                sum = sum + data[i].compensation;
                td.innerHTML = data[i].compensation;
            }
            if (j == 3) {
                td.innerHTML = data[i].provider;
            }
            if (j == 4) {
                td.innerHTML = dateFormat(data[i].startDate);
            }
            if (j == 5) {
                td.innerHTML = dateFormat(data[i].endDate);
            }
            if (j == 6) {
                td.innerHTML = dateFormat(data[i].dataSale);
            }
            if (j == 7) {
                td.innerHTML = data[i].shop;
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

function ceateTableAmount(data) {

    var elem = document.querySelector('#table_tableAmount');
    var elem1 = document.querySelector('#tables_tableAmount');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_tableAmount';
    table.classList.add("table");
    table.classList.add("table-hover");
    table.classList.add("tables_tablePromo");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "id";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "поставщик";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "модель";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "старт";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "конец";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "сумма"
    let heading_12 = document.createElement('th');
    heading_12.innerHTML = " "
    let heading_13 = document.createElement('th');
    heading_13.innerHTML = " "
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);;
    row_1.appendChild(heading_12);
    row_1.appendChild(heading_13);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels");
    var sum = 0;
    for (var i = 0; i < data.length; i++) {
        table.id = 'tables_tableAmount';
        var tr = document.createElement('tr');
        for (var j = 0; j < 8; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].id;
            }
            if (j == 1) {
                td.innerHTML = data[i].suppliers;
            }
            if (j == 2) {
                td.innerHTML = data[i].model;
            }
            if (j == 3) {
                td.innerHTML = data[i].startPromo;
            }
            if (j == 4) {
                td.innerHTML = data[i].endPromo;
            }
            if (j == 5) {
            sum = sum + data[i].amount;
                td.innerHTML = data[i].amount;
            }
            if (j == 6) {
                var button = document.createElement('button')
                button.type = 'button';
                td.appendChild(button);
                button.classList.add("btn");
                button.classList.add("btn-outline-primary");
                button.classList.add("UPDATE");
                button.id = 'button';
                button.innerHTML = "UPDATE";
            }
            if (j == 7) {
                var button = document.createElement('button')
                button.type = 'button';
                td.appendChild(button);
                button.classList.add("btn");
                button.classList.add("btn-outline-primary");
                button.classList.add("DEL");
                button.id = 'button';
                button.innerHTML = "DEL";;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
     $("#sumAmount").html("ИТОГО БОНУСОВ"+"  "+ sum +"  "+"РУБ");
    delet()
    update()
    clear()
}

function delet() {
    $(document).find('.DEL').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;
        $.get('/deleteAmount/' + models, {}, function(data) {
            ceateTableAmount(data);
        });
    });
}

function update() {
    $(document).find('.UPDATE').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;
        $.get('/updateAmount/' + models, {}, function(data) {
            $('#IDupdate').val(data.id)
            $('#startDateAmount').val(data.startPromo);
            $('#endDateAmount').val(data.endPromo);
            $('#amount').val(data.amount);
        });
    });
}

function clear() {
    $('#IDupdate').val(0)
    $('#startDateAmount').val('');
    $('#endDateAmount').val('');
    $('#amount').val('');
}

function getFirstDayOfMonth(year, month) {
    return new Date(year, month, 1);
}

function modals(message) {
    $('#myModal').modal("show");
    document.querySelector('.modal-body').textContent = message;
    $('.btn-close').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-secondary').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-primary').attr('disabled', true);
}