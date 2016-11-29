<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="unitsDataTable" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid"  data-ajax="true" data-url="/units/data"
       data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true">
    <thead>
    <tr>
        <th data-column-id="id" data-width="5%">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID</th>
        <th data-column-id="unitNo" width="10%">单位编号</th>
        <th data-column-id="description" width="20%">单位名称</th>
        <th data-column-id="linkman" width="10%">联系人</th>
        <th data-column-id="telephone" width="10%">电话</th>
        <th data-column-id="workDays" width="10%">工作制</th>
    </tr>
    </thead>
</table>