//数据列表
var listTab = $('#myTab li:eq(0) a');
//数据列表
var formTab = $('#myTab li:eq(1) a');
//维修历史列表
var historyTab = $('#myTab li:eq(2) a');
var recordsTab = $('#myTab li:eq(3) a');
var pointer = 0;
var eqStatuses = [];
var runStatus = [];


var vdm = null, hm = null, rm = null,
    rmListVue = null;
var pointer = 0;
$(function () {
    var validateOptions = {
        message: '该值无效 ',
        fields: {
            "eqCode": {
                message: '设备编号无效',
                validators: {
                    notEmpty: {
                        message: '设备编号不能为空!'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '设备编号长度为6到20个字符'
                    }
                }
            },
            "description": {
                message: '设备描述无效',
                validators: {
                    notEmpty: {
                        message: '设备描述不能为空!'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '设备描述长度为2到20个字符'
                    }
                }
            },
            "locations.id": {
                message: '设备位置无效',
                validators: {
                    notEmpty: {
                        message: '设备位置不能为空!'
                    }
                }
            },
            "equipmentsClassification.id": {
                message: '设备分类无效',
                validators: {
                    notEmpty: {
                        message: '设备分类不能为空!'
                    }
                }
            },
            "status": {
                message: '设备状态无效',
                validators: {
                    notEmpty: {
                        message: '设备状态不能为空!'
                    }
                }
            }
            ,
            "running": {
                message: '运行状态无效',
                validators: {
                    notEmpty: {
                        message: '运行状态不能为空!'
                    }
                }
            }
        }
    };
    //初始化配置信息
    //主对象信息
    mainObject = "equipment";
    //列表id
    dataTableName = "#equipmentsDataTable";
    //导出文档配置信息
    docName = "设备信息";
    //表单界面信息
    formName = "#detailForm";
    //查询模型


    var url_location = "/commonData/findMyLoc";
    $.getJSON(url_location, function (data) {
        locs = data;
    });

    var url = "/commonData/getEqStatus";
    $.getJSON(url, function (data) {
        eqStatuses = data;
    });

    var url = "/commonData/getEqRunStatus";
    $.getJSON(url, function (data) {
        runStatus = data;
    });

    var url = "/commonData/findVEqClass";
    $.getJSON(url, function (data) {
        eqClasses = data;
        console.log(JSON.stringify(eqClasses));
    });


    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            locs: locs,
            eqClasses: eqClasses
        }

    });

    searchModel = [
        {"param": "eqCode", "paramDesc": "设备编号"},
        {"param": "eqName", "paramDesc": "设备名称"},
        {"param": "locName", "paramDesc": "设备位置"},
        {"param": "eqClass", "paramDesc": "设备分类"}
    ];


    selectedIds = findAllRecordId();
    vdm = new Vue({
        el: "#detailForm",
        data: {
            equipment: null,
            locs: locs,
            eqClasses: eqClasses,
            eqStatuses: eqStatuses,
            runStatus: runStatus
        }
    });


    hm = new Vue({
        el: "#historyInfo",
        data: {
            e: findById(selectedIds[pointer]),
            histories: loadFixHistoryByEid(selectedIds[pointer])
        }
    });


    rm = new Vue({
        el: "#recordInfo",
        data: {
            e: findById(selectedIds[pointer])

        }
    });


    rmListVue = new Vue({

        el: "#updateRecords",
        data: {

            records: loadUpdateHistoryByEid(selectedIds[pointer])
        }

    });


    var config = {
        selection: true,
        multiSelect: true,
        rowSelect: true,
        keepSelection: true,
        formatters: {
            "report": function (column, row) {
                return '<a class="btn btn-default btn-xs"  onclick="report(' + row.id + ')" title="报修" ><i class="glyphicon glyphicon-wrench"></i></a>'
                    + '<a class="btn btn-default btn-xs"  onclick="eqUpdate(' + row.id + ')" title="更新" ><i class="glyphicon glyphicon-retweet"></i></a>'
            }
        }
    };
    //初始化加载列表
    initBootGridMenu(dataTableName, config);
    //验证保存信息
    validateForm(validateOptions);
    initSelect();
    search();
    showDetail();


    historyTab.on('click', function () {
        showFixHistory.call();
    });

    recordsTab.on('click', function () {
        showUpdateRecords.call();
    });
});


function showFixHistory() {
    var eid = selectedIds[pointer];
    var histories = loadFixHistoryByEid(eid);
    rm.$set("e", vdm.equipment);
    rm.$set("e.location.description", vdm.equipment.location.description);
    hm.$set("histories", histories);
}


function showUpdateRecords(eid) {
    var eid = selectedIds[pointer];
    var records = loadUpdateHistoryByEid(eid);
    rm.$set("e", vdm.equipment);
    rm.$set("e.location.description", vdm.equipment.location.description);
    rm.$set("records", records);
}


/**
 * 根据设备ID载入维修历史信息
 */
function loadFixHistoryByEid(eid) {
    var url = "/equipment/getFixStepsByEid/" + eid;
    var histories = [];
    $.getJSON(url, function (data) {
        histories = data;
    });
    return histories;
}


/**
 * 根据设备ID载入维修历史信息
 * @param eid
 * @return {Array}
 */
function loadUpdateHistoryByEid(eid) {
    var url = "/equipment/getUpdateHistoryById/" + eid;
    var records = [];
    $.getJSON(url, function (data) {
        records = data;
    });
    return records;
}


/**
 *  弹出框显示维修历史明细信息
 */
function showFixDetail(orderLineNo) {
    if (orderLineNo) {
        var url = "/equipment/loadFixHistory/" + orderLineNo;
        $("#fix-history").load(url, function (data) {
            $("#show_history_modal").modal("show");
        });
    }
}


/**
 * 设备报修
 * @param id
 */
function report(id) {
    var status = "0";
    var path = "/equipment/findById/" + id;
    $.getJSON(path, function (data) {
        status = data["status"]
    });
    var curl = "/workOrderReportCart/loadReportedEqPage/" + id;
    if (status == "0") {
        $("#eqList").load(curl, function (data) {
            $("#show_eq_modal").modal("show");
            //eqId = id;
            reportId = id;
        })
    } else {
        equipReport(id);
    }
}


/**
 * 设备更新申请
 * @param id
 */
function eqUpdate(id) {
    //找到该设备
    //跳转到设备更新页面  然后将参数带入
    $("#main-content").load("/eqUpdateBill/list", function () {
        eqUpdateAdd(id);
    });
}

function equipReport(id) {
    var url = "/workOrderReportCart/add2Cart";
    $.post(url, {equipmentId: id}, function (data) {
        var size = $("#reportOrderSize").html();
        if (!size) {
            size = 0
        }
        $("#reportOrderSize").html(parseInt(size) + 1);
        showMessageBox("info", "已将设备报修加入到维修车!")
    })
}
function continueEqReport() {
    $("#show_eq_modal").modal("hide");
    equipReport(reportId);
}
