<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal myform" role="form" id="detailForm">
    <div class="form-group">
        <label class="col-md-2 control-label" for="lineNo">线路编号</label>
        <div class="col-md-4 ">
            <input type="text" class="form-control" id="lineNo" name="lineNo" v-model="line.lineNo">
            <input type="hidden" class="form-control" id="id" name="id" v-model="line.id">
        </div>
        <div class="col-md-2 control-label">
            <label for="description">线路描述</label>
        </div>
        <div class="col-md-4 ">
            <input type="text" class="form-control" id="description" name="description" v-model="line.description">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="type">类型</label>
        <div class="col-md-4">
            <select class="form-control" id="type" name="type" required v-model="line.type" style="width:100%" required>
                <template v-for="option in type ">
                    <option :value="option.key" v-if="option.key == line.type" selected>
                        {{ option.value }}
                    </option>
                    <option :value="option.key" v-else>
                        {{ option.value }}
                    </option>
                </template>
            </select>
        </div>
        <label class="col-md-2 control-label" for="status">状态</label>
        <div class="col-md-4">
            <select class="form-control" id="status" name="status" required v-model="line.status" style="width:100%"
                    required>
                <template v-for="option in statuses ">
                    <option :value="option.key" v-if="option.key == line.status" selected>
                        {{ option.value }}
                    </option>
                    <option :value="option.key" v-else>
                        {{ option.value }}
                    </option>
                </template>
            </select>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-danger">保存记录</button>
    </div>
</form>