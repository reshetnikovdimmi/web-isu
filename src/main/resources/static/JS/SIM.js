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
                    var shop = SIM[1].shop;
                    document.querySelector('#Shop').innerHTML = shop;
               });
     });

      $('.table_t2m .btn').on('click', function(event){
                 event.preventDefault();
                 var href = $(this).attr("href");
             $.get(href, function(SIM, status){             
                 var sim = SIM;
                 var shop = SIM[1].shop;
                 document.querySelector('#Shop_1').innerHTML = shop;
                 var elem = document.querySelector('#table_t2m');
                 var elem1 = document.querySelector('#table_t3m');
                 elem1.parentNode.removeChild(elem1);

                 createTable(elem,6,sim.length,sim);

             });


                                function createTable(parent,cols,rows,shops){
                                 var table = document.createElement(`table`);
                                 table.id = 'table_t3m';
                                 table.classList.add("table-borderless-1");

                                    let thead = document.createElement('thead');


                                        let row_1 = document.createElement('tr');
                                        let heading_1 = document.createElement('th');
                                        heading_1.innerHTML = "Name SIM";
                                        let heading_2 = document.createElement('th');
                                        heading_2.innerHTML = "Sale_6/6";
                                        let heading_3 = document.createElement('th');
                                        heading_3.innerHTML = "Sale";
                                        let heading_4 = document.createElement('th');
                                        heading_4.innerHTML = "Remanis";

                                        let heading_6 = document.createElement('th');
                                        heading_6.innerHTML = "Plan";
                                        let heading_7 = document.createElement('th');
                                        heading_7.innerHTML = "% Plan";

                                        row_1.appendChild(heading_1);
                                        row_1.appendChild(heading_2);
                                        row_1.appendChild(heading_3);
                                        row_1.appendChild(heading_4);

                                        row_1.appendChild(heading_6);
                                        row_1.appendChild(heading_7);

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
                                               td.innerHTML = shops[i].sale_6;
                                               }else if(j==2){
                                               td.innerHTML = shops[i].sale;
                                               }else if(j==3){
                                               td.innerHTML = shops[i].remanis;
                                               }else if(j==4){
                                               td.innerHTML = shops[i].plan;
                                               }else{
                                               td.innerHTML = shops[i].planVypol;
                                               }

                                                tr.appendChild(td);

                                            }
                                            tbody.appendChild(tr);
                                        }
                                        table.appendChild(tbody);
                                        parent.appendChild(table);

                                        var tds = document.querySelectorAll('table.table-borderless-1 td');
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

                                                    input.addEventListener('change', function(){

                                                        $('#table_t3m td').on('click',function(){
                                                                const body = {
                                                                  id: $(this).parent().index(),
                                                                  shop: tab
                                                                }

                                                            console.log($(this).parent().index());
                                                            $('#table_t3m td').off('click');

                                                                        sendRequest('POST', requestURL, body)
                                                                          .then(data => console.log(data))
                                                                          .catch(err => console.log(err))


                                                        });
                                                    });
                                                    this.removeEventListener('click', func)
                                              });
                                          }

                                  }

      });
                                        function sendRequest(method, url, body = null) {
                                          return new Promise((resolve, reject) => {
                                            const xhr = new XMLHttpRequest()
                                        alert('сохранено' + "-" + tab)
                                            xhr.open(method, url)

                                            xhr.responseType = 'json'
                                            xhr.setRequestHeader('Content-Type', 'application/json')

                                            xhr.onload = () => {
                                              if (xhr.status >= 400) {
                                                reject(xhr.response)
                                              } else {
                                                resolve(xhr.response)
                                              }
                                            }

                                            xhr.onerror = () => {
                                              reject(xhr.response)
                                            }

                                            xhr.send(JSON.stringify(body))
                                          })
                                        }
});









