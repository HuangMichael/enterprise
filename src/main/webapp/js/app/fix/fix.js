/**
 * Created by Administrator on 2016/7/22.
 */
$(document).ready(function () {


    var tableName0 = "#fixListTable0";
    var tableName1 = "#fixListTable1";
    var tableName2 = "#fixListTable2";
    var tableName3 = "#fixListTable3";

    docName = "维修单信息";
    mainObject = "workOrderFix";
    //导出必须配置的两个量
    dataTableName = "#fixListTable0";


    var url_location = "/commonData/findMyLoc";
    $.getJSON(url_location, function (data) {
        locs = data;
    });


    var url = "/commonData/findVEqClass";
    $.getJSON(url, function (data) {
        eqClasses = data;
    });


    initSelect();

    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            locs: locs,
            eqClasses: eqClasses
        }

    });

    searchModel = [
        {"param": "orderLineNo", "paramDesc": "跟踪号"},
        {"param": "orderDesc", "paramDesc": "故障描述"},
        {"param": "location", "paramDesc": "设备位置"},
        {"param": "eqClass", "paramDesc": "设备分类"}
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
        },
        templates: {
            actionButton: "<button class=\"btn btn-default\" type=\"button\" title=\"{{ctx.text}}\">{{ctx.content}}</button> <button class='btn btn-default' type='button' title='导出数据'  name='fixListTable0'  onclick='exportExcelByName(" + tableName0 + ")'>导出</button>"
        }
    };

    initBootGridMenu(tableName0, cfg);
    search();


    $('#fixListTable1').bootgrid({
        templates: {
            actionButton: "<button class=\"btn btn-default\" type=\"button\" title=\"{{ctx.text}}\">{{ctx.content}}</button> <button class='btn btn-default' type='button' title='导出数据' name='fixListTable1' onclick='exportExcelByName(" + tableName1 + ")'>导出</button>"
        }
    });

    $('#fixListTable2').bootgrid({

        templates: {
            actionButton: "<button class=\"btn btn-default\" type=\"button\" title=\"{{ctx.text}}\">{{ctx.content}}</button> <button class='btn btn-default' type='button' title='导出数据' name='fixListTable2' onclick='exportExcelByName(" + tableName2 + ")'>导出</button>"
        }
        ,
        formatters: {
            "opMenus": function (column, row) {
                return '<a class="btn btn-default btn-xs"  onclick="pause(' + row.id + ')" title="恢复" ><i class="glyphicon glyphicon-pause"></i></a>' +
                    '<a class="btn btn-default btn-xs"  onclick="abort(' + row.id + ')" title="取消" ><i class="glyphicon glyphicon glyphicon-remove-circle"></i></a>' +
                    '<a class="btn btn-default btn-xs"  onclick="finish(' + row.id + ')" title="完工" ><i class="glyphicon glyphicon glyphicon-ok"></i></a>'
            }
        }
    });

    $('#fixListTable3').bootgrid({
        templates: {
            actionButton: "<button class=\"btn btn-default\" type=\"button\" title=\"{{ctx.text}}\">{{ctx.content}}</button> <button class='btn btn-default' type='button' title='导出数据' name='fixListTable3' onclick='exportExcelByName(" + tableName3 + ")'>导出</button>"
        }
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
    $("#myTab a").on("click", function (e) {
        e.preventDefault();
        $(this).tab('show');
    });


    $("button[title='导出数据']").on("click", function () {
        var tableName = "#" + $(this).attr("name");
        var nodeState = ["已派工", "已完工", "已暂停", "已取消"];
        var state = nodeState[tableName.charAt(tableName.length - 1)];
        exportExcelByName(tableName, state, state + docName);

    })
});


function dealResult(orderId, operationType, operationDesc) {
    $("#orderId").val(orderId);
    $("#operationType").val(operationType);
    $("#operationDesc").val(operationDesc);
    $("#fix_desc_modal").modal("show");
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
 *  调整维修期限
 * @param id
 */
function adjust(id) {
    var orderId = id;
    var url = "/workOrderFix/getCellingDate/" + orderId;
    $("#orderId").val(orderId);
    $.getJSON(url, function (data) {
        $("#fixAdjust0").val(transformDate(data));
        $("#fix_adjust_modal").modal("show");
    })
}


/**
 *
 * @param orderId
 * @param deadLine
 */
function confirmAdjust() {
    var url = "workOrderFix/updateDeadLine";
    var data = {
        orderId: $("#orderId").val(),
        deadLine: $("#fixAdjust1").val()
    };
    $.post(url, data, function (data) {
        if (data.result) {
            showMessageBox("info", data['resultDesc']);
        } else {
            showMessageBox("danger", data['resultDesc']);
        }
        $("#fix_adjust_modal").modal("hide");
        $('#fixListTable').bootgrid();
    });

}


function updateOrderStatus(orderId, operationType, operationDesc, fixDesc) {
    var url = "/workOrderFix/" + operationType;
    $.post(url, {fixId: orderId, fixDesc: fixDesc}, function (data) {
        $("#fix_desc_modal").modal("hide");
        $("#tr-" + orderId).html(operationDesc);
        $("#tr-" + orderId).html(fixDesc);
        (data.result) ? showMessageBox("info", data['resultDesc']) : showMessageBox("danger", data['resultDesc']);
    });
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


