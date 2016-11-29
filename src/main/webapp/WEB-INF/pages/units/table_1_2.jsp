<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="contract" class="table table-striped table-bordered table-responsive table-condensed">
    <thead>
    <tr>
        <th width="5%" data-column-id="id">序号</th>
        <th width="5%" data-column-id="contractNo">合同编号</th>
        <th width="5%" data-column-id="beginDate">开始日期</th>
        <th width="5%" data-column-id="endDate">结束日期</th>
        <th width="20%" data-column-id="contractUrl">合同内容</th>
        <th width="5%">上传</th>
        <th width="5%">下载</th>
        <th width="5%">编辑</th>
        <th width="5%">删除</th>
        <th width="5%">保存</th>
    </tr>
    </thead>
    <tbody id="fix_list" style="height: 100px;overflow: scroll">

    <c:forEach items="${unit.contractList}" var="c" varStatus="s">
        <tr>
            <td width="5%">${s.index+1}</td>
            <td width="5%">${c.contractNo}</td>
            <td width="5%">${c.beginDate}</td>
            <td width="5%">${c.endDate}</td>
            <td width="20%">${c.contractUrl}</td>
            <td width="5%"><a class="btn btn-default btn-xs" onclick="edit(${c.id})"><i
                    class="glyphicon glyphicon-upload"></i>上传</a></td>
            <td width="5%"><a class="btn btn-default btn-xs" onclick="edit(${c.id})"><i
                    class="glyphicon glyphicon-download"></i>下载</a></td>
            <td width="5%"><a class="btn btn-default btn-xs" onclick="edit(${c.id})"><i
                    class="glyphicon glyphicon-edit"></i>编辑</a></td>
            <td width="5%"><a class="btn btn-default btn-xs" onclick="del(${c.id})"><i
                    class="glyphicon glyphicon-remove"></i>删除</a></td>
            <td width="5%"><a class="btn btn-default btn-xs" onclick="save(${s.index+1})"><i
                    class="glyphicon glyphicon-save"></i>保存</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>