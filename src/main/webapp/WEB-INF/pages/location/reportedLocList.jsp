<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="locList"></div>
<div style="text-align: right">
    <button type="button" class="btn btn-default" data-dismiss="modal">取消报修
    </button>
    <button type="button" id="locReport" name="locReport" onclick="continueLocReport()" class="btn btn-danger">继续报修
    </button>
</div>