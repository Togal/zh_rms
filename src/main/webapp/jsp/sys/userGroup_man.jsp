<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<body>
	<div style="padding:20px;">
		<div class="panel panel-default">
			<div class="panel-heading">用户-用户组分配</div>
			<div class="panel-body">
				<form id="users-groups-form" action="/rms/user/userGroup/manSubmit" method="POST" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
						    	<span class="font_exp">*</span><label for="users-groups">用户</label>
						    	<select id="users-groups" name="userId" class="form-control">
						    		<option value="-1">请选择用户</option>
						    		<c:forEach items="${users }" var="user">
										<option value="${user.id }">${user.useraccount }</option>
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