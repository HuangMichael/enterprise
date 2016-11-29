<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String docPath = "/docs/wocost/工单物资消耗模板.xls";

%>
<!-- /SAMPLE BOX CONFIGURATION MODAL FORM-->
<div class="container" id="equipmentsApp">
    <div class="row">
        <div id="content" class="col-lg-12">
            <!-- PAGE HEADER-->
            <%@include file="../common/common-breadcrumb.jsp" %>
            <div class="row">
                <div class="col-md-12">
                    <!-- BOX -->
                    <div class="box border blue">
                        <div class="box-title">
                            <h4 class="appTitle"><i class="fa fa-sitemap"></i>工单物料消耗</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>


                        <div class="box-body">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active">
                                        <a href="#list_tab" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold">
                                            <i class="fa fa-home" id="list"></i>工单物料消耗</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="list_tab"
                                         style="color: #111;background-color: #fff;border-color: #d26911 #e5e5e5 ">

                                        <div class="form-group" style="margin-bottom:10px;position:inherit"
                                             id="searchBox">

                                            <div class="col-md-2">
                                                <input class="form-control" id="orderLineNo" type="text"
                                                       name="orderLineNo"
                                                       placeholder="跟踪号"/>
                                            </div>

                                            <div class="col-md-2">
                                                <input class="form-control" id="matName" type="text" name="matName"
                                                       placeholder="耗材名称"/>
                                            </div>

                                            <div class="col-md-2">
                                                <input class="form-control" id="matModel" type="text" name="matModel"
                                                       placeholder="耗材型号"/>
                                            </div>

                                            <div class="col-md-2">
                                                <button id="searchBtn" class="btn btn-default" onclick="search()">查询
                                                </button>
                                            </div>
                                        </div>


                                        <div class="container-fluid">
                                            <table id="workOrderMatCostDataTable"
                                                   class="table  table-striped  table-bordered table-hover"
                                                   data-toggle="bootgrid" data-ajax="true"
                                                   data-url="/workOrderMatCost/data">
                                                <thead>
                                                <tr>
                                                    <th data-column-id="id" data-type="numeric" data-identifier="true"
                                                        data-visible="false" width="2%">序号
                                                    </th>
                                                    <th data-align="center" data-column-id="orderLineNo" width="10%">
                                                        工单编号
                                                    </th>
                                                    <th data-align="center" data-column-id="matName" width="20%">
                                                        物资名称
                                                    </th>
                                                    <th data-align="center" data-column-id="matModel" width="10%">
                                                        物资型号
                                                    </th>
                                                    <th data-align="center" data-column-id="matAmount" width="10%">
                                                        数量
                                                    </th>
                                                    <th data-align="center" data-column-id="matPrice" width="10%">
                                                        单价
                                                    </th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../common/common-back2top.jsp" %>
</div>


<div class="modal fade " id="import_modal" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">请选择导入的excel文件</h4>
            </div>
            <div class="modal-body">
                <%@include file="matCostList.jsp" %>
            </div>
        </div>
    </div>
</div>
<script src="/js/app/workOrderMatCost/workOrderMatCost.js"></script>