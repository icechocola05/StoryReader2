<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@ page import="model.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 미리보기</title>

</head>
<%@ include file="header.jsp" %>
<body>
	<%
		Story currStory = (Story)session.getAttribute("currStory");
		ArrayList<Sentence> sentenceSet = (ArrayList<Sentence>)session.getAttribute("sentenceSet");
		ArrayList<String> voiceColorList = (ArrayList<String>)request.getAttribute("voiceColorList");
		ArrayList<String> emoticonNameList = (ArrayList<String>)request.getAttribute("emoticonNameList");
		ArrayList<String> opacityList = (ArrayList<String>)request.getAttribute("opacityList");
		String pageImgUrl = (String)session.getAttribute("currPageImg");
		int size = sentenceSet.size()-1;
	 %>
	<div class="main w3-row-padding w3-margin">
		<div class="preview-page w3-row-padding">
			<div class = "page-image w3-container w3-half" style="text-align:center;">
				<img name="page-img" src="<%= pageImgUrl%>" style="width:80%;height:50vh;">
			</div>
			<div class = "page-script w3-container w3-half w3-center w3-padding-large w3-border w3-round-large" style="text-align:center;">
				<%for(int i=0 ; i<sentenceSet.size() ; i++){ %>
				<div id = 'sentenceset<%=i%>' class = "sentenceset w3-row w3-border-bottom w3-center w3-margin-top w3-margin-bottom w3-padding-bottom" style="width:100%;"> 
					<div class="speaker w3-col s4" id='speaker<%=i%>'style="width:10%;"><%= sentenceSet.get(i).getSpeaker()%></div>
					<!--voice 붙이기 -->
	                <div class="voice w3-col w3-border w3-round-xxlarge w3-center" id="voiceVal<%=i%>" style="background-color:<%=voiceColorList.get(i)%>; width:5%;">
	                	<div class="emotion w3-center" id ="emotionVal<%=i%>" style="width:100%;">
	                        <label id="emotionFace<%=i%>" style="opacity:<%=opacityList.get(i)%>;">
	                           <span id='emotionFaceSpan<%=i%>' class='iconify' data-inline='false' data-icon='<%=emoticonNameList.get(i)%>'></span>
	                        </label>
	                     </div>
	                </div>
					<div class="sentence w3-col s4 w3-round-large" id='sentence<%=i%>' style="width:85%;"><%= sentenceSet.get(i).getSentence() %></div>
				</div>
				<%}%>
			</div> 
		</div>
		<div class="audio w3-row w3-container w3-margin-top w3-margin-bottom" >



            <div class="w3-col w3-container w3-center" style="width:25%">
            	<img class="w3-button w3-hover-white" id="next_btn"  src="./IMG/next_w.png" >
            </div>
         </div>
	</div>
	<form method="post" action="DoConfirmPage">
	<div class="w3-center">
		 <button type="submit" class="w3-button w3-padding-large" style="width:50%; background-color: #927D71;"> 저장 </button>
	</div>
	</form>
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script>
		var index = 2;
		var index_c = index-2;
		
		$(document).ready(function() { 
			$("#sentence0").css("background-color", "#F0F0F0");
			}
		);
		
		//이전 버튼을 눌렀을 때(onclick)
		$('#pre_btn').click(function() {
			index_c = index-2;
			$("#sentence"+index_c).css("background-color", "#FFFFFF");
			index--;
			if(index < 1) index=2;
			console.log( index + '번째 소스 재생' );
			index_c = index-2;
			$("#sentence"+index_c).css("background-color", "#F0F0F0");
			$('#player source#play-source').attr('src',
				$('#player source:nth-child('+index+')').attr('src'));
			$("#player")[0].load();
			$("#player")[0].play();
		});
		
		//음성 재생이 끝났을 때(onended) 
		$("#player").bind('ended', function(){
			index_c = index-2;
			$("#sentence"+index_c).css("background-color", "#FFFFFF");
			index++;
			if(index > $('#player source').length) index=2;
			console.log( index + '번째 소스 재생' );
			index_c = index-2;
			$("#sentence"+index_c).css("background-color", "#F0F0F0");
			$('#player source#play-source').attr('src',
				$('#player source:nth-child('+index+')').attr('src'));
			$("#player")[0].load();
			$("#player")[0].play();
		});
		
		//다음 버튼을 눌렀을 때(onclick)
		
		$('#next_btn').click(function() {
			index_c = index-2;
			$("#sentence"+index_c).css("background-color", "#FFFFFF");
			index++;
			if(index > $('#player source').length) index=2;
			console.log( index + '번째 소스 재생' );
			index_c = index-2;
			$("#sentence"+index_c).css("background-color", "#F0F0F0");
			$('#player source#play-source').attr('src',
				$('#player source:nth-child('+index+')').attr('src'));
			$("#player")[0].load();
			$("#player")[0].play();
		});
	</script>

</body>
</html>