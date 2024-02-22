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

public class Users_delete implements Action {

	@Override
	public void execu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("서비스 유저 딜리트 시작");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//String conpath = request.getContextPath();
		HttpSession session = request.getSession();
		
		Users_dao dao = new Users_dao();
		Users_dto dto = new Users_dto();

		dto.setU_id(request.getParameter("id"));
		dto.setU_pass(request.getParameter("pass"));
		System.out.println("전달받은 값 id : "+dto.getU_id());
		System.out.println("전달받은 값 pass : "+dto.getU_pass());

		
		if (dao.user_delete(dto) < 0) {
			System.out.println("dao.user_delete(dto) 실패");
			out.print("0");  //history.go(-1) 써도 됨
			System.out.println("서비스 유저 딜리트 끝-실패");
			return;
		}else {
			System.out.println("dao.user_delete(dto) 성공 - 유저 데이터 삭제");
			session.invalidate();
			// 쿠키 삭제
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("remember_login".equals(cookie.getName())) {
	                    cookie.setMaxAge(0); // 쿠키 삭제
	                    response.addCookie(cookie);
	                }
	            }
	        }
	        System.out.println("서비스 유저 딜리트 끝-성공");
	        out.print("1"); 
			return;
		}
		
		
	}


}
