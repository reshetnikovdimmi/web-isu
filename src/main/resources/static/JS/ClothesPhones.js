$(document).ready(function() {
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    $(document).find('.table_Glass .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShop/' + models, {}, function(data) {
            $(".remainsGroupShopGlass").html(data);
        });
        tableCashGlass(models)
    });
    $(document).find('.table_Case .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCase/' + models, {}, function(data) {
            $(".remainsGroupShopCase").html(data);
        });
    });
    $(document).find('.table_CoverBook .btn').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/remainsGroupShopCoverBook/' + models, {}, function(data) {
            $(".remainsGroupShopCoverBook").html(data);
        });

    });
});
function tableCashGlass(models) {
$.get('/remainsCashGlass/' + models, {}, function(data) {


            $(".remainsCashGlass").html(data);
        });
}