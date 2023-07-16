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
    console.log(data)
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
                td.innerHTML = data[i].startDate;
            }
            if (j == 5) {
                td.innerHTML = data[i].endDate;
            }
            if (j == 6) {
                td.innerHTML = data[i].dataSale;
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
    tbody.classList.add("labels2");
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
                td.innerHTML = data[i].startDate;
            }
            if (j == 5) {
                td.innerHTML = data[i].endDate;
            }
            if (j == 6) {
                td.innerHTML = data[i].dataSale;
            }
            if (j == 7) {
                td.innerHTML = data[i].shop;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    // $("#sum").html("ИТОГО БОНУСОВ"+"  "+ sum +"  "+"РУБ");
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function getFirstDayOfMonth(year, month) {
    return new Date(year, month, 1);
}