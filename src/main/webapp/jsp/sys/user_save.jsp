<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">用户<c:choose><c:when test="${type == 'add'}">新增</c:when><c:otherwise>编辑</c:otherwise></c:choose>页面</div>
			<div class="panel-body">
				<form id="user-form" action="/rms/user/saveSubmit/<c:choose><c:when test="${type == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>" method="POST" enctype="multipart/form-data" onsubmit="return checkForm(this)">
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="useraccount">用户名</label>
				    	<input type="text" name="user.useraccount" value="${user.useraccount }" class="form-control" id="useraccount" placeholder="用户名">
					</div>
					<c:choose><c:when test="${type == 'add'}">
						<div class="form-group">
					    	<span class="font_exp">*</span><label for="userpassword">密码</label>
					    	<input type="password" name="userpassword" class="form-control" id="userpassword" placeholder="密码">
						</div>
						<div class="form-group">
					    	<span class="font_exp">*</span><label for="userpasswordagain">密码确认</label>
					    	<input type="password" name="userpasswordagain" class="form-control" id="userpasswordagain" placeholder="密码确认">
						</div>
						<script type="text/javascript">
							$(function(){
								//新增用户
								$('#user-form').submit(function(){
									if($('#userpassword').val() != $('#userpasswordagain').val()){
										alert('错误提示:\r\n' + '两次输入密码不一致');
										return false;
									}
								});
							});
						</script>
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
					</c:choose>
					<div class="form-group">
				    	<label for="departments">所属部门</label>
				    	<select id="departments" name="user.departmentid" class="form-control">
				    		<option value="-1">请选择所属部门</option>
				    		<c:forEach items="${departments }" var="department">
								<option value="${department.id }" <c:if test="${department.id == user.departmentid}">selected</c:if>>${department.departmentname }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
				    	<label for="useraddtime">添加时间</label>
						<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:ii:ss">
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</span>
							<!-- <span class="input-group-addon">
								<span class="glyphicon glyphicon-remove"></span>
							</span> -->
							<input class="form-control" name="addtime" size="16" type="text" value="${user.addTimeToDate}" readonly>
						</div>
					</div>
					<input type="hidden" name="user.id" value="${user.id }">
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