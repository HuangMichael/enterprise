<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="detailForm">
    <div class="form-group">
        <div class="col-md-12">
            <div class="form-group">
                <label class="col-md-1 control-label"
                       for="location">角色名称</label>
                <div class="col-md-5">
                    <input class="form-control" id="location" type="text"  name="location"   value="${role.roleName}"/>
                </div>
                <label for="roleDesc" class="col-md-1 control-label">角色描述</label>
                <div class="col-md-5">
                    <input class="form-control" id="roleDesc" type="text" name="roleDesc" value="${role.roleDesc}"/>

                    <input type="hidden" name="locationsId" value="20">
                </div>
            </div>
            <div class="form-group">

                <label for="status"
                       class="col-md-1 control-label">是否启用</label>
                <div class="col-md-5">
                    <form:select path="role.status" itemValue="${role.status}" cssClass="form-control" id="status">
                        <form:option value="1">是</form:option>
                        <form:option value="0">否</form:option>
                    </form:select>
                </div>
                <label for="sortNo" class="col-md-1 control-label">排序</label>
                <div class="col-md-5">
                    <input class="form-control" id="sortNo" type="text"
                           name="sortNo" value="${role.sortNo}"/>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger">保存记录
            </button>
        </div>
    </div>
</form>


