package com.company.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.company.dao.Schedule_dao;
import com.company.dto.Schedule_dto;

public class Schedule_delete implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//String conpath = request.getContextPath();
		
		
		Schedule_dao dao = new Schedule_dao();
		Schedule_dto dto = new Schedule_dto();

		dto.setS_index(Integer.parseInt(request.getParameter("s_index")));		
		

		if (dao.schedule_delete(dto) < 0) {
			System.out.println("dao.schedule_delete(dto) 실패");
			out.print("<script>alert('스케쥴 삭제 실패. 아이디 비번 확인'); location.href='view.do';</script>");  //history.go(-1) 써도 됨
			return;
		}else {
			System.out.println("dao.schedule_delete(dto) 성공 - 스케쥴 데이터 삭제");
			
			return;
		}
	}


}
