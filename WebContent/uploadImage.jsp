<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>페이지 등록</title>
<link rel="stylesheet" href="CSS/selection.css" type='text/css' >
</head>
<%@ include file="header.jsp" %>
<body>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btn-upload').click(function(e){
			e.preventDefault();
			$('#input-file').click();
		});
	});
	
	function checkImgUploaded(){
		var fileCheck = document.getElementById("input-file").value;
		if(fileCheck){
			alert("이미지 업로드 성공!");
			document.getElementById('submit_btn').click();
		}
	}
</script>
	
	<div id="msg-container">이미지 등록 방식을 정해주세요</div>
	<div id="btn-container">
		<button onclick="location='fileInput.jsp'" >사진 촬영하기</button> <br><br>
		<form action="doUploadImage" id="uploadForm" method="post" enctype="multipart/form-data">
			<input type="file" id="input-file" name="input-file" onchange="checkImgUploaded()" style="display: none;"/>
			<button type="submit" id="submit_btn" style="display: none;"></button>
		</form>
		<button type="button" id="btn-upload" >저장된 이미지 사용하기</button>
	</div>
</body>
</html>
