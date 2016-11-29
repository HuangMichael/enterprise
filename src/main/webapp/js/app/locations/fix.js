var zTree;
var demoIframe;

var showCols = ["id", "eqCode"];
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
            var url = "/equipmentAccount/findEqByLocLike/" + treeNode.location;
            $("#tbody").html("");
            // findEqByLocLike(url, "#tbody");
            return true;
        }
    }
};
var zNodes = [];


/**
 *
 * @param url ajax 根据设备编号模糊查询设备
 * @returns {*} tableId, cols
 */
function findEqByLocLike(url, tbodyId) {
    $.getJSON(url, function (data) {
        for (var x = 0; x < data.length; x++) {
            if (data[x]) {
                var html = "<tr class='gradeX'>";
                /* html += "<td>" + data[x]["line"]["description"] + "</td>";
                 html += "<td>" + data[x]["station"]["description"] + "</td>";*/
                html += "<td>" + data[x]["id"] + "</td>";
                html += "<td>" + data[x]["eqCode"] + "</td>";
                html += "<td>" + data[x]["description"] + "</td>";
                html += "<td>" + data[x]["location"] + "</td>";
                html += "<td>" + data[x]["location"] + "</td>";
                html += "<td><a>报修</a></td>";
                html += "</tr>";

            }
            $(tbodyId).append(html);
        }
    });


}


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


        /* $("#datatable1").bootgrid(
         {
         formatters: {
         "report": function (column, row) {
         var conId = row.id;
         return '<a class="btn btn-default btn-xs"  onclick="report(' + conId + ')">报修</a>';
         }
         }
         }
         );*/


    });
    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
            htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
            maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
            h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }
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
            if (fix.id) {
                showMessageBox("info", "工单信息更新成功");
            } else {
                showMessageBox("info", "工单信息添加成功");

            }
        },
        error: function (msg) {
            if (fix.id) {
                showMessageBox("danger", "工单信息更新失败");
            } else {
                showMessageBox("danger", "工单信息添加失败");

            }
        }
    });
}

function saveFixWorkOrder() {
    var objStr = getFormJsonData("woForm");
    var workOrder = JSON.parse(objStr);
    var url = "/workOrder/save";
    $.ajax({
        type: "POST",
        url: url,
        data: workOrder,
        dataType: "JSON",
        success: function (msg) {
            $("#wo_modal").modal("hide");
            showMessageBox("info", "工单信息生成成功!");
        }
    });

    /**
     *
     * @param equipmentId 设备编号
     */
    function report(equipmentId) {
        if (equipmentId) {
            var url = "/equipment/findById/" + equipmentId;
            $.getJSON(url, function (data) {
                showEqModal("wo_modal", data, equipmentId);
            });
        } else {
            showMessageBox("danger", "操作失败，请重试!");

        }


    }


    function showEqModal(modalId, data, equipmentId) {
        $("#eqDesc").val(data.description);
        $("#location_id").val(data.locations.id);
        /*    $("#line_id").val(data.line.id);
         $("#station_id").val(data.station.id);*/
        $("#equipment_id").val(equipmentId);
        $("#equip_class_id").val(data["equipmentsClassification"]["id"]);
        $("#" + modalId).modal("show");


    }


}

