<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>������ ���</title>
<style>
</style>
</head>
<%@ include file="header.jsp" %>
<body>
	<div>�̹��� ��� ����� �����ּ���</div>
		<button onclick="location='fileInput.jsp'">���� �Կ��ϱ�</button> <br>
	<form action="doUploadImage" id="uploadForm" method="post" enctype="multipart/form-data">
		<label className="input-file-button" for="input-file">����� �̹��� ����ϱ�</label>
		 <!-- ��ư Ŭ�� �� ���� ã��� ��ũ -->
		<input type="file" name="input-file" id="input-file" onchange="checkImgUploaded()" style="display: none;"/> <!-- ȭ�鿡�� ���� ã�� ��ư ������ �ʵ��� -->
		<button type="submit" id="submit_btn" style="display: none;"></button>
	</form>
	
	<script>
		function checkImgUploaded() {
			var fileCheck = document.getElementById("input-file").value;
			if(fileCheck){
				alert("�̹��� ���ε� ����!");
				document.getElementById('submit_btn').click();
			}
		}
		
	</script>
</body>
</html>