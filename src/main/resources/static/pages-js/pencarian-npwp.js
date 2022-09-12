var PencarianNpwp = function (){
    var pageHandler = function (){
        $('#btnCari').on("click", function (){
            var npwp = $('#txtNpwp').val().replace(/_/g, '').replace('-', '').replace(/\./gi, '');
            if (npwp.length > 14) {
                    getDataWp(npwp);
            } else {
                Utility.showErrorMessage("Cari NPWP", "NPWP harus 15 digit");
            }
        })
    }

    var getDataWp = function (npwp) {
        // Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/datawp/get",
            data: {"npwp": npwp},
            success: function (data) {
                // Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessage("Sukses", "Mantap kal!!");
                    console.log(data.object);
                    $("#lblNpwp").text(data.object.npwp15);
                    $("#txtJenisWp").val(data.object.jenisWp);
                    $("#txtNama").val(data.object.namaWp);
                    $("#txtTglLahir").val(data.object.tanggalLahir);
                    $("#txtAlamat").val(data.object.alamat);
                    $("#txtTglDaftar").val(data.object.tanggalDaftar);
                    $("#txtKelurahan").val(data.object.kelurahan);
                    $("#txtStatusWp").val(data.object.statusWp);
                    $("#txtKecamatan").val(data.object.kecamatan);
                    $("#txtNipAr").val(data.object.nipAr);
                    $("#txtKota").val(data.object.kota);
                    $("#txtNamaAr").val(data.object.nipAr);
                    $("#txtPropinsi").val(data.object.propinsi);
                    $("#txtKdPos").val(data.object.kodePos);
                    $("#txtNoTelp").val(data.object.nomorTelepon);
                    $("#txtNoFax").val(data.object.nomorFax);
                    $("#txtEmail").val(data.object.email);
                    $("#txtNoIdentitas").val(data.object.nomorIdentitas);
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "NPWP tidak ditemukan " + message);
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
                Utility.showErrorMessageCallback('Terjadi kesalahan!', msg, function (r) {
                    // Utility.removeBoxOverlay();
                });
            }
        });
    }
    return{
        init: function (){
            pageHandler();
        }
    }
}();

jQuery(document).ready(function (){
    PencarianNpwp.init();
})