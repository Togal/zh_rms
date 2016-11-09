<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">菜单<c:choose><c:when test="${type == 'add'}">新增</c:when><c:otherwise>编辑</c:otherwise></c:choose>页面</div>
			<div class="panel-body">
				<form id="menu-form" action="/rms/menu/saveSubmit/<c:choose><c:when test="${type == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>" method="POST" enctype="multipart/form-data" onsubmit="return checkForm(this)">
					<div class="form-group">
				    	<span class="font_exp">*</span><label for=""menuname"">菜单名称</label>
				    	<input type="text" name="menu.menuname" value="${menu.menuname }" class="form-control" id="menuname" placeholder="菜单名称">
					</div>
					<div class="form-group">
						<label for="menuicon">菜单图标</label>
	    				<input type="file" id="menuicon">
					</div>
					<div class="form-group">
				    	<label>菜单类型</label>
				    	<div>
					    	<label class="radio-inline">
							  <input type="radio" name="menu.menutype" id="menutype-master" value="0" <c:if test="${menu.menutype == 0 }">checked</c:if>> 主（Master）菜单
							</label>
							<label class="radio-inline">
							  <input type="radio" name="menu.menutype" id="menutype-slave" value="1" <c:if test="${menu.menutype == 1 }">checked</c:if>> 从（Slave）菜单
							</label>
						</div>
					</div>
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="mastermenus">主（Master）菜单</label>
				    	<select id="mastermenus" name="menu.mastermenuid" class="form-control">
				    		<option value="-1">请选择主（Master）菜单</option>
				    		<c:forEach items="${masterMenus }" var="masterMenu">
								<option value="${masterMenu.id }" <c:if test="${masterMenu.id == menu.mastermenuid}">selected</c:if>>${masterMenu.menuname }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
				    	<label for="parentmenus">父级菜单</label>
				    	<select id="parentmenus" name="menu.parentid" class="form-control">
				    		<option value="-1">请选择父级菜单</option>
				    		<%@ include file="/jsp/public/menu_select.jsp"%>
				    		<%-- <c:forEach items="${parentMenus }" var="parentMenu">
								<option value="${parentMenu.id }" <c:if test="${parentMenu.id == menu.parentid}">selected</c:if>>${parentMenu.menuname }</option>
							</c:forEach> --%>
						</select>
					</div>
					<div class="form-group">
				    	<span class="font_exp">*</span><label for="menusort">排序</label>
				    	<input type="text" name="menu.sort" value="${menu.sort }" class="form-control" id="menusort" placeholder="排序">
					</div>
					<div class="form-group">
				    	<label for="menustatus">状态</label>
				    	<div>
					    	<label class="radio-inline">
							  <input type="radio" name="menu.status" <c:if test="${menu.status == 1 }">checked</c:if> id="menustatus-on" value="1"> 启用
							</label>
							<label class="radio-inline">
							  <input type="radio" name="menu.status" <c:if test="${menu.status == 0 }">checked</c:if> id="menustatus-off" value="0"> 禁用
							</label>
						</div>
					</div>
					<div class="form-group">
				    	<label for="menusaddtime">添加时间</label>
						<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:ii:ss">
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</span>
							<!-- <span class="input-group-addon">
								<span class="glyphicon glyphicon-remove"></span>
							</span> -->
							<input class="form-control" name="addtime" size="16" type="text" value="${menu.addTimeToDate}" readonly>
						</div>
					</div>
					<input type="hidden" name="menu.id" value="${menu.id }">
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