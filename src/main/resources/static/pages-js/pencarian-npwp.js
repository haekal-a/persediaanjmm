var PencarianNpwp = function (){
    var listPegawai = [];
    var listKlu = [];
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
            validator = $('#frmProfilWp').validate();
            $(".ubah").removeAttr('disabled');
            $(".ubah").removeClass('disabled');
            $('#divAction').hide();
            $('#divActionEdit').show();
            $('.selectpicker').selectpicker('refresh');
        });

        $("#btnSubmit").on("click", function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessageCallback("Profil Wajib Pajak", "Apakah anda yakin ingin menyimpan data ini?", function () {
                    save();
                })
            } else {
                Utility.showErrorMessage("Error!", "Terdapat isian yang belum sesuai.")
            }
        });

        $("#btnCancel").on("click", function () {
            validator.resetForm();
            $("#txtNpwp").val($("#txtNpwp1").val());
            $("#btnCari").trigger('click');
            $('#frmProfilWp input').each(function (){
                $(this).removeClass('is-invalid');
            })
        });

        $("#btnNext").on("click", function () {
            $("#mdlPegawai").modal("show");
            $("#txtIdWpKunjungan").val($("#txtIdWp").val());
            $("#txtNpwpKunjungan").val($("#txtNpwp1").val().replace(/_/g, '').replace('-', '').replace(/\./gi, ''))
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

        $("#ddlKlu").on("change", function (){
            $.each(listKlu, function (){
                if (this.klu == $("#ddlKlu").val()){
                    $("#txtKeteranganKlu").val(this.uraian);
                }
            });
        });

        $("#btnSubmitKunjungan").on("click", function (e){
            e.preventDefault();
            if($("#ddlPegawai").val() == null){
                Utility.showErrorMessage("Kunjungan Wajib Pajak", "Anda belum memilih pegawai");
                return;
            }
            if (validator.form()) {
                Utility.showConfirmMessageCallback("Kunjungan Wajib Pajak", "Apakah anda yakin ingin menyimpan data ini?", function () {
                    saveKunjungan();
                })
            } else {
                Utility.showErrorMessage("Error!", "Terdapat isian yang belum sesuai.")
            }
        });

        $('#mdlPegawai').on('show.bs.modal', function () {
            validator = $('#frmKunjungan').validate();
        });

        $('#mdlPegawai').on('hide.bs.modal', function () {
            validator.resetForm();
            $('#frmKunjungan').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
        });

        $("#btnBatal").on("click", function (e) {
            e.preventDefault();
            $("#mdlPegawai").modal("hide");
        })
    }

    var getDataWp = function (npwp) {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/datawp/get",
            data: {"npwp": npwp},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    if (data.object == null){
                        Utility.showErrorMessage("Terjadi Kesalahan!", "NPWP tidak ditemukan");
                        $('#divProfilWp').hide();
                        return;
                    }
                    if(data.object.jenisWp == "OP"){
                        $(".op").show();
                    } else {
                        $(".op").hide();
                    }
                    $('#divProfilWp').show('slow');
                    $("#txtIdWp").val(data.object.id);
                    $("#txtNpwp1").val(data.object.npwp15);
                    $("#txtJenisWp").val(data.object.jenisWp);
                    $("#txtNama").val(data.object.namaWp);
                    $("#txtTglLahir").val(Utility.formatTanggalToString(data.object.tanggalLahir));
                    $("#txtAlamat").val(data.object.alamat);
                    // $("#txtTglDaftar").val(data.object.tanggalDaftar);
                    $("#txtKelurahan").val(data.object.kelurahan);
                    // $("#txtStatusWp").val(data.object.statusWp);
                    $("#txtKecamatan").val(data.object.kecamatan);
                    // $("#txtNipAr").val(data.object.nipAr);
                    $("#txtKota").val(data.object.kota);
                    // $("#txtNamaAr").val(data.object.nipAr);
                    $("#txtPropinsi").val(data.object.propinsi);
                    $("#txtKdPos").val(data.object.kodePos);
                    $("#txtNoTelp").val(data.object.nomorTelepon);
                    $("#txtNoFax").val(data.object.nomorFax);
                    $("#txtEmail").val(data.object.email);
                    $("#txtNoIdentitas").val(data.object.nomorIdentitas);
                    $("#ddlKlu").val(data.object.kodeKlu).trigger('change');
                    $("#txtKeteranganKlu").val(data.object.namaKlu);
                    // $('.selectpicker').selectpicker('refresh');
                    // preselected(data.object.kodeKlu);
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", message);
                    $('#divProfilWp').hide();
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

    var save = function () {
        Utility.showBoxOverlay("frmProfilWp");
        var myform = $('#frmProfilWp');
        var npwp = $("#txtNpwp").val().replace(/_/g, '').replace('-', '').replace(/\./gi, '');
        var data = {
            "id": $("#txtIdWp").val(),
            "npwp15": npwp,
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
            "tglLahir": Utility.formatTanggalToDate($("#txtTglLahir").val()),
            "klu": $("#ddlKlu").val(),
            "keteranganKlu": $("#txtKeteranganKlu").val()
        }
        $.ajax({
            type: "POST",
            url: "/datawp/save",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.downloadExcel(data.object.newData, "Data Baru_"+npwp+".xls");
                    Utility.downloadExcel(data.object.oldData, "Data Lama_"+npwp+".xls");
                    Utility.showSuccessMessageCallback("Sukses!", "Data Berhasil diubah.", function () {
                        $("#txtNpwp").val($("#txtNpwp1").val());
                        $("#btnCari").trigger('click');
                    });
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
                Utility.showErrorMessage('Terjadi kesalahan!', msg);
                Utility.removeBoxOverlay();
            }
        });
    };

    var getListKlu = function () {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/datawp/getlistklu",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    listKlu = obj;
                    $("#ddlKlu").empty();
                    $("#ddlKlu").append('<option value="" selected="" disabled>Pilih</option>');
                    $.each(obj, function () {
                        $("#ddlKlu").append('<option value="' + this.klu + '">' + this.klu + ' - ' + this.uraian + '</option>');
                    });
                    // $("#ddlKlu").selectpicker('refresh');
                    $('.js-data-example-ajax').select2();
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data KLU " + message);
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

    function getListKluRemote () {
        $('.js-data-example-ajax').select2({
            ajax: {
                url: '/datawp/getlistallklu',
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        q: params.term, // search term
                        page: params.page
                    };
                },
                processResults: function (data, params) {
                    // parse the results into the format expected by Select2
                    // since we are using custom formatting functions we do not need to
                    // alter the remote JSON data, except to indicate that infinite
                    // scrolling can be used
                    params.page = params.page || 1;

                    return {
                        results: data.object,
                        pagination: {
                            more: (params.page * 30) < data.object.length
                        }
                    };
                },
                transport: function (params, success, failure) {
                    var $request = $.ajax(params);

                    $request.then(success);
                    $request.fail(failure);

                    return $request;
                },
                cache: true
            },
            width: 'resolve',
            placeholder: 'Cari data klu',
            minimumInputLength: 1,
            templateResult: formatRepo,
            templateSelection: formatRepoSelection
        });
    }

    function formatRepo (repo) {
        if (repo.loading) {
            return repo.text;
        }

        var $container = $(
            "<div class='select2-result-repository clearfix'>" +
            "<div class='select2-result-repository__meta'>" +
            "<div class='select2-result-repository__title'></div>" +
            "<div class='select2-result-repository__description'></div>" +
            "</div>" +
            "</div>"
        );

        $container.find(".select2-result-repository__title").text(repo.klu);
        $container.find(".select2-result-repository__description").text(repo.uraian);

        return $container;
    }

    function formatRepoSelection (repo) {
        return repo.klu || repo.uraian;
    }

    function preselected (q) {
        // Fetch the preselected item, and add to the control
        var ddlKlu = $('.js-data-example-ajax');
        $.ajax({
            type: 'GET',
            url: '/datawp/getlistklu?q=' + q
        }).then(function (data) {
            // create the option and append to Select2
            var option = new Option(data.object.klu, data.object.klu, true, true);
            ddlKlu.append(option).trigger('change');

            // manually trigger the `select2:select` event
            ddlKlu.trigger({
                type: 'select2:select',
                params: {
                    data: data
                }
            });
        });
    }

    var saveKunjungan = function () {
        Utility.showBoxOverlay("frmProfilWp");
        var myform = $('#frmProfilWp');
        var data = {
            "idWp": $("#txtIdWpKunjungan").val(),
            "idPegawai": $("#ddlPegawai").val(),
            "nip": $("#txtNip").val(),
            "jabatan": $("#txtJabatan").val(),
            "unit": $("#txtUnit").val(),
            "keperluan": $("#txtKeperluan").val(),
            "npwp": $("#txtNpwpKunjungan").val(),
        }
        $.ajax({
            type: "POST",
            url: "/kunjungan/save",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    Utility.showSuccessMessageCallback("Sukses!", "Data Berhasil disimpan.", function (){
                        window.location.href = "/";
                    });
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

    return {
        init: function () {
            pageHandler();
            getListKlu();
            getListPegawai();
        }
    }
}();

jQuery(document).ready(function (){
    PencarianNpwp.init();
})