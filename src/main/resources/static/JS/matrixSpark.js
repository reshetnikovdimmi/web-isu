const requestURL = '/matrixSparkSale'
const requestURLupdate = '/updateSparkSale'
const requestURLsave = '/saveSparkSale'
$(document).ready(function() {
    $('#updateMatrixSpark').on('click', function() {
        if ($('#Shop_1').text() == 'МАГАЗИН') {
            modals("ВЫБЕРИ МАГАЗИН");
        } else {
            $.get(requestURLupdate + '/' + $('#Shop_1').text(), function(sparkSalePhone, status) {
                $(".tableMatrix").html(sparkSalePhone);
            });
        }
    });
    $('#saveMatrixSpark').on('click', function() {
        alert("ok")
        var bodyArr = [];
        var tds = document.querySelectorAll('#table_t2 td');
        for (var i = 5; i < tds.length; i += 6) {
            console.log(tds[i].children[0].value + "--" + tds[i - 1].innerHTML)
            const body = {
                matrix: null,
                sale1: null,
                sale6: null,
                saleAll: null,
                group: null,
                shop: null
            }
            body.matrix = tds[i].children[0].value;
            body.sale1 = tds[i - 1].innerHTML;
            body.sale6 = tds[i - 2].innerHTML;
            body.saleAll = tds[i - 3].innerHTML;
            body.group = tds[i - 4].innerHTML;
            body.shop = $('#Shop_1').text();
            bodyArr.push(body);
        }
        sendRequest('POST', requestURLsave + '/' + $('#Shop_1').text() , bodyArr).then(data => console.log(data)).catch(err => console.log(err))
    });
    $('.table_t2m .btn').on('click', function(event) {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/creatMatrix/' + shop, {}, function(data) {
            $('#Shop_1').text(shop);

            $(".tableMatrix").html(data);
            total()
        });
    });
});
function total() {
var tds = document.querySelectorAll('#table_t2 td');
var cou = 0;

for (var i = 5; i < tds.length; i += 6) {
console.log(tds[i].children[0].value + "--" + tds[i - 1].innerHTML)
cou= cou+Number(tds[i].children[0].value)
}
$('#recommendedValue').text(cou);
}

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