<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="eqDataTable" class=" table table-striped table-bordered table-hover table-condensed">
    <thead>
    <tr>
        <th data-column-id="index" data-width="3%">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false"
            data-width="10%">ID
        </th>
        <th data-column-id="eqCode" data-width="5%">设备编号</th>
        <th data-column-id="eqName" data-width="25%">设备名称</th>
        <th data-column-id="eqClass" data-width="10%">设备分类</th>
        <th data-column-id="locName" data-width="10%">设备位置</th>
        <th data-column-id="report" data-formatter="report" data-sortable="false" data-width="3%">
            报修
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${equipmentsList}" var="equipment" varStatus="s">
        <tr class="gradeX" data-rowId="${equipment.id}" id="tr${equipment.id}">
            <td>${s.index+1}</td>
            <td>${equipment.id}</td>
            <td>
                    ${equipment.eqCode}
            </td>
            <td>
                    ${equipment.eqName}
            </td>
            <td>
                    ${equipment.eqClass}
            </td>
            <td>
                    ${equipment.locName}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>