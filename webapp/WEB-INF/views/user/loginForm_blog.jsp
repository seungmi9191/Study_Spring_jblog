<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.form-error {
	color : blue;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div class="center-content">
		
		<!-- logo header-->
 		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
 		<!-- logo header end -->
		
		<form class="login-form" method="POST" action="${pageContext.request.contextPath}/user/login_blog">
      		<label>아이디</label> 
      		<input type="text" id="id" name="id" value="">
      		
      		<label>패스워드</label> 
      		<input type="password" id="password" name="password" value="">
      			
      			<!--아이디 혹은 비밀번호가 안맞는 경우 controller에서 result값을 받아와서 뿌려줌-->
      			<c:if test="${param.result  eq 'fail'}">
      				<p class="form-error">
      					로그인 실패<br>
						아이디/패스워드를 확인해 주세요.
					</p>
      			</c:if>
      			
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>

</html>