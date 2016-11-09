<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">按钮级别权限管理<c:choose><c:when test="${type == 'add'}">新增</c:when><c:otherwise>编辑</c:otherwise></c:choose>页面</div>
			<div class="panel-body">
				<form id="privilegeButton-form" action="/rms/privilegeButton/saveSubmit/<c:choose><c:when test="${type == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>" method="POST" enctype="multipart/form-data" onsubmit="return checkForm(this)">
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="name">功能名称</label>
				    	<input type="text" name="privilegeButton.name" value="${privilegeButton.name }" class="form-control" id="name" placeholder="功能名称">
					</div>
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="selectDomMethods">选择DOM方式</label>
						<select id="selectDomMethods" name="privilegeButton.selectdommethod" class="form-control">
				    		<option value="-1">请选择选择DOM方式</option>
				    		<option value="0" <c:if test="${privilegeButton.selectdommethod == 0}">selected</c:if>>id</option>
				    		<option value="1" <c:if test="${privilegeButton.selectdommethod == 1}">selected</c:if>>class</option>
						</select>
					</div>
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="selectDomName">DOM元素名称</label>
				    	<input type="text" name="privilegeButton.selectdomname" value="${privilegeButton.selectdomname }" class="form-control" id="selectDomName" placeholder="DOM元素名称">
					</div>
					<div class="form-group">
				    	<label for="privilegeButtonaddtime">添加时间</label>
						<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:ii:ss">
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</span>
							<!-- <span class="input-group-addon">
								<span class="glyphicon glyphicon-remove"></span>
							</span> -->
							<input class="form-control" name="addtime" size="16" type="text" value="${privilegeButton.addTimeToDate}" readonly>
						</div>
					</div>
					<input type="hidden" name="privilegeButton.id" value="${privilegeButton.id }">
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