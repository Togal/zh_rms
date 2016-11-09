$(function(){
	//菜单管理按钮
    var oButtonInit = new menuButtonInit();
    oButtonInit.Init();
    //权限管理按钮
    var oButtonInit = new privilegeButtonInit();
    oButtonInit.Init();
    //角色管理按钮
    var oButtonInit = new roleButtonInit();
    oButtonInit.Init();
    //部门管理按钮
    var oButtonInit = new departmentButtonInit();
    oButtonInit.Init();
    //用户组别管理按钮
    var oButtonInit = new groupButtonInit();
    oButtonInit.Init();
    //用户管理按钮
    var oButtonInit = new userButtonInit();
    oButtonInit.Init();
    //按钮级别权限管理按钮
    var oButtonInit = new privilegeButtonButtonInit();
    oButtonInit.Init();
})
window.operateEvents = {
	//菜单管理按钮
    'click .menu-edit': function (e, value, row, index) {
       window.location.href = '/rms/menu/save/edit?id=' + row.id;
    },
    'click .menu-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?（如有子级数据则会级联操作）'))
    		return false;
    	window.location.href = '/rms/menu/deleteMenu/' + row.id;
    },
    //权限管理按钮
    'click .privilege-edit': function (e, value, row, index) {
    	window.location.href = '/rms/privilege/save/edit?id=' + row.id;
     },
    'click .privilege-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?（如有子级数据则会级联操作）'))
    		return false;
    	window.location.href = '/rms/privilege/deletePrivilege/' + row.id;
    },
    //角色管理按钮
    'click .role-edit': function (e, value, row, index) {
    	window.location.href = '/rms/role/save/edit?id=' + row.id;
     },
    'click .role-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?'))
    		return false;
    	window.location.href = '/rms/role/deleteRole/' + row.id;
    },
    //部门管理按钮
    'click .department-edit': function (e, value, row, index) {
    	window.location.href = '/rms/department/save/edit?id=' + row.id;
     },
    'click .department-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?'))
    		return false;
    	window.location.href = '/rms/department/deleteDepartment/' + row.id;
    },
    //用户组管理
    'click .group-edit': function (e, value, row, index) {
    	window.location.href = '/rms/group/save/edit?id=' + row.id;
     },
    'click .group-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?'))
    		return false;
    	window.location.href = '/rms/group/deleteGroup/' + row.id;
    },
    //用户管理
    'click .user-edit': function (e, value, row, index) {
    	window.location.href = '/rms/user/save/edit?id=' + row.id;
     },
    'click .user-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?'))
    		return false;
    	window.location.href = '/rms/user/deleteUser/' + row.id;
    },
    //按钮级别权限控制管理
    'click .privilegeButton-edit': function (e, value, row, index) {
    	window.location.href = '/rms/privilegeButton/save/edit?id=' + row.id;
     },
    'click .privilegeButton-delete': function (e, value, row, index) {
    	if(!confirm('确认要删除该条记录?'))
    		return false;
    	window.location.href = '/rms/privilegeButton/delete/' + row.id;
    }
};
//菜单表格
var menuTableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function(menuType) {
		$('#menus-table').bootstrapTable({
			url : '/rms/menu/parentListData/' + menuType,
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			/*height : 700,*/
			uniqueId : "id",
			detailView : true,
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			//注册加载子表的事件。注意下这里的三个参数！
			onExpandRow : function(index, row, $detail) {
				var oButtonInit = new menuButtonInit();
				oButtonInit.InitSubTable(index, row, $detail);
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menuicon',
				title : '菜单图标',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menuname',
				title : '菜单名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menutype',
				title : '菜单类型',
				align: 'center',
				valign: 'middle'
					
			}, {
				field : 'mastermenuid',
				title : '主（Master）菜单',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'sort',
				title : '排序',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'status',
				title : '状态',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: menuOperateFormatter
			} ]
		});
	}
	return oTableInit;
}

var menuButtonInit = function() {
	var oInit = new Object();
	//初始化子表格(无线循环)
	oInit.InitSubTable = function(index, row, $detail) {
		var parentid = row.id;
		var cur_table = $detail.html('<table></table>').find('table');
		$(cur_table).bootstrapTable({
			url : '/rms/menu/childrenListData/' + parentid,
			method : 'get',
			clickToSelect : true,
			detailView : true,//父子表
			uniqueId : "id",
			pageSize : 10,
			pageList : [ 10, 25 ],
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			columns : [ {
				field : 'id',
				title : 'ID',
				align: 'center'
			}, {
				field : 'menuicon',
				title : '菜单图标',
				align: 'center'
			}, {
				field : 'menuname',
				title : '菜单名称',
				align: 'center'
			}, {
				field : 'menutype',
				title : '菜单类型',
				align: 'center'
			}, {
				field : 'mastermenuid',
				title : '主（Master）菜单',
				align: 'center'
			}, {
				field : 'sort',
				title : '排序',
				align: 'center'
			}, {
				field : 'status',
				title : '状态',
				align: 'center'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				events : operateEvents,
				formatter: menuOperateFormatter
			} ],
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			//无线循环取子表，直到子表里面没有记录
			onExpandRow : function(index, row, $Subdetail) {
				oInit.InitSubTable(index, row, $Subdetail);
			}
		});
	};
	oInit.Init = function(){
		$('#menuBtn_add').click(function(){
			 window.location.href = '/rms/menu/save/add';
		});
		$('#menuBtn_delete').click(function(){
			createHiddenInputs('#menus-table');
		})
	}
	return oInit;
}

//按钮
function menuOperateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm menu-edit access-control">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm menu-delete access-control">删除</button>',
            '</div>'
    ].join('');
}

$(function(){
	//主菜单不用选择附属主菜单，从菜单可以选择父级菜单
	if($('#menu-form').find('input[name="menu.menutype"]').length != 0){
		menuType($('#menu-form').find('input[name="menu.menutype"]:checked'));
	}
	$('#menu-form').find('input[name="menu.menutype"]').change(function(){
		menuType(this);
	});
})

function menuType(_target){
	if($(_target).val() == 0){
		$('#mastermenus').parent().attr('style','display:none;');
		$('#parentmenus').parent().attr('style','display:none;');
		$('#mastermenus').parent().find('span').html('');
		$('#parentmenus').parent().find('span').html('');
	}else{//1
		$('#mastermenus').parent().attr('style','display:block;');
		$('#parentmenus').parent().attr('style','display:block;');
		$('#mastermenus').parent().find('span').html('*');
		$('#parentmenus').parent().find('span').html('*');
	}
}

//菜单编辑
$(function(){
	//主（Master）菜单选择
	if($('#mastermenus').length == 1){
		//masterMenusLoad($('#mastermenus').val(),$('input[name="menu.id"]').val());
	}
	$('#mastermenus').change(function(){
		masterMenusLoad($('#mastermenus').val(),$('input[name="menu.id"]').val());
	});
	function masterMenusLoad(masterMenu,menuId){
		if(masterMenu != -1){
			$.ajax({
				type: 'GET',
				url: '/rms/menu/parentListDataByMasterMenuId/' + masterMenu,
				data:{
					isNotMenuId:menuId
				},
			    success: function(data){
			    	if(data.code != 100000){
			    		alert(data.msg);
			    	}else{
			    		$('#parentmenus').empty();
			    		$('#parentmenus').html('<option value="-1">请选择父级菜单</option>');
			    		$.each(data.data.list,function(i,item){
			    			item.menuname = item.menuname.replace('-','&nbsp;&nbsp;&nbsp;');
			    			$('#parentmenus').append('<option value="'+ item.id +'">'+ item.menuname +'</option>')
			    		});
			    	}
			    },
			    error: function() {  
			    	alert('请求异常');
		      	}
			});
		}else{//清空父级菜单
			$('#parentmenus').empty();
			$('#parentmenus').html('<option value="-1">请选择父级菜单</option>');
		}
	}
});

//权限表格
var privilegeTableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#privilege-table').bootstrapTable({
			url : '/rms/privilege/listData',
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			/*height : 700,*/
			uniqueId : "id",
			detailView : true,
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			//注册加载子表的事件。注意下这里的三个参数！
			onExpandRow : function(index, row, $detail) {
				var oButtonInit = new privilegeButtonInit();
				oButtonInit.InitSubTable(index, row, $detail);
			},
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'privilegename',
				title : '权限名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'privilegeurl',
				title : 'URL',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menuid',
				title : '绑定菜单',
				align: 'center',
				valign: 'middle'
					
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: privilegeOperateFormatter
			} ]
		});
	}
	return oTableInit;
}

//按钮
function privilegeOperateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm privilege-edit access-control">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm privilege-delete access-control">删除</button>',
            '</div>'
    ].join('');
}

var privilegeButtonInit = function() {
	var oInit = new Object();
	//初始化子表格(无线循环)
	oInit.InitSubTable = function(index, row, $detail) {
		var parentid = row.id;
		var cur_table = $detail.html('<table></table>').find('table');
		$(cur_table).bootstrapTable({
			url : '/rms/privilege/childrenListData/' + parentid,
			method : 'get',
			clickToSelect : true,
			detailView : true,//父子表
			uniqueId : "id",
			pageSize : 10,
			pageList : [ 10, 25 ],
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			columns : [{
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'privilegename',
				title : '权限名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'privilegeurl',
				title : 'URL',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'menuid',
				title : '绑定菜单',
				align: 'center',
				valign: 'middle'
					
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: privilegeOperateFormatter
			} ],
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			//无线循环取子表，直到子表里面没有记录
			onExpandRow : function(index, row, $Subdetail) {
				oInit.InitSubTable(index, row, $Subdetail);
			}
		});
	};
	oInit.Init = function(){
		$('#privilegeBtn_add').click(function(){
			 window.location.href = '/rms/privilege/save/add';
		});
		$('#privilegeBtn_delete').click(function(){
			createHiddenInputs('#privilege-table');
		})
	}
	return oInit;
}

$(function(){
	/**
	 * 权限-角色分配
	 */
	//获取权限的角色
	$('#privileges-roles').change(function(){
		loadPrivilegesRoles($(this).val());
	});
	function loadPrivilegesRoles(_privilegeId){
		if(_privilegeId == -1){
			$('#privileges-roles-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/privilege/rolesByPrivilege/' + _privilegeId, 
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#roles-ckbox').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.roleIds,function(i,item1){
		    			$.each($('#roles-ckbox').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新权限的角色
	$('#privileges-roles-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadPrivilegesRoles($('#privileges-roles').val());
		    	}else{
		    		alert('更新权限-角色分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
});

//角色表格
var roleTableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#role-table').bootstrapTable({
			url : '/rms/role/listData',
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			/*height : 700,*/
			uniqueId : "id",
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'rolename',
				title : '角色名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'status',
				title : '状态',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: roleOperateFormatter
			} ]
		});
	}
	return oTableInit;
}

//按钮
function roleOperateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm role-edit access-control">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm role-delete access-control">删除</button>',
            '</div>'
    ].join('');
}

var roleButtonInit = function() {
	var oInit = new Object();
	oInit.Init = function(){
		$('#roleBtn_add').click(function(){
			 window.location.href = '/rms/role/save/add';
		});
		$('#roleBtn_delete').click(function(){
			createHiddenInputs('#role-table');
		})
	}
	return oInit;
}


$(function(){
	/**
	 * 角色-权限分配
	 */
	//获取角色的权限
	$('#roles-privileges').change(function(){
		loadRolesPrivileges($(this).val());
	});
	function loadRolesPrivileges(_roleId){
		if(_roleId == -1){
			$('#roles-privileges-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/role/privilegesByRole/' + _roleId,
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#privilegeTree').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.privilegeIds,function(i,item1){
		    			$.each($('#privilegeTree').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新角色的权限
	$('#roles-privileges-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadRolesPrivileges($('#roles-privileges').val());
		    	}else{
		    		alert('更新角色-权限分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
	
	/**
	 * 角色-用户分配
	 */
	//获取角色的用户
	$('#roles-users').change(function(){
		loadRolesUsers($(this).val());
	});
	function loadRolesUsers(_roleId){
		if(_roleId == -1){
			$('#roles-users-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/role/usersByRole/' + _roleId,
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#users-ckbox').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.userIds,function(i,item1){
		    			$.each($('#users-ckbox').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新角色的用户
	$('#roles-users-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadRolesusers($('#roles-users').val());
		    	}else{
		    		alert('更新角色-用户分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
	
	/**
	 * 角色-用户组分配
	 */
	//获取角色的用户组
	$('#roles-groups').change(function(){
		loadRolesGroups($(this).val());
	});
	function loadRolesGroups(_roleId){
		if(_roleId == -1){
			$('#roles-groups-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/role/groupsByRole/' + _roleId, 
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#groups-ckbox').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.groupIds,function(i,item1){
		    			$.each($('#groups-ckbox').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新角色的用户组
	$('#roles-groups-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadRolesGroups($('#roles-groups').val());
		    	}else{
		    		alert('更新角色-用户组分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
})

//部门表格
var departmentTableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#department-table').bootstrapTable({
			url : '/rms/department/listData',
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			/*height : 700,*/
			uniqueId : "id",
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'departmentname',
				title : '部门名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: departmentOperateFormatter
			} ]
		});
	}
	return oTableInit;
}

//按钮
function departmentOperateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm department-edit access-control">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm department-delete access-control">删除</button>',
            '</div>'
    ].join('');
}

var departmentButtonInit = function() {
	var oInit = new Object();
	oInit.Init = function(){
		$('#departmentBtn_add').click(function(){
			 window.location.href = '/rms/department/save/add';
		});
		$('#departmentBtn_delete').click(function(){
			createHiddenInputs('#department-table');
		})
	}
	return oInit;
}

//用户组别表格
var groupTableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#group-table').bootstrapTable({
			url : '/rms/group/listData',
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			/*height : 700,*/
			uniqueId : "id",
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'groupname',
				title : '用户组名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: groupOperateFormatter
			} ]
		});
	}
	return oTableInit;
}

//按钮
function groupOperateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm group-edit access-control">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm group-delete access-control">删除</button>',
            '</div>'
    ].join('');
}

var groupButtonInit = function() {
	var oInit = new Object();
	oInit.Init = function(){
		$('#groupBtn_add').click(function(){
			 window.location.href = '/rms/group/save/add';
		});
		$('#groupBtn_delete').click(function(){
			createHiddenInputs('#group-table');
		})
	}
	return oInit;
}

$(function(){
	/**
	 * 用户组-角色分配
	 */
	//获取用户组的角色
	$('#groups-roles').change(function(){
		loadGroupsRoles($(this).val());
	});
	function loadGroupsRoles(_groupId){
		if(_groupId == -1){
			$('#groups-roles-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/group/rolesByGroup/' + _groupId, 
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#roles-ckbox').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.roleIds,function(i,item1){
		    			$.each($('#roles-ckbox').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新用户组的角色
	$('#groups-roles-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadGroupsRoles($('#groups-roles').val());
		    	}else{
		    		alert('更新用户组-角色分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
	
	/**
	 * 用户组-用户分配
	 */
	//获取用户组的用户
	$('#groups-users').change(function(){
		loadGroupsUsers($(this).val());
	});
	function loadGroupsUsers(_groupId){
		if(_groupId == -1){
			$('#groups-users-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/group/usersByGroup/' + _groupId, 
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#users-ckbox').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.userIds,function(i,item1){
		    			$.each($('#users-ckbox').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新用户组的用户
	$('#groups-users-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadGroupsUsers($('#groups-users').val());
		    	}else{
		    		alert('更新用户组-用户分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
});

//用户表格
var userTableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#user-table').bootstrapTable({
			url : '/rms/user/listData',
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			/*height : 700,*/
			onPageChange:function(number, size){
				accessControl();
			},
			onLoadSuccess:function(data){
				accessControl();
			},
			uniqueId : "id",
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'useraccount',
				title : '用户名',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'departmentid',
				title : '所属部门',
				align: 'center',
				valign: 'middle'
			},{
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: userOperateFormatter
			} ]
		});
	}
	return oTableInit;
}

//按钮
function userOperateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm user-edit access-control">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm user-delete access-control">删除</button>',
            '</div>'
    ].join('');
}

var userButtonInit = function() {
	var oInit = new Object();
	oInit.Init = function(){
		$('#userBtn_add').click(function(){
			 window.location.href = '/rms/user/save/add';
		});
		$('#userBtn_delete').click(function(){
			createHiddenInputs('#user-table');
		})
	}
	return oInit;
}

$(function(){
	/**
	 * 用户-角色分配
	 */
	//获取用户的角色
	$('#users-roles').change(function(){
		loadUsersRoles($(this).val());
	});
	function loadUsersRoles(_userId){
		if(_userId == -1){
			$('#users-roles-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/user/rolesByUser/' + _userId, 
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#roles-ckbox').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.roleIds,function(i,item1){
		    			$.each($('#roles-ckbox').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新用户的角色
	$('#users-roles-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadUsersRoles($('#users-roles').val());
		    	}else{
		    		alert('更新用户-角色分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
	
	/**
	 * 用户-用户组分配
	 */
	//获取用户的用户组
	$('#users-groups').change(function(){
		loadUsersGroups($(this).val());
	});
	function loadUsersGroups(_userId){
		if(_userId == -1){
			$('#users-groups-form')[0].reset();
			return;
		}
		
		$.ajax({
			type: 'GET',
			url: '/rms/user/groupsByUser/' + _userId, 
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    	}else{
		    		$('#groups-ckbox').find('input[type="checkbox"]').prop('checked',false);
		    		$.each(data.data.groupIds,function(i,item1){
		    			$.each($('#groups-ckbox').find('input[type="checkbox"]'),function(j,item2){
		    				if($(item2).val() == item1){
		    					$(item2).prop('checked',true);
		    					return;
		    				}
		    			})
		    		});
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
	}
	//更新用户-用户组
	$('#users-groups-form').submit(function(){
		var _checkFormResult = checkForm(this);
		if(_checkFormResult == false){
			return _checkFormResult;
		}
		
		var _form = $(this);
		$.ajax({
			type: 'POST',
			url: $(_form).attr('action'),
			data: $(_form).serialize(),
		    success: function(data){
		    	if(data.code != 100000){
		    		alert(data.msg);
		    		loadUsersGroups($('#users-groups').val());
		    	}else{
		    		alert('更新用户-用户组分配成功')
		    	}
		    },
		    error: function() {  
		    	alert('请求异常');
	      	}
		});
		return false;
	});
});

//按钮级别权限表格
var privilegeButtonTableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#privilegeButton-table').bootstrapTable({
			url : '/rms/privilegeButton/listData',
			method : 'get',
			toolbar: '#toolbar',
			striped : true,
			cache : false,
			pagination : true,
			//queryParams: oTableInit.queryParams,//传递参数（*）
			sidePagination : "client",//分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 25, 50, 100 ],
			showRefresh : true,
			search: true,  
			showColumns: true,
			showToggle:true,    
			minimumCountColumns : 2,
			clickToSelect : true,
			/*height : 700,*/
			uniqueId : "id",
			onLoadSuccess:function(data){
				accessControl();
			},
			onPageChange:function(number, size){
				accessControl();
			},
			responseHandler : function(res) {
				if (res.code = 100000)
					return res.data.list;
			},
			columns : [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
			}, {
				field : 'id',
				title : 'ID',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'name',
				title : '功能名称',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'selectdommethod',
				title : '选择元素方式',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'selectdomname',
				title : '选择元素名',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'addtime',
				title : '添加时间',
				align: 'center',
				valign: 'middle'
			}, {
				field : 'operate',
				title : '操作',
				align: 'center',
				valign: 'middle',
				events: operateEvents,
				formatter: privilegeButtonOperateFormatter
			} ]
		});
	}
	return oTableInit;
}

//按钮
function privilegeButtonOperateFormatter(value, row, index) {
    return [
            '<div class="btn-group">',
            '<button type="button" class="btn btn-primary btn-sm privilegeButton-edit access-control">修改</button>',
            '<button type="button" class="btn btn-danger btn-sm privilegeButton-delete access-control">删除</button>',
            '</div>'
    ].join('');
}

var privilegeButtonButtonInit = function() {
	var oInit = new Object();
	oInit.Init = function(){
		$('#privilegeButtonBtn_add').click(function(){
			 window.location.href = '/rms/privilegeButton/save/add';
		});
		$('#privilegeButtonBtn_delete').click(function(){
			createHiddenInputs('#privilegeButton-table');
		})
	}
	return oInit;
}


