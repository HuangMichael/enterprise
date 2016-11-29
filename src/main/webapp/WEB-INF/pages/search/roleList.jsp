<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="roleListTable" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid" data-ajax="true" data-url="/search/data">
    <thead>
    <tr>
        <th data-column-id="id" data-width="10%">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false" data-width="10%">ID</th>
        <th data-column-id="roleName">角色名</th>
        <th data-column-id="roleDesc">角色描述</th>
    </tr>
    </thead>
</table>