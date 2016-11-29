<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="shareWorkOrderForm" method="post">
    <div class="form-group">
        <div class="col-md-12">
            <div class="form-group">
                <label for="limitedHours" class="col-md-2 control-label">时限(小时)</label>
                <div class="col-md-10">
                    <input class="form-control" id="limitedHours" type="number" min="0"
                           name="limitedHours" required="true"/>
                </div>
            </div>
            <div class="form-group">
                <label for="unit_id" class="col-md-2 control-label">维修单位</label>
                <div class="col-md-10">
                    <form:select path="outsourcingUnitList" class="form-control" id="unit_id">
                        <form:options itemLabel="description" items="${outsourcingUnitList}" itemValue="id"></form:options>
                    </form:select>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default"
                data-dismiss="modal">关闭
        </button>
        <button type="button" id="shareWorkOrder" name="shareWorkOrder"
                class="btn btn-primary">保存
        </button>
    </div>
    </div>
</form>



