<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal" role="form" id="form">
    <fieldset class="form-group" id="a">
        <legend>基本信息</legend>
        <div class="form-group">
            <label class="col-md-1 control-label" for="eqCode">设备编号</label>
            <div class="col-md-3">
                <input class="form-control" id="eqCode" type="text" name="eqCode" value="${equipments.eqCode}"/>
                <input class="form-control" id="id" type="hidden" name="id" value="${equipments.id}"/>
            </div>
            <label for="description" class="col-md-1 control-label">设备名称</label>

            <div class="col-md-3">
                <input class="form-control" id="description" type="text" name="description"
                       value="${equipments.description}" required/>
            </div>

            <label for="locations_id" class="col-md-1 control-label">设备位置</label>

            <div class="col-md-3">
                <select class="form-control" id="locations_id" name="locations.id" required>
                    <option value=""></option>
                    <c:forEach items="${locationsList}" var="l">
                        <option value="${l.id}">${l.line.description}--${l.station.description}--${l.description}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-1 control-label" for="maintainer">维护单位</label>

            <div class="col-md-3">
                <select class="form-control" id="unit_id" name="unit.id" required>
                    <option value=""></option>
                    <c:forEach items="${outsourcingUnitList}" var="u">
                        <option value="${u.id}">${u.description}</option>
                    </c:forEach>
                </select>
            </div>
            <label class="col-md-1 control-label" for="maintainer">维护人员</label>

            <div class="col-md-3">
                <input class="form-control" id="maintainer" type="text" name="maintainer"
                       value="${equipments.maintainer}"/>
            </div>

            <label class="col-md-1 control-label" for="originalValue">设备型号</label>

            <div class="col-md-3">
                <input class="form-control" id="eqModel" type="text" name="eqModel"
                       value="${equipments.eqModel}"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-1 control-label" for="netValue">管理等级</label>

            <div class="col-md-3">
                <select class="form-control" id="manageLevel" name="manageLevel" required>
                    <option value=""></option>
                    <option value="1">一级设备</option>
                    <option value="2">二级设备</option>
                    <option value="3">三级设备</option>
                    <option value="4">四级设备</option>
                </select>
            </div>
            <label for="equipmentsClassification_id" class="col-md-1 control-label">设备类型</label>

            <div class="col-md-3">
                <select class="form-control" id="equipmentsClassification_id" name="equipmentsClassification.id"
                        required>
                    <option value=""></option>
                    <c:forEach items="${equipmentsClassificationList}" var="type">
                        <option value="${type.id}">${type.description}</option>
                    </c:forEach>
                </select>
            </div>

            <label for="status" class="col-md-1 control-label">设备状态</label>

            <div class="col-md-3">
                <select class="form-control" id="status" name="status" required>
                    <option value="1" selected>启用</option>
                    <option value="0">禁用</option>
                </select>
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-1 control-label" for="assetNo">固定资产编号</label>

            <div class="col-md-3">
                <input class="form-control" id="assetNo" type="text" name="assetNo" value="${equipments.assetNo}"/>
            </div>
            <label class="col-md-1 control-label" for="isRunning">是否运行</label>

            <div class="col-md-3">
                <select class="form-control" id="isRunning" name="isRunning" required>
                    <option value="true" selected>是</option>
                    <option value="false">否</option>
                </select>
            </div>

            <label class="col-md-1 control-label" for="productFactory">生产厂家</label>

            <div class="col-md-3">
                <input class="form-control" id="productFactory" type="text" name="productFactory"
                       value="${equipments.productFactory}"/>
            </div>

        </div>

        <div class="form-group">
            <label class="col-md-1 control-label" for="productDate">出厂日期</label>

            <div class="col-md-3">
                <input class="form-control" id="productDate" type="text" name="productDate" value="${equipments.productDate}"/>
            </div>

            <label class="col-md-1 control-label" for="originalValue">原值(元)</label>

            <div class="col-md-3">
                <input class="form-control" id="originalValue" type="text" name="originalValue"
                       value="${equipments.originalValue}"/>
            </div>
            <label class="col-md-1 control-label" for="netValue">净值(元)</label>

            <div class="col-md-3">
                <input class="form-control" id="netValue" type="text" name="netValue" value="${equipments.netValue}"/>
            </div>
        </div>

    </fieldset>

    <fieldset class="form-group">

        <legend>其他信息</legend>

        <div class="form-group">
            <label class="col-md-1 control-label" for="purchasePrice">采购价格</label>
            <div class="col-md-3">
                <input class="form-control" id="purchasePrice" type="text" name="purchasePrice" value="${equipments.purchasePrice}"/>
            </div>
            <label class="col-md-1 control-label" for="warrantyPeriod">保修期至</label>
            <div class="col-md-3">
                <input class="form-control" id="warrantyPeriod" type="text" name="warrantyPeriod" value="${equipments.warrantyPeriod}"/>
            </div>
            <label class="col-md-1 control-label" for="setupDate">安装日期</label>
            <div class="col-md-3">
                <input class="form-control" id="setupDate" type="text" name="setupDate" value="${equipments.setupDate}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-1 control-label" for="runDate">运行日期</label>
            <div class="col-md-3">
                <input class="form-control" id="runDate" type="text" name="runDate" value="${equipments.runDate}"/>
            </div>
            <label class="col-md-1 control-label" for="expectedYear">预计年限</label>
            <div class="col-md-3">
                <input class="form-control" id="expectedYear" type="text" name="expectedYear" value="${equipments.expectedYear}"/>
            </div>
            <%--      <label class="col-md-1 control-label" for="productDate">出厂日期</label>
                  <div class="col-md-3">
                      <input class="form-control" id="productDate" type="text" name="productDate" value="${equipments.productDate}"/>
                  </div>--%>
        </div>
    </fieldset>

    <div class="modal-footer">
        <button type="button" class="btn btn-default"
                data-dismiss="modal">关闭
        </button>
        <button type="button" id="saveBtn" name="saveBtn" class="btn btn-primary" onclick="save()">保存
        </button>
    </div>
</form>
