<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal myform" role="form" id="detailForm">
    <div class="form-group">
        <label class="col-md-1 control-label" for="personNo">人员编号</label>

        <div class="col-md-3">
            <input type="hidden" class="form-control" id="id" name="id" v-model="person.id"/>
            <input type="text" class="form-control" id="personNo" name="personNo" v-model="person.personNo" required/>
        </div>
        <label class="col-md-1 control-label" for="personName">人员姓名</label>

        <div class="col-md-3">
            <input type="text" class="form-control" id="personName" name="personName" v-model="person.personName"/>
        </div>
        <label class="col-md-1 control-label" for="telephone">联系电话</label>

        <div class="col-md-3">
            <input type="tel" class="form-control" id="telephone" name="telephone" v-model="person.telephone"/>
        </div>
    </div>
    <div class="form-group">

        <label class="col-md-1 control-label" for="email">电子邮箱</label>

        <div class="col-md-3">
            <input type="email" class="form-control" id="email" name="email" v-model="person.email"/>
        </div>

        <label class="col-md-1 control-label" for="status">使用状态</label>

        <div class="col-md-3"><select class="form-control" id="status" name="status" v-model="person.status"
                                      style="width: 100%">
            <option value="1">启用</option>
            <option value="0">禁用</option>

        </select>
        </div>

        <label class="col-md-1 control-label" for="sortNo">排序</label>
        <div class="col-md-3">
            <input class="form-control" type="text" id="sortNo" name="sortNo"
                   v-model="person.sortNo"/>
        </div>


    </div>
    <div class="modal-footer">
        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger">保存记录
        </button>
    </div>
</form>

