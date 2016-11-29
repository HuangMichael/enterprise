<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="box border blue">
    <div class="box-body">
        <table id="subListTable" class=" table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th data-column-id="index">序号</th>
                <th data-column-id="id" data-visible="false" data-identifier="true">序号</th>
                <th data-column-id="resouceCode">资源编号</th>
                <th data-column-id="resouceName">资源名称</th>
                <th data-column-id="description">资源描述</th>
                <th data-column-id="resourceUrl">资源路径</th>
                <th data-column-id="appName">应用名称</th>
                <th data-column-id="iconClass">资源样式</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resourceList}" var="r" varStatus="s">
                <tr class="gradeX">
                    <td>${s.index+1}</td>
                    <td>
                            ${r.id}
                    </td>
                    <td>
                            ${r.resourceCode}
                    </td>
                    <td>
                            ${r.resourceName}
                    </td>
                    <td>
                            ${r.description}
                    </td>
                    <td>
                            ${r.resourceUrl}
                    </td>
                    <td>
                            ${r.appName}
                    </td>
                    <td>
                            ${r.iconClass}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    $("#subListTable").bootgrid({
        selection: true,
        multiSelect: true,
        rowSelect: true,
        keepSelection: true
    });
</script>