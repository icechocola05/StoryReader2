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
	<div class = "story-title">
			<%=currStory.getStoryTitle()%>
	</div>
	<div class="main">
		<div class="preview-page w3-row-padding">
			<div class = "page-image w3-half w3-container" style="width:50%">
				<img name="page-img" src="<%= pageImgUrl%>" style="width:80%">
			</div>
			<div class = "page-script w3-half w3-container w3-border w3-round-large" style="width:50%">
				<%for(int i=0 ; i<sentenceSet.size() ; i++){ %>
				<div id = 'sentenceset<%=i%>' class = "sentenceset w3-panel w3-border-bottom"> 
					<div class="speaker" id='speaker<%=i%>'><%= sentenceSet.get(i).getSpeaker()%></div>
					<!--voice 붙이기 -->
	                <div class="voice  w3-container w3-border w3-round-xlarge" id="voiceVal<%=i%>" style="background-color:<%=voiceColorList.get(i)%>; width:2%;">
	                	<div class="emotion" id ="emotionVal<%=i%>" >
	                        <label id="emotionFace<%=i%>" style="opacity:<%=opacityList.get(i)%>;">
	                           <span id='emotionFaceSpan<%=i%>' class='iconify' data-inline='false' data-icon='<%=emoticonNameList.get(i)%>'></span>
	                        </label>
	                     </div>
	                </div>
					<div class="sentence" id='sentence<%=i%>'><%= sentenceSet.get(i).getSentence() %></div>
				</div>
				<%}%>
			</div> 
		</div>
		<div class="audio">
			<button type="submit" id="pre_btn" onclick ="javascript:pre_clicked();">
				<img src="./Img/previous_w.png" alt="image">
			</button>
            <audio id='player' autoplay controls onended="javascript:next();">
               	<source id = "play-source">
               	<%for (int i = 0 ; i < sentenceSet.size(); i++ ){ %>
               	<source src="/output/<%=sentenceSet.get(i).getSentenceWavUrl()%>" type="audio/wav">
               	<%} %>
            </audio>
            <button type="submit" id="next_btn">
				<img src="./Img/next_w.png" alt="image">
			</button>`
         </div>
	</div>
	<form method="post" action="DoConfirmPage">
	<button>저장</button>
	</form>
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script>
		var index = 1;
		
		//이전 버튼을 눌렀을 때(onclick)
		$('#pre_btn').click(function() {
			index--;
			if(index < 1) index=2;
			console.log( index + '번째 소스 재생' );
	
			$('#player source#play-source').attr('src',
				$('#player source:nth-child('+index+')').attr('src'));
			$("#player")[0].load();
			$("#player")[0].play();
		});
		
		//음성 재생이 끝났을 때(onended) 
		$("#player").bind('ended', function(){
			index++;
			if(index > $('#player source').length) index=2;
			console.log( index + '번째 소스 재생' );
	
			$('#player source#play-source').attr('src',
				$('#player source:nth-child('+index+')').attr('src'));
			$("#player")[0].load();
			$("#player")[0].play();
		});
		
		//다음 버튼을 눌렀을 때(onclick)
		
		$('#next_btn').click(function() {
			index++;
			if(index > $('#player source').length) index=2;
			console.log( index + '번째 소스 재생' );
	
			$('#player source#play-source').attr('src',
				$('#player source:nth-child('+index+')').attr('src'));
			$("#player")[0].load();
			$("#player")[0].play();
		});
	</script>

</body>
</html>