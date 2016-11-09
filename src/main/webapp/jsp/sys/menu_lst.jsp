<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<body>
	<form action="/rms/menu/deleteMenus" method="post" onsubmit="return checkDelForm();">
	<div style="padding:0 20px 20px 20px;height:85%;">
		<div id="toolbar">
			<button class="btn btn-default dropdown-toggle" type="button" id="menuTypes" data-toggle="dropdown">
				${menuTypeName }
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu" aria-labelledby="menuTypes">
		  		<li role="presentation"><a role="menuitem" tabindex="-1" href="/rms/menu/list?menuType=0">主（Master）菜单</a></li>
			    <li role="presentation"><a role="menuitem" tabindex="-1" href="/rms/menu/list?menuType=1">从（Slave）菜单</a></li>
			</ul>
        	<button id="menuBtn_add" type="button" class="btn btn-default access-control">
            	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        	</button>
	        <button id="menuBtn_delete" type="submit" class="btn btn-default access-control">
	          	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
	       	</button>
        	<div id="hiddenInputs"></div>
    	</div>
		<table id="menus-table"></table>
	</div>
	</form>
	<script type="text/javascript">		
		$(function(){
		    //1.初始化Table
		    var oTable = new menuTableInit();
		    oTable.Init(${menuTypeValue});
		});
	</script>
</body>
<%@ include file="footer.jsp" %>