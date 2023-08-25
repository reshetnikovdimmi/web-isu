$(document).ready(function() {
    // bind form submission event
    $("#file-upload-spark").on("submit", function(e) {
        // cancel the default behavior
        e.preventDefault();
        // use $.ajax() to upload file
        $.ajax({
            url: "/file-upload",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function(res) {
                console.log(res);
                $('#status-spark').text(res);
            },
            error: function(err) {
                $('#status-spark').text(err.responseText);
                console.error(err);
            }
        });
    });
    $("#file-upload-unf").on("submit", function(e) {
        // cancel the default behavior
        e.preventDefault();
        // use $.ajax() to upload file
        $.ajax({
            url: "/file-upload-unf",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function(res) {
                console.log(res);
                $('#status-unf').text(res);
            },
            error: function(err) {
                $('#status-unf').text(err.responseText);
                console.error(err);
            }
        });
    });
    $("#file-upload-doc").on("submit", function(e) {
        // cancel the default behavior
        e.preventDefault();
        // use $.ajax() to upload file
        $.ajax({
            url: "/file-upload-doc",
            type: "POST",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function(res) {
                console.log(res);
                $('#status-doc').append('<div class="third">' + res.unfLoad + '</div>');
                ceateTableAllBonus(res.docUnfTales)
            },
            error: function(err) {
                $('#status-doc').text(err.responseText);
                console.error(err);
            }
        });
    });
    $("#del").on("click", function(e) {
        $('.third').remove();
    });
});

function ceateTableAllBonus(data) {
    var elem = document.querySelector('#table_tablePromo');
    var elem1 = document.querySelector('#tables_tablePromo');
    elem1.parentNode.removeChild(elem1);
    var table = document.createElement(`table`);
    table.id = 'tables_tablePromo';
    table.classList.add("table");
    table.classList.add("table-hover");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Номенклатура";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Характеристика";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Количество";
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Цена";
    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    let tbody = document.createElement('tbody');
    var sum = 0;
    for (var i = 0; i < data.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < 5; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = data[i].nomenclatures;
            }
            if (j == 1) {
                td.innerHTML = data[i].barcode;
            }
            if (j == 2) {
                td.innerHTML = data[i].quantity;
            }
            if (j == 4) {
                td.innerHTML = data[i].price;
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