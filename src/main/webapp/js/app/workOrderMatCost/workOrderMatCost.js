/**
 * Created by Administrator on 2016/9/29.
 */

var bootGridCfg = {
    searchSettings: {
        delay: 100,
        characters: 3
    }
};
$(function () {

    docName = "工单物料消耗信息";
    mainObject = "workOrderMatCost";
    dataTableName = "#workOrderMatCostDataTable";
    searchModel = [
        {"param": "orderLineNo", "paramDesc": "跟踪号"},
        {"param": "matName", "paramDesc": "物资名称"},
        {"param": "matModel", "paramDesc": "物资型号"}
    ];


    initSelect();
    initBootGrid(dataTableName);
    search();


    $(".dropzone").dropzone({
        paramName: "file", // The name that will be used to transfer the file
        maxFilesize: 0.5, // MB
        addRemoveLinks: true,
        dictResponseError: '上传文件错误!',
        //change the previewTemplate to use Bootstrap progress bars
        previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-sm progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>",
        success: function () {
            showMessageBox("info", "工单物资数据导入成功!");
            $("#import_modal").modal("hide");
            $(dataTableName).bootgrid("reload");
        }, error: function () {
            showMessageBox("danger", "工单物资数据导入失败!");
        }
    });
});


function refresh() {

    $("#main-content").load("workOrderMatCost/list", function () {
        showMessageBox("info", "数据已上传成功!");
    });
}
/**
 * 导入excel
 */
function importExcel() {
    $("#import_modal").modal("show");
}


/**
 * 下载excel
 */
function downExcel() {
    var url = mainObject + "/download";
    location.href = url;
}


/**
 * 导入excel
 */
function importExcelData() {

    var filePath = $("#theFilePath").val();
    if (!file) {
        showMessageBox("danger", "请选择excel文件");
        return;
    }
    var url = "/workOrderMatCost/importExcel";
    $.post(url, {filePath: filePath}, function (data) {
        if (data.result) {
            showMessageBox("info", data['resultDesc']);

        } else {
            showMessageBox("danger", data['resultDesc']);
        }
    })
}
