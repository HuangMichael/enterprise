<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="v-bind" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal myform" role="form" id="detailForm">

    <fieldset class="form-group" id="a">
        <legend>申请人信息</legend>
        <div class="form-group">

            <label for="applicant" class="col-md-1 control-label">申请人</label>
            <div class="col-md-3">
                <input class="form-control" id="applicant" type="text" name="applicant" required
                       v-model="budget.applicant"/>
                <input class="form-control" id="id" type="hidden" name="id" required v-model="budget.id"/>
            </div>
            <label for="applyDep" class="col-md-1 control-label">申请部门</label>
            <div class="col-md-3">
                <input class="form-control" id="applyDep" type="text" name="applyDep" required
                       v-model="budget.applyDep"/>
            </div>

            <label for="applicant" class="col-md-1 control-label">申请日期</label>
            <div class="col-md-3">
                <input class="Wdate form-control myDateBox" id="applyDate" onLoad="WdatePicker({maxDate:'%y-%M-%d'})"
                       onClick="WdatePicker({maxDate:'%y-%M-%d'})" name="applyDate" v-model="budget.applyDate"/>
            </div>
        </div>
    </fieldset>
    <fieldset class="form-group" id="ab">
        <legend>配件信息</legend>
        <div class="form-group">
            <label class="col-md-1 control-label" for="accessoryName">配件名称</label>
            <div class="col-md-3">
                <input class="form-control" id="accessoryName" type="text" name="accessoryName"
                       v-model="budget.accessoryName"/>
            </div>
            <label class="col-md-1 control-label" for="accessoryName">规格型号</label>
            <div class="col-md-3">
                <input class="form-control" id="specifications" type="text" name="specifications"
                       v-model="budget.specifications"/>

            </div>
            <label class="col-md-1 control-label" for="amount">申请数量</label>
            <div class="col-md-3">
                <input class="form-control" id="amount" type="number" name="amount" v-model="budget.amount" value="1"/>
            </div>
        </div>
    </fieldset>
    <fieldset class="form-group" id="ac">
        <legend>用途信息</legend>
        <div class="form-group">
            <label class="col-md-1 control-label" for="purpose">申请用途</label>
            <div class="col-md-11">
                <textarea class="form-control" id="purpose" type="text" name="purpose" v-model="budget.purpose"
                          rows="6"/>
            </div>
        </div>
        <div class="form-group">
            <label for="vlocations_id" class="col-md-1 control-label">使用位置</label>
            <div class="col-md-3">
                <select v-model="budget.vlocations.id" class="form-control" id="vlocations_id" name="vlocations.id"
                        required style="width:100%" required>
                    <template v-for="option in locs">
                        <option :value="option.id" v-if="option.id==budget.vlocations.id" selected>
                            {{option.locName }}
                        </option>
                        <option :value="option.id" v-else>
                            {{option.locName }}
                        </option>
                    </template>
                </select>
            </div>

            <label for="eq_class_id" class="col-md-1 control-label">设备分类</label>
            <div class="col-md-3">
                <select v-model="budget.eqClass.id" class="form-control" id="eq_class_id" name="eqClass.id" required
                        style="width:100%" required>
                    <template v-for="option in eqClasses">
                        <option :value="option.id" v-if="option.id==budget.eqClass.id" selected>
                            {{option.cpName+option.cname }}
                        </option>
                        <option :value="option.id" v-else>
                            {{option.cpName+option.cname }}
                        </option>
                    </template>
                </select>
            </div>
        </div>
    </fieldset>

    <fieldset class="form-group" id="af">
        <legend>批准信息</legend>
        <div class="form-group">
            <label class="col-md-1 control-label" for="approver">批准人</label>
            <div class="col-md-3">
                <input class="form-control" id="approver" type="text" name="approver" v-model="budget.approver"/>
            </div>
            <label for="handler" class="col-md-1 control-label">经办人</label>
            <div class="col-md-3">
                <input class="form-control" id="handler" type="text" name="handler" v-model="budget.handler"/>
            </div>
            <label for="receiver" class="col-md-1 control-label">接收人</label>
            <div class="col-md-3">
                <input class="form-control" id="receiver" type="text" name="receiver" required
                       v-model="budget.receiver"/>
            </div>
        </div>
    </fieldset>

    <div class="modal-footer">
        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger">保存记录
        </button>
    </div>
</form>