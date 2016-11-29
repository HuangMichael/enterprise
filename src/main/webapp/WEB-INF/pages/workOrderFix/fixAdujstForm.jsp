<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="fixAdjustForm" method="post">
    <div class="form-group">
        <div class="col-md-12">
            <div class="form-group">
                <input id="orderId" type="hidden">

                <div class="col-md-2">原维修时限</div>
                <div class="col-md-4">
                    <input type="hidden" name="orderId">
                    <input class="Wdate form-control" id="fixAdjust0"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                           name="fixAdjust"
                           style="height:34px;border:1px solid #cccccc" readonly/>
                </div>
                <div class="col-md-2">调整到</div>
                <div class="col-md-4">
                    <input class="Wdate form-control" id="fixAdjust1"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'fixAdjust0\')}'})"
                           name="fixAdjust"
                           style="height:34px;border:1px solid #cccccc"/>
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消
        </button>
        <button type="button" id="adjustDeadLine" name="adjustDeadLine" class="btn btn-primary btn-danger"
                onclick="confirmAdjust()">确认
        </button>
    </div>
</form>



