var listTab = $('#myTab li:eq(0) a');
//数据列表
var formTab = $('#myTab li:eq(1) a');
pointer = 0;
var validationConfig = {
    message: '该值无效 ',
    fields: {
        unitNo: {
            message: '单位编号无效',
            validators: {
                notEmpty: {
                    message: '单位编号不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '单位编号长度为3到20个字符'
                }
            }
        },
        description: {
            message: '单位名称无效',
            validators: {
                notEmpty: {
                    message: '单位名称不能为空!'
                },
                stringLength: {
                    min: 2,
                    max: 20,
                    message: '单位名称长度为2到20个字符'
                }
            }
        },
        "status": {
            message: '单位状态无效',
            validators: {
                notEmpty: {
                    message: '单位状态不能为空!'
                }
            }
        }
    }
}


$(function () {

    dataTableName = "#unitsDataTable";
    mainObject = "units";
    formName = "#detailForm";
    docName = "外委单位信息";

    searchModel = [{"param": "description", "paramDesc": "单位名称"}, {"param": "linkMan", "paramDesc": "联系人"}];
    initBootGrid(dataTableName);
    initSelect();
    //初始化查询所有的
    ids = findAllRecordId();
    selectedIds = ids;
    validateForm.call(validationConfig);
    vdm = new Vue({
        el: formName,
        data: {
            units: findById(selectedIds[pointer])
        }
    });
})


