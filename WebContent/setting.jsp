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
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<title>설정</title>
<link rel="stylesheet" href="CSS/setting.css" type='text/css' >
</head>
<%@ include file="header.jsp" %>
<body>
   
   <% 
      //저장한 이야기, 문장, 화자 정보 받아오기
      ArrayList<String> speaker_list = (ArrayList<String>) request.getAttribute("speaker_list");
      ArrayList<String> sentence_list = (ArrayList<String>) request.getAttribute("sentence_list");
      
      for(int i=0; i<speaker_list.size(); i++) {
    	  System.out.println(speaker_list.get(i));
      }
      
      //DB의 Emotion, Voice 가져오기 + session에 저장 -> index.jsp에서 처리 -> 가져옴
      List<Voice> voiceSet = (List<Voice>)session.getAttribute("voiceSet");
      List<Emotion> emotionSet = (List<Emotion>)session.getAttribute("emotionSet");
      System.out.println(emotionSet.get(0).getEmotionNameKr());
      
      //voice color 배열 만들기
      String voiceColorSet[] = new String[10];
      for(int i=0; i<voiceSet.size(); i++) {
         String color = voiceSet.get(i).getVoiceColor().substring(1,7);
         voiceColorSet[i] = color;
      }
      session.setAttribute("voiceColorSet", voiceColorSet);
     
      //js로 보낼 color 배열 생성
      StringBuffer colorBuff = new StringBuffer();
      for(int i=0; i<voiceSet.size(); i++) {
         if(voiceColorSet[i] == null) break;
         colorBuff.append(voiceColorSet[i]);
         colorBuff.append(",");
      }
      colorBuff.deleteCharAt(colorBuff.lastIndexOf(","));
      
      //js로 보낼 speakerNum 2차원 배열 생성
      StringBuffer voiceBuff = new StringBuffer();
      for(int i=0; i<speaker_list.size(); i++) {
    	 if(speaker_list.get(i) == null) break;
    	 voiceBuff.append(speaker_list.get(i));
    	 voiceBuff.append(",");
      }
      voiceBuff.deleteCharAt(voiceBuff.lastIndexOf(","));
   %>

   <form method="Post" action="setVoiceEmotion">
   <!-- sentence setting -->
   <div class="settings" style="margin: 3% 0 0 0;">
      <br>
      <%
         //문장 수 만큼 div 생성
         int len = sentence_list.size(); 
         for(int i=0; i<len; i++) { 
      %>
         
      <div class="w3-container" style="border:2px solid #C4C4C4; border-radius:20px; margin-bottom: 2%;">
         <div class="w3-row w3-center">
            <div class="w3-col w3-xlarge" style="color: #3A91DA; font-weight: bold; margin: 5% 0 0 2%; width: 15%;">
               <!-- speaker 붙이기-->
               <input type="text" id='speaker<%=i%>' value="<%= speaker_list.get(i) %>" placeholder="화자" style="color: #3A91DA; font-weight: bold; text-align: center; width: 90%;"> <br>
               
               <!-- voice option 붙이기-->
               <select class='w3-select w3-large w3-margin-bottom' id='voice<%=i%>' name='voice<%=i%>' onchange="changeVoice(this.value, <%=i%>);" style="font-weight: bold; text-align: center; width: 90%;">
                  <% for(int ls=0; ls<voiceSet.size(); ls++) { // value 뒤에 붙은 ls로 몇번째 voice인지 판별%>
                     <option value= <%=voiceSet.get(ls).getVoiceName() + ls%> style="background-color: #<%=voiceColorSet[ls]%> "> <%=voiceSet.get(ls).getVoiceNameKr()%> </option>
                  <% } %>
               </select>
            </div>
            
            <div class="w3-col w3-cell-middle" style="margin: 1% 3% 1% 3%; width: 8%;">
               <!-- voice 형태 붙이기 -->
               <div class="voiceColor<%=i%>" id="voiceColor<%=i%>" style="border:3px solid #EF9CA1; border-radius: 35%; background-color: #EF9CA1; width: 150%; height: 180%p; padding-top: 10%;" > 
                  <input type="text" style="display:none;" id ="voiceVal<%=i%>" name="voiceVal<%=i%>" value="ema&nea">
                  <!-- emotion 붙이기-->
                  <div class="w3-center w3-cell-middle" style="margin: 1%;">
                     <label id="emotionFace<%=i%>"  style="opacity: 70%;">
                        <span id='emotionFaceSpan<%=i%>' class='iconify' data-inline='false' data-icon='noto:neutral-face'></span>
                     </label> <br>
                     <select class='w3-select w3-margin-bottom' id='emotion<%=i%>' name='emotion<%=i%>' onchange="changeEmotion(this.value)" style="width: 50%; text-align: center; margin-bottom: 10%;">
                              <% for (int ls=0; ls<emotionSet.size(); ls++)  {  // value 뒤에 붙은 i로 몇번째 emotion인지 판별 %> 
                                 <option value=<%= emotionSet.get(ls).getEmotionName() + i%>><%=emotionSet.get(ls).getEmotionNameKr() %></option>
                              <% } %>
                     </select>
                         <input type="text" style="display:none;" id ="emotionVal<%=i%>" name="emotionVal<%=i%>" value="neutral">
                     
                  </div>
               </div>
            </div>
            
            <!-- emotion intensity 붙이기-->
            <div class="w3-col w3-display-container" style="margin: 8% 1% 0 2%; width: 15%">
               <input type="range" class="w3-display-middle" name="intensity<%=i%>" min="0" max ="1" step="0.1" value="0.5" onchange="changeIntensity(this.value, <%=i%>)">
               <input type="text" style="display:none;" id ="intensityVal<%=i%>" name="intensityVal<%=i%>" value="0.5">
            </div>
            
            <!-- sentence 붙이기-->
            <div class="w3-col" style="margin: 5% 0% 0% 0%; width: 40%">
               <textarea id="sentence<%=i%>" class="w3-col s12 w3-large" name="sentence<%=i%>"><%= sentence_list.get(i) %></textarea>
            </div>
            
            <!-- 미리듣기 버튼 붙이기 -->
            <div class="w3-col w3-cell-middle" style="margin: 6% 0 0 1%; width: 10%;">
               <button type="button" id="pre-listen" value="미리듣기" onclick="getPreListen(<%=i%>); return false;">
                  <img id="pre-listen-img" src="./IMG/play-button.png" alt="image">
               </button>
            </div>
         </div>
      </div>
         <% } //for문: 문장 수 %>
       </div>
         
      <div class="w3-center w3-cell-middle">
         <div class="audio" style="width: 100%;" >
            <audio id='player' autoplay controls style="width: 100%">
                  <source id = "pre-listen-audio" src="" type="audio/wav;">
            </audio>
         </div>
      </div>
      
      <div class="btn">
         <button type="SUBMIT" class="submit-btn"> 다음 단계로 >  </button>
      </div>
   </form>
   <br>
   
   <script>
      function changeVoice(val, tar) {
         //tar = speaker 인덱스
         var valNum = parseInt(val.charAt(val.length - 1)); // 몇번째 화자인지
         var colorStr = "<%=colorBuff.toString()%>";
         var colors = colorStr.split(","); // JS에서 사용할 color 배열
         var voiceStr = "<%=voiceBuff.toString()%>";
         var voices = voiceStr.split(","); // JS에서 사용할 voice 배열
         
         var element = document.getElementById("voiceColor" + tar);
         var target = document.getElementById("voiceVal" + tar);
         
         //색 바꾸기
         element.style.borderColor='#' + colors[valNum];
         element.style.backgroundColor='#' + colors[valNum];
         var target = document.getElementsByName('voiceVal' + now);
      }
      
      function changeEmotion(val) {
         var valNum = 0; // option 인덱스 - parseInt(val.charAt(val.length - 1));
         var valTimes = 1; // 자리수 산정하는 변수 : 1->10->100
         var sliceVal = 0; //slice 하려는 대상

         while(true){
        	 
        	 console.log(val.slice(-1));
        	 sliceVal = parseInt(val.slice(-1));
        	 
        	 if (sliceVal>=0&& sliceVal<=9){
        		 valNum = valNum + (sliceVal * valTimes);
        		 val = val.slice(0, -1);
        		 valTimes=valTimes*10;
             }else{
            	 break;
             }
         }
         
         var element = document.getElementById("emotionFace" + valNum);
         var target = document.getElementById("emotionVal" + valNum);
         
         var deleteElement = document.getElementById('emotionFaceSpan' + valNum);
         deleteElement.parentNode.removeChild(deleteElement);
            
         if(val == "neutral") {
         	var added = document.createElement('span');
            added.setAttribute('id', 'emotionFaceSpan' + valNum);
            added.setAttribute('class', 'iconify');
            added.setAttribute('data-inline', 'false');
            added.setAttribute('data-icon', 'noto:neutral-face');
            element.appendChild(added);
            target.value = val;
         }
         if(val == "happiness") {
            var added = document.createElement('span');
            added.setAttribute('id', 'emotionFaceSpan' + valNum);
            added.setAttribute('class', 'iconify');
            added.setAttribute('data-inline', 'false');
            added.setAttribute('data-icon', 'noto:grinning-face-with-smiling-eyes');
            element.appendChild(added);
            target.value = val;
          }
          if(val == "anger") {
            var added = document.createElement('span');
            added.setAttribute('id', 'emotionFaceSpan' + valNum);
            added.setAttribute('class', 'iconify');
            added.setAttribute('data-inline', 'false');
            added.setAttribute('data-icon', 'noto:angry-face');
            element.appendChild(added);
            target.value = val;
          }
          if(val == "sadness") {
            var added = document.createElement('span');
            added.setAttribute('id', 'emotionFaceSpan' + valNum);
            added.setAttribute('class', 'iconify');
            added.setAttribute('data-inline', 'false');
            added.setAttribute('data-icon', 'noto:crying-face');
            element.appendChild(added);
            target.value = val;
          }
      }
      
      function changeIntensity(val, valNum) {
         var element = document.getElementById('emotionFace' + valNum);
          var target = document.getElementsByName('intensityVal' + valNum);
          if(val >= 0.1 && val <= 0.3) {
             element.style.opacity = "20%";
          }
          else if(val >= 0.4 && val <= 0.7) {
            element.style.opacity = "70%";
          }
          else if(val >= 0.8) {
            element.style.opacity = "100%";
         }
         target.value = val.toString();
      }

      
      function getPreListen(val){
          const xhttp = new XMLHttpRequest();
          var sentence = document.getElementById('sentence'+val).value;
          var voice_name = document.getElementById('voiceVal'+val).value;
          var emotion_name = document.getElementById('emotionVal'+val).value;
          var emotion_intensity = document.getElementById('intensityVal'+val).value;
          var json_req_obj = {sentence : sentence, voice_name : voice_name, emotion_name : emotion_name, intensity : emotion_intensity.toString()};
          alert(JSON.stringify(json_req_obj));
          console.log(val);
          
          console.log(document.getElementById('voiceVal'+val).value);
          console.log(document.getElementById('emotionVal'+val).value);
          console.log(document.getElementById('intensityVal'+val).value);
          
          console.log("sentence="+sentence+"&voice_name="+voice_name+"&emotion_name="+emotion_name+"&intensity="+emotion_intensity.toString());
            xhttp.onreadystatechange = function () {
              if (xhttp.readyState == 4 && xhttp.status == 200) {
                console.log(xhttp.responseText);
                   document.getElementById("pre-listen-audio").src = "pre/"+xhttp.responseText;
                document.getElementById('player').load();
              }
          };
          xhttp.open("POST", "./GetPreListen", true);
          xhttp.setRequestHeader("Content-type", "application/json");
          xhttp.send(JSON.stringify(json_req_obj));
       }
   </script>

</body>
</html>