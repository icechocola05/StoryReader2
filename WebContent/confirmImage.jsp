<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	 %>
	  <img src="<%=uploadFilePath %>">
	  <div>
	  <%=(String)request.getAttribute("pageText") %>
	  </div>
	  <button onclick="location='uploadImage.jsp'"> 다음 </button>
</body>
</html>