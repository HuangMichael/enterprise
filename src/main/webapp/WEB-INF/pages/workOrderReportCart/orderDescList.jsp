<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<table id="fillTable"
       class="table table-responsive table-condensed table-striped  table-hover">
    <thead>
    <tr id="trr2">
        <th width="10%">序号</th>
        <th width="10%">设备编号</th>
        <th width="15%">设备名称</th>
        <th width="15%">设备位置</th>
        <th width="10%">设备分类</th>
        <th width="15%">故障描述</th>
        <th width="10%">报告人</th>
        <th width="10%">报修时间</th>
    </tr>
    </thead>
    <tbody id="tbody2">
    <c:forEach items="${workOrderReportCartList}" var="workOrder" varStatus="w">
        <tr id="tr-${w.index+1}" class="gradeX">
            <td width="10%">${w.index+1}</td>
            <td width="10%">${workOrder.equipments.eqCode}</td>
            <td width="15%">${workOrder.equipments.description}</td>
            <td width="15%">${workOrder.vlocations.locName}</td>
            <td width="10%">${workOrder.equipmentsClassification.description}</td>
            <td width="15%"><input type="text" id="orderDesc${workOrder.id}" class="form-control" style="height:28px"
                                   value="${workOrder.orderDesc}" onchange="changeContent(${workOrder.id})"/>
            <td width="10%"><input type="text" id="reporter${workOrder.id}" class="form-control" style="height:28px"
                                   value="${workOrder.reporter}" onchange="changeReporter(${workOrder.id})"/>
            </td>
            <td width="10%"><input class="Wdate form-control" type="text" onClick="WdatePicker()"
                                   id="reportTime${workOrder.id}" value="${workOrder.reportTime}"
                                   style="height:28px;width:80%;border:1px solid #cccccc"/></td>
            <td width="10%"><a class="btn  btn-default btn-xs"
                               onclick="changeReportTime(${workOrder.id})" title="修改时间"><i
                    class="glyphicon glyphicon-refresh"></i></a>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    /**
     *
     * @param id  报修明细ID
     * @param orderDesc 故障描述内容
     */
    function changeContent(id) {
        var orderDesc = $("#orderDesc" + id).val();
        if (!orderDesc) {
            showMessageBox("danger", "故障描述不能为空,请输入故障描述!");
            return;
        }
        $.ajaxSettings.async = false;
        var url = "/workOrderReportCart/updateOrderDesc";
        $.post(url, {id: id, orderDesc: orderDesc}, function (data) {
            if (data) {
                showMessageBox("info", "故障描述更新成功!");
                return;
            }
        });
    }

    /**
     *
     * @param id  报修明细ID
     * @param orderDesc 故障描述内容
     */
    function changeReporter(id) {
        var reporter = $("#reporter" + id).val();
        if (!reporter) {
            showMessageBox("danger", "报告人不能为空,请输入报告人!");
            return;
        }
        $.ajaxSettings.async = false;
        var url = "/workOrderReportCart/updateReporter";
        $.post(url, {id: id, reporter: reporter}, function (data) {
            if (data) {
                showMessageBox("info", "报告人更新成功!");
                return;
            }
        });
    }

    /**
     *
     * @param id  报修明细ID
     * 修改报修时间
     */
    function changeReportTime(id) {
        var reportTime = $("#reportTime" + id).val();
        console.log(reportTime);
        if (!reportTime) {
            showMessageBox("danger", "报修时间不能为空,请输入报修时间!");
            return;
        }
        $.ajaxSettings.async = false;
        var url = "/workOrderReportCart/updateReportTime";
        $.post(url, {id: id, reportTime: reportTime}, function (data) {
            if (data) {
                showMessageBox("info", "报修时间更新成功!");
                return;
            }
        });
    }
</script>


