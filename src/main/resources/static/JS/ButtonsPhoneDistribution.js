$(document).ready(function() {

var tds = document.querySelectorAll('.table_graduation .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            var brend = this.innerHTML;
            $.post('/tableShopRemanisSele', {
                           text: brend
                       }, function(data) {
                          // $(".GlassShop").html(data);
                         //  tableGlasShops(brend);
                          console.log(data)
                       });
        });
    }
 });