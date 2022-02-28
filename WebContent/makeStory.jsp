<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="model.dao.*"%>
<%@ page import="model.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<title>동화 만들기 : 페이지 추가</title>
<link rel="stylesheet" href="CSS/makeStory.css" type='text/css' >
</head>
<%@ include file="header.jsp" %>
<body>
	<%
		//session에 저장해 둔 currStory, pageList 가져오기
		Story currStory = (Story)session.getAttribute("currStory");
		String storyTitle = currStory.getStoryTitle();
		
		ArrayList<Page> pageList = (ArrayList<Page>)session.getAttribute("pageList");
		
		//doMakeFullStory 에서 사용할 index
		int pageIndex = 1;
		session.setAttribute("pageIndex", pageIndex);
		
	%>
	<div class="settings" style="margin: 3% 1% 2% 1%;">
	<iframe id="iframe1" name="iframe1" style="display:none"></iframe>
	<div class="w3-display-container w3-margin-top w3-row w3-center" style="width: 60%;">
		<form method="post" class="w3-display-container w3-margin-top w3-row w3-center"  action="doChangeStoryTitle" target="iframe1">
			<input type="text" class="w3-input w3-xlarge w3-margin-top" name="storyTitle" value="<%=storyTitle%>" placeholder="동화 제목을 입력해주세요"> <button type="submit">수정</button>
		</form>
	</div>
	<form method="post" class="w3-display-container w3-margin-top w3-row w3-center"  action="doMakeFullStory">
	<% 
		if(pageList != null) {
			int pageSize = pageList.size();
			for(int i=0; i<pageSize; i++) { 
				String pageImgUrl = pageList.get(i).getPageImgUrl();
				String pageSentence = pageList.get(i).getPageSentence();
	%>
	
		<div class="w3-container" style="border:2px solid #C4C4C4; border-radius:20px; margin:0 3% 2% 3%; ">
			<div class="w3-row w3-center">
				<div class="w3-col w3-cell-middle" style="margin: 1% 3% 1% 3%; width: 10%;">
					<img name="page-img" src="<%=pageImgUrl%>" style="width:50%; height: 20%; object-fit:cover">
				</div>
				<div class="w3-col w3-cell-middle w3-xlarge" style="margin: 2% 1% 1% 10%; width: 60%;">
					<%=pageSentence%>
				</div>
			</div>
		</div>
	<%		}
		}
	%>
	<br>
	<div class="w3-container" style="border:2px solid #C4C4C4; border-radius:20px; margin:0 3% 0% 3%; padding:1% 3% 1% 1%; ">
		<a onclick="location='uploadImage.jsp'">
			<img class="w3-button w3-hover-white" id="plus_page" style="width:10%;" src="./IMG/plus.png" >
		</a>
	</div>
	
	<div class="btn">
       <button type="SUBMIT" class="submit-btn"> 동화 완성  </button>
    </div>
    </form>
    </div>
	
</body>
</html>