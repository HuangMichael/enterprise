<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="pmOrderList0" class=" table table-striped table-bordered table-hover" data-toggle="bootgrid"
       data-ajax="true" data-url="/preMaintDispatcher/data/0">
    <thead>
    <tr>
        <th data-column-id="orderLineNo" data-width="10%">跟踪号</th>
        <th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID</th>
        <th data-column-id="eqName" data-width="20%">设备名称</th>
        <th data-column-id="locName" data-width="15%">设备位置</th>
        <th data-column-id="eqClass" data-width="10%">设备分类</th>
        <th data-column-id="orderDesc" data-width="15%">故障描述</th>
        <th data-column-id="reportTime" data-width="10%">生成时间</th>
        <th data-column-id="reporter" data-width="5%">操作人员</th>
        <th data-column-id="nodeState" data-width="5%">工单状态</th>
        <th data-column-id="opMenus" data-formatter="opMenus" data-sortable="false" data-width="8%">暂停&nbsp;取消&nbsp;完工
        </th>
    </tr>
    </thead>
</table>

<div class="modal fade " id="fix_desc_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="fix_desc_modal_label">请输入维修描述</h4>
            </div>
            <div class="modal-body" id="fix_desc_modal_div">
                <%@include file="fixDescForm.jsp" %>
            </div>
        </div>
    </div>
</div>