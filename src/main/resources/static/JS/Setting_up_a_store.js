$(document).ready(function(){


     $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){
                        console.log(SimSetup.id);
                            $('#IDupdateSHOP').val(SimSetup.id);
                            $('#LOGIN').val(SimSetup.login);
                            $('#PASWORD').val(SimSetup.pasword);
                            $('#NAMESPARK').val(SimSetup.shopIskra);
                            $('#NAMERARUS').val(SimSetup.shopRarus);
                            $('#NAMERAINBOW').val(SimSetup.name);
                            $('#CLUSTERT2').val(SimSetup.clusterT2);
                            $('#CLUSTERRTK').val(SimSetup.clusterRtk);
                            $('#SIMT2').val(SimSetup.simT2);
                            $('#CLUSTERMTS').val(SimSetup.simMts);
                            $('#SIMBEE').val(SimSetup.simBee);
                            $('#SIMMF').val(SimSetup.simMf);
                     });




     });

