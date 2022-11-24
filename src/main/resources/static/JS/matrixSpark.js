const requestURL = '/matrixSparkSale'
const requestURLupdate = '/updateSparkSale'
const requestURLsave = '/saveSparkSale'
$(document).ready(function() {
    $('#updateMatrixSpark').on('click', function() {
        $.get(requestURLupdate, function(sparkSalePhone, status) {
            console.log(sparkSalePhone);
            createTableSpark(sparkSalePhone);
        });
    });
    $('#saveMatrixSpark').on('click', function() {
        var bodyArr = [];
        $('input:checkbox:checked').each(function() {
            const body = {
                model: "0",
                sale: "0"
            }
            body.model = $(this).parents('tr:first').find('td:eq(0)').text();
            body.sale = $(this).parents('tr:first').find('td:eq(1)').text();
            bodyArr.push(body);
        });
        sendRequest('POST', requestURLsave, bodyArr)
        .then(data => createTableSpark(data))
        .catch(err => console.log(err))
    });
});
$.get(requestURL, function(sparkSalePhone, status) {
    createTableSpark(sparkSalePhone);
});

function delModel() {
    var cou = 0;
    $(document).find('.del').on('click', function() {
        if ($(this).is(':checked')) {
            cou++;
            $('#recommendedValue').text(cou);
        } else {
            cou--;
            $('#recommendedValue').text(cou);
        }
    });
}

function createTableSpark(sparkSalePhone) {
    var elem = document.querySelector('#table_Spark');
    var elem1 = document.querySelector('#tables_Spark');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_Spark';
    table.classList.add("table-borderless-1");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "МОДЕЛЬ";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Sale";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Save";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < sparkSalePhone.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 3; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = sparkSalePhone[i].model;
            } else if (j == 1) {
                td.innerHTML = sparkSalePhone[i].sale;
            } else {
                var button = document.createElement('input')
                button.type = 'checkbox';
                td.appendChild(button);
                button.classList.add("del");
                button.id = 'del';
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    delModel();
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