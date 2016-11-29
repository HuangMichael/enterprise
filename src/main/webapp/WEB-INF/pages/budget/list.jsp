<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
                            <h4 class="appTitle"><i class="fa fa-sitemap"></i>采购申请</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>
                        <div class="box-body">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active">
                                        <a href="#list_tab" data-toggle="tab" class="tab_title">
                                            <i class="fa fa-home" id="list"></i>列表信息</a>
                                    </li>
                                    <li>
                                        <a href="#detail_tab" data-toggle="tab" class="tab_title">
                                            <i class="fa fa-home" id="detail"></i>明细信息</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="list_tab"
                                         style="color: #111;background-color: #fff;border-color: #d26911 #e5e5e5 ">

                                        <div class="form-group" style="margin-bottom:10px;position:inherit"
                                             id="searchBox">
                                            <div class="col-md-2">
                                                <input class="Wdate form-control" id="beginDate"
                                                       name="beginDate"
                                                       onLoad="WdatePicker({maxDate:'%y-%M-%d'})"
                                                       onClick="WdatePicker({maxDate:'%y-%M-%d'})"
                                                       style="height:34px;border:1px solid #cccccc"/>

                                            </div>
                                            <div class="col-md-2">
                                                <input class="Wdate form-control" id="endDate" name="endDate"
                                                       onLoad="WdatePicker({maxDate:'%y-%M-%d'})"
                                                       onClick="WdatePicker({maxDate:'%y-%M-%d'})"
                                                       style="height:34px;border:1px solid #cccccc"
                                                />

                                            </div>

                                            <div class="col-md-2">
                                                <input class="form-control" id="accessoryName" type="text"
                                                       name="accessoryName"
                                                       placeholder="配件名称"/>
                                            </div>
                                            <div class="col-md-2">
                                                <input class="form-control" id="applyDep" type="text"
                                                       name="applyDep"
                                                       placeholder="申请部门"/>
                                            </div>

                                            <div class="col-md-2">
                                                <button id="searchBtn" class="btn btn-default" onclick="search()">查询
                                                </button>
                                            </div>
                                        </div>


                                        <div class="container-fluid">
                                            <table id="budgetDataTable"
                                                   class=" table table-striped table-bordered table-hover"
                                                   data-toggle="bootgrid" data-ajax="true" data-url="/budget/data">
                                                <thead>
                                                <tr>
                                                    <th data-column-id="id" data-type="numeric" data-identifier="true"
                                                        data-visible="false">ID
                                                    </th>
                                                    <th data-align="center" data-column-id="applyDate" data-width="5%">
                                                        采购日期
                                                    </th>
                                                    <th data-align="center" data-column-id="accessoryName"
                                                        data-width="10%">配件名称
                                                    </th>
                                                    <th data-align="center" data-column-id="specifications"
                                                        data-width="5%">规格型号
                                                    </th>
                                                    <th data-align="center" data-column-id="locName" data-width="10%">
                                                        位置
                                                    </th>
                                                    <th data-align="center" data-column-id="applicant" data-width="5%">
                                                        申请人
                                                    </th>
                                                    <th data-align="center" data-column-id="applyDep" data-width="5%">
                                                        申请部门
                                                    </th>
                                                    <th data-align="center" data-column-id="amount" data-width="5%">
                                                        申请数量
                                                    </th>
                                                    <th data-align="center" data-column-id="purpose" data-width="20%">
                                                        用途
                                                    </th>
                                                    <th data-align="center" data-column-id="approver" data-width="5%">
                                                        批准人
                                                    </th>
                                                    <th data-align="center" data-column-id="handler" data-width="5%">
                                                        经办人
                                                    </th>
                                                    <th data-align="center" data-column-id="receiver" data-width="5%">
                                                        接收人
                                                    </th>
                                                </tr>
                                                </thead>

                                            </table>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="detail_tab"
                                         style="color: #111;background-color: #fff;border-color: #d26911 #e5e5e5 ">
                                        <%@include file="detail.jsp" %>
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
<script src="/js/app/budget/budget.js"></script>