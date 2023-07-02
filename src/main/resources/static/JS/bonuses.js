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
            bonuses.models = $('#dropDownListModelGB').val().trim();
        }
        if ($('#dropDownListPhone').val() != 'null') {
            bonuses.shop = $('#dropDownListPhone').val().trim();
        }
        if ($('#dropDownListProvider').val() != 'null') {
            bonuses.provider = $('#dropDownListProvider').val().trim();
        }
        if ($('#dropDownListPhone').val() != 'null') {
            bonuses.phone = $('#dropDownListPhone').val().trim();
        }
        if ($('#startDate').val() != '') {
            bonuses.startDate = $('#startDate').val().trim();
        }

        if ($('#endDate').val() != '') {
            bonuses.endDate = $('#endDate').val().trim();
        }
        sendRequest('POST', '/buttonShowAll', bonuses).then(data => console.log(data)).catch(err => console.log(err))
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