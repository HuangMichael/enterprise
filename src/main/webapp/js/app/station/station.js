var listTab = $('#myTab li:eq(0) a');
//数据列表
var formTab = $('#myTab li:eq(1) a');
var validationConfig = {
    message: '该值无效 ',
    fields: {
        stationNo: {
            message: '车站编号无效',
            validators: {
                notEmpty: {
                    message: '车站编号不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '车站编号长度为3到20个字符'
                }
            }
        },
        description: {
            message: '车站描述无效',
            validators: {
                notEmpty: {
                    message: '车站描述不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '车站描述长度为3到20个字符'
                }
            }
        }
    }
};

$(function () {


    var lines = getAllLines();
    var types = [{"id": "1", "typeName": "站区"}, {"id": "2", "typeName": "段区"}];
    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            lines: lines
        }
    });


    mainObject = "station";
    dataTableName = '#stationListTable';
    formName = "#detailForm";
    searchModel = [
        {
            "param": "stationNo",
            "paramDesc": "车站编号"
        },
        {
            "param": "description", "paramDesc": "车站名称"
        },
        {"param": "line", "paramDesc": "线路"}
    ];

    initBootGrid(dataTableName);
    initSelect();
    //初始化查询所有的
    ids = findAllRecordId();
    selectedIds = ids;
    validateForm.call(validationConfig);
    vdm = new Vue({
        el: "#detailForm",
        data: {
            station: findById(selectedIds[pointer]),
            lines: lines,
            types: types,
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