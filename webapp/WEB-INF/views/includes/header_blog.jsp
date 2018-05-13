<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	<!-- 블로그 헤더 -->
		<div id="header">
		
		<h1><a href="${pageContext.request.contextPath}/${bVo.id}">${bVo.blogTitle}</a></h1>
		<ul>
			<c:choose>
				<c:when test="${bVo.id eq authUser.id}">
						<li><a href="${pageContext.request.contextPath}/user/logout_blog?id=${authUser.id}">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">내 블로그 관리</a></li>
				</c:when>
				
				<c:when test="${!empty authUser}">
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</c:when>
				
				<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/login_blog?id=${authUser.id}">로그인</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		</div>