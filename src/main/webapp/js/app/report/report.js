$(document).ready(function() {
    $('#fixListTable').bootgrid({
        columnSelection: 1,
        rowCount: [10, 20, 25, -1]
    });

    $("#myTab a").on("click", function(e) {
        e.preventDefault();
        preview(1);
        $(this).tab('show');
    })

});

function finish(id) {
    var fixDesc = $("#fixDesc" + id).val();
    if(!fixDesc) {
        showMessageBox("danger", "请输入维修描述!");
        $("#fixDesc" + id).focus();
        return;
    }
    var url = "/workOrderFix/finishDetail";
    $.post(url, {
        fixId: id,
        fixDesc: fixDesc
    }, function(data) {
        (data.result) ? showMessageBox("info", data.resultDesc): showMessageBox("danger", data.resultDesc);
    });

}

function pause(id) {
    var fixDesc = $("#fixDesc" + id).val();
    if(!fixDesc) {
        $("#fixDesc" + id).focus();
        return;
    }
    var url = "/workOrderFix/pauseDetail";
    $.post(url, {
        fixId: id,
        fixDesc: fixDesc
    }, function(data) {
        (data.result) ? showMessageBox("info", data.resultDesc): showMessageBox("danger", data.resultDesc);
    });

}

/**
 * 全选 全不选
 * @param obj
 */
function checkAll(obj) {
    var checkName = $(obj).attr("name");
    $("input[name^='" + checkName + "']").prop('checked', $(obj).prop('checked'));
}

/**
 * 生成维修单
 */
function generateFixRpt() {
    var orderReportList = [];
    $("input[name^='check']").each(function() {
        var value = $(this).val();
        if($(this).is(":checked") && !isNaN(value)) {
            orderReportList.push(value);
        } else {
            orderReportList.push(null);
        }

    });
    var ids = orderReportList.join(",");
    var url = "/workOrderReport/mapByType";
    $.post(url, {
        ids: ids
    }, function(data) {
        if(data) {
            showMessageBox("info", "维修报告单已生成!");
        }
    });
}

/**
 *
 * @param id 预览
 */
function preview(id) {
    PDFObject.embed("/report/report.pdf", "#pdf_view", {
        width: "100%",
        height: "750px"
    });
}