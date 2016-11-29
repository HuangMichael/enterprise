/**
 * Created by Administrator on 2016/4/19.
 */

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
            var url = "/department/detail/" + treeNode.id;
            $("#contentDiv").load(url);
            return true;
        }
    }
};
var zNodes = [];
$(document).ready(function () {
    var url = "/department/findAll";
    var pid = 0;
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

    var array = [];
    $("input[name='checkbox']:checkbox").each(function () {
        if ($(this).attr("checked")) {
            array.push($(this).attr("value"));
        }
    });


    $("#selectUserBtn").on("click", function () {
        $("#selectUsers-modal").modal('hide');
        $.ajaxSettings.async = false;
        $.getJSON("/department/selectUsers", {personId: 1, departmentId: 6},
            function (msg) {

                //App.init();
                $.bootstrapGrowl("人员信息添加成功！", {
                    type: 'info',
                    align: 'right',
                    stackup_spacing: 30
                });
            });
    })
})
;


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
    var url = "/department/create/" + id;
    $("#contentDiv").load(url, function () {
        flag = true;
    });
}
/**
 *当前选中树节点id
 * */
function getSelectedNodeId() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    return id;
}
/**
 * 返回当前选中节点
 * */
function getSelectedNode() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    return selectedNode;
}




function save() {
    var array = $("#form").serializeArray();
    var objStr = '{';
    for (var x in array) {
        var name = array[x]["name"];
        var value = array[x]["value"];
        if (name && value) {
            objStr += '"' + name + '"';
            objStr += ":";
            objStr += '"' + value + '",';
        }
    }
    objStr = objStr.substring(0, objStr.length - 1);
    objStr += '}';

    console.log(objStr);
    var locations = JSON.parse(objStr);
    var url = "/department/save";
    $.ajax({
        type: "POST",
        url: url,
        data: locations,
        dataType: "JSON",
        success: function (obj) {

            var childZNode = {
                id: obj.id,
                pId: (obj.parent) ? obj.parent.id : 0,
                name: obj.description,
                line: (obj.line) ? (obj.line.id) : 5,
                location: obj.location,
                station: (obj.station) ? (obj.station.id) : 1,
                superior: obj.superior,
                isParent: obj["hasChild"]
            };

            if (locations.id) {
                updateNode(null, childZNode);
                showMessageBox("info", "部门信息更新成功");

            } else {
                addNodeAfterOperation(null, childZNode);
                showMessageBox("info", "部门信息添加成功");

            }
        },
        error: function (msg) {
            if (locations.id) {
                showMessageBox("danger", "部门信息更新失败");
            } else {
                showMessageBox("danger", "部门信息添加失败");

            }
        }
    });
}
function deleteObject() {
    if (!confirm("确定要删除该信息么？")) {
        return;
    }
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    var url = "/location/delete/" + id;
    $.getJSON(url, function (data) {

        console.log(JSON.parse(data));
        if (data) {
            var zTree = $.fn.zTree.getZTreeObj("tree");
            zTree.removeNode(zTree.getSelectedNodes()[0]);
            zTree.selectNode(zTree.getNodeByParam("id", 1));
            showMessageBox("info", "设备部门信息删除成功");
        } else {
            showMessageBox("danger", "设备部门信息删除失败");
        }

    });
}



/**
 *删除信息
 * */


function deleteObject() {
    if (!confirm("确定要删除该信息么？")) {
        return;
    }
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    var url = "/department/delete/" + id;
    $.getJSON(url, function (data) {
        zTree.removeNode(selectedNode);
        $.bootstrapGrowl("部门信息删除成功！", {
            type: 'info',
            align: 'right',
            stackup_spacing: 30
        });
        //部门
        zTree.selectNode(zTree.getNodeByParam("id", 1));
    })
}

/*  function selectUsers() {
 $("#selectUsers-modal").modal("hide");

 }*/


function getSelectUsers() {
    var str = "";
    $("checkbox").each(function () {
        if ($(this).attr("checked")) {
            str += $(this).val() + ","
        }
    });
    console.log("getSelectUsers----------------------" + str);
}
