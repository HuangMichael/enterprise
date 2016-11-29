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
                            <h4 class="appTitle"><i class="fa fa-sitemap"></i>预防性维修信息</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>
                        <div class="box-body">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active"><a href="#tab_1_0" data-toggle="tab"
                                                          style="font-family: 微软雅黑;font-weight: bold">
                                        <i class="fa fa-home" id="eq"></i>预防性维修信息</a>
                                    </li>

                                    <li><a href="#tab_1_1" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold">
                                        <i class="fa fa-flag" id="eqDetail"></i>预防性维修详细信息</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="tab_1_0"  style="color: #111;background-color: #fff;border-color: #d26911 #e5e5e5 ">

                                        <div class="form-group" style="margin-bottom:10px;position:inherit"
                                             id="searchBox">
                                            <div class="col-md-2">
                                                <input class="form-control" id="pmDesc" type="text"
                                                       name="pmDesc"
                                                       placeholder="计划描述"/>
                                            </div>
                                            <div class="col-md-2">
                                                <select class="form-control" id="locName" name="locName"
                                                        style="width:100%" required>
                                                    <option></option>
                                                    <template v-for="option in locs">
                                                        <option :value="option.location">
                                                            {{option.locName }}
                                                        </option>
                                                    </template>
                                                </select>
                                            </div>

                                            <div class="col-md-2">
                                                <button id="searchBtn" class="btn btn-default" onclick="search()">查询
                                                </button>
                                            </div>
                                        </div>
                                        <%@include file="table_1_0.jsp" %>
                                    </div>
                                    <div class="tab-pane fade" id="tab_1_1">
                                        <%@include file="table_1_1.jsp" %>
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

<div class="modal fade " id="confirm_modal" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">请选择预防性维修工单截止日期</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form class="form-horizontal myform" role="form" id="form">
                            <label class="col-md-3 control-label" for="deadLine">截止日期</label>
                            <div class="col-md-5">
                                <input class="Wdate form-control" id="deadLine"
                                       onLoad="WdatePicker({minDate:'%y-%M-%d'})"
                                       onClick="WdatePicker({minDate:'%y-%M-%d'})" name="deadLine"
                                       style="height:34px;border:1px solid #cccccc"/>
                            </div>
                            <div class="col-md-4">
                                <button type="button" id="saveBtn" name="saveBtn" class="btn btn-primary btn-danger" onclick="generateOrder()">
                                    生成工单
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/app/preMaint/preMaint.js"></script>
