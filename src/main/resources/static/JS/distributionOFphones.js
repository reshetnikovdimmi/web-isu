
$(document).ready(function() {
     $(document).find('.tableRemainsCash .btn').on('click', function() {
          var  group = $(this).parents('tr:first').find('td:eq(0)').text().trim();
           $.get('/RemanisPhoneSach/' + group, {}, function(data) {
                 $(".RemanisPhoneSach").html(data);
                 });
        });
     $(document).find('.RemanisPhoneShopT2 .btn').on('click', function() {
               var  shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
             distributionTable(shop)
             });
     $(document).find('.RemanisPhoneShopMult .btn').on('click', function() {
                    var  shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
                  distributionTable(shop)
                  });
});

function distributionTable(shop) {

        $.get('/TableDistributionPhone/' + shop, {}, function(data) {
            $(".TableDistributionPhone").html(data);

        });

}