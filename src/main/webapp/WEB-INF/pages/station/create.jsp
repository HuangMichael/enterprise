<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal myform" role="form" id="createForm">
	<%@include file="form.jsp"%>
</form>

<script>
	$(function() {
		$("select").select2({
			theme: "bootstrap"
		});
	});
</script>