var dataTable;

$(document).ready(function () {
    $("#fileuploader").uploadFile({
        url: "upload",
        allowedTypes: "csv",
        onSuccess: function (files) {
            $.ajax({
                url: "process",
                data: {
                    fileName: files[0]
                },
                type: "GET",
                success: function (result) {
                    toastr["success"]("File uploaded successfully")
                    process_table(result);
                }
            });
        },
        onSubmit: function (files) {
            $(".ajax-file-upload-statusbar").remove();
        },
        onError: function (error) {
            toastr["error"]("Error while trying to save file on server")
        }
    });

});

var _remove_old_table = function () {
    if (dataTable) {
        dataTable.destroy();
        $('#myTable').empty();
    }

};

var process_table = function (data) {
    var head = data[0].split(",");
    var columns = [];
    _remove_old_table();
    for (var j = 0; j < head.length; j++) {
        columns.push({"title": head[j]});
    }

    var result_data = [];
    for (var i = 1; i < data.length; i++) {
        result_data.push(data[i].split(","));
    }

    data.shift();
    dataTable = $('#myTable').DataTable({
        "columns": columns,
        "data": result_data
    });

    toastr["success"]("File processed and data showed on table!")
};