const requestURLsave = '/saveSlotongMatching'
var arr = [];
var check;
$(document).ready(function() {
    $.getJSON('/slotongMatchingTable', function(data) {
        //console.log(data)
        clothingTable(data)
            //  alert(data.error);
    });
    $('#save').on('click', function() {
        if ($('#pdistributionModel').val() === '') {
            modals("Что то забыл");
        } else if (arr.length > 0) {
            sendRequest('POST', '/slotongMatchingTableDel', arr).then(data => save()).catch(err => console.log(err))
            arr = new Array();
        } else {
            save();
        }
    });
    is_checked();


});

function is_checked() {
    $('#group input:checkbox').click(function() {
        check = $(this).val();
        if ($(this).is(':checked')) {
            $('#group input:checkbox').not(this).prop('checked', false);
            $('#save').attr('disabled', false);
        } else {
            $('#save').attr('disabled', true);
        }
    });
}

function save() {
    var bodyArr = [];
    $('#tables_remanisWarehousePhone input:checkbox:checked').each(function() {
        const body = {
            viewClothes: "0",
            phoneClothes: "0",
            nameClothes: "0"
        }
        body.viewClothes = check;
        body.phoneClothes = $('#pdistributionModel').val();
        body.nameClothes = $(this).parents('tr:first').find('td:eq(0)').text();
        bodyArr.push(body);
        $(this).parents('tr:first').find('td:eq(1)').text();
    });
    sendRequest('POST', requestURLsave, bodyArr).then(data => clothingTable(data)).catch(err => console.log(err))
    $('#pdistributionModel').val("");
    const checkboxes = document.querySelectorAll('input[name="color"]');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = false;
    });
    $('#save').attr('disabled', true);
}

function clothingTable(data) {
    //console.log(data)
    var elem = document.querySelector('#table_distributionPhone');
    var elem1 = document.querySelector('#tables_distributionPhone');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_distributionPhone';
    table.classList.add("tables_distributionPhone");
    table.classList.add("table-borderless-1");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_0 = document.createElement('th');
    heading_0.innerHTML = "Вид";
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Модель тедефона";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Модель стекла";
    row_1.appendChild(heading_0);
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < data.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 5; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].viewClothes;
            }
            if (j == 1) {
                td.innerHTML = data[i].nameClothes;
            }
            if (j == 2) {
                td.innerHTML = data[i].phoneClothes;
            }
            if (j == 3) {
                var button = document.createElement('button')
                button.type = 'button';
                td.appendChild(button);
                button.classList.add("button");
                button.classList.add("UPDATE");
                button.id = 'button';
                button.innerHTML = "update";
            }
            if (j == 4) {
                var button = document.createElement('button')
                button.type = 'button';
                td.appendChild(button);
                button.classList.add("button");
                button.classList.add("DEL");
                button.id = 'button';
                button.innerHTML = "DEL";
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.UPDATE').on('click', function() {
        updateSlotongMatchingTable($(this).parents('tr:first').find('td:eq(2)').text(), data);
    });
    $(document).find('.DEL').on('click', function() {
        for (var j = 0; j < data.length; j++) {
            if ($(this).parents('tr:first').find('td:eq(2)').text() == data[j].phoneClothes) {
                console.log($(this).parents('tr:first').find('td:eq(2)').text() + "--" + data[j].phoneClothes)
                arr.push(data[j].id);
            }
        }
        sendRequest('POST', '/slotongMatchingTableDel', arr).then(data => clothingTable(data)).catch(err => console.log(err))
        arr = new Array();
    });
}

function updateSlotongMatchingTable(glassModel, data) {
    $('#pdistributionModel').val(glassModel);
    $('ya-tr-span').each(function() {
        var span = $(this);
        span.after(span.text()).remove();
    });
    var tds1 = document.querySelectorAll('table.table-borderless td');
    for (var j = 0; j < data.length; j++) {
        for (var i = 1; i < tds1.length; i += 2) {
            if (data[j].nameClothes == tds1[i - 1].innerHTML && glassModel == data[j].phoneClothes) {
                arr.push(data[j].id);
                tds1[i].innerHTML = '<input type="checkbox" name="color" checked="checked" />';
            }
        }
    }
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

function tableSearch() {
    var phrase = document.getElementById('search-text');
    var table = document.getElementById('tables_distributionPhone');
    var regPhrase = new RegExp(phrase.value, 'i');
    var flag = false;
    for (var i = 1; i < table.rows.length; i++) {
        flag = false;
        for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
            flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
            if (flag) break;
        }
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }
    }

}