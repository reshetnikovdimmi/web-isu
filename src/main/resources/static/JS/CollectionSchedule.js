$(document).ready(function() {
  $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){

                            $('#IDupdateSHOP').val(SimSetup.id);
                            $('#shop').val(SimSetup.shop);
                            $('#monday').val(SimSetup.monday);
                            $('#tuesday').val(SimSetup.tuesday);
                            $('#wednesday').val(SimSetup.wednesday);
                            $('#thursday').val(SimSetup.thursday);
                            $('#friday').val(SimSetup.friday);

                     });




     });
});
