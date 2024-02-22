import { GoogleGenerativeAI } from "@google/generative-ai";

const genAI = new GoogleGenerativeAI("AIzaSyBe1o15wuxPslLc63blShq2Je-n7UTsVNc");


let easy_prompt = document.getElementById("easy_prompt");
easy_prompt.addEventListener("keydown", (e) => {

	if (e.key == "Enter") {
		// 엔터 키를 눌렀을 때 실행할 함수를 호출합니다.
		let sentence = easy_prompt.value;
		console.log(sentence);
		run(sentence);
	}

});

async function run(sentence) {

	easy_prompt.style.backgroundImage = 'url("/easy_planner/images/spinner.svg")';

	const model = genAI.getGenerativeModel({ model: "gemini-pro" });

	const generationConfig = {
		temperature: 0.1,
		topK: 1,
		topP: 1,
		maxOutputTokens: 2048,
	};

	const parts = [
		{ text: "input :  24년 12월 23일부터 28일까지 회사자체 연휴기간이라 모든 일정 중단" },
		{ text: "output :  { \"start_date\" : \"2024-12-23T00:00\",\"end_date\" : \"2024-12-28T23:50\", \"content\" : \"회사자체 연휴기간 모든 일정 중단\",\"type\" : \"insert\"}" },
		{ text: "input :  2024년 1월 5일부터 1월 10일까지 휴가 신청" },
		{ text: "output :  { \"start_date\" : \"2024-01-05T00:00\", \"end_date\" : \"2024-01-10T23:50\", \"content\" : \"휴가 신청\" , \"type\" : \"insert\" }" },
		{ text: "input :  23년 12월 25일 아침 8시에 등산 일정" },
		{ text: "output :  { \"start_date\" : \"2023-12-25T08:00\", \"end_date\" : \"2023-12-25T09:00\", \"content\" : \"등산 갔다오기\", \"type\" : \"insert\" }" },
		{ text: "input :  24년 3월 15일 오후 2시부터 회의" },
		{ text: "output :  { \"start_date\" : \"2024-03-15T14:00\", \"end_date\" : \"2024-03-15T15:00\", \"content\" : \"회의\", \"type\" : \"insert\" }" },
		{ text: "input :  2023-12-30부터 5일간 연말 휴가" },
		{ text: "output :  { \"start_date\" : \"2023-12-30T00:00\", \"end_date\" : \"2024-01-03T23:50\", \"content\" : \"연말 휴가\", \"type\" : \"insert\" }" },
		{ text: "input :  24년 12월 24일부터 2주 동안 매일 아침 8시에 운동" },
		{ text: "output :  { \"start_date\" : \"2024-12-24T08:00\", \"end_date\" : \"2025-01-06T23:50\", \"content\" : \"매일 아침 운동\", \"type\" : \"insert\" }" },
		{ text: "input :  24년 12월 25일 휴가" },
		{ text: "output :  { \"start_date\" : \"2024-12-25T00:00\", \"end_date\" : \"2024-12-25T23:50\", \"content\" : \"휴가\", \"type\" : \"insert\" }" },
		{ text: "input :  2023년 12월 3일 일정 삭제" },
		{ text: "output :  { \"start_date\" : \"2023-12-03T00:00\", \"end_date\" : \"2023-12-03T23:50\", \"content\" : \"일정 삭제\", \"type\" : \"delete\", \"order\" : \"1\" }" },
		{ text: "input :  2024년 12월 3일 전체 일정 삭제" },
		{ text: "output :  { \"start_date\" : \"2024-12-03T00:00\", \"end_date\" : \"2024-12-03T23:50\", \"content\" : \"전체 일정 삭제\", \"type\" : \"delete\", \"order\" :\"all\" }" },
		{ text: "input :  2024년 12월 17일 첫번째 일정 지워줘" },
		{ text: "output :  { \"start_date\" : \"2024-12-17T00:00\", \"end_date\" : \"2024-12-17T23:50\", \"content\" : \"첫번째 일정 지우기\", \"type\" : \"delete\", \"order\" : \"1\" }" },
		{ text: "input :  2023년 2월 15일 세번째 일정 내용을 오후미팅 참가로 바꿔줘" },
		{ text: "output :  { \"start_date\" : \"2023-02-15T00:00\", \"end_date\" : \"2023-02-15T23:50\", \" update_date\" : \"2023-02-15T00:00\", \"content\" : \"오후 미팅 참가\", \"type\" : \"update\", \"order\" : \"3\" }" },
		{ text: "input :  2024년 3월 4일 두번째 일정 오마카세가기 로 변경하고 5일 오후 7시로 바꿔줘" },
		{ text: "output :  { \"start_date\" : \"2024-03-04T00:00\", \"end_date\" : \"2024-03-04T23:50\", \"update_date\" : \"2023-03-05T19:00\", \"content\" : \"오마카세 가기\", \"type\" : \"update\", \"order\" : \"2\" }" },
		{ text: "input :   12월 25일 일정 전부 26일로 옮겨줘" },
		{ text: "output :  { \"start_date\" : \"2024-12-25T00:00\", \"end_date\" : \"2024-12-25T23:50\", \"update_date\" : \"2023-12-26T00:00\", \"content\" : \"0\", \"type\" : \"update\", \"order\" : \"all\" }" },
		{ text: "input :  12월3일 첫번째꺼 2일로" },
		{ text: "output :  { \"start_date\" : \"2024-12-03T00:00\", \"end_date\" : \"2024-12-03T23:50\", \"update_date\" : \"2024-08-02T00:00\", \"content\" : \"0\", \"type\" : \"update\", \"order\" : \"1\" }" },
		{ text: "input : " + sentence },
		{ text: "output :  " },
	];

	const result = await model.generateContent({
		contents: [{ role: "user", parts }],
		generationConfig,

	});

	const response = result.response;
	console.log(response.text());
	
	// 두 번째 AJAX 요청
	let ajax_callback = await $.ajax({
			url: "Schedule_Control",
			data: { "Schedule_call_data": response.text() }
		});
		
	if(ajax_callback=="ok"){
		
		console.log("ok");
		
	}else{
		console.log("false");
		alert("로그인이 안되어 있거나, 명령문장을 처리하지 못했습니다. 날짜를 정확히 명시해주세요(몇년 몇월 몇일).");
	}
	
	
	
	
	
	
	
	easy_prompt.value="";
	easy_prompt.style.backgroundImage = 'none';
	
	load();
}