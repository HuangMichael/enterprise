$(document).ready(function () {
    App.setPage("inbox");  //Set current page
    App.init(); //Initialise plugins and elements
    Highcharts.setOptions({
        colors: ['#50B432', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4', '#058DC7'],
        exporting: {
            enabled: true
        },
    });

    loadChartData(addMonth(0));
    //默认加载当月数据


    $("#currentMonth").on("click", function () {
        var reportMonth = addMonth(0);
        loadChartData(reportMonth);
        $("#setupDate").val(reportMonth);
    });
    $("#lastMonth").on("click", function () {
        var reportMonth = addMonth(-1);
        loadChartData(reportMonth);
        $("#setupDate").val(reportMonth);
    })


    $("#displayBtn").on("click", function () {
        var reportMonth = $("#setupDate").val();
        reportMonth = (!reportMonth) ? addMonth(0) : reportMonth;
        loadChartData(reportMonth);

    })
});


/**
 *
 * @param reportMonth
 */
function loadChartData(reportMonth) {
    loadEqClassChart(reportMonth);
    loadReportFinishChart(reportMonth);
    loadLineChart(reportMonth);
}

/**
 *加载设备分类统计
 * @param reportMonth
 */
function loadEqClassChart(reportMonth) {
    /**
     *
     * @param chart2Data
     * @returns {*}
     */
    var newData = [];

    function assembleData(chart2Data) {
        var sumOther = 0;
        chart2Data.forEach(function (e, i) {
            var obj = null;
            if (i < 5) {
                obj = {name: e[1], y: e[2]}
                newData.push(obj);
            } else {
                sumOther += e[2];
            }

        });
        if (sumOther) {
            newData.push({
                name: "其他分类",
                y: sumOther
            })
            return newData;
        }
    }

    //ajax 请求当月设备分类前5
    var chart2Data = [];
    $.getJSON("/portal/findTopEqClass/" + reportMonth, function (data) {
        chart2Data = assembleData(data);
    });
    var eqClassChartConfig = {
        chart: {
            type: 'pie'
        },
        title: {
            text: reportMonth + '报修按设备类型统计'
        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '{point.name}: {point.y}'
                }
            }
        },
        exporting: {
            enabled: true
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>报修: <b>{point.y}</b>单/共<b>{point.total}</b>单<b>/占比:{point.percentage:.1f}%</b>'
        },
        series: [{
            name: '报修数量',
            colorByPoint: true,
            data: newData
        }]
    }
    $('#highCharts0').highcharts(eqClassChartConfig);


}


/**
 *加载设备分类统计
 * @param reportMonth
 */
function loadReportFinishChart(reportMonth) {
    var seriesOptions = [];
    var option0, option1;

    option0 = {
        "name": "报修数量",
        "data": get3MonthReportNum(reportMonth)
    };
    option1 = {
        "name": "完工数量",
        "data": get3MonthFinishNum(reportMonth)
    };
    seriesOptions.push(option0);
    seriesOptions.push(option1);


    function get3MonthTitle(reportMonth) {
        var title = [];
        var date = new Date();
        title.push((date.getMonth() - 1) + "月");
        title.push(date.getMonth() + "月");
        title.push((date.getMonth() + 1) + "月");
        return title;
    }


    function get3MonthReportNum(reportMonth) {
        $.ajaxSettings.async = false;
        var url = "/workOrderReport/sel3mRptNum";
        var reportNums = [];
        $.getJSON(url, function (data) {
            for (var x = 0; x < 3; x++) {
                if (data[x] && data[x]["reportNum"] && !isNaN(data[x]["reportNum"])) {
                    reportNums.push(data[x]["reportNum"]);
                } else {
                    reportNums.push(0);
                }
            }
        });
        return reportNums;
    }

    function get3MonthFinishNum() {
        $.ajaxSettings.async = false;
        var url = "/workOrderReport/sel3mFinishNum";
        var finishNums = [];
        $.getJSON(url, function (data) {
            for (var x = 0; x < 3; x++) {
                if (data[x] && data[x]["finishNum"] && !isNaN(data[x]["finishNum"])) {
                    finishNums.push(data[x]["finishNum"]);
                } else {
                    finishNums.push(0);
                }
            }
        });
        return finishNums;
    }

    var reportFinishChartConfig = {
        chart: {
            type: 'column'
        },

        exporting: {
            enabled: true
        },
        title: {
            text: '最近3个月报修完成情况统计'
        },
        /*    subtitle: {
         text: get3MonthTitle()
         },*/
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
            categories: get3MonthTitle()
        },
        yAxis: {
            min: 0,
            title: {
                text: '工单数量(单位:个)'
            }
        },
        series: seriesOptions
    }
    $('#highCharts1').highcharts(reportFinishChartConfig);


}


/**
 * 根据线路统计各状态的订单数量
 * @param reportMonth
 */
function loadLineChart(reportMonth) {
    function loadByStatus(status) {
        var url = "/portal/getLineReportNum/" + reportMonth + "/" + status;
        var dataList = [];
        $.ajaxSettings.async = false;
        $.getJSON(url, function (data) {
            for (var x in data) {
                if (data[x]['num']) {
                    dataList[x] = data[x]['num'];
                }
            }
        });
        return dataList;
    }


    var orderStatus = ["待分配", "维修中", "完工", "暂停", "取消"];
    var url = "/line/findAllLines";
    var lines = [];
    $.ajaxSettings.async = false;
    $.getJSON(url, function (data) {
        for (var x in data) {
            if (data[x]['description']) {
                lines[x] = data[x]['description'];
            }
        }
    });
    $('#highCharts2').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: reportMonth + '月维修单状态按线别统计'
        },
        xAxis: {
            categories: lines,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '工单数量(单位:个)'
            }
        },
        lang: {
            noData: "当前无显示数据"
        },
        noData: {
            style: {
                fontWeight: 'bold',
                fontSize: '15px',
                color: '#303030'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
            {
                name: orderStatus[0],
                data: loadByStatus(0)

            }, {
                name: orderStatus[1],
                data: loadByStatus(1)

            },

            {
                name: orderStatus[2],
                data: loadByStatus(2)
            },
            {
                name: orderStatus[3],
                data: loadByStatus(3)

            },
            {
                name: orderStatus[4],
                data: loadByStatus(4)

            }
        ]
    });
}