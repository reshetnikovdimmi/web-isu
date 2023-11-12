var a, b, c, models, fragment, shop;
$(document).ready(function() {
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    $(document).find('.table_Glass .btn').on('click', function() {
        models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        fragment = "RemanSimCash";
        updateRemainsCash()
    });
    $(document).find('.table_Case .btn').on('click', function() {
        models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        fragment = "RemanCase";
        updateRemainsCash()
    });
    $(document).find('.table_CoverBook .btn').on('click', function() {
        models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        fragment = "RemanCoverBook";
        updateRemainsCash()
    });
});

function updateRemainsCash() {
    $.get('/remainsGroupShopGlass/' + models + '/'+ fragment, {}, function(data) {
        $("." + fragment).html(data);
        $("#group").html(group);
        // scrollInto()
        $(document).find('.table_graduation .btn').on('click', function() {
            shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
            alert(shop)
                //    distributionTable()
        });
    });
}