<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table cellpadding="0" cellspacing="0" border="0"
       class="simple_page table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>选择</th>
        <th>序号</th>
        <th>用户名</th>
        <th>使用状态</th>
        <th>排序</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usersList}" var="user" varStatus="s">
        <tr class="gradeX">
            <td><input type="checkbox" name="selUser" data-userId="${user.id}"></td>
            <td>${s.index+1}</td>
            <td>
                    ${user.userName}
            </td>
            <td class="center">${user.status}</td>
            <td class="center">${user.sortNo}</td>

        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    </tfoot>
</table>

<div class="modal-footer">
    <button type="button" class="btn btn-default"
            data-dismiss="modal">关闭
    </button>
    <button type="button" id="confirmSelect" name="confirmSelect"
            class="btn btn-primary">确定
    </button>
</div>
<script>
    jQuery(document).ready(function () {
        $("#confirmSelect").on("click", function () {
            var url = "/groups/add2Group";

            console.log("userIdStrArray-------------"+userIdStrArray);
            console.log("groupIdStrArray-------------"+groupIdStrArray);
            $.post(url, {userIdStrArray: userIdStrArray, groupId: groupIdStrArray}, function (data) {

                if (data) {
                    $.bootstrapGrowl("用户组添加用户信息成功！", {
                        type: 'info',
                        align: 'right',
                        stackup_spacing: 30
                    });
                } else {
                    $.bootstrapGrowl("用户组添加用户信息失败！", {
                        type: 'danger',
                        align: 'right',
                        stackup_spacing: 30
                    });
                }
            })
        })


        var userIdStrArray = "";

        $("[name='selUser']:checkbox").on("click", function () {
            if ($(this).is(":checked")) {
                userIdStrArray += $(this).attr("data-userId") + ",";
            } else {
                userIdStrArray = userIdStrArray.replace($(this).attr("data-userId") + ",", "");
            }
        })
    });
</script>

