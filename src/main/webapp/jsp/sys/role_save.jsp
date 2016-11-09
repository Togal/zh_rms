<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">角色<c:choose><c:when test="${type == 'add'}">新增</c:when><c:otherwise>编辑</c:otherwise></c:choose>页面</div>
			<div class="panel-body">
				<form id="role-form" action="/rms/role/saveSubmit/<c:choose><c:when test="${type == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>" method="POST" enctype="multipart/form-data" onsubmit="return checkForm(this)">
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="rolename">角色名称</label>
				    	<input type="text" name="role.rolename" value="${role.rolename }" class="form-control" id="rolename" placeholder="角色名称">
					</div>
					<div class="form-group">
				    	<label for="rolestatus">状态</label>
				    	<div>
					    	<label class="radio-inline">
							  <input type="radio" name="role.status" <c:if test="${role.status == 1 }">checked</c:if> id="rolestatus-on" value="1"> 启用
							</label>
							<label class="radio-inline">
							  <input type="radio" name="role.status" <c:if test="${role.status == 0 }">checked</c:if> id="rolestatus-off" value="0"> 禁用
							</label>
						</div>
					</div>
					<div class="form-group">
				    	<label for="roleaddtime">添加时间</label>
						<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:ii:ss">
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</span>
							<!-- <span class="input-group-addon">
								<span class="glyphicon glyphicon-remove"></span>
							</span> -->
							<input class="form-control" name="addtime" size="16" type="text" value="${role.addTimeToDate}" readonly>
						</div>
					</div>
					<input type="hidden" name="role.id" value="${role.id }">
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    todayBtn:  1,
	autoclose: 1
	//pickerPosition:'bottom-left'
});
</script>    
<%@ include file="footer.jsp" %>