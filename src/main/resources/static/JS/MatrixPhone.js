$(document).ready(function(){


     $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){
                        console.log(SimSetup.id);
                            $('#IDupdateMatrixPhone').val(SimSetup.id);
                            $('#Matrix_T2').val(SimSetup.matrix_T2);
                            $('#Brend').val(SimSetup.brend);
                            $('#Model').val(SimSetup.model);
                            $('#Model_GB').val(SimSetup.model_GB);
                            $('#Phone').val(SimSetup.phone);

                     });




     });
$(document).find('.DEL').on('click', function() {
                 var models = $(this).parents('tr:first').find('td:eq(0)').text(),
                     data;

                 $.get('/delet_MatrixPhone/' + models, {}, function(data) {
         $(".MatrixPhones").html(data);
                 });
             });
 });
