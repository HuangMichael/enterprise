<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="userDataTable" class="table table-striped table-bordered table-hover" data-toggle="bootgrid" data-ajax="true"
       data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true"
       data-url="/user/data">
    <thead>
    <tr>
        <th data-column-id="id" data-identifier="true" data-type="numeric" data-align="center" data-width="5%">序号</th>
        <%--<th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID</th>--%>
        <th data-column-id="userName" data-sortable="true" data-width="10%">用户名</th>
        <th data-column-id="location" data-sortable="true" data-width="10%">位置</th>
        <th data-column-id="sortNo" data-sortable="true" data-width="75%">排序</th>
    </tr>
    </thead>
</table>