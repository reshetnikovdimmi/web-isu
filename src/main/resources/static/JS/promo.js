$(document).ready(function(){
  $('#brend').on('change', function(){
     var brend = $(this).val();
       $.ajax({

       	url: "/brend/" + brend,
		type: "GET",

	success: function(data) {

    var data = JSON.parse(data);
    			var s = '';
				for(var i = 0; i < data.length; i++) {
					s += '<option value="' + data[i].one + '">' + data[i].one + '</option>';
					}
				$('#models').html(s);

				}
	});
   return false;
  });


$('#search_models').chosen({
		width: '100%',
		no_results_text: 'Совпадений не найдено',
		placeholder_text_single: 'Выберите город'

	});


     $('#models').on('change', function(){
         var mode = $(this).val();

s = mode.replace("/", "_");
console.log(s);
                $.ajax({

                        type: "GET",
                        url: "/mode/" + s,

	                        success: function(data) {
	                        console.log(data);
                            $('#price').val(data);
                        }

                });
        });
 $('.table .btn').on('click', function(event){
                 event.preventDefault();

                 var href = $(this).attr("href");

                 $.get(href, function(promo, status){
                    console.log(promo.id);
                    $('#IDupdate').val(promo.id);
                    $('#brend').val(promo.brend);
                    $('#models').html('<option value="' + promo.models + '">' + promo.models + '</option>');
                    $('#price').val(promo.price);
                    $('#Promo_price').val(promo.price_promo);
                    $('#start_date').val(promo.startPromo);
                    $('#end_date').val(promo.endPromo);
                    $('#Marwel').val(promo.marwel);
                    $('#TFN').val(promo.tfn);
                    $('#ВВП').val(promo.vvp);
                    $('#Merlion').val(promo.merlion);
                 });



            });
 });



