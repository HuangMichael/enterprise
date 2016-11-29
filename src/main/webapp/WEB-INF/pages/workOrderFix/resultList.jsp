<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<table id="datatable1" cellpadding="0" cellspacing="0" border="0"
       class=" table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th data-column-id="select" data-formatter="select">选择</th>
        <th data-column-id="index">序号</th>
        <th data-column-id="id" data-visible="false">序号</th>
        <th data-column-id="orderNo">维修单号</th>
        <th class="hidden-xs hidden-sm" data-column-id="orderLineNo">维修单行号</th>
        <th data-column-id="eqName">设备名称</th>
        <th data-column-id="location">设备位置</th>
        <th data-column-id="eqClass">设备种类</th>
        <th class="hidden-xs hidden-sm" data-column-id="reporter">报告人</th>
        <th class="hidden-xs hidden-sm" data-column-id="reportTime">报告时间</th>
        <th data-column-id="orderDesc">故障描述</th>

    </tr>
    </thead>
    <tbody id="tbody">


    <c:forEach items="${workOrderReportList}" var="workOrder" varStatus="w">
     <c:if test ="${workOrder.deadLine < new Date()}">
        <tr data-style="color:red">
            <td data-column-id="select" data-formatter="select">选择</td>
            <td>${w.index+1}</td>
            <td>${workOrder.id}</td>
            <td>${workOrder.workOrderReport.orderNo}</td>
            <td class="hidden-xs hidden-sm">${workOrder.orderLineNo}</td>
            <td>${workOrder.equipments.description}</td>
            <td>${workOrder.locations.description}</td>
            <td>${workOrder.equipmentsClassification.description}</td>
            <td class="hidden-xs hidden-sm">${workOrder.workOrderReport.reporter}</td>
            <td class="hidden-xs hidden-sm">${workOrder.workOrderReport.reportTime}</td>
            <td class="hidden-xs hidden-sm">${workOrder.workOrderReport.orderDesc}</td>
            <td></td>

        </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>


