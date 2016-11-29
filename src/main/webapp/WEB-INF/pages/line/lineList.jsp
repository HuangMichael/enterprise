<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="lineListTable" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid"
       data-selection="true" data-multi-select="true" data-row-select="true" data-keep-selection="true" data-ajax="true"
       data-url="/line/data"
>
    <thead>
    <tr>
        <th data-column-id="id" data-width="6%">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false" data-width="6%">ID
        </th>
        <th data-column-id="lineNo" data-width="30%">线路编号</th>
        <th data-column-id="description" data-width="60%">线路名称</th>
    </tr>
    </thead>
</table>
