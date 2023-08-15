var a, b, c;
$(document).ready(function() {
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    $(document).find('.table_Glass .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopGlass/' + models, {}, function(data) {
            $(".remainsGroupShopGlass").html(data);
            tableRemainsGroupShopGlassAll(models)
        });
        tableCashGlass(models)
    });
    $(document).find('.table_Case .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCase/' + models, {}, function(data) {
            $(".remainsGroupShopCase").html(data);
            tableRemainsGroupShopCaseAll(models)
        });
        tableCashCase(models)
    });
    $(document).find('.table_CoverBook .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCoverBook/' + models, {}, function(data) {
            $(".remainsGroupShopCoverBook").html(data);
            tableRemainsGroupShopCoverBookAll(models)
        });
        tableCashCoverBook(models)
    });
});

function tableRemainsGroupShopGlassAll(models) {
    $(document).find('.remainsGroupShopGlass .btn').on('click', function() {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopGlassAll/' + models + '/' + shop, {}, function(data) {
            $(".remainsGroupShopGlassAll").html(data);
            $(document).find('.minMatrix').on('click', function() {
                models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                $.get('/remainsGroupShopGlass/' + models, {}, function(data) {
                    $(".remainsGroupShopGlass").html(data);
                    tableRemainsGroupShopGlassAll(models)
                });
                tableCashGlass(models)
                if (a != undefined) {
                    a.parents().nextAll('.hide_minMatrix').toggle();
                }
                a = $(this);
                a.parents().nextAll('.hide_minMatrix').toggle();
            });
            $("#TT_Glass").html(shop);
            changeOrderGlass(shop, models);
        });
    });
}

function tableRemainsGroupShopCaseAll(models) {
    $(document).find('.remainsGroupShopCase .btn').on('click', function() {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCaseAll/' + models + '/' + shop, {}, function(data) {
            $(".remainsGroupShopCaseAll").html(data);
            $(document).find('.minMatrix').on('click', function() {
                models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                $.get('/remainsGroupShopCase/' + models, {}, function(data) {
                    $(".remainsGroupShopCase").html(data);
                    tableRemainsGroupShopCaseAll(models)
                });
                tableCashCase(models)
                if (b != undefined) {
                    b.parents().nextAll('.hide_minMatrix').toggle();
                }
                b = $(this);
                b.parents().nextAll('.hide_minMatrix').toggle();
            });
            $("#TT_Case").html(shop);
            changeOrderCase(shop, models);
        });
    });
}

function tableRemainsGroupShopCoverBookAll(models) {
    $(document).find('.remainsGroupShopCoverBook .btn').on('click', function() {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCoverBookAll/' + models + '/' + shop, {}, function(data) {
            $(".remainsGroupShopCoverBookAll").html(data);
            $(document).find('.minMatrix').on('click', function() {
                models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                $.get('/remainsGroupShopCoverBook/' + models, {}, function(data) {
                    $(".remainsGroupShopCoverBook").html(data);
                    tableRemainsGroupShopCoverBookAll(models)
                });
                tableCashCoverBook(models)
                if (c != undefined) {
                    c.parents().nextAll('.hide_minMatrix').toggle();
                }
                c = $(this);
                c.parents().nextAll('.hide_minMatrix').toggle();
            });
            $("#TT_CoverBook").html(shop);
            changeOrderCoverBook(shop, models);
        });
    });
}

function changeOrderGlass(shop, models) {
    $(document).find('.changeGlass').on('change', function() {
        var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/updatingAllTables/' + shop + '/' + $("#Model_Glass").text() + '/' + nomenclature.replaceAll('/', '_') + '/' + this.value, {}, function(data) {
            console.log(data)
        });
    });
}

function changeOrderCase(shop, models) {
    $(document).find('.changeCase').on('change', function() {
        var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/updatingAllTables/' + shop + '/' + $("#Model_Case").text() + '/' + nomenclature.replaceAll('/', '_') + '/' + this.value, {}, function(data) {
            console.log(data)
        });
    });
}

function changeOrderCoverBook(shop, models) {
    $(document).find('.changeCoverBook').on('change', function() {
        var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/updatingAllTables/' + shop + '/' + $("#Model_CoverBook").text() + '/' + nomenclature.replaceAll('/', '_') + '/' + this.value, {}, function(data) {
            var tds = document.querySelectorAll('.remainsGroupShopCoverBook td');
            for (var i = 0; i < tds.length; i++) {
                if (tds[i].lastElementChild != null && data.shop == tds[i].lastElementChild.innerHTML) {
                    tds[i + 1].innerHTML = data.remainsShop
                }
            }
            var group = document.querySelectorAll('.remainsGroupShopCoverBookAll td');
            for (var i = 0; i < group.length; i++) {
                if (group[i].lastElementChild != null && data.group == group[i].lastElementChild.innerHTML) {
                    group[i + 5].innerHTML = data.order
                    group[i + 6].innerHTML = data.remainsCash1
                    group[i + 7].innerHTML = data.remainsCash2
                }
           for (var j = 0; j < data.all.length; j++) {
                if (data.all[j].nomenclature == group[i].innerHTML) {
                        console.log(group[i].innerHTML)

                    }
                }
            }

        });
    });
}

function tableCashGlass(models) {
    $.get('/remainsCashGlass/' + models, {}, function(data) {
        $(".remainsCashGlass").html(data);
        $("#Model_Glass").html(models);
    });
}

function tableCashCase(models) {
    $.get('/remainsCashCase/' + models, {}, function(data) {
        $(".remainsCashCase").html(data);
        $("#Model_Case").html(models);
    });
}

function tableCashCoverBook(models) {
    $.get('/remainsCashCoverBook/' + models, {}, function(data) {
        $(".remainsCashCoverBook").html(data);
        $("#Model_CoverBook").html(models);
    });
}