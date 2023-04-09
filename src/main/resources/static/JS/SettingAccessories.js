$(document).ready(function(){


     $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){
                        console.log(SimSetup.id);
                  //  document.forms['myForm']['ID'].value = SimSetup.id;
                            $('#group').val(SimSetup.group);
                            $('#PriceMin').val(SimSetup.priceMin);
                            $('#PriceMax').val(SimSetup.priceMax);
                            $('#ID').val(SimSetup.id);

                     });




     });

 });