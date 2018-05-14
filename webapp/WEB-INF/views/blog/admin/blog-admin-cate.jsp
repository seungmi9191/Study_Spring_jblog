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
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      	
		   		     <!--리스트가 들어가서 반복됨 -->
		      		<tbody id=cateList>
						<!-- 리스트 들어가서 반복 -->
					</tbody>
		      	</table>	
				
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add" >
		      		<tr>
		      			<td class="t"></td>
		      			<td><input type="hidden" name="id" value="${bVo.id}"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>

		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	
			</div>
		</div>
		
		<!-- footer-->
 		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
 		<!-- footer end-->
	</div>
</body>

	<!--ajax 처리 -->
	<script type="text/javascript">
		//객체화 한 다음 리스트 그릴 준비 됐다고 알리기
		$(document).ready(function() {
			fetchList();
		});
		
		//카테고리 삭제 버튼
		$("#cateList").on("click","input", function(){
			//event.preventDefault();
			var cateNo = $(this).data("cateno"); 
			console.log(cateNo);
			console.log("click btnDel");
			
			var postNum = $(this).data("postno");
			if(postNum>0) {
				alert("삭제할 수 없습니다.");
			} else  {
				console.log("포스트가 없으니 삭제가능합니다.");
			}
			 
		//acc-03.카테고리 삭제		
		//카테고리 삭제 ajax
		$.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/category/delete",
			type : "post",
			data : {cateNo:cateNo},
			
			dataType : "json",
			success : function(result) {
				if(result==true) {
					$("#card").remove();//result가 true면 하단 tr의 id값을 지우렴.
					console.log(result);
					console.log("삭제성공");
				} else {
					console.log(result);
					console.log("삭제실패");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});	
		
		//카테고리 추가 버튼
		$("#btnAddCate").on("click",function(){
			event.preventDefault();
			console.log("click btnAdd");
			
			cateVo = {  id : $("[name=id]").val(),
						cateName: $("[name=name]").val(),
					    description : $("[name=desc]").val()
					 }
		console.log(cateVo);
		
			
		//acc-02.카테고리 추가
		//카테고리 등록 ajax
		$.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/category/add",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cateVo),
			
			dataType : "json",
			success : function(cateVo) {
				render(cateVo, "up");
				$("[name=name]").val("");
				$("[name=desc]").val(""); 
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});	
	
		
	//acc-01.카테고리 리스트 출력
	//카테고리 리스트 ajax
	function fetchList() {
		$.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/category/list",
			type : "post",
				
			dataType : "json",
			success : function(list) {
				/*성공시 처리*/
				console.log(list);
				for(var i=0; i<list.length; i++) {
					render(list[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	//리스트 그려주기
	function render(cateVo, updown) {
			var str ="";
			//str += "		<tr id ='" + cateVo.cateNo + "'>";
			str += "		<tr id ='card'>";
			str += "			<td>" + cateVo.cateNo + "</td>";
			str += "			<td>" + cateVo.cateName +  "</td>";
			str += "			<td>" + cateVo.postNum + "</td>";
			str += "			<td>" + cateVo.description + "</td>";
			str += "			<td><input type='image' src='${pageContext.request.contextPath}/assets/images/delete.jpg' id='btnDel' data-cateno='"+cateVo.cateNo+"' data-postno='"+cateVo.postNum+"'></td>";
			str += "       </tr>";
			
			if(updown == "up") {
				$("#cateList").prepend(str);
			} else if(updown == "down") {
				$("#cateList").append(str);
			} else {
				console.log("error!");
			}
	}
	
	</script>
</html>