<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="stationListTable" class=" table table-striped table-bordered table-hover" data-ajax="true" data-url="/station/data">
    <thead>
    <tr>
        <th data-column-id="id" data-width="5%">序号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID
        <th data-column-id="stationNo" data-width="15%">编号</th>
        <th data-column-id="description" data-width="25%">车站</th>
        <th data-column-id="sortNo" data-width="5%">排序</th>
    </tr>
    </thead>
</table>