<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">

		<!-- logo header-->
 		<c:import url="/WEB-INF/views/includes/header_blog.jsp"></c:import>
 		<!-- logo header end -->
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>제목</h4>
					<p>
						등록된 글이 없습니다.
					</p>
					
					
				</div>
				
				<ul class="blog-list">
					<li>
						<a href="">어벤져스: 인피니티 워</a> 
						<span>18/05/04</span>
					</li>
					<li>
						<a href="">챔피언</a> 
						<span>18/05/04</span>
					</li>
					<li>
						<a href="">그날, 바다</a> 
						<span>18/05/04</span>
					</li>
					<li>
						<a href="">원더스트럭</a> 
						<span>18/05/04</span>
					</li>
					<li>
						<a href="">렛 더 선샤인 인</a> 
						<span>18/05/04</span>
					</li>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/upload/${bVo.logoFile}">				
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${list}" var="cateVo">
					<li><a href="">${cateVo.cateName}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<!-- footer-->
 		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
 		<!-- footer end-->
		
	</div>
</body>
</html>