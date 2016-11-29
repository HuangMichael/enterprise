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

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-md-2">
                                    <select id="selectYear" type="text" name="selectYear" class="form-control">
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <select id="selectUnits" type="text" name="selectUnits" class="form-control">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-md-2">
                                    <select id="selectYear1" type="text" name="selectYear" class="form-control">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div id="highCharts1"></div>
                        </div>
                        <div class="col-md-6">
                            <div id="highCharts0"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-md-2">
                                    <select id="selectYear2" type="text" name="selectYear" class="form-control">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
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
<script type="text/javascript" src="/js/Highcharts-4.2.4/js/highcharts.js"></script>
<script type="text/javascript" src="/js/app/unitsStatistics/unitsStatistics.js"></script>
<script type="text/javascript" src="/js/Highcharts-4.2.4/js/modules/exporting.js"></script>
</body>
</html>