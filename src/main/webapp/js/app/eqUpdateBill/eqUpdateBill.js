var listTab = $('#myTab li:eq(0) a');
//数据列表
var formTab = $('#myTab li:eq(1) a');

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
        "locations.id": {
            message: '位置无效',
            validators: {
                notEmpty: {
                    message: '位置不能为空!'
                }
            }
        }


        , "equipments.id": {
            message: '设备无效',
            validators: {
                notEmpty: {
                    message: '设备不能为空!'
                }
            }
        },


        "equipmentsClassification.id": {
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
        }
    }
};
$(function () {
    //设置数据有效性验证配置项
    dataTableName = "#eqUpdateDataTable";
    eqs = findMyEqs();
    formName = "#detailForm";
    mainObject = "eqUpdateBill";
    locs = findMyLoc();
    eqClasses = findEqClass();
    ids = findAllRecordId();
    initSearchDate();

    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            locs: locs,
            eqClasses: eqClasses
        }

    });


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
            eqUpdateBill: findById(selectedIds[pointer]),
            locs: locs,
            eqClasses: eqClasses,
            eqs: eqs
        }
    });


});


function eqUpdateAdd(eid) {

    vdm = new Vue({
        el: "#detailContainer",
        data: {
            myEqs: myEqs,
            locs: locs,
            eqClasses: eqClasses
        }
    });
    //重新建立模型 新建对象模型
    $.getJSON("eqUpdateBill/create/" + eid, function (data) {
        vdm.$set("budgetBill", data);
    });
    setFormReadStatus("#detailForm", false);
    formTab.tab('show');
    $("#locName").attr("readonly", "readonly");
    $("#eqClass").attr("readonly", "readonly");

}


function changeLoc(a) {
    var locationsId = $(a).val();
    var url = "/eqUpdateBill/findCByLocId/" + locationsId;
    $.getJSON(url, function (data) {
        vdm.$set("eqClasses", data);
    });
    var url = "/eqUpdateBill/findEqBy/" + locationsId + "/" + eqClasses[0].id;
    $.getJSON(url, function (data) {
        vdm.$set("myEqs", data);
    });

    //查询出该位置下对应的分类
}


/**
 *
 * @param a
 */
function changeEqc(a) {
    var lid = $("#locName").val();//获取位置id
    var cid = $(a).val();
    var url = "/eqUpdateBill/findEqBy/" + lid + "/" + cid;
    $.getJSON(url, function (data) {
        vdm.$set("myEqs", data);
    });
}

