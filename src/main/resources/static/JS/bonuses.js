$(document).ready(function() {
    $('#dropDownListPhone').on('change', function() {
        $.get('/dropDownListModelGB/' + $('#dropDownListPhone').val().trim(), {}, function(data) {
            $(".dropDownListModelGB").html(data);
        });
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
            bonuses.provider = $('#dropDownListProvider').val()
        }
        if ($('#dropDownListPhone').val() != 'null') {
            bonuses.phone = $('#dropDownListPhone').val();
        }
        if ($('#startDate').val() != '') {
            bonuses.startDate = $('#startDate').val();
        }

        if ($('#endDate').val() != '') {
            bonuses.endDate = $('#endDate').val().trim();
        }
        sendRequest('POST', '/buttonShowAll', bonuses).then(data => orderFromMinMatrixT2Warehouse(data)).catch(err => console.log(err))
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
function orderFromMinMatrixT2Warehouse(data) {
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
   // const sumValues = data => Object.values(data.compensation).reduce((a, b) => a + b, 0);
   // heading_1.innerHTML = sumValues;
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
    for (var i = 0;i< data.length; i++) {
        let tbody1 = document.createElement('tbody');
        table.id = 'tables_DistributionButton';
        var tr = document.createElement('tr');
        for (var j = 0; j < 6; j++) {

            var td = document.createElement('td');
            if (j == 0) {
            td.innerHTML = data[i].models;
            }
            if (j == 1) {
            td.innerHTML = data[i].imei;
            }
            if (j == 2) {
            td.innerHTML = data[i].compensation;
            }
            if (j == 3) {
            td.innerHTML = data[i].provider;
            }
            if (j == 4) {
            td.innerHTML = data[i].startDate;
            }
            if (j == 5) {
            td.innerHTML = data[i].models;
            }

            tr.appendChild(td);
        }
        tbody1.appendChild(tr);
        tbody1.classList.add("labels");
        tbody.appendChild(tbody1);


    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);

}