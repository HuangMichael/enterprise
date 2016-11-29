<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<table id="detailList" class=" table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th data-column-id="id">序号</th>
        <th data-column-id="id" data-visible="false">序号</th>
        <th data-column-id="eqName">设备名称</th>
        <th data-column-id="location">设备位置</th>
        <th data-column-id="eqClass">设备种类</th>
        <th data-column-id="orderDesc">故障描述</th>
        <th class="hidden-xs hidden-sm" data-column-id="pause" data-formatter="transform"
            width="10%">
            转单
        </th>
    </tr>
    </thead>
    <tbody id="tbody">
    <c:forEach items="${workOrderFixDetailList}" var="workOrder" varStatus="w">
        <tr>
            <td>${w.index+1}</td>
            <td>${workOrder.id}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>


