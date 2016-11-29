<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="equipmentsDataTable" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid" data-ajax="true" data-url="/equipment/data">
    <thead>
    <tr>
        <th data-column-id="id" data-width="3%">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false" data-width="10%">ID</th>
        <th data-column-id="eqCode" data-width="5%" >设备编号</th>
        <th data-column-id="eqName"  data-width="15%">设备名称</th>
        <th data-column-id="locName" data-width="10%">设备位置</th>
        <th data-column-id="eqClass" data-width="10%">设备分类</th>
        <th data-column-id="report" data-formatter="report" data-sortable="false" data-width="5%" >报修|更新</th>
    </tr>
    </thead>
</table>