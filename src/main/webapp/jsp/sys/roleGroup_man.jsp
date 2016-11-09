<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">角色-用户组分配</div>
			<div class="panel-body">
				<form id="roles-groups-form" action="/rms/role/roleGroup/manSubmit" method="POST" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
						    	<span class="font_exp">*</span><label for="roles-groups">角色</label>
						    	<select id="roles-groups" name="roleId" class="form-control">
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
								<label>用户组</label>
								<div id="groups-ckbox" class="checkbox">
									<c:forEach items="${groups }" var="group">
										<label>
									      <input type="checkbox" name="groupIds" value="${group.id }">${group.groupname}
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