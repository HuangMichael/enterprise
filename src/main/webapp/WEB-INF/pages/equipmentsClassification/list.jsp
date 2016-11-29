<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <h4><i class="fa fa-sitemap"></i>设备分类信息</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <!-- BOX -->
                    <div class="box border blue">
                        <div class="box-body treeContainer"  id="treeDiv">
                            <ul id="tree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="box border blue">
                         <%@include file="../common/common-menubar.jsp"%>
                    </div>
                    <div class="divide-2"></div>
                    <!-- BOX -->
                    <div class="box border blue">
                        <div class="box-body">
                            <div id="contentDiv">
                                <%@include file="detail.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../common/common-back2top.jsp" %>
        </div>
    </div>
    <!-- /CONTENT-->
</div>

<!-- SAMPLE BOX CONFIGURATION MODAL FORM-->
<div class="modal fade" id="unitListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">请选择外委单位</h4>
            </div>
            <div class="modal-body" id="mBody">
                <%--<%@include file="unitList.jsp" %>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">取消
                </button>
                <button type="button" id="confitmBtn" name="confitmBtn"
                        class="btn btn-danger">确定
                </button>
            </div>
        </div>
    </div>
</div>
<script src="js/app/equipmentsClassification/equipmentsClassification.js"></script>
