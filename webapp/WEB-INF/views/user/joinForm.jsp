<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#checkid-msg {
	color : blue;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div class="center-content">
		
		<!-- logo header-->
 		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
 		<!-- logo header end -->
		
		<form class="join-form" id="join-form" method="POST" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input type="text" name="userName"  value="" />
			
			<label class="block-label" for="id">아이디</label>
			<input type="text" name="id"  id="id" value="" />
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<p id="checkid-msg" class="form-error">
			&nbsp;
			</p>
			
			<label class="block-label" for="password">패스워드</label>
			<input type="password" name="password"  value="" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>

	

</body>

	<!--ajax -->
	<script type="text/javascript">
	$("#btn-checkid").on("click", function(){
		var id = $("#id").val();
		console.log(id);//id가 잘 체크되는지 브라우저 콘솔에 찍어주렴!
		
	 	$.ajax({
	 		
	 		url : "${pageContext.request.contextPath}/user/idcheck",		
	 		type : "post",
	 		data : {id:id}, //파라미터값 : 넘어갈 때 담아뒀던 이름
	 		
	 		dataType : "json",
	 		success : function(isCheckOk){
	 		console.log(isCheckOk);
	 			
	 			/*ajax 성공시 처리*/
	 			if(isCheckOk==true) {
	 				$("#checkid-msg").html("다른 아이디로 가입해 주세요.");
	 			}else {
	 				$("#checkid-msg").html("사용할 수 있는 아이디 입니다.");
	 			}
	 			
	 		},
	 		error : function(XHR, status, error) {
	 			console.error(status + " : " + error);
	 		}
	 	}); //end ajax
	}); //end on

	</script>
</html>