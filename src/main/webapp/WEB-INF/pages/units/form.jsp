<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="detailForm">
    <div class="form-group">
        <div class="col-md-12">
            <div class="form-group">
                <label for="unitNo" class="col-md-1 control-label">单位编号</label>

                <div class="col-md-3">
                    <input class="form-control" name="unitNo" id="unitNo" v-model="units.unitNo" required lazy
                           @change="checkUnitNo()"/>
                    <input class="form-control" type="hidden" name="id" id="id" v-model="units.id"/>
                </div>

                <label for="description" class="col-md-1 control-label">单位名称</label>

                <div class="col-md-3">
                    <input class="form-control" id="description" name="description" v-model="units.description"
                           required/>
                </div>
                <label for="linkman" class="col-md-1 control-label">联系人</label>

                <div class="col-md-3">
                    <input class="form-control" name="linkman" id="linkman" v-model="units.linkman"/>
                </div>
            </div>
            <div class="form-group">
                <label for="telephone" class="col-md-1 control-label">联系电话</label>

                <div class="col-md-3">
                    <input class="form-control" id="telephone" name="telephone" v-model="units.telephone"/>
                </div>

                <label for="status" class="col-md-1 control-label">工作制</label>

                <div class="col-md-3">
                    <select class="form-control" id="workDays" name="workDays" style="width:100%"
                            v-model="units.workDays">
                        <option value="5">5天</option>
                        <option value="7">7天</option>
                    </select>
                </div>
                <label for="status" class="col-md-1 control-label">状态</label>

                <div class="col-md-3">
                    <select class="form-control" id="status" name="status" style="width:100%" v-model="units.status">
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger">保存记录
        </button>
    </div>
</form>