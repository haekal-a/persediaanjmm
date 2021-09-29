var Master = function (){

    var validator;

    var loadTblMaster = function () {
        $('#tblPmqaMaster').DataTable({
            ajax: {
                url: "/pmqa/master/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    Utility.showBoxOverlay("tblPmqaMaster");
                },
                dataSrc: function (data) {
                    if (data.code === "0") {
                        Utility.showErrorMessage("Data Master", data.message);
                        data.draw = 1;
                        data.recordsTotal = 0;
                        data.recordsFiltered = 0;
                        return null;
                    } else {
                        data.draw = data.object.draw;
                        data.recordsTotal = data.object.recordsTotal;
                        data.recordsFiltered = data.object.recordsFiltered;
                        return data.object.data;
                    }
                },
                error: function (jqXHR, status, error) {
                    Utility.showErrorMessage(status, error);
                }
            },
            processing: false,
            destroy: true,
            serverSide: false,
            columns: [
                {
                    "data": null, "className": "text-center", "width": "5%", "render": function (data, type, full, meta) {
                        return meta.settings._iDisplayStart + meta.row + 1;
                    }
                },
                {"data": "idMaster", "className": "text-center"},
                {"data": "idDeliverable", "className": "text-center"},
                {"data": "sectionNo", "className": "text-center"},
                {"data": "function"},
                {"data": "taskNo", "className": "text-center"},
                {"data": "task", "className": "text-center"},
                {"data": "deliverableName", "className": "text-center"},
                {"data": "scheduleInTor", "className": "text-center"},
                {"data": null, "className": "text-center"}
            ],
            columnDefs: [
                {
                    "targets": -1, "render": function (data, type, full, meta) {
                        var btnedit = '<a href="#" class="fas fa-edit btn-info btn-sm edit"></a>';
                        var btnDelete = '<a href="#" class="fas fa-trash btn-danger btn-sm delete"></a>';
                        return btnedit + btnDelete;
                    }
                }
            ],
            responsive: true,
            // dom: 'Bfrtip',
            // buttons: [
            //     "copy", "csv", "excel", "pdf", "print", "colvis"
            // ],
            lengthChange: true,
            lengthMenu: [10, 20, 50],
            ordering: false,
            searching: true,
            // pagingType: "numbers",
            language: {
                url: "/plugins/datatables/indonesian.json",
            },
            drawCallback: function (settings) {

            },
            initComplete: function (settings, json) {
                Utility.removeBoxOverlay("tblPmqaMaster");
                /*var btnTambah = '<a href="#" class="pull-left btn btn-sm btn-success addMaster">Tambah Master</a>';
                                $("#tblPmqaMaster_wrapper").children(":first").prepend(btnTambah);

                                $(".addMaster").on("click", function (e) {
                                    e.preventDefault();
                                    $("#mdlEditMaster").find('.modal-title').html("Add Master");
                                    $("#mdlEditMaster").modal('show');
                                })*/
            }
        }).buttons().container().appendTo('#tblPmqaMaster_wrapper .col-md-6:eq(0)');
    };

    var pageHandler = function (){

        // init validation
        validator = $('#formPmqaMaster').validate();

        $('#mdlPmqaMaster').on('hide.bs.modal', function () {
            validator.resetForm();
            $('#formPmqaMaster').each(function () {
                this.reset();
            });
        });

        $("#formPmqaMaster").submit(function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessage("Data Master", "Apakah anda yakin ingin menyimpan data ini?", function (r) {
                    if (r) {
                        saveMaster();
                    }
                })
            }
        });

        $('#tblPmqaMaster').on("click", "a.edit", function (e){
            e.preventDefault();
            var table = $('#tblPmqaMaster').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#idMaster").val(rData["idMaster"]);
            $("#idDeliverable").val(rData["idDeliverable"]);
            $("#sectionNo").val(rData["sectionNo"]);
            $("#function").val(rData["function"]);
            $("#taskNo").val(rData["taskNo"]);
            $("#task").val(rData["task"]);
            $("#deliverableName").val(rData["deliverableName"]);
            $("#scheduleInTor").val(rData["scheduleInTor"]);
            $("#mdlPmqaMaster").modal("show");
        });

        $("#tblPmqaMaster").on('click', 'a.delete', function (e) {
            e.preventDefault();
            var table = $('#tblPmqaMaster').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            Utility.showConfirmMessage("Data User", "Apakah anda yakin ingin menghapus data ini?", function (r) {
                if (r) {
                    deleteMaster(rData['idMaster']);
                }
            })
        });
    };

    var saveMaster = function () {
        Utility.showBoxOverlay("formPmqaMaster");
        var data = $('#formPmqaMaster').serialize();
        $.ajax({
            type: "POST",
            url: "/pmqa/master/",
            data: data,
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil diubah.", function (r) {
                        loadTblMaster();
                        $("#mdlPmqaMaster").modal('hide');
                    })
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", message);
                }
            },
            error: function (xhr, status, error) {
                var err = xhr.responseJSON;
                var msg = "";
                if (err.status === "Bad Request") {
                    $.each(err.errors, function (index, item) {
                        msg += " [" + item.field + "] " + item.defaultMessage;
                    });
                } else {
                    // msg = eval("(" + xhr.responseText + ")");
                    msg = xhr.responseText;
                }
                Utility.showErrorMessage('Terjadi kesalahan!', msg, function (r) {
                    Utility.removeBoxOverlay();
                });
            }
        });
    };

    var deleteMaster = function (idMaster){
        Utility.showBoxOverlay("formPmqaMaster");
        $.ajax({
            type: "DELETE",
            url: "/pmqa/master/",
            data: {"idMaster": idMaster},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil dihapus.", function (r) {
                        loadTblMaster();
                    })
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", message);
                }
            },
            error: function (xhr, status, error) {
                var err = xhr.responseJSON;
                var msg = "";
                if (err.status === "Bad Request") {
                    $.each(err.errors, function (index, item) {
                        msg += " [" + item.field + "] " + item.defaultMessage;
                    });
                } else {
                    // msg = eval("(" + xhr.responseText + ")");
                    msg = xhr.responseText;
                }
                Utility.showErrorMessage('Terjadi kesalahan!', msg, function (r) {
                    Utility.removeBoxOverlay();
                });
            }
        });
    };

    return {
        init: function (){
            loadTblMaster();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function (){
    Master.init();
})