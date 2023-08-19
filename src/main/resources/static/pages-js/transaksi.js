var Transaksi = function (){

    var listBarang = [];
    var lastTransaction;
    var validator;

    function pageHandler() {

        // init validation
        validator = $('#frmTransaksi').validate();

        $("#frmTransaksi").submit(function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessageCallback("Data Transaksi Barang", "Apakah anda yakin ingin menyimpan data ini?", function () {
                    saveTransaksi();
                })
            }
        });

        $('#btnBatal').on('click', function () {
            validator.resetForm();
            $('#frmTransaksi').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
            getListBarang();
        });

        $("#ddlBarang").on("change", function (){
            $.each(listBarang, function (){
                if (this.idPersediaan == $("#ddlBarang").val()){
                    $("#idPersediaan").val(this.idPersediaan);
                    $("#txtJenisBarang").val(this.jenisBarang);
                    $("#txtMerk").val(this.merk);
                    $("#txtJumlahBarang").val(this.jumlahBarang);
                    $("#txtHargaSatuan").val(this.hargaSatuan);
                }
            });
        });

        $("#txtTanggalTransaksi").on("change", function (){
            getLastTransaction($("#txtTanggalTransaksi").val());
        });

        $("#ddlJenisTransaksi").on("change", function (){
            if ($("#txtTanggalTransaksi").val()!="") getLastTransaction($("#txtTanggalTransaksi").val());
        });

        $("#txtjmlBarang").on("keyup", function (){
            var jml = $("#txtjmlBarang").val();
            var hrg = $("#txtHrgSatuan").val();
            $("#txtTotal").val(jml * hrg);
        });

        $("#txtHrgSatuan").on("keyup", function (){
            var jml = $("#txtjmlBarang").val();
            var hrg = $("#txtHrgSatuan").val();
            $("#txtTotal").val(jml * hrg);
        });
    }

    var getListBarang = function () {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/transaksi/getlistbarang",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    listBarang = obj;
                    $("#ddlBarang").empty();
                    $("#ddlBarang").append('<option value="" selected="">Pilih</option>');
                    $.each(obj, function () {
                        $("#ddlBarang").append('<option value="' + this.idPersediaan + '">' + this.namaBarang + '</option>');
                    });
                    $("#ddlBarang").selectpicker('refresh');
                    if ($("#idPersediaan").val()!=""){
                        $("#ddlBarang").val($("#idPersediaan").val()).trigger('change');
                    }
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

    var getLastTransaction = function (tgl) {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/transaksi/getlasttrx",
            data: {"tgl": tgl},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    lastTransaction = data.object;
                    if ($("#ddlJenisTransaksi").val() == "1"){
                        $("#txtKodeTransaksi").val("MSK"+lastTransaction);
                    } else {
                        $("#txtKodeTransaksi").val("KLR"+lastTransaction);
                    }

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

    var getTransaksiById = function (idTransaksi) {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/transaksi/getbyid",
            data: {"idTransaksi": idTransaksi},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var trx = data.object;
                    $("#ddlBarang").val(trx.idPersediaan).trigger('change');
                    $("#ddlJenisTransaksi").val(trx.jenisTransaksi).trigger('change');
                    $("#txtTanggalTransaksi").val(Utility.formatTanggalToString(trx.tanggalTransaksi));
                    $("#txtKodeTransaksi").val(trx.kodeTransaksi);
                    $("#txtTitle").val(trx.title);
                    $("#txtKeterangan").val(trx.keterangan);
                    $("#txtjmlBarang").val(trx.jumlahBarang).trigger('keyup');
                    $("#txtHrgSatuan").val(trx.hargaSatuan).trigger('keyup');
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

    var saveTransaksi = function () {
        Utility.showBoxOverlay("frmTransaksi");
        var data = {
            "idTransaksi":$("#idTransaksi").val(),
            "idPersediaan":$("#idPersediaan").val(),
            "ddlJenisTransaksi":$("#ddlJenisTransaksi").val(),
            "tanggalTransaksi":$("#txtTanggalTransaksi").val(),
            "kodeTransaksi":$("#txtKodeTransaksi").val(),
            "title":$("#txtTitle").val(),
            "keterangan":$("#txtKeterangan").val(),
            "jumlahBarang":$("#txtjmlBarang").val(),
            "hargaSatuan":$("#txtHrgSatuan").val(),
        };
        $.ajax({
            type: "POST",
            url: "/transaksi/save/",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    $("#btnBatal").trigger('click');
                    Utility.showSuccessMessage("Sukses!", "Data Berhasil disimpan.");
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
        // todo
        /*Utility.removeBoxOverlay();
        $("#btnBatal").trigger('click');
        Utility.showSuccessMessage("Sukses!", "Data Berhasil disimpan.");*/
    };

    return {
        init: function (){
            getListBarang();
            pageHandler();
            if ($("#idTransaksi").val()!=""){
                getTransaksiById($("#idTransaksi").val());
            }
        }
    }
}();
jQuery(document).ready(function (){
    Transaksi.init();
});