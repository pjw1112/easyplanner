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

//로그인 한 유저의 모든 스케쥴을 Get_UserAllSchedule() 함수에서 json형식 문자열 배열로 리딩
let all_schedule;

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
	ArrowRight();
});
function ArrowRight(){
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
}
left_arrow.addEventListener("click", () => {
	ArrowLeft();
});
function ArrowLeft(){
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
}
document.addEventListener("keyup", (e) => {
	let black_display = window
		.getComputedStyle(document.querySelector(".black"))
		.getPropertyValue("display");
	if (black_display == "none" && document.activeElement.id!="easy_prompt") {
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
		} else if (e.key == "Enter") {
			document.getElementById("easy_prompt").focus();
		}


		//1,2,3,4 키 눌렀을때 뷰 전환 
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








/*







*/
//메인 view 구현
function load() {


	/* $.ajax({
				 url:"Get_loginUserIndex", //경로
				 type:"post", //get, post
				 data:"", 
				 dataType:"text", // text, json, xml
				 success: function(data){ 
					 
					console.log("세션 로그인 유저 : "+ data);
				 }, // 성공시 처리
				 
				 error: function(xhr, textStatus, errorThrown){  
					 $(".r3").html(textStatus+"(HTTP-"+xhr.status+")");
					 
				 } // 실패시 처리
				 
			 });//end $.ajax*/

	//if(all_schedule != null){
	//all_schedule.forEach((json) => {

	// console.log(json.start_date.split('T')[0]);
	// });  

	//}









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
			if (e.id == formatDate(dt)) {
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
			if (view_type != "년별") {
				insert_schedule.style.display = "block";
				background_black.style.display = "block";
				document.querySelector("#start_date").value = box.id + "T00:00";
				document.querySelector("#end_date").value = box.id + "T00:00";


				let str = box.id.split('-');
				dt.setFullYear(str[0]);
				dt.setMonth(str[1] - 1);
				dt.setDate(str[2]);


				load();
			}
		});
	});


	//각종 공휴일, 기념일 표시 추가
	if(view_type != "년별"){
		dayboxs.forEach((box) => { 
	
			//aniversaries_2023
			let allkeys = Object.keys(aniversaries_2023);
			allkeys.forEach(key => {
			if(key==box.id){
				let orderNum_toAdd = box.children.length;
				let item = document.createElement("div");
					    item.classList.add(`row${orderNum_toAdd}`);
						item.classList.add("aniversaries");
						item.style.color="black";
						item.innerText = aniversaries_2023[key];
						box.appendChild(item);
				  		}
  					});
  					
  			//aniversaries_2024
			allkeys = Object.keys(aniversaries_2024);
			allkeys.forEach(key => {
			if(key==box.id){
				let orderNum_toAdd = box.children.length;
				let item = document.createElement("div");
					    item.classList.add(`row${orderNum_toAdd}`);
						item.classList.add("aniversaries");
						item.style.color="black";
						item.innerText = aniversaries_2024[key];
						box.appendChild(item);
				  		}
  					});		
	
			//holiday_2023
			allkeys = Object.keys(holiday_2023);
			allkeys.forEach(key => {
			if(key==box.id){
				let orderNum_toAdd = box.children.length;
				let item = document.createElement("div");
					    item.classList.add(`row${orderNum_toAdd}`);
						
						item.innerText = holiday_2023[key];
						box.appendChild(item);
						box.classList.add("holiday");
				  		}
  					});	


			//holiday_2024
			allkeys = Object.keys(holiday_2024);
			allkeys.forEach(key => {
			if(key==box.id){
				let orderNum_toAdd = box.children.length;
				let item = document.createElement("div");
					    item.classList.add(`row${orderNum_toAdd}`);
						
						item.innerText = holiday_2024[key];
						box.appendChild(item);
						box.classList.add("holiday");
				  		}
  					});	






		});
	}




	if (view_type == "월별") {
		//#calendar_body 자식 요소 (daybox) 개수 세서 35개 이상이면 6줄 grid row 그 이하면 5줄 grid row 로 css 설정
		let numRows = Math.ceil(calendar_body.children.length / 7);

		if (numRows <= 5) {
			calendar_body.classList.remove('grid6rows');
			calendar_body.classList.add("grid5rows");
		} else {
			calendar_body.classList.remove('grid5rows');
			calendar_body.classList.add("grid6rows");
		}

	} else {
		calendar_body.classList.remove('grid5rows');
		calendar_body.classList.remove('grid6rows');
	}




	Get_UserAllSchedule();//유저 일정 블럭 추가

}//end load()
// 로그인한 유저의 모든 스케쥴 불러오기 ASYNC 함수
async function Get_UserAllSchedule() {
	// 첫 번째 AJAX 요청
	console.log("11111")
	let login_U_index = await $.ajax({ url: "Get_loginUserIndex" });
	console.log(login_U_index);
	console.log("22222")
	if (login_U_index != "null") {
		// 두 번째 AJAX 요청
		
		console.log("33333");
		
		let response8 = await $.ajax({
			url: "Get_UserAllSchedule",
			data: { "login_U_index": login_U_index }
		});
		
		console.log(response8);
		let all_schedule = JSON.parse(response8);
		
		console.log("44444");

		//유저 일정 view 구현
		if (view_type != "년별") {
			console.log("55555")
			if (all_schedule != null) {
				console.log("6666666")
				all_schedule.forEach((json) => {
					console.log("7777777")
					//console.log(json);/////////////////////////////////
					if (json.start_date.split('T')[0]) {
						let start_date = json.start_date.split('T')[0];
console.log("8888888")
						if (document.getElementById(`${start_date}`)) {
							console.log("9999999")
							let target_daybox = document.getElementById(`${start_date}`);

							let orderNum_toAdd = target_daybox.children.length;

							let item = document.createElement("div");
							item.classList.add(`row${orderNum_toAdd}`);
							item.classList.add("schedule_box");
							item.innerText = json.content;
							item.addEventListener("click", (event) => {
console.log("10 10 10 10 10 ")
								update_schedule.style.display = "block";
								background_black.style.display = "block";
								document.querySelector("#start_date2").value = json.start_date;
								document.querySelector("#end_date2").value = json.end_date;
								document.querySelector("#content2").value = json.content;
								document.querySelector("#s_index").value = json.s_index;
								document.querySelector(".update_schedule .schedule_delete").href += "?s_index=" + json.s_index;
console.log("11 11 11 11 11")
								load();

								event.stopPropagation();
							}, true);
							target_daybox.appendChild(item);
						}
					}
				});
			}
		}//end 유저 일정 view 구현
	}
}//end ASYNC
/*







*/
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
/*







*/
//캘린더를 월 형태로 출력해주는 함수
function view_like_month(week, month, year, month_str) {
	// console.log(...calendar_body.classList);
	calendar_body.classList.remove(...calendar_body.classList);
	calendar_body.classList.add("view_like_month");

	let today_temp = formatDate(new Date());
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
		let dateString = formatDate(dt_temp);

		let item = document.createElement("div");
		item.classList.add("beforeMonth_daybox");
		item.classList.add("daybox");
		item.id = dateString;

		let item2 = document.createElement("div");
		item2.classList.add("row0");
		item2.innerText = daysInBeforeMonth - (week - i - 1);
		item.appendChild(item2);

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

		let item2 = document.createElement("div");
		item2.classList.add("row0");
		item2.innerText = i + 1;

		if (dt_temp.getDay() == 0 || dt_temp.getDay() == 6) {
			item.classList.add("holiday");
		}
		if (dateString == today_temp) {
			item.classList.add("month_in_today");
			item2.innerHTML += "<span>&nbsp오늘 ♬</span>";
		}
		item.appendChild(item2);
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

		let item2 = document.createElement("div");
		item2.classList.add("row0");
		item2.innerText = i + 1;
		item.appendChild(item2);

		calendar_body.appendChild(item);
	}

	month_display.innerText = year + "년 " + month_str;
}

/*







*/
//캘린더를 주 형태로 출력해주는 함수
function view_like_week(day, week, month, year, month_str) {
	calendar_body.classList.remove(...calendar_body.classList);
	calendar_body.classList.add("view_like_week");

	let today_temp = formatDate(new Date());

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
		let dateString = formatDate(dt_temp);

		let item = document.createElement("div");
		item.classList.add("beforeMonth_daybox");
		item.classList.add("daybox");
		item.id = dateString;

		let item2 = document.createElement("div");
		item2.classList.add("row0");
		item2.innerText = daysInBeforeMonth - (week - i - 1);
		item.appendChild(item2);

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


		let item2 = document.createElement("div");
		item2.classList.add("row0");
		item2.innerText = i + 1;

		if (dt_temp.getDay() == 0 || dt_temp.getDay() == 6) {
			item.classList.add("holiday");
		}
		if (dateString == today_temp) {
			item.classList.add("month_in_today");
			item2.innerHTML += "<span>&nbsp&nbsp&nbsp&nbsp오늘 ♬</span>";
		}

		item.appendChild(item2);

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

		let item2 = document.createElement("div");
		item2.classList.add("row0");
		item2.innerText = i + 1;
		item.appendChild(item2);

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
/*







*/
//캘린더를 일 형태로 출력해주는 함수
function view_like_day(day, week, month, year, month_str) {
	calendar_body.classList.remove(...calendar_body.classList);
	calendar_body.classList.add("view_like_day");

	let today_temp = formatDate(new Date());
	let dateString = formatDate(dt);
	let item = document.createElement("div");
	item.classList.add("week");
	item.innerHTML = "&nbsp" + week_str[new Date(year, month, day).getDay()] + "요일";
	item.style.textAlign = "left";
	list_column.appendChild(item);

	item = document.createElement("div");
	item.classList.add("thisMonth_daybox");
	item.classList.add("daybox");
	item.id = dateString;


	let item2 = document.createElement("div");
	item2.classList.add("row0");
	item2.innerText = day;
	item.appendChild(item2);
	// if(dt_temp.getDay()==0 || dt_temp.getDay()==6 ){
	//   item2.classList.add("holiday");
	// }
	if (dateString == today_temp) {
		item.classList.add("month_in_today");
		item2.innerHTML += "<span>&nbsp&nbsp&nbsp&nbsp오늘 ♬</span>";
	}

	calendar_body.appendChild(item);

	month_display.innerText = year + "년 " + month_str + " " + day + "일";
}
/*







*/
//date 객체를 받아서 "yyyy-mm-dd"형식 문자열로 반환해주는 함수
function formatDate(date) {
	const year = date.getFullYear();
	const month = (date.getMonth() + 1).toString().padStart(2, '0');
	const day = date.getDate().toString().padStart(2, '0');

	return `${year}-${month}-${day}`;
}



//hammer.js
/*
let chg=0;
 var hammertime = new Hammer(calendar_body);

  hammertime.get('pan').set({ direction: Hammer.DIRECTION_ALL, threshold: 150  });

  hammertime.on('pan', function(ev) {
	  var thresholdPassed = Math.abs(ev.deltaX) > 150 || Math.abs(ev.deltaY) > 150; // Checking if movement exceeds threshold
    if (thresholdPassed) {
    // ev.direction contains the direction of the drag (up, down, left, or right)
    if (ev.direction === Hammer.DIRECTION_UP) {
		
	  window.scrollBy(0, 50);
      console.log('Up');
    } else if (ev.direction === Hammer.DIRECTION_DOWN) {
	  
	  window.scrollBy(0, -50);
      console.log('Down');
    } else if (ev.direction === Hammer.DIRECTION_LEFT) {
		if(chg==0){
			chg=1;
			ArrowRight();
			setTimeout(function(){chg=0;},500);
		}
		
      console.log('Left');
    } else if (ev.direction === Hammer.DIRECTION_RIGHT) {
		
		if(chg==0){
			chg=1;
			
			ArrowLeft();
			setTimeout(function(){chg=0;},500);
		}
      console.log('Right');
    }
    }
  });
  */

//모바일 용으로 새로만든 뷰타입(년월주일) 버튼 이벤트 처리
$(".btn_y").on("click",() =>{
	view_type="년별";
	load();
});
$(".btn_m").on("click",() =>{
	view_type="월별";
	load();
}); 
$(".btn_w").on("click",() =>{
	view_type="주별";
	load();
}); 
$(".btn_d").on("click",() =>{
	view_type="일별";
	load();
}); 



//아이디 찾기 1.이메일 보내기 2.인증번호 일치 확인


