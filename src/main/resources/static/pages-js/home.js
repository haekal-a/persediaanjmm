var Home = function (){
    var barangAsc = [];
    var barangDesc = [];

    function doughnutChart() {
        var doughnutChartCanvas = $("#doughnutChart").get(0).getContext("2d");
        var doughnutPieData = {
            datasets: [{
                data: [barangDesc[0].jumlahBarang, barangDesc[1].jumlahBarang, barangDesc[2].jumlahBarang, barangDesc[3].jumlahBarang],
                backgroundColor: [
                    "#1F3BB3",
                    "#FDD0C7",
                    "#52CDFF",
                    "#81DADA"
                ],
                borderColor: [
                    "#1F3BB3",
                    "#FDD0C7",
                    "#52CDFF",
                    "#81DADA"
                ],
            }],

            // These labels appear in the legend and in the tooltips when hovering different arcs
            labels: [
                barangDesc[0].namaBarang,
                barangDesc[1].namaBarang,
                barangDesc[2].namaBarang,
                barangDesc[3].namaBarang,
            ]
        };
        var doughnutPieOptions = {
            cutoutPercentage: 50,
            animationEasing: "easeOutBounce",
            animateRotate: true,
            animateScale: false,
            responsive: true,
            maintainAspectRatio: true,
            showScale: true,
            legend: false,
            legendCallback: function (chart) {
                var text = [];
                text.push('<div class="chartjs-legend"><ul class="justify-content-center">');
                for (var i = 0; i < chart.data.datasets[0].data.length; i++) {
                    text.push('<li><span style="background-color:' + chart.data.datasets[0].backgroundColor[i] + '">');
                    text.push('</span>');
                    if (chart.data.labels[i]) {
                        text.push(chart.data.labels[i]);
                    }
                    text.push('</li>');
                }
                text.push('</div></ul>');
                return text.join("");
            },

            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            tooltips: {
                callbacks: {
                    title: function(tooltipItem, data) {
                        return data['labels'][tooltipItem[0]['index']];
                    },
                    label: function(tooltipItem, data) {
                        return data['datasets'][0]['data'][tooltipItem['index']];
                    }
                },

                backgroundColor: '#fff',
                titleFontSize: 14,
                titleFontColor: '#0B0F32',
                bodyFontColor: '#737F8B',
                bodyFontSize: 11,
                displayColors: false
            }
        };
        var doughnutChart = new Chart(doughnutChartCanvas, {
            type: 'doughnut',
            data: doughnutPieData,
            options: doughnutPieOptions
        });
        document.getElementById('doughnut-chart-legend').innerHTML = doughnutChart.generateLegend();
    }
    function listTersedikit(){
        for (var i=0; i<4; i++){
            var elem = '<div class="wrapper d-flex align-items-center justify-content-between py-2 border-bottom">\n' +
                '                                    <div class="d-flex">\n' +
                '                                        <div class="wrapper">\n' +
                '                                            <p class="mb-1 fw-bold">'+barangAsc[i].namaBarang+'</p>\n' +
                '                                            <small class="text-muted mb-0">'+Utility.formatRupiah(barangAsc[i].hargaSatuan)+'</small>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                    <div class="text-muted text-small">\n' +
                '                                        '+barangAsc[i].jumlahBarang+' Barang\n' +
                '                                    </div>\n' +
                '                                </div>';
            $("#listTersedikit").append(elem);
        }
    }

    var getListBarang = function () {
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/transaksi/getlistbarang",
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    barangAsc = data.object.sort(function (a, b){
                        return a.jumlahBarang - b.jumlahBarang;
                    });
                    listTersedikit();
                    barangDesc = data.object.sort(function (a, b){
                        return b.jumlahBarang - a.jumlahBarang;
                    });
                    doughnutChart();
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

    var getListTransaksi = function () {
        var date = new Date();
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
        Utility.showBoxOverlay();
        $.ajax({
            type: "GET",
            url: "/transaksi/getlist",
            data: function (data) {
                data.draw = 1;
                data.param1 = "";
                data.param2 = null;
                data.param3 = null;
                data.param4 = Utility.formatTanggalToString(firstDay);
                data.param5 = Utility.formatTanggalToString(lastDay);
                Utility.showBoxOverlay("tblTransaksi");
            },
            success: function (data) {
                Utility.removeBoxOverlay();
                if (data.code == 1) {
                    console.log(data.object.data);
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
            getListTransaksi();
        }
    }
}();
jQuery(document).ready(function (){
    Home.init();
});