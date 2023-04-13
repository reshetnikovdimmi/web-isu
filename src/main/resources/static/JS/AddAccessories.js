$(document).ready(function() {
    $('#save').on('click', function(event) {
        var tele2;

        if ($('#Tele2').is(':checked')) {
            tele2 = true;
        } else {
            tele2 = false;
        }

        $.get('/AddAccessoriesCategory/' + $('#accessoriesName').val() + '/' + $('#accessoriesCategory').val() + "/" + tele2, {}, function(data) {
            $(".AccessoriesCategory").html(data);

        });
    });
});