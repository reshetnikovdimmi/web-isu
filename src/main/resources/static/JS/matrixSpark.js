const requestURL = '/matrixSparkSale'
const requestURLupdate = '/updateSparkSale'

$(document).ready(function() {

    $('#updateMatrixSpark').on('click', function() {

        $.get(requestURLupdate, function(sparkSalePhone, status) {
        console.log(sparkSalePhone);
            createTableSpark(sparkSalePhone);
        });

    });

    $('#saveMatrixSpark').on('click', function() {
        alert("saveMatrixSpark")
    });

});


$.get(requestURL, function(sparkSalePhone, status) {
    createTableSpark(sparkSalePhone);
});

function delModel() {
    $(document).find('.del').on('click', function() {
        // for (var i = 1; i < arr.length - 2; i++) {
        alert($(this).parents('tr:first').find('td:eq( 0 )').text())
            //   }
    });
}

function createTableSpark(sparkSalePhone) {
    var elem = document.querySelector('#table_Spark');
    var elem1 = document.querySelector('#tables_Spark');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_Spark';
    table.classList.add("table-borderless-1");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "МОДЕЛЬ";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Sale";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Save";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    let tbody = document.createElement('tbody');
    for (var i = 0; i < sparkSalePhone.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 3; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = sparkSalePhone[i].model;
            } else if (j == 1) {
                td.innerHTML = sparkSalePhone[i].sale;
            } else {
                var button = document.createElement('input')
                button.type = 'checkbox';
                td.appendChild(button);

                button.classList.add("del");

            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    delModel();
}