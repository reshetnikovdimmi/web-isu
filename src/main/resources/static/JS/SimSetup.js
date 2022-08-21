$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
    });


     $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){
                        console.log(SimSetup.id);
                            $('#IDupdateSIM').val(SimSetup.id);
                            $('#view').val(SimSetup.view);
                            $('#nameRainbow').val(SimSetup.nameRainbow);
                            $('#nameSpark').val(SimSetup.nameSpark);
                            $('#nameRarus').val(SimSetup.nameRarus);
                            $('#distributionModel').val(SimSetup.distributionModel);
                            $('#toOrder').val(SimSetup.toOrder);
                     });




     });

 });