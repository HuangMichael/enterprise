var zTree;
appType = appTypeArray[1];  //tree
var demoIframe;
var reportModel = null;
var eqClasses = [];
var setting = {
    check: {enable: false},
    view: {dblClickExpand: false, showLine: true, selectedMulti: false},
    data: {simpleData: {enable: true, idKey: "id", pIdKey: "pId", rootPId: ""}},
    callback: {
        onClick: function (event, treeId, treeNode) {
            fillForm(treeNode.id);
            loadEqList(treeNode.id);
            return true
        }
    }
};
var zNodes = [];
var validationConfig = {
    message: '该值无效 ',
    fields: {
        "location": {
            message: '位置编号无效',
            validators: {
                notEmpty: {
                    message: '位置编号不能为空!'
                }
                ,
                stringLength: {
                    min: 2,
                    max: 20,
                    message: '位置编号长度为2到20个字符'
                }
            }
        }
        ,
        "description": {
            message: '位置描述无效',
            validators: {
                notEmpty: {
                    message: '位置描述不能为空!'
                }
                ,
                stringLength: {
                    min: 2,
                    max: 20,
                    message: '位置描述长度为2到20个字符'
                }
            }
        }
    }
};


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
            };
        }

        var t = $("#tree");
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        zTree = $.fn.zTree.getZTreeObj("tree");
        zTree.selectNode(zTree.getNodeByParam("id", zNodes[0]));
        //firstLoad(zNodes[0]);


        afterClick.call(zNodes[0]["id"]);


    });
    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight, htmlH = demoIframe.contents().find("html").get(0).scrollHeight, maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH), h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) {
            h = 530
        }
        demoIframe.height(h)
    }


    dataTableName = "#eqDataTable";
    formName = "#detailForm";
    mainObject = "location";
    docName = "设备信息";
    exportObject = "equipment";
    validateForm(validationConfig);

    vdm = new Vue({
        el: formName
    });


    initSelect();
});
var flag = false;


function add() {
    var parent = addNode();
    var url = mainObject + "/create/" + parent.id;
    $.getJSON(url, function (data) {
        vdm.$set(mainObject, data);
    })
    setFormReadStatus(formName, false);
}


var reportId;
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
            eqId = id;
            reportId = id;
        })
    } else {
        equipReport(id);
    }
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


/**
 *  删除位置信息
 */
function del() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var selectedNode = zTree.getSelectedNodes()[0];
    var id = selectedNode.id;
    var url = "/location/delete/" + id;

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
                            if (msg.result) {
                                showMessageBox("info", msg["resultDesc"]);
                                var zTree = $.fn.zTree.getZTreeObj("tree");
                                zTree.removeNode(zTree.getSelectedNodes()[0]);
                                zTree.selectNode(zTree.getNodeByParam("id", 1));
                            }
                        },
                        error: function (msg) {
                            showMessageBox("danger", msg["resultDesc"]);
                        }
                    });
                }
            }
        });
    }
}

/**
 * 位置保修
 */
function reportByLocation() {
    var location = getSelectedNode().location;
    var locationId = getSelectedNode().id;
    var status = "0";
    var path = "/location/findById/" + locationId;
    $.getJSON(path, function (data) {
        status = data["status"]
    });
    if (!location) {
        showMessageBox("danger", "请先选中位置再进行报修操作!");
        return
    }
    var url = "/commonData/findVEqClass";
    $.getJSON(url, function (data) {
        eqClasses = data;
    });
    //新建一个数据模型
    //初始化请求设备分类
    reportModel = new Vue({
        el: "#locReportForm",
        data: {
            eqClasses: eqClasses

        }
    });
    if (status == "2") {
        var curl = "/workOrderReportCart/loadReportedLocPage/" + location;
        $("#locList").load(curl, function (data) {
            $("#show_loc_modal").modal("show")
        })
    } else if (status == "1") {
        $("#rptLoc").val(getSelectedNode().name);
        $("#loc_modal").modal("show");

    }
}
/**
 *  已经报修提示重复报修 选择继续
 */
function continueLocReport() {
    $("#show_loc_modal").modal("hide");
    //再次报修时  将原来的输入清空
    $("#orderDesc").val("");
    $("#rptLoc").val(getSelectedNode().name);
    $("#loc_modal").modal("show");
}

/**
 * 加入位置报修
 */
function add2LocCart() {
    var nodeId = getSelectedNodeId();
    var url = "/workOrderReportCart/add2LocCart";
    var orderDesc = $("#orderDesc").val();
    var reporter = $("#reporter").val();
    var creator = $("#creator").val();
    var eqClassId = $("#equipmentsClassification_id").val();

    if (!nodeId) {
        showMessageBox("danger", "请选中报修位置!");
        return;
    }

    if (!orderDesc) {
        showMessageBox("danger", "请输入报修故障描述!");
        $("#orderDesc").focus();
        $("#orderDesc").css("border", "dashed 1px red");
        return;
    }
    if (!reporter) {
        showMessageBox("danger", "报修人不能为空!");
        $("#reporter").focus();
        $("#reporter").css("border", "dashed 1px red");
        return
    }


    var obj = {locationId: nodeId, orderDesc: orderDesc, reporter: reporter, creator: creator, eqClassId: eqClassId};
    $.post(url, obj, function (data) {
        $("#loc_modal").modal("hide");
        var size = $("#reportOrderSize").html();
        if (!size) {
            size = 0
        }
        $("#reportOrderSize").html(parseInt(size) + 1);
        showMessageBox("info", "已将位置报修加入到报修车!")
    });

}
/**
 *
 * @param data
 * 首次加载函数 在form中显示第一条记录内容
 */
/*
 function firstLoad(data) {
 if (data.length > 0) {
 $("#form #lid").val(data.id);
 $("#form #location").val(data.location);
 $("#form #description").val(data.name);
 $("#form #superior").val(data.superior);
 //$("#parent_id").val(null).attr("readonly", "readonly");

 }
 }
 */


/**
 *
 * @param id 点击树节点触发
 */
function afterClick(id) {
    fillForm(id);
    loadEqList(id);
}

/**
 *
 * @param id 同步选中数据
 */
function fillForm(id) {
    var location = findById(id);
    vdm.$set(mainObject, location);
    setFormReadStatus(formName, true);
}


/**
 *
 * @param locationId
 */
function loadEqList(locationId) {
    //载入页面并刷新
    var url = "location/detail/" + locationId;
    $("#tab_1_1").load(url, function (data) {

    });
    $("#eqDataTable").bootgrid({
        rowCount: [8],
        formatters: {
            "report": function (column, row) {
                return '<a class="btn btn-default btn-xs"  onclick="report(' + row.id + ')" title="报修" ><i class="glyphicon glyphicon-wrench"></i></a>';
            }
        }
    });
}
