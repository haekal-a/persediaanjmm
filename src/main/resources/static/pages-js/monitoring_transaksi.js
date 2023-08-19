var MonitoringTransaksi = function (){

    var listTransaksi = [
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
            "jumlahBarang": "24",
            "kodeTransaksi": "MSK230811001",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Putra Jaya",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "50.000",
            "jumlahBarangTrx": "25",
            "sisaBarang": "25",
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
            "jumlahBarang": "5",
            "kodeTransaksi": "MSK230811002",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Putra Jaya",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "48.000",
            "jumlahBarangTrx": "5",
            "sisaBarang": "5",
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
            "jumlahBarang": "29",
            "kodeTransaksi": "MSK230811003",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Putra Jaya",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "9.000",
            "jumlahBarangTrx": "30",
            "sisaBarang": "30",
        },
        /*{
            "idPersediaan": 1,
            "idTransaksi": 4,
            "kodeBarang": "E002",
            "namaBarang": "SPX 10W30 SLMA O,8L",
            "jenisBarang": "Oli",
            "merk": "Honda",
            "keterangan": "oli motor bebek",
            "hargaSatuan": "50.000",
            "hargaPerolehan": "1.200.000",
            "jumlahBarang": "24",
            "kodeTransaksi": "KLR230811001",
            "jenisTransaksi": "2",
            "tanggalTransaksi": "11-08-2023",
            "title": "Deni",
            "keteranganTrx": "service dan ganti oli",
            "hargaSatuanTrx": "58.000",
            "jumlahBarangTrx": "1",
            "sisaBarang": "24",
        },*/
        {
            "idPersediaan": 4,
            "idTransaksi": 5,
            "kodeBarang": "B014",
            "namaBarang": "Bearing Ball (Set)",
            "jenisBarang": "Bearing",
            "merk": "Honda",
            "keterangan": "-",
            "hargaSatuan": "40.000",
            "hargaPerolehan": "800.000",
            "jumlahBarang": "20",
            "kodeTransaksi": "MSK230811004",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Mandiri Makmur",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "40.000",
            "jumlahBarangTrx": "20",
            "sisaBarang": "20",
        },
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
            "jumlahBarang": "40",
            "kodeTransaksi": "MSK230811005",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Mandiri Makmur",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "7.500",
            "jumlahBarangTrx": "40",
            "sisaBarang": "40",
        },
        /*{
            "idPersediaan": 3,
            "idTransaksi": 7,
            "kodeBarang": "S009",
            "namaBarang": "Park Plug U20EPR9S(DS)",
            "jenisBarang": "Sikring",
            "merk": "Honda",
            "keterangan": "sikring karbu",
            "hargaSatuan": "9.000",
            "hargaPerolehan": "261.000",
            "jumlahBarang": "29",
            "kodeTransaksi": "KLR230811002",
            "jenisTransaksi": "2",
            "tanggalTransaksi": "11-08-2023",
            "title": "Deni",
            "keteranganTrx": "service ganti sikring",
            "hargaSatuanTrx": "15.000",
            "jumlahBarangTrx": "1",
            "sisaBarang": "29",
        },*/
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
            "jumlahBarang": "10",
            "kodeTransaksi": "MSK230811006",
            "jenisTransaksi": "1",
            "tanggalTransaksi": "11-08-2023",
            "title": "PT. Mandiri Makmur",
            "keteranganTrx": "pembelian barang",
            "hargaSatuanTrx": "93.000",
            "jumlahBarangTrx": "10",
            "sisaBarang": "10",
        },
    ];

    var loadTblTransaksi = function () {
        $('#tblTransaksi').DataTable({
            ajax: {
                url: "/transaksi/getlist/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    data.param1 = $("#ddlJenisTransaksi").val();
                    data.param2 = $('#ddlJenisBarang').val();
                    data.param3 = $("#ddlBarang").val();
                    data.param4 = $("#txtTglAwal").val();
                    data.param5 = $("#txtTglAkhir").val();
                    Utility.showBoxOverlay("tblTransaksi");
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
            // data: listTransaksi,
            processing: false,
            destroy: true,
            serverSide: false,
            columns: [
                {
                    "data": null, "className": "text-center", "width": "5%", "render": function (data, type, full, meta) {
                        return meta.settings._iDisplayStart + meta.row + 1;
                    }
                },
                {"data": "kodeTransaksi"},
                {"data": "namaBarang"},
                {"data": "jenisTransaksi"},
                {"data": "tanggalTransaksi"},
                {"data": "title"},
                {"data": "keteranganTrx"},
                {"data": "hargaSatuanTrx"},
                {"data": "jumlahBarangTrx"},
                {"data": "sisaBarang"},
                {"data": null, "className": "text-center"}
            ],
            columnDefs: [
                {
                    "targets": -1, "render": function (data, type, full, meta) {

                        var btnedit = '<a href="'+ctx+'/transaksi/'+full['idTransaksi']+'" class="ti-pencil btn-info btn-sm edit" title="Ubah"></a>';
                        var btnDelete = '<a href="#" class="ti-trash btn-danger btn-sm delete" title="Hapus"></a>';
                        return roleUser ? btnedit : btnedit + btnDelete;
                    }
                },
                {
                    "targets": 2, "render": function (data, type, full, meta) {
                        return '<a href="#" class="detail" title="Detail Barang">'+data+'</a>';
                    }
                },
                {
                    "targets": 3, "render": function (data, type, full, meta) {
                        if (data == "1"){
                            return '<label class="badge badge-opacity-success">Barang Masuk</label>';
                        } else if (data == "2"){
                            return '<label class="badge badge-opacity-warning">Barang Keluar</label>';
                        }
                    }
                },
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
                Utility.removeBoxOverlay("tblTransaksi");
            }
        }).buttons().container().appendTo('#tblTransaksi_wrapper .col-md-6:eq(0)');
    };

    var pageHandler = function (){
        $("#btnFilterUp").hide();
        $("#frmTransaksi").hide();

        $('#btnFilterDown').on('click', function () {
            $('#frmTransaksi').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
            $("#btnFilterDown").hide();
            $("#btnFilterUp").show();
            $("#frmTransaksi").show('slow');
        });

        $('#btnFilterUp').on('click', function () {
            $("#btnFilterDown").show();
            $("#btnFilterUp").hide();
            $("#frmTransaksi").hide('slow');
            $('#frmTransaksi').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
            loadTblTransaksi()
        });

        $('#btnReset').on('click', function () {
            $('#frmTransaksi').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
        });

        $('#btnCari').on('click', function () {
            loadTblTransaksi();
        });

        $('#tblTransaksi').on("click", "a.detail", function (e){
            e.preventDefault();
            var table = $('#tblTransaksi').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#namaBarang").val(rData["namaBarang"]);
            $("#kodeBarang").val(rData["kodeBarang"]);
            $("#jenisBarang").val(rData["jenisBarang"]);
            $("#merk").val(rData["merk"]);
            $("#keterangan").val(rData["keterangan"]);
            $("#jumlahBarang").val(rData["jumlahBarang"]);
            $("#hargaSatuan").val(rData["hargaSatuan"]);
            $("#hargaPerolehan").val(rData["hargaPerolehan"]);
            $("#mdlBarang").modal('show');

        });

        /*$('#tblTransaksi').on("click", "a.edit", function (e){
            e.preventDefault();
            var table = $('#tblTransaksi').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#idTransaksi").val(rData["idTransaksi"]);
            // todo
        });*/

        $("#tblTransaksi").on('click', 'a.delete', function (e) {
            e.preventDefault();
            var table = $('#tblTransaksi').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            Utility.showConfirmMessageCallback("Data Transaksi Barang", "Apakah anda yakin ingin menghapus data ini?", function () {
                deleteTransaksi(rData['idTransaksi']);
            })
        });

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

    var deleteTransaksi = function (idTransaksi){
        Utility.showBoxOverlay("tblTransaksi");
        $.ajax({
            type: "DELETE",
            url: "/transaksi/del/",
            data: {"idTransaksi": idTransaksi},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    loadTblTransaksi();
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
        //todo
        /*var i = listTransaksi.length;
        console.log(idTransaksi);
        console.log(i);
        while(i--){
            if( listTransaksi[i]
                && listTransaksi[i].hasOwnProperty("idTransaksi")
                && (arguments.length > 2 && listTransaksi[i]["idTransaksi"] == idTransaksi ) ){
                listTransaksi.splice(i,1);

            }
        }
        console.log(listTransaksi);
        Utility.removeBoxOverlay();
        loadTblTransaksi();
        Utility.showSuccessMessage("Sukses!", "Data Berhasil dihapus.");*/
    };

    return {
        init: function (){
            loadTblTransaksi();
            getListJenis();
            getListBarang();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function (){
    MonitoringTransaksi.init();
});