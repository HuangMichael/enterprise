<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="v-bind" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal myform" role="form" id="detailForm">
    <div class="form-group">
        <label class="col-md-1 control-label" for="pmCode">编号</label>
        <div class="col-md-3">
            <input class="form-control" id="pmCode" type="text" name="pmCode" v-model="preMaint.pmCode" required/>
            <input class="form-control" id="id" type="hidden" name="id" v-model="preMaint.id"/>
        </div>

        <label for="description" class="col-md-1 control-label">描述</label>
        <div class="col-md-3">
            <input class="form-control" id="description" type="text" name="description" required
                   v-model="preMaint.description"/>
        </div>
        <label for="createBy" class="col-md-1 control-label">创建人</label>
        <div class="col-md-3">
            <input class="form-control" id="createBy" type="text" name="createBy" required
                   v-model="preMaint.createBy"/>
        </div>
    </div>
    <div class="form-group">
        <label for="equipment_id" class="col-md-1 control-label">设备</label>
        <div class="col-md-3">
            <select v-model="preMaint.equipment.id" class="form-control" id="equipment_id" name="equipment.id"
                    required style="width:100%" required>
                <template v-for="option in eqs">
                    <option :value="option.id" v-if="option.id == preMaint.equipment.id" selected>
                        {{option.locName}} {{option.eqName }}
                    </option>
                    <option :value="option.id" v-else>
                        {{option.locName}}{{option.eqName }}
                    </option>
                </template>
            </select>
        </div>

        <label class="col-md-1 control-label" for="frequency">频率</label>
        <div class="col-md-3">
            <input class="form-control" id="frequency" type="text" name="frequency" v-model="preMaint.frequency"
                   required/>
        </div>
        <label for="unit_id" class="col-md-1 control-label">单位</label>
        <div class="col-md-3">
            <select class="form-control" id="unit" name="unit"
                    required v-model="preMaint.unit"
                    style="width:100%;background-color:#ffffce" required>
                <template v-for="option in f_units">
                    <option :value="option.key" v-if="option.key == preMaint.unit" selected>
                        {{ option.text }}
                    </option>
                    <option :value="option.key" v-else>
                        {{ option.text }}
                    </option>
                </template>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="unit_id" class="col-md-1 control-label">维修单位</label>
        <div class="col-md-3">
            <select class="form-control" id="unit_id" name="outUnit.id" required v-model="preMaint.outUnit.id"
                    style="width:100%;background-color:#ffffce" required>
                <template v-for="option in units">
                    <option :value="option.id" v-if="option.id == preMaint.outUnit.id" selected>
                        {{ option.description }}
                    </option>
                    <option :value="option.id" v-else>
                        {{ option.description }}
                    </option>
                </template>
            </select>
        </div>


        <label class="col-md-1 control-label" for="frequency">最近日期</label>
        <div class="col-md-3">
            <input class="Wdate form-control" id="latestTime" onLoad="WdatePicker({maxDate:'%y-%M-%d'})"
                   onClick="WdatePicker({maxDate:'%y-%M-%d'})" name="latestTime" v-model="preMaint.latestTime"
                   style="height:34px;border:1px solid #cccccc"/>
        </div>
        <label for="applyDate" class="col-md-1 control-label ">下次日期</label>
        <div class="col-md-3 ">
            <input class="Wdate form-control" id="applyDate" onLoad="WdatePicker({maxDate:'%y-%M-%d'})"
                   onClick="WdatePicker({maxDate:'%y-%M-%d'})" name="nextTime" v-model="preMaint.nextTime"
                   style="height:34px;border:1px solid #cccccc"/>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger">保存记录
        </button>
    </div>
</form>
