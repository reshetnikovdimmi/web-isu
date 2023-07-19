$(document).ready(function() {
    $('#dropDownListPhone').on('change', function() {
        $.get('/dropDownListBrendPromo/' + $(this).val(), {}, function(data) {
            var data = JSON.parse(data);
            var s = '<option value="' + "select option" + '">' + "select option" + '</option>';
            for (var i = 0; i < data.length; i++) {
                s += '<option value="' + data[i] + '">' + data[i] + '</option>';
            }
            $('#dropDownListModelGB').html(s);
        });
    });
    $('#dropDownListModelGB ').on('change', function() {
        $.get('/dropDownListModels/' + $(this).val(), {}, function(data) {
            var data = JSON.parse(data);
            var s = '<option value="' + "select option" + '">' + "select option" + '</option>';
            for (var i = 0; i < data.length; i++) {
                s += '<option value="' + data[i] + '">' + data[i] + '</option>';
            }
            $("#dropDownListModels").html(s);
        });
    });
});