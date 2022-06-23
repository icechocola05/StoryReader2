<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.*"%>
<%@ page import="model.dto.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
</head>
<%@ include file="header.jsp" %>
<body>
	<%
		User currUser = (User)session.getAttribute("currUser");
	%>
	<div class="contents">
		<div class="w3-display-container w3-margin-top w3-row w3-center indexHeight">
			<h2>내 정보</h2> <hr>
		</div>
		<div>
			<input type="text" class="login_input w3-input w3-margin-top" name="user_input_id" placeholder="이메일"> <br />
			<input type="text" class="login_input w3-input w3-margin-top" name="user_input_name" placeholder="이름"> <br />
			<input type="password" class="login_input w3-input w3-margin-top" name="user_input_pw" placeholder="비밀번호"> <br />
			<input type="password" class="login_input w3-input w3-margin-top" name="user_input_pw_check" placeholder="비밀번호 확인"> <br />
				<button onclick="location='login.jsp'" class="login-btn w3-margin-top"> 회원 가입 </button>
		</div>
	</div>
	
</body>
</html>