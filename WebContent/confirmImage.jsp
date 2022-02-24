<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>업로드 이미지 확인</title>
<style>
img {
	display:inline-block;
	width: 38vw;
	height: 40vh;
	object-fit: contain;
	background-color:#C4C4C4;  
}
#input-text{
	display:inline-block;
  	vertical-align:middle;
}
#pageText{
	vertical-align:middle;
	display:inline-block;
	width: 40vw;
  	height: 40vh;

}
</style>

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
		 <img name="input-img" src="<%=uploadFilePath %>">
		 <input type="hidden" name="pageImgUrl" value="<%=uploadFilePath %>" >
		 <div name="input-text">
		 <textarea id="pageText" name="pageText" cols="50" rows="10"><%=pageText%></textarea>
		 </div>
		 <button type="submit"> 다음 </button>
	 </form>
</body>
</html>