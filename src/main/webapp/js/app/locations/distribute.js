var zTree;
var demoIframe;
var setting = {
    check: {
        enable: false
    },
    view: {
        dblClickExpand: false,
        showLine: true,
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: ""
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode, clickFlag) {
            var url = "/fix/detail/" + treeNode.id;
            /*
             console.log(url);
             $("#contentDiv").load(url);*/
            return true;
        }
    }
};
var zNodes = [];
$(document).ready(function () {
    var url = "/location/findTree";
    var pid = 0;
    $.ajaxSettings.async = false;
    $.getJSON(url, function (data) {
        for (var x = 0; x < data.length; x++) {
            zNodes[x] = {
                id: data[x][0],
                location: data[x][1],
                name: data[x][2],
                superior: data[x][3],
                pId: (data[x][4]) ? (data[x][4]) : 0,
                open: false,
                isParent: pid
            }
            ;
        }
        var t = $("#tree");
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        // var zTree = $.fn.zTree.getZTreeObj("tree");
        //zTree.selectNode(zTree.getNodeByParam("id", 1));

    });
    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
            htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
            maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
            h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }


    $("#datatable1").bootgrid(
        {
            formatters: {
                "distribute": function (column, row) {
                    var conId = row.id;
                    return '<a class="btn btn-default btn-xs"  onclick="distribute(' + conId + ')">分配</a>';
                },
                "suspend": function (column, row) {
                    var conId = row.id;
                    return '<a class="btn btn-default btn-xs"  onclick="suspend(' + conId + ')">挂起</a>';
                }
            }
        }
    );


});


var flag = false;
/**
 *加载创建form
 *
 * */
function loadCreateForm() {

    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    if (!id) {
        id = 0;
    }
    var url = "/fix/create/" + id;
    $("#contentDiv").load(url, function () {
        flag = true;
    });
}


function save() {
    var objStr = getFormJsonData("form");
    var fix = JSON.parse(objStr);
    var url = "/fix/save";
    $.ajax({
        type: "POST",
        url: url,
        data: fix,
        dataType: "JSON",
        success: function (msg) {

            var childZNode = {
                id: msg.id,
                pId: msg.parent.id,
                name: msg.description,
                open: msg.parent.id,
                isParent: msg["hasChild"]
            };

            if (fix.id) {
                showMessageBox("info", "位置信息更新成功");
            } else {
                addNodeAfterOperation(null, childZNode);
                showMessageBox("info", "位置信息添加成功");

            }
        },
        error: function (msg) {
            if (fix.id) {
                showMessageBox("danger", "位置信息更新失败");
            } else {
                showMessageBox("danger", "位置信息添加失败");

            }
        }
    });
}

/**
 *
 * @param workOrderId 工单编号
 */
function distribute(workOrderId) {
    if (workOrderId) {
        var url = "/workOrder/findById/" + workOrderId;
        $.getJSON(url, function (data) {
            $("#workOrderId").val(workOrderId);
            $("#share-workOrder").modal("show");
        });
    }


}
/**
 *
 * @param workOrderId
 * 工单编号
 */
function suspend(workOrderId) {
    if (workOrderId) {
        var url = "/workOrder/findById/" + parseInt(workOrderId);
        $.getJSON(url, function (data) {
            $("#workOrderId").val(workOrderId);
            showEqModal("wo_modal", data);
        });
    }


}


function showEqModal(modalId, data) {
    $("#eqDesc").val(data.description);
    $("#location_id").val(data.locations.id);
    /*    $("#line_id").val(data.line.id);
     $("#station_id").val(data.station.id);*/
    $("#equip_class_id").val(data["equipmentsClassification"]["id"]);
    $("#" + modalId).modal("show");


}


$("#saveSuspend").on("click", function () {
    var objStr = getFormJsonData("woForm");
    var suspend = JSON.parse(objStr);
    console.log("objStr-------------" + objStr);
    var url = "/workOrder/suspend";
    $.ajax({
        type: "POST",
        url: url,
        data: suspend,
        dataType: "JSON",
        success: function (msg) {
            $("#wo_modal").modal("hide");
            showMessageBox("info", "工单挂起成功");
        },
        error: function (msg) {
            showMessageBox("danger", "工单挂起失败");
        }
    });
});


/**
 * 分配工单弹出框 录入数据后保存
 */
function distributeWorkOrder() {
    var limitedHours = $("#limitedHours").val();
    var unit_id = $("#unit_id").val();
    var workOrderId = $("#workOrderId").val();
    var data = {
        limitedHours: limitedHours,
        workOrderIdArray: workOrderId + ",",
        unit_id: unit_id
    };
    var url = "/workOrder/shareWorkOrderBatch";
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function (msg) {
            $("#share-workOrder").modal("hide");
            showMessageBox("info", "工单信息分配成功");
        },
        error: function () {
            showMessageBox("danger", "工单信息分配失败");
        }
    });
}


