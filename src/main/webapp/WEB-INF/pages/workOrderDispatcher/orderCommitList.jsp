<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:forEach items="${workOrderReportDetailList}" var="workOrder" varStatus="w">
    <div class="well">
        <div class="form-group">
            <div class="col-md-2">
                <label class="control-label">跟踪号：</label>

                <div class="form-control-static">${workOrder.orderLineNo}</div>
            </div>


            <div class="col-md-2">
                <label class="control-label">设备名称：</label>

                <div class="form-control-static">${workOrder.equipments.description}</div>
            </div>

            <div class="col-md-2">
                <label class="control-label">设备位置：</label>
                <div class="form-control-static">${workOrder.vlocations.locName}</div>
            </div>


            <div class="col-md-2">
                <label class="control-label">设备故障：</label>
                <div class="form-control-static">${workOrder.orderDesc}</div>
            </div>
            <div class="col-md-2">
                <label class="control-label">维修单位：</label>
                <div class="form-control-static">${workOrder.unit.description}
                </div>
            </div>
        </div>
    </div>
</c:forEach>

