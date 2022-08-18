$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
    });

     $('.table_t2 .btn').on('click', function(event){
                     event.preventDefault();
                     var href = $(this).attr("href");
                     $.get(href, function(SIM, status){

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
                                                        createTable(elem,4,sim.length,sim);

                          });
                                function createTable(parent,cols,rows,shops){
                                 var table = document.createElement(`table`);
                                 table.id = 'table_t3m';
                                 table.classList.add("table-borderless");

                                    let thead = document.createElement('thead');


                                        let row_1 = document.createElement('tr');
                                        let heading_1 = document.createElement('th');
                                        heading_1.innerHTML = "Sr. No.";
                                        let heading_2 = document.createElement('th');
                                        heading_2.innerHTML = "Name";
                                        let heading_3 = document.createElement('th');
                                        heading_3.innerHTML = "Company";
                                        let heading_4 = document.createElement('th');
                                        heading_4.innerHTML = "план";

                                        row_1.appendChild(heading_1);
                                        row_1.appendChild(heading_2);
                                        row_1.appendChild(heading_3);
                                        row_1.appendChild(heading_4);
                                        thead.appendChild(row_1);

                                    table.appendChild(thead);

                                    let tbody = document.createElement('tbody');



                                        for (var i = 0; i < rows; i++) {
                                            var tr = document.createElement('tr');

                                            for (var j = 0; j < cols; j++) {
                                                var td = document.createElement('td');
                                               if(j==0){

                                               td.innerHTML = shops[i].nameSimAndModem;
                                               } else if(j==1){
                                               td.innerHTML = shops[i].remainsSimModem;
                                               }else if(j==2){
                                               td.innerHTML = shops[i].shop;
                                               }else{
                                                td.innerHTML = shops[i].id;
                                               }

                                                tr.appendChild(td);

                                            }
                                            tbody.appendChild(tr);
                                        }
                                        table.appendChild(tbody);
                                        parent.appendChild(table);

                                        var tds = document.querySelectorAll('table.table-borderless td');
                                          for(var i =3;i<tds.length;i+=4){
                                          tds[i].addEventListener('click', function func(){

                                            var input = document.createElement('input');
                                            input.value = this.innerHTML;
                                            this.innerHTML = '';
                                            this.appendChild(input);
                                            var td = this;
                                            input.addEventListener('blur', function(){
                                            td.innerHTML = this.value;
                                            console.log(i);
                                            td.addEventListener('click', func);
                                            });

                                            this.removeEventListener('click', func)
                                          });
                                          }

                                  }

          });


 });