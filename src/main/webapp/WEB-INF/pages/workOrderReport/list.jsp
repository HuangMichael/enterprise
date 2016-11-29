<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="http://yandex.st/highlightjs/7.3/styles/default.min.css">
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
                            <h4><i class="fa fa-table"></i>报修单信息</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>
                        <div class="box-body">
                            <div id="contentDiv">
                                <div class="box-body">
                                    <div class="tabbable">
                                        <ul class="nav nav-tabs" id="myTab">
                                            <li class="active">
                                                <a href="#tab_1_0" data-toggle="tab">
                                                    <i class="fa fa-home" id="eq"></i>报修单信息</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="form-group" style="margin-bottom:10px;position:inherit"
                                                 id="searchBox">
                                                <div class="col-md-2">
                                                    <input class="form-control" id="eqCode" type="text"
                                                           name="orderLineNo"
                                                           placeholder="跟踪号"/>
                                                </div>
                                                <div class="col-md-2">
                                                    <input class="form-control" id="orderDesc" type="text" name="orderDesc"
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
                                                    <button id="searchBtn" class="btn btn-default" onclick="search()">查询
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade in active" id="tab_1_0">
                                                <table id="fixListTable"
                                                       class="table table-bordered table-hover table-striped"
                                                       data-toggle="bootgrid" data-ajax="true"
                                                       data-url="/workOrderReport/data">
                                                    <thead>
                                                    <tr>
                                                        <th data-column-id="id" data-width="10%">序号</th>
                                                        <th data-column-id="orderLineNo" data-width="10%">跟踪号</th>
                                                        <th data-column-id="eqName" data-width="10%">设备名称</th>
                                                        <th data-column-id="orderDesc" data-width="20%">故障描述</th>
                                                        <th data-column-id="locName" data-width="10%">设备位置</th>
                                                        <th data-column-id="eqClass" data-width="8%">设备分类</th>
                                                        <th data-column-id="nodeTime" data-width="10%">生成时间</th>
                                                        <th data-column-id="nodeState" data-width="5%">报修状态</th>
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
                    <!-- /BOX -->
                </div>
            </div>
            <%@include file="../common/common-back2top.jsp" %>
        </div>
        <!-- /CONTENT-->
    </div>
</div>
<script type="text/javascript" src="/js/app/report/reportBill.js"></script>
