package com.company.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.Users_dao;
import com.company.dto.Users_dto;

public class Users_Cookie_login implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String cookie_login_id = (String)request.getAttribute("cookie_login_id");
		

		Users_dao dao = new Users_dao();
		Users_dto dto = new Users_dto();

		dto.setU_id(cookie_login_id );
		System.out.println("dao.user_read_byCookie(dto)에 인풋한 dto 값 : "+dto);
		dto = dao.user_read_byCookie(dto);
		System.out.println("dao.user_read_byCookie(dto)로 리턴한 dto 값 : "+dto);
		if (dto != null) {
			
			System.out.println("dao.user_read_byCookie(dto) 성공");
			
			session.setAttribute("login_id", dto.getU_id());		
			session.setAttribute("login_U_index", dto.getU_index());
			session.setAttribute("login_dto", dto);
			
		} else {
			System.out.println("dao.user_read_byCookie(dto) 실패");
			out.print("<script>alert('쿠키를 이용한 로그인에 실패했습니다.'); location.href='/pages/view.jsp';</script>");
			return;
		}

	}

}
