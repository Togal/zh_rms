<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">权限<c:choose><c:when test="${type == 'add'}">新增</c:when><c:otherwise>编辑</c:otherwise></c:choose>页面</div>
			<div class="panel-body">
				<form id="privilege-form" action="/rms/privilege/saveSubmit/<c:choose><c:when test="${type == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>" method="POST" enctype="multipart/form-data" onsubmit="return checkForm(this)">
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="privilegename">权限名称</label>
				    	<input type="text" name="privilege.privilegename" value="${privilege.privilegename }" class="form-control" id="privilegename" placeholder="权限名称">
					</div>
					<div class="form-group">
				    	<label for="privilegeurl">URL</label>
				    	<input type="text" name="privilege.privilegeurl" value="${privilege.privilegeurl }" class="form-control" id="privilegeurl" placeholder="URL">
					</div>
					<div class="form-group">
				    	<label for="parentprivileges">父级权限</label>
				    	<select id="parentprivileges" name="privilege.parentid" class="form-control">
				    		<option value="-1">请选择父级权限</option>
				    		<%@ include file="/jsp/public/privilege_select.jsp"%>
				    		<%-- <c:forEach items="${parentPrivileges }" var="parentPrivilege">
				    			<c:if test="${privilege.id != parentPrivilege.id }">
									<option value="${parentPrivilege.id }" <c:if test="${parentPrivilege.id == privilege.parentid}">selected</c:if>>${parentPrivilege.privilegename }</option>
								</c:if>
							</c:forEach> --%>
						</select>
					</div>
					<div class="form-group">
				    	<label for="menus">绑定菜单</label>
				    	<select id="menus" name="privilege.menuid" class="form-control">
				    		<option value="-1">请选择绑定菜单</option>
				    		<%@ include file="/jsp/public/menu_select.jsp"%>
				    		<%-- <c:forEach items="${menus }" var="menu">
								<option value="${menu.id }" <c:if test="${menu.id == privilege.menuid}">selected</c:if>>${menu.menuname }</option>
							</c:forEach> --%>
						</select>
					</div>
					<div class="form-group">
				    	<label for="privilegeButtons">绑定按钮</label>
				    	<select id="privilegeButtons" name="privilegeButtonId" class="form-control">
				    		<option value="-1">请选择绑定按钮</option>
				    		<c:forEach items="${privilegeButtons }" var="privilegeButton">
								<option value="${privilegeButton.id }" <c:if test="${privilegeButton.privilegeid == privilege.id && !empty privilege.id}">selected</c:if>>${privilegeButton.nameStr }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
				    	<label for="privilegeaddtime">添加时间</label>
						<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:ii:ss">
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</span>
							<!-- <span class="input-group-addon">
								<span class="glyphicon glyphicon-remove"></span>
							</span> -->
							<input class="form-control" name="addtime" size="16" type="text" value="${privilege.addTimeToDate}" readonly>
						</div>
					</div>
					<input type="hidden" name="privilege.id" value="${privilege.id }">
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