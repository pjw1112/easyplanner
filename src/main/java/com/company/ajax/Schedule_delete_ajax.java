package com.company.ajax;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.Schedule_dao;
import com.company.dto.Schedule_dto;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Schedule_delete_ajax implements Action {

	@Override
	public int execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int result = -1;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String Schedule_data = (String) request.getParameter("Schedule_call_data");
		JsonParser parser = new JsonParser();
		JsonObject job = (JsonObject) parser.parse(Schedule_data);

		// 로그인 중인 사용자가 있을때와 없을때로 구분
		if (request.getSession().getAttribute("login_U_index") != null) {
			
			String start_date = job.get("start_date").getAsString();
			String order = job.get("order").getAsString();
			
			Schedule_dao dao = new Schedule_dao();
			Schedule_dto dto = new Schedule_dto();

			dto.setStart_date(start_date);
			dto.setU_index((Integer)request.getSession().getAttribute("login_U_index")); 
			
			if(!order.equals("all")) {
			
			dto.setS_index(Integer.parseInt(order)); //s_index 아닌데 코드 더 만들기 귀찮아서 몇번째 항목 삭제할건지(순서) 변수를 임시로 s_index에 담아서 씀
			if (dao.schedule_delete_ajax(dto) < 0) {
				System.out.println("AJAX 요청 - dao.schedule_delete(dto) 실패");
			}else {
				System.out.println("AJAX 요청 - dao.schedule_delete(dto) 성공");
				result = 1 ;
			}
			
			}else {
				
				if (dao.schedule_deleteALL_ajax(dto) < 0) {
					System.out.println("AJAX 요청 - dao.schedule_deleteALL(dto) 실패");
				}else {
					System.out.println("AJAX 요청 - dao.schedule_deleteALL(dto) 성공");
					result = 1 ;
				}	
				
				
			}
			
			
			
		}

		return result;
	}

	
}
