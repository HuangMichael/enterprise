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
                            <h4 class="appTitle"><i class="fa fa-sitemap"></i>设备信息</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>
                        <div class="box-body">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active"><a href="#tab_1_0" data-toggle="tab"
                                                          style="font-family: 微软雅黑;font-weight: bold">
                                        <i class="fa fa-home" id="eq"></i>设备信息</a>
                                    </li>

                                    <li><a href="#tab_1_1" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold">
                                        <i class="fa fa-flag" id="eqDetail"></i>设备详细信息</a>
                                    </li>
                                    <li><a href="#tab_1_3" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold"><i class="fa fa-lock"
                                                                                          id="history"></i>维修历史信息</a>
                                    </li>
                                    <li><a href="#tab_1_5" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold"><i class="fa fa-lock"
                                                                                          id="profile"></i>设备履历</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="tab_1_0"
                                         style="color: #111;background-color: #fff;border-color: #d26911 #e5e5e5 ">
                                        <div class="form-group" style="margin-bottom:10px;position:inherit"
                                             id="searchBox">
                                            <div class="col-md-2">
                                                <input class="form-control" id="eqCode" type="text" name="eqCode"
                                                       placeholder="设备编号"/>
                                            </div>
                                            <div class="col-md-2">
                                                <input class="form-control" id="eqName" type="text" name="eqName"
                                                       placeholder="设备描述"/>
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
                                                <select class="form-control" id="eqClass" name="eqClass"
                                                        style="width:100%"
                                                        required>
                                                    <option></option>
                                                    <template v-for="option in eqClasses">
                                                        <option :value="option.cname">
                                                            {{option.cname }}
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
                                    <div class="tab-pane fade" id="tab_1_3">
                                        <%@include file="table_1_3.jsp" %>
                                    </div>
                                    <div class="tab-pane fade" id="tab_1_5">
                                        <%@include file="table_1_5.jsp" %>
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
<div class="modal fade " id="show_eq_modal" tabindex="-1" back-drop="false"
     role="dialog" aria-labelledby="fix_work_order">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="fix_work_order">该报修流程还未完工,要继续报修么?</h4>
            </div>
            <div class="modal-body">
                <%@include file="../location/reportedEqList.jsp" %>
            </div>
        </div>
    </div>
</div>

<div class="modal fade " id="track_eq_modal" tabindex="-1" back-drop="false"
     role="dialog" aria-labelledby="fix_work_order">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="track_work_order">查看当前设备维修进度</h4>
            </div>
            <div class="modal-body" id="fix-progress">
                <%@include file="table_1_2.jsp" %>
            </div>
        </div>
    </div>
</div>


<div class="modal fade " id="show_history_modal" tabindex="-1" back-drop="false"
     role="dialog" aria-labelledby="fix_work_order">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="history_work_order">查看维修历史</h4>
            </div>
            <div class="modal-body" id="fix-history">

            </div>
        </div>
    </div>
</div>


<script src="/js/app/equipment/equipments.js"></script>