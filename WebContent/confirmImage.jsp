<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>업로드 이미지 확인</title>

</head>
<%@ include file="header.jsp" %>
<body>
<script>
	$(function() {
		
		checkQuo = <%=session.getAttribute("checkQuo") %>;
		console.log("체크값 : " + checkQuo);
		if(checkQuo == 1) {
			$('#fake_textarea').html($('#fake_textarea').html().replace(/\"/g, '<span style="color:#336699">\"</span>'));
		}
		
		//radio 값 유지
		method = $("#processing_method").val();
		$("input:radio[name='processing-type']").each(function(index, element) {
			if(method == $(element).val()) {
				console.log(method);
				$(element).attr('checked', true);
			}
    	});
		
		// radio 값 변경
		$("input[name='processing-type']").change(function() {
			$('#pageText').val($('#fake_textarea').html());
			console.log($('#pageText').val());
			$('.processingTextSubmit-btn').click();
			setTimeout(function() { 
				location.reload(); }, 300);
		});
		
		//텍스트 변경
		$("#pageText").change(function() {
			$('#pageText').val($('#fake_textarea').html());
			console.log($('#pageText').val());
			$('.processingTextSubmit-btn').click();
			setTimeout(function() { 
				location.reload(); }, 300);
			
		});
		
	});
</script>
	<%
		session = request.getSession();
		String processingMethod = (String) session.getAttribute("processingMethod");
		String describeMethod = (String) session.getAttribute("describeMethod");
		//이미지 경로 받기
		String uploadFilePath = "";
		if(session.getAttribute("uploadFilePath") != null) uploadFilePath = (String) session.getAttribute("uploadFilePath");
		
		//이미지 속 텍스트 받기
		String pageText = (String)session.getAttribute("pageText");
		
		//세션에 저장해둔 가공 텍스트 가져오기
		ArrayList<String> sentence_list = (ArrayList<String>)session.getAttribute("sentence_list");
	 %>
	 <iframe id="iframe1" name="iframe1" style="display:none"></iframe>
	 <form>
	 	<div class="w3-row-padding w3-margin-top">
	 		<div class="w3-container w3-half w3-padding-large w3-mobile" style="text-align:center;">
		 		<div style="background-color:#C4C4C4;width:100%;">
			 		<img name="input-img" style="max-width:100%; max-height:60vh;object-fit: contain;"src="<%=uploadFilePath %>">
			 		<input type="hidden" name="pageImgUrl" value="<%=uploadFilePath %>" >
			 	</div>
		 	</div>
			 <div name="input-text" class="w3-container w3-half w3-mobile w3-margin-top" >
			 	<div id='fake_textarea' contenteditable="true"><%=pageText%></div>
			 	<input type='hidden' id='pageText' name='pageText'/>
			 </div>
			 <div class="w3-center">
			 	<img  src="./IMG/down-arrow.png" >
			 </div>
			 <div class="w3-container w3-half w3-padding-large w3-large">
			 	<input type="hidden" id="processing_method" value="<%=processingMethod %>">
			 	<input type="radio" class="w3-margin-left processing-type" name="processing-type" value="byEnter">줄 바꿈 분리
			 	<input type="radio" class="w3-margin-left processing-type" name="processing-type" value="bySpeaker"> 화자 별 분리
			 	<input type="radio" class="w3-margin-left processing-type" name="processing-type" value="byMark"> 문장 별 분리
			 </div>
			 <div>
			 	<span id="describe-type" class="w3-padding-large w3-margin-left"><%=describeMethod%></span>
			 </div>
			 <div name="processed-text" class="w3-container w3-half w3-padding-large" style="text-align:center;">
			 	<%for(int i=0; i<sentence_list.size(); i++) { %>
			 		<div id="processedText<%=i%>" class = "w3-panel w3-border w3-round-large w3-padding-16" name="processedText<%=i%>"><%=sentence_list.get(i) %></div>
			 	<%} %>
			 </div>
		 </div>
		 <div class="w3-center">
		 <button type="SUBMIT" formmethod="post" formaction="doProcessText" formtarget="iframe1" class="processingTextSubmit-btn" style="display:none;"></button>
		 <button type="submit" formmethod="post" formaction="doPrepareSetting" class="w3-button w3-padding-large" style="width:50%; background-color: #927D71;"> 다음 </button>
		 </div>
	 </form>
</body>
</html>