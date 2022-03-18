<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<%@ include file="head.html" %>
<body>
	<div class="header">
		<div class="w3-bar w3-border w3-card-4 w3-xlarge">
			<c:set var="user" scope="page" value="${sessionScope.currUser }" />
	  		
	  		<c:choose>
	  			<c:when test="${user eq null }">
					<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24 w3-margin-left" onclick="alertLogin();">동화 만들기</a>
					<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24" onclick="alertLogin();">만든 동화 보기</a>
				</c:when>
	  			<c:otherwise>
	  				<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24 w3-margin-left" href="enterStoryTitle.jsp">동화 만들기</a>
					<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24" href="DoGetMyStoryList">만든 동화 보기</a>
	  			</c:otherwise>
	  		</c:choose>

			<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24" href="#">도움말</a>
			
	  		<c:choose>
	  			<c:when test="${user eq null }">
			<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24 w3-right w3-margin-right" href="login.jsp">로그인</a>
			<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24 w3-right w3-margin-right" href="register.jsp">회원 가입</a>
				</c:when>
	  			<c:otherwise>
	  			<form id="f" name = "post" action = "logout">
	  				<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24 w3-right w3-margin-right" href="#">안녕하세요. ${user.userName}님</a>
					<a class="w3-bar-item w3-button w3-hover-none w3-text-black w3-hover-text-white w3-padding-24 w3-right w3-margin-right" href="javascript:void(0);" onclick="doLogout();">로그아웃</a>
	  			</form>
	  			</c:otherwise>
	  		</c:choose>
	  		<c:remove var="userId" scope="page" />
		</div>
	</div>
	<script type="text/javascript" src="js/logout.js" charset="UTF-8"></script>
</body>
</html>