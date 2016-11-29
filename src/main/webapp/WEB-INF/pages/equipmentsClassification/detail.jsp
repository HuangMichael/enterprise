<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="box border blue">
                <div class="box-title">
                    <h4><i class="fa fa-table"></i>设备维护外委单位信息</h4>
                </div>
            </div>
            <div class="box-body">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm myNavBtn active"
                            onclick="addMoreUnit()">
                        <i class="glyphicon glyphicon-plus"></i>添加单位
                    </button>
                    <button type="button" class="btn btn-sm myNavBtn active" onclick="removeUnit()">
                        <i class="glyphicon glyphicon-remove"></i>删除单位
                    </button>
                </div>
                <%@include file="table_1_2.jsp" %>
            </div>
        </div>
    </div>
</div>
