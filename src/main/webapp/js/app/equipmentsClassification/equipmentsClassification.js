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
            var url = "/equipmentsClassification/detail/" + treeNode.id;
            $("#contentDiv").load(url);
            $.getJSON(url, function (data) {
                fillForm(data, "#form");
                loadTable();
            });


            return true;
        }
    }
};
var zNodes = [];
$(document).ready(function () {
    var url = "/equipmentsClassification/findAll";
    var pid = 0;
    var obj = null;
    $.getJSON(url, function (data) {
        for (var x = 0; x < data.length; x++) {
            if (data) {
                obj = data[x];
                pid = (!obj["parent"]) ? 0 : obj["parent"].id;
                zNodes[x] = {id: obj.id, pId: pid, name: obj.description, open: !pid, isParent: obj["hasChild"]};
            } else {
                alert("信息加载出错");
            }
        }
        var t = $("#tree");
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
        zTree.selectNode(zTree.getNodeByParam("id", 1));

    });
    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
            htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
            maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
            h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }


    $("input[name = 'unit']").on("click", function () {
        alert($(this).val());

    });


    //查询当前选中的分类对应的外委单位


});


var flag = false;
/**
 *加载创建form
 *
 * */
function add() {

    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    if (!id) {
        id = 0;
    }
    var url = "/equipmentsClassification/create/" + id;
    $("#contentDiv").load(url, function () {
        flag = true;
    });
}


function save() {
    var lid = $("#lid").val();
    var parentId = $("#parentId").val();
    var description = $("#description").val();
    var classId = $("#classId").val();
    var limitHours = $("#limitHours").val();
    var classType = $("#classType").find("option:selected").val();
    var url = "/equipmentsClassification/save";
    var obj = {
        description: description,
        parentId: parentId,
        classId: classId,
        lid: lid,
        classType: classType,
        limitHours: limitHours ? limitHours : 72
    };
    var operation = (!lid) ? "添加" : "更新";
    $.post(url, obj, function (data) {
        if (data) {
            selectTreeNode(data, lid);
            $("#contentDiv").load("/equipmentsClassification/detail/" + data.id);
            $.bootstrapGrowl("设备分类信息" + operation + "成功！", {
                type: 'info',
                align: 'right',
                stackup_spacing: 30
            });
        } else {
            $.bootstrapGrowl("设备分类信息" + operation + "失败！", {
                type: 'danger',
                align: 'right',
                stackup_spacing: 30
            });
        }
    });
}


function selectTreeNode(data, add) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var parentZNode = zTree.getNodeByParam("id", data.parent.id, null); //获取父节点
    var childZNode = {
        id: data.id,
        pId: data.parent.id,
        name: data.description,
        open: data.parent.id,
        isParent: data["hasChild"]
    };
    if (add == 0) {
        zTree.addNodes(parentZNode, childZNode, true);
    } else {
        zTree.reAsyncChildNodes(null, "refresh");
    }
    zTree.selectNode(zTree.getNodeByParam("id", data.id));
}


/**
 * 删除节点信息
 */
function del() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    var url = "/equipmentsClassification/delete/" + id;

    if (id) {
        bootbox.confirm({
            message: "确定要删除该记录么？?",
            buttons: {
                confirm: {
                    label: '是',
                    className: 'btn-success'
                },
                cancel: {
                    label: '否',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        type: "GET",
                        url: url,
                        success: function (msg) {
                            if (msg) {
                                showMessageBox("info", "设备分类信息删除成功!");
                                var zTree = $.fn.zTree.getZTreeObj("tree");
                                zTree.removeNode(zTree.getSelectedNodes()[0]);
                                zTree.selectNode(zTree.getNodeByParam("id", 1));
                                showMessageBox("info", data["resultDesc"]);
                            }
                        },
                        error: function (msg) {
                            showMessageBox("danger", "设备分类有关联数据无法删除，请联系管理员");
                        }
                    });
                }
            }
        });
    }

}


function loadTable() {
    var columns = [{
        name: 'id', title: '序号'
    },
        {
            name: 'description', title: '分类描述'
        }
    ];
    var url = "/equipmentsClassification/findAll";
    $.getJSON(url, function (data) {
        loadTableData("#datatable2", columns, data);
        $("#datatable2").bootgrid({
            rowCount: 5, template: ""
        });
    });
}

/**
 * 选中设备分类节点  添加外委单位
 */
function addUnit() {
//获取选中的设备分类节点
    var selectEqClassId = getSelectedNodeId();
    //弹出框选择未添加的外委单位信息
    var url = "/equipmentsClassification/loadSelectUnitPage/" + selectEqClassId;
    $("#mBody").load(url);
    $("#unitListModal").modal("show");
}

/**
 * 选中设备分类节点  添加外委单位
 */
function addMoreUnit() {
//获取选中的设备分类节点
    var selectEqClassId = getSelectedNodeId();
    //弹出框选择未添加的外委单位信息
    var url = "/equipmentsClassification/loadSelectUnitPage/" + selectEqClassId;
    $("#mBody").load(url);
    var units = getUnitsByEqClass(selectEqClassId);
    for (var x in units) {
        if (!isNaN(units[x])) {
            $("#unitListModal input[value='" + units[x] + "'] ").each(function () {
                $(this).attr("checked", "checked");
            });
        }
    }
    //将已有的默认选中//
    $("#unitListModal").modal("show");
}


$("#confitmBtn").on("click", function () {
    //检查获取选择的ids
    var ids = "";
    $("#unitTable input[type='checkbox']").each(function (i) {
        if ($(this).is(":checked") && !isNaN($(this).val())) {
            ids += $(this).val() + ",";
        }
    });
    ids = ids.replace(/\ +/g, "").replace(/[\r\n]/g, "");
    var cid = getSelectedNodeId();
    if (!ids) {
        showMessageBox("danger", "请选择外委单位！");

    } else {
        //加入
        $("#unitListModal").modal("hide");
        var url = "/equipmentsClassification/addUnits";
        // 提示操作成功或失败
        $.post(url, {cid: cid, ids: ids}, function (data) {
            if (data) {
                $("#contentDiv").load("/equipmentsClassification/detail/" + cid);
                showMessageBox("info", "设备分类添加外委单位成功！")
            } else {
                showMessageBox("danger", "设备分类添加外委单位失败！")
            }
        });
    }
});


/**
 * 根据设备分类查询对应的外委单位ID集合
 */
function getUnitsByEqClass(cid) {
    var unitIds = [];
    var url = "/equipmentsClassification/getUnitsByEqClassId/" + cid;
    $.getJSON(url, function (data) {
        unitIds = data;
    });
    return unitIds
}


/**
 *  移除关联的外委单位
 */
function removeUnit() {
    var cid = getSelectedNodeId();
    if (!cid) {
        showMessageBoxCenter("danger", "center", "请选中要操作的设备分类信息再进行操作！");
        return;
    }
    var selectedUnitIds = [];
    $("#unitsTable input[name='unit']").each(function () {
        if ($(this).is(":checked")) {
            selectedUnitIds.push($(this).val());
        }
    });
    var ids = selectedUnitIds.join(",");
    if (!ids) {
        showMessageBoxCenter("danger", "center", "请选中外委单位信息再进行操作！");
        return;
    }
    bootbox.confirm({
        message: "确定要删除当前选中关联的外委单位信息吗?",
        buttons: {
            confirm: {
                label: '是',
                className: 'btn-success'
            },
            cancel: {
                label: '否',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                var url = "/equipmentsClassification/removeUnits";
                $.post(url, {cid: cid, ids: ids}, function (data) {
                    if (data) {
                        $("#contentDiv").load("/equipmentsClassification/detail/" + cid);
                        showMessageBox("info", "设备分类外委单位移除成功！")
                    }
                });
            }
        }
    });
}