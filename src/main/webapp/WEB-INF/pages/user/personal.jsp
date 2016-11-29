<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../common/common-head.jsp" %>

<div class="container">
    <div class="row">
        <div id="content" class="col-lg-12">
            <!-- PAGE HEADER-->
            <%@include file="../common/common-breadcrumb.jsp" %>
            <div class="row">
                <div class="col-md-12">
                    <!-- BOX -->
                    <div class="box border blue" id="formWizard">
                        <div class="box-title">
                            <h4><i class="fa fa-bars"></i>个人信息<span class="stepHeader">第1步 共3步</span></h4>
                        </div>
                        <div class="box-body form">
                            <form id="DisPatchFormWizard" action="#" class="form-horizontal">
                                <div class="wizard-form">
                                    <div class="wizard-content">
                                        <ul class="nav nav-pills nav-justified steps">
                                            <li>
                                                <a href="#account" data-toggle="tab" class="wiz-step">
                                                    <span class="step-number">1</span>
                                                            <span class="step-name"><i
                                                                    class="fa fa-check"></i>用户账户信息</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#payment" data-toggle="tab" class="wiz-step active">
                                                    <span class="step-number">2</span>
                                                            <span class="step-name"><i
                                                                    class="fa fa-check"></i>用户人员信息</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#confirm" data-toggle="tab" class="wiz-step">
                                                    <span class="step-number">3</span>
                                                            <span class="step-name"><i
                                                                    class="fa fa-check"></i>保存个人信息</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="bar" class="progress progress-striped progress-sm active"
                                             role="progressbar">
                                            <div class="progress-bar progress-bar-warning"></div>
                                        </div>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="account">


	<div class="form-group">
          		  <label class="col-md-2" for="userName">用户名</label>
          		<div class="col-md-4">
          		  <input type="text" class="form-control" id="userName" name="userName" v-model="user.userName">
          		</div>
          		  <label class="col-md-2" for="userName">原密码</label>
                  <div class="col-md-4">
                  		 <input type="text" class="form-control" id="userName" name="userName" v-model="user.userName">
                  </div>
          	</div>
	<div class="form-group">
          		  <label class="col-md-2" for="userName">新密码</label>
          		<div class="col-md-4">
          		  <input type="text" class="form-control" id="userName" name="userName" v-model="user.userName">
          		</div>
          		  <label class="col-md-2" for="userName">确认密码</label>
                  <div class="col-md-4">
                  		 <input type="text" class="form-control" id="userName" name="userName" v-model="user.userName">
                  </div>
          	</div>
                                            </div>
                                            <div class="tab-pane" id="payment">


                                            </div>
                                            <div class="tab-pane" id="confirm">


                                            </div>
                                        </div>
                                    </div>
                                    <div class="wizard-buttons">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <a href="javascript:;" class="btn btn-default prevBtn">
                                                        <i class="fa fa-arrow-circle-left"></i> 上一步
                                                    </a>
                                                    <a href="javascript:;" class="btn btn-primary nextBtn">
                                                        下一步 <i class="fa fa-arrow-circle-right"></i>
                                                    </a>
                                                    <a href="javascript:;" class="btn btn-danger submitBtn">
                                                        确认提交 <i class="fa fa-arrow-circle-right"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../common/common-back2top.jsp" %>
        </div>
        <!-- /CONTENT-->
    </div>
</div>
<script type="text/javascript" src="/js/app/user/user-form-wizard.min.js"></script>
<script type="text/javascript" src="/js/app/user/personal.js"></script>

