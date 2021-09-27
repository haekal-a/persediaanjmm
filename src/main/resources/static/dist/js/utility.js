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

        $('[data-toggle="tooltip"]').tooltip();

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
        }

        var bsFile = $(".custom-file-input");
        if (typeof bsFile !== 'undefined' && bsFile !== false){
            bsCustomFileInput.init()
        }

        // Initialize selectpicker Elements
        $('.selectpicker').selectpicker();
    }

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

    var showSuccessMessage = function (t, m, callback){
        swal.fire({
            title: t,
            text: m,
            icon: "success",
            confirmButtonText: "Ok",
        }).then(function(result) {
            callback(result.value);
        });
    }

    var showErrorMessage = function (t, m, callback){
        swal.fire({
            title: t,
            text: m,
            icon: "error",
            confirmButtonText: "Ok",
        }).then(function(result) {
            callback(result.value);
        });
    }

    var showConfirmMessage = function (t, m, callback){
        swal.fire({
            title: t,
            text: m,
            icon: "warning",
            confirmButtonText: "Ya",
            showCancelButton: true,
            cancelButtonText: "Batal",
            allowOutsideClick: false
        }).then(function(result) {
            callback(result.value);
        });
    }

    var showBoxOverlay = function (target) {
        var boxOverlay = '<div class="overlay">\n' +
            '                        <i class="fas fa-2x fa-sync-alt fa-spin"></i>\n' +
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

        jQuery.validator.setDefaults({
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

        jQuery.validator.addMethod("checkForWhiteSpaceErrors", function (value, element, param) {
            var elem = $(element);
            var val = elem.val();
            if (val && $.trim(val) == '') {
                return false;
            }
            return true;
        }, "Isian ini harus diisi.");
    }

    return{
        init: function (){
            utilityHandler();
        },
        showSuccessMessage: function (t, m, callback){
            showSuccessMessage(t, m, callback)
        },
        showErrorMessage: function (t, m, callback){
            showErrorMessage(t, m, callback)
        },
        showConfirmMessage: function (t, m, callback){
            showConfirmMessage(t, m, callback)
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
        formatTanggalToString: function (data){
            return formatTanggalToString(data)
        },
        formatTanggalToDate: function (data){
            return formatTanggalToDate(data)
        }
    }
}();
jQuery(document).ready(function (){
    Utility.init();
});