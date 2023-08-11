var a,b,c;
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
                var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                $.get('/remainsGroupShopGlass/' + models, {}, function(data) {
                    $(".remainsGroupShopGlass").html(data);
                    tableRemainsGroupShopGlassAll(models)
                });
                tableCashGlass(models)
                if (a != undefined) {
                    a.parents().nextAll('.hide_minMatrix').toggle();
                }
                a= $(this);
               a.parents().nextAll('.hide_minMatrix').toggle();
            });
            $("#TT_Glass").html(shop);
            changeOrderGlass(shop);
        });
    });
}

function tableRemainsGroupShopCaseAll(models) {
    $(document).find('.remainsGroupShopCase .btn').on('click', function() {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCaseAll/' + models + '/' + shop, {}, function(data) {
            $(".remainsGroupShopCaseAll").html(data);

            $(document).find('.minMatrix').on('click', function() {
                var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();

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
            changeOrderCase(shop);
        });
    });
}
function tableRemainsGroupShopCoverBookAll(models) {
    $(document).find('.remainsGroupShopCoverBook .btn').on('click', function() {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCoverBookAll/' + models + '/' + shop, {}, function(data) {
            $(".remainsGroupShopCoverBookAll").html(data);

            $(document).find('.minMatrix').on('click', function() {
                var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();

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
            changeOrderCoverBook(shop);
        });
    });
}

function changeOrderGlass(shop) {
    $(document).find('.changeGlass').on('change', function() {
        var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/updatingAllTables/' + shop + '/' + nomenclature.replaceAll('/', '_') + '/' + this.value, {}, function(data) {
            console.log(data)
        });
    });
}
function changeOrderCase(shop) {
    $(document).find('.changeCase').on('change', function() {
        var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/updatingAllTables/' + shop + '/' + nomenclature.replaceAll('/', '_') + '/' + this.value, {}, function(data) {
            console.log(data)
        });
    });
}
function changeOrderCoverBook(shop) {
    $(document).find('.changeCoverBook').on('change', function() {
        var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/updatingAllTables/' + shop + '/' + nomenclature.replaceAll('/', '_') + '/' + this.value, {}, function(data) {
            console.log(data)
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