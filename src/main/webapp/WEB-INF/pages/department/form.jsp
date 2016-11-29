<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" method="post" id="form">
    <div class="form-group">
        <div class="col-md-12">
            <div class="form-group">
                <label class="col-md-1 control-label"
                       for="deptNum">部门编号</label>

                <div class="col-md-5">
                    <input class="form-control" id="deptNum" type="text"
                           name="deptNum" value="${department.deptNum}"/>

                    <%-- <input class="form-control" id="parent_id" type="hidden"
                            name="parent.id" value="${department.parent.id}"/>--%>

                    <input class="form-control" id="id" type="hidden"
                           name="id" value="${department.id}"/>
                </div>
                <label for="description" class="col-md-1 control-label">部门名称</label>

                <div class="col-md-5">
                    <input class="form-control" id="description" type="text"
                           name="description" value="${department.description}"/>
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
                    <form:select id="status" path="department.status" cssClass="form-control" name="status"
                                 itemValue="${department.status}">
                        <form:option value="1">是</form:option>
                        <form:option value="0">否</form:option>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <label for="status" class="col-md-1 control-label">上级部门</label>
                <div class="col-md-5">
                    <select id="parent_id" name="parent.id" class="form-control">
                        <c:forEach var="d" items="${departmentList}">
                            <option value="${d.id}">${d.description}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
</form>



