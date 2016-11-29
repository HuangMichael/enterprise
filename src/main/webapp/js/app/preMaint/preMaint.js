var listTab = $('#myTab li:eq(0) a');
var formTab = $('#myTab li:eq(1) a');
$.ajaxSettings.async = false;
var validationConfig = {
    message: '该值无效 ',
    fields: {

        pmCode: {
            message: '预防性维修计划编号无效',
            validators: {
                notEmpty: {
                    message: '预防性维修计划编号不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '预防性维修计划编号长度为3到20个字符'
                }
            }
        },


        description: {
            message: '预防性维修计划描述无效',
            validators: {
                notEmpty: {
                    message: '预防性维修计划描述不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '预防性维修计划描述长度为3到20个字符'
                }
            }
        },
        "locations.id": {
            message: '设备位置无效',
            validators: {
                notEmpty: {
                    message: '设备位置不能为空!'
                }
            }
        },

        "equipment.id": {
            message: '设备无效',
            validators: {
                notEmpty: {
                    message: '设备不能为空!'
                }
            }
        },
        "frequency": {
            message: '频率设置无效',
            validators: {
                notEmpty: {
                    message: '频率不能为空!'
                },
                min: {
                    message: '频率必须大于0'
                }
            }
        },
        "unit": {
            message: '计划执行频率单位无效',
            validators: {
                notEmpty: {
                    message: '计划执行频率单位不能为空!'
                }
            }
        },
        "outUnit.id": {
            message: '维修单位无效',
            validators: {
                notEmpty: {
                    message: '维修单位不能为空!'
                }
            }
        }
    }
};
$(function () {

    mainObject = "preMaint";
    dataTableName = "#pmDataTable";
    formName = "#detailForm";


    locs = findMyLoc();
    eqClasses = findEqClass();
    eqs = findMyEqs();
    units = findUnits();
    var f_units = [{key: "0", text: "日"}, {key: "1", text: "月"}, {key: "2", text: "年"}];


    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            locs: locs
        }
    });

    ids = findAllRecordId();
    docName = "预防性维修计划信息";
    searchModel = [
        {"param": "pmDesc", "paramDesc": "计划描述"},
        {"param": "location", "paramDesc": "设备位置"}
    ];

    //初始化从数据库获取列表数据
    initBootGrid(dataTableName);
    initSelect.call();
    initSearchDate();
    //初始化查询所有的
    selectedIds = ids;
    validateForm.call(validationConfig);
    vdm = new Vue({
        el: formName,
        data: {
            preMaint: findById(selectedIds[pointer]),
            eqClasses: eqClasses,
            eqs: eqs,
            units: units,
            f_units: f_units
        }
    });


});


/**
 * 生成工单
 */
function popGen() {
    var selected = $(dataTableName).bootgrid("getSelectedRows");
    if (autoScheduled()) {
        bootbox.confirm({
            message: "预防性维修调度已启用，确定要手动执行生成么?",
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
                    $("#confirm_modal").modal("show");
                }
            }
        });

    } else {

        if (selected.length == 0) {
            showMessageBox("danger", "请选择要执行的计划信息！");
            return;
        } else {
            $("#confirm_modal").modal("show");
        }
    }
}


/**
 * 生成工单
 */
function generateOrder() {
    var deadLine = $("#deadLine").val();
    var url = "/preMaint/executeGenerate";
    var selected = $(dataTableName).bootgrid("getSelectedRows");
    var obj = {
        deadLine: deadLine,
        ids: selected.toString()
    };
    $.post(url, obj, function (data) {
        $("#confirm_modal").modal("hide");
        if (data.result) {
            showMessageBox("info", data["resultDesc"]);
        } else {
            showMessageBox("danger", data["resultDesc"]);
        }
    });
}


/**
 *  查看调度启用状态
 * @returns {boolean}
 */
function autoScheduled() {
    var autoScheduled = false;
    var url = "preMaint/autoScheduled";
    $.getJSON(url, function (data) {
        autoScheduled = data;
    });
    return autoScheduled;

}






