$(document).ready(function() {
    var tab;
    const requestURL = '/tableGlasShop'
    $(".nav-tabs a").click(function() {
        $(this).tab('show');
    });
    tableGlasShop();
    tableCaseShop();
    tableCoverBookShop();
});

function tableGlasShop() {
    var tds = document.querySelectorAll('.table_Glass .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            $.post('/tableGlasShop', {
                text: this.innerHTML
            }, function(data) {
                $(".GlassShop").html(data);
               tableGlasShops();
            });

        });
    }

}
function tableGlasShops() {
    var tds = document.querySelectorAll('.GlassShop .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
         /*   $.post('/tableGlasShop', {
                text: this.innerHTML
            }, function(data) {
                $(".GlassShop").html(data);
            });*/
alert(this.innerHTML)
        });
    }

}

function tableCaseShop() {
    var tds = document.querySelectorAll('.table_Case .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            $.post('/tableCaseShop', {
                text: this.innerHTML
            }, function(data) {

                $(".CaseShop").html(data);
            });
        });
    }
}

function tableCoverBookShop() {
    var tds = document.querySelectorAll('.table_CoverBook .btn');
    for (var i = 0; i < tds.length; i++) {
        tds[i].addEventListener('click', function func() {
            $.post('/tableCoverBookShop', {
                text: this.innerHTML
            }, function(data) {

                $(".CoverBookShop").html(data);
            });
        });
    }
}