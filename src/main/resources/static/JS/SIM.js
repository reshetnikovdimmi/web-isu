$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
    });


     $('.table_t2 .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");


                     $.get(href, function(SIM, status){
                        console.log(SIM.shop);
document.querySelector('#Shop').innerHTML = href.substr(-7, 5).charCodeAt();

                     });




     });
      $('.table_t2m .btn').on('click', function(event){
                          event.preventDefault();

                          var href = $(this).attr("href");


                          $.get(href, function(SIM, status){
                             var data = JSON.parse(SIM);
                             console.log(data.shop);

     document.querySelector('#Shop').innerHTML = data[1].shop;

                          });




          });

 });