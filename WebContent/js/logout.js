/**
 * 
 */
 const idInput = document.getElementById('id');
 const nameInput = document.getElementById('name');

// 로그아웃
function  doLogout() {
  var confirmResult = confirm('로그아웃 하시겠습니까?');
  if (confirmResult) {
	f.action = "DoLogout"; //호출명
	f.method = "post"; //POST방식 
	f.submit();
}
}
function  alertLogin() {
  alert("로그인 후 이용해주세요.");
}

// submit전 모든 입력 조건을 완료했는지 검사
function checkValue() {
  if (idInput === '') {
  	alert("아이디를 입력해주세요.");
  	// location을 사용하는 방법 
  	location.reload(); 
  	location.replace(location.href); 
  	location.href = location.href; // history를 사용하는 방법 
  	history.go(0);
    return false;
  }
  if (nameInput === '') {
  	alert("이름을 입력해주세요.");// location을 사용하는 방법 
  	location.reload(); 
  	location.replace(location.href); 
  	location.href = location.href; // history를 사용하는 방법 
  	history.go(0);
    return false;
  }
  return true;
}