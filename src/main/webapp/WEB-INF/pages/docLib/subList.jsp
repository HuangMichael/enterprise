<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="box border blue">
    <div class="box-body">
        <table id="subListTable" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid"
               data-ajax="true" data-url="/docLib/findDocs/1">
            <thead>
            <tr>
                <th data-column-id="index">序号</th>
                <th data-column-id="id" data-visible="false" data-identifier="true">序号</th>
                <th data-column-id="docNo">文档编号</th>
                <th data-column-id="docName">文档名称</th>
                <th data-column-id="docPath">文档路径</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
