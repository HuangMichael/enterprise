$(document).ready(function () {

    docName = "预防性维修工单信息";
    mainObject = "preMaintDispatcher";
    dataTableName = "#pmOrderList0";
    formName = "#detailForm";

    var url_location = "/commonData/findMyLoc";
    $.getJSON(url_location, function (data) {
        locs = data;
    });


    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            locs: locs
        }
    });

    ids = findAllRecordId();

    searchModel = [
        {"param": "orderDesc", "orderDesc": "故障描述"},
        {"param": "location", "paramDesc": "设备位置"}
    ];

    var cfg = {
        columnSelection: 1,
        rowCount: [10, 20, 25, -1],
        formatters: {
            "opMenus": function (column, row) {
                return '<a class="btn btn-default btn-xs"  onclick="pause(' + row.id + ')" title="暂停" ><i class="glyphicon glyphicon-pause"></i></a>' +
                    '<a class="btn btn-default btn-xs"  onclick="abort(' + row.id + ')" title="取消" ><i class="glyphicon glyphicon glyphicon-remove-circle"></i></a>' +
                    '<a class="btn btn-default btn-xs"  onclick="finish(' + row.id + ')" title="完工" ><i class="glyphicon glyphicon glyphicon-ok"></i></a>';
            }
        }
    };
    initBootGridMenu(dataTableName, cfg);
    initSelect();
    search();


    $('#pmOrderList1').bootgrid({
        columnSelection: 1,
        rowCount: [10, 20, 25, -1]
    });

    $('#pmOrderList2').bootgrid({
        columnSelection: 1,
        rowCount: [10, 20, 25, -1],
        formatters: {
            "opMenus": function (column, row) {
                return '<a class="btn btn-default btn-xs"  onclick="pause(' + row.id + ')" title="暂停" ><i class="glyphicon glyphicon-pause"></i></a>' +
                    '<a class="btn btn-default btn-xs"  onclick="abort(' + row.id + ')" title="取消" ><i class="glyphicon glyphicon glyphicon-remove-circle"></i></a>' +
                    '<a class="btn btn-default btn-xs"  onclick="finish(' + row.id + ')" title="完工" ><i class="glyphicon glyphicon glyphicon-ok"></i></a>';
            }
        }
    });

    $('#pmOrderList3').bootgrid({
        columnSelection: 1,
        rowCount: [10, 20, 25, -1]
    });


    $("#saveFixDesc").on("click", function () {
        var orderId = $("#orderId").val();
        var operationType = $("#operationType").val();
        var operationDesc = $("#operationDesc").val();
        var fixDesc = $("#fixDesc").val();

        if (!fixDesc) {
            $("#fixDesc").css("border", "red dashed 1px");
            showMessageBox("danger", " 请输入维修描述");
            return;
        }
        dealResultDetail(orderId, operationType, operationDesc, fixDesc);

    });


});


function updateOrderStatus(orderId, operationType, operationDesc, fixDesc) {
    var url = "/preMaintDispatcher/" + operationType;
    $.post(url, {fixId: orderId, fixDesc: fixDesc}, function (data) {
        $("#fix_desc_modal").modal("hide");
        $("#tr-" + orderId).html(operationDesc);
        $("#tr-" + orderId).html(fixDesc);
        (data.result) ? showMessageBox("info", data["resultDesc"]) : showMessageBox("danger", data["resultDesc"]);
    });
}


function dealResultDetail(orderId, operationType, operationDesc, fixDesc) {
    updateOrderStatus(orderId, operationType, operationDesc, fixDesc);
}
/**
 *
 * @param id 完工
 */
function finish(id) {
    var orderId = id;
    var operationType = "finishDetail";
    var operationDesc = "完工";
    dealResult(orderId, operationType, operationDesc);
}

/**
 *
 * @param id 暂停
 */
function pause(id) {
    var orderId = id;
    var operationType = "pauseDetail";
    var operationDesc = "暂停";
    dealResult(orderId, operationType, operationDesc);
}
/**
 *
 * @param id 取消
 */
function abort(id) {
    var orderId = id;
    var operationType = "abortDetail";
    var operationDesc = "取消";
    dealResult(orderId, operationType, operationDesc);
}

function dealResult(orderId, operationType, operationDesc) {
    $("#orderId").val(orderId);
    $("#operationType").val(operationType);
    $("#operationDesc").val(operationDesc);
    $("#fix_desc_modal").modal("show");
}