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
            var url = "/resource/detail/" + treeNode.id;
            $("#contentDiv").load(url);
            return true;
        }
    }
};

var zNodes = [];
$(document).ready(function () {
    $.ajaxSettings.async = false;
    var url = "/resource/findAll";
    var pid = 0;
    var obj = null;
    $.getJSON(url, function (data) {
        for (var x = 0; x < data.length; x++) {
            if (data) {
                obj = data[x];
                pid = (!obj["parent"]) ? 0 : obj["parent"].id;
                zNodes[x] = {
                    id: obj.id,
                    pId: pid,
                    name: obj.description,
                    open: !pid,
                    isParent: 0
                };
            } else {
                alert("信息加载出错");
            }
        }
    });
    var t = $("#tree");
    t = $.fn.zTree.init(t, setting, zNodes);
    demoIframe = $("#testIframe");
    demoIframe.bind("load", loadReady);
    var zTree = $.fn.zTree.getZTreeObj("tree");
    zTree.selectNode(zTree.getNodeByParam("id", zNodes[0].id));

    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
            htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
            maxH = Math.max(bodyH, htmlH),
            minH = Math.min(bodyH, htmlH),
            h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }
});

var flag = false;
/**
 * 新建记录
 */
function add() {
    var tree = getTreeRoot();
    var selectedNode = tree.getSelectedNodes()[0];
    var id = selectedNode.id;
    if (!id) {
        id = 0
    }
    var url = "/resource/create/" + id;
    $("#parentId").val(id);
    $("#contentDiv").load(url);
}


function edit() {


}


/**
 * 新建记录
 */
function save() {

    var resourceName = $("#resourceName").val();
    var description = $("#description").val();

    if (!resourceName) {
        showMessageBox("danger", "资源名称不能为空!");
        $("#resourceName").focus();
    }
    if (!description) {
        showMessageBox("danger", "资源描述不能为空!");
        $("#description").focus();
    }
    var obj = getFormJsonData("resourceForm");
    var resource = JSON.parse(obj);
    var url = "resource/save";
    $.post(url, resource, function (data) {

        if (data) {

            showMessageBox("info", "资源保存成功!");
        } else {

            showMessageBox("danger", "资源保存失败!")
        }

    })


}


/**
 *  删除位置信息
 */
function del() {
    if (!confirm("确定要删除该信息么？")) {
        return;
    }
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    var url = "/resource/delete/" + id;


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
                                showMessageBox("info", "资源信息删除成功!");
                                var zTree = $.fn.zTree.getZTreeObj("tree");
                                zTree.removeNode(zTree.getSelectedNodes()[0]);
                                zTree.selectNode(zTree.getNodeByParam("id", 1));
                            }
                        },
                        error: function (msg) {
                            showMessageBox("danger", "资源信息有关联数据，无法删除，请联系管理员");
                        }
                    });
                }
            }
        });
    } else {
        showMessageBoxCenter("danger", "center", "请选中一条记录再操作");
    }
}