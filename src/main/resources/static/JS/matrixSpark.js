const requestURL = '/matrixSparkSale'
const requestURLupdate = '/updateSparkSale'
const requestURLsave = '/saveSparkSale'
$(document).ready(function() {
    $('#updateMatrixSpark').on('click', function() {
        $.get(requestURLupdate, function(sparkSalePhone, status) {
            console.log(sparkSalePhone);
            $(".tableMatrix").html(data);
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
     $('.table_t2m .btn').on('click', function(event) {
    var shop = $(this).parents('tr:first').find('td:eq(0)').text();


            $.get('/creatMatrix/' + shop, {}, function(data) {
console.log(data)
                        $(".tableMatrix").html(data);


                    });




    });
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