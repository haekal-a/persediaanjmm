var Transaksi = function (){

    function pageHandler() {
        $("#txtJenisBarang").val("Oli");
        $("#txtMerk").val("Castrol");
        $("#txtJumlahBarang").val(12);
        $("#txtHargaSatuan").val(54000);
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