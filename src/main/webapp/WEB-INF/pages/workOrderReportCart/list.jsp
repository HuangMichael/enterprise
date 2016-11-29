<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../common/common-head.jsp" %>

<div class="container">
    <div class="row">
        <div id="content" class="col-lg-12">
            <!-- PAGE HEADER-->
            <%@include file="../common/common-breadcrumb.jsp" %>
            <div class="row">
                <div class="col-md-12">
                    <!-- BOX -->
                    <div class="box border blue" id="formWizard">
                        <div class="box-title">
                            <h4><i class="fa fa-bars"></i>报修车信息<span class="stepHeader">第1步 共3步</span></h4>
                        </div>
                        <div class="box-body form">
                            <form id="wizForm" action="#" class="form-horizontal">
                                <div class="wizard-form">
                                    <div class="wizard-content">
                                        <ul class="nav nav-pills nav-justified steps">
                                            <li>
                                                <a href="#account" data-toggle="tab" class="wiz-step">
                                                    <span class="step-number">1</span>
                                                            <span class="step-name"><i
                                                                    class="fa fa-check"></i> 报修车信息</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#payment" data-toggle="tab" class="wiz-step active">
                                                    <span class="step-number">2</span>
                                                            <span class="step-name"><i
                                                                    class="fa fa-check"></i>故障描述</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#confirm" data-toggle="tab" class="wiz-step">
                                                    <span class="step-number">3</span>
                                                            <span class="step-name"><i
                                                                    class="fa fa-check"></i>提交报修单 </span>
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="bar" class="progress progress-striped progress-sm active"
                                             role="progressbar">
                                            <div class="progress-bar progress-bar-warning"></div>
                                        </div>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="account">
                                                <table id="cartTable"
                                                       class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr id="trr">
                                                        <td><input type="checkbox" name="cartCheck" id="cartCheck"
                                                                   onclick="checkAll(this)" value=""></td>
                                                        <th>序号</th>
                                                        <th>跟踪号</th>
                                                        <th>设备编号</th>
                                                        <th>设备名称</th>
                                                        <th>设备位置</th>
                                                        <th>设备分类</th>
                                                        <th class="hidden-xs hidden-sm">报告人</th>
                                                        <th class="hidden-xs hidden-sm">
                                                            报告时间
                                                        </th>
                                                        <th>移除</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody">
                                                    <c:forEach items="${workOrderReportCartList}" var="workOrder"
                                                               varStatus="w">
                                                        <tr id="tr${workOrder.id}">
                                                            <td><input type="checkbox" name="cartCheck${workOrder.id}"
                                                                       value="${workOrder.id}">
                                                            </td>
                                                            <td>${w.index+1}</td>
                                                            <td>${workOrder.orderLineNo}</td>
                                                            <td>${workOrder.equipments.eqCode}</td>
                                                            <td>${workOrder.equipments.description}</td>
                                                            <td>${workOrder.vlocations.locName}</td>
                                                            <td>${workOrder.equipmentsClassification.description}</td>
                                                            <td>${workOrder.reporter}</td>
                                                            <td><fmt:formatDate value="${workOrder.reportTime}"
                                                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                            <td><a class="btn  btn-default btn-xs"
                                                                   onclick="delCart(${workOrder.id})" title="移除报修车"><i
                                                                    class="glyphicon glyphicon-trash"></i></a></td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tab-pane" id="payment">


                                            </div>
                                            <div class="tab-pane" id="confirm">



                                            </div>
                                        </div>
                                    </div>
                                    <div class="wizard-buttons">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <a href="javascript:;" class="btn btn-default prevBtn">
                                                        <i class="fa fa-arrow-circle-left"></i> 上一步
                                                    </a>
                                                    <a href="javascript:;" class="btn btn-primary nextBtn">
                                                        下一步 <i class="fa fa-arrow-circle-right"></i>
                                                    </a>
                                                    <a href="javascript:;" class="btn btn-danger submitBtn">
                                                        确认提交 <i class="fa fa-arrow-circle-right"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../common/common-back2top.jsp" %>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/bootstrap-wizard/form-wizard.min.js"></script>
<script type="text/javascript" src="/js/app/workOrder/reportCart.js"></script>

