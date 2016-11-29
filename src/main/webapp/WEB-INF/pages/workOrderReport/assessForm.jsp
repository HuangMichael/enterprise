<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class="form-horizontal " id="assessForm" method="post">
    <div class="form-group">
        <label class="col-md-4 control-label">
            请评分:
        </label>
        <div class="col-md-4">
            <select class="form-control" name="assessLevel" id="assessLevel">
                <option value="5">☆☆☆☆☆</option>
                <option value="4">☆☆☆☆</option>
                <option value="3">☆☆☆</option>
                <option value="2">☆☆</option>
                <option value="1">☆</option>
            </select>
        </div>
        <div class="col-md-4">
            <button type="button" id="assessWorkOrder" name="assessWorkOrder"
                    class="btn btn-default">评价
            </button>
        </div>
    </div>
</form>



