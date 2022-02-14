<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>업로드 이미지 확인</title>
</head>
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
		 <div>
		 <textarea id="pageText" name="pageText" cols="50" rows="10"><%=pageText%></textarea>
		 </div>
		 <button type="submit"> 다음 </button>
	 </form>
</body>
</html>