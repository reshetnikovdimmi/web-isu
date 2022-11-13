const requestURL = '/matrixT2table'
$.get(requestURL, function(distribution, status) {
    var elem = document.querySelector('#table_MatrixT2');
    var elem1 = document.querySelector('#tables_MatrixT2');
    elem1.parentNode.removeChild(elem1);
    createTable(elem, 6, distribution.length, distribution);
});

function createTable(parent, cols, rows, shops) {
    var table = document.createElement(`table`);
    table.id = 'tables_MatrixT2';
    table.classList.add("table-borderless-1");
    let thead = document.createElement('thead');
    let row_1 = document.createElement('tr');
    var arr = createArr(shops);
    for (var i = 0; i < arr.length; i++) {
        let heading_1 = document.createElement('th');
        heading_1.innerHTML = arr[i];
        row_1.appendChild(heading_1);
    }
    thead.appendChild(row_1);
    table.appendChild(thead);
    let tbody = document.createElement('tbody');
    var arrrows = createArrRows(shops);
    for (var i = 0; i < arrrows.length; i++) {
        var tr = document.createElement('tr');
        for (var j = 0; j < arr.length; j++) {
            var td = document.createElement('td');
            if (j == 0) {
                td.innerHTML = arrrows[i];
            } else if (j == arr.length - 2) {
                var button = document.createElement('button')
                td.appendChild(button);
                button.innerHTML = "РЕДАКТИРОВАТь";
                button.classList.add("btn");
                button.classList.add("btn-outline-primary");
            } else if (j == arr.length - 1) {
                var button = document.createElement('button')
                td.appendChild(button);
                button.innerHTML = "удалить";
                button.classList.add("btn");
                button.classList.add("btn-outline-primary");
            } else {
                td.innerHTML = createСolumn(shops, arr[j], arrrows[i]);
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    parent.appendChild(table);
     var tds = document.querySelectorAll('table.table-borderless-1 td');
                for (var i = 3; i < tds.length; i += 5) {
                    //tds[i].addEventListener('click', function func() {


                            $('#tables_MatrixT2 td').on('click', function() {
                        //  alert("tds[i]")

                                alert($(this).parent().index());

                            });

                       // this.removeEventListener('click', func)
                  //  });
                }
}

function createСolumn(shops, arr, arrrows) {
    var a = null;
    for (var i = 0; i < shops.length; i++) {
        if (shops[i].distributionModel == arrrows && shops[i].cluster == arr) {
            a = shops[i].quantity;
        }
    }
    return a;
}

function createArr(shops) {
    var a = ["МОДЕЛЬ РАСПРЕДЕЛЕНИЯ"];
    for (var i = 0; i < shops.length; i++) {
        a.push(shops[i].cluster);
    }
    var b = uniqueArray3(a);
    b.push(null, null);
    return b;
}

function createArrRows(shops) {
    var a = [];
    for (var i = 0; i < shops.length; i++) {
        a.push(shops[i].distributionModel);
    }
    uniqueArray3(a)
    return uniqueArray3(a);
}

function uniqueArray3(a) {
    function onlyUnique(value, index, self) {
        return self.indexOf(value) === index;
    }
    var unique = a.filter(onlyUnique);
    return unique;
}