var DaftarKunjungan = function (){

    var loadTblKunjungan = function () {
        $('#tblKunjungan').DataTable({
            ajax: {
                url: "/kunjungan/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    data.param1 = $("#txtNamaWp").val();
                    data.param2 = $('#txtNpwp').val().replace(/_/g, '').replace('-', '').replace(/\./gi, '');
                    data.param3 = $("#ddlPegawai").val();
                    data.param4 = $("#txtTglAwal").val();
                    data.param5 = $("#txtTglAkhir").val();
                    data.param6 = $("#ddlStatus").val();
                    Utility.showBoxOverlay("tblKunjungan");
                },
                dataSrc: function (data) {
                    if (data.code == 0) {
                        Utility.showErrorMessage("Get List Kunjungan", data.message);
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
                {"data": "namaWp"},
                {"data": "npwp"},
                {"data": "namaPegawai"},
                {"data": "nip"},
                {"data": "jabatan"},
                {"data": "unit"},
                {"data": "keperluan"},
                {"data": "tanggal"},
                {"data": "status"},
                {"data": "keterangan"},
                {"data": null, "className": "text-center"}
            ],
            columnDefs: [
                {
                    "targets": 2, "render": function (data, type, full, meta) {
                        return Utility.formatNpwp(data);
                    }
                },
                {
                    "targets": 9, "render": function (data, type, full, meta) {
                        if (data == "Menunggu"){
                            return '<label class="badge badge-warning">'+data+'</label>';
                        } else if (data == "Selesai"){
                            return '<label class="badge badge-success">'+data+'</label>';
                        } else if (data == "Batal"){
                            return '<label class="badge badge-danger">'+data+'</label>';
                        }
                    }
                },
                {
                    "targets": -1, "render": function (data, type, full, meta) {
                        var btnDone = '<a href="#" class="ti-check btn-success btn-sm done" title="Selesai"></a>';
                        var btnCancel = '<a href="#" class="ti-close btn-danger btn-sm cancel" title="Batal"></a>';
                        return full['status']==="Menunggu"? btnDone + btnCancel : "-";
                    }
                }
            ],
            responsive: false,
            // dom: 'Bfrtip',
            // buttons: [
            //     "copy", "csv", "excel", "pdf", "print", "colvis"
            // ],
            scrollY: true,
            scrollX: true,
            scrollCollapse: true,
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
                Utility.removeBoxOverlay("tblKunjungan");
            }
        }).buttons().container().appendTo('#tblKunjungan_wrapper .col-md-6:eq(0)');
    };

    var pageHandler = function (){
        $("#btnFilterUp").hide();
        $("#frmKunjungan").hide();

        $('#btnFilterDown').on('click', function () {
            $('#frmKunjungan').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
            $("#btnFilterDown").hide();
            $("#btnFilterUp").show();
            $("#frmKunjungan").show('slow');
        });

        $('#btnFilterUp').on('click', function () {
            $("#btnFilterDown").show();
            $("#btnFilterUp").hide();
            $("#frmKunjungan").hide('slow');
            $('#frmKunjungan').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
            loadTblKunjungan()
        });

        $('#btnReset').on('click', function () {
            $('#frmKunjungan').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
        });

        $('#btnCari').on('click', function () {
            loadTblKunjungan();
        });

        $('#mdlBatal').on('hide.bs.modal', function () {
            $("#ket").val("");
        });

        $('#tblKunjungan').on("click", "a.cancel", function (e){
            e.preventDefault();
            var table = $('#tblKunjungan').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#id").val(rData["idKunjungan"]);
            $("#mdlBatal").modal("show");
        });

        $("#tblKunjungan").on('click', 'a.done', function (e) {
            e.preventDefault();
            var table = $('#tblKunjungan').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            Utility.showConfirmMessageCallback("Daftar Kunjungan", "Apakah anda yakin ingin memperbaharui data ini?", function () {
                updateKunjungan(rData['idKunjungan'], "2", "");
            })
        });

        $("#btnUpdate").on("click", function (e) {
            e.preventDefault();
            if ($("#ket").val()===""){
                Utility.showErrorMessage("Daftar Kunjungan", "Keterangan belum diisi.");
            } else {
                Utility.showConfirmMessageCallback("Daftar Kunjungan", "Apakah anda yakin ingin memperbaharui data ini?", function () {
                    updateKunjungan($("#id").val(), "3", $("#ket").val());
                    $("#mdlBatal").modal("hide");
                })
            }
        })

        $("#btnBatal").on("click", function (e) {
            e.preventDefault();
            $("#mdlBatal").modal("hide");
        })
    };

    var updateKunjungan = function (id, status, ket){
        Utility.showBoxOverlay("formUser");
        $.ajax({
            type: "POST",
            url: "/kunjungan/update/",
            data: {
                "id": id,
                "status": status,
                "ket": ket
            },
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil diperbaharui.");
                    loadTblKunjungan();
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

    var getListPegawai = function () {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/pegawai/getlist",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    $("#ddlPegawai").empty();
                    $("#ddlPegawai").append('<option value="" selected="">Pilih</option>');
                    $.each(obj, function () {
                        $("#ddlPegawai").append('<option value="' + this.nip + '">' + this.nama + '</option>');
                    });
                    $("#ddlPegawai").selectpicker('refresh');
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data Pegawai " + message);
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
            loadTblKunjungan();
            getListPegawai();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function (){
    DaftarKunjungan.init();
});