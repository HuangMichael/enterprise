var listTab = $('#myTab li:eq(0) a');
//数据列表
var formTab = $('#myTab li:eq(1) a');
$(function () {
    var validationConfig = {
        message: '该值无效 ',
        fields: {
            personNo: {
                message: '人员编号无效',
                validators: {
                    notEmpty: {
                        message: '人员编号不能为空!'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '人员编号长度为2到10个字符'
                    }
                }
            },
            personName: {
                message: '人员名称无效',
                validators: {
                    notEmpty: {
                        message: '人员名称不能为空!'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '人员名称长度为2到20个字符'
                    }
                }
            },
            telephone: {
                validators: {
                    notEmpty: {
                        message: '电话号码不能为空'
                    },
                    digits: {
                        message: '请输入正确的电话号码'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '电话号码长度有误'
                    }
                }
            },
            email: {
                validators: {
                    emailAddress: {
                        message: '请输入正确的邮箱'
                    }
                }
            }
        }
    };
    //初始化配置信息
    //主对象信息
    mainObject = "person";
    //列表id
    dataTableName = "#personListTable";
    //导出文档配置信息
    docName = "人员信息";
    //表单界面信息
    formName = "#detailForm";
    //查询模型
    searchModel = [{"param": "personNo", "paramDesc": "人员编号"}, {"param": "personName", "paramDesc": "人员名称"}];

    //配置动态列表

    initBootGridMenu(dataTableName, null);
    initSelect.call();
    //初始化查询所有的
    ids = findAllRecordId();
    console.log("ids------" + JSON.stringify(ids));
    selectedIds = ids;

    console.log(JSON.stringify(selectedIds));
    validateForm.call(validationConfig);

    vdm = new Vue({
        el: formName,
        data: {
            person: findById(selectedIds[pointer])
        }
    });



});


