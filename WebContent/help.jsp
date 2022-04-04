<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Help</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="CSS/base.css" type='text/css' >
<style>
.menu-active{
	background-color:#C4C4C4;
}
</style>
</head>

<body>
<div class="w3-container w3-center">
  <h1>  도  움  말  </h1>
</div>

<!-- Side bar -->
<div class="menu w3-sidebar w3-light-grey w3-bar-block" style="width:20%">
  <h4 class="w3-bar-item">Menu</h4>
  <a href="#intro"  class="w3-bar-item w3-button">서비스 소개</a>
  <a href="#usage" class="w3-bar-item w3-button">사용 방법</a>
  <a href="#makeStory" class="w3-bar-item w3-button">&nbsp;&nbsp;동화 만들기</a>
  <a href="#inputTitle" class="w3-bar-item w3-button">&nbsp;&nbsp;&nbsp;&nbsp;동화 제목 입력</a>
  <a href="#uploadPage" class="w3-bar-item w3-button">&nbsp;&nbsp;&nbsp;&nbsp;페이지 등록</a>
  <a href="#modifyPage" class="w3-bar-item w3-button">&nbsp;&nbsp;&nbsp;&nbsp;페이지 수정</a>
  <a href="#removePage" class="w3-bar-item w3-button">&nbsp;&nbsp;&nbsp;&nbsp;페이지 삭제</a>
  <a href="#myStory" class="w3-bar-item w3-button">&nbsp;&nbsp;&nbsp;&nbsp;만든 동화 확인</a>
  <a href="#readStory" class="w3-bar-item w3-button">&nbsp;&nbsp;동화 읽기</a>
  <a href="#modifyStory" class="w3-bar-item w3-button">&nbsp;&nbsp;동화 수정</a>
  <a href="#removeStory"  class="w3-bar-item w3-button">&nbsp;&nbsp;동화 삭제</a>
</div>

<!-- Content -->
<div style="margin-left:25%; margin-right:5%;">
<div id="intro" class="w3-container" style="background-color:#E0E0E0;">
  <h3> <b>서비스 소개</b> </h3>
</div>
<div class="w3-container scroll">
  <p>이미지로부터 텍스트를 추출하고, 감정과 음색을 다양하게 설정하여 동화책을 읽어주는 웹 서비스입니다. 
	원하는 동화책의 사진을 찍고, 화자와 감정을 설정하여 동화를 읽어보세요.</p>
  <img src = "IMG/0_main.JPG" style="width:50vw;" class="w3-container w3-center">
</div>

<br><br>
<div id ="usage" class="w3-container usage scroll" style="background-color:#E0E0E0;">
  <h3><b>사용 방법</b></h3>
</div><br>
<div id="makeStory" class="w3-container" style="background-color:#F0F0F0;">
  <h3><b>동화 만들기</b></h3>
</div>
<div id="inputTitle" class="w3-container">
  <h4>1. 동화 제목 입력</h4>
  <p>원하는 동화 제목을 입력하세요. </p>
  <img src = "IMG/2_input_story_title.JPG" style="width:50vw;" class="w3-container w3-center">
</div>
<br><br><br><br>
<div id="uploadPage" class="w3-container">
  <h4>2. 페이지 등록</h4>
  <p>1) '➕'버튼을 눌러 동화의 페이지를 등록하세요. </p>
  <img src = "IMG/3_make_page.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
  <p>2) 페이지의 사진을 등록하세요.   </p>
  <img src = "IMG/4_upload_img.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
  <p>3) 사진에서 추출된 문장들을 확인하세요. 문장을 원하는 단위 별로 선택하여 끊을 수 있습니다. 이렇게 분리된 문장은 화자와 감정을 설정하는 단위가 됩니다.  </p>
  <img src = "IMG/5_0_img_text.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
  <p>4) 문장에 적합한 화자, 음색, 감정을 설정합니다. 화자는 해설로 기본 설정되며, 텍스트 박스를 클릭하여 화자 이름을 수정할 수 있습니다.</p>
  <img src = "IMG/6_0_setting.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
  <p>화자에 적합한 음색을 선택하고, 색을 통해 음색을 확인하세요. </p>
  <img src = "IMG/6_1_setting.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
  <p>감정을 변경하고, 이모티콘의 표정을 통해 감정을 확인할 수 있습니다. 문장의 음색과 감정을 설정하고 [재생] 버튼을 누르면 미리 듣기가 가능합니다.</p>
  <img src = "IMG/6_2_setting.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
  <p>5) 저장 전, 페이지 미리 보기를 통해 설정 내용을 확인합니다. [저장] 버튼을 누르면 페이지가 생성됩니다. </p>
  <img src = "IMG/7_0_preview_page.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
  <p>6) 페이지가 완성되면 다음과 같은 화면을 볼 수 있습니다. </p>
  <img src = "IMG/7_1_store_page.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
</div>
<br>
<div id="modifyPage" class="w3-container">
  <h4>3. 페이지 수정</h4>
  <p>1)  페이지 순서를 변경하고 싶을 때<br>
  순서를 변경하고 싶은 페이지를 드래그 하여 원하는 위치에 놓습니다.</p>
  <div class="" style="display:inline-block;width:100%;"><img src = "IMG/8_1_page_order.JPG" style="width:47%;" class="w3-container w3-center">
  <img src = "IMG/8_2_change_page_order.JPG" style="width:47%;" class="w3-container w3-center"></div>
  <br><br><br>
  <p>2) 페이지의 설정을 변경하고 싶을 때<br>
원하는 페이지를 클릭하여 페이지의 설정 내용을 확인할 수 있습니다.</p>
  <div class="" style="display:inline-block;width:100%;"><img src = "IMG/9_1_page_list.JPG" style="width:47%;" class="w3-container w3-center">
  <img src = "IMG/9_2_check_page.JPG" style="width:47%;" class="w3-container w3-center"></div>
  <br><br><br>
  <p>[수정] 버튼을 클릭하여 페이지에 대한 음색과 감정 정보를 재 설정합니다. </p>
  <img src = "IMG/10_modify_page.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
</div>
<br><br>
<div id = "removePage" class="w3-container">
  <h4>4. 페이지 삭제</h4>
  <p>'x' 버튼을 클릭하여 페이지를 삭제할 수 있습니다. </p>
  <img src = "IMG/11_remove_page.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
</div>
<div id = "myStory"class="w3-container">
  <h4>5. 만든 동화 확인</h4>
  <p>동화가 완성되면, [만든 동화 보기]를 클릭하여 내가 만든 동화를 확인할 수 있습니다. </p>
  <img src = "IMG/12_my_story_list.JPG" style="width:50vw;" class="w3-container w3-center">
  <br><br><br>
</div>
<br><br>
<div id="readStory" class="w3-container"style="background-color:#F0F0F0;">
  <h3><b>동화 읽기</b></h3>
</div>
<div class="w3-container">
  <p> 설정한 음색과 감정에 따라 한 문장씩 읽어줍니다. <br>문장을 넘기려면 상단 화살표, 페이지를 넘기려면 하단 화살표를 누르세요. </p>
  <img src = "IMG/13_read_story.JPG" style="width:50vw;" class="w3-container w3-center">
  <br>
</div>
<br><br>
<div  id = "modifyStory" class="w3-container"style="background-color:#F0F0F0;">
  <h3><b>동화 수정</b></h3>
</div>
<div class="w3-container">
  <p>[수정]버튼을 눌러 동화의 페이지를 확인하고 수정할 수 있습니다.</p>
<div class="" style="display:inline-block;width:80%;">
	<img src = "IMG/14_modify_story.JPG" style="width:47%;" class="w3-container w3-center">
  <img src = "IMG/15_modify_story.JPG" style="width:47%;" class="w3-container w3-center"></div>
  <br>
<br><br>
</div>
<div  id = "removeStory" class="w3-container"style="background-color:#F0F0F0;">
  <h3><b>동화 삭제</b></h3>
</div>
<div class="w3-container">
  <p>[삭제]버튼을 눌러 동화를 삭제하세요.</p>
<div class="" style="display:inline-block;width:80%;">
	<img src = "IMG/16_remove_story.JPG" style="width:47%;" class="w3-container w3-center">
  <img src = "IMG/16_1_remove_story.JPG" style="width:47%;" class="w3-container w3-center"></div>
  <br>
<br><br>
</div>
</div>
<script type="text/javascript">
intro= $('#intro').position().top;
usage= $('#usage').position().top;
makeStory= $('#makeStory').position().top;
inputTitle= $('#inputTitle').position().top;
uploadPage= $('#uploadPage').position().top;
modifyPage= $('#modifyPage').position().top;
removePage= $('#removePage').position().top;
readStory= $('#readStory').position().top;
modifyStory= $('#modifyStory').position().top;

$(window).scroll(function() {
var scroll = $(window).scrollTop();

if (scroll < intro) 
{
  $("#m1").addClass("menu-active");
  $("#m2").removeClass("menu-active");
} 
else if (intro <= scroll && scroll< usage) 
{
  $("#m1").removeClass("menu-active");
  $("#m2").addClass("menu-active");
  $("#m3").removeClass("menu-active");
} 
else if (usage <= scroll && scroll < makeStory) 
{
  $("#m2").removeClass("menu-active");
  $("#m3").addClass("menu-active");
  $("#m4").removeClass("menu-active");
}
else if (contact <= scroll)
{
  $("#header-menu-history").removeClass("menu-active");
  $("#header-menu-contact").addClass("menu-active");
}
</script>
</body>
</html>