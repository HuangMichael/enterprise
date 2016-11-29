/**
 * Created by Administrator on 2016/4/12.
 */
$(document).ready(function () {
    //$("#datatable1").bootgrid();

    loadList("#datatable1");

});

function reload() {
    $("#contentDiv").load("/workOrder/list");
}


$(".modal").on("hide.bs.modal", function () {

    $("#contentDiv").load("/workOrder/list");
});

var workOrderIdArray = "";
$("#shareWorkOrder").on("click", function () {
    shareWorkOrder();
});

$("#finishWorkOrderBtn").on("click", function () {
    finishWorkOrderBatch();
});

$("#assessWorkOrder").on("click", function () {
    assessWorkOrderBatch();
});


function shareWorkOrder() {

    if (!workOrderIdArray) {
        $.bootstrapGrowl("请选择您要分配的工单！", {
            type: 'danger',
            align: 'right',
            stackup_spacing: 30
        });
        return;
    }
    var limitedHours = $("#limitedHours").val();
    var serviceMan = $("#serviceMan").val();
    var serviceTelephone = $("#serviceTelephone").val();
    var serviceCompany = $("#serviceCompany").val();
    var unit_id = $("#unit_id").find("option:selected").val();

    var data = {
        limitedHours: limitedHours,
        serviceCompany: serviceCompany,
        serviceMan: "",
        serviceTelephone: "",
        workOrderIdArray: workOrderIdArray,
        unit_id: unit_id
    };
    var url = "/workOrder/shareWorkOrderBatch";
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function (msg) {

            reload();
            $.bootstrapGrowl(msg.length + "条工单信息分配成功！", {
                type: 'info',
                align: 'right',
                stackup_spacing: 30
            });
        },
        error: function () {
            $.bootstrapGrowl("工单信息分配失败！", {
                type: 'danger',
                align: 'right',
                stackup_spacing: 30
            });
        }
    });
}


function finishWorkOrderBatch() {
    if (!workOrderIdArray) {
        $.bootstrapGrowl("请选择要确认完工的工单！", {
            type: 'danger',
            align: 'right',
            stackup_spacing: 30
        });
        return;
    }
    var url = "/workOrder/finishWorkOrderBatch";
    $.post(url, {workOrderIdArray: workOrderIdArray}, function (data) {
        reload();
        $.bootstrapGrowl(data.length + "条工单维修已完成！", {
            type: 'info',
            align: 'right',
            stackup_spacing: 30
        });
    });
}


function assessWorkOrderBatch() {

    if (!workOrderIdArray) {
        $.bootstrapGrowl("请选择要评价的工单！", {
            type: 'danger',
            align: 'right',
            stackup_spacing: 30
        });
        return;
    }
    var assessLevel = $("#assessLevel option:selected").val();
    var url = "/workOrder/assessWorkOrderBatch";
    $.post(url, {workOrderIdArray: workOrderIdArray, assessLevel: assessLevel}, function (data) {

        reload();
        $.bootstrapGrowl("工单评价已完成,感谢您的参与！", {
            type: 'info',
            align: 'right',
            stackup_spacing: 30
        });
    });
}


$("[name='workOrderSel']:checkbox").on("click", function () {
    if ($(this).is(":checked")) {
        workOrderIdArray += $(this).attr("data-woId") + ",";
    } else {
        workOrderIdArray = workOrderIdArray.replace($(this).attr("data-woId") + ",", "");
    }
    console.log(workOrderIdArray);
});


$('#search-workOrder').on('hide.bs.modal', function () {
    $("#contentDiv").load("/workOrder/list", function () {
    });
});

$("#select1 dd").click(function () {

    $(this).addClass("selected").siblings().removeClass("selected");
    if ($(this).hasClass("select-all")) {
        $("#selectA").remove();
    } else {
        var copyThisA = $(this).clone();
        if ($("#selectA").length > 0) {
            $("#selectA a").html($(this).text());
        } else {
            $(".select-result dl").append(copyThisA.attr("id", "selectA"));
        }
    }
});

function selectLine(element) {

    $(element).addClass("selected").siblings().removeClass("selected");
    if ($(element).hasClass("select-all")) {
        $("#selectA").remove();
    } else {
        var copyThisA = $(element).clone();
        if ($("#selectA").length > 0) {
            $("#selectA a").html($(element).text());
        } else {
            $(".select-result dl").append(copyThisA.attr("id", "selectA"));
        }
    }


}


$("#select2 dd").click(function () {
    $(this).addClass("selected").siblings().removeClass("selected");
    if ($(this).hasClass("select-all")) {
        $("#selectB").remove();
    } else {
        var copyThisB = $(this).clone();
        if ($("#selectB").length > 0) {
            $("#selectB a").html($(this).text());
        }
        else {
            $(".select-result dl").append(copyThisB.attr("id", "selectB"));
        }
    }
});

$("#select3 dd").click(function () {
    $(this).addClass("selected").siblings().removeClass("selected");
    if ($(this).hasClass("select-all")) {
        $("#selectC").remove();
    } else {
        var copyThisC = $(this).clone();
        if ($("#selectC").length > 0) {
            $("#selectC a").html($(this).text());
        }
        else {
            $(".select-result dl").append(copyThisC.attr("id", "selectC"));
        }
    }
});

$("#selectA").on("click", function () {
    $(this).remove();
    $("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
});

$("#selectB").on("click", function () {
    $(this).remove();
    $("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
});

$("#selectC").on("click", function () {
    $(this).remove();
    $("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
});

$(".select dd").on("click", function () {
    if ($(".select-result dd").length > 1) {
        $(".select-no").hide();
    } else {
        $(".select-no").show();
    }
});


$("#showSearch").on("click", function () {
    //初始化加载线路
    initLine();
    $("#searchBar").toggle("fast", function () {
        var iconUp = '<i id="btnIcon" class="glyphicon glyphicon-arrow-up">高级搜索</i>';
        var iconDown = '<i id="btnIcon" class="glyphicon glyphicon-arrow-down">高级搜索</i>';
        if ($("#showSearch").html() == iconUp) {
            $($("#showSearch")).html(iconDown);
        } else {
            $($("#showSearch")).html(iconUp);
        }
    });
});

function initLine() {
    $("#select1").html('<dt>路线：</dt><dd><a id="selectall1" class="selected select-all">全部</a></dd>');
    var url = '/line/lines';
    var lineStr = $("#select1").html();
    var elementHtml = "";
    $.getJSON(url, {}, function (data) {
        for (var x in data) {
            elementHtml += "<dd><a id='line" + data[x].id + "' onclick=addLine('" + data[x].id + "','" + data[x].description + "') onmouseover='initStation(" + data[x].id + ")'>" + data[x].description + "</a></dd>";
        }
        $("#select1").html(lineStr + elementHtml);
    });
}


function initStation(lineId) {
    var url = '/station/findStationByLine/' + lineId;
    var lineStr = $("#select2").html();
    var elementHtml = "";
    elementHtml += '<dt>车站：</dt><dd><a>全部</a></dd>';
    $.getJSON(url, function (data) {
        for (var x in data) {
            elementHtml += "<dd><a onclick=addStation('" + data[x].id + "','" + data[x].description + "')>" + data[x].description + "</a></dd>";
        }
        $("#select2").html(elementHtml);
    });
}
$("#selectall1").on("click", function () {
    alert("all select");
});

$("#selectall2").on("click", function () {
    alert("all select");
});


var line_id, station_id = null;
function addLine(id, desc) {

    $("#line" + id).siblings().removeClass("selected").addClass("selected");
    $("#cell1").empty().html("<a onclick=remove(this,'l') style='background-color:#f60;18px;margin-right: 8px;color:#fff'>" + desc + "</a>");
    $("#cell2").empty();
    $("select-all").removeClass();
    line_id = id;

}

function addStation(id, desc) {
    $("#station" + id).siblings().removeClass("selected").addClass("selected");
    $("#cell2").empty().html("<a onclick=remove(this,'s') style='background-color: #f60;18px;margin-right: 8px;color:#fff'>" + desc + "</a>");
    station_id = id;
}


$("#searchBtn").on("click", function () {
    // $("#resultListDiv").html("");
    var url = "/workOrder/search/" + line_id + "/" + station_id;
    console.log("url-------------" + url);
    /* if (!line_id && !station_id){
     url = "/workOrder/search/" + line_id + "/" + station_id;
     }
     if (!line_id && station_id){
     url = "/workOrder/search/0/" + station_id;
     }
     if (line_id && !station_id){
     url = "/workOrder/search/"+line_id;
     }
     if (line_id && !station_id){
     url = "/workOrder/search/all";
     }*/

    $("#resultListDiv").load(url, function (data) {
    });

});

function remove(obj, tag) {
    if (tag == 'l') {
        line_id = 0;
    }
    if (tag == 's') {
        station_id = 0;
    }
    $(obj).parent().remove();
}



/**
 *重新刷新表格
 */
function loadList(tableName) {
    $(tableName).bootgrid({
        formatters: {
            "detail": function (column, row) {
                var conId = row.id;
                return '<a class="btn btn-default btn-xs" onclick ="detail(' + conId + ')">查看</a>';
            },
            "edit": function (column, row) {
                var conId = row.id;

                return '<a class="btn btn-default btn-xs" onclick ="edit(' + conId + ')">编辑</a>';
            }
        }
    });

}