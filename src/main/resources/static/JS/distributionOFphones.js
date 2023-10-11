var a, group, shop;
var btn;
$(document).ready(function() {
    $(document).find('.tableRemainsCash .btn').on('click', function() {
        group = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/RemanisPhoneSach/' + group, {}, function(data) {
            $(".RemanisPhoneSach").html(data);
            $("#group").html(group);
            scrollInto()
            $(document).find('.RemainsShopGroup .btn').on('click', function() {
                var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                distributionTable(shop)
            });
        });
    });
    $(document).find('.RemanisPhoneShopT2 .btn').on('click', function() {
        shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        distributionTable(shop)
    });
    $(document).find('.RemanisPhoneShopMult .btn').on('click', function() {
        shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        distributionTable(shop)
    });
});

function scrollInto() {
    var tds = document.querySelectorAll('.minMatrix');
    for (var i = 0; i < tds.length; i++) {
        if (tds[i].innerHTML == group) {
            tds[i].scrollIntoView(true);
            tds[i].click();
        }
    }
}

function distributionTable(shop) {
    $.get('/TableDistributionPhone/' + shop, {}, function(data) {
        $(".TableDistributionPhone").html(data);
        tableRemainsGroupShopGlassAll()
        $("#Shop").html(shop);
        scrollInto()
    });
}

function tableRemainsGroupShopGlassAll() {
    $(document).find('.TableDistributionPhone .btn').on('click', function() {
        group = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/RemanisPhoneSach/' + group, {}, function(data) {
            $(".RemanisPhoneSach").html(data);
            $("#group").html(group);
            $(document).find('.RemainsShopGroup .btn').on('click', function() {
                var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                distributionTable(shop)
            });
        });
    });
    $(document).find('.minMatrix').on('click', function() {
        if (a != undefined) {
            a.parents().nextAll('.hide_minMatrix').toggle();
        }
        a = $(this);
        a.parents().nextAll('.hide_minMatrix').toggle();
        $(document).find('.form-control').on('change', function() {
            var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text()
            var order = $(this).parents('tr:first').find('td:eq(4)').text()

                let OrderRecommendations = {
                        shop: shop,
                        nomenclature: nomenclature,
                        group: group,
                        order: this.value,

                    };

                    sendRequest('POST', '/Distribution', OrderRecommendations).then(data => console.log(data)).catch(err => console.log(err))
        });
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