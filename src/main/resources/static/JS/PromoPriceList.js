$(document).ready(function() {

  $('#dropDownListPhone').on('change', function() {
        $.get('/dropDownListBrendPromo/' + $('#dropDownListPhone').val().trim(), {}, function(data) {
            $(".dropDownListBrend").html(data);
        });
    });


});