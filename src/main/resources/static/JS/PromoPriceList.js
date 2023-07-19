$(document).ready(function() {

     $('#dropDownListPhone').on('change', function() {
          var brend = $(this).val();
          $.ajax({
              url: "/dropDownListBrendPromo/" + brend,
              type: "GET",
              success: function(data) {
                  var data = JSON.parse(data);
                  var s = '';
                  s += '<option value="' + "select option" + '">' + "select option" + '</option>';
                  for (var i = 0; i < data.length; i++) {

                      s += '<option value="' + data[i] + '">' + data[i] + '</option>';
                  }
                  $('#dropDownListModelGB').html(s);
              }
          });
          return false;
      });
$('#dropDownListModelGB ').on('change', function() {
          $.get('/dropDownListModels/' + $('#dropDownListModelGB').val().trim(), {}, function(data) {
               $(".dropDownListModels").html(data);
           });
        });
});