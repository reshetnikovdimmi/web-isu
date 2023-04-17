$(document).ready(function() {
  $(document).find('.btn').on('click', function() {

            $.get('/AccessoriesCategoryShop/' + $(this).parents('tr:first').find('td:eq(0)').text(), {}, function(data) {
                $(".AccessoriesCategoryShop").html(data);

            });
    });

});