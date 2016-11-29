<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal myform" role="form" id="createForm">
    <div class="form-group">
        <label class="col-md-2 col-sm-2 col-lg-2" for="userName">用户名</label>

        <div class="col-md-4 col-sm-4 col-lg-4">
            <input type="text" class="form-control" id="userName" name="userName" v-model="user.userName">
            <input type="hidden" class="form-control" id="userId" name="userId" v-model="user.id">
        </div>
        <div class="col-md-2 col-sm-2 col-lg-2">
            <label for="person_id">人员</label>
        </div>
        <div class="col-md-4 col-sm-4 col-lg-4">
            <select class="form-control" id="person_id" name="person.id" required v-model="user.person.id"
                    style="width:100%" required>
                <template v-for="option in activePerson">
                    <option :value="option.id" v-if="option.id == user.person.id" selected>
                        {{option.personName}}
                    </option>
                    <option :value="option.id" v-else>
                        {{option.personName}}
                    </option>
                </template>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-2 col-sm-2 col-lg-2">
            <label for="locations_id">我的位置</label>
        </div>
        <div class="col-md-4 col-sm-4 col-lg-4">
            <select v-model="user.vlocations.id" class="form-control" id="locations_id" name="locations_id"
                    required style="width:100%" required>
                <template v-for="option in locs">
                    <option :value="option.id" v-if="option.id == user.vlocations.id" selected>
                        {{option.locName }}
                    </option>
                    <option :value="option.id" v-else>
                        {{option.locName }}
                    </option>
                </template>
            </select>
        </div>

        <div class="col-md-2 col-sm-2 col-lg-2">
            <label for="status">用户状态</label>
        </div>
        <div class="col-md-4 col-sm-4 col-lg-4">
            <select class="form-control" id="status" name="status" required v-model="user.status" style="width:100%"
                    required>
                <option value="1" selected>启用</option>
                <option value="0">禁用</option>
            </select>
        </div>
    </div>
  <%--  <div class="modal-footer">
        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger">创建用户
        </button>
    </div>--%>
</form>


<script type="text/javascript">


    $(function () {

        $("select").select2({theme: "bootstrap"});

    });


</script>

