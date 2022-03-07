<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>업로드 이미지 확인</title>

</head>
<%@ include file="header.jsp" %>
<body>
	<%
		//이미지 경로 받기
		String uploadFilePath = "";
		if(request.getAttribute("uploadFilePath") != null) uploadFilePath = (String) request.getAttribute("uploadFilePath");
		
		//이미지 속 텍스트 받기
		String pageText = (String)request.getAttribute("pageText");
	 %>

	 <form action="doPrepareSetting" method="POST">
	 	<div class="w3-row-padding w3-margin-top">
	 		<div class="w3-container w3-half w3-padding-large" style="text-align:center;">
		 		<img name="input-img" style="object-fit: contain;"src="<%=uploadFilePath %>">
		 		<input type="hidden" name="pageImgUrl" value="<%=uploadFilePath %>" >
		 	</div>
			 <div name="input-text" class="w3-container w3-half w3-padding-large" style="text-align:center;">
			 	<textarea id="pageText" class = "w3-round-large w3-padding" name="pageText" cols="50" rows="10" style="width:90%;"><%=pageText%></textarea>
			 </div>
		 </div>
		 <div class="w3-center">
		 <button type="submit" class="w3-button w3-padding-large" style="width:50%; background-color: #927D71;"> 다음 </button>
		 </div>
	 </form>
</body>
</html>