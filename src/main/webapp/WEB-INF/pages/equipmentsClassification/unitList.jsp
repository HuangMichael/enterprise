<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<table id="unitTable" class=" table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th data-column-id="index">序号</th>
        <th data-column-id="id" data-visible="false" data-identifier="true">序号</th>
        <th data-column-id="description">单位名称</th>
        <th data-column-id="linkman">联系人</th>
        <th data-column-id="telephone">联系电话</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${unitList}" var="unit" varStatus="s">
        <tr class="gradeX">
            <td>${s.index+1}</td>
            <td>
                    ${unit[0]}
            </td>
            <td>
                    ${unit[1]}
            </td>
            <td>
                    ${unit[2]}
            </td>
            <td>
                    ${unit[3]}
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    </tfoot>
</table>
<script type="text/javascript">

    var selectedUnitIds = [];
    $("#unitTable").bootgrid(
            {
                selection: true,
                multiSelect: true,
                rowSelect: true,
                keepSelection: true
            }
    );
</script>


