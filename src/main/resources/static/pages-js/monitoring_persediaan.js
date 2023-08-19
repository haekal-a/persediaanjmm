var MonitoringPersediaan = function () {

    var listPersediaan = [
        {
            "idPersediaan": 1,
            "idTransaksi": 1,
            "kodeBarang": "E002",
            "namaBarang": "SPX 10W30 SLMA O,8L",
            "jenisBarang": "Oli",
            "merk": "Honda",
            "keterangan": "oli motor bebek",
            "hargaSatuan": "50.000",
            "hargaPerolehan": "1.200.000",
            "jumlahBarang": 24,
            "kodeTransaksi": "MSK230811001",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Putra Jaya",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "50.000",
            "jumlahBarangTrx": 25,
            "sisaBarang": 25,
        },
        {
            "idPersediaan": 2,
            "idTransaksi": 2,
            "kodeBarang": "J025",
            "namaBarang": "Pad Set RR",
            "jenisBarang": "Pad",
            "merk": "Honda",
            "keterangan": "Pad Set Belakang",
            "hargaSatuan": "48.000",
            "hargaPerolehan": "240.000",
            "jumlahBarang": 5,
            "kodeTransaksi": "MSK230811002",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Putra Jaya",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "48.000",
            "jumlahBarangTrx": 5,
            "sisaBarang": 5,
        },
        {
            "idPersediaan": 3,
            "idTransaksi": 3,
            "kodeBarang": "S009",
            "namaBarang": "Park Plug U20EPR9S(DS)",
            "jenisBarang": "Sikring",
            "merk": "Honda",
            "keterangan": "sikring karbu",
            "hargaSatuan": "9.000",
            "hargaPerolehan": "261.000",
            "jumlahBarang": 29,
            "kodeTransaksi": "MSK230811003",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Putra Jaya",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "9.000",
            "jumlahBarangTrx": 30,
            "sisaBarang": 30,
        },
        /*{
            "idPersediaan": 4,
            "idTransaksi": 5,
            "kodeBarang": "B014",
            "namaBarang": "Bearing Ball (Set)",
            "jenisBarang": "Bearing",
            "merk": "Honda",
            "keterangan": "-",
            "hargaSatuan": "40.000",
            "hargaPerolehan": "800.000",
            "jumlahBarang": 20,
            "kodeTransaksi": "MSK230811004",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Mandiri Makmur",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "40.000",
            "jumlahBarangTrx": 20,
            "sisaBarang": 20,
        },*/
        {
            "idPersediaan": 5,
            "idTransaksi": 6,
            "kodeBarang": "O005",
            "namaBarang": "O-Ring TA PP Adj",
            "jenisBarang": "Adjustment",
            "merk": "Honda",
            "keterangan": "-",
            "hargaSatuan": "7.500",
            "hargaPerolehan": "300.000",
            "jumlahBarang": 40,
            "kodeTransaksi": "MSK230811005",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Mandiri Makmur",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "7.500",
            "jumlahBarangTrx": 40,
            "sisaBarang": 40,
        },
        {
            "idPersediaan": 6,
            "idTransaksi": 8,
            "kodeBarang": "R020",
            "namaBarang": "Drive Chain Kit",
            "jenisBarang": "Rantai Motor",
            "merk": "Honda",
            "keterangan": "Set Rantai Motor Bebek",
            "hargaSatuan": "93.000",
            "hargaPerolehan": "930.000",
            "jumlahBarang": 10,
            "kodeTransaksi": "MSK230811006",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Mandiri Makmur",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "93.000",
            "jumlahBarangTrx": 10,
            "sisaBarang": 10,
        },
    ];

    var loadTblPersediaan = function () {
        $('#tblPersediaan').DataTable({
            ajax: {
                url: "/barang/getmonitoring/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    data.param1 = $("#ddlJenisBarang").val();
                    data.param2 = $('#ddlBarang').val();
                    data.param3 = $("#ddlMerk").val();
                    data.param4 = $("#txtJmlAwal").val();
                    data.param5 = $("#txtJmlAkhir").val();
                    data.param6 = $("#txtHargaAwal").val();
                    data.param7 = $("#txtHargaAkhir").val();
                    Utility.showBoxOverlay("tblPersediaan");
                },
                dataSrc: function (data) {
                    if (data.code == 0) {
                        Utility.showErrorMessage("Data Transaksi Barang", data.message);
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
            //todo
            // data: listPersediaan,
            processing: false,
            destroy: true,
            serverSide: false,
            columns: [
                {
                    "data": null,
                    "className": "text-center",
                    "width": "5%",
                    "render": function (data, type, full, meta) {
                        return meta.settings._iDisplayStart + meta.row + 1;
                    }
                },
                {"data": "kodeBarang"},
                {"data": "namaBarang"},
                {"data": "jenisBarang"},
                {"data": "merk"},
                {"data": "keterangan"},
                {"data": "hargaSatuan"},
                {"data": "jumlahBarang"},
                {"data": "hargaPerolehan"},
                {"data": null, "className": "text-center"}
            ],
            columnDefs: [
                {
                    "targets": -1, "render": function (data, type, full, meta) {
                        return  '<a href="'+ctx+'/transaksibrg/'+full['idPersediaan']+'" class="btn btn-outline-primary btn-rounded btn-icon input" title="Input Transaksi"><i class="ti-share"></i> </a>';
                    }
                },
                /*{
                    "targets": 1, "render": function (data, type, full, meta) {
                        if (full['jumlahBarang'] < 6) {
                            return '<a href="#" class="badge badge-danger">Barang Masuk</a>';
                        } else if (full['jumlahBarang'] < 11) {
                            return '<a href="#" class="badge badge-warning">Barang Keluar</a>';
                        } else {
                            return data
                        }
                    }
                },*/
            ],
            "createdRow": function (row, data, dataIndex) {
                if (data['jumlahBarang'] < 6) {
                    $(row).addClass('table-danger');
                } else if (data['jumlahBarang'] < 11) {
                    $(row).addClass('table-warning');
                }
            },
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
            ordering: true,
            searching: true,
            pagingType: "numbers",
            language: {
                url: "/vendors/datatables/indonesian.json",
            },
            drawCallback: function (settings) {

            },
            initComplete: function (settings, json) {
                Utility.removeBoxOverlay("tblPersediaan");
            }
        }).buttons().container().appendTo('#tblPersediaan_wrapper .col-md-6:eq(0)');
    };

    var pageHandler = function () {
        $("#btnFilterUp").hide();
        $("#frmPersediaan").hide();

        $('#btnFilterDown').on('click', function () {
            $('#frmPersediaan').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
            $("#btnFilterDown").hide();
            $("#btnFilterUp").show();
            $("#frmPersediaan").show('slow');
        });

        $('#btnFilterUp').on('click', function () {
            $("#btnFilterDown").show();
            $("#btnFilterUp").hide();
            $("#frmPersediaan").hide('slow');
            $('#frmPersediaan').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
            loadTblPersediaan()
        });

        $('#btnReset').on('click', function () {
            $('#frmPersediaan').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
        });

        $('#btnCari').on('click', function () {
            loadTblPersediaan();
        });

        /*$('#tblPersediaan').on("click", "a.input", function (e) {
            e.preventDefault();
            var table = $('#tblPersediaan').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#idTransaksi").val(rData["idTransaksi"]);
            // todo
        });*/

        $("#btnBatal").on("click", function (e) {
            e.preventDefault();
            $("#mdlBarang").modal("hide");
        })
    };

    var getListBarang = function () {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/transaksi/getlistbarang",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    $("#ddlBarang").empty();
                    $.each(obj, function () {
                        $("#ddlBarang").append('<option value="' + this.namaBarang + '">' + this.namaBarang + '</option>');
                    });
                    $("#ddlBarang").selectpicker('refresh');
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data Barang " + message);
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

    var getListJenis = function () {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/transaksi/getalljenisbarang",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    $("#ddlJenisBarang").empty();
                    $.each(obj, function () {
                        $("#ddlJenisBarang").append('<option value="' + this + '">' + this + '</option>');
                    });
                    $("#ddlJenisBarang").selectpicker('refresh');
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data Barang " + message);
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
        //todo
        /*$("#ddlJenisBarang").empty();
        $("#ddlJenisBarang").append('<option value="" selected="" disabled>Pilih</option>');
        $("#ddlJenisBarang").append('<option value="Oli">Oli</option>');
        $("#ddlJenisBarang").append('<option value="Pad">Pad</option>');
        $("#ddlJenisBarang").append('<option value="Sikring">Sikring</option>');
        $("#ddlJenisBarang").append('<option value="Bearing">Bearing</option>');
        $("#ddlJenisBarang").append('<option value="Adjustment">Adjustment</option>');
        $("#ddlJenisBarang").append('<option value="Rantai Motor">Rantai Motor</option>');*/
    };

    var getListMerk = function () {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/barang/getallmerk",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    $("#ddlMerk").empty();
                    $.each(obj, function () {
                        $("#ddlMerk").append('<option value="' + this + '">' + this + '</option>');
                    });
                    $("#ddlMerk").selectpicker('refresh');
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data Barang " + message);
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
        //todo
        /*$("#ddlMerk").empty();
        $("#ddlMerk").append('<option value="" selected="" disabled>Pilih</option>');
        $("#ddlMerk").append('<option value="Honda">Honda</option>');*/
    };

    return {
        init: function () {
            getListJenis();
            getListBarang();
            getListMerk();
            loadTblPersediaan();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function () {
    MonitoringPersediaan.init();
});