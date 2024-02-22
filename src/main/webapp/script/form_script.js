let users_create_form = document.querySelector("#users_create_form");

let id_form = document.querySelector("#id_join");
let pass_form = document.querySelector("#pass_join");
let pass2_form = document.querySelector("#pass2_join");
let email_form = document.querySelector("#email_join");
let birth_form = document.querySelector("#birth_join");

let id_form_label_Check = document.querySelector(".id_check .check_message");
let pass_form_label_Check = document.querySelector(".pass_check .check_message");
let pass2_form_label_Check = document.querySelector(".pass2_check .check_message");
let email_form_label_Check = document.querySelector(".email_check .check_message");
let birth_form_label_Check = document.querySelector(".birth_check .check_message");


let login_click = document.querySelector(".login_click");
let login_box = document.querySelector(".login_box");

let join_click = document.querySelector(".join_click");
let join_box = document.querySelector(".join_box");

let find_id = document.querySelector(".find_id");
let insert_schedule = document.querySelector(".insert_schedule");
let update_schedule = document.querySelector(".update_schedule");

let background_black = document.querySelector(".black");
let all_cancels = document.querySelectorAll(".cancel");


let id_ok = false;
let pass_ok = false;
let pass2_ok = false;
let email_ok = false;
let birth_ok = false;




// 도움말 버튼 클릭시 도움말 창 팝업
let btn_q = document.querySelector(".btn_question");
let q_box = document.querySelector(".q_box");
btn_q.addEventListener("click" , () => {
	
  q_box.style.display="initial";
  background_black.style.display="initial";
  
});



// 가입하기 버튼 클릭시 가입하기창 팝업
join_click.addEventListener("click" , () => {
	
  join_box.style.display="initial";
  background_black.style.display="initial";
  
});




//로긴 버튼 클릭시 로긴창 팝업
login_click.addEventListener("click" , () => {
  login_box.style.display="initial";
  background_black.style.display="initial";
  
  if(getCookieValue("remember_id")){
	  login_box.querySelector("#remember_id").checked=true;
	  login_box.querySelector("#id_login").value = getCookieValue("remember_id");
  }
  
});
//  아아이디 기억하기 설정된 쿠키 값 확인하기
function getCookieValue(cookieName) {
    // 쿠키 문자열을 가져옴
    var cookieString = document.cookie;

    // 쿠키를 ; 기준으로 분리
    var cookies = cookieString.split(';');

    // 쿠키를 순회하며 원하는 쿠키를 찾음
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();

        // 쿠키의 이름과 값으로 분리
        var cookieParts = cookie.split('=');
        var name = cookieParts[0];
        var value = cookieParts[1];

        // 원하는 쿠키를 찾으면 값을 반환
        if (name === cookieName) {
            return value;
        }
    }

    // 해당 쿠키가 없으면 null 반환
    return null;
}








// 팝업중 바깥 화면 클릭시 모든 팝업 상태 초기화
background_black.addEventListener("click" , () => {
  cancel ();
});
// cancel 클래스가 붙은 요소 클릭시 모든 팝업 상태 초기화
all_cancels.forEach((e) => {
  e.addEventListener("click", (box) => {
    cancel ();
  });
});
// 모든 팝업 상태 초기화 함수
function cancel (){
  login_box.style.display="none";
  join_box.style.display="none";
  find_id.style.display="none";
  insert_schedule.style.display="none";
  update_schedule.style.display="none";
  q_box.style.display="none";

  background_black.style.display="none";

  document.querySelectorAll(".check_message").forEach((box) => {
    box.innerHTML="";
  });
  document.querySelectorAll('input:not([type="submit"]):not([type="datetime-local"]):not([type="reset"]):not([type="button"])').forEach((box) => {
    box.value="";
  });

  id_ok = false;
  pass_ok = false;
  pass2_ok = false;
  email_ok = false;
  birth_ok = false;

};







// 가입하기 창 입력 요소들 실시간 유효성 검사 로직
// 1. ID 조건 확인
id_form.addEventListener("input", () => {
  if (id_form.value.length != 0) {
    if (4 <= id_form.value.length && id_form.value.length <= 20) {
      id_form_label_Check.innerHTML = "";
      let p = document.createElement("p");
      p.innerHTML = "아이디 조건 충족";
      p.classList.add("success");
      id_form_label_Check.appendChild(p);

      let success = document.createElement("div");
      success.innerHTML = "<i class='fa-regular fa-circle-check'></i>";
      success.classList.add("check_success");
      id_form_label_Check.appendChild(success);

      id_ok = true;
    } else {
      id_form_label_Check.innerHTML = "";
      let p = document.createElement("p");
      p.innerHTML = "아이디는 4자이상 20자이하";
      p.classList.add("fail");
      id_form_label_Check.appendChild(p);

      let fail = document.createElement("div");
      fail.innerHTML = "<i class='fa-regular fa-circle-xmark'></i>";
      fail.classList.add("check_fail");
      id_form_label_Check.appendChild(fail);

      id_ok = false;
    }
  } else {
    id_form_label_Check.innerHTML = "";
    id_ok = false;
  }
});
// 2. PASS 조건 확인
pass_form.addEventListener("input", () => {
    if (pass_form.value.length != 0) {
      if (4 <= pass_form.value.length && pass_form.value.length <= 20) {
        pass_form_label_Check.innerHTML = "";
        let p = document.createElement("p");
        p.innerHTML = "비밀번호 조건 충족";
        p.classList.add("success");
        pass_form_label_Check.appendChild(p);
  
        let success = document.createElement("div");
        success.innerHTML = "<i class='fa-regular fa-circle-check'></i>";
        success.classList.add("check_success");
        pass_form_label_Check.appendChild(success);

        pass_ok = true;
      } else {
        pass_form_label_Check.innerHTML = "";
        let p = document.createElement("p");
        p.innerHTML = "비밀번호는 4자이상 20자이하";
        p.classList.add("fail");
        pass_form_label_Check.appendChild(p);
  
        let fail = document.createElement("div");
        fail.innerHTML = "<i class='fa-regular fa-circle-xmark'></i>";
        fail.classList.add("check_fail");
        pass_form_label_Check.appendChild(fail);

        pass_ok = false;
      }
    } else {
      pass_form_label_Check.innerHTML = "";
      pass_ok = false;
    }
    pass_match()
  });
// 3. PASS2 조건 확인
pass2_form.addEventListener("input", () => {
    pass_match();
  });

function pass_match(){
    if (pass2_form.value.length != 0) {
        if (pass_form.value == pass2_form.value) {
          pass2_form_label_Check.innerHTML = "";
          let p = document.createElement("p");
          p.innerHTML = "비밀번호 일치";
          p.classList.add("success");
          pass2_form_label_Check.appendChild(p);
    
          let success = document.createElement("div");
          success.innerHTML = "<i class='fa-regular fa-circle-check'></i>";
          success.classList.add("check_success");
          pass2_form_label_Check.appendChild(success);

          pass2_ok = true;
        } else {
          pass2_form_label_Check.innerHTML = "";
          let p = document.createElement("p");
          p.innerHTML = "비밀번호 불일치";
          p.classList.add("fail");
          pass2_form_label_Check.appendChild(p);
    
          let fail = document.createElement("div");
          fail.innerHTML = "<i class='fa-regular fa-circle-xmark'></i>";
          fail.classList.add("check_fail");
          pass2_form_label_Check.appendChild(fail);

          pass2_ok = false;
        }
      } else {
        pass2_form_label_Check.innerHTML = "";
        pass2_ok = false;
      }
}
// 4. email 조건 확인
email_form.addEventListener("input", () =>{

    let email = email_form.value;
    let emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    
    if (email_form.value.length != 0) {
        if (emailRegex.test(email)) {
          email_form_label_Check.innerHTML = "";
          let p = document.createElement("p");
          p.innerHTML = "이메일 형식 충족";
          p.classList.add("success");
          email_form_label_Check.appendChild(p);
    
          let success = document.createElement("div");
          success.innerHTML = "<i class='fa-regular fa-circle-check'></i>";
          success.classList.add("check_success");
          email_form_label_Check.appendChild(success);

          email_ok = true;

        } else {
          email_form_label_Check.innerHTML = "";
          let p = document.createElement("p");
          p.innerHTML = "이메일 형식으로 입력";
          p.classList.add("fail");
          email_form_label_Check.appendChild(p);
    
          let fail = document.createElement("div");
          fail.innerHTML = "<i class='fa-regular fa-circle-xmark'></i>";
          fail.classList.add("check_fail");
          email_form_label_Check.appendChild(fail);

          email_ok = false;
        }
      } else {
        email_form_label_Check.innerHTML = "";
        email_ok = false;
      }

});
// 5. 생일 입력 확인
birth_form.addEventListener("input",() =>{
  birth_form_label_Check.innerHTML = "";
  let p = document.createElement("p");
  p.innerHTML = "생일 입력 완료";
  p.classList.add("success");
  birth_form_label_Check.appendChild(p);
  birth_ok = true;
});
// 6. 1~5의 모든 항목이 true 일 경우 submit 통과
users_create_form.addEventListener("submit" , (event) => {
	
 if( id_ok == false || pass_ok == false || pass2_ok == false || email_ok == false || birth_ok == false ){
  event.preventDefault();
 }
});









// 아이디 찾기 버튼 클릭
/*
let find_id_button = document.querySelector(".find_id_button");
find_id_button.addEventListener("click" , function(){
  login_box.style.display="none"
 
  document.querySelector(".find_id").style.display="initial"
  background_black.style.display="initial"
});
*/

// 비밀번호 찾기 버튼 클릭
// let find_pass_button = document.querySelector(".find_pass_button");
// find_pass_button.addEventListener("click" , function(){
//   document.querySelector(".find_id").style.display="initial"
//   background_black.style.display="initial"
// });







// 이지 프롬프트 컨트롤
document.getElementById("easy_prompt").addEventListener("keyup", (e) => {

	if (e.key == "Escape") {
		
		document.getElementById("easy_prompt").blur();
		e.stopPropagation();
	}

}, true);
