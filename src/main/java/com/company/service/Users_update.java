package com.company.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.Users_dao;
import com.company.dto.Users_dto;

public class Users_update implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//String conpath = request.getContextPath();
		HttpSession session = request.getSession();
		
		Users_dao dao = new Users_dao();
		Users_dto dto = new Users_dto();

		dto.setU_id(request.getParameter("id"));
		dto.setU_pass(request.getParameter("pass"));
		dto.setU_email(request.getParameter("email"));
		dto.setU_birth(request.getParameter("birth"));
		

		if (dao.user_update(dto) < 0) {
			System.out.println("dao.user_update(dto) 실패");
			out.print("<script>alert('회원 정보 수정 실패. 아이디 비번 확인'); location.href='users_info.do';</script>");  //history.go(-1) 써도 됨
			return;
		}else {
			System.out.println("dao.user_update(dto) 성공");
			dto=dao.user_read_byID(dto);
			session.setAttribute("login_id", dto.getU_id());		
			session.setAttribute("login_U_index", dto.getU_index());
			session.setAttribute("login_dto", dto);
			
			return;
		}
	}


}
