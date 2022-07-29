
# 📖 StoryReader_Plus 📖
이미지로부터 텍스트를 추출하는 Google Vision API와 감정과 음색을 다양하게 설정할 수 있는 TTS를 이용하여 동화책을 읽어주는 웹 서비스입니다. 
원하는 동화책의 사진을 찍고, 화자와 감정을 설정하여 동화를 읽어보세요.

[이용 가이드🕹](https://www.notion.so/4aff97ea916a468c9fa8bf2f8122e521)
<br>
<div align="center"><img src="https://user-images.githubusercontent.com/68148196/181388234-f6bd1dcb-e5ef-4d8f-8959-5a483f62a67b.png" width=700 height=350></div>

## 🚀 Contributor
| 이성민 | 이지원 |
| :------: | :------: |
| <div align="center"><img src="https://user-images.githubusercontent.com/59546983/181454691-721ae0f0-3d8f-454d-a36a-d436949dcb46.png" width=200 height=200 ></div> | <div align="center"><img src="https://user-images.githubusercontent.com/68148196/181294932-e130fac7-02ef-416d-a879-96dae785a061.png" width=200 height=200 ></div> |
| [@icechocola05](https://github.com/icechocola05) | [@jioniy](https://github.com/jioniy) |
| 기획, DB 설계, TTS 연동, 웹 개발, 배포 | 기획, DB 설계, Google Vision API 연동, <br> DBCP 연동, 웹 개발, 배포 |
<br>

## ✏ 핵심 기능 
✔ 동화 페이지 별 등록

✔ 동화 페이지 사진 등록 시 문장 추출

✔ 문장 별 화자(음색), 감정 설정

✔ 설정한 목소리와 감정으로 동화 낭독

✔ 동화 저장 및 수정/삭제

<!--
✔ 동화 페이지 별 등록<br></br>
  <div align="center"><img src="https://user-images.githubusercontent.com/68148196/181300809-b4932745-9b32-465e-a66b-eda3b5253530.jpg" width=500 height=280></div>
  <br></br>
✔ 동화 페이지 사진 등록 시 동화 문장 추출<br></br>
  <div align="center"><img src="https://user-images.githubusercontent.com/68148196/181298046-6514b30d-d570-4e5d-8a66-a10cb1b92d09.jpg" width=650 height=250></div>
  <br></br>
✔ 문장 별 화자, 음색, 감정 설정<br></br>
  <div align="center"><img src="https://user-images.githubusercontent.com/68148196/181298754-ab323e21-56d7-4eb3-9623-0567ba5d9d76.jpg" width=500 height=230>
  <img src="https://user-images.githubusercontent.com/68148196/181298792-dde394ef-ef40-423f-8886-e95ea2bb9394.jpg" width=500 height=230></div>
  <br></br>
✔ 설정한 목소리와 감정으로 동화 낭독<br></br>
  <div align="center"><img src="https://user-images.githubusercontent.com/68148196/181299610-e29817f1-5ffa-4a9b-bd0c-245cc8a04d0a.jpg" width=650 height=250></div>
  <br></br>
✔ 동화 저장 및 수정/삭제<br></br>
  <div align="center"><img src="https://user-images.githubusercontent.com/68148196/181301904-e0d227b1-2899-48eb-8908-2de5efd68a87.jpg" width=600 height=200></div>
-->
<br>

## 🛠 사용 기술
<table>
<tbody>
  <tr>
    <td><b>Web</b></td>
    <td>JSP, Servlet, Tomcat 9.0</td>
  </tr>
  <tr>
      <td><b>Database</b></td>
      <td>MySQL 8.0</td>
  </tr>
  <tr>
      <td><b>Library</b></td>
      <td>cos, json-simple-1.1</td>
  </tr>
  <tr>
      <td><b>API</b></td>
      <td>Google Vision API</td>
  </tr>
  <tr>
      <td><b>Model</b></td>
      <td><a href="https://github.com/emotiontts/emotiontts_open_db">Emotion TTS</a></td>
  </tr>
  <tr>
      <td><b>Tools</b></td>
      <td>Github, Notion, GoogleDocs</td>
  </tr>
</tbody>
</table>
Emotion TTS는 <a href="https://koreascience.kr/article/JAKO202128837799053.pdf">다음색 감정 음성합성 응용을 위한 감정 SSML 처리기</a>에서 구현한 모델에 API 로 접근하여 사용
<br><br>

## 🗂 Structure
```
📦 StoryReader2
 ┣ 📂 src
 ┃ ┗ 📂 controller
 ┃ ┃ ┣ 📂 editStory
 ┃ ┃ ┣ 📂 makeStory
 ┃ ┃ ┣ 📂 readStory
 ┃ ┃ ┗ 📂 user
 ┃ ┗ 📂 model
 ┃ ┃ ┣ 📂 dao
 ┃ ┃ ┣ 📂 dto
 ┃ ┃ ┗ 📂 service
 ┃ ┗ 📂 util
 ┃   ┣ 📂 text
 ┃   ┣ 📂 view
 ┃   ┗ 📜 DBConnectionManager.java
 ┗ 📂 WebContent
   ┗ 📂 CSS
   ┗ 📂 IMG
   ┗ 📂 META-INF
   ┗ 📂 WEB-INF
   ┗ 📂 js
   ┗ 📜 jsp_files
```
<br>

##  📅 DB ERD
<div align="center"><img src="https://user-images.githubusercontent.com/68148196/181388586-269b0adc-8dfc-405d-b13d-0823f7dddf42.png" width=800 height=320></div>


##  ✨ 개선/발전 방향
✔ 사용자가 직접 음성 파일 추가

✔ 서비스 내에서 이용 가능한 이미지 제공
