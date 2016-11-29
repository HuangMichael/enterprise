<div class="box-body" style="padding: 5px 20px 5px 5px">
	<div class="btn-group">
		<c:forEach var="m" items="${appMenus}" varStatus ="mi">
			<button type="button" class="btn btn-sm myNavBtn active" ${m.resourceUrl}>
                                    <i class="${m.iconClass}"></i>${m.resourceName}
                                </button>
		</c:forEach>
	</div>
</div>