$(document).ready(function(){


     $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){
                        console.log(SimSetup.id);
                  //  document.forms['myForm']['ID'].value = SimSetup.id;
                            $('#Brend').val(SimSetup.brend);
                            $('#Model').val(SimSetup.model);
                            $('#ID').val(SimSetup.id);

                     });




     });

 });