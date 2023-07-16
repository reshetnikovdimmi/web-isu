$(document).ready(function() {
    $('.table .btn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr("href");
        $.get(href, function(SimSetup, status) {
            $('#IDupdateMarClasif').val(SimSetup.id);
            $('#RainbowNomenclature').val(SimSetup.rainbowNomenclature);
            $('#ManufacturersArticle').val(SimSetup.manufacturersArticle);
            $('#Name').val(SimSetup.name);
        });
    });
    $('#promoCode td').on('click', function(event) {
        let marvelPromo = {
            promoCode: null,
            startPromo: null,
            endPromo: null,
        };
        marvelPromo.promoCode = $(this).parents('tr:first').find('td:eq(0)').text();
        marvelPromo.startPromo = $(this).parents('tr:first').find('td:eq(1)').text();
        marvelPromo.endPromo = $(this).parents('tr:first').find('td:eq(2)').text();
        sendRequest('POST', '/promoCodeDistinct', marvelPromo).then(data => ceateTableSearchBonus(data)).catch(err => console.log(err))
    });
});

function ceateTableSearchBonus(data) {
var l = data[data.length-1].noClassifier;
   console.log(l.length)
if(l.length>0){
modals(l);
}
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
    heading_2.innerHTML = "Нчало";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Конец";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "ИМЕИ";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "Модель";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "Дата продажи";
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels2");
    var sum = 0;
    for (var i = 0; i < data.length-1; i++) {
        table.id = 'tables_TableSearchBonus';
        var tr = document.createElement('tr');
        for (var j = 0; j < 5; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].startDate;
            }
            if (j == 1) {
                td.innerHTML = data[i].endDate;
            }
            if (j == 2) {
                // sum = sum + data[i].compensation;
                td.innerHTML = data[i].imei;
            }
            if (j == 3) {
                td.innerHTML = data[i].models;
            }
            if (j == 4) {
                td.innerHTML = data[i].dataSale;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    //  $("#sum").html("ИТОГО БОНУСОВ"+"  "+ sum +"  "+"РУБ");
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function sendRequest(method, url, marvelPromo = null) {
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
        xhr.send(JSON.stringify(marvelPromo))
    })
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