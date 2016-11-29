<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="myUsers" class="table table-striped table-bordered table-condensed table-hover">
    <thead>
    <tr>
        <th data-column-id="id">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID</th>
        <th data-column-id="userName">用户名</th>
        <th data-column-id="remove">移除</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${role.usersList}" var="u" varStatus="s">
        <tr>
            <td>${s.index+1}</td>
            <td>
                    ${u.id}
            </td>
            <td>
                    ${u.userName}
            </td>
            <td>
                    ${u.userName}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>