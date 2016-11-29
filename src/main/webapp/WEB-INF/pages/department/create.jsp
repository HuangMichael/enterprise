<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
<form class="form-horizontal" role="form" method="post" id="form" action="/department/save">
    <div class="form-group">
        <div class="col-md-12">
            <div class="form-group">
                <label class="col-md-1 control-label"
                       for="deptNum">部门编号</label>

                <div class="col-md-5">
                    <input class="form-control" id="deptNum" type="text"
                           name="deptNum" value="${department.deptNum}"/>
                </div>

                <label for="description" class="col-md-1 control-label">部门名称</label>

                <div class="col-md-5">
                    <input class="form-control" id="description" type="text"
                           name="description" value="${department.description}"/>
                </div>
            </div>
            <div class="form-group">

                <label class="col-md-1 control-label"
                       for="supervisor">部门主管</label>

                <div class="col-md-5">
                    <form:select path="personList" id="supervisor" cssClass="form-control" name="supervisor">
                        <form:option value="" label="请选择部门主管"/>
                        <form:options items="${personList}" itemValue="personName" itemLabel="personName"/>
                    </form:select>
                </div>
                <label for="parent" class="col-md-1 control-label">上级部门</label>

                <div class="col-md-5">
                    <form:select path="departmentList" id="parent" cssClass="form-control" name="parent.id">
                        <form:option value="" label="请选择上级部门"/>
                        <form:options items="${departmentList}" itemValue="id" itemLabel="description"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label for="sortNo" class="col-md-1 control-label">排序</label>

                <div class="col-md-5">
                    <input class="form-control" id="sortNo" type="text"
                           name="sortNo" value="${department.sortNo}"/>
                </div>
                <label for="status"
                       class="col-md-1 control-label">是否启用</label>

                <div class="col-md-5">
                    <select class="form-control" id="status"
                            name="status">
                        <option>是</option>
                        <option>否</option>
                    </select>
                </div>

                &lt;%&ndash;<div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>&ndash;%&gt;
            </div>
        </div>
    </div>
</form>
--%>


<%@include file="form.jsp"%>
