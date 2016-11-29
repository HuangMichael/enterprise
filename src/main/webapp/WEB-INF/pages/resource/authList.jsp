<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="authViewDiv">
    <table id="authListTable" class="table table-striped table-bordered table-hover" data-toggle="bootgrid"
           data-ajax="true" data-url="/authority/loadByRole/1">
        <thead>
        <tr>
            <th data-width="5%" data-column-id="id">序号</th>
            <th data-width="15%" data-column-id="resourceCode">资源编号</th>
            <th data-width="15%" data-column-id="appName">应用名称</th>
            <th data-width="15%" data-column-id="resourceName">资源名称</th>
            <th data-width="15%" data-column-id="resourceDesc">资源描述</th>
            <th data-width="10%" data-column-id="remove" data-formatter="remove" data-sortable="false">取消授权</th>
        </tr>
        </thead>
    </table>
</div>
