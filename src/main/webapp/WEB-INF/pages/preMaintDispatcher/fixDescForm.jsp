<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="fixDescForm" method="post">
    <div class="form-group">
        <div class="col-md-12">
            <div class="form-group">
                <label for="fixDesc" class="col-md-2 control-label">维修描述</label>
                <input id="operationType" type="hidden">
                <input id="orderId" type="hidden">
                <input id="operationDesc" type="hidden">
                <div class="col-md-10">
                    <textarea class="form-control" id="fixDesc" name="fixDesc" rows="5" placeholder="请输入维修描述" required/>
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消
        </button>
        <button type="button" id="saveFixDesc" name="saveFixDesc" class="btn btn-primary btn-danger">确认
        </button>
    </div>
</form>



