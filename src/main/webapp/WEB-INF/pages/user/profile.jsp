<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <form class="form-horizontal" role="form" id="changePwdForm" method="post">
   <div class="form-group">
    <div class="col-md-12">
     <div class="col-md-4">
      <img src="/img/avatars/avatar3.jpg" width="160px" height="160px" class="img-rounded img-responsive img-thumbnail" />
     </div>
     <div class="col-md-8">
      <div class="form-group">
       <label class="col-md-3 control-label">用户名</label>
       <div class="col-md-9">
        <input type="text" class=" form-control" id="userName" value="${user.userName}" readonly="readonly" />
       </div>
      </div>
      <div class="form-group">
       <label class="col-md-3 control-label">原密码</label>
       <div class="col-md-9">
        <input type="password" class=" form-control" name="oldPwd" id="oldPwd"/>
       </div>
      </div>
      <div id="newDiv" style="display:none">
      <div class="form-group">
       <label class="col-md-3 control-label">新密码</label>
       <div class="col-md-9">
        <input type="password" class=" form-control" name="newPwd" id="newPwd"/>
       </div>
      </div>
      <div class="form-group">
       <label class="col-md-3 control-label">确认密码</label>
       <div class="col-md-9">
        <input type="password" class=" form-control" name="confirmPwd" />
       </div>
      </div>
      </div>
     </div>
    </div>
   </div>
   <div class="modal-footer">
              <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary" data-dismiss="modal">取消</button>
           <button type="submit" id="changePwdBtn" name="changePwdBtn" class="btn btn-primary btn-danger">确定
           </button>
       </div>
  </form>


  <script type="text/javascript">
 $(function() {
     var validateOptions = {
         message: '该值无效 ',
         fields: {
             "oldPwd": {
                 message: '原密码不能为空',
                 validators: {
                     notEmpty: {
                         message: '原密码不能为空!'
                     }
                 }
             },
             "newPwd": {
                 message: '新密码不能为空',
                 validators: {
                     notEmpty: {
                         message: '新密码不能为空!'
                     },
                     stringLength: {
                         min: 6,
                         max: 20,
                         message: '密码长度为6到20个字符'
                     },
                     identical: {
                         field: 'confirmPwd',
                         message: '新密码和确认密码不相同！'
                     }
                 }
             },
             "confirmPwd": {
                 message: '确认密码不能为空',
                 validators: {
                     notEmpty: {
                         message: '确认密码不能为空!'
                     },
                     stringLength: {
                         min: 6,
                         max: 20,
                         message: '密码长度为6到20个字符'
                     },
                     identical: {
                         field: 'newPwd',
                         message: '新密码和确认密码不相同！'
                     }
                 }
             }
         }
     };

     $('#changePwdForm').bootstrapValidator(validateOptions).on('success.form.bv',
     function(e) {
         e.preventDefault();
         changePwd();

     });

     $("#oldPwd").on("change",
     function(data) {
         var userName = $("#userName").val();
         var oldPwd = $("#oldPwd").val();
         var data = {
             userName: userName,
             oldPwd: oldPwd
         };

         var url = "user/checkPwd";
         $.post(url, data,
         function(data) {
             if (data.result) {
                 $("#newDiv").show();
                 showMessageBox("info", data.resultDesc);
             } else {
              $("#newDiv").hide();
                 showMessageBox("danger", data.resultDesc);
                  $("#submitBtn").attr("disabled","disabled");

             }

         })
     })
 });

 function changePwd() {
     var userName = $("#userName").val();
     var newPwd = $("#newPwd").val();

     if(!newPwd){
     return;
     }
     var url = "user/changePwd";
     var data = {
         userName: userName,
         newPwd: newPwd
     }
     $.post(url, data,
     function(data) {
         if (data.result) {
             $("#user_modal").modal("hide");
             showMessageBox("info", data.resultDesc + "，请重新登录!");
             setTimeout("window.location='/'", 3000);

         } else {
             showMessageBox("danger", data.resultDesc);
         }
     });
 }
  </script>