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
				<%-- <c:forEach items="${listPostContent}" var="listPostContent">
					<li>
						<h4>${listPostContent.postTitle}</h4>
						<p>${listPostContent.postContent}</p>
					</li>
				</c:forEach> --%>
	
				</div>
				
				<ul class="blog-list">
						<c:forEach items="${listPost}" var="listPost">
							<li>
								<a href="${pageContext.request.contextPath}/${authUser.id}?cateNo=${listPost.postNo}">${listPost.postTitle}</a>
								<span>${listPost.regDate}</span>
							</li> 
							
						</c:forEach>
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
				 <c:forEach items="${list}" var="list">
					<li><a href="${pageContext.request.contextPath}/${authUser.id}?cateNo=${list.cateNo}">${list.cateName}</a></li>
				</c:forEach> 
			</ul>
		</div>
		
		<!-- footer-->
 		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
 		<!-- footer end-->
		
	</div>
</body>
</html>