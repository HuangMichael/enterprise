//数据列表
var listTab = $('#myTab li:eq(0) a');
//数据列表
var formTab = $('#myTab li:eq(1) a');
//设置数据有效性验证配置项
var validationConfig = {
    message: '该值无效 ',
    fields: {
        "applicant": {
            message: '申请人无效',
            validators: {
                notEmpty: {
                    message: '申请人不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '1到20个字符'
                }
            }
        },
        "applyDep": {
            message: '申请部门无效',
            validators: {
                notEmpty: {
                    message: '申请部门不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '1到20个字符'
                }
            }
        },
        "amount": {
            message: '申请数量无效',
            validators: {
                notEmpty: {
                    message: '申请数量不能为空!'
                }
            }
        },
        "accessoryName": {
            message: '配件名称无效',
            validators: {
                notEmpty: {
                    message: '配件名称不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '1到20个字符'
                }
            }
        },
        "specifications": {
            message: '规格型号无效',
            validators: {
                notEmpty: {
                    message: '规格型号不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '1到20个字符'
                }
            }
        },
        "purpose": {
            message: '用途无效',
            validators: {
                notEmpty: {
                    message: '用途不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 50,
                    message: '1到50个字符'
                }
            }
        },
        "vlocations.id": {
            message: '位置无效',
            validators: {
                notEmpty: {
                    message: '位置不能为空!'
                }
            }
        },
        "eqClass.id": {
            message: '设备分类无效',
            validators: {
                notEmpty: {
                    message: '设备分类不能为空!'
                }
            }
        },
        "approver": {
            message: '批准人无效',
            validators: {
                notEmpty: {
                    message: '批准人不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 50,
                    message: '1到50个字符'
                }
            }
        },
        "handler": {
            message: '经办人无效',
            validators: {
                notEmpty: {
                    message: '经办人不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 50,
                    message: '1到50个字符'
                }
            }
        },
        "receiver": {
            message: '接收人无效',
            validators: {
                notEmpty: {
                    message: '接收人不能为空!'
                },
                stringLength: {
                    min: 1,
                    max: 50,
                    message: '1到50个字符'
                }
            }
        },
    }
};
$(function () {


    locs = findMyLoc();
    eqClasses = findEqClass();
    mainObject = "budget";
    dataTableName = "#budgetDataTable";
    docName = "采购申请信息";
    formName = "#detailForm";
    searchModel = [
        {"param": "beginDate", "paramDesc": "申请日期"},
        {"param": "endDate", "paramDesc": "申请日期"},
        {"param": "accessoryName", "paramDesc": "配件名称"},
        {"param": "applyDep", "paramDesc": "申请部门"}
    ];

    //初始化从数据库获取列表数据

    initBootGrid(dataTableName);
    initSelect.call();
    initSearchDate();
    //初始化查询所有的
    ids = findAllRecordId();
    selectedIds = ids;
    validateForm.call(validationConfig);

    vdm = new Vue({
        el: formName,
        data: {
            budget: findById(selectedIds[pointer]),
            locs: locs,
            eqClasses: eqClasses
        }
    });

});


