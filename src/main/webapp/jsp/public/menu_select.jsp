<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 菜单按下拉框递归 -->
<c:forEach items="${parentMenus}" var="parentMenu">
		<c:if test="${parentMenu.id != menu.id}">
			<option value="${parentMenu.id }" <c:if test="${parentMenu.id == privilege.menuid }">selected</c:if>>
			<c:forEach var="i" begin="1" end="${level }">
				<c:choose>
					<c:when test="${i == level}">
						&nbsp;&nbsp;&nbsp;┗
					</c:when>
					<c:otherwise>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			${parentMenu.menuname }
			</option>
		</c:if>
		<c:choose>
			<c:when test="${fn:length(parentMenu.slaveChildrenMenus) > 0}">
					<c:if test="${parentMenu.id != menu.id}">
						<c:set var="level" value="${level + 1 }" scope="request" />	
					</c:if>
					<c:set var="parentMenus" value="${parentMenu.slaveChildrenMenus}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->
					<c:import url="/jsp/public/menu_select.jsp" />
			</c:when>
		</c:choose>
</c:forEach>
<c:if test="${level > 0}">
	<c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 -->
</c:if>
