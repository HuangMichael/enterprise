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
                            <h4 class="appTitle"><i class="fa fa-sitemap"></i>物料消耗</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>
                        <div class="box-body">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active">
                                        <a href="#list_tab" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold">
                                            <i class="fa fa-home" id="list"></i>物料消耗</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="list_tab"
                                         style="color: #111;background-color: #fff;border-color: #d26911 #e5e5e5 ">


                                        <div class="form-group" style="margin-bottom:10px;position:inherit"
                                             id="searchBox">
                                            <div class="col-md-2">
                                                <select class="form-control" id="locName" name="locName"
                                                        style="width:100%">
                                                    <option value="">请选择设备位置</option>
                                                    <template v-for="option in locs">
                                                        <option :value="option.locName">
                                                            {{option.locName }}
                                                        </option>
                                                    </template>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                                <input class="form-control" id="eqName" type="text" name="eqName"
                                                       placeholder="耗材名称"/>
                                            </div>
                                            <div class="col-md-2">
                                                <select class="form-control" id="ecType" name="ecType">
                                                    <option value="">选择分类</option>
                                                    <option value="易耗品">易耗品</option>
                                                    <option value="物资">物资</option>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                                <button id="searchBtn" class="btn btn-default" onclick="search()">查询
                                                </button>
                                            </div>
                                        </div>
                                        <div class="container-fluid">

                                            <table id="matCostDataTable"
                                                   class="table table-striped table-bordered table-hover"
                                                   data-toggle="bootgrid" data-ajax="true" data-url="/matCost/data">
                                                <thead>
                                                <tr>
                                                    <th data-column-id="id" data-width="5%">序号</th>
                                                    <th data-column-id="id" data-type="numeric"
                                                        data-identifier="true"
                                                        data-visible="false" data-width="5%">ID
                                                    </th>
                                                    <th data-align="center" data-column-id="applyDate" width="10%">
                                                        采购日期
                                                    </th>
                                                    <th data-align="center" data-column-id="locName" width="20%">
                                                        位置
                                                    </th>
                                                    <th data-align="center" data-column-id="ecName" width="10%">
                                                        名称
                                                    </th>
                                                    <th data-align="center" data-column-id="amount" width="10%">
                                                        数量
                                                    </th>
                                                    <th data-align="center" data-column-id="ecType" width="10%">
                                                        分类
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
<script src="/js/app/matCost/matCost.js"></script>