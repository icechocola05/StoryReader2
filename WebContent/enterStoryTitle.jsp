<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>동화 만들기: 제목 입력</title>
<link rel="stylesheet" href="CSS/enterStoryTitle.css" type='text/css' >
</head>
<%@ include file="header.jsp" %>
<body>
	<script>
		// 제목 빈칸인지 확인
		$(function(){
	        $("button[type=submit]").click(function(){
	            var isRight = true;
	            $("#frm").find("input[type=text]").each(function(index, item){
	                // 아무값없이 띄어쓰기만 있을 때도 빈 값으로 체크되도록 trim() 함수 호출
	                if ($(this).val().trim() == '') {
	                    alert("제목을 입력하세요");
	                    isRight = false;
	                    return false;
	                }
	            });
	            if (!isRight) {
	                return false;
	            }
	
	            
	        });
	
	    });
	</script>
	<div class="contents">
		<div class="msg-container">동화 제목을 입력해주세요</div>
		<div class="w3-display-container w3-margin-top w3-row w3-center">
			<form method="Post" action="doSaveStoryTitle" id="frm" class="w3-display-middle w3-padding w3-padding-64 w3-col s11 m6 l4">
				<input type="text" class=" w3-input w3-xlarge w3-margin-top" name="storyTitle" placeholder="동화 제목"><br>
				<button type="submit" class="w3-xlarge w3-margin-top">다음</button>
			</form>
		</div>
	</div>
</body>
</html>