<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 菜单递归 -->
<%-- <li><a href="#">${topSlaveMenu.menuname }</a>
	<c:choose>
		<c:when test="${fn:length(topSlaveMenu.slaveChildrenMenus) > 0}">
			<ul>
				<c:forEach items="${topSlaveMenu.slaveChildrenMenus}" var="childrenMenu">
					<c:set var="level" value="${level + 1}" scope="request" />
					<c:set var="topSlaveMenu" value="${childrenMenu}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->
					<c:import url="public/menu.jsp" />
				</c:forEach>
			</ul>
		</c:when>
	</c:choose>
</li> --%>

<c:forEach items="${topSlaveMenu.slaveChildrenMenus}" var="childrenMenu">

	<c:choose>
		<c:when test="${sessionScope.currentUser.useraccount == 'zowbman' }">
			<c:set var="isShow" value="true" scope="request"/>
		</c:when>
		<c:otherwise>
			<c:set var="isShow" value="false" scope="request"/>
			<c:set var="exitId" value="${fn:length(sessionScope.currentUser.hasPrivileges)}" scope="request"/>
			<c:forEach items="${sessionScope.currentUser.hasPrivileges}" var="hasPrivilege" end="${exitId}">
				<c:if test="${childrenMenu.id == hasPrivilege.menuid}">
					<c:set var="isShow" value="true" scope="request"/>
					<c:set var="exitId" value="0" scope="request" />
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>

	<c:if test="${isShow == true}">
		<li><a href="javascript:;" onclick="frameLoad('${childrenMenu.privilege.privilegeurl }');">${childrenMenu.menuname }</a>
			<c:choose>
				<c:when test="${fn:length(childrenMenu.slaveChildrenMenus) > 0}">
					<ul>
						<c:set var="level" value="${level + 1}" scope="request" />
						<c:set var="topSlaveMenu" value="${childrenMenu}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->
						<c:import url="/jsp/public/menu.jsp" />
					</ul>
				</c:when>
			</c:choose>
		</li>
	</c:if>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 -->