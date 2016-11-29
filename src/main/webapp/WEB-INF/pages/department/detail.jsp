<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="form.jsp" %>
<div class="divide-10"></div>
<div class="box border blue">
    <div class="box-title">
        <h4><i class="fa fa-table"></i>部门人员信息</h4>
    </div>
</div>
<div class="box-body">
    <table id="datatable1" cellpadding="0"
           cellspacing="0" border="0"
           class="datatable table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th class="center" width="10%"> 序号</th>
            <th class="center" width="10%"> 人员工号</th>
            <th class="center" width="10%"> 姓名</th>
            <th class="center" width="10%"> 部门</th>
            <th class="center" width="10%"> 电话</th>
            <th class="center" width="10%"> 编辑</th>
            <th class="center" width="10%"> 删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${department.personList}" var="person" varStatus="s">
            <tr class="gradeX">
                <td>${s.index+1}</td>
                <td class="center">
                        ${person.personNo}
                </td>
                <td class="center">
                        ${person.personName}
                </td>
                <td class="center">${person.department.description}</td>
                <td class="center">${person.telephone}</td>
                <td class="center"><a href="#">编辑</a></td>
                <td class="center"><a href="#">删除</a></td>
            </tr>
        </c:forEach>

        <c:if test="${department.personList.size() ==0}">
            <tr class="gradeX">
                <td colspan="7" class="center">当前没有数据</td>
            </tr>
        </c:if>
        </tbody>
        <tfoot>
        </tfoot>
    </table>
</div>