<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="personListTable" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid"
       data-ajax="true" data-url="/person/data"
       data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true">
    <thead>
    <tr>
        <th data-column-id="id" data-width="5%">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false" data-width="5%">ID</th>
        <th data-column-id="personNo" data-width="15%">人员工号</th>
        <th data-column-id="personName" data-width="15%">姓名</th>
        <th data-column-id="telephone" data-width="15%">电话</th>
    </tr>
    </thead>
</table>

