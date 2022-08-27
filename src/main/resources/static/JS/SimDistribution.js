$(document).ready(function(){
var tab;
const requestURL = '/simos'
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
    });

     $('.table_t2 .btn').on('click', function(event){
          event.preventDefault();
          var href = $(this).attr("href");
               $.get(href, function(SIM, status){
                    var sim = SIM;
                    console.log(sim);
                    var shop = SIM[0].shop;
                    document.querySelector('#Shop').innerHTML = shop;
               });
     });

      $('.table_t2m .btn').on('click', function(event){
                 event.preventDefault();
                 var href = $(this).attr("href");
             $.get(href, function(SIM, status){
                 var sim = SIM;
                 var shop = SIM[0].shop;
                 document.querySelector('#Shop_1').innerHTML = shop;
                 var elem = document.querySelector('#table_t2m');
                 var elem1 = document.querySelector('#table_t3m');
                 elem1.parentNode.removeChild(elem1);

                 createTable(elem,3,sim.length,sim);

             });


                                function createTable(parent,cols,rows,shops){
                                 var table = document.createElement(`table`);
                                 table.id = 'table_t3m';
                                 table.classList.add("table-borderless");

                                    let thead = document.createElement('thead');


                                        let row_1 = document.createElement('tr');
                                        let heading_1 = document.createElement('th');
                                        heading_1.innerHTML = "Name SIM";
                                        let heading_2 = document.createElement('th');
                                        heading_2.innerHTML = "Кол-во";


                                        row_1.appendChild(heading_1);
                                        row_1.appendChild(heading_2);


                                        thead.appendChild(row_1);

                                    table.appendChild(thead);

                                    let tbody = document.createElement('tbody');

                                        for (var i = 0; i < rows; i++) {
                                            var tr = document.createElement('tr');

                                            for (var j = 0; j < cols; j++) {
                                                var td = document.createElement('td');
                                               if(j==0){
                                               td.innerHTML = shops[i].nameSim;
                                               } else if(j==1){
                                               td.innerHTML = shops[i].toOrder;
                                               }else{
                                               td.innerHTML = shops[i].distribution;
                                               }

                                                tr.appendChild(td);

                                            }
                                            tbody.appendChild(tr);
                                        }
                                        table.appendChild(tbody);
                                        parent.appendChild(table);

                                        var tds = document.querySelectorAll('table.table-borderless td');
                                          for(var i =4;i<tds.length;i+=6){
                                              tds[i].addEventListener('click', function func(){

                                                    var input = document.createElement('input');

                                                    input.value = this.innerHTML;
                                                    this.innerHTML = '';
                                                    this.appendChild(input);

                                                    var td = this;
                                                    input.addEventListener('blur', function(){
                                                        td.innerHTML = this.value;
                                                     tab =  this.value;
                                                        td.addEventListener('click', func);

                                                    });


                                                    this.removeEventListener('click', func)
                                              });
                                          }

                                  }

      });

      $('.table_t2m_sim .btn').on('click', function(event){
                event.preventDefault();
                var href = $(this).attr("href");
                     $.get(href, function(SIM, status){
                          var sim = SIM;
                          console.log(sim);
                          var shop = SIM[0].nameSim;
                          document.querySelector('#SIM').innerHTML = shop;
                           var elem = document.querySelector('#table_t2m_sim');
                                           var elem1 = document.querySelector('#table_t3m_sim');
                                           elem1.parentNode.removeChild(elem1);

                                           createTableSIM(elem,3,sim.length,sim);

                     });
           });

           function createTableSIM(parent,cols,rows,shops){

                                            var table = document.createElement(`table`);
                                            table.id = 'table_t3m_sim';
                                            table.classList.add("table-borderless");

                                               let thead = document.createElement('thead');


                                                   let row_1 = document.createElement('tr');
                                                   let heading_1 = document.createElement('th');
                                                   heading_1.innerHTML = "Name SIM";
                                                   let heading_2 = document.createElement('th');
                                                   heading_2.innerHTML = "Кол-во";


                                                   row_1.appendChild(heading_1);
                                                   row_1.appendChild(heading_2);


                                                   thead.appendChild(row_1);

                                               table.appendChild(thead);

                                               let tbody = document.createElement('tbody');

                                                   for (var i = 0; i < rows; i++) {
                                                       var tr = document.createElement('tr');

                                                       for (var j = 0; j < cols; j++) {
                                                           var td = document.createElement('td');
                                                          if(j==0){
                                                          td.innerHTML = shops[i].shop;
                                                          } else if(j==1){
                                                          td.innerHTML = shops[i].toOrder;
                                                          }else{
                                                          td.innerHTML = shops[i].distribution;
                                                          }

                                                           tr.appendChild(td);

                                                       }
                                                       tbody.appendChild(tr);
                                                   }
                                                   table.appendChild(tbody);
                                                   parent.appendChild(table);

                                                   var tds = document.querySelectorAll('table.table-borderless td');
                                                     for(var i =4;i<tds.length;i+=6){
                                                         tds[i].addEventListener('click', function func(){

                                                               var input = document.createElement('input');

                                                               input.value = this.innerHTML;
                                                               this.innerHTML = '';
                                                               this.appendChild(input);

                                                               var td = this;
                                                               input.addEventListener('blur', function(){
                                                                   td.innerHTML = this.value;
                                                                tab =  this.value;
                                                                   td.addEventListener('click', func);

                                                               });


                                                               this.removeEventListener('click', func)
                                                         });
                                                     }

                                             }

});









