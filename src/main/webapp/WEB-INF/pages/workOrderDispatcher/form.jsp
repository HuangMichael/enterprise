<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="woForm" method="post" action="/workOrder/save">
    <div class="form-group">
        <div class="col-md-12">
           <%-- <div class="form-group">
                <label for="orderDesc" class="col-md-2 control-label">设备位置</label>
                <div class="col-md-10">
                    <input class="form-control" value="${locations.description}"/>
                </div>
            </div>--%>

           <%-- <div class="form-group">
                <label for="orderDesc" class="col-md-2 control-label">设备分类</label>

                <div class="col-md-10">
                    <form:select path="equipmentsClassificationList"  class="form-control"  id="equip_class_id">
                        <form:options itemLabel="description" items="${equipmentsClassificationList}" itemValue="id"></form:options>
                    </form:select>
                </div>
            </div>--%>
            <div class="form-group">
                <label for="orderDesc" class="col-md-2 control-label">故障描述</label>

                <div class="col-md-10">
                    <input class="form-control" id="orderDesc" type="text" name="orderDesc" required="required"/>
                </div>
            </div>

            <div class="form-group">
                <label for="reporter" class="col-md-2 control-label">报告人员</label>

                <div class="col-md-10">
                    <input class="form-control" id="reporter" type="text"
                           name="reporter"/>
                </div>
            </div>
            <div class="form-group">
                <label for="reportTelephone" class="col-md-2 control-label">报告电话</label>

                <div class="col-md-10">
                    <input class="form-control" id="reportTelephone" type="text"
                           name="reporter"/>
                </div>
            </div>
            <div class="form-group">
                <label for="reportTime" class="col-md-2 control-label">报告时间</label>

                <div class="col-md-10">
                    <input class="form-control" id="reportTime" type="date"
                           name="reportTime"/>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default"
                data-dismiss="modal">关闭
        </button>
        <button type="button" id="saveWorkOrder" name="saveWorkOrder"
                class="btn btn-primary">保存
        </button>
    </div>
    </div>
</form>



