<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="tabbable">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#tab_1_1" data-toggle="tab"><i
                class="fa fa-home"></i>分类信息</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane fade in active" id="tab_1_1">
            <div class="divide-10"></div>
            <div id="detailForm"></div>
            <div class="box-body">
                <%@include file="form.jsp" %>
            </div>
        </div>
    </div>
</div>