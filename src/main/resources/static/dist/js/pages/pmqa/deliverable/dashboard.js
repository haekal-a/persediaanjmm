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
                    display: false,
                    text: "World Wide Wine Production"
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

    return {
        init: function (){
            getDashboard();
        }
    }
}();
jQuery(document).ready(function (){
    Dashboard.init();
})