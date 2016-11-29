<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="row">
        <div id="content" class="col-lg-12">
            <!-- PAGE HEADER-->
            <%@include file="../common/common-breadcrumb.jsp" %>
            <div class="row">
                <div class="col-md-12">
                    <!-- BOX -->
                    <div class="box border blue">
                        <div class="box-title">
                            <h4><i class="fa fa-sitemap"></i>维修单查询</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>
                        <div class="box-body">
                            <div id="contentDiv">
                                <div class="box-body">
                                    <div class="tabbable">
                                        <ul class="nav nav-tabs" id="myTab">
                                            <li class="active">
                                                <a href="#tab_1_0" data-toggle="tab">
                                                    <i class="fa fa-user" id="eq"></i>已派工</a>
                                            </li>
                                            <li>
                                                <a href="#tab_1_1" data-toggle="tab">
                                                    <i class="fa fa-home" id="eq1"></i>已完工</a>
                                            </li>
                                            <li>
                                                <a href="#tab_1_2" data-toggle="tab">
                                                    <i class="fa  fa-caret-square-o-right" id="eq2"></i>已暂停</a>
                                            </li>
                                            <li>
                                                <a href="#tab_1_3" data-toggle="tab">
                                                    <i class="fa  fa-remove" id="eq3"></i>已取消</a>
                                            </li>

                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane fade in active" id="tab_1_0">
                                                <div class="form-group" style="margin-bottom:10px;position:inherit"
                                                     id="searchBox">
                                                    <div class="col-md-2">
                                                        <input class="form-control" id="eqCode" type="text"
                                                               name="orderLineNo"
                                                               placeholder="跟踪号"/>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <input class="form-control" id="orderDesc" type="text"
                                                               name="orderDesc"
                                                               placeholder="故障描述"/>
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
                                                        <button id="searchBtn" class="btn btn-default"
                                                                onclick="search()">查询
                                                        </button>
                                                    </div>
                                                </div>


                                                <table id="fixListTable0"
                                                       class="table table-striped table-bordered table-hover  table-responsive"
                                                       data-toggle="bootgrid" data-ajax="true"
                                                       data-url="/workOrderFix/data/0">
                                                    <thead>
                                                    <tr>
                                                        <th data-column-id="orderLineNo" data-width="8%">跟踪号</th>
                                                        <th data-column-id="id" data-type="numeric"
                                                            data-identifier="true" data-visible="false">ID
                                                        </th>
                                                        <th data-column-id="eqName" data-width="10%">设备名称</th>
                                                        <th data-column-id="locName" data-width="8%">设备位置</th>
                                                        <th data-column-id="orderDesc" data-width="15%">故障描述</th>
                                                        <th data-column-id="eqClass" data-width="5%">设备分类</th>
                                                        <th data-column-id="unitName" data-width="10%"
                                                            data-visible="false">维修单位
                                                        </th>
                                                        <th data-column-id="nodeState" data-width="5%">维修状态</th>
                                                        <th data-column-id="nodeTime" data-width="8%" data-order="desc">
                                                            处理时间
                                                        </th>
                                                        <th data-column-id="deadLine" data-width="8%">截止日期</th>
                                                        <th data-column-id="expired" data-width="5%"
                                                            data-sortable="false" align="center">是否超期
                                                        </th>
                                                        <th data-column-id="opMenus" data-formatter="opMenus"
                                                            data-sortable="false" data-width="8%">暂停&nbsp;取消&nbsp;完工

                                                        </th>
                                                    </tr>
                                                    </thead>
                                                </table>
                                            </div>
                                            <div class="tab-pane fade in" id="tab_1_1">
                                                <table id="fixListTable1"
                                                       class="table table-striped table-bordered table-hover  table-responsive"
                                                       data-toggle="bootgrid" data-ajax="true"
                                                       data-url="/workOrderFix/data/1">
                                                    <thead>
                                                    <tr>
                                                        <th data-column-id="orderLineNo" data-width="8%">跟踪号</th>
                                                        <th data-column-id="eqName" data-width="10%">设备名称</th>
                                                        <th data-column-id="locName" data-width="8%">设备位置</th>
                                                        <th data-column-id="orderDesc" data-width="15%">故障描述</th>
                                                        <th data-column-id="eqClass" data-width="5%">设备分类</th>
                                                        <th data-column-id="unitName" data-width="10%"
                                                            data-visible="false">维修单位
                                                        </th>
                                                        <th data-column-id="nodeState" data-width="5%">维修状态</th>
                                                        <th data-column-id="nodeTime" data-width="8%" data-order="desc">
                                                            处理时间
                                                        </th>
                                                        <th data-column-id="deadLine" data-width="8%">截止日期</th>
                                                    </tr>
                                                    </thead>

                                                </table>
                                            </div>
                                            <div class="tab-pane fade in" id="tab_1_2">
                                                <table id="fixListTable2"
                                                       class="table table-striped table-bordered table-hover  table-responsive"
                                                       data-toggle="bootgrid" data-ajax="true"
                                                       data-url="/workOrderFix/data/2">
                                                    <thead>
                                                    <tr>
                                                        <th data-column-id="orderLineNo" data-width="8%">跟踪号</th>
                                                        <th data-column-id="eqName" data-width="10%">设备名称</th>
                                                        <th data-column-id="locName" data-width="8%">设备位置</th>
                                                        <th data-column-id="orderDesc" data-width="15%">故障描述</th>
                                                        <th data-column-id="eqClass" data-width="5%">设备分类</th>
                                                        <th data-column-id="unitName" data-width="10%"
                                                            data-visible="false">维修单位
                                                        </th>
                                                        <th data-column-id="status" data-width="5%">维修状态</th>
                                                        <th data-column-id="nodeTime" data-width="8%" data-order="desc">
                                                            处理时间
                                                        </th>
                                                        <th data-column-id="deadLine" data-width="8%">截止日期</th>

                                                    </tr>
                                                    </thead>

                                                </table>
                                            </div>
                                            <div class="tab-pane fade in" id="tab_1_3">
                                                <table id="fixListTable3"
                                                       class="table table-striped table-bordered table-hover  table-responsive"
                                                       data-toggle="bootgrid" data-ajax="true"
                                                       data-url="/workOrderFix/data/3">
                                                    <thead>
                                                    <tr>
                                                        <th data-column-id="orderLineNo" data-width="8%">跟踪号</th>
                                                        <th data-column-id="eqName" data-width="10%">设备名称</th>
                                                        <th data-column-id="locName" data-width="8%">设备位置</th>
                                                        <th data-column-id="orderDesc" data-width="15%">故障描述</th>
                                                        <th data-column-id="eqClass" data-width="5%">设备分类</th>
                                                        <th data-column-id="unitName" data-width="10%"
                                                            data-visible="false">维修单位
                                                        </th>
                                                        <th data-column-id="nodeState" data-width="5%">维修状态</th>
                                                        <th data-column-id="nodeTime" data-width="8%" data-order="desc">
                                                            处理时间
                                                        </th>
                                                        <th data-column-id="deadLine" data-width="8%">截止日期</th>

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
    </div>
    <%@include file="../common/common-back2top.jsp" %>
</div>

<div class="modal fade " id="fix_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">维修单明细信息</h4>
            </div>
            <div class="modal-body" id="modal_div">
                <%@include file="form.jsp" %>
            </div>
        </div>
    </div>
</div>

<div class="modal fade " id="fix_desc_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="fix_desc_modal_label">请输入维修描述</h4>
            </div>
            <div class="modal-body" id="fix_desc_modal_div">
                <%@include file="fixDescForm.jsp" %>
            </div>
        </div>
    </div>
</div>

<div class="modal fade " id="fix_adjust_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="fix_adjust_modal_desc">请选择调整后的时间</h4>
            </div>
            <div class="modal-body" id="fix_adjust_modal_div">
                <%@include file="fixAdujstForm.jsp" %>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/app/fix/fix.js"></script>