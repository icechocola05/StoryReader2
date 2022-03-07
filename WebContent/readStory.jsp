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
<title>내 동화 읽기</title>
</head>
<%@ include file="header.jsp" %>
<body>
	<%
		Story currStory = (Story)session.getAttribute("currStory");//동화 정보
		Page currPage = (Page)request.getAttribute("currPage");//페이지 정보
		ArrayList<Sentence> currSentences = (ArrayList<Sentence>)request.getAttribute("currSentences");//문장 묶음
		
		ArrayList<String> voiceColorList = (ArrayList<String>)request.getAttribute("voiceColorList");
		ArrayList<String> emoticonNameList = (ArrayList<String>)request.getAttribute("emoticonNameList");
		ArrayList<String> opacityList = (ArrayList<String>)request.getAttribute("opacityList");
	%>
	<input type="hidden" name="readPage" value="0">
	<div id="story-title"><%=currStory.getStoryTitle() %></div>
	<div class="w3-row w3-container w3-margin-top w3-margin-bottom" >
		<div class="w3-col w3-container w3-center" style="width:25%">
			<img class="w3-button w3-hover-white" id="pre_btn" src="./IMG/previous_w.png">
		</div>
		<div id="page-sentence" class="w3-col w3-container" style="width:50%"><%=currSentences.get(0).getSentence() %></div>
		<div class="w3-col w3-container w3-center" style="width:25%">
			<img class="w3-button w3-hover-white" id="next_btn" src="./IMG/next_w.png">
		</div>
	</div>
	<div id="page-img"><%=currPage.getPageImgUrl()%></div>
</body>
</html>