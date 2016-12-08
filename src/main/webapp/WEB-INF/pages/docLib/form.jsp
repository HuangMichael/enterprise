<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal myform" role="form" id="detailForm">
    <div class="form-group">
        <label class="col-md-1 control-label" for="description">文档库名称</label>
        <div class="col-md-5">
            <input type="text" class="form-control" id="description" name="description" v-model="docLib.description"/>
        </div>
        <label class="col-md-1 control-label" for="docLevel">文档库级别</label>
        <div class="col-md-5">
            <input type="text" class="form-control" id="docLevel" name="docLevel" v-model="docLib.docLevel"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-1 control-label" for="sortNo">文档库排序</label>
        <div class="col-md-5">
            <input type="text" class="form-control" id="sortNo" name="sortNo" v-model="docLib.sortNo"/>
        </div>

        <label class="col-md-1 control-label" for="status">启用状态</label>
        <div class="col-md-5">
            <input type="text" class="form-control" id="status" name="status" v-model="docLib.status"/>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" id="saveBtn" class="btn btn-danger">保存记录</button>
    </div>
</form>

