$(document).ready(function() {
    const requestURL = '/matrixT2table'
    const requestURL_2 = '/matrixT2Update'
    createTables(requestURL);
    $('#submit').on('click', function() {
        if ($('#pdistributionModel').val() === '') {
            modals();
        }
        var bodyArr = [];
        var tds = document.querySelectorAll('#matrixT2 input');
        for (var i = 0; i < tds.length; i += 2) {
            const body = {
                id: 1,
                distributionModel: "tab",
                cluster: "tab",
                quantity: "tab"
            }
            body.id = i;
            body.distributionModel = $('#pdistributionModel').val();
            body.cluster = tds[i].value;
            body.quantity = tds[i + 1].value;
            bodyArr.push(body);
        }
        console.log(bodyArr);
        sendRequest('POST', requestURL_2, bodyArr)
        .then(data => updateTables(data))
        .catch(err => console.log(err))
        var arr = [];
        modal(arr);
        $('#pdistributionModel').val('');
    });
    $('#addCluster').on('click', function() {
        var arr = [];
        var tds = document.querySelectorAll('#matrixT2 input');
        for (var i = 0; i < tds.length; i++) {
            arr.push(tds[i].value);
        }
        arr.push(null, null);
        modal(arr);
    });
    $('#delCluster').on('click', function() {
        var arr = [];
        var tds = document.querySelectorAll('#matrixT2 input');
        for (var i = 0; i < tds.length - 2; i++) {
            arr.push(tds[i].value);
        }
        const requestURL = '/matrixT2Del/' + $('#pdistributionModel').val().replaceAll("/", "_")

                createTables(requestURL);
        modal(arr);
    });
});

function createArrCluster() {}

function createTables(requestURL) {
    $.get(requestURL, function(distribution, status) {
        var elem = document.querySelector('#table_MatrixT2');
        var elem1 = document.querySelector('#tables_MatrixT2');
        elem1.parentNode.removeChild(elem1);
        createTable(elem, 6, distribution.length, distribution);
        var arr = [];
        modal(arr);
    });
}
function updateTables(data) {

        var elem = document.querySelector('#table_MatrixT2');
        var elem1 = document.querySelector('#tables_MatrixT2');
        elem1.parentNode.removeChild(elem1);
        createTable(elem, 6, data.length, data);
        var arr = [];
        modal(arr);

}

function modals() {
    $('#myModal').modal("show");
    $('.btn-close').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-secondary').on('click', function() {
        $('#myModal').modal('hide');
    });
}

function modal(arr) {
    /* */
    var elem = document.querySelector('#matrixT2');
    var elem1 = document.querySelector('#matrixT2_3');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'matrixT2_3';
    table.classList.add("table-borderless-1");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "кластер";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "количество";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    thead.appendChild(row_1);
    table.appendChild(thead);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < arr.length; i += 2) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 2; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                var input = document.createElement('input');
                td.appendChild(input);
                input.value = arr[i];
            } else if (j == 1) {
                var input = document.createElement('input');
                td.appendChild(input);
                input.value = arr[i + 1];
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    elem.appendChild(table);
}

function createTable(parent, cols, rows, shops) {
    var table = document.createElement(`table`);
    table.id = 'tables_MatrixT2';
    table.classList.add("table-borderless-1");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    var arr = createArr(shops);
    for (var i = 0; i < arr.length; i++) {
        let heading_1 = document.createElement('th');
        heading_1.innerHTML = arr[i];
        row_1.appendChild(heading_1);
    }
    thead.appendChild(row_1);
    table.appendChild(thead);
    let tbody = document.createElement('tbody');
    var arrrows = createArrRows(shops);
    for (var i = 0; i < arrrows.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < arr.length; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = arrrows[i];
            } else if (j == arr.length - 2) {
                var button = document.createElement('button')
                td.appendChild(button);
                button.innerHTML = "edit";
                button.classList.add("btn");
                button.classList.add("edit");
                button.classList.add("btn-outline-primary");
            } else if (j == arr.length - 1) {
                var button = document.createElement('button')
                td.appendChild(button);
                button.innerHTML = "del";
                button.classList.add("btn");
                button.classList.add("del");
                button.classList.add("btn-outline-primary");
            } else {
                td.innerHTML = createСolumn(shops, arr[j], arrrows[i]);
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    parent.appendChild(table);
    var arr1 = [];
    $(document).find('.edit').on('click', function() {
        $('#pdistributionModel').val($(this).parents('tr:first').find('td:eq(0)').text());
        for (var i = 1; i < arr.length - 2; i++) {
            arr1.push(arr[i]);
            arr1.push($(this).parents('tr:first').find('td:eq( ' + i + ' )').text());
        }
        modal(arr1);
        arr1 = [];
        arr1.length = 0;
    });
    $(document).find('.del').on('click', function() {
        var age = $(this).parents('tr:first').find('td:eq(0)').text();
        const requestURL = '/matrixT2Del/' + age.replaceAll("/", "_")

        createTables(requestURL);
    });
}

function createСolumn(shops, arr, arrrows) {
    var a = null;
    for (var i = 0; i < shops.length; i++) {
        if (shops[i].distributionModel == arrrows && shops[i].cluster == arr) {
            a = shops[i].quantity;
        }
    }
    return a;
}

function createArr(shops) {
    var a = ["МОДЕЛЬ РАСПРЕДЕЛЕНИЯ"];
    for (var i = 0; i < shops.length; i++) {
        a.push(shops[i].cluster);
    }
    var b = uniqueArray3(a);
    b.push(null, null);
    return b;
}

function createArrRows(shops) {
    var a = [];
    for (var i = 0; i < shops.length; i++) {
        a.push(shops[i].distributionModel);
    }
    uniqueArray3(a)
    return uniqueArray3(a);
}

function uniqueArray3(a) {
    function onlyUnique(value, index, self) {
        return self.indexOf(value) === index;
    }
    var unique = a.filter(onlyUnique);
    return unique;
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