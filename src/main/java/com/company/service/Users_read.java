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

public class Users_read implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//String conpath = request.getContextPath();

		Users_dao dao = new Users_dao();
		Users_dto dto = new Users_dto();

		dto.setU_id(request.getParameter("id_login"));
		dto.setU_pass(request.getParameter("pass_login"));

		String remember_id = request.getParameter("remember_id");
		String remember_login = request.getParameter("remember_login");
		
		dto = dao.user_read(dto);
		
		if (dto != null) {
			
			System.out.println("dao.user_read(dto) 성공");
			
			session.setAttribute("login_id", dto.getU_id());		
			session.setAttribute("login_U_index", dto.getU_index());
			session.setAttribute("login_dto", dto);
			
			if( remember_id != null ) {
				Cookie cookie = new Cookie("remember_id", dto.getU_id());
				cookie.setMaxAge(10 * 24 * 60 * 60);  // 유효기간 10일 
				response.addCookie(cookie);
			}else {
				// 쿠키 삭제
		        Cookie[] cookies = request.getCookies();
		        if (cookies != null) {
		            for (Cookie cookie : cookies) {
		                if ("remember_id".equals(cookie.getName())) {
		                    cookie.setMaxAge(0); // 쿠키 삭제
		                    response.addCookie(cookie);
		                }
		            }
		        }
			}
			
			if( remember_login != null ) {
				Cookie cookie = new Cookie("remember_login", dto.getU_id());
				cookie.setMaxAge(10 * 24 * 60 * 60);  // 유효기간 10일
				response.addCookie(cookie);
			}

			
			
			
			
		} else {
			System.out.println("dao.user_read(dto) 실패");
			out.print("<script>alert('로그인 실패. 아이디와 비밀번호를 확인해주세요.'); location.href='view.do';</script>");
			return;
		}

	}

}
