<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">权限-角色分配</div>
			<div class="panel-body">
				<form id="privileges-roles-form" action="/rms/privilege/privilegeRole/manSubmit" method="POST" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
						    	<span class="font_exp">*</span><label for="privileges-roles">权限</label>
						    	<select id="privileges-roles" name="privilegeId" class="form-control">
						    		<option value="-1">请选择权限</option>
						    		<%@ include file="/jsp/public/privilege_select.jsp"%>
								</select>
							</div>
							<div class="form-group">
						    	<button type="submit" class="btn btn-primary">submit</button>
							</div>
						</div>
						<div class="col-md-9">
							<div class="form-group">
								<label>角色</label>
								<div id="roles-ckbox" class="checkbox">
									<c:forEach items="${roles }" var="role">
										<label>
									      <input type="checkbox" name="roleIds" value="${role.id }">${role.rolename}
									    </label>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<%@ include file="footer.jsp"%>