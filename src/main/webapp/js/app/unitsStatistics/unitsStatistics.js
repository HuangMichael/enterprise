$(document).ready(function () {
    App.setPage("inbox");  //Set current page
    App.init(); //Initialise plugins and elements
    Highcharts.setOptions({
        colors: ['#50B432', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4', '#058DC7'],
        exporting: {
            enabled: true
        },
    });

    //初始化加载

    //加载有数据的年份
    getDataYear();

    //加载所有的外委单位信息
    loadAllUnits();

    $("#selectUnits").on("change", function () {
        loadReportFinishChart();
    })


    $("#selectYear").on("change", function () {
        loadReportFinishChart();
    })


    $("#selectYear1").on("change", function () {
        loadUnitsRankChart();
    })

    $("#selectYear2").on("change", function () {
        loadEfficiencyChart();
    })


    $("select").select2({
        theme: "bootstrap"
    });

    loadChartData();
    //默认加载当月数据
});


var dataMonth = [];


/**
 * 默认加载数据
 */
function loadChartData() {
    loadUnitsRankChart();
    loadReportFinishChart();
    loadEfficiencyChart();
}

/**
 *加载设备分类统计
 * @param reportMonth
 */
function loadUnitsRankChart() {
    var reportYear = $("#selectYear1").val();
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
                obj = {name: e["unitName"], y: e["fixNum"]};
                newData.push(obj);
            } else {
                sumOther += e["fixNum"];
            }

        });
        if (sumOther) {
            newData.push({
                name: "其他单位",
                y: sumOther
            })
            return newData;
        }
    }

    //ajax 请求当月设备分类前5
    var chart2Data = [];
    $.getJSON("/unitsStatistics/findByReportYear/" + reportYear, function (data) {
        chart2Data = assembleData(data);
    });
    var unitsRankChartConfig = {
        chart: {
            type: 'pie'
        },
        title: {
            text: reportYear + '年度维修数量前五名统计'
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
            name: '维修数量',
            colorByPoint: true,
            data: newData
        }]
    }
    $('#highCharts0').highcharts(unitsRankChartConfig);


}


/**
 *
 * @param year 根据年份获得有数据的月
 */
function getDataYear() {
    var url = "unitsStatistics/getDataYear";
    var year = [];
    $.getJSON(url, function (data) {
        year = data;

        data.forEach(function (e, i) {

            if (i == 0) {
                $("select[name='selectYear']").append("<option value='" + e + "' selected>" + e + '年' + "</option>");
            } else {
                $("select[name='selectYear']").append("<option value='" + e + "'>" + e + '年' + "</option>");
            }

        })


    });
    return year;
}


/**
 *加载设备分类统计
 * @param reportMonth
 */
function loadReportFinishChart() {


    var year = $("#selectYear").val();
    var unitId = $("#selectUnits").val();
    var unitName = $("#selectUnits").find("option:selected").text();
    dataMonth = [];
    var seriesOptions = [];
    var option0, option1;

    option0 = {
        "name": "分配数量",
        "data": getDataDistributed(unitId, year)
    };
    option1 = {
        "name": "完工数量",
        "data": getDataFinished(unitId, year)
    };
    seriesOptions.push(option0);
    seriesOptions.push(option1);


    /**
     *
     * @returns {Array}
     */
    function getDataMonthByYear(year) {
        var title = [];
        if (!year) {
            year = new Date().getFullYear();
        }
        var url = "unitsStatistics/getDataMonthByYear/" + year;
        $.getJSON(url, function (data) {

            title = data;
        });
        return title;
    }


    /**
     *
     * @returns {Array} 获取已分配的数据
     * @param unitId 外委单位ID
     * @param year 年份
     */
    function getDataDistributed(unitId, year) {
        $.ajaxSettings.async = false;
        var url = "unitsStatistics/getDataDistributed/" + unitId + "/" + year;
        var distributedNum = [];
        $.getJSON(url, function (data) {
            data.forEach(function (d) {
                dataMonth.push(d["reportMonth"]);
                distributedNum.push(d["reportNum"]);
            });
        });
        return distributedNum;
    }


    function getDataFinished(unitId, year) {
        $.ajaxSettings.async = false;
        var url = "unitsStatistics/getDataFinished/" + unitId + "/" + year;
        var finishNum = [];
        $.getJSON(url, function (data) {
            data.forEach(function (d) {
                dataMonth.push(d["reportMonth"]);
                finishNum.push(d["reportNum"]);
            });
        });
        return finishNum;
    }

    var reportFinishChartConfig = {
        chart: {
            type: 'column'
        },

        exporting: {
            enabled: true
        },
        title: {
            text: unitName + year + '年度维修统计'
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
            categories: dataMonth
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
 *加载设备分类统计
 * @param reportMonth
 */
function loadEfficiencyChart() {

    var year = $("#selectYear2").val();
    var seriesOptions = [];
    var option0;

    option0 = {
        "name": "维修及时率",
        "data": getDataPercent(year)
    };
    seriesOptions.push(option0);


    /**
     *
     * @returns {Array}
     */
    function getTop5Units(year) {
        var units = [];
        if (!year) {
            year = new Date().getFullYear();
        }
        var url = "unitsStatistics/findEffRankByReportYear/" + year;
        $.getJSON(url, function (data) {
            data.forEach(function (e, i) {
                units[i] = e["unitName"];
            })
        });
        return units;
    }


    /**
     *
     * @returns {Array} 获取已分配的数据
     * @param unitId 外委单位ID
     * @param year 年份
     */
    function getDataPercent(year) {
        $.ajaxSettings.async = false;
        var url = "unitsStatistics/findEffRankByReportYear/" + year;
        var distributedNum = [];

        $.getJSON(url, function (data) {
            data.forEach(function (m, i) {

                distributedNum[i] = m["percent"];
            })

        });
        return distributedNum;
    }


    var reportFinishChartConfig = {
        chart: {
            type: 'column'
        },

        exporting: {
            enabled: true
        },
        title: {
            text: year + '年度外委单位维修及时率后五名统计'
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
            categories: getTop5Units(year)
        },
        yAxis: {
            min: 0,
            title: {
                text: '维修及时率百分比(单位：%)'
            }
        },
        series: seriesOptions
    }
    $('#highCharts2').highcharts(reportFinishChartConfig);


}


/**
 * 加载所有的单位信息
 */
function loadAllUnits() {
    var units = null;
    var url = "units/findOutUnits/1";
    $.getJSON(url, function (data) {
        units = data;
        data.forEach(function (e, i) {
            $("#selectUnits").append("<option value='" + e["id"] + "'>" + e["description"] + "</option>");
        })
    });

    return units;
    //加载完毕后建立模型为其赋值
}