package com.company.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.company.dao.Users_dao;
import com.company.dto.Users_dto;

public class Users_create implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String conpath = request.getContextPath();

		// 가입 시간 얻기
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String formattedDateTime = currentDateTime.format(formatter);

		Users_dao dao = new Users_dao();
		Users_dto dto = new Users_dto();

		dto.setU_id(request.getParameter("id_join"));
		dto.setU_pass(request.getParameter("pass_join"));
		dto.setU_email(request.getParameter("email_join"));
		dto.setU_birth(request.getParameter("birth_join"));
		dto.setU_join_date(formattedDateTime);
		dto.setU_grade(4); // 디폴트 회원 등급

		if (dao.user_create(dto) < 0) {
			System.out.println("dao.user_create(dto) 실패");
			out.print("<script>alert('회원가입 실패. 이미 존재하는 아이디'); location.href='view.do';</script>"); // history.go(-1) 써도
			return;
		}
	}

}
