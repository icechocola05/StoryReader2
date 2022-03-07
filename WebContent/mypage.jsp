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
	<div class="w3-row-padding w3-margin-top">
	<%
		for (int i=0;i< myStory.size();i++){
	%>
		<form method = "Post" action = "DoReadStory">
			<div class = "w3-container w3-border w3-round-large w3-quarter w3-margin-bottom w3-padding-right" onclick="readStory(<%=i%>)">
				<div id="story-img<%=i%>" class="w3-margin-top w3-margin-bottom" >
					<img src="<%=myStoryImgUrl.get(i)%>" style="width:100%; height:40vh; object-fit: contain;">
				</div>
				<div id="story-title<%=i%>" class="w3-center"><%=myStory.get(i).getStoryTitle()%></div>
				<input type="hidden" name="story_id" value="<%=i%>">
				<input type="submit" id = "readBtn<%=i%>" name="readBtn" style="display:none;">
			</div>
		</form>
	<%}%>
	</div>
<script>
function readStory(num){
	document.getElementById("readBtn"+num).click();
}
</script>
</body>
</html>