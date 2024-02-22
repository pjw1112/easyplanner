package com.company.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.Schedule_dao;
import com.company.dto.Schedule_dto;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Schedule_update_ajax implements Action {

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
			String update_date = job.get("update_date").getAsString();
			String update_content = job.get("content").getAsString();
			
			String order =  job.get("order").getAsString();

			Schedule_dao dao = new Schedule_dao();
			Schedule_dto dto = new Schedule_dto();

			dto.setStart_date(start_date);
			dto.setU_index((Integer) request.getSession().getAttribute("login_U_index"));
			dto.setContent(update_content);
			if (order.equals("all")) {

				// 전체 변경일때
				if (dao.schedule_updateAll_ajax(dto, update_date) < 0) {
					System.out.println("AJAX 요청 - dao.schedule_updateALL(dto) 실패");
				} else {
					System.out.println("AJAX 요청 - dao.schedule_updateALL(dto) 성공");
					result = 1;
				}

				
			} else {
				
				// 전체 변경 아닐때
				if (dao.schedule_update_ajax(dto, update_date, Integer.parseInt(order) ) < 0) {
					System.out.println("AJAX 요청 - dao.schedule_update(dto) 실패");
				} else {
					System.out.println("AJAX 요청 - dao.schedule_update(dto) 성공");
					result = 1;
				}
				
			}

		}

		return result;
	}

}
