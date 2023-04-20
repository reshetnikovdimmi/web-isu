$(document).ready(function() {
    $(document).find('.btn').on('click', function() {
        $.get('/AccessoriesCategoryShop/' + $(this).parents('tr:first').find('td:eq(0)').text(), {}, function(data) {
            $(".AccessoriesCategoryShop").html(data);
            updateAccessoriesCategoryShop();
        });
    });
});

function updateAccessoriesCategoryShop() {
    $(document).find('.AccessoriesCategoryShop .btn').on('click', function() {
        $.get('/AccessoriesCategoryNomenclatureShop/' + $(this).parents('tr:first').find('td:eq(0)').text(), {}, function(data) {
                        console.log(data)
                  //  $(".AccessoriesCategoryShop").html(data);

                });
    });
}