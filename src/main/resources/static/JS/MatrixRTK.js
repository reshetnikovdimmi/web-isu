var a, group, shop, nomenclature;
var btn;

$(document).ready(function() {

    $(document).find('.table_RTK .btn').on('click', function() {
        group = $(this).parents('tr:first').find('td:eq(0)').text().trim();

        $.get('/RemanisCashRTK/' + group, {}, function(data) {

            $(".RemanRTKCash").html(data);
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
    $.get('/TableDistributionRTK/' + shop, {}, function(data) {
        $(".TableDistributionPhone").html(data);
        tableRemainsGroupShopGlassAll()
        $("#Shop").html(shop);
        scrollInto()
    });
}

function tableRemainsGroupShopGlassAll() {
    $(document).find('.TableDistributionPhone .btn').on('click', function() {
        group = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/RemanisCashRTK/' + group, {}, function(data) {
            $(".RemanRTKCash").html(data);
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
    });
    $(document).find('.form-control').on('change', function() {
        nomenclature = $(this).parents('tr:first').find('td:eq(0)').text()
        var order = $(this).parents('tr:first').find('td:eq(4)').text()
        let OrderRecommendations = {
            shop: shop,
            nomenclature: nomenclature,
            group: group,
            order: this.value,
        };
        $('#loader').removeClass('hidden')
        sendRequest('POST', '/DistributionRTK', OrderRecommendations).then(data => distribution(data)).catch(err => console.log(err))
    });
}

function distribution(data) {

    var tds = document.querySelectorAll('.tableRemainsCash td');
    for (var i = 0; i < tds.length; i++) {
        for (var j = 0; j < data.indicatorPhoneShop.length; j++) {
            if (tds[i].lastElementChild != null && tds[i].lastElementChild.innerHTML == data.indicatorPhoneShop[j].group) {
                tds[i + 1].innerHTML = data.indicatorPhoneShop[j].remainsCash1 == 0 ? null : data.indicatorPhoneShop[j].remainsCash1;
                tds[i + 2].innerHTML = data.indicatorPhoneShop[j].remainsCash2 == 0 ? null : data.indicatorPhoneShop[j].remainsCash2;
            }
        }
    }
    var tds = document.querySelectorAll('.RemanisPhoneShopT2 td');
    for (var i = 0; i < tds.length; i++) {
        for (var j = 0; j < data.remanisPhoneShopT2.length; j++) {
            if (tds[i].lastElementChild != null && tds[i].lastElementChild.innerHTML == data.remanisPhoneShopT2[j].shop) {
                tds[i + 1].innerHTML = data.remanisPhoneShopT2[j].remainsShop == 0 ? null : data.remanisPhoneShopT2[j].remainsShop;
            }
        }
    }
    var tds = document.querySelectorAll('.RemanisPhoneShopMult td');
    for (var i = 0; i < tds.length; i++) {
        for (var j = 0; j < data.remanisPhoneShopMult.length; j++) {
            if (tds[i].lastElementChild != null && tds[i].lastElementChild.innerHTML == data.remanisPhoneShopMult[j].shop) {
                tds[i + 1].innerHTML = data.remanisPhoneShopMult[j].remainsShop == 0 ? null : data.remanisPhoneShopMult[j].remainsShop;
            }
        }
    }
    var tds = document.querySelectorAll('.RemanisPhoneSach td');
    for (var i = 0; i < tds.length; i++) {
        for (var j = 0; j < data.indicatorPhoneSach.length; j++) {
            if (tds[i].lastElementChild != null && tds[i].lastElementChild.innerHTML == data.indicatorPhoneSach[j].nomenclature) {
                tds[i + 1].innerHTML = data.indicatorPhoneSach[j].remainsCash1 == 0 ? null : data.indicatorPhoneSach[j].remainsCash1;
                tds[i + 2].innerHTML = data.indicatorPhoneSach[j].remainsCash2 == 0 ? null : data.indicatorPhoneSach[j].remainsCash2;
            }
        }
    }
    var tds = document.querySelectorAll('.RemainsShopGroup td');
    for (var i = 0; i < tds.length; i++) {
        for (var j = 0; j < data.remainsGroupShop.length; j++) {
            if (tds[i].lastElementChild != null && tds[i].lastElementChild.innerHTML == data.remainsGroupShop[j].shop && data.remainsGroupShop[j].group == group) {
                tds[i + 1].innerHTML = data.remainsGroupShop[j].remainsShop == 0 ? null : data.remainsGroupShop[j].remainsShop;
            }
        }
    }
    var tds = document.querySelectorAll('.TableDistributionPhone td');
    var rem, rem1;
    for (var i = 0; i < tds.length; i++) {
        for (var j = 0; j < data.distributionPhone.length; j++) {
            if (tds[i].lastElementChild != null && tds[i].lastElementChild.innerHTML == data.distributionPhone[j].group && data.distributionPhone[j].shop == shop) {
                tds[i + 4].innerHTML = data.distributionPhone[j].order == 0 ? null : data.distributionPhone[j].order;
                tds[i + 5].innerHTML = data.distributionPhone[j].remainsCash1 == 0 ? null : data.distributionPhone[j].remainsCash1;
                tds[i + 6].innerHTML = data.distributionPhone[j].remainsCash2 == 0 ? null : data.distributionPhone[j].remainsCash2;
                for (var c = 0; c < data.distributionPhone[j].all.length; c++) {
                    if (data.distributionPhone[j].all[c].nomenclature == nomenclature) {
                        rem = data.distributionPhone[j].all[c].remainsCash1;
                        rem1 = data.distributionPhone[j].all[c].remainsCash2;
                    }
                }
            }
        }
        if (tds[i].innerHTML == nomenclature) {
            tds[i + 5].innerHTML = rem == 0 ? null : rem;
            tds[i + 6].innerHTML = rem1 == 0 ? null : rem1;
        }

    }
 /* $.get('/UpDateMatrix', {}, function(data) {
            $(".UpDateMatrix").html(data);

        });*/

$('#loader').addClass('hidden')
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