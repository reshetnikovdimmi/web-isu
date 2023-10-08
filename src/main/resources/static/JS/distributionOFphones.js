var a, b, c;
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
tableRemainsGroupShopGlassAll()
        });

}
function tableRemainsGroupShopGlassAll() {
    $(document).find('.TableDistributionPhone .btn').on('click', function() {
       // var shop = $(this).parents('tr:first').find('td:eq(0)').text().trim();
      //  $.get('/remainsGroupShopGlassAll/' + models + '/' + shop, {}, function(data) {
           // $(".remainsGroupShopGlassAll").html(data);
            $(document).find('.minMatrix').on('click', function() {
              //  models = $(this).parents('tr:first').find('td:eq(0)').text().trim();
               // $.get('/remainsGroupShopGlass/' + models, {}, function(data) {
               //     $(".remainsGroupShopGlass").html(data);
                 //   tableRemainsGroupShopGlassAll(models)
               // });
               // tableCashGlass(models)
                if (a != undefined) {
                    a.parents().nextAll('.hide_minMatrix').toggle();
                }
                a = $(this);
                a.parents().nextAll('.hide_minMatrix').toggle();

            });
           // $("#TT_Glass").html(shop);
           // changeOrderGlass(shop, models);
      //  });
    });
}