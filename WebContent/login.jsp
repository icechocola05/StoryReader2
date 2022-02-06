<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<link rel="stylesheet" href="CSS/login.css" type='text/css' >
</head>
<%@ include file="header.jsp" %>
<body>
	<div class="contents">
		<div class="w3-display-container w3-margin-top w3-row w3-center indexHeight">
			<h2>로그인</h2> <hr>
			
			<form method="post" action="doLogin" class="w3-display-middle w3-padding w3-padding-64 w3-col s11 m6 l4">
				<input type="text" class="login_input w3-input w3-margin-top" name="user_input_id" placeholder="이메일"> <br />
				<input type="password" class="login_input w3-input w3-margin-top" name="user_input_pw" placeholder="비밀번호"> <br />
				<button onclick="location='login.jsp'" class="login-btn w3-margin-top"> 로그인 </button>
			</form>
		</div>
	</div>
</body>
</html>