var Dashboard = function (){
    var newPieChart = function (chart, label, data, backgroundColor){
        new Chart(chart, {
            type: 'pie',
            data: {
                labels: label,
                datasets: [{
                    backgroundColor: backgroundColor,
                    data: data
                }]
            },
            options: {
                maintainAspectRatio : false,
                responsive : true,
                title: {
                    display: true,
                    text: "Jumlah Data yang Ditampilkan adalah Jumlah Data Sudah Disubmit oleh PT Deloitte Consulting"
                }
            }
        });
    }

    var addData = function (chart, label, data, backgroundColor) {
        chart.data.labels.push(label);
        chart.data.datasets.forEach((dataset) => {
            dataset.data.push(data);
            dataset.backgroundColor.push(backgroundColor);
        });
        chart.update();
    }

    var getDashboard = function (){
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/pmqa/dashboard/piechart",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    var obj = data.object;
                    var backgroundColorDeliverable = ['#f39c12', '#00c0ef', '#00a65a'];
                    var backgroundColorPayment = ['#f56954', '#00c0ef', '#f39c12', '#00a65a'];
                    newPieChart("deliverableChart", obj.deliverableLabel, obj.deliverableData, backgroundColorDeliverable);
                    newPieChart("paymentChart", obj.paymentLabel, obj.paymentData, backgroundColorPayment);
                } else {
                    var message = data.message;
                    Utility.showErrorMessage("Terjadi Kesalahan!", "Gagal mengambil data "+message);
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

    var loadTblDeliverable = function () {
        $('#tblDeliverable').DataTable({
            ajax: {
                url: "/pmqa/dashboard/deliverable",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    Utility.showBoxOverlay("tblDeliverable");
                },
                dataSrc: function (data) {
                    if (data.code === "0") {
                        Utility.showErrorMessage("Data Monitoring", data.message);
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
            processing: false,
            destroy: true,
            serverSide: false,
            columns: [
                {"data": "process", "className": "all text-center"},
                {"data": "m1", "className": "all text-center"},
                {"data": "m2", "className": "all text-center"},
                {"data": "m3", "className": "all text-center"},
                {"data": "m4", "className": "all text-center"},
                {"data": "m5", "className": "all text-center"},
                {"data": "m6", "className": "all text-center"},
                {"data": "m7", "className": "all text-center"},
                {"data": "m8", "className": "all text-center"},
                {"data": "m9", "className": "all text-center"},
                {"data": "m10", "className": "all text-center"},
                {"data": "m11", "className": "all text-center"},
                {"data": "m12", "className": "all text-center"},
                {"data": "m13", "className": "all text-center"},
                {"data": "m14", "className": "all text-center"},
                {"data": "m15", "className": "all text-center"},
                {"data": "m16", "className": "all text-center"},
                {"data": "m17", "className": "all text-center"},
                {"data": "m18", "className": "all text-center"},
                {"data": "m19", "className": "all text-center"},
                {"data": "m20", "className": "all text-center"},
                {"data": "m21", "className": "all text-center"},
                {"data": "m22", "className": "all text-center"},
                {"data": "m23", "className": "all text-center"},
                {"data": "m24", "className": "all text-center"},
                {"data": "m25", "className": "all text-center"},
                {"data": "m26", "className": "all text-center"},
                {"data": "m27", "className": "all text-center"},
                {"data": "m28", "className": "all text-center"},
                {"data": "m29", "className": "all text-center"},
                {"data": "m30", "className": "all text-center"},
                {"data": "m31", "className": "all text-center"},
                {"data": "m32", "className": "all text-center"},
                {"data": "m33", "className": "all text-center"},
                {"data": "m34", "className": "all text-center"},
                {"data": "m35", "className": "all text-center"},
                {"data": "m36", "className": "all text-center"},
                {"data": "total", "className": "all text-center"},
            ],
            columnDefs: [],
            responsive: {
                details: false
            },
            dom: 'Blfrtip',
            buttons: [
                "copy", "excel"
            ],

            scrollX: true,
            lengthChange: false,
            lengthMenu: [5, 10, 20, 50],
            ordering: false,
            searching: false,
            info: false,
            paging: false,
            // pagingType: "numbers",
            language: {
                url: "/plugins/datatables/indonesian.json",
            },
            drawCallback: function (settings) {

            },
            initComplete: function (settings, json) {
                Utility.removeBoxOverlay("tblDeliverable");
            }
        }).buttons().container().appendTo('#tblDeliverable_wrapper .col-md-6:eq(0)');
    };

    var loadTblPayment = function () {
        $('#tblPayment').DataTable({
            ajax: {
                url: "/pmqa/dashboard/payment",
                type: "GET",
                data: function (data) {
                    data.draw = 1;
                    Utility.showBoxOverlay("tblPayment");
                },
                dataSrc: function (data) {
                    if (data.code === "0") {
                        Utility.showErrorMessage("Data Monitoring", data.message);
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
            processing: false,
            destroy: true,
            serverSide: false,
            columns: [
                {"data": "process", "className": "all text-center"},
                {"data": "m1", "className": "all text-center"},
                {"data": "m2", "className": "all text-center"},
                {"data": "m3", "className": "all text-center"},
                {"data": "m4", "className": "all text-center"},
                {"data": "m5", "className": "all text-center"},
                {"data": "m6", "className": "all text-center"},
                {"data": "m7", "className": "all text-center"},
                {"data": "m8", "className": "all text-center"},
                {"data": "m9", "className": "all text-center"},
                {"data": "m10", "className": "all text-center"},
                {"data": "m11", "className": "all text-center"},
                {"data": "m12", "className": "all text-center"},
                {"data": "m13", "className": "all text-center"},
                {"data": "m14", "className": "all text-center"},
                {"data": "m15", "className": "all text-center"},
                {"data": "m16", "className": "all text-center"},
                {"data": "m17", "className": "all text-center"},
                {"data": "m18", "className": "all text-center"},
                {"data": "m19", "className": "all text-center"},
                {"data": "m20", "className": "all text-center"},
                {"data": "m21", "className": "all text-center"},
                {"data": "m22", "className": "all text-center"},
                {"data": "m23", "className": "all text-center"},
                {"data": "m24", "className": "all text-center"},
                {"data": "m25", "className": "all text-center"},
                {"data": "m26", "className": "all text-center"},
                {"data": "m27", "className": "all text-center"},
                {"data": "m28", "className": "all text-center"},
                {"data": "m29", "className": "all text-center"},
                {"data": "m30", "className": "all text-center"},
                {"data": "m31", "className": "all text-center"},
                {"data": "m32", "className": "all text-center"},
                {"data": "m33", "className": "all text-center"},
                {"data": "m34", "className": "all text-center"},
                {"data": "m35", "className": "all text-center"},
                {"data": "m36", "className": "all text-center"},
                {"data": "total", "className": "all text-center"},
            ],
            columnDefs: [],
            responsive: {
                details: false
            },
            dom: 'Blfrtip',
            buttons: [
                "copy", "excel"
            ],

            scrollX: true,
            lengthChange: false,
            lengthMenu: [5, 10, 20, 50],
            ordering: false,
            searching: false,
            info: false,
            paging: false,
            // pagingType: "numbers",
            language: {
                url: "/plugins/datatables/indonesian.json",
            },
            drawCallback: function (settings) {

            },
            initComplete: function (settings, json) {
                Utility.removeBoxOverlay("tblPayment");
            }
        }).buttons().container().appendTo('#tblPayment_wrapper .col-md-6:eq(0)');
    };

    return {
        init: function (){
            getDashboard();
            loadTblDeliverable();
            loadTblPayment();
        }
    }
}();
jQuery(document).ready(function (){
    Dashboard.init();
})