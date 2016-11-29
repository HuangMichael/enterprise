<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal myform" role="form" id="detailForm">

	<fieldset class="form-group" id="a">
		<legend>使用单位填写</legend>
		<div class="form-group">
			<label class="col-md-1 control-label" for="applyDate">申请日期</label>
			<div class="col-md-3">
				<input class="Wdate form-control" id="applyDate" type="text" onClick="WdatePicker({maxDate:'%y-%M-%d'})" name="applyDate" v-model="ecbudget.applyDate" style="height:34px;border:1px solid #cccccc" />
			</div>
			<label for="applicant" class="col-md-1 control-label ">填报人</label>
			<div class="col-md-3">
				<input class="form-control" id="applicant" type="text" name="applicant" v-model="ecbudget.applicant" required="required" />
				<input class="form-control" id="id" type="hidden" name="id" v-model="ecbudget.id" />
			</div>
			<label class="col-md-1 control-label" for="locations_id">使用位置</label>
			<div class="col-md-3">
				<select v-model="ecbudget.vlocations.id " class="form-control " id="locations_id" name="vlocations.id" required style="width:80% " required>
					<template v-for="option in locs ">
						<option :value="option.id " v-if="option.id==ecbudget.vlocations.id " selected>
							{{option.locName }}
						</option>
						<option :value="option.id " v-else>
							{{option.locName }}
						</option>
					</template>
				</select>
			</div>

		</div>
	</fieldset>
	<fieldset class="form-group" id="ab">
		<div class="form-group">
			<label class="col-md-1 control-label" for="ecname">易耗品名称</label>
			<div class="col-md-3">
				<input class="form-control" id="ecname" type="text" name="ecname" v-model="ecbudget.ecname" required="required" />
			</div>
			<label class="col-md-1 control-label" for="amount">申请数量</label>
			<div class="col-md-3">
				<input class="form-control" id="amount" type="number" name="amount" v-model="ecbudget.amount" value="1" />
			</div>
			<div class="form-group">
				<label for="epermited" class="col-md-1 control-label ">有无用电许可证</label>
				<div class="col-md-3">
					<select class="js-example-basic-multiple" style="width:80% " name="epermited" id="epermited" v-model="ecbudget.epermited">
						<option>有</option>
						<option>无</option>
					</select>
				</div>
			</div>
		</div>
	</fieldset>
	<fieldset class="form-group" id="ac">
		<div class="form-group">
			<label class="col-md-1 control-label" for="updateReason">选择原因</label>
			<div class="col-md-3">
				<select class="js-example-basic-multiple" multiple="multiple" style="width:90% " name="updateReason" id="updateReason" required="required" v-model="ecbudget.updateReason">
					<option>使用年限较长</option>
					<option>自然损坏</option>
					<option>人为损坏</option>
					<option>安全隐患</option>
					<option>其他</option>
				</select>
			</div>
			<label class="col-md-1 control-label" for="updateReason">申请原因</label>
			<div class="col-md-3"><input type="text" class="form-control" v-model="ecbudget.updateReason" disabled></div>
		</div>
	</fieldset>
	<fieldset class="form-group" id="ad">
		<legend>综合维修中心填写</legend>
		<div class="form-group">
			<label class="col-md-1 control-label" for="auditDate">审核日期</label>
			<div class="col-md-3">
				<input class="Wdate form-control" id="auditDate" onClick="WdatePicker({maxDate:'%y-%M-%d'})" type="text" name="auditDate" v-model="ecbudget.auditDate"  style="height:34px;border:1px solid #cccccc" />
			</div>

			<label for="auditor" class="col-md-1 control-label ">填报人</label>
			<div class="col-md-3">
				<input class="form-control" id="auditor" type="text" name="auditor" v-model="ecbudget.auditor" value="1" required="required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-1 control-label" for="confirmReason">选择原因</label>
			<div class="col-md-3">
				<select class="js-example-basic-multiple" multiple="multiple" style="width:90% " id="confirmReason" name="confirmReason" required="required" v-model="ecbudget.confirmReason" style="width:100%">
					<option>使用年限较长</option>
					<option>自然损坏</option>
					<option>人为损坏</option>
					<option>安全隐患</option>
					<option>其他</option>
				</select>
			</div>
			<label class="col-md-1 control-label" for="updateReason">确认原因</label>
			<div class="col-md-3"><input type="text" class="form-control" v-model="ecbudget.confirmReason" disabled></div>
		</div>
	</fieldset>
	<fieldset class="form-group" id="ae">
		<div class="form-group">
			<label class="col-md-1 control-label" for="fixAdvice">维修意见</label>
			<div class="col-md-11">
				<textarea class="form-control" id="fixAdvice" name="fixAdvice" v-model="ecbudget.fixAdvice" rows ="2" required="required"/>
			</div>
		</div>
	</fieldset>
	<fieldset class="form-group" id="af">
		<div class="form-group">
			<label class="col-md-1 control-label" for="leaderAdvice">领导意见</label>
			<div class="col-md-11">
				<textarea class="form-control" id="leaderAdvice" name="leaderAdvice" v-model="ecbudget.leaderAdvice"  rows ="2" required="required"/>
			</div>
		</div>
	</fieldset>
	<div class="modal-footer">
		<button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger ">保存记录
        </button>
	</div>
</form>