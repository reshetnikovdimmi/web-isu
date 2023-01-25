const requestURL = '/movementsPhone'

var cou = 0;
var body;
$(document).ready(function() {
    $('#loader').removeClass('hidden')
    requirementPhone(requestURL);
matrixT2Phone();

$('#MovementsPhone').on('click', function() {
 $('#loader').removeClass('hidden')
        $.ajax({
            url: '/allMovements',
            type: "GET",
            success: function(data) {

from_where(data);
to_where(data);
matrixT2Phone();
            }
        });
        return false;
    });



});

function requirementPhone(requestURL) {
    $.get(requestURL, function(requirementPhoneArr, status) {
        requirementPhone_mono(requirementPhoneArr);
        requirementPhone_multi(requirementPhoneArr);

    });



}
function matrixT2Phone() {
   $.ajax({
               url: '/requirementMatrixT2Phone',
               type: "GET",
               success: function(data) {

  table_matrixT2Phone(data);
               }
           });
           return false;
}
function table_matrixT2Phone(matrixT2Phone) {

    var uniqueArrays = [];
    for (var i = 0; i < matrixT2Phone.length; i++) {
        uniqueArrays.push(matrixT2Phone[i].shop);
    }
    var row = uniqueArray(uniqueArrays);
    var uniqueArrays = [];
    uniqueArrays.push("МОДЕЛЬ РАСПРЕДЕЛЕНИЯ");
    for (var i = 0; i < matrixT2Phone.length; i++) {
        uniqueArrays.push(matrixT2Phone[i].distributionModel);
    }
    var cell = uniqueArray(uniqueArrays);
    var elem = document.querySelector('#table_MatrixT2');
    var elem1 = document.querySelector('#tables_MatrixT2');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_MatrixT2';
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let tbody = document.createElement('tbody');
    for (var i = 0; i < cell.length; i++) {
        let heading_1 = document.createElement('th');
        heading_1.innerHTML = cell[i].replaceAll("/", " ");
        row_1.appendChild(heading_1);
    }
    for (var i = 0; i < row.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < cell.length; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = row[i];
            }
            for (var k = 0; k < matrixT2Phone.length; k++) {
                if (row[i] == matrixT2Phone[k].shop && cell[j] == matrixT2Phone[k].distributionModel) {
                    td.innerHTML = matrixT2Phone[k].quantity+"%";
                    if(matrixT2Phone[k].quantity<100){
                    td.style.color = "#ff0000";
                    }


                }
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);

}
function to_where(data){
data.sort((a, b) => a.to_where.localeCompare(b.to_where));
 var elem = document.querySelector('#table_to_where');
    var elem1 = document.querySelector('#tables_to_where');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_to_where';
    table.classList.add("table-borderless");
    table.classList.add("tables_to_where");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Откуда";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Модель";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "куда";

    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);

    let tbody = document.createElement('tbody');
    for (var i = 0; i < data.length; i++) {

            var tr = document.createElement('tr');
            for (var j = 0; j < 3; j++) {
                var td = document.createElement('td');
                if (j == 0) {

                    td.innerHTML = data[i].to_where;
                } else if (j == 1) {
                    td.innerHTML = data[i].model;
                } else if (j == 2) {
                    td.innerHTML = data[i].from_where;
                }
                tr.appendChild(td);
            }
            tbody.appendChild(tr);

    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}
function from_where(data){
 var elem = document.querySelector('#table_from_wheret');
    var elem1 = document.querySelector('#tables_from_wheret');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_from_wheret';
    table.classList.add("table-borderless");
    table.classList.add("tables_from_wheret");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Куда";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Модель";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Откуда";

    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);

    let tbody = document.createElement('tbody');
    for (var i = 0; i < data.length; i++) {

            var tr = document.createElement('tr');
            for (var j = 0; j < 3; j++) {
                var td = document.createElement('td');
                if (j == 0) {

                    td.innerHTML = data[i].from_where;
                } else if (j == 1) {
                    td.innerHTML = data[i].model;
                } else if (j == 2) {
                    td.innerHTML = data[i].to_where;
                }
                tr.appendChild(td);
            }
            tbody.appendChild(tr);

    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);

    requirementPhone(requestURL);
}

function requirementPhone_mono(requirementPhoneArr) {
    var elem = document.querySelector('#table_requirement_t2');
    var elem1 = document.querySelector('#tables_requirement_t2');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_requirement_t2';
    table.classList.add("table-borderless");
    table.classList.add("tables_requirement_t2");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Магазин";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Остаток";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Потребность";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Ассортимент";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "%";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < requirementPhoneArr.length; i++) {
        if (requirementPhoneArr[i].cluster != '') {
            var tr = document.createElement('tr');
            for (var j = 0; j < 5; j++) {
                var td = document.createElement('td');
                if (j == 0) {
                    var button = document.createElement('button')
                    button.type = 'button';
                    td.appendChild(button);
                    button.classList.add("button");
                    button.classList.add("requirementPhone");
                    button.id = 'button';
                    button.innerHTML = requirementPhoneArr[i].shop;
                } else if (j == 1) {
                    td.innerHTML = requirementPhoneArr[i].remanis;
                } else if (j == 2) {
                    td.innerHTML = requirementPhoneArr[i].requirement;
                } else if (j == 3) {
                    td.innerHTML = requirementPhoneArr[i].representation;
                } else if (j == 4) {
                    td.innerHTML = requirementPhoneArr[i].percent;
                }
                tr.appendChild(td);
            }
            tbody.appendChild(tr);
        }
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
}

function requirementPhone_multi(requirementPhoneArr) {
    var elem = document.querySelector('#table_requirement_t2_mult');
    var elem1 = document.querySelector('#tables_requirement_t2_mult');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_requirement_t2_mult';
    table.classList.add("table-borderless");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Магазин";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Остаток";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Потребность";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Ассортимент";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "%";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < requirementPhoneArr.length; i++) {
        if (requirementPhoneArr[i].cluster === '') {
            var tr = document.createElement('tr');
            for (var j = 0; j < 5; j++) {
                var td = document.createElement('td');
                if (j == 0) {
                    var button = document.createElement('button')
                    button.type = 'button';
                    td.appendChild(button);
                    button.classList.add("button");
                    button.classList.add("requirementPhone");
                    button.id = 'button';
                    button.innerHTML = requirementPhoneArr[i].shop;
                } else if (j == 1) {
                    td.innerHTML = requirementPhoneArr[i].remanis;
                } else if (j == 2) {
                    td.innerHTML = requirementPhoneArr[i].requirement;
                } else if (j == 3) {
                    td.innerHTML = requirementPhoneArr[i].representation;
                } else if (j == 4) {
                    td.innerHTML = requirementPhoneArr[i].percent;
                }
                tr.appendChild(td);
            }
            tbody.appendChild(tr);
        }
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    $(document).find('.requirementPhone').on('click', function() {
        var shopSKY = $(this).parents('tr:first').find('td:eq(0)').text();
        document.querySelector('#ShopDistribution').innerHTML = shopSKY;
        const url = '/shopMovements/' + shopSKY;
$('#loader').removeClass('hidden')
        $.get(url, function(shopMovements, status) {
      if(shopMovements.length == 0)  {
      $('#loader').addClass('hidden')
      modals("А ничего");

      }



from_where(shopMovements);
to_where(shopMovements);
matrixT2Phone();
       });
    });
    $('#loader').addClass('hidden')
}




function uniqueArray(a) {
    function onlyUnique(value, index, self) {
        return self.indexOf(value) === index;
    }
    var unique = a.filter(onlyUnique);
    return unique;
}



function sendRequest(method, url, body = null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()
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

function modals(message) {
    cou = 0;
    $('#myModal').modal("show");
    document.querySelector('.modal-body').textContent = message;
    $('.btn-close').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-secondary').on('click', function() {
        $('#myModal').modal('hide');
    });
}

