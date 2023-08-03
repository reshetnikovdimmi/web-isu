$(document).ready(function() {
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    $(document).find('.table_Glass .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShop/' + models, {}, function(data) {
            $(".remainsGroupShopGlass").html(data);
            tableRemainsGroupShopGlassAll(models)
        });
        tableCashGlass(models)
    });
    $(document).find('.table_Case .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCase/' + models, {}, function(data) {
            $(".remainsGroupShopCase").html(data);
        });
        tableCashCase(models)
    });
    $(document).find('.table_CoverBook .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCoverBook/' + models, {}, function(data) {
            $(".remainsGroupShopCoverBook").html(data);
        });
        tableCashCoverBook(models)
    });
});

function tableRemainsGroupShopGlassAll(models) {
    $(document).find('.remainsGroupShopGlass .btn').on('click', function() {
        var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopGlassAll/' + models + '/' + shop, {}, function(data) {
            $(".remainsGroupShopGlassAll").html(data);
             var d;
                $(document).find('.minMatrix').on('click', function() {
                    if (d != undefined) {
                        d.parents().nextAll('.hide_minMatrix').toggle();
                    }
                    d = $(this);
                    d.parents().nextAll('.hide_minMatrix').toggle();
                });

             changeOrder(shop);
        });
    });
}
function changeOrder(shop) {
$(document).find('.change').on('change', function() {
var nomenclature = $(this).parents('tr:first').find('td:eq(0)').text();

      $.get('/updatingAllTables/' + shop + '/' + nomenclature + '/' + this.value, {}, function(data) {

console.log(data)
              });
    });
}


function tableCashGlass(models) {
    $.get('/remainsCashGlass/' + models, {}, function(data) {
        $(".remainsCashGlass").html(data);
    });
}

function tableCashCase(models) {
    $.get('/remainsCashCase/' + models, {}, function(data) {
        $(".remainsCashCase").html(data);
    });
}

function tableCashCoverBook(models) {
    $.get('/remainsCashCoverBook/' + models, {}, function(data) {
        $(".remainsCashCoverBook").html(data);
    });
}