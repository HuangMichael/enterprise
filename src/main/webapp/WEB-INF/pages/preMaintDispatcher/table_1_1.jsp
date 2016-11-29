<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="pmOrderList1" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid"
       data-ajax="true" data-url="/preMaintDispatcher/data/1">
    <thead>
    <tr>
        <th data-column-id="orderLineNo" data-width="8%">跟踪号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID</th>
        <th data-column-id="eqName" data-width="10%">设备名称</th>
        <th data-column-id="locName" data-width="15%">设备位置</th>
        <th data-column-id="eqClass" data-width="10%">设备分类</th>
        <th data-column-id="orderDesc" data-width="15%">故障描述</th>
        <th data-column-id="reportTime" data-width="10%">生成时间</th>
        <th data-column-id="reporter" data-width="5%">操作人员</th>
        <th data-column-id="nodeState" data-width="5%">工单状态</th>
    </tr>
    </thead>
</table>