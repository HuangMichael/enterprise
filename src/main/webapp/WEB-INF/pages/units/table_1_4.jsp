<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="reportHistory" class=" table table-striped table-bordered table-hover">
    <thead>
    <tr>

        <th>报修行号</th>
        <th>报修描述</th>
        <th>报修时间</th>
        <th>状态</th>
        <th>报修时间</th>
    </tr>
    </thead>
    <tbody id="reportHistory_list" style="height: 100px;overflow: scroll">
    <c:forEach var="h" items="${historyList}">
        <tr>
            <td>${h[0]}</td>
            <td>${h[2]}</td>
            <td>${h[3]}</td>
            <td>
                <c:if test="${h[4]==0}">
                    未完成
                </c:if>
                <c:if test="${h[4]==1}">
                    已完成
                </c:if>
            </td>
            <td>${h[5]}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>