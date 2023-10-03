
$(document).ready(function() {
     $(document).find('.tableRemainsCash .btn').on('click', function() {
          var  group = $(this).parents('tr:first').find('td:eq(0)').text().trim();
           $.get('/RemanisPhoneSach/' + group, {}, function(data) {


                     $(".RemanRTKCash").html(data);

                 });
        });
});
