<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>업로드 이미지 확인</title>
<style>
#img-content{
	margin-top : 10%;
	margin-left : 10%;
	margin-right: 10%;
	margin-bottom:5%;
	display:inline-block;
	width:80%;
	height:80%;
}
img {
	display:inline-block;
	width: 38vw;
	height: 40vh;
	object-fit: contain;
	background-color:#C4C4C4;
	
  
}
textarea{
	width: 40vw;
  	height: 40vh;
  	margin-top : 10%;
	margin-right : 10%;
}
</style>
</head>
<%@ include file="headerIn.jsp" %>
<body>
	<%
		//이미지 경로 받기
		String uploadFilePath = "";
		if(request.getAttribute("uploadFilePath") != null) uploadFilePath = (String) request.getAttribute("uploadFilePath");
	 %>
	 <div id = "img-content">
	  <img src="<%=uploadFilePath %>">
	  <textarea>
	  		<%=(String)request.getAttribute("pageText") %>
	  </textarea>
	  </div>
	  <br><br>
	  <button onclick="location='uploadImage.jsp'"> 다음 </button>
</body>
</html>