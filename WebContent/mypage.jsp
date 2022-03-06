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
<title>내 동화 보기</title>
</head>
<%@ include file="header.jsp" %>
<body>
	<%
		ArrayList<Story> myStory = (ArrayList<Story>)session.getAttribute("myStoryList");//내 스토리 리스트 받아오기
		ArrayList<String> myStoryImgUrl = (ArrayList<String>)session.getAttribute("myStoryListImgUrl");//내 스토리 리스트 받아오기
		System.out.println(myStory.size());
		%>
		
	<%
		for (int i=0;i< myStory.size();i++){
	%>
		<form method = "Post" action = "DoReadMyScript">
			<div class = "w3-border w3-round-large w3-quater">
				<div id="story-img<%=i%>" class="w3-margin-top w3-margin-bottom">
					<img src="<%=myStoryImgUrl.get(i)%>">
				</div>
				<div id="story-title<%=i%>" class=""><%=myStory.get(i).getStoryTitle()%></div>
			</div>
		</form>
		<%} %>
</body>
</html>