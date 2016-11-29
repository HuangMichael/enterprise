//数据列表
var listTab = $('#myTab li:eq(0) a');
//数据列表
var formTab = $('#myTab li:eq(1) a');
$.ajaxSettings.async = false;
var validationConfig = {
    message: '该值无效 ',
    fields: {
        lineNo: {
            message: '线路编号无效',
            validators: {
                notEmpty: {
                    message: '线路编号不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '线路编号长度为3到20个字符'
                }
            }
        },
        description: {
            message: '线路描述无效',
            validators: {
                notEmpty: {
                    message: '线路描述不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '线路描述长度为3到20个字符'
                }
            }
        },
        type: {
            message: '线路类型无效',
            validators: {
                notEmpty: {
                    message: '线路类型不能为空!'
                }
            }
        }
    }
};

$(function () {
    var dataTableName = '#lineListTable';
    mainObject = "line";
    docName = "线路信息";
    formName = "#detailForm";
    searchModel = [{"param": "lineNo", "paramDesc": "线路编号"}, {"param": "description", "paramDesc": "线路名称"}];
    initBootGrid(dataTableName);
    initSelect();
    //初始化查询所有的
    ids = findAllRecordId();
    selectedIds = ids;
    validateForm.call(validationConfig);
    vdm = new Vue({
        el: "#detailForm",
        data: {
            line: findById(selectedIds[pointer]),
            type: [{
                key: '1', value: '站区'

            }, {
                key: '2', value: '段区'

            }],
            statuses: [{
                key: '1', value: '启用'

            }, {
                key: '0', value: ''

            }]
        }
    });


});