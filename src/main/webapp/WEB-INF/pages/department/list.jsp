<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="js/zTree_v3-master/css/metroStyle/metroStyle.css" type="text/css">
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
                            <h4><i class="fa fa-sitemap"></i>部门信息</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <!-- BOX -->
                    <div class="box border blue">
                        <div class="box-body">
                            <%-- <div id="treeDemo" class="ztree"></div>--%>
                            <ul id="tree" class="ztree" style="width:560px; overflow:auto;"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">

                    <div class="box border blue">
                        <div class="box-body">
                            <a type="button" class="btn  btn-default btn-mini" id="createBtn"
                               onclick="loadCreateForm()">新建记录</a>
                            <a type="button" class="btn  btn-mini btn-default" id="saveBtn" onclick="save()">保存记录</a>
                            <a type="button" class="btn  btn-mini btn-default" id="deleteBtn" onclick="deleteObject()">删除记录</a>
                            <a href="#selectUsers-modal" data-toggle="modal" type="button"
                               class="btn  btn-mini btn-default" id="addPersonBatch" data-backdrop="static">批量添加人员</a>
                        </div>
                    </div>
                    <div class="divide-2"></div>
                    <!-- BOX -->
                    <div class="box border blue">
                        <div class="box-body">
                            <div id="contentDiv">
                                <%@include file="form.jsp" %>
                                <div class="divide-10"></div>
                                <div class="box border blue">
                                    <div class="box-title">
                                        <h4><i class="fa fa-table"></i>部门人员信息</h4>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <table id="datatable1" cellpadding="0"
                                           cellspacing="0" border="0"
                                           class="datatable table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="center" width="10%"> 序号</th>
                                            <th class="center" width="10%"> 人员工号</th>
                                            <th class="center" width="10%"> 姓名</th>
                                            <th class="center" width="10%"> 部门</th>
                                            <th class="center" width="10%"> 电话</th>
                                            <th class="center" width="10%"> 编辑</th>
                                            <th class="center" width="10%"> 删除</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${department.personList}" var="person" varStatus="s">
                                            <tr class="gradeX">
                                                <td>${s.index+1}</td>
                                                <td class="center">
                                                        ${person.personNo}
                                                </td>
                                                <td class="center">
                                                        ${person.personName}
                                                </td>
                                                <td class="center">${person.department.description}</td>
                                                <td class="center">${person.telephone}</td>
                                                <td class="center"><a href="#">编辑</a></td>
                                                <td class="center"><a href="#">删除</a></td>
                                            </tr>
                                        </c:forEach>

                                        <c:if test="${department.personList.size() ==0}">
                                            <tr class="gradeX">
                                                <td colspan="7" class="center">当前没有数据</td>
                                            </tr>
                                        </c:if>
                                        </tbody>
                                        <tfoot>
                                        </tfoot>
                                    </table>
                                </div>
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
<%@include file="../common/common-foot.jsp" %>
<script type="text/javascript" src="js/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/js/app/common/common-utils.js"></script>
<script type="text/javascript" src="js/app/common/common-show-message.js"></script>
<script src="js/app/department/department.js"></script>