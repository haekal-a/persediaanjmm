var AlurDok = function (){

    var listMonitoring = [];
    var listPeriod = [];

    var loadTblAlurDok = function () {
        $('#tblPmqaAlurDok').DataTable({
            ajax: {
                url: "/pmqa/alurdok/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    Utility.showBoxOverlay("tblPmqaAlurDok");
                },
                dataSrc: function (data) {
                    if (data.code === "0") {
                        Utility.showErrorMessage("Data Alur Dokumen", data.message);
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
                {"data": "idDetailDocument", "className": "all text-center"},
                {"data": "idDeliverable", "className": "all text-center"},
                {"data": "idMonitoring", "className": "all text-center"},
                {"data": "period", "className": "all text-center"},
                {"data": "version", "className": "all text-center"},
                {"data": "versionName", "className": "all text-center"},
                {"data": "tglSubmission", "className": "all text-center"},
                {"data": "suratDeloitte", "className": "all text-center"},
                {"data": "tglSurat", "className": "all text-center"},
                {"data": "statusDeliverable", "className": "all", "render": function (data, type, full, meta) {
                        if (data == "1") {
                            return "Dalam Proses QA";
                        } else if (data == "2") {
                            return "Perbaikan oleh Deloitte";
                        } else if (data == "3") {
                            return "Disetujui Steerco";
                        } else {
                            return data;
                        }
                    }},
                {"data": "statusPembayaran", "className": "all", "render": function (data, type, full, meta) {
                        if (data == "1") {
                            return "Belum Dapat Dibayarkan";
                        } else if (data == "2") {
                            return "Belum Ditagihkan";
                        } else if (data == "3") {
                            return "Proses Pembayaran";
                        } else if (data == "4") {
                            return "Sudah Dibayar";
                        } else {
                            return data;
                        }
                    }},
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
                Utility.removeBoxOverlay("tblPmqaAlurDok");
            }
        }).buttons().container().appendTo('#tblPmqaAlurDok_wrapper .col-md-6:eq(0)');
    };

    var getListMonitoring = function (){
        Utility.showBoxOverlay("formPmqaAlurDok");
        $.ajax({
            type: "GET",
            url: "/pmqa/monitoring/getlist",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    var listDeliverableCode = [];
                    $("#idDeliverable").empty();
                    $("#idDeliverable").append('<option value="" selected="" disabled>Pilih</option>');
                    if (obj.length > 0){
                        listMonitoring = obj;
                        $.each(obj, function (){
                            if (listDeliverableCode.indexOf(this.deliverableCode)==-1){
                                $("#idDeliverable").append('<option value="'+this.deliverableCode+'">'+this.deliverableCode+'</option>');
                                listDeliverableCode.push(this.deliverableCode);
                            }
                        });
                        $("#idDeliverable").selectpicker('refresh');
                    }
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data deliverable "+message);
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

    var pageHandler = function (){

        if (roleUser){
            $(".card-tools").empty();
        }

        // init validation
        var validator = $('#formPmqaAlurDok').validate();

        $('#mdlPmqaAlurDok').on('hide.bs.modal', function () {
            validator.resetForm();
            $('#formPmqaAlurDok').each(function () {
                this.reset();
            });
            // $("#idDeliverable").removeAttr("disabled");
            $("#idDeliverable").prop("disabled", false).selectpicker('refresh');
            $("#period").prop("disabled", false).selectpicker('refresh');
            // $("#idDeliverable").selectpicker('refresh');
            // $("#period").selectpicker('refresh');
            // getListMonitoring();
        });

        $("#formPmqaAlurDok").submit(function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessage("Data Monitoring", "Apakah anda yakin ingin menyimpan data ini?", function (r) {
                    if (r) {
                        saveAlurDok();
                    }
                })
            }
        });

        $('#tblPmqaAlurDok').on("click", "a.edit", function (e){
            e.preventDefault();
            var table = $('#tblPmqaAlurDok').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#idDetailDocument").val(rData["idDetailDocument"]);
            $("#idDeliverable").val(rData["idDeliverable"]);
            $("#idDeliverable").selectpicker('refresh');
            $("#idDeliverable").prop("disabled", true);
            $("#idMonitoring").val(rData["idMonitoring"]);
            $("#period").empty();
            $("#period").append('<option value="'+rData["period"]+'">'+rData["period"]+'</option>');
            $("#period").val(rData["period"]);
            $("#period").selectpicker('refresh');
            $("#period").prop("disabled", true);
            $("#version").val(rData["version"]);
            $("#versionName").val(rData["versionName"]);
            $("#suratDeloitte").val(rData["suratDeloitte"]);
            $("#tglSurat").datepicker("setDate", Utility.formatTanggalToString(rData["tglSurat"]));
            $("#tglSubmission").datepicker("setDate", Utility.formatTanggalToString(rData["tglSubmission"]));
            $("#ndPenerusanPpk").val(rData["ndPenerusanPpk"]);
            $("#tglNd").datepicker("setDate", Utility.formatTanggalToString(rData["tglNd"]));
            $("#flagPsiap").val(rData["flagPsiap"]);
            $("#ndPsiap").val(rData["ndPsiap"]);
            $("#tglNdPsiap").datepicker("setDate", Utility.formatTanggalToString(rData["tglNdPsiap"]));
            $("#baSteerco").val(rData["baSteerco"]);
            $("#tglBaSteerco").datepicker("setDate", Utility.formatTanggalToString(rData["tglBaSteerco"]));
            $("#noApproveSteerco").val(rData["noApproveSteerco"]);
            $("#tglApprove").datepicker("setDate", Utility.formatTanggalToString(rData["tglApprove"]));
            $("#sPemberitahuanPpk").val(rData["sPemberitahuanPpk"]);
            $("#tglS").datepicker("setDate", Utility.formatTanggalToString(rData["tglS"]));
            $("#baKemajuan").val(rData["baKemajuan"]);
            $("#tglBaKemajuan").datepicker("setDate", Utility.formatTanggalToString(rData["tglBaKemajuan"]));
            $("#bast").val(rData["bast"]);
            $("#tglBast").datepicker("setDate", Utility.formatTanggalToString(rData["tglBast"]));
            $("#baPembayaran").val(rData["baPembayaran"]);
            $("#tglBaPembayaran").datepicker("setDate", Utility.formatTanggalToString(rData["tglBaPembayaran"]));
            $("#tagihan").val(rData["tagihan"]);
            $("#tglTagihan").datepicker("setDate", Utility.formatTanggalToString(rData["tglTagihan"]));
            $("#ndPermohonanBayar").val(rData["ndPermohonanBayar"]);
            $("#tglNdPermohonanBayar").datepicker("setDate", Utility.formatTanggalToString(rData["tglNdPermohonanBayar"]));
            $("#spp").val(rData["spp"]);
            $("#tglSpp").datepicker("setDate", Utility.formatTanggalToString(rData["tglSpp"]));
            $("#spm").val(rData["spm"]);
            $("#tglSpm").datepicker("setDate", Utility.formatTanggalToString(rData["tglSpm"]));
            $("#sp2D").val(rData["sp2D"]);
            $("#tglSp2D").datepicker("setDate", Utility.formatTanggalToString(rData["tglSp2D"]));
            $("#statusDeliverable").val(rData["statusDeliverable"]);
            $("#statusPembayaran").val(rData["statusPembayaran"]);
            $("#mdlPmqaAlurDok").modal("show");
        });

        $('#tblPmqaAlurDok').on("click", "a.view", function (e){
            e.preventDefault();
            var table = $('#tblPmqaAlurDok').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#idDetailDocument").val(rData["idDetailDocument"]).prop("disabled", true);
            $("#idDeliverable").val(rData["idDeliverable"]);
            $("#idDeliverable").selectpicker('refresh');
            $("#idDeliverable").prop("disabled", true);
            $("#idMonitoring").val(rData["idMonitoring"]);
            $("#period").empty();
            $("#period").append('<option value="'+rData["period"]+'">'+rData["period"]+'</option>');
            $("#period").val(rData["period"]);
            $("#period").selectpicker('refresh');
            $("#period").prop("disabled", true);
            $("#version").val(rData["version"]).prop("disabled", true);
            $("#versionName").val(rData["versionName"]).prop("disabled", true);
            $("#suratDeloitte").val(rData["suratDeloitte"]).prop("disabled", true);
            $("#tglSurat").datepicker("setDate", Utility.formatTanggalToString(rData["tglSurat"])).prop("disabled", true);
            $("#tglSubmission").datepicker("setDate", Utility.formatTanggalToString(rData["tglSubmission"])).prop("disabled", true);
            $("#ndPenerusanPpk").val(rData["ndPenerusanPpk"]).prop("disabled", true);
            $("#tglNd").datepicker("setDate", Utility.formatTanggalToString(rData["tglNd"])).prop("disabled", true);
            $("#flagPsiap").val(rData["flagPsiap"]).prop("disabled", true);
            $("#ndPsiap").val(rData["ndPsiap"]).prop("disabled", true);
            $("#tglNdPsiap").datepicker("setDate", Utility.formatTanggalToString(rData["tglNdPsiap"])).prop("disabled", true);
            $("#baSteerco").val(rData["baSteerco"]).prop("disabled", true);
            $("#tglBaSteerco").datepicker("setDate", Utility.formatTanggalToString(rData["tglBaSteerco"])).prop("disabled", true);
            $("#noApproveSteerco").val(rData["noApproveSteerco"]).prop("disabled", true);
            $("#tglApprove").datepicker("setDate", Utility.formatTanggalToString(rData["tglApprove"])).prop("disabled", true);
            $("#sPemberitahuanPpk").val(rData["sPemberitahuanPpk"]).prop("disabled", true);
            $("#tglS").datepicker("setDate", Utility.formatTanggalToString(rData["tglS"])).prop("disabled", true);
            $("#baKemajuan").val(rData["baKemajuan"]).prop("disabled", true);
            $("#tglBaKemajuan").datepicker("setDate", Utility.formatTanggalToString(rData["tglBaKemajuan"])).prop("disabled", true);
            $("#bast").val(rData["bast"]).prop("disabled", true);
            $("#tglBast").datepicker("setDate", Utility.formatTanggalToString(rData["tglBast"])).prop("disabled", true);
            $("#baPembayaran").val(rData["baPembayaran"]).prop("disabled", true);
            $("#tglBaPembayaran").datepicker("setDate", Utility.formatTanggalToString(rData["tglBaPembayaran"])).prop("disabled", true);
            $("#tagihan").val(rData["tagihan"]).prop("disabled", true);
            $("#tglTagihan").datepicker("setDate", Utility.formatTanggalToString(rData["tglTagihan"])).prop("disabled", true);
            $("#ndPermohonanBayar").val(rData["ndPermohonanBayar"]).prop("disabled", true);
            $("#tglNdPermohonanBayar").datepicker("setDate", Utility.formatTanggalToString(rData["tglNdPermohonanBayar"])).prop("disabled", true);
            $("#spp").val(rData["spp"]).prop("disabled", true);
            $("#tglSpp").datepicker("setDate", Utility.formatTanggalToString(rData["tglSpp"])).prop("disabled", true);
            $("#spm").val(rData["spm"]).prop("disabled", true);
            $("#tglSpm").datepicker("setDate", Utility.formatTanggalToString(rData["tglSpm"])).prop("disabled", true);
            $("#sp2D").val(rData["sp2D"]).prop("disabled", true);
            $("#tglSp2D").datepicker("setDate", Utility.formatTanggalToString(rData["tglSp2D"])).prop("disabled", true);
            $("#statusDeliverable").val(rData["statusDeliverable"]).prop("disabled", true);
            $("#statusPembayaran").val(rData["statusPembayaran"]).prop("disabled", true);
            $('[type="submit"]').remove();
            $("#mdlPmqaAlurDok").modal("show");
        });

        $("#tblPmqaAlurDok").on('click', 'a.delete', function (e) {
            e.preventDefault();
            var table = $('#tblPmqaAlurDok').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            Utility.showConfirmMessage("Data User", "Apakah anda yakin ingin menghapus data ini?", function (r) {
                if (r) {
                    deleteAlurDok(rData['idDetailDocument']);
                }
            })
        });

        $("#idDeliverable").on("change", function (e){
            e.preventDefault();
            $("#idMonitoring").val("");
            $("#version").val("");
            $("#period").val("");
            $("#period").empty();
            $("#period").append('<option value="" selected="" disabled>Pilih</option>');
            listPeriod = [];
            $.each(listMonitoring, function (){
                if (this.deliverableCode === $("#idDeliverable").val()){
                    $("#period").append('<option value="'+this.period+'">'+this.period+'</option>');
                    listPeriod.push(this);
                }
            });
            $("#period").selectpicker('refresh');
        });

        $("#period").on("change", function (e){
            e.preventDefault();
            $.each(listPeriod, function (){
                if (this.period == $("#period").val()){
                    $("#idMonitoring").val(this.idMonitoring);
                    getLastVersion(this.idMonitoring, this.period);
                }
            });
        });

        setTimeout(function(){
            $('.dataTables_filter').addClass('pull-right');
            $('.dataTables_length').addClass('pull-left').attr("style","padding-right:1rem");
        }, 200);
    };

    var getLastVersion = function (idMonitoring, period){
        Utility.showBoxOverlay("formPmqaAlurDok");
        $.ajax({
            type: "GET",
            url: "/pmqa/alurdok/getlist",
            data: {"idMonitoring": idMonitoring, "period": period},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    if (obj.length>0) {
                        obj.sort(function (a, b) {
                            return b.version - a.version;
                        });
                        $("#version").val(obj[0].version + 1);
                    } else {
                        $("#version").val(1);
                    }
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data detail document "+message);
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

    var saveAlurDok = function () {
        Utility.showBoxOverlay("formPmqaAlurDok");
        var myform = $('#formPmqaAlurDok');
        // Find disabled inputs, and remove the "disabled" attribute
        var disabled = myform.find(':input:disabled').removeAttr('disabled');

        // serialize the form
        var formData = myform.serializeArray();

        // re-disabled the set of inputs that you previously enabled
        disabled.attr('disabled','disabled');

        var data = {};
        $(formData).each(function (index, obj) {
            if (this.name.indexOf("tgl") != -1) {
                data[this.name] = Utility.formatTanggalToDate(this.value);
            } else {
                data[this.name] = this.value;
            }
        });

        var fd = new FormData();
        fd.append('model', new Blob([JSON.stringify(data)], {type: "application/json"}));
        $.each($('input[type=file]')[0].files, function(i, file) {
            if (file.length !== 0){
                fd.append('file'+$('input[type=file]').prop('id'), file);
            }
        });
        // fd.append('fileUploadSurat', $("#uploadSurat")[0].files[0]);
        // fd.append('fileUploadNd', $('#uploadNd')[0].files[0]);
        // fd.append('fileUploadNdPsiap', $('#uploadNdPsiap')[0].files[0]);
        // fd.append('fileUploadBaSteerco', $('#uploadBaSteerco')[0].files[0]);
        // fd.append('fileUploadPersetujuanSteerco', $('#uploadPersetujuanSteerco')[0].files[0]);
        // fd.append('fileUploadSPemberitahuanPPK', $('#uploadSPemberitahuanPPK')[0].files[0]);
        // fd.append('fileUploadBaKemajuan', $('#uploadBaKemajuan')[0].files[0]);
        // fd.append('fileUploadBast', $('#uploadBast')[0].files[0]);
        // fd.append('fileUploadBaPembayaran', $('#uploadBaPembayaran')[0].files[0]);
        // fd.append('fileUploadTagihan', $('#uploadTagihan')[0].files[0]);
        // fd.append('fileUploadNdPermohonanBayar', $('#uploadNdPermohonanBayar')[0].files[0]);
        // fd.append('fileUploadSpp', $('#uploadSpp')[0].files[0]);
        // fd.append('fileUploadSpm', $('#uploadSpm')[0].files[0]);
        // fd.append('fileUploadSp2d', $('#uploadSp2d')[0].files[0]);

        $.ajax({
            type: "POST",
            url: "/pmqa/alurdok/",
            data: fd,
            processData: false,
            contentType: false,
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil diubah.", function (r) {
                        loadTblAlurDok();
                        $("#mdlPmqaAlurDok").modal('hide');
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

    var deleteAlurDok = function (idDetailDocument){
        Utility.showBoxOverlay("formPmqaAlurDok");
        $.ajax({
            type: "DELETE",
            url: "/pmqa/alurdok/",
            data: {"idDetailDocument": idDetailDocument},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil dihapus.", function (r) {
                        loadTblAlurDok();
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
            loadTblAlurDok();
            getListMonitoring();
            pageHandler();
        }
    }

}();
jQuery(document).ready(function (){
    AlurDok.init();
})