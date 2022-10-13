$(document).ready(function(){


     $('.table .btn').on('click', function(event){
                     event.preventDefault();

                     var href = $(this).attr("href");

                     $.get(href, function(SimSetup, status){
                        console.log(SimSetup.id);
                            $('#IDupdateMatrixRTK').val(SimSetup.id);
                            $('#Cluster').val(SimSetup.cluster);
                            $('#VnutrLowVideoCam').val(SimSetup.vnutrLowVideoCam);
                            $('#VideoCamVnutrMiddle').val(SimSetup.videoCamVnutrMiddle);
                            $('#VnutrHighVideoCam').val(SimSetup.vnutrHighVideoCam);
                            $('#VneshKupolVideoCam').val(SimSetup.vneshKupolVideoCam);
                            $('#VneshCylindrVideoCam').val(SimSetup.vneshCylindrVideoCam);
                            $('#VneshHighVideoCam').val(SimSetup.vneshHighVideoCam);
                            $('#VneshWiFiVideoCam').val(SimSetup.vneshWiFiVideoCam);
                            $('#IPTVset_topBox').val(SimSetup.IPTVset_topBox);
                            $('#SmartMiniSpeaker').val(SimSetup.smartMiniSpeaker);
                            $('#SmartColumnCapsule').val(SimSetup.smartColumnCapsule);
                            $('#RostelecomRouter').val(SimSetup.rostelecomRouter);
                            $('#PowerInjectorForVideoCam').val(SimSetup.powerInjectorForVideoCam);
                            $('#GameController').val(SimSetup.gameController);

                     });
     });
 });