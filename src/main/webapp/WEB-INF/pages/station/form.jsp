<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group">
    <label class="col-md-2" for="stationNo">车站编号</label>
    <div class="col-md-4">
        <input type="text" class="form-control" id="stationNo" name="stationNo" v-model="station.stationNo">
        <input type="hidden" class="form-control" id="id" name="id" v-model="station.id">
    </div>
    <div class="col-md-2">
        <label for="description">车站名称</label>
    </div>
    <div class="col-md-4">
        <input type="text" class="form-control" id="description" name="description" v-model="station.description">
    </div>
</div>
<div class="form-group">
    <div class="col-md-2"><label for="lineId">线路名称</label></div>
    <div class="col-md-4">
        <select v-model="station.line.id" class="form-control" id="lineId" name="line.id" required style="width:100%"
                required>
            <template v-for="option in lines">
                <option :value="option.id" v-if="option.id == station.line.id" selected>
                    {{option.description }}
                </option>
                <option :value="option.id" v-else>
                    {{option.description }}
                </option>
            </template>
        </select>
    </div>
    <div class="col-md-2"><label for="type">车站类型</label></div>
    <div class="col-md-4">
        <select v-model="station.type" class="form-control" id="type" name="type" required style="width:100%" required>
            <template v-for="option in types">
                <option :value="option.id" v-if="option.id == station.type" selected>
                    {{option.typeName }}
                </option>
                <option :value="option.id" v-else>
                    {{option.typeName }}
                </option>
            </template>
        </select>
    </div>
</div>