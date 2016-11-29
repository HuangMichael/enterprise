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
                            <h4><i class="fa fa-users"></i>线路信息</h4>
                        </div>

                        <%@include file="../common/common-menubar.jsp" %>

                        <div class="box-body">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active">
                                        <a href="#tab_1_0" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold">
                                            <i class="fa fa-home" id="eq"></i>线路信息</a>
                                    </li>
                                    <li>
                                        <a href="#tab_1_1" data-toggle="tab"
                                           style="font-family: 微软雅黑;font-weight: bold">
                                            <i class="fa fa-flag" id="eqDetail"></i>线路详细信息</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="tab_1_0">
                                        <div class="form-group" style="margin-bottom:10px;position:inherit"
                                             id="searchBox">
                                            <div class="col-md-2">
                                                <input class="form-control" id="lineNo" type="text" name="lineNo"
                                                       placeholder="线路编号"/>
                                            </div>
                                            <div class="col-md-2">
                                                <input class="form-control" id="description" type="text"
                                                       name="description"
                                                       placeholder="线路名称"/>
                                            </div>

                                            <div class="col-md-2">
                                                <button id="searchBtn" class="btn btn-default" onclick="search()">查询
                                                </button>
                                            </div>
                                        </div>
                                        <%@include file="lineList.jsp" %>
                                    </div>
                                    <div class="tab-pane fade" id="tab_1_1" style="padding: 20px">
                                        <%@include file="detail.jsp" %>
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

<!-- SAMPLE BOX CONFIGURATION MODAL FORM-->
<div class="modal fade" id="userListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">请选择要添加的用户</h4>
            </div>
            <div class="modal-body" id="mBody">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" id="confirmBtn" name="confirmBtn" onclick="confirmAddUsers()"
                        class="btn btn-danger">确定
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/app/line/line.js"></script>