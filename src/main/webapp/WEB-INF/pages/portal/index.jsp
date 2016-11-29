<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<% response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<%@include file="../common/common-head.jsp" %>
<body>
<!-- HEADER -->
<%@include file="../common/common-navbar.jsp" %>
<!--/HEADER -->
<!-- PAGE -->
<section id="page">
    <!-- SIDEBAR -->
    <%@include file="../common/common-siderbar.jsp" %>
    <!-- /SIDEBAR -->
    <div id="main-content">
        <div class="container">
            <div class="row">
                <div id="content" class="col-lg-12">
                    <!-- PAGE HEADER-->
                    <%@include file="../common/common-breadcrumb.jsp" %>
                    <div class="clearfix">
                        <span class="date-range pull-right">
											<div class="btn-group">
												<a class="js_update btn btn-default" id="currentMonth"
                                                   onclick="loadChartData(addMonth(0))">当月</a>
												<a class="js_update btn btn-default" id="lastMonth"
                                                   onclick="loadChartData(addMonth(-1))">上月</a>
												 <a class="js_update btn btn-default"><input class="Wdate form-control"
                                                                                             type="text"
                                                                                             onClick="WdatePicker({maxDate:'%y-%M-%d',dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false})"
                                                                                             id="setupDate"
                                                                                             style="width:80%"
                                                                                             name="setupDate"
                                                 /></a>
                                                <a class="js_update btn btn-default" id="displayBtn">显示</a>
											</div>
										</span>
                        <!-- /DATE RANGE PICKER -->
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div id="highCharts0"></div>
                        </div>
                        <div class="col-md-6">
                            <div id="highCharts1"></div>
                        </div>
                        <div class="col-md-12">
                            <div id="highCharts2"></div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="../common/common-foot.jsp" %>
<script type="text/javascript" src="js/Highcharts-4.2.4/js/highcharts.js"></script>
<script type="text/javascript" src="/js/app/portal/portal.js"></script>
<script src="js/Highcharts-4.2.4/js/modules/exporting.js"></script>
</body>
</html>