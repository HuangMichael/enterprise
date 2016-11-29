<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal myform" role="form" id="createForm">
    <div class="form-group">
        <label class="col-md-2 col-sm-2 col-lg-2" for="roleName">角色名称</label>

        <div class="col-md-4 col-sm-4 col-lg-4">
            <input type="text" class="form-control" id="roleName" name="roleName" v-model="role.roleName">
            <input type="hidden" class="form-control" id="roleId" name="roleId" v-model="role.id">
        </div>
        <div class="col-md-2 col-sm-2 col-lg-2">
            <label for="roleDesc">角色描述</label>
        </div>
        <div class="col-md-4 col-sm-4 col-lg-4">
            <input type="text" class="form-control" id="roleDesc" name="roleDesc" v-model="role.roleDesc">
        </div>
    </div>
</form>

