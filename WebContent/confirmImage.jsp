<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>���ε� �̹��� Ȯ��</title>
</head>
<body>
	<%
		//�̹��� ��� �ޱ�
		String uploadFilePath = "";
		if(request.getAttribute("uploadFilePath") != null) uploadFilePath = (String) request.getAttribute("uploadFilePath");
	 %>
	  <img src="<%=uploadFilePath %>">
	  <div>
	  <%=(String)request.getAttribute("pageText") %>
	  </div>
	  <button onclick="location='uploadImage.jsp'"> ���� </button>
</body>
</html>