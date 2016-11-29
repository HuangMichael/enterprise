var zTree;
var demoIframe;
var setting = {
    check: {
        enable: true,
        chkboxType: {"Y": "p", "N": "s"}
    },
    view: {
        dblClickExpand: false,
        showLine: true,
        selectedMulti: true
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
        onCheck: onCheck
    }
};
var zNodes = [];
$(function () {
    var url = "/resource/findApps";
    var pid = 0;
    var obj = null;
    $.getJSON(url, function (data) {
        for (var x = 0; x < data.length; x++) {
            if (data) {
                obj = data[x];
                pid = (!obj["parent"]) ? 0 : obj["parent"].id;
                zNodes[x] = {id: obj.id, pId: pid, name: obj.description, open: 1, isParent: 1};
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


    $("#authListTable").bootgrid(
        {
            formatters: {
                "remove": function (column, row) {
                    return '<a class="btn btn-default btn-xs"  onclick="removeAuth(' + row.id + ')" title="取消授权" ><i class="glyphicon glyphicon-remove"></i></a>'
                }
            }
        }
    );
    $("select").select2({theme: "bootstrap"});


    docName = "权限信息";
    mainObject = "authority";
    dataTableName = "#authListTable";
});


/**
 *  授权
 */
function grant() {

    var roleId = $("#role_id").val();
    var resourceIds = checkedNodeIds;
    var url = "authority/grant";
    if (!roleId) {
        showMessageBox("danger", "请选择授权角色!");
        return;
    }
    if (!resourceIds) {
        showMessageBox("danger", "请选择授权的资源信息!");
        return;
    }
    var data = {
        roleId: roleId,
        resourceIds: resourceIds
    };
    $.post(url, data, function (value) {
        if (value.result) {
            loadAuthView();
            showMessageBox("info", value["resultDesc"]);
        } else {
            showMessageBox("danger", value["resultDesc"]);
        }
    });
}


/**
 * 根据角色加载权限视图
 */
function loadAuthView() {
    var roleId = $("#role_id").val();
    var url = "/authority/loadByRole/" + roleId;

    console.log("roleId----------------" + roleId);
    console.log("url----------------" + url);
    /*
     $("#authListTable").data("url", url);
     $("#authListTable").bootgrid("reload");*/

    $("#authListTable").bootgrid();
    /* $("#authViewDiv").load(url, function (data) {

     });*/


}

var checkedNodeIds = null;
/**
 *
 * @param e
 * @param treeId 树的id
 * @param treeNode 树节点
 */
function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var nodes = zTree.getCheckedNodes(true),
        v = "";
    for (var i = 0; i < nodes.length; i++) {
        v += nodes[i].id + ",";
    }
    checkedNodeIds = v;
}


/**
 *
 * @param id 资源id
 */
function removeAuth(id) {
    var roleId = $("#role_id").val();
    var resourceId = id;
    var url = mainObject + "/revoke";
    var params = {
        roleId: roleId,
        resourceId: resourceId,
    }
    $.post(url, params, function (data) {
        showMessageBox("info", "授权取消成功!");
    });
}