<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form class="form-horizontal" role="form" id="locReportForm">
    <div class="row">
        <div class="col-md-12">
            <div class="form-group">
                <label class="col-md-2 control-label" for="rptLoc">故障位置</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="rptLoc" name="rptLoc" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label" for="equipmentsClassification_id">设备分类</label>
                <div class="col-md-10">
                    <select class="form-control" id="equipmentsClassification_id" name="equipmentsClassification.id"
                            v-model="equipments.equipmentsClassification.id"
                            style="width:100%;background-color:#ffffce" required>
                        <template v-for="option in eqClasses">
                            <option :value="option.id">
                                {{ option.cpName}}{{ option.cname }}
                            </option>
                        </template>
                    </select>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-2 control-label" for="rptLoc">故障描述</label>

                <div class="col-md-10">
                    <textarea class="form-control" id="orderDesc" name="orderDesc" rows="5"
                              placeholder="请输入报修故障描述" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label" for="reporter">报修人</label>

                <div class="col-md-10">
                    <input type="text" class="form-control" id="reporter" name="reporter" value="${personName}" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label" for="creator">录入人</label>

                <div class="col-md-10">
                    <input type="text" class="form-control" id="creator" name="creator" value="${personName}" readonly/>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消
        </button>
        <button type="button" id="reportBtn" name="reportBtn" class="btn btn-primary btn-danger" onclick="add2LocCart()">报修
        </button>
    </div>
</form>

