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

public class Schedule_create_ajax implements Action {

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
			String end_date = job.get("end_date").getAsString();
			String content = job.get("content").getAsString();
			
			// 현재 시간 얻기
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			String formattedDateTime = currentDateTime.format(formatter);

			Schedule_dao dao = new Schedule_dao();
			Schedule_dto dto = new Schedule_dto();

			dto.setStart_date(start_date);
			dto.setEnd_date(end_date);
			dto.setContent(content);
			dto.setCreate_date(formattedDateTime);
			dto.setIp(InetAddress.getLocalHost().getHostAddress());
			dto.setU_index((Integer)request.getSession().getAttribute("login_U_index")); 

			if (dao.schedule_create(dto) < 0) {
				System.out.println("AJAX 요청 - dao.schedule_create(dto) 실패");
				
				
			}else {
				System.out.println("AJAX 요청 - dao.schedule_create(dto) 성공");
				result = 1 ;
			}
			
			
			
		}

		return result;
	}

}
