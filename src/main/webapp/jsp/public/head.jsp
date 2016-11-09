<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>boboface's后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="/css/index.css">
	<link rel='stylesheet prefetch' href='/menu/css/foundation.css'>
	<link href="/menu/css/mtree.css" rel="stylesheet" type="text/css">
	<script src="/js/jquery.min.js"></script> 
	<script src="/js/common.js"></script>
	<style>
		.mtree-demo .mtree {
			background: #EEE;
			/*margin: 20px auto;*/
			max-width: 320px;
			border-radius: 3px;
			min-width: 200px;
		}
		
		ul.mtree.transit {
			background-color: #EEE;
		}
		
		.mtree-skin-selector {
			text-align: center;
			background: #EEE;
			padding: 10px 0 15px;
		}
		
		.mtree-skin-selector li {
			display: inline-block;
			float: none;
		}
		ul.mtree.transit a {
			color: black;
		}
		
		.mtree-skin-selector button {
			padding: 5px 10px;
			margin-bottom: 1px;
			background: #BBB;
		}
		
		.mtree-skin-selector button:hover {
			background: #999;
		}
		
		.mtree-skin-selector button.active {
			background: #999;
			font-weight: bold;
		}
		
		.mtree-skin-selector button.csl.active {
			background: #FFC000;
		}
	</style>
  </head>
  
  <body>
  	<ul style="display:none;">
  		<c:forEach items='${sessionScope.currentUser.hasPrivileges}' var='hasPrivilege'>
  			<c:if test="${!empty hasPrivilege.privilegeurl}">
  				<li class="currentUserHasPrivilege">${hasPrivilege.privilegeurl}@<c:choose><c:when test="${hasPrivilege.privilegeButton.selectdommethod == 0}">#${hasPrivilege.privilegeButton.selectdomname}</c:when><c:when test="${hasPrivilege.privilegeButton.selectdommethod == 1}">.${hasPrivilege.privilegeButton.selectdomname}</c:when></c:choose></li>
  			</c:if>
		</c:forEach>
  	</ul>
    <div>
    	<div id="head">
    		<div class="head-left">
    			<span>boboface's后台管理系统<span>
    		</div>
    		<div class="head-right">
    			<span>欢迎，${sessionScope.currentUser.useraccount}</span>
    			<a href="/logout">退出</a>
    		</div>
    	</div><!-- head结束 -->