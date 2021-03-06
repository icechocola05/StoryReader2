<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="model.dao.*"%>
<%@ page import="model.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<title>동화 만들기 : 페이지 추가</title>
<link rel="stylesheet" href="CSS/makeStory.css" type='text/css' >
</head>
<%@ include file="header.jsp" %>
<body>
<script type="text/javascript">

	$(function() {
		
		// 제목 변경
		$("#storyTitle").change(function() {
			$('#storyTitleSubmit_btn').click();
		})
		
		// 페이지 드래그 앤 드롭
		var pageIndex = [];
		$(".sortable").sortable({
			connectWith: ".connectedSortable",
			
			//드래그 할 때만 해당 page 색 변경
		    start: function(e,ui){
		        ui.item.children(".sort").css('background-color', 'rgba(146, 125, 113, 0.85)');
		    },
		    stop: function(e,ui){
		    	ui.item.children(".sort").css('background-color', 'rgba(255, 255, 255, 0)');
		    	$(".sortable li").each(function(index, element) {
		    		pageIndex[index] = element.value;
		    		console.log($(element).children('input:eq(1)').val());
		    		$(element).children('input:eq(1)').val(index+1);
		    		console.log(pageIndex);
		    	});
		    	//순서 변경 DB 적용
		    	$(".storyPageSubmit-btn").click();
		    }
		});
		
		//페이지 삭제
		$(".delete_page").each(function(index, element) {
			$(element).click(function() {
				console.log($(element));
				if(confirm("페이지를 삭제하시겠습니까?") == true) {
					$(element).children('input').val(1);
					console.log($(element).children('input').val());
					$(".storyPageDelete-btn").click();
				}
				else {
					return;
				}
			});
		});
		
		$(".sortable").disableSelection();
		
		//페이지 선택 -> 미리 보기
		$(".page-click").click(function() {
			$('#selectedPageId').attr('value',
					$("div.sort").children('input.selectPageId').val());
			console.log($("div.sort").children('input.selectPageId').val());
			console.log($('#selectedPageId').attr('value'));
			$("#previewSelectedPage_btn").click();
		});
		
		//동화 완성 눌렀을 때 페이지 존재하는지 확인하기		
		$(".checkPageExist").click(function() {
			if($('.pageId').length == 0){ 
				//페이지 존재하지 않는 경우
				alert("페이지를 추가해주세요!");
			}
			else {
				$(".storySubmit-btn").click();
			}
		});
		
	});
	
</script>
	<%
		//session에 저장해 둔 currStory, pageList 가져오기
		session = request.getSession();
		Story currStory = (Story)session.getAttribute("currStory");
		String storyTitle = currStory.getStoryTitle();
		
		ArrayList<Page> pageList = (ArrayList<Page>)session.getAttribute("pageList");
		
		//doMakeFullStory 에서 사용할 index
		int pageIndex = 1;
		session.setAttribute("pageIndex", pageIndex);
		
	%>
	
	<div class="settings" style="margin: 3% 1% 2% 1%;">
	<iframe id="iframe1" name="iframe1" style="display:none"></iframe>
	<iframe id="iframe2" name="iframe2" style="display:none"></iframe>
	<iframe id="iframe3" name="iframe3" style="display:none"></iframe>
	<div class="w3-display-container w3-margin-top w3-row w3-center" style="width: 40%; margin-left: 30%;">
		<form class="w3-display-container w3-margin-top w3-row w3-center"  action="doChangeStoryTitle" target="iframe1">
			<input type="text" class="w3-input w3-xlarge w3-margin-top w3-center" id="storyTitle" name="storyTitle" value="<%=storyTitle%>" placeholder="동화 제목을 입력해주세요">
			<button type="submit" id="storyTitleSubmit_btn" style="display: none;"></button>
		</form>
	</div>
	<form method="post" action="DoPreviewSavePage">
		<input type="hidden" id="selectedPageId" name="selectedPageId" value="">
		<button type="submit" id="previewSelectedPage_btn" style="display: none;"></button>
	</form>
	<form class="container w3-display-container w3-margin-top w3-row w3-center">
	<ul class="sortable">
	<% 
		if(pageList != null) {
			int pageSize = pageList.size();
			for(int i=0; i<pageSize; i++) { 
				String pageImgUrl = pageList.get(i).getPageImgUrl();
				String pageSentence = pageList.get(i).getPageSentence();
				int pageId = pageList.get(i).getPageId();
				int pageIndexJquery = i + 1;
	%>
	<li id="pageId<%=pageIndexJquery%>">
		<input type="hidden" class="pageId" name="pageId<%=pageIndexJquery%>" value="<%=pageId%>">
		<input type="hidden" class="changedNum" name="changedNum<%=pageIndexJquery%>" value="<%=pageIndexJquery%>">
		<div class="w3-container sort" style="border:2px solid #C4C4C4; border-radius:20px; margin-bottom: 2%; ">
			<input type="hidden" class="selectPageId" name="selectPageId<%=pageIndexJquery%>" value="<%=pageId%>">
			<div class="w3-row w3-center">
				<div class="page-click w3-col w3-cell-middle" style="margin: 2% 1% 1% 3%; width: 10%;">
					<img name="page-img" src="<%=pageImgUrl%>" style="width:50%; height: 20%; object-fit:cover">
				</div>
				<div class="page-click w3-col w3-cell-middle w3-xlarge w3-left-align" style="margin: 1% 1% 1% 5%; width: 63%;">
					<%=pageSentence%>
				</div>
				<div class="w3-col w3-cell-middle w3-xlarge" style="margin: 3% 1% 1% 5%; width: 10%;">
					<div class="delete_page">
						<img class="w3-button w3-hover-white" style="width:70%;" src="./IMG/delete.png" >
						<input type="hidden" class="deleteFlag" name="deleteFlag<%=pageIndexJquery%>" value="0">
					</div>
				</div>
			</div>
		</div>
	</li>
	<%		}
		}
	%>
	</ul>
	<br>
	<div class="w3-container" style="border:2px solid #C4C4C4; border-radius:20px; margin-bottom: 2%; padding:1% 3% 1% 1%; ">
		<a onclick="location='uploadImage.jsp'">
			<img class="w3-button w3-hover-white" id="plus_page" style="width:10%;" src="./IMG/plus.png" >
		</a>
	</div>
	
	<div class="btn">
	   <button type="SUBMIT" formmethod="post" formaction="doChangeStoryPageOrder" formtarget="iframe2" class="storyPageSubmit-btn" style="display:none;"></button>
	   <button type="SUBMIT" formmethod="post" formaction="doDeleteStoryPage" class="storyPageDelete-btn" style="display:none;"></button>
	   <button type="SUBMIT" formmethod="post" formaction="doMakeFullStory" class="storySubmit-btn" style="display:none;"></button>
       <button type="BUTTON" class="checkPageExist"> 동화 완성  </button>
    </div>
    </form>
    </div>
	
</body>
</html>