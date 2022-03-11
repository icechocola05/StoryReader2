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
<title>내 동화 읽기</title>
</head>
<%@ include file="header.jsp" %>
<body>
	<%
		Story currStory = (Story)session.getAttribute("currStory");//동화 정보
		ArrayList<Page> currPages = (ArrayList<Page>)session.getAttribute("currPages");
		Page currPage = (Page)request.getAttribute("currPage");//페이지 정보
		ArrayList<Sentence> currSentences = (ArrayList<Sentence>)request.getAttribute("currSentences");//문장 묶음
		
		ArrayList<String> voiceColorList = (ArrayList<String>)request.getAttribute("voiceColorList");
		ArrayList<String> emoticonNameList = (ArrayList<String>)request.getAttribute("emoticonNameList");
		ArrayList<String> opacityList = (ArrayList<String>)request.getAttribute("opacityList");
	%>
	<div class="w3-container w3-center w3-margin-top w3-margin-bottom w3-padding">
		<h3 style="font-weight:bold;"><%=currStory.getStoryTitle() %></h3>
	</div>
	<div class="w3-row w3-center w3-border w3-round-xlarge w3-padding"  style="width:80%;margin-left:10%;margin-right:10%;">
		<div class="w3-col" style="width:10%; ">
			<button class="w3-button w3-circle w3-white w3-left" id="pre_btn">◀</button>
		</div>
		<div id="page-sentence" class="w3-col w3-container w3-padding" style="width:80%">
			<%for (int i=0;i<currSentences.size();i++){ %>
				<div id='speaker<%=i%>'style="max-width:10%; min-width:50px; display:none;"><%= currSentences.get(i).getSpeaker()%></div>
				<div id="voiceVal<%=i%>" class="w3-center w3-border w3-round-xlarge" style="background-color:<%=voiceColorList.get(i)%>; max-width:40px; min-width:30px; font-size:20px; display:none;">
	                	<div class="emotion w3-center" id ="emotionVal<%=i%>" style="width:100%;display:none;">
	                        <label id="emotionFace<%=i%>" style="opacity:<%=opacityList.get(i)%>;display:none;">
	                           <span id='emotionFaceSpan<%=i%>' class='iconify' data-inline='false' data-icon='<%=emoticonNameList.get(i)%>'></span>
	                        </label>
	                     </div>
	             </div>
				<div id ="sentence<%=i%>" style="display:none; min-width:80%; text-align:center;"><%=currSentences.get(i).getSentence() %></div>
			<%} %>
		</div>
		<div class="w3-col" style="width:10%">
			<button class="w3-button w3-circle w3-white w3-right" id="next_btn">▶</button>
		</div>
	</div>
	<div class="w3-row w3-center w3-margin-top w3-margin-bottom">
	<form id="read_story" action="DoReadStory" method="post">
		<input type="hidden" id="page_num" name="readPage" value="<%=currPage.getPageNum()-1%>"><!-- 현재 페이지 쪽 번호 -->
		<input type="hidden" id="page_total" name="totalPage" value="<%=currPages.size()%>"><!-- 전체 페이지 개수 -->
		<div class="w3-col w3-container w3-center" style="width:15%;min-height:30vh;max-height:60vh;display:flex;align-items:center; justify-content:right;">
			<button class="w3-button w3-circle w3-white w3-right" id="page_pre_btn" style="float:right;">◀</button>
		</div>
		<div class="w3-col w3-center" style="width:70%;">
			<img style="width:100%;max-height:60vh;" src="<%=currPage.getPageImgUrl()%>">
		</div>
		<div class="w3-col w3-container w3-center" style="width:15%;min-height:30vh;max-height:60vh;display:flex;align-items:center; ">
			<button class="w3-button w3-circle w3-white w3-left" id="page_next_btn">▶</button>
		</div>
	</form>
	</div>
	<div class="w3-container w3-margin w3-center">
	    <audio class="w3-container w3-mobile"id='player' style="width:40%" autoplay controls>
	       	<source id = "play-source">
	       	<%for (int i = 0 ; i < currSentences.size(); i++ ){ %>
	       	<source src="/output/<%=currSentences.get(i).getSentenceWavUrl()%>" type="audio/wav">
	       	<%} %>
	    </audio>
    </div>
    <script src="//code.jquery.com/jquery.min.js"></script>
	<script>
		$(window).load(function() {// 로딩되기 시작할때 -> 첫 Sentence의 display
			$("#sentence0").css("display", "inline-block");
			$("#speaker0").css("display", "inline-block");
			$("#voiceVal0").css("display", "inline-block");
			$("#emotionVal0").css("display", "inline-block");
			$("#emotionFace0").css("display", "inline-block");
		});
		
	 	var read_form = $('#read_story');
		//이전 페이지 버튼을 누를 때
		$('#page_pre_btn').click(function() {
			var page_num = $('#page_num').attr('value');
			//console.log("현재 페이지 : "+page_num);
			page_num = Number(page_num)-1;
			//console.log("변경 페이지 : "+page_num);
			if (page_num >= 0) {//page_num -> 0부터 시작
				//console.log("변경 가능 : "+page_num);
				$('#page_num').attr('value',page_num);
				read_form.submit();
			}else{
				alert("첫 페이지 입니다. ");
			}
		});
		
		//다음 페이지 버튼을 누를 때 
		$('#page_next_btn').click(function() {
			var page_num = $('#page_num').attr('value');
			//console.log("현재 페이지 : "+page_num);
			page_num = Number(page_num) + 1;
			//console.log("변경 페이지 : "+page_num);
			if (page_num < $('#page_total').attr('value')) {
				//console.log("변경 가능 : "+page_num);
				$('#page_num').attr('value',page_num);
				read_form.submit();
			}else{
				alert("마지막 페이지 입니다. ");
			}
		});
		
		
		var index = 2;
		var index_c = index-2;//sentence id 번호
		//이전 버튼을 눌렀을 때(onclick)
		$('#pre_btn').click(function() {
			if(index-1 <= 1) $("#page_pre_btn").trigger("click");
			else{
				index_c = index-2;
				$("#sentence"+index_c).css("display", "none");
				$("#speaker"+index_c).css("display", "none");
				$("#voiceVal"+index_c).css("display", "none");
				$("#emotionVal"+index_c).css("display", "none");
				$("#emotionFace"+index_c).css("display", "none");
				index--;
				index_c = index-2;
				$("#sentence"+index_c).css("display", "inline-block");
				$("#speaker"+index_c).css("display", "inline-block");
				$("#voiceVal"+index_c).css("display", "inline-block");
				$("#emotionVal"+index_c).css("display", "inline-block");
				$("#emotionFace"+index_c).css("display", "inline-block");
				console.log( index + '번째 소스 재생' );
				$('#player source#play-source').attr('src',
					$('#player source:nth-child('+index+')').attr('src'));
				$("#player")[0].load();
				$("#player")[0].play();
			}
		});
		
		//음성 재생이 끝났을 때(onended) 
		$("#player").bind('ended', function(){
			if(index+1 >= $('#player source').length) $("#page_next_btn").trigger("click");//한 페이지의 모든 문장이 재생 되었을 때
			else{
				index_c = index-2;
				$("#sentence"+index_c).css("display", "none");
				$("#speaker"+index_c).css("display", "none");
				$("#voiceVal"+index_c).css("display", "none");
				$("#emotionVal"+index_c).css("display", "none");
				$("#emotionFace"+index_c).css("display", "none");
				index++;
				console.log( index + '번째 소스 재생' );
				index_c = index-2;
				$("#sentence"+index_c).css("display", "inline-block");
				$("#speaker"+index_c).css("display", "inline-block");
				$("#voiceVal"+index_c).css("display", "inline-block");
				$("#emotionVal"+index_c).css("display", "inline-block");
				$("#emotionFace"+index_c).css("display", "inline-block");
				$('#player source#play-source').attr('src',
					$('#player source:nth-child('+index+')').attr('src'));
				$("#player")[0].load();
				$("#player")[0].play();
			}
		});
		
		//다음 버튼을 눌렀을 때(onclick)
		$('#next_btn').click(function() {
			
			if(index >= $('#player source').length) $("#page_next_btn").trigger("click");//한 페이지의 모든 문장이 재생 되었을 때
			else{
				index_c = index-2;
				$("#sentence"+index_c).css("display", "none");
				$("#speaker"+index_c).css("display", "none");
				$("#voiceVal"+index_c).css("display", "none");
				$("#emotionVal"+index_c).css("display", "none");
				$("#emotionFace"+index_c).css("display", "none");
				index++;
				console.log( index + '번째 소스 재생' );
				index_c = index-2;
				$("#sentence"+index_c).css("display", "inline-block");
				$("#speaker"+index_c).css("display", "inline-block");
				$("#voiceVal"+index_c).css("display", "inline-block");
				$("#emotionVal"+index_c).css("display", "inline-block");
				$("#emotionFace"+index_c).css("display", "inline-block");
				$('#player source#play-source').attr('src',
					$('#player source:nth-child('+index+')').attr('src'));
				$("#player")[0].load();
				$("#player")[0].play();
			}
		});
	</script>
    
</body>
</html>