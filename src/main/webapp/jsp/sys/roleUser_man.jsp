<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">角色-用户分配</div>
			<div class="panel-body">
				<form id="roles-users-form" action="/rms/role/roleUser/manSubmit" method="POST" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
						    	<span class="font_exp">*</span><label for="roles-users">角色</label>
						    	<select id="roles-users" name="roleId" class="form-control">
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
								<label>用户</label>
								<div id="users-ckbox" class="checkbox">
									<c:forEach items="${users }" var="user">
										<label>
									      <input type="checkbox" name="userIds" value="${user.id }">${user.useraccount}
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