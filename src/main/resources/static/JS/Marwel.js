$(document).ready(function(){


     $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){

                            $('#IDupdateMarClasif').val(SimSetup.id);
                            $('#RainbowNomenclature').val(SimSetup.rainbowNomenclature);
                            $('#ManufacturersArticle').val(SimSetup.manufacturersArticle);
                            $('#Name').val(SimSetup.name);

                     });




     });

 });