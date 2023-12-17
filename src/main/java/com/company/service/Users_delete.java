package com.company.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.Users_dao;
import com.company.dto.Users_dto;

public class Users_delete implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String conpath = request.getContextPath();
		HttpSession session = request.getSession();
		
		Users_dao dao = new Users_dao();
		Users_dto dto = new Users_dto();

		dto.setU_id(request.getParameter("id_delete"));
		dto.setU_pass(request.getParameter("pass_delete"));
		

		if (dao.user_delete(dto) < 0) {
			System.out.println("dao.user_delete(dto) 실패");
			out.print("<script>alert('회원 탈퇴 실패. 아이디 비번 확인'); location.href='users_delete_view.do';</script>");  //history.go(-1) 써도 됨
			return;
		}else {
			System.out.println("dao.user_delete(dto) 성공 - 유저 데이터 삭제");
			session.invalidate();
			return;
		}
	}


}
