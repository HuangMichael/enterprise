<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped table-bordered table-hover" id="unitsTable">
    <thead>
    <tr>
        <th><input type="checkbox" value="" name="unit"></th>
        <th width="10%" data-column-id="index">序号</th>
        <th width="10%">单位编号</th>
        <th width="20%">单位名称</th>
        <th width="20%">联系人</th>
        <th width="20%">电话</th>
        <th width="10%">状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${unitList}" var="unit" varStatus="s">
        <tr class="gradeX">
            <td><input type="checkbox" name="unit" value="${unit.id}"></td>
            <td>${s.index+1}</td>
            <td>
                    ${unit.unitNo}
            </td>
            <td>
                    ${unit.description}
            </td>
            <td>
                    ${unit.linkman}
            </td>
            <td>
                    ${unit.telephone}
            </td>
            <td>
                <c:if test="${unit.status=='1'}"> <span
                    class="badge badge-green">启用</span></td>
            </c:if>
            <c:if test="${unit.status!='1'}"> <span
                    class="badge badge-red">禁用</span></td></c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    </tfoot>
</table>