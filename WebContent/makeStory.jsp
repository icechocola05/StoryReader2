<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.*"%>
<%@ page import="model.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<title>동화 만들기 : 페이지 추가</title>
</head>
<%@ include file="header.jsp" %>
<body>
	<%
		//session에 저장해 둔 currStory 객체 가져오기
		Story currStory = (Story)session.getAttribute("currStory");
		String storyTitle = currStory.getStoryTitle();
	%>
	<iframe id="iframe1" name="iframe1" style="display:none"></iframe>
	<form method="post" action="doChangeStoryTitle" target="iframe1">
		<input type="text" class=" w3-input w3-xlarge w3-margin-top" name="storyTitle" value="<%=storyTitle%>" placeholder="동화 제목을 입력해주세요"><br>
	<button type="submit">수정</button>
	</form>
	<br>
	<button onclick="location='uploadImage.jsp'"> + </button>
	
	
</body>
</html>