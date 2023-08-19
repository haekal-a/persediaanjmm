var Utility = function (){

    var utilityHandler = function (){
        $(".toggle-password").click(function() {

            $(this).toggleClass("glyphicon-eye-open glyphicon-eye-close");
            var input = $($(this).attr("toggle"));
            if (input.attr("type") == "password") {
                input.attr("type", "text");
            } else {
                input.attr("type", "password");
            }
        });

        $(".npwp").inputmask("99.999.999.9-999.999", {
            clearMaskOnLostFocus: true,
            placeholder: "_"
        });

        $(".datepicker").inputmask("99-99-9999", {
            clearMaskOnLostFocus: true,
        });

        //Date picker
        $('.datepicker').datepicker({
            format: "dd-mm-yyyy",
            endDate: "+0d",
            autoclose: true
        }).on('hide', function(e) {
            e.stopPropagation();
        });

        var attr = $("form").prop("id");
        if (typeof attr !== 'undefined' && attr !== false) {
            setDefaultValidation();
            localizeValidationMessage();
        }

        var bsFile = $(".custom-file-input").prop("id");
        if (typeof bsFile !== 'undefined' && bsFile !== false){
            bsCustomFileInput.init()
        }

        // Initialize selectpicker Elements
        var selectpicker = $('.selectpicker').prop("id");
        if (typeof selectpicker !== 'undefined' && selectpicker !== false) {
            $('.selectpicker').selectpicker();
        }
    }

    var formatNpwp = function (npwp) {

        var npwpString = npwp.substring(0, 2) + '.' +
            npwp.substring(2, 5) + '.' + npwp.substring(5, 8) + '.' +
            npwp.substring(8, 9) + '-' + npwp.substring(9, 12) + '.' +
            npwp.substring(12, 15);

        return npwpString;
    };

    var formatTanggalToString = function (data) {
        if (typeof data === "string") {
            var ptrn = /(\d{4})\-(\d{2})\-(\d{2})/;
            return data.replace(ptrn, '$3-$2-$1');
        } else if (typeof data === "number") {
            var date = new Date(data); // Date milisecond
            var year = date.getFullYear();
            var month = ("0" + (date.getMonth() + 1)).slice(-2);
            var day = ("0" + date.getDate()).slice(-2);
            return day + "-" + month + "-" + year;
        } else {
            return data;
        }
    };

    const formatRupiah = function (number){
        return new Intl.NumberFormat("id-ID", {
            style: "currency",
            currency: "IDR"
        }).format(number);
    }


    var formatTanggalToDate = function (ddmmyyyy) {
        if (ddmmyyyy === null || ddmmyyyy === "" || typeof ddmmyyyy == "undefined") {
            return;
        }
        var strDate = ddmmyyyy.split('-');
        var dd = parseInt(strDate[0], 10);
        var mm = parseInt(strDate[1], 10) - 1;
        var yy = parseInt(strDate[2], 10);
        return new Date(yy, mm, dd);
    };

    var showSuccessMessage = function (t, m){
        Swal.fire(t, m, "success")
    }

    var showErrorMessage = function (t, m){
        Swal.fire(t, m, "error");
    }

    var showSuccessMessageCallback = function (t, m, callback){
        Swal.fire({
            title: t,
            text: m,
            icon: "success",
            showCancelButton: false,
            confirmButtonText: "OK",
            allowOutsideClick: false
        }).then(function (result) {
            if (result.isConfirmed) {
                callback();
            }
        });
    }

    var showErrorMessageCallback = function (t, m, callback){
        Swal.fire({
            title: t,
            text: m,
            icon: "error",
            showCancelButton: false,
            confirmButtonText: "OK",
            allowOutsideClick: false
        }).then(function(result) {
            if (result.isConfirmed) {
                callback();
            }
        });
    }

    var showConfirmMessageCallback = function (t, m, callback){
        Swal.fire({
            title: t,
            text: m,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Ya",
            cancelButtonText: "Tidak",
            reverseButtons: false
        }).then(function(result) {
            if (result.isConfirmed) {
                callback();
            }
        });
    }

    var showBoxOverlay = function (target) {
        var boxOverlay = '<div class="overlay">\n' +
            // '                        <div class="loader"></div>\n' +
            '                        <div class="loader"></div>\n' +
            '                    </div>';
        if (target == null || target == ""){
            $("body").append(boxOverlay);
        } else {
            $('#' + target).append(boxOverlay);
        }
    }

    var removeBoxOverlay = function (target) {
        if (target == null || target == ""){
            $('.overlay').remove();
        } else {
            $('#' + target).find('.overlay').remove();
        }
    }

    var setDefaultValidation = function (){

        $.validator.setDefaults({
            ignore: ":hidden",
            onfocusout: false,
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.parent().append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });

        $.validator.addMethod("checkForWhiteSpaceErrors", function (value, element, param) {
            var elem = $(element);
            var val = elem.val();
            if (val && $.trim(val) == '') {
                return false;
            }
            return true;
        }, "Isian ini tidak boleh spasi.");
    };

    var localizeValidationMessage = function(){

        //belum semua di translate
        jQuery.extend(jQuery.validator.messages, {
            required: "Isian ini harus diisi.",
            remote: "Silahkan perbaiki isian ini.",
            email: "silahkan masukkan alamat email valid.",
            url: "Silahkan masukkan URL valid.",
            date: "Silahkan masukkan format tanggal valid.",
            dateISO: "Please enter a valid date (ISO).",
            number: "Please enter a valid number.",
            digits: "Isian ini harus diisi dengan angka.",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            accept: "Silahkan masukkan type file valid.",
            maxlength: jQuery.validator.format("Isian tidak boleh lebih dari {0} karakter."),
            minlength: jQuery.validator.format("Isian tidak boleh kurang dari {0} karakter."),
            rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
            range: jQuery.validator.format("Please enter a value between {0} and {1}."),
            max: jQuery.validator.format("Silahkan masukkan nilai kurang dari atau sama dengan {0}."),
            min: jQuery.validator.format("Silahkan masukkan nilai lebih dari atau sama dengan {0}.")
        });
    };

    /**
     * Creates an anchor element `<a></a>` with
     * the base64 pdf source and a filename with the
     * HTML5 `download` attribute then clicks on it.
     * @param  {string} base64
     * @param  {string} fileName
     * @return {void}
     */
    var downloadExcel = function (base64, fileName) {
        const linkSource = `data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,${base64}`;
        const downloadLink = document.createElement("a");

        downloadLink.href = linkSource;
        downloadLink.download = fileName;
        downloadLink.click();
    }

    return{
        init: function (){
            utilityHandler();
        },
        showSuccessMessage: function (t, m){
            showSuccessMessage(t, m)
        },
        showErrorMessage: function (t, m){
            showErrorMessage(t, m)
        },
        showSuccessMessageCallback: function (t, m, callback){
            showSuccessMessageCallback(t, m, callback)
        },
        showErrorMessageCallback: function (t, m, callback){
            showErrorMessageCallback(t, m, callback)
        },
        showConfirmMessageCallback: function (t, m, callback){
            showConfirmMessageCallback(t, m, callback)
        },
        showBoxOverlay: function (target){
            showBoxOverlay(target)
        },
        removeBoxOverlay: function (target){
            removeBoxOverlay(target)
        },
        setDefaultValidation: function (){
            setDefaultValidation()
        },
        localizeValidationMessage: function (){
            localizeValidationMessage()
        },
        formatNpwp: function (npwp) {
            return formatNpwp(npwp);
        },
        formatTanggalToString: function (data){
            return formatTanggalToString(data)
        },
        formatRupiah: function (data){
            return formatRupiah(data)
        },
        formatTanggalToDate: function (data){
            return formatTanggalToDate(data)
        },
        downloadExcel: function (base64, fileName){
            return downloadExcel(base64, fileName)
        }
    }
}();
jQuery(document).ready(function (){
    Utility.init();
});