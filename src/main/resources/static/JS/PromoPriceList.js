$(document).ready(function() {
    let today = new Date().toISOString().slice(0, 10);
    $('#showDate').val(today)
    $('#dropDownListPhone').on('change', function() {
        $.get('/dropDownListBrendPromo/' + $(this).val(), {}, function(data) {
            var data = JSON.parse(data);
            var s = '<option value="' + "select option" + '">' + "select option" + '</option>';
            for (var i = 0; i < data.length; i++) {
                s += '<option value="' + data[i] + '">' + data[i] + '</option>';
            }
            $('#dropDownListModelGB').html(s);
            $("#dropDownListModels").html('<option value="' + "select option" + '">' + "select option" + '</option>');
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




    $('#button-show-promo').on('click', function() {
        if ($('#showDate').val() == '') {
            modals("showDate");
        } else {
            $.get('/showPromo/' + $('#showDate').val(), {}, function(data) {
                $(".showPromo").html(data);
            });
        }
    });
    $('#button-search').on('click', function() {
        let price_promo = {
            id: null,
            brend: null,
            models: null,
            price: null,
            price_promo: null,
            startPromo: null,
            endPromo: null,
            marwel: null,
            tfn: null,
            vvp: null,
            merlion: null,
        };
        if ($('#dropDownListModelGB').val() != 'select option') {
            price_promo.brend = $('#dropDownListModelGB').val();
        }
        if ($('#dropDownListModels').val() != 'select option') {
            price_promo.models = $('#dropDownListModels').val();
        }
        if ($('#startDate').val() != '') {
            price_promo.startPromo = $('#startDate').val();
        }
        if ($('#endDate').val() != '') {
            price_promo.endPromo = $('#endDate').val();

        }

        var json = JSON.stringify(price_promo);
        $.ajax({
            type: "POST",
            url: "searchPromo",
            data: json,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(msg) {
                ceateTableAllBonus(msg)
            }
        });
    });
    $('#button-save').on('click', function() {
        let price_promo = {
            id: $('#IDupdate').val(),
            brend: $('#dropDownListModelGB').val(),
            models: $('#dropDownListModels').val(),
            price: $('#price').val(),
            price_promo: $('#pricePromo').val(),
            startPromo: $('#startDate').val(),
            endPromo: $('#endDate').val(),
            marwel: $('#marwel').val(),
            tfn: $('#tfn').val(),
            vvp: $('#vvp').val(),
            merlion: $('#merlion').val(),
        };
        if ($('#dropDownListModelGB').val() == 'select option') {
            modals("модель");
        } else if ($('#dropDownListModels').val() == 'select option') {
            modals("модель гб");
        } else if ($('#price').val() == '') {
            modals("price");
        } else if ($('#pricePromo').val() == '') {
            modals("pricePromo");
        } else if ($('#startDate').val() == '') {
            modals("startDate");
        } else if ($('#endDate').val() == '') {
            modals("endDate");
        } else if ($('#marwel').val() == '') {
            modals("marwel");
        } else if ($('#tfn').val() == '') {
            modals("tfn");
        } else if ($('#vvp').val() == '') {
            modals("vvp");
        } else if ($('#merlion').val() == '') {
            modals("merlion");
        } else {
            var json = JSON.stringify(price_promo);
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
    update()
    clear()
});

function clear() {
    $('#IDupdate').val(0)
    $('#dropDownListModelGB').html('<option value="' + 'select option' + '">' + 'select option' + '</option>');
    $('#dropDownListModels').html('<option value="' + 'select option' + '">' + 'select option' + '</option>');
    $('#price').val('');
    $('#pricePromo').val('');
    $('#startDate').val('');
    $('#endDate').val('');
    $('#marwel').val('');
    $('#tfn').val('');
    $('#vvp').val('');
    $('#merlion').val('');
}

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
    update()
    clear()
    $.get('/showPromo/' + $('#showDate').val(), {}, function(data) {
        $(".showPromo").html(data);
    });
}

function delet() {
    $(document).find('.DEL').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;
        $.get('/deletePromo/' + models, {}, function(data) {
            ceateTableAllBonus(data);
        });
    });
}

function update() {
    $(document).find('.UPDATE').on('click', function() {
        var models = $(this).parents('tr:first').find('td:eq(0)').text(),
            data;
        $.get('/updatePromo/' + models, {}, function(data) {
            $('#IDupdate').val(data.id)
            $('#dropDownListModelGB').html('<option value="' + data.brend + '">' + data.brend + '</option>');
            $('#dropDownListModels').html('<option value="' + data.models + '">' + data.models + '</option>');
            $('#price').val(data.price);
            $('#pricePromo').val(data.price_promo);
            $('#startDate').val(data.startPromo);
            $('#endDate').val(data.endPromo);
            $('#marwel').val(data.marwel);
            $('#tfn').val(data.tfn);
            $('#vvp').val(data.vvp);
            $('#merlion').val(data.merlion);
        });
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