<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>�α���</title>
</head>
<%@ include file="head.html" %>
<body>
	<div class="login-section">
		<p class ="login-title">�α���</p>
		<hr>
		<form method="post" action="doLogin">
		<label>���̵�
			<input type="text" class = "input-user" name="user_input_id" >
		</label><br />
		<label>��й�ȣ
			<input type="password" class = "input-user" name="user_input_pw" >
		</label><br />
		
		<button onclick="location='login.jsp'" class = "login-btn"> Login </button>
	</form>
	<button onclick="location='join.jsp'" class = "join-btn"> ȸ�������ϱ� </button>
	</div>
</body>
</html>