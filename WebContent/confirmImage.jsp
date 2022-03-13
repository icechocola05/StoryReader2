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
		
		//radio 값 유지
		
		method = $("#processing_method").val();
		$("input:radio[name='processing-type']").each(function(index, element) {
			//console.log($(element).val());
			if(method == $(element).val()) {
				console.log(method);
				$(element).attr('checked', true);
			}
    		//pageIndex[index] = element.value;
    		//console.log($(element).children('input:eq(1)').val());
    		//$(element).children('input:eq(1)').val(index+1);
    		//console.log(pageIndex);
    	});
		
		
		//method = $("#processing_method").val();
		//$('input:radio[name="processing-type"][value=method]').prop('checked', true);
		//console.log(method);
		//$('#myform input:radio[name=myradio]:input[value=myvalue1]').attr('checked',true);
		
		// radio 값 변경
		$("input[name='processing-type']").change(function() {
			$('.processingTextSubmit-btn').click();
			setTimeout(function() { 
				location.reload(); }, 300);
		});
		
		//텍스트 변경
		$("#pageText").change(function() {
			$('.processingTextSubmit-btn').click();
			setTimeout(function() { 
				location.reload(); }, 300);
			
		});
		
	});
</script>
	<%
		session = request.getSession();
		String processingMethod = (String) session.getAttribute("processingMethod");
		System.out.println("jsp: " + processingMethod);
		//이미지 경로 받기
		String uploadFilePath = "";
		if(session.getAttribute("uploadFilePath") != null) uploadFilePath = (String) session.getAttribute("uploadFilePath");
		
		//이미지 속 텍스트 받기
		String pageText = (String)session.getAttribute("pageText");
		
		//세션에 저장해둔 가공 텍스트 가져오기
		ArrayList<String> sentence_list = (ArrayList<String>)session.getAttribute("sentence_list");
		//String fullSentence = "";
		//for(int i=0; i<sentence_list.size(); i++) {
			//fullSentence = fullSentence + sentence_list.get(i) + "\n";
			//System.out.println(fullSentence);
		//}
	 %>
	 <iframe id="iframe1" name="iframe1" style="display:none"></iframe>
	 <form>
	 	<div class="w3-row-padding w3-margin-top">
	 		<div class="w3-container w3-half w3-padding-large" style="text-align:center;">
		 		<img name="input-img" style="object-fit: contain;"src="<%=uploadFilePath %>">
		 		<input type="hidden" name="pageImgUrl" value="<%=uploadFilePath %>" >
		 	</div>
			 <div name="input-text" class="w3-container w3-half w3-padding-large" style="text-align:center;">
			 	<textarea id="pageText" class = "w3-round-large w3-padding" name="pageText" cols="50" rows="10" style="width:90%;"><%=pageText%></textarea>
			 </div>
			 <div class="w3-container w3-half w3-padding-large">
			 	<input type="hidden" id="processing_method" value="<%=processingMethod %>">
			 	<input type="radio" class="processing-type" name="processing-type" value="byEnter">줄 바꿈 분리
			 	<input type="radio" class="processing-type" name="processing-type" value="bySpeaker"> 화자 별 분리
			 	<input type="radio" class="processing-type" name="processing-type" value="bySentence"> 문장 별 분리
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