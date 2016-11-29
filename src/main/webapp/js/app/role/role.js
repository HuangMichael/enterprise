var locs = [];
var eqs = [];
var persons = [];
var listTab = $('#myTab li:eq(0) a');
var formTab = $('#myTab li:eq(1) a');
var object = null;

$.ajaxSettings.async = false;
var validationConfig = {
    message: '该值无效 ',
    fields: {
        roleName: {
            message: '角色名称无效',
            validators: {
                notEmpty: {
                    message: '角色名称不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '角色名称长度为3到20个字符'
                }
            }
        },
        roleDesc: {
            message: '角色描述无效',
            validators: {
                notEmpty: {
                    message: '角色描述不能为空!'
                },
                stringLength: {
                    min: 3,
                    max: 20,
                    message: '角色描述长度为3到20个字符'
                }
            }
        }
    }
};

$(function () {
    formName = "#detailForm";
    dataTableName = '#roleListTable';
    docName = "角色信息";
    mainObject = "role";
    //初始化从数据库获取列表数据
    searchModel = [{"param": "roleName", "paramDesc": "角色名称"}, {"param": "roleDesc", "paramDesc": "角色描述"}];
    locs = findMyLoc();
    var person_location = "/commonData/findActivePerson";
    $.getJSON(person_location, function (data) {
        persons = data;
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
            role: findById(selectedIds[pointer]),
            locs: locs,
            persons: persons
        }
    });


    //含有子列表加载的  需要重写

    $(formTab).on("click", function () {
        showDetail();
    })


});


/**
 *  显示明细
 */
function showDetail() {
    pointer = pointer ? pointer : 0;
    var object = findById(selectedIds[pointer]);
    vdm.$set(getMainObject(), object);
    setFormReadStatus(formName, true);
    loadUsers(selectedIds[pointer]);
}


/**
 *
 * @param roleId
 * 根据角色载入包含用户
 */
function loadUsers(roleId) {
    if (!roleId) {
        showMessageBox("danger", "角色信息获取失败，请重新尝试!");
        return;
    }
    var url = "role/findUsersOfRole/" + roleId;
    var html = '<table  class="table table-striped table-bordered table-hover">';
    $.getJSON(url, function (data) {
        for (var x in data) {
            var id = data[x]['id'];
            if (id) {
                html += '<tr  id="tr' + id + '" style="height: 12px">';
                html += '<td width="5%">' + Number(Number(x) + Number(1)) + '</td>';
                html += '<td width="20%">' + data[x]['userName'] + '</td>';
                html += '<td width="20%"><a class="btn btn-default btn-xs"  onclick="removeUser(' + roleId + "," + id + ')" title="移除" ><i class="glyphicon glyphicon-remove"></i></a></td>';
                html += '</tr>';
            }
        }
        html += "</table>";
        $("#usersDiv").html(html);
    });

}


/**
 *移除用户
 */
function removeUser(roleId, userId) {

    var url = "role/removeUser/";
    var data = {
        roleId: roleId,
        userId: userId
    };
    if (userId) {
        bootbox.confirm({
            message: "确定要从当前角色中删除该用户么？?",
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
                    $.post(url, data, function (msg) {
                        if (msg.result) {
                            showMessageBox("info", "用户移除成功!");
                            $("#tr" + userId).remove();
                        } else {
                            showMessageBox("danger", "用户移除失败!");
                        }
                    });
                }
            }
        });

    }


}


function addUsers() {
    var roleId = vdm.role.id;
    var url = "role/popUsers/" + roleId;
    $("#mBody").load(url,
        function (data) {
            $("#userListModal").modal("show");
        });

    //ajax请求数据和页面 弹出
}

function confirmAddUsers() {
    var roleId = vdm.role.id;
    ;
    $("#userListModal").modal("hide");
    var usersIdStr = $("#usersNotInRole").bootgrid("getSelectedRows").join(",");
    // ajax将选中的用户进行与角色关联
    var url = "role/addUser";
    var data = {
        roleId: roleId,
        usersIdStr: usersIdStr
    };
    $.post(url, data, function (data) {
        if (data.result) {
            loadUsers(roleId);
            showMessageBox("info", data["resultDesc"]);
        }
    });
}


