<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="recordInfo">
    <div class="col-md-12">
        <!-- BOX -->
        <div class="box border blue">
            <div class="box-body" style="padding: 20px">
                <form class="form-horizontal" role="form" id="recordForm">
                    <div class="form-group">
                        <label class="col-md-1 control-label" for="eqCode">设备编号</label>

                        <div class="col-md-3">
                            <input class="form-control" id="eqCode" type="text" name="eqCode" v-model="e.eqCode"
                                   readonly/>
                        </div>
                        <label class="col-md-1 control-label" for="eqCode">设备名称</label>

                        <div class="col-md-3">
                            <input class="form-control" id="description" type="text" name="description"
                                   v-model="e.description" readonly/>
                        </div>
                        <label class="col-md-1 control-label" for="eqCode">设备位置</label>

                        <div class="col-md-3">
                            <input class="form-control" id="location" type="text" name="location"
                                   v-model="e.locations.description" readonly/>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<table id="updateRecords" class=" table table-striped table-bordered table-hover table-responsive">
    <thead>
    <tr>
        <th width="5%" style="overflow: hidden">序号</th>
        <th width="10%" style="overflow: hidden">类型</th>
        <th width="10%" style="overflow: hidden">申请人</th>
        <th width="10%" style="overflow: hidden">申请时间</th>
        <th width="10%" style="overflow: hidden">申请部门</th>
        <th width="20%" style="overflow: hidden">申请目的</th>
        <th width="10%" style="overflow: hidden">经办人</th>
        <th width="10%" style="overflow: hidden">批准人</th>
    </tr>
    </thead>
    <tbody id="record" v-for="uh in records">
    <tr>
        <td style="overflow: hidden">{{$index+1}}</td>
        <td style="overflow: hidden">{{uh.dataType}}</td>
        <td style="overflow: hidden">{{uh.applicant}}</td>
        <td style="overflow: hidden">{{uh.applyDate}}</td>
        <td style="overflow: hidden">{{uh.applyDep}}</td>
        <td style="overflow: hidden">{{uh.purpose}}</td>
        <td style="overflow: hidden">{{uh.handler}}</td>
        <td style="overflow: hidden">{{uh.approver}}</td>
    </tr>

    </tbody>
</table>
</div>

