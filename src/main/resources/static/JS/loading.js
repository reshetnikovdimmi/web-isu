$(document).ready(function() {
   // bind form submission event
    $("#file-upload-spark").on("submit", function (e) {
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
            success: function (res) {
                console.log(res);
                $('#status-spark').text(res);
            },
            error: function (err) {
                $('#status-spark').text(err.responseText);
                console.error(err);
            }
        });
    });

        $("#file-upload-unf").on("submit", function (e) {
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
                success: function (res) {
                    console.log(res);
                    $('#status-unf').text(res);
                },
                error: function (err) {
                    $('#status-unf').text(err.responseText);
                    console.error(err);
                }
            });
        });

        $("#file-upload-doc").on("submit", function (e) {
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
                        success: function (res) {
                            console.log(res);
                            $('#status-doc').text(res);
                        },
                        error: function (err) {
                            $('#status-doc').text(err.responseText);
                            console.error(err);
                        }
                    });
                });

   });