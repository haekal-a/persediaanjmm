var Monitoring = function (){

    var validator;

    var loadTblMonitoring = function () {
        $('#tblPmqaMonitoring').DataTable({
            ajax: {
                url: "/pmqa/monitoring/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    Utility.showBoxOverlay("tblPmqaMonitoring");
                },
                dataSrc: function (data) {
                    if (data.code === "0") {
                        Utility.showErrorMessage("Data Monitoring", data.message);
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
                    "data": null, "className": "all text-center", "width": "5%", "render": function (data, type, full, meta) {
                        return meta.settings._iDisplayStart + meta.row + 1;
                    }
                },
                {"data": "sectionNo", "className": "all text-center"},
                {"data": "function", "className": "all"},
                {"data": "taskNo", "className": "all text-center"},
                {"data": "task", "className": "all text-center"},
                {"data": "deliverableName", "className": "all text-center"},
                {"data": "scheduleInTor", "className": "all text-center"},
                {"data": "deliverableCode", "className": "all text-center"},
                {"data": "monthSubmission", "className": "all text-center"},
                {"data": "submissionStatus", "className": "all", "render": function (data, type, full, meta) {
                        if (data == 1){
                            return "Inactive";
                        } else if (data == 2){
                            return "Active";
                        } else if (data == 3){
                            return "Reallocated";
                        } else if (data == 4){
                            return "No Schedule";
                        } else {
                            return data;
                        }
                    }},
                {"data": "period", "className": "all text-center"},
                {"data": "latestVersion", "className": "all text-center"},
                {"data": "position", "className": "all", "render": function (data, type, full, meta) {
                    if (data == 1){
                        return "Dalam Proses QA";
                    } else if (data == 2){
                        return "Perbaikan oleh Deloitte";
                    } else if (data == 3){
                        return "Disetujui Steerco";
                    } else {
                        return data;
                    }
                    }},
                {"data": "softcopyStatus", "className": "all", "render": function (data, type, full, meta) {
                        return data == "2" ? "Ada" : "Tidak Ada";
                    }},
                {"data": "hardcopyStatus", "className": "all", "render": function (data, type, full, meta) {
                        return data == "2" ? "Ada" : "Tidak Ada";
                    }},
                {"data": "hardcopyIn", "className": "all text-right"},
                {"data": "hardcopyOut", "className": "all text-right"},
                {"data": "hardcopyLeft", "className": "all text-right"},
                {"data": "paymentStatus", "className": "all", "render": function (data, type, full, meta) {
                        if (data == 1){
                            return "Belum Dapat Dibayarkan";
                        } else if (data == 2){
                            return "Belum Ditagih";
                        } else if (data == 3){
                            return "Proses Pembayaran";
                        } else if (data == 4){
                            return "Sudah Dibayarkan";
                        } else {
                            return data;
                        }
                    }},
                {"data": "keterangan", "className": "all"},
                {"data": null, "className": "all text-center"}
            ],
            columnDefs: [
                {
                    "targets": -1, "render": function (data, type, full, meta) {
                        var btnedit = '<a href="#" class="fas fa-edit btn-info btn-sm edit" title="Edit"></a>';
                        var btnDelete = '<a href="#" class="fas fa-trash btn-danger btn-sm delete" title="Delete"></a>';
                        var btnView = '<a href="#" class="fab fa-chromecast btn-primary btn-sm view" title="View"></a>';
                        return roleUser ? btnView : btnedit + btnDelete;
                    }
                }
            ],
            responsive: {
                details: false
            },
            dom: 'Blfrtip',
            buttons: [
                "copy", "excel"
            ],

            scrollX: true,
            lengthChange: true,
            lengthMenu: [5, 10, 20, 50],
            ordering: false,
            searching: true,
            // pagingType: "numbers",
            language: {
                url: "/plugins/datatables/indonesian.json",
            },
            drawCallback: function (settings) {

            },
            initComplete: function (settings, json) {
                Utility.removeBoxOverlay("tblPmqaMonitoring");
                /*var btnTambah = '<a href="#" class="pull-left btn btn-sm btn-success addMonitoring">Tambah Monitoring</a>';
                                $("#tblPmqaMonitoring_wrapper").children(":first").prepend(btnTambah);

                                $(".addMonitoring").on("click", function (e) {
                                    e.preventDefault();
                                    $("#mdlEditMonitoring").find('.modal-title').html("Add Monitoring");
                                    $("#mdlEditMonitoring").modal('show');
                                })*/
            }
        }).buttons().container().appendTo('#tblPmqaMonitoring_wrapper .col-md-6:eq(0)');
    };

    var pageHandler = function (){

        if (roleUser){
            $(".card-tools").empty();
        }

        // init validation
        validator = $('#formPmqaMonitoring').validate();
        $("#adaHardcopy").hide();

        $('#mdlPmqaMonitoring').on('hide.bs.modal', function () {
            validator.resetForm();
            $('#formPmqaMonitoring').each(function () {
                this.reset();
            });
            $("#adaHardcopy").hide();
        });

        $("#formPmqaMonitoring").submit(function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessage("Data Monitoring", "Apakah anda yakin ingin menyimpan data ini?", function (r) {
                    if (r) {
                        saveMonitoring();
                    }
                })
            }
        });

        $('#tblPmqaMonitoring').on("click", "a.edit", function (e){
            e.preventDefault();
            var table = $('#tblPmqaMonitoring').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            if (rData["hardcopyStatus"] == "2") $("#adaHardcopy").show();
            $("#idMonitoring").val(rData["idMonitoring"]);
            $("#deliverableCode").val(rData["deliverableCode"]);
            $("#sectionNo").val(rData["sectionNo"]);
            $("#function").val(rData["function"]);
            $("#taskNo").val(rData["taskNo"]);
            $("#task").val(rData["task"]);
            $("#deliverableName").val(rData["deliverableName"]);
            $("#scheduleInTor").val(rData["scheduleInTor"]);
            $("#submissionStatus").val(rData["submissionStatus"]);
            $("#period").val(rData["period"]);
            $("#softcopyStatus").val(rData["softcopyStatus"]);
            $("#hardcopyStatus").val(rData["hardcopyStatus"]);
            $("#hardcopyIn").val(rData["hardcopyIn"]);
            $("#hardcopyOut").val(rData["hardcopyOut"]);
            $("#hardcopyLeft").val(rData["hardcopyLeft"]);
            $("#keterangan").val(rData["keterangan"]);
            $("#mdlPmqaMonitoring").modal("show");
        });

        $('#tblPmqaMonitoring').on("click", "a.view", function (e){
            e.preventDefault();
            var table = $('#tblPmqaMonitoring').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            if (rData["hardcopyStatus"] == "2") $("#adaHardcopy").show();
            $("#idMonitoring").val(rData["idMonitoring"]).prop("disabled", true);
            $("#deliverableCode").val(rData["deliverableCode"]).prop("disabled", true);
            $("#sectionNo").val(rData["sectionNo"]).prop("disabled", true);
            $("#function").val(rData["function"]).prop("disabled", true);
            $("#taskNo").val(rData["taskNo"]).prop("disabled", true);
            $("#task").val(rData["task"]).prop("disabled", true);
            $("#deliverableName").val(rData["deliverableName"]).prop("disabled", true);
            $("#scheduleInTor").val(rData["scheduleInTor"]).prop("disabled", true);
            $("#submissionStatus").val(rData["submissionStatus"]).prop("disabled", true);
            $("#period").val(rData["period"]).prop("disabled", true);
            $("#softcopyStatus").val(rData["softcopyStatus"]).prop("disabled", true);
            $("#hardcopyStatus").val(rData["hardcopyStatus"]).prop("disabled", true);
            $("#hardcopyIn").val(rData["hardcopyIn"]).prop("disabled", true);
            $("#hardcopyOut").val(rData["hardcopyOut"]).prop("disabled", true);
            $("#hardcopyLeft").val(rData["hardcopyLeft"]).prop("disabled", true);
            $("#keterangan").val(rData["keterangan"]).prop("disabled", true);
            $('[type="submit"]').remove();
            $("#mdlPmqaMonitoring").modal("show");
        });

        $("#tblPmqaMonitoring").on('click', 'a.delete', function (e) {
            e.preventDefault();
            var table = $('#tblPmqaMonitoring').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            Utility.showConfirmMessage("Data User", "Apakah anda yakin ingin menghapus data ini?", function (r) {
                if (r) {
                    deleteMonitoring(rData['idMonitoring']);
                }
            })
        });

        $("#deliverableCode").on("blur", function (e){

            $("#sectionNo").val("");
            $("#function").val("");
            $("#taskNo").val("");
            $("#task").val("");
            $("#deliverableName").val("");
            $("#scheduleInTor").val("");

            e.preventDefault();
            Utility.showBoxOverlay("formPmqaMonitoring");
            $.ajax({
                type: "GET",
                url: "/pmqa/master/get",
                data: {deliverableCode: $("#deliverableCode").val()},
                success: function (data) {
                    Utility.removeBoxOverlay();
                    if (data.code == 1) {
                        var obj = data.object;
                        $("#sectionNo").val(obj.sectionNo);
                        $("#function").val(obj.function);
                        $("#taskNo").val(obj.taskNo);
                        $("#task").val(obj.task);
                        $("#deliverableName").val(obj.deliverableName);
                        $("#scheduleInTor").val(obj.scheduleInTor);
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
        });

        $("#hardcopyStatus").on("change", function (e){
            e.preventDefault();
            if ($("#hardcopyStatus").val() == "2"){
                $("#adaHardcopy").show("slow");
            } else {
                $("#adaHardcopy").hide("slow");
            }
        });

        $("#period").inputmask("99999999", {
            clearMaskOnLostFocus: true,
            placeholder: "_"
        });

        setTimeout(function(){
            $('.dataTables_filter').addClass('pull-right');
            $('.dataTables_length').addClass('pull-left').attr("style","padding-right:1rem");
        }, 200);
    };

    var saveMonitoring = function () {
        Utility.showBoxOverlay("formPmqaMonitoring");
        var myform = $('#formPmqaMonitoring');
        // Find disabled inputs, and remove the "disabled" attribute
        var disabled = myform.find(':input:disabled').removeAttr('disabled');

        // serialize the form
        var data = myform.serialize();

        // re-disabled the set of inputs that you previously enabled
        disabled.attr('disabled','disabled');

        $.ajax({
            type: "POST",
            url: "/pmqa/monitoring/",
            data: data,
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil diubah.", function (r) {
                        loadTblMonitoring();
                        $("#mdlPmqaMonitoring").modal('hide');
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

    var deleteMonitoring = function (idMonitoring){
        Utility.showBoxOverlay("formPmqaMonitoring");
        $.ajax({
            type: "DELETE",
            url: "/pmqa/monitoring/",
            data: {"idMonitoring": idMonitoring},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil dihapus.", function (r) {
                        loadTblMonitoring();
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
            loadTblMonitoring();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function (){
    Monitoring.init();
})