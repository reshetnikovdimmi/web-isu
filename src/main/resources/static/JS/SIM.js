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
                            var sim = SIM;
                            var shop = SIM[1].nameSimAndModem;

                            document.querySelector('#Shop_1').innerHTML = shop;

                            var elem = document.querySelector('#table_t2m');
                            var elem1 = document.querySelector('#table_t3m');
                            elem1.parentNode.removeChild(elem1);
                                                        createTable(elem,3,sim.length,sim);

                          });
                                function createTable(parent,cols,rows,shops){
                                 var table = document.createElement(`table-borderless-sm`);
                                 table.id = 'table_t3m';
                                    let thead = document.createElement('thead');
                                    let tbody = document.createElement('tbody');

                                        let row_1 = document.createElement('tr');
                                        let heading_1 = document.createElement('th');
                                        heading_1.innerHTML = "Sr. No.";
                                        let heading_2 = document.createElement('th');
                                        heading_2.innerHTML = "Name";
                                        let heading_3 = document.createElement('th');
                                        heading_3.innerHTML = "Company";

                                        row_1.appendChild(heading_1);
                                        row_1.appendChild(heading_2);
                                        row_1.appendChild(heading_3);
                                        thead.appendChild(row_1);

                                    table.appendChild(thead);


                                  console.log(cols);
                                        for (var i = 0; i < rows; i++) {
                                            var tr = document.createElement('tr');

                                            for (var j = 0; j < cols; j++) {
                                                var td = document.createElement('td');
                                               if(j==0){

                                               td.innerHTML = shops[i].nameSimAndModem;
                                               } else if(j==1){
                                               td.innerHTML = shops[i].remainsSimModem;
                                               }else{
                                               td.innerHTML = shops[i].shop;
                                               }

                                                tr.appendChild(td);

                                            }
                                            tbody.appendChild(tr);
                                        }
                                        table.appendChild(tbody);
                                        parent.appendChild(table);


                                  }

          });
 });