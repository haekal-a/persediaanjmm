var PencarianNpwp = function (){
    var listPegawai = [];
    var pageHandler = function () {
        // init validation
        var validator = $('#frmProfilWp').validate({
            /*rules: {
                txtNoTelp: {
                    digits: true
                },
                txtNoFax: {
                    digits: true
                },
                txtNoIdentitas: {
                    digits: true
                },
                txtKdPos: {
                    digits: true
                }
            }*/
        });

        $('#frmProfilWp .angka').each(function(){
            $(this).rules( "add", {
                digits: true
            });
        });

        $('#frmProfilWp input').each(function(){
            $(this).rules( "add", {
                checkForWhiteSpaceErrors: true
            });
        });

        $('#divProfilWp').hide();
        $('#divActionEdit').hide();
        $('#divAction').hide();
        $(".ubah").prop('disabled', 'disabled');

        $('#btnCari').on("click", function () {
            $('#divActionEdit').hide();
            $('#divAction').show();
            $(".ubah").prop('disabled', 'disabled');
            var npwp = $('#txtNpwp').val().replace(/_/g, '').replace('-', '').replace(/\./gi, '');
            if (npwp.length > 14) {
                getDataWp(npwp);
            } else {
                $('#divProfilWp').hide();
                $('#frmProfilWp').each(function () {
                    this.reset();
                });
                Utility.showErrorMessage("Cari NPWP", "NPWP harus 15 digit");
            }
        });

        $("#btnEdit").on("click", function () {
            $(".ubah").removeAttr('disabled');
            $('#divAction').hide();
            $('#divActionEdit').show();
        });

        $("#btnSubmit").on("click", function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessageCallback("Profil Wajib Pajak", "Apakah anda yakin ingin menyimpan data ini?", function (r) {
                    if (r) {
                        save();
                    }
                })
            }
        });

        $("#btnCancel").on("click", function () {
            $("#txtNpwp").val($("#txtNpwp1").val());
            $("#btnCari").trigger('click');
            $('#frmProfilWp input').each(function (){
                $(this).removeClass('is-invalid');
            })
        });

        $("#btnNext").on("click", function () {
            $("#mdlPegawai").modal("show");
            $("#txtIdWpKunjungan").val($("#txtIdWp").val());
        });

        $("#ddlPegawai").on("change", function (){
            $.each(listPegawai, function (){
                if (this.id == $("#ddlPegawai").val()){
                    $("#txtNip").val(this.nip);
                    $("#txtJabatan").val(this.jabatan);
                    $("#txtUnit").val(this.unit);
                }
            });
        });

        $("#btnSubmitKunjungan").on("click", function (e){
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessageCallback("Kunjungan Wajib Pajak", "Apakah anda yakin ingin menyimpan data ini?", function (r) {
                    if (r) {
                        saveKunjungan();
                    }
                })
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
                    $('#divProfilWp').show('slow');
                    $("#txtIdWp").val(data.object.id);
                    $("#txtNpwp1").val(data.object.npwp15);
                    $("#txtJenisWp").val(data.object.jenisWp);
                    $("#txtNama").val(data.object.namaWp);
                    $("#txtTglLahir").val(Utility.formatTanggalToString(data.object.tanggalLahir));
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
    };

    var save = function () {
        // Utility.showBoxOverlay("frmProfilWp");
        var myform = $('#frmProfilWp');
        var data = {
            "id": $("#txtIdWp").val(),
            "alamat": $("#txtAlamat").val(),
            "kelurahan": $("#txtKelurahan").val(),
            "kecamatan": $("#txtKecamatan").val(),
            "kota": $("#txtKota").val(),
            "propinsi": $("#txtPropinsi").val(),
            "kodePos": $("#txtKdPos").val(),
            "nomorTelepon": $("#txtNoTelp").val(),
            "nomorFax": $("#txtNoFax").val(),
            "email": $("#txtEmail").val(),
            "noIdentitas": $("#txtNoIdentitas").val(),
            "tglLahir": Utility.formatTanggalToDate($("#txtTglLahir").val())
        }
        $.ajax({
            type: "POST",
            url: "/datawp/save",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                // Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.downloadExcel(data.object.newData, "data baru.xls");
                    Utility.downloadExcel(data.object.oldData, "data lama.xls");
                    Utility.showSuccessMessageCallback("Sukses!", "Data Berhasil diubah.", function (r) {
                        if (r) {
                            $("#txtNpwp").val($("#txtNpwp1").val());
                            $("#btnCari").trigger('click');
                        }
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
                    // Utility.removeBoxOverlay();
                });
            }
        });
    };

    var getListPegawai = function () {
        // Utility.showBoxOverlay("formPmqaAlurDok");
        $.ajax({
            type: "GET",
            url: "/pegawai/getlist",
            success: function (data) {
                // Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    listPegawai = obj;
                    $("#ddlPegawai").empty();
                    $("#ddlPegawai").append('<option value="" selected="" disabled>Pilih</option>');
                    $.each(obj, function () {
                        $("#ddlPegawai").append('<option value="' + this.id + '">' + this.nama + '</option>');
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
                Utility.showErrorMessage('Terjadi kesalahan!', msg, function (r) {
                    Utility.removeBoxOverlay();
                });
            }
        });
    };

    var saveKunjungan = function () {
        // Utility.showBoxOverlay("frmProfilWp");
        var myform = $('#frmProfilWp');
        var data = {
            "idWp": $("#txtIdWpKunjungan").val(),
            "idPegawai": $("#ddlPegawai").val(),
            "nip": $("#txtNip").val(),
            "jabatan": $("#txtJabatan").val(),
            "unit": $("#txtUnit").val(),
            "keperluan": $("#txtKeperluan").val(),
        }
        $.ajax({
            type: "POST",
            url: "/kunjungan/save",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                // Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessageCallback("Sukses!", "Data Berhasil disimpan.", function (r) {
                        if (r) {
                            window.location.href = "/";
                        }
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
                    // Utility.removeBoxOverlay();
                });
            }
        });
    };

    return {
        init: function () {
            pageHandler();
            getListPegawai();
        }
    }
}();

jQuery(document).ready(function (){
    PencarianNpwp.init();
})