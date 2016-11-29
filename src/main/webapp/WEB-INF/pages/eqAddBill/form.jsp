<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="v-bind" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal myform" role="form" id="detailForm">
    <fieldset class="form-group" id="a">
        <legend>申请人信息</legend>
        <div class="form-group">

            <label for="applicant" class="col-md-1 control-label ">申请人</label>
            <div class="col-md-3 ">
                <input class="form-control " id="applicant" type="text " name="applicant" required
                       v-model="eqAddBill.applicant"/>
            </div>
            <label for="applyDep" class="col-md-1 control-label ">申请部门</label>
            <div class="col-md-3 ">
                <input class="form-control " id="applyDep" type="text " name="applyDep" required
                       v-model="eqAddBill.applyDep"/>
            </div>

            <label for="applyDate" class="col-md-1 control-label ">申请日期</label>
            <div class="col-md-3 ">
                <input class="Wdate form-control" id="applyDate" onLoad="WdatePicker({maxDate:'%y-%M-%d'})"
                       onClick="WdatePicker({maxDate:'%y-%M-%d'})" name="applyDate" v-model="eqAddBill.applyDate"
                       style="height:34px;border:1px solid #cccccc"/>
            </div>
        </div>
    </fieldset>
    <fieldset class="form-group" id="b">
        <legend>设备信息</legend>
        <div class="form-group">
            <label class="col-md-1 control-label" for="locName">设备位置</label>
            <div class="col-md-5">
                <select v-model="eqAddBill.location.id" class="form-control " id="locName"
                        name="location.id" required style="width:100% " required>
                    <template v-for="option in locs ">
                        <option :value="option.id " v-if="option.id==eqAddBill.location.id" selected>
                            {{option.locName }}
                        </option>
                        <option :value="option.id " v-else>
                            {{option.locName }}
                        </option>
                    </template>
                </select>
            </div>
            <label for="eqClass_id " class="col-md-1 control-label ">设备分类</label>
            <div class="col-md-5 ">
                <select v-model="eqAddBill.eqClass.id " class="form-control "
                        id="eqClass_id " name="eqClass.id" required style="width:100% " required>
                    <template v-for="option in eqClasses ">
                        <option :value="option.id " v-if="option.id==eqAddBill.eqClass.id"
                                selected>
                            {{option.cname }}
                        </option>
                        <option :value="option.id " v-else>
                            {{option.cname }}
                        </option>
                    </template>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-1 control-label" for="eqCode">设备编号</label>
            <div class="col-md-5">
                <input class="form-control" id="eqCode" type="text" name="eqCode" v-model="eqAddBill.eqCode"/>
            </div>
            <label class="col-md-1 control-label" for="eqName">设备名称</label>
            <div class="col-md-5">
                <input class="form-control" id="id" type="hidden" name="id" v-model="eqAddBill.id"/>
                <input class="form-control" id="eqName" type="text" name="eqName" v-model="eqAddBill.eqName"/>
            </div>
        </div>
    </fieldset>
    <fieldset class="form-group" id="d">
        <legend>用途信息</legend>
        <div class="form-group">
            <label class="col-md-1 control-label" for="purpose">申请用途</label>
            <div class="col-md-11">
                <textarea class="form-control" id="purpose" type="text" name="purpose" v-model="eqAddBill.purpose"
                          rows="6"/>
            </div>
        </div>
    </fieldset>

    <fieldset class="form-group" id="e">
        <legend>批准信息</legend>
        <div class="form-group">
            <label class="col-md-1 control-label" for="approver">批准人</label>
            <div class="col-md-3">
                <input class="form-control" id="approver" type="text" name="approver" v-model="eqAddBill.approver"/>
            </div>
            <label for="handler" class="col-md-1 control-label ">经办人</label>
            <div class="col-md-3 ">
                <input class="form-control" id="handler" type="text" name="handler" v-model="eqAddBill.handler"/>
            </div>
            <label for="receiver" class="col-md-1 control-label ">接收人</label>
            <div class="col-md-3 ">
                <input class="form-control " id="receiver" type="text" name="receiver" required
                       v-model="eqAddBill.receiver"/>
            </div>
        </div>
    </fieldset>

    <div class="modal-footer ">
        <button type="submit" id="saveBtn" name="saveBtn " class="btn btn-primary btn-danger ">保存记录
        </button>
    </div>
</form>