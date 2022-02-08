<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>페이지 등록</title>
<style>
</style>
</head>
<%@ include file="header.jsp" %>
<body>
	<div>이미지 등록 방식을 정해주세요</div>
		<button onclick="location='fileInput.jsp'">사진 촬영하기</button> <br>
	<form action="doUploadImage" id="uploadForm" method="post" enctype="multipart/form-data">
		<label className="input-file-button" for="input-file">저장된 이미지 사용하기</label>
		 <!-- 버튼 클릭 시 파일 찾기로 링크 -->
		<input type="file" name="input-file" id="input-file" onchange="checkImgUploaded()" style="display: none;"/> <!-- 화면에서 파일 찾는 버튼 보이지 않도록 -->
		<button type="submit" id="submit_btn" style="display: none;"></button>
	</form>
	
	<script>
		function checkImgUploaded() {
			var fileCheck = document.getElementById("input-file").value;
			if(fileCheck){
				alert("이미지 업로드 성공!");
				document.getElementById('submit_btn').click();
			}
		}
		
	</script>
</body>
</html>