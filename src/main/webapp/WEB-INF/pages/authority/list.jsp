<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                            <h4><i class="fa fa-sitemap"></i>应用授权</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <div class="box border blue">
                        <div class="row">
                            <div class="col-md-8">
                                <form:select path="roleList" class="form-control" id="role_id" onchange="loadAuthView()">
                                    <form:options itemLabel="roleDesc" items="${roleList}"
                                                  itemValue="id"></form:options>
                                </form:select>
                            </div>
                            <div class="col-md-4"><a class="btn  btn-sm btn-danger" onclick="grant()">授权</a></div>
                        </div>
                        <div class="box-body treeContainer" id="treeDiv">
                            <ul id="tree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="box border blue">
                        <div class="box-body">
                            <%@include file="../resource/authList.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../common/common-back2top.jsp" %>
        </div>
    </div>
    <!-- /CONTENT-->
</div>
<script src="js/app/authority/authority.js"></script>
