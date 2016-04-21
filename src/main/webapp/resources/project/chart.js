$(document).ready(function () {
    var chart = new Highcharts.Chart({

        chart: {
            renderTo: $("#container")[0],
            type: 'spline'
        },
        subtitle: {
            text: 'Source: WorldClimate.com'
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Temperature'
            },
            labels: {
                formatter: function () {
                    return this.value + '°';
                }
            }
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }
        },
        series: [{
            name: 'Tokyo',
            marker: {
                symbol: 'square'
            },
            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, {
                y: 26.5,
                marker: {
                    symbol: 'url(/resources/img/sun.png)'
                }
            }, 23.3, 18.3, 13.9, 9.6]

        }, {
            name: 'London',
            marker: {
                symbol: 'diamond'
            },
            data: [{
                y: 3.9,
                marker: {
                    symbol: 'url(/resources/img/snow.png)'
                }
            }, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }],
        credits: {enabled: false},
        exporting: {enabled: false},
        title: {
            text: null
        }
    });

    $(".jsLoad").on("click", function () {
        //saveChartToPng(chart, function (png) {
        //
        //})

        var data = {name: "Test", image: exportSVG(chart)};
        $.ajax({
            type: "POST",
            url: "/upload",
            data: data,
            success: function (data) {
                alert("Image Uploaded successfully");
            }
        });
    });


    function exportSVG(chart) {
        var render_width = 1000;
        var render_height = render_width * chart.chartHeight / chart.chartWidth;

        return chart.getSVG({
            exporting: {
                sourceWidth: chart.chartWidth,
                sourceHeight: chart.chartHeight
            }
        });
    }


    function saveChartToPng(chart, callback) {
        var EXPORT_WIDTH = 1000;
        var render_width = EXPORT_WIDTH;
        var render_height = render_width * chart.chartHeight / chart.chartWidth;
        var DOMURL = window.URL || window.webkitURL || window;

        var svg = chart.getSVG({
            exporting: {
                sourceWidth: chart.chartWidth,
                sourceHeight: chart.chartHeight
            }
        });

        var canvas = document.createElement('canvas');
        canvas.height = render_height;
        canvas.width = render_width;

        var img = new Image();
        var blob = new Blob([svg.toString()], {type: "image/svg+xml;charset=utf-8"});
        var url = DOMURL.createObjectURL(blob);

        img.onload = function () {
            canvas.getContext("2d").drawImage(img, 0, 0);
            var png = canvas.toDataURL("image/png");
            callback(png);
            DOMURL.revokeObjectURL(png);
        };

        img.src = url;
    }
});