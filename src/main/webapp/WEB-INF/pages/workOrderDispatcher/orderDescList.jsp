<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<table id="fillTable"
       class="table table-responsive table-condensed table-striped  table-hover">
    <thead>
    <tr id="trr2">
        <th width="5%">序号</th>
        <th width="10%">跟踪号</th>
        <th width="10%">设备名称</th>
        <th width="15%">设备位置</th>
        <th width="10%">设备分类</th>
        <th width="20%">故障描述</th>
        <th width="20%">维修单位</th>
        <th width="20%">维修期限</th>
        <th width="5%">操作菜单</th>
    </tr>
    </thead>
    <tbody id="tbody2">
    <c:forEach items="${WorkOrderReportDetailList}" var="workOrder"
               varStatus="w">
        <tr id="tr-${w.index+1}">
            <td>${w.index+1}</td>
            <td>${workOrder.orderLineNo}</td>
            <td>${workOrder.equipments.description}</td>
            <td>${workOrder.vlocations.locName}</td>
            <td>${workOrder.equipmentsClassification.description}</td>
            <td>${workOrder.orderDesc}</td>
            <td>
                <select class="form-control" id="selUnit${workOrder.id}" onchange="selectUnit('selUnit${workOrder.id}')"
                        style="height:24px;padding: 2px 2px;font-size: 12px;line-height: 1"
                <option value="">请选择外委单位</option>
                <c:forEach var="u" items="${workOrder.equipmentsClassification.unitSet}">
                    <option value="${u.id}"
                            <c:if test="${workOrder.unit.id==u.id}">selected</c:if>>${u.description}</option>
                </c:forEach>
                </select>
            </td>
            <td>
                <input class="Wdate form-control" type="text"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"
                       id="deadLine${workOrder.id}"
                       name="deadLine"
                       style="height:24px;padding: 2px 2px ;border:1px solid #cccccc" value="${workOrder.deadLine}"
                       onchange="changeDeadLine(${workOrder.id})"/>

            </td>
            <td>

                <div class="dropdown">
                    <button type="button" class="btn btn-xs dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
                        操作菜单
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1"
                               onclick="linkUnit(${workOrder.id},${workOrder.equipmentsClassification.id})"
                               style="font-size: 12px"><i class="glyphicon glyphicon-link"></i>关联单位</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1"
                               onclick="addLinkUnit(${workOrder.id},${workOrder.equipmentsClassification.id})"
                               style="font-size: 12px"><i class="glyphicon glyphicon-plus"></i>新增单位</a>
                        </li>
                    </ul>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="modal fade " id="link_unit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="link_unit_modal_label">请选择要关联的外委单位信息</h4>
            </div>
            <div class="modal-body" id="unitBody">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" id="confirmBtn" name="confirmBtn" class="btn btn-danger"
                        onclick="confirmLinkUnit()">确定
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade " id="add_link_unit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="add_link_unit_modal_label">新增外委单位信息</h4>
            </div>
            <div class="modal-body" id="addUnitBody">
                <form class="form-horizontal" role="form" id="createForm">
                    <div class="form-group">
                        <label for="unitNo" class="col-md-2 control-label">单位编号</label>

                        <div class="col-md-10">
                            <input class="form-control" name="unit.unitNo" id="unitNo" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-2 control-label">单位名称</label>

                        <div class="col-md-10">
                            <input class="form-control" id="description" name="unit.description" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="linkman" class="col-md-2 control-label">联系人</label>

                        <div class="col-md-10">
                            <input class="form-control" name="unit.linkman" id="linkman"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="telephone" class="col-md-2 control-label">联系电话</label>

                        <div class="col-md-10">
                            <input class="form-control" id="telephone" name="unit.telephone"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger"
                                onclick="createUnit()">保存记录
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function changeDeadLine(id) {
        var url = "workOrderFix/updateDeadLine";
        var data = {
            orderId: id,
            deadLine: $("#deadLine" + id).val()
        }
        $.post(url, data, function (data) {
            if (data.result) {
                showMessageBox("info", data.resultDesc);
            } else {
                showMessageBox("danger", data.resultDesc);
            }
        });
    }</script>