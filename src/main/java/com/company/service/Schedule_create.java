package com.company.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.company.dao.Schedule_dao;
import com.company.dto.Schedule_dto;

public class Schedule_create implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String conpath = request.getContextPath();
		
		// 현재 시간 얻기
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String formattedDateTime = currentDateTime.format(formatter);

		Schedule_dao dao = new Schedule_dao();
		Schedule_dto dto = new Schedule_dto();

		dto.setStart_date(request.getParameter("start_date"));
		dto.setEnd_date(request.getParameter("end_date"));
		dto.setContent(request.getParameter("content"));
		dto.setCreate_date(formattedDateTime);
		dto.setIp(InetAddress.getLocalHost().getHostAddress());
		dto.setU_index((Integer)request.getSession().getAttribute("login_U_index")); 

		if (dao.schedule_create(dto) < 0) {
			System.out.println("dao.schedule_create(dto) 실패");
			out.print("<script>alert('스케쥴 생성 실패.'); location.href='view.do';</script>"); // history.go(-1) 써도
			return;
		}
	}

}
