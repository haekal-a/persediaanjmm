var Role = function (){

    var validator;

    var loadTblUser = function () {
        $('#tblUser').DataTable({
            ajax: {
                url: "/pengaturan/role/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    Utility.showBoxOverlay("tblUser");
                },
                dataSrc: function (data) {
                    if (data.code == 0) {
                        Utility.showErrorMessage("Data User", data.message);
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
                {"data": "username"},
                {"data": "nama"},
                {"data": "level", "className": "text-center", "render": function (data){
                        if (data == "1"){
                            return "Admin"
                        } else if (data == "2"){
                            return "User"
                        }
                    }},
                {"data": null, "className": "text-center"}
            ],
            columnDefs: [
                {
                    "targets": -1, "render": function (data, type, full, meta) {
                        var btnedit = '<a href="#" class="ti-pencil btn-info btn-sm edit" title="Ubah"></a>';
                        var btnDelete = '<a href="#" class="ti-trash btn-danger btn-sm delete" title="Hapus"></a>';
                        return roleUser ? btnedit : btnedit + btnDelete;
                    }
                }
            ],
            responsive: true,
            // dom: 'Bfrtip',
            // buttons: [
            //     "copy", "csv", "excel", "pdf", "print", "colvis"
            // ],
            lengthChange: true,
            lengthMenu: [5, 10, 20, 50],
            ordering: false,
            searching: true,
            pagingType: "numbers",
            language: {
                url: "/vendors/datatables/indonesian.json",
            },
            drawCallback: function (settings) {

            },
            initComplete: function (settings, json) {
                Utility.removeBoxOverlay("tblUser");
            }
        }).buttons().container().appendTo('#tblUser_wrapper .col-md-6:eq(0)');
    };

    var pageHandler = function (){

        // init validation
        validator = $('#formUser').validate();

        $('#mdlUser').on('hide.bs.modal', function () {
            validator.resetForm();
            $('#formUser').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
        });

        $("#formUser").submit(function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessageCallback("Data User", "Apakah anda yakin ingin menyimpan data ini?", function () {
                    saveUser();
                })
            }
        });

        $('#tblUser').on("click", "a.edit", function (e){
            e.preventDefault();
            var table = $('#tblUser').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#id").val(rData["id"]);
            $("#user").val(rData["username"]);
            $("#nama").val(rData["nama"]);
            $("#level").val(rData["level"]).selectpicker('refresh');
            if (roleUser){
                $("#level").prop("disabled", true);
            }
            $("#mdlUser").modal("show");
        });

        $("#tblUser").on('click', 'a.delete', function (e) {
            e.preventDefault();
            var table = $('#tblUser').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            Utility.showConfirmMessageCallback("Data User", "Apakah anda yakin ingin menghapus data ini?", function () {
                deleteUser(rData['id']);
            })
        });

        $("#btnBatal").on("click", function (e) {
            e.preventDefault();
            $("#mdlUser").modal("hide");
        })
    };

    var saveUser = function () {
        Utility.showBoxOverlay("formUser");
        // var formData = $('#formUser').serializeArray();
        var myform = $('#formUser');
        // Find disabled inputs, and remove the "disabled" attribute
        var disabled = myform.find(':input:disabled').removeAttr('disabled');

        // serialize the form
        var formData = myform.serializeArray();

        // re-disabled the set of inputs that you previously enabled
        disabled.attr('disabled','disabled');
        var data = {};
        $(formData).each(function (index, obj) {
            if (this.name.indexOf("user") != -1) {
                data["username"] = this.value;
            } else if (this.name.indexOf("pass") != -1) {
                data["password"] = this.value;
            } else {
                data[this.name] = this.value;
            }
        });
        $.ajax({
            type: "POST",
            url: "/pengaturan/role/",
            data: data,
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    loadTblUser();
                    $("#mdlUser").modal('hide');
                    $("#btnBatal").trigger('click');
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil diubah.");
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
                Utility.showErrorMessage('Terjadi kesalahan!', msg);
                Utility.removeBoxOverlay();
            }
        });
    };

    var deleteUser = function (id){
        Utility.showBoxOverlay("formUser");
        $.ajax({
            type: "DELETE",
            url: "/pengaturan/role/",
            data: {"id": id},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    loadTblUser();
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil dihapus.");
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
                Utility.showErrorMessage('Terjadi kesalahan!', msg);
                Utility.removeBoxOverlay();
            }
        });
    };

    return {
        init: function (){
            loadTblUser();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function (){
    Role.init();
});