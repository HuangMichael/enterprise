<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/common-head.jsp" %>
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
                            <h4><i class="fa fa-users"></i>信息查询测试</h4>
                        </div>
                        <%@include file="../common/common-menubar.jsp" %>
                        <div class="box-body">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active"><a href="#tab_1_0" data-toggle="tab"
                                                          style="font-family: 微软雅黑;font-weight: bold">
                                        <i class="fa fa-home" id="eq"></i>信息查询测试</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="form-group" style="margin-bottom:10px;position:inherit" id="searchBox">
                                        <div class="col-md-2">
                                            <input class="form-control" id="roleName" type="text" name="roleName"
                                                   placeholder="角色名"/>
                                        </div>
                                        <div class="col-md-2">
                                            <input class="form-control" id="roleDesc" type="text" name="roleDesc"
                                                   placeholder="角色描述"/>
                                        </div>

                                        <div class="col-md-2">
                                            <button id="searchBtn" class="btn btn-default" onclick="search()">查询
                                            </button>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade in active" id="tab_1_0">
                                        <%@include file="roleList.jsp" %>
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
<%@include file="../common/common-foot.jsp" %>
<script type="text/javascript" src="/js/app/search/search.js"></script>
