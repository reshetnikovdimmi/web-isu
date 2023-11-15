var a, b, view, models, fragment, shop;
$(document).ready(function() {
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    $(document).find('.table_Glass .btn').on('click', function() {
        models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        fragment = "RemanSimCash";
        view = "TableDistributionPhone";
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

            distributionTable()
        });
    });
}
function distributionTable() {
    $.get('/TableDistributionClothes/' + shop + '/'+view, {}, function(data) {
        $("."+view).html(data);
        tableRemainsGroupShopGlassAll()
        $("#Shop").html(shop);
      //  scrollInto()
    });
}
function tableRemainsGroupShopGlassAll() {
    $(document).find('.'+ view +' .btn').on('click', function() {
        group = $(this).parents('tr:first').find('td:eq(0)').text().trim();
        $.get('/table_simT2/' + group + '/'+fragment, {}, function(data) {
            $("."+fragment).html(data);
            $("#group").html(group);
            $(document).find('.table_graduation .btn').on('click', function() {
               shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                distributionTable()
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
       order = $(this).parents('tr:first').find('td:eq(4)').text()
        let OrderRecommendations = {
            shop: shop,
            nomenclature: nomenclature,
            group: group,
            order: this.value,
        };
        $('#loader').removeClass('hidden')

        sendRequest('POST', '/DistributionSim', OrderRecommendations).then(data => distribution(data)).catch(err => console.log(err))
    });
}