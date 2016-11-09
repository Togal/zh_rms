<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">角色-权限分配</div>
			<div class="panel-body">
				<form id="roles-privileges-form" action="/rms/role/rolePrivilege/manSubmit" method="POST" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
						    	<span class="font_exp">*</span><label for="roles-privileges">角色</label>
						    	<select id="roles-privileges" name="roleId" class="form-control">
						    		<option value="-1">请选择角色</option>
						    		<c:forEach items="${roles }" var="role">
										<option value="${role.id }">${role.rolename }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
						    	<button type="submit" class="btn btn-primary">submit</button>
							</div>
						</div>
						<div class="col-md-9">
							<div class="form-group">
								<label>权限</label>
								<ul id="privilegeTree">
									<%@ include file="/jsp/public/privilege_ckbox.jsp"%>
								</ul>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#privilegeTree').treeview();
		$(function(){
			// 指定事件处理函数
			$("[name=privilegeIds]").click(function(){
				$(this).siblings("ul").find("input").prop("checked", this.checked);
				if(this.checked == true){
					$(this).parents("li").children("input").prop("checked", true);
				}
			});
		});
	</script>
</body>
<%@ include file="footer.jsp"%>