var locs = [];
var eqs = [];
var persons = [];
var listTab = $('#myTab li:eq(0) a');
var formTab = $('#myTab li:eq(1) a');
var object = null;
formName = "#detailForm";
$.ajaxSettings.async = false;
var validationConfig = {
    message: '该值无效 ',
    fields: {
        userName: {
            message: '用户名号无效',
            validators: {
                notEmpty: {
                    message: '用户名!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '用户名长度为3到20个字符'
                }
            }
        }
    }
};

$(function () {
    dataTableName = '#userDataTable';
    docName = "用户信息";
    mainObject = "user";
    //初始化从数据库获取列表数据


    searchModel = [{"param": "userName", "paramDesc": "用户名称"}, {"param": "location", "paramDesc": "位置"}];


    locs = findMyLoc();

    var person_location = "/commonData/findActivePerson";
    $.getJSON(person_location, function (data) {
        persons = data;
    });

    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            locs: locs
        }
    });
    initBootGridMenu(dataTableName, null);
    initSelect.call();
    //初始化查询所有的
    ids = findAllRecordId();
    selectedIds = ids;
    validateForm.call(validationConfig);

    vdm = new Vue({
        el: formName,
        data: {
            user: findById(selectedIds[pointer]),
            locs: locs,
            persons: persons
        }
    });
});






