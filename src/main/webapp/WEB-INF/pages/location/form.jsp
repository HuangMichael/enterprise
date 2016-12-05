<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="detailForm">
    <div class="row">
        <div class="col-md-12 col-sm-12 col-lg-12">
            <div class="form-group">
                <label for="description" class="col-md-2 col-sm-2 col-lg-2 control-label">位置名称</label>
                <div class="col-md-3 col-sm-3 col-lg-3">
                    <input class="form-control" id="description" type="text" name="description"
                           value="${location.description}" v-model="location.description" required/>
                </div>
                <label class="col-md-2 col-sm-2 col-lg-2 control-label" for="superior">负责人员</label>
                <div class="col-md-3 col-sm-3 col-lg-3">
                    <input class="form-control" id="superior" type="text" name="superior" value="${location.superior}"
                           v-model="location.superior"/>
                    <input class="form-control" id="id" type="hidden" name="id" v-model="location.id"/>
                    <input class="form-control" id="location" type="hidden" name="location" v-model="location.location"/>
                    <input class="form-control" id="parent" type="hidden" name="parent" v-model="location.parent"/>
                    <input class="form-control" id="locLevel" type="hidden" name="locLevel"
                           v-model="location.locLevel"/>
                    <input class="form-control" id="status" type="hidden" name="status" value="1"
                           v-model="location.status"/>
                </div>
                <div class="col-md-2 col-sm-2 col-lg-2">
                    <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger btn-sm">保存记录
                    </button>
                </div>

            </div>
        </div>
    </div>
</form>