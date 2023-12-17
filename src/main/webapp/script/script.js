let month_display = document.querySelector(".month_display");
let list_column = document.querySelector(".list_column");
let calendar_body = document.querySelector("#calendar_body");
let left_arrow = document.querySelector(".left_arrow");
let right_arrow = document.querySelector(".right_arrow");
let view_type_dom = document.querySelectorAll(".view_type");
let dropdown_toggle = document.querySelector(".dropdown-toggle");

let dt = new Date();
dt = new Date(dt.getFullYear(), dt.getMonth(), dt.getDate());

let nav = 0; //월 이동 할때마다 +1 or -1 해주고 0으로 다시 초기화 되는 변수
let nav2 = 0; //년 이동 할때마다 +1 or -1 해주고 0으로 다시 초기화 되는 변수
let nav3 = 0; //주 이동 할때마다 +1 or -1 해주고 0으로 다시 초기화 되는 변수
let nav4 = 0; //일 이동 할때마다 +1 or -1 해주고 0으로 다시 초기화 되는 변수
let view_type_list = ["일별", "주별", "월별", "년별"];
let view_type = "월별";
let week_str = ["일", "월", "화", "수", "목", "금", "토"];

load();

// 이벤트 리스너 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

view_type_dom.forEach((e, i) => {
  e.addEventListener("click", () => {
    view_type = view_type_list[i];
    dropdown_toggle.innerText = view_type_list[i] + " 보기";
    load();
  });
});

right_arrow.addEventListener("click", () => {
  switch (view_type) {
    case "년별":
      nav2++;
      load();
      break;

    case "월별":
      nav++;
      load();
      break;

    case "주별":
      nav3++;
      load();
      break;

    case "일별":
      nav4++;
      load();
      break;
  }
});

left_arrow.addEventListener("click", () => {
  switch (view_type) {
    case "년별":
      nav2--;
      load();
      break;

    case "월별":
      nav--;
      load();
      break;

    case "주별":
      nav3--;
      load();
      break;

    case "일별":
      nav4--;
      load();
      break;
  }
});

document.addEventListener("keydown", (e) => {
  let black_display = window
    .getComputedStyle(document.querySelector(".black"))
    .getPropertyValue("display");
  if (black_display == "none") {
    if (e.key == "ArrowRight") {
      switch (view_type) {
        case "년별":
          nav2++;
          load();
          break;

        case "월별":
          nav4++;
          load();
          break;

        case "주별":
          nav4++;
          load();
          break;

        case "일별":
          nav4++;
          load();
          break;
      }
    } else if (e.key == "ArrowLeft") {
      switch (view_type) {
        case "년별":
          nav2--;
          load();
          break;

        case "월별":
          nav4--;
          load();
          break;

        case "주별":
          nav4--;
          load();
          break;

        case "일별":
          nav4--;
          load();
          break;
      }
    } else if (e.key == "ArrowUp") {
      switch (view_type) {
        case "년별":
          break;

        case "월별":
          nav3--;
          load();
          break;

        case "주별":
          nav3--;
          load();
          break;

        case "일별":
          nav3--;
          load();
          break;
      }
    } else if (e.key == "ArrowDown") {
      switch (view_type) {
        case "년별":
          break;

        case "월별":
          nav3++;
          load();
          break;

        case "주별":
          nav3++;
          load();
          break;

        case "일별":
          nav3++;
          load();
          break;
      }
    } else if (e.key == "Escape") {
      let i = new Date();
      dt = new Date(i.getFullYear(), i.getMonth(), i.getDate());
      load();
    }

    if (1 <= parseInt(e.key) && parseInt(e.key) <= 4) {
      if (view_type != view_type_list[parseInt(e.key) - 1]) {
        view_type = view_type_list[parseInt(e.key) - 1];
        dropdown_toggle.innerText =
          view_type_list[parseInt(e.key) - 1] + " 보기";
        load();
      }
    }
  }
});




// 이벤트 리스너 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








//메인 view 구현
function load() {
	
	 $.ajax({
				 url:"Get_loginUserId", //경로
				 type:"post", //get, post
				 data:"", 
				 dataType:"text", // text, json, xml
				 success: function(data){ 
					 
					console.log("세션 로그인 유저 : "+ data);
				 }, // 성공시 처리
				 
				 error: function(xhr, textStatus, errorThrown){  
					 $(".r3").html(textStatus+"(HTTP-"+xhr.status+")");
					 
				 } // 실패시 처리
				 
			 });//end $.ajax
			 
	
	
	
	
	
	
	
	
	
	
	
	
	
  //1. 기존에 작성된 view 비우기
  calendar_body.innerHTML = "";
  list_column.innerHTML = "";

  //2. 년,월,주,일 이동 변수 더해주고 변수 초기화
  dt.setMonth(dt.getMonth() + nav);
  dt.setFullYear(dt.getFullYear() + nav2);
  dt.setDate(dt.getDate() + 7 * nav3 + nav4);
  nav = 0;
  nav2 = 0;
  nav3 = 0;
  nav4 = 0;

  //3. 확정된 년, 월 기준으로 필요한 변수 계산
  let month = dt.getMonth(); //월 (int)
  let month_str = dt.toLocaleString("default", { month: "long" }); //월 (String)
  let year = dt.getFullYear(); //년
  let week = new Date(year, month, 1).getDay(); //요일 (int)
  let day = dt.getDate();

  //4. 현재 뷰타입에 따라 캘린더 구현
  switch (view_type) {
    case "년별":
      view_like_year(year);
      break;

    case "월별":
      view_like_month(week, month, year, month_str);
      break;

    case "주별":
      view_like_week(day, week, month, year, month_str);
      break;

    case "일별":
      view_like_day(day, week, month, year, month_str);
      break;
  }

  if (view_type != "일별") {
    calendar_body.childNodes.forEach((e) => {
      if ( e.id == formatDate(dt)) {
        e.classList.add("select");
      } else {
        e.classList.remove("select");
      }
    });
  }

  let dayboxs = document.querySelectorAll(".daybox");

  // 각 요소에 대해 클릭 이벤트를 추가합니다.
  dayboxs.forEach((box) => {
    box.addEventListener("click", () => {
      if(view_type != "년별"){
      insert_schedule.style.display="block";
      background_black.style.display="block";
      document.querySelector("#start_date").value = box.id + "T00:00";
      document.querySelector("#end_date").value = box.id + "T00:00";


      let str = box.id.split('-');
      dt.setFullYear(str[0]);
      dt.setMonth(str[1]-1);
      dt.setDate(str[2]);


      load();
      }
    });
  });



}

//캘린더를 년 형태로 출력해주는 함수
function view_like_year(year) {
  month_display.innerText = year + "년 ";

  calendar_body.classList.remove(...calendar_body.classList);
  calendar_body.classList.add("view_like_year");
  let today_temp = formatDate(new Date());

  for (let i = 0; i < 12; i++) {
    let daysInMonth = new Date(year, i + 1, 0).getDate(); //n월의 총 일 수
    let firstDayInt = new Date(year, i, 1).getDay(); //n월이 시작하는 요일 int값

    let month_box = document.createElement("div");
    month_box.classList.add("month_box");

    // month_box 첫 줄 : 몇 월 표시
    let month_name = document.createElement("div");
    month_name.innerHTML = "&nbsp " + (i + 1) + "월";
    month_box.appendChild(month_name);

    // month_box 둘째 줄 : 요일 표시
    let week_box = document.createElement("div");
    for (let j = 0; j < 7; j++) {
      let item = document.createElement("div");
      item.innerText = week_str[j];
      week_box.appendChild(item);
      week_box.classList.add("week_box");
    }
    month_box.appendChild(week_box);

    // month_box 셋째 줄 : 일 표시
    let last_box = document.createElement("div");
    last_box.classList.add("last_box"); //빈박스 넣기 (전 달)
    for (let j = 0; j < firstDayInt; j++) {
      let bin_box = document.createElement("div");
      bin_box.classList.add("bin_box");
      bin_box.innerHTML = "&nbsp";
      last_box.appendChild(bin_box);
    }
    month_box.appendChild(last_box);

    for (let j = 0; j < daysInMonth; j++) {
      let dt_temp = new Date(year, i, j + 1);
      let dateString = formatDate(dt_temp);

      let item = document.createElement("div");
      item.id = dateString;
      item.classList.add("year_view_day_box");
      item.classList.add("daybox");
      item.innerText = j + 1;
      if (dt_temp.getDay() == 0 || dt_temp.getDay() == 6) {
        item.classList.add("holiday");
      }

      if (dateString == today_temp) {
        item.classList.add("year_in_today");
      }

      last_box.appendChild(item);
    }

    calendar_body.appendChild(month_box);
  }
}

//캘린더를 월 형태로 출력해주는 함수
function view_like_month(week, month, year, month_str) {
  // console.log(...calendar_body.classList);
  calendar_body.classList.remove(...calendar_body.classList);
  calendar_body.classList.add("view_like_month");

  let today_temp = new Date().toLocaleDateString("default", {
    weekday: "long",
    year: "numeric",
    month: "numeric",
    day: "numeric",
  });
  //상단 날짜 구분 기준 표현
  for (let i = 0; i < 7; i++) {
    let item = document.createElement("div");
    item.classList.add("week");
    item.innerText = week_str[i];
    list_column.appendChild(item);
  }

  //전 달(before month) 표현
  let daysInBeforeMonth = new Date(year, month, 0).getDate(); //전달의 총 일 수
  for (let i = 0; i < week; i++) {
    let dt_temp = new Date(year, month - 1, daysInBeforeMonth - (week - i - 1));
    let dateString =  formatDate(dt_temp);

    let item = document.createElement("div");
    item.classList.add("beforeMonth_daybox");
    item.classList.add("daybox");
    item.id = dateString;
    item.innerText = daysInBeforeMonth - (week - i - 1);

    calendar_body.appendChild(item);
  }

  //이번 달(this month) 표현
  let daysInMonth = new Date(year, month + 1, 0).getDate(); //이번 달의 총 일 수
  for (let i = 0; i < daysInMonth; i++) {
    let dt_temp = new Date(year, month, i + 1);
    let dateString = formatDate(dt_temp);

    let item = document.createElement("div");
    item.classList.add("thisMonth_daybox");
    item.classList.add("daybox");
    item.id = dateString;
    item.innerText = i + 1;
    if (dt_temp.getDay() == 0 || dt_temp.getDay() == 6) {
      item.classList.add("holiday");
    }
    if (dateString == today_temp) {
      item.classList.add("month_in_today");
      item.innerHTML += "<span>&nbsp오늘 ♬</span>";
    }

    calendar_body.appendChild(item);
  }

  //다음 달(after month) 표현
  FirstDayInAfterMonth = new Date(year, month + 1, 1).getDay();
  let temp = [0, 6, 5, 4, 3, 2, 1];
  for (let i = 0; i < temp[FirstDayInAfterMonth]; i++) {
    let dt_temp = new Date(year, month + 1, i + 1);
    let dateString = formatDate(dt_temp);
    let item = document.createElement("div");
    item.classList.add("afterMonth_daybox");
    item.classList.add("daybox");
    item.id = dateString;
    item.innerText = i + 1;
    calendar_body.appendChild(item);
  }

  month_display.innerText = year + "년 " + month_str;
}

//캘린더를 주 형태로 출력해주는 함수
function view_like_week(day, week, month, year, month_str) {
  calendar_body.classList.remove(...calendar_body.classList);
  calendar_body.classList.add("view_like_week");

  let today_temp =  formatDate(new Date());

  //상단 날짜 구분 기준 표현
  for (let i = 0; i < 7; i++) {
    let item = document.createElement("div");
    item.classList.add("week");
    item.innerText = week_str[i];
    list_column.appendChild(item);
  }

  //전 달(before month) 표현
  let daysInBeforeMonth = new Date(year, month, 0).getDate(); //전달의 총 일 수
  for (let i = 0; i < week; i++) {
    let dt_temp = new Date(year, month - 1, daysInBeforeMonth - (week - i - 1));
    let dateString =  formatDate(dt_temp);

    let item = document.createElement("div");
    item.classList.add("beforeMonth_daybox");
    item.classList.add("daybox");
    item.id = dateString;
    item.innerText = daysInBeforeMonth - (week - i - 1);
    calendar_body.appendChild(item);
  }

  //이번 달(this month) 표현
  let daysInMonth = new Date(year, month + 1, 0).getDate(); //이번 달의 총 일 수
  for (let i = 0; i < daysInMonth; i++) {
    let dt_temp = new Date(year, month, i + 1);
    let dateString = formatDate(dt_temp);

    let item = document.createElement("div");
    item.classList.add("thisMonth_daybox");
    item.classList.add("daybox");
    item.id = dateString;
    item.innerText = i + 1;

    if (dt_temp.getDay() == 0 || dt_temp.getDay() == 6) {
      item.classList.add("holiday");
    }
    if (dateString == today_temp) {
      item.classList.add("month_in_today");
      item.innerHTML += "<span>&nbsp&nbsp&nbsp&nbsp오늘 ♬</span>";
    }

    calendar_body.appendChild(item);
  }

  //다음 달(after month) 표현
  FirstDayInAfterMonth = new Date(year, month + 1, 1).getDay();
  let temp = [0, 6, 5, 4, 3, 2, 1];
  for (let i = 0; i < temp[FirstDayInAfterMonth]; i++) {
    let dt_temp = new Date(year, month + 1, i + 1);
    let dateString = formatDate(dt_temp);
    let item = document.createElement("div");
    item.classList.add("afterMonth_daybox");
    item.classList.add("daybox");
    item.id = dateString;
    item.innerText = i + 1;
    calendar_body.appendChild(item);
  }

  //월 전체를 앞의 코드에서 다 구현한 뒤 특정 주차를 제외하고 나머지 데이 박스들을 전부 삭제
  let total_week = Math.floor(
    (week + new Date(year, month + 1, 0).getDate()) / 7
  );
  let this_week = Math.floor(
    (week + new Date(year, month, day).getDate() - 1) / 7
  );

  calendar_body.childNodes.forEach((e, i) => {
    if (Math.floor(i / 7) != this_week) {
      e.classList.add("remove");
    }
  });

  for (let i = 0; i < 42; i++) {
    if (calendar_body.firstChild.classList.contains("remove")) {
      calendar_body.firstChild.remove();
    }
  }
  for (let i = 0; i < 42; i++) {
    if (calendar_body.lastChild.classList.contains("remove")) {
      calendar_body.lastChild.remove();
    }
  }

  month_display.innerText =
    year + "년 " + month_str + " " + (this_week + 1) + "주차";
}

//캘린더를 일 형태로 출력해주는 함수
function view_like_day(day, week, month, year, month_str) {
  calendar_body.classList.remove(...calendar_body.classList);
  calendar_body.classList.add("view_like_day");

  let today_temp =  formatDate(new Date());
  let dateString =  formatDate(dt);
  let item = document.createElement("div");
  item.classList.add("week");
  item.innerHTML ="&nbsp&nbsp&nbsp&nbsp&nbsp" + week_str[new Date(year, month, day).getDay()] + "요일";
  item.style.textAlign = "left";
  list_column.appendChild(item);

  let item2 = document.createElement("div");
  item2.classList.add("thisMonth_daybox");
  item2.classList.add("daybox");
  item2.id = dateString;
  item2.innerText = day;

  // if(dt_temp.getDay()==0 || dt_temp.getDay()==6 ){
  //   item2.classList.add("holiday");
  // }
  if (dateString == today_temp) {
    item2.classList.add("month_in_today");
    item2.innerHTML += "<span>&nbsp&nbsp&nbsp&nbsp오늘 ♬</span>";
  }

  calendar_body.appendChild(item2);

  month_display.innerText = year + "년 " + month_str + " " + day + "일";
}

//date 객체를 받아서 "yyyy-mm-dd"형식 문자열로 반환해주는 함수
function formatDate(date) {
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');

  return `${year}-${month}-${day}`;
}