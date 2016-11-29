<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<table id="datatable1" cellpadding="0" cellspacing="0" border="0"
       class=" table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th data-column-id="index">序号</th>
        <th data-column-id="id" data-visible="false">序号</th>
        <th data-column-id="eqCode">设备编号</th>
        <th data-column-id="eqName">设备名称</th>
        <th data-column-id="location">设备位置</th>
        <th data-column-id="eqClass">设备种类</th>
    </tr>
    </thead>
    <tbody id="tbody">
    <c:forEach items="${workOrderReportCartList}" var="workOrder" varStatus="w">
        <tr>
            <td>${w.index+1}</td>
            <td class="hidden-xs hidden-sm">${workOrder.orderLineNo}</td>
            <td>${workOrder.equipments.eqCode}</td>
            <td>${workOrder.equipments.description}</td>
            <td>${workOrder.locations.description}</td>
            <td>${workOrder.equipmentsClassification.description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="modal-footer">
    <button type="button" class="btn btn-default"
            data-dismiss="modal">关闭
    </button>
    <button type="button" id="confirmBtn" name="confirmBtn" class="btn btn-primary" onclick="confirmGenerate()">确定
    </button>
</div>


