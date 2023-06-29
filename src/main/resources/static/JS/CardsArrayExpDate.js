$(document).ready(function() {
$(document).find('#imei').on('change', function() {

 $.get('/imeiDistribution/' + $('#imei').val().trim(), {}, function(data) {

 $('#distribution').val(data);
        });

    });

});

