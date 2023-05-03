var id = 0;
$(document).ready(function() {
    var tele2;
    $('#save').on('click', function(event) {
        if ($('#Tele2').is(':checked')) {
            tele2 = true;
        } else {
            tele2 = false;
        }
        $.get('/AddAccessoriesCategory/' + id + '/' + $('#accessoriesName').val().replaceAll('/', '_') + '/' + $('#accessoriesCategory').val() + "/" + tele2, {}, function(data) {
            $(".AccessoriesCategory").html(data);
            delet();
            update();
            id = 0;
        });
    });
    delet();
    update();
});

function delet() {
    $(document).find('.delet').on('click', function() {
        var id = $(this).parents('tr:first').find('td:eq(0)').text();
        $.get('/DelAccessoriesCategory/' + id, {}, function(data) {
            $(".AccessoriesCategory").html(data);
            delet();
            update();
        });
    });
}

function update() {
    $(document).find('.btn').on('click', function() {
        id = $(this).parents('tr:first').find('td:eq(0)').text();
        $('#accessoriesName').val($(this).parents('tr:first').find('td:eq(1)').text());
        $('#accessoriesCategory').val($(this).parents('tr:first').find('td:eq(2)').text());
        var tele = new Boolean($(this).parents('tr:first').find('td:eq(3)').text());
        if (tele == "true") {
        tele = true;
            $("#Tele2").attr('checked', tele);
        } else {
        tele = false;
            $("#Tele2").attr('checked', tele);
        }
        update();
    });
}