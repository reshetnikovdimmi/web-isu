const requestURLsave = '/saveSlotongMatching'
$(document).ready(function() {
    $('#save').on('click', function() {
        if ($('#pdistributionModel').val() === '') {
            modals();
        } else {
            var bodyArr = [];
            $('input:checkbox:checked').each(function() {
                const body = {
                    viewClothes: "0",
                    phoneClothes: "0",
                    nameClothes: "0"
                }
                body.viewClothes = "Glass";
                body.phoneClothes = $('#pdistributionModel').val();
                body.nameClothes = $(this).parents('tr:first').find('td:eq(0)').text();
                bodyArr.push(body);
                $(this).parents('tr:first').find('td:eq(1)').text();
            });
            sendRequest('POST', requestURLsave, bodyArr).then(data => console.log(data)).catch(err => console.log(err))
            $('#pdistributionModel').val("");

            const checkboxes = document.querySelectorAll('input[name="color"]');
                            checkboxes.forEach((checkbox) => {
                                checkbox.checked = false;
                            });
        }
    });
});

function modals() {
    $('#myModal').modal("show");
    $('.btn-close').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-secondary').on('click', function() {
        $('#myModal').modal('hide');
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