var Transaksi = function (){

    var validator;

    function pageHandler() {
        //todo
        $("#txtJenisBarang").val("Oli");
        $("#txtMerk").val("Castrol");
        $("#txtJumlahBarang").val(12);
        $("#txtHargaSatuan").val(54000);

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
                    $("#ddlBarang").empty();
                    $("#ddlBarang").append('<option value="" selected="">Pilih</option>');
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

    var saveTransaksi = function () {
        Utility.showBoxOverlay("frmTransaksi");
        var data = {};
        /*$.ajax({
            type: "POST",
            url: "/persediaan/save/",
            data: data,
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    loadTblBarang();
                    $("#mdlBarang").modal('hide');
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
        });*/
        // todo
        Utility.removeBoxOverlay();
        $("#btnBatal").trigger('click');
        Utility.showSuccessMessage("Sukses!", "Data Berhasil diubah.");
    };

    return {
        init: function (){
            getListBarang();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function (){
    Transaksi.init();
});