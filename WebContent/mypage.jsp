<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.*"%>
<%@ page import="model.dto.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 동화 보기</title>
</head>
<%@ include file="header.jsp" %>
<style>
	#story-banner:hover{
		background-color: rgba(146, 125, 113, 0.50);
	}
	
	#editStory:hover{
		background-color: #a7d9b1;
	}
	
	#deleteStory:hover {
		background-color: #d4a3a3;
	}
</style>
<body>
	<%
		ArrayList<Story> myStory = (ArrayList<Story>)session.getAttribute("myStoryList");//내 스토리 리스트 받아오기
		ArrayList<String> myStoryImgUrl = (ArrayList<String>)session.getAttribute("myStoryListImgUrl");//내 스토리 리스트 받아오기
		for(int i=0; i<myStory.size(); i++) {
			System.out.println(myStory.get(i).getStoryTitle());
		}
		
	%>
	<div class="w3-row w3-margin">
	<%
		for (int i=0;i< myStory.size();i++){
	%>									
		<form>
			<div class = "w3-container w3-quarter" >
			<div class="w3-container w3-margin w3-border w3-round-large">
				<div class="w3-round-large" id ="story-banner" onclick="readStory(<%=i%>)">
					<div id="story-img<%=i%>" class="w3-margin-top" style="background-color:#C4C4C4" >
						<img src="<%=myStoryImgUrl.get(i)%>" style="width:100%; height:40vh; object-fit: contain;">
					</div>
					<div id="story-title<%=i%>" class="w3-center w3-padding" style="font-size:20px; font-weight:bold; margin-bottom: 2%;"><%=myStory.get(i).getStoryTitle()%></div>
					<input type="hidden" name="story_id" value="<%=myStory.get(i).getStoryId()%>">
					<button type="SUBMIT" formmethod="post" formaction="DoReadStory" id="readBtn<%=i%>" name="readBtn" style="display:none;"></button>
				</div>
				<div class=" w3-cell-row w3-border w3-round-large w3-margin-bottom" style="width:100%; height: 100%;">
					<div id="editStory" onclick="editStory(<%=i%>)" class="w3-container w3-cell w3-center w3-margin w3-padding w3-border-right" style="font-size:15px;">수정</div>
					<div id="deleteStory" onclick="deleteStory(<%=i%>)" class="w3-container w3-cell w3-center w3-margin w3-padding" style="font-size:15px;">삭제</div>
					
					<button type="SUBMIT" formmethod="post" formaction="doEditStory" id="editBtn<%=i%>" name="editBtn" style="display:none;"></button>
					<button type="SUBMIT" formmethod="post" formaction="doDeleteStory" id="deleteBtn<%=i%>" name="deleteBtn" style="display:none;"></button>
				</div>
			</div>
			</div>
		</form>
	<%}%>
	</div>
<script>


function readStory(num){
	document.getElementById("readBtn"+num).click();
}

function editStory(num) {
	document.getElementById("editBtn"+num).click();
}

function deleteStory(num) {
	if(confirm("동화를 삭제하시겠습니까?") == true) {
		document.getElementById("deleteBtn"+num).click();
	} else {
		return false;
	}
	
}
</script>
</body>
</html>