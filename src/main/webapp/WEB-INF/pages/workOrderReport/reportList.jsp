<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="fixListTable" class="table table-bordered table-hover table-striped">
	<thead>
		<tr>

			<th data-column-id="orderLineNo" data-width="10%">跟踪号</th>
			<th data-column-id="eqName" data-width="10%">设备名称</th>
			<th data-column-id="orderDesc" data-width="20%">故障描述</th>
			<th data-column-id="locName" data-width="10%">设备位置</th>
			<th data-column-id="eqClass" data-width="8%">设备分类</th>
			<th data-column-id="nodeTime" data-width="10%">生成时间</th>
			<th data-column-id="nodeState" data-width="5%">报修状态</th>
		</tr>

	</thead>

	<tbody>
		<c:forEach items="${searchResult}" var='r'>
			<tr>
				<td>${r.orderLineNo}</td>
				<td>${r.eqName}</td>
				<td>${r.orderDesc}</td>
				<td>${r.locName}</td>
				<td>${r.eqClass}</td>
				<td>${r.nodeTime}</td>
				<td>${r.nodeState}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>