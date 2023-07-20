$(document).ready(function() {
    $('#dropDownListPhone').on('change', function() {
        $.get('/dropDownListBrendPromo/' + $(this).val(), {}, function(data) {
            var data = JSON.parse(data);
            var s = '<option value="' + "select option" + '">' + "select option" + '</option>';
            for (var i = 0; i < data.length; i++) {
                s += '<option value="' + data[i] + '">' + data[i] + '</option>';
            }
            $('#dropDownListModelGB').html(s);
        });
    });
    $('#dropDownListModelGB ').on('change', function() {
        $.get('/dropDownListModels/' + $(this).val(), {}, function(data) {
            var data = JSON.parse(data);
            var s = '<option value="' + "select option" + '">' + "select option" + '</option>';
            for (var i = 0; i < data.length; i++) {
                s += '<option value="' + data[i] + '">' + data[i] + '</option>';
            }
            $("#dropDownListModels").html(s);
        });
    });
    $('#dropDownListModels ').on('change', function() {
        $.get('/price/' + $(this).val().replaceAll('/', '_'), {}, function(data) {
            $("#price").val(data);
        });
    });
    $('#button-save').on('click', function() {
        let bonuses = {
            shop: null,
            models: null,
            provider: null,
            phone: null,
            startDate: null,
            endDate: null,
        };
        if ($('#dropDownListModelGB').val() == 'select option') {
            modals("l");
        } else {
            var json = JSON.stringify(bonuses);
            $.ajax({
                type: "POST",
                url: "savePromo",
                data: json,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(msg) {
                    ceateTableAllBonus(msg)
                }
            });
        }
    });
    delet()
});

function ceateTableAllBonus(data) {
    var elem = document.querySelector('#table_tablePromo');
    var elem1 = document.querySelector('#tables_tablePromo');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_tablePromo';
    table.classList.add("table");
    table.classList.add("table-hover");
    table.classList.add("tables_tablePromo");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "id";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "brend";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "models";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "price";
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = "price_promo";
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = "start_promo"
    let heading_7 = document.createElement('th');
    heading_7.innerHTML = "end_promo"
    let heading_8 = document.createElement('th');
    heading_8.innerHTML = "marwel"
    let heading_9 = document.createElement('th');
    heading_9.innerHTML = "tfn"
    let heading_10 = document.createElement('th');
    heading_10.innerHTML = "vvp"
    let heading_11 = document.createElement('th');
    heading_11.innerHTML = "merlion"
    let heading_12 = document.createElement('th');
    heading_12.innerHTML = " "
    let heading_13 = document.createElement('th');
    heading_13.innerHTML = " "
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    row_1.appendChild(heading_7);
    row_1.appendChild(heading_8);
    row_1.appendChild(heading_9);
    row_1.appendChild(heading_10);
    row_1.appendChild(heading_11);
    row_1.appendChild(heading_12);
    row_1.appendChild(heading_13);
    let tbody = document.createElement('tbody');
    tbody.classList.add("labels");
    var sum = 0;
    for (var i = 0; i < data.length; i++) {
        table.id = 'tables_tablePromo';
        var tr = document.createElement('tr');
        for (var j = 0; j < 13; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].id;
            }
            if (j == 1) {
                td.innerHTML = data[i].brend;
            }
            if (j == 2) {
                td.innerHTML = data[i].models;
            }
            if (j == 3) {
                td.innerHTML = data[i].price;
            }
            if (j == 4) {
                td.innerHTML = data[i].price_promo;
            }
            if (j == 5) {
                td.innerHTML = data[i].startPromo;
            }
            if (j == 6) {
                td.innerHTML = data[i].endPromo;
            }
            if (j == 7) {
                td.innerHTML = data[i].marwel;
            }
            if (j == 8) {
                td.innerHTML = data[i].tfn;
            }
            if (j == 9) {
                td.innerHTML = data[i].vvp;
            }
            if (j == 10) {
                td.innerHTML = data[i].merlion;
            }
            if (j == 11) {
                var button = document.createElement('button')
                button.type = 'button';
                td.appendChild(button);
                button.classList.add("btn");
                button.classList.add("btn-outline-primary");
                button.classList.add("UPDATE");
                button.id = 'button';
                button.innerHTML = "UPDATE";
            }
            if (j == 12) {
                var button = document.createElement('button')
                button.type = 'button';
                td.appendChild(button);
                button.classList.add("btn");
                button.classList.add("btn-outline-primary");
                button.classList.add("DEL");
                button.id = 'button';
                button.innerHTML = "DEL";;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    thead.appendChild(row_1);
    table.appendChild(thead);
    elem.appendChild(table);
    delet()
}

function delet() {
    $(document).find('.DEL').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;
        alert(models)
    });
}

function modals(message) {
    $('#myModal').modal("show");
    document.querySelector('.modal-body').textContent = message;
    $('.btn-close').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-secondary').on('click', function() {
        $('#myModal').modal('hide');
    });
    $('.btn-primary').attr('disabled', true);
}