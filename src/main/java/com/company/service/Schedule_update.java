package com.company.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.company.dao.Schedule_dao;
import com.company.dto.Schedule_dto;

public class Schedule_update implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//String conpath = request.getContextPath();
		

		Schedule_dao dao = new Schedule_dao();
		Schedule_dto dto = new Schedule_dto();

		dto.setStart_date(request.getParameter("start_date2"));
		dto.setEnd_date(request.getParameter("end_date2"));
		dto.setContent(request.getParameter("content2"));
		dto.setS_index(Integer.parseInt(request.getParameter("s_index")));
		
		if (dao.schedule_update(dto) < 0) {
			System.out.println("dao.schedule_update(dto) 실패");
			out.print("<script>alert('스케쥴 수정 실패.'); location.href='view.do';</script>");
			return;
		}
	}

}
