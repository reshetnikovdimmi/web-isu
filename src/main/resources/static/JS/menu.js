
var shop;
$(document).ready(function(){

     $(document).find('.btn').on('click', function() {
           shop = $(this).parents('tr:first').find('td:eq(0)').text().trim().replaceAll('/', '_');

            $.get('/AllShoppingNeeds/' + shop, {}, function(data) {

            $(".AllShoppingNeeds").html(data);
        });


        });

});