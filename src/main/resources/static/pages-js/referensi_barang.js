var ReferensiBarang = function (){

    var validator;

    var listBarang = [
        {
            "idPersediaan": 1,
            "kodeBarang": "E002",
            "namaBarang": "SPX 10W30 SLMA O,8L",
            "jenisBarang": "Oli",
            "merk": "Honda",
            "keterangan": "oli motor bebek"
        },
        {
            "idPersediaan": 2,
            "kodeBarang": "J025",
            "namaBarang": "Pad Set RR",
            "jenisBarang": "Pad",
            "merk": "Honda",
            "keterangan": "Pad Set Belakang"
        },
        {
            "idPersediaan": 3,
            "kodeBarang": "S009",
            "namaBarang": "Park Plug U20EPR9S(DS)",
            "jenisBarang": "Sikring",
            "merk": "Honda",
            "keterangan": "sikring karbu"
        },
        {
            "idPersediaan": 4,
            "kodeBarang": "B014",
            "namaBarang": "Bearing Ball (Set)",
            "jenisBarang": "Bearing",
            "merk": "Honda",
            "keterangan": "-"
        },
        {
            "idPersediaan": 5,
            "kodeBarang": "O005",
            "namaBarang": "O-Ring TA PP Adj",
            "jenisBarang": "Adjustment",
            "merk": "Honda",
            "keterangan": "-"
        },
        {
            "idPersediaan": 6,
            "kodeBarang": "R020",
            "namaBarang": "Drive Chain Kit",
            "jenisBarang": "Rantai Motor",
            "merk": "Honda",
            "keterangan": "Set Rantai Motor Bebek"
        },
    ];

    var loadTblBarang = function () {
        $('#tblBarang').DataTable({
            /*ajax: {
                url: "/persediaan/getlist/",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    Utility.showBoxOverlay("tblBarang");
                },
                dataSrc: function (data) {
                    if (data.code == 0) {
                        Utility.showErrorMessage("Data Persediaan Barang", data.message);
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
            },*/
            //todo
            data: listBarang,
            processing: false,
            destroy: true,
            serverSide: false,
            columns: [
                {
                    "data": null, "className": "text-center", "width": "5%", "render": function (data, type, full, meta) {
                        return meta.settings._iDisplayStart + meta.row + 1;
                    }
                },
                {"data": "kodeBarang"},
                {"data": "namaBarang"},
                {"data": "jenisBarang"},
                {"data": "merk"},
                {"data": "keterangan"},
                {"data": null, "className": "text-center"}
            ],
            columnDefs: [
                {
                    "targets": -1, "render": function (data, type, full, meta) {
                        var btnedit = '<a href="#" class="ti-pencil btn-info btn-sm edit" title="Ubah"></a>';
                        var btnDelete = '<a href="#" class="ti-trash btn-danger btn-sm delete" title="Hapus"></a>';
                        return roleUser ? btnedit : btnedit + btnDelete;
                    }
                }
            ],
            responsive: true,
            // dom: 'Bfrtip',
            // buttons: [
            //     "copy", "csv", "excel", "pdf", "print", "colvis"
            // ],
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
                Utility.removeBoxOverlay("tblBarang");
            }
        }).buttons().container().appendTo('#tblBarang_wrapper .col-md-6:eq(0)');
    };

    var pageHandler = function (){

        // init validation
        validator = $('#formBarang').validate();

        $('#mdlBarang').on('hide.bs.modal', function () {
            validator.resetForm();
            $('#formBarang').each(function () {
                this.reset();
            });
            $(".selectpicker").selectpicker('refresh');
        });

        $("#formBarang").submit(function (e) {
            e.preventDefault();
            if (validator.form()) {
                Utility.showConfirmMessageCallback("Data Persediaan Barang", "Apakah anda yakin ingin menyimpan data ini?", function () {
                    saveBarang();
                })
            }
        });

        $('#tblBarang').on("click", "a.edit", function (e){
            e.preventDefault();
            var table = $('#tblBarang').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            $("#idPersediaan").val(rData["idPersediaan"]);
            $("#namaBarang").val(rData["namaBarang"]);
            $("#jenisBarang").val(rData["jenisBarang"]);
            $("#merk").val(rData["merk"]);
            $("#keterangan").val(rData["keterangan"]);
            $("#mdlBarang").modal("show");
        });

        $("#tblBarang").on('click', 'a.delete', function (e) {
            e.preventDefault();
            var table = $('#tblBarang').DataTable();
            var tbl = table.row($(this).parents('tr'));
            var rData = tbl.data();
            Utility.showConfirmMessageCallback("Data Persediaan Barang", "Apakah anda yakin ingin menghapus data ini?", function () {
                deleteBarang(rData['idPersediaan']);
            })
        });

        $("#btnBatal").on("click", function (e) {
            e.preventDefault();
            $("#mdlBarang").modal("hide");
        })
    };

    var saveBarang = function () {
        Utility.showBoxOverlay("formBarang");
        // var formData = $('#formBarang').serializeArray();
        var myform = $('#formBarang');
        // Find disabled inputs, and remove the "disabled" attribute
        var disabled = myform.find(':input:disabled').removeAttr('disabled');

        // serialize the form
        var formData = myform.serializeArray();

        // re-disabled the set of inputs that you previously enabled
        disabled.attr('disabled','disabled');
        var data = {};
        $(formData).each(function (index, obj) {
            data[this.name] = this.value;
        });
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
        data["kodeBarang"] = "T013";
        console.log(data);
        console.log(data["idPersediaan"]);
        if (data["idPersediaan"]==""||data["idPersediaan"]==null ||data["idPersediaan"]==undefined) {
            data["idPersediaan"]=listBarang.length+1;
        } else {
            deleteBarang(data["idPersediaan"]);
            data["idPersediaan"] == parseInt(data["idPersediaan"]);
        }
        listBarang.push(data);
        Utility.removeBoxOverlay();
        loadTblBarang();
        $("#mdlBarang").modal('hide');
        $("#btnBatal").trigger('click');
        Utility.showSuccessMessage("Sukses!", "Data Berhasil diubah.");
    };

    var deleteBarang = function (idPersediaan){
        Utility.showBoxOverlay("formBarang");
        /*$.ajax({
            type: "DELETE",
            url: "/persediaan/del/",
            data: {"idPersediaan": idPersediaan},
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    loadTblBarang();
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
        });*/
        //todo
        var i = listBarang.length;
        console.log(idPersediaan);
        console.log(i);
        while(i--){
            if( listBarang[i]
                && listBarang[i].hasOwnProperty("idPersediaan")
                && (arguments.length > 2 && listBarang[i]["idPersediaan"] == idPersediaan ) ){
                listBarang.splice(i,1);

            }
        }
        console.log(listBarang);
        Utility.removeBoxOverlay();
        loadTblBarang();
        Utility.showSuccessMessage("Sukses!", "Data Berhasil dihapus.");
    };

    return {
        init: function (){
            loadTblBarang();
            pageHandler();
        }
    }
}();
jQuery(document).ready(function (){
    ReferensiBarang.init();
});