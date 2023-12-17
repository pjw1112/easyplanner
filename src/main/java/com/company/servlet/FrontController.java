package com.company.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.service.Action;
import com.company.service.Schedule_create;
import com.company.service.Users_create;
import com.company.service.Users_delete;
import com.company.service.Users_read;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action(request, response);
	}

	protected void action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Action controller;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		
		String path = request.getServletPath();
		String url =  "view.do";
		String msg = "관리자에게 문의 바랍니다.";

		if (path.equals("/view.do")) { // index에서 메인(view.jsp) 페이지로

			url= "pages/view.jsp";
			request.getRequestDispatcher(url).forward(request, response);
			
			System.out.println("세션에 저장된 id 값 : " + session.getAttribute("login_id")); 
		}else if (path.equals("/users_join.do")) { // 유저 회원가입

			controller = new Users_create();
			controller.execu(request, response);
			
			msg ="회원 가입 성공!";
			
		}else if (path.equals("/users_login.do")) { // 유저 로그인

			controller = new Users_read();
			controller.execu(request, response);
			
			msg = session.getAttribute("login_id")+" 유저로 로그인 되었습니다.";

		}else if (path.equals("/users_logout.do")) { // 유저 로그아웃

			session.invalidate();
			
			msg ="로그 아웃";

		}else if (path.equals("/users_info.do")) { // 유저 내 정보 보기 페이지로 이동

			
			url = "pages/userinfo.jsp";
			request.getRequestDispatcher(url).forward(request, response);

		}else if (path.equals("/users_delete_view.do")) { // 유저 회원 탈퇴 페이지로 이동

			url = "pages/user_delete.jsp";
			request.getRequestDispatcher(url).forward(request, response);

		
		}else if (path.equals("/users_delete.do")) { // 유저 회원 탈퇴 기능

			controller = new Schedule_create();
			controller.execu(request, response);
			
			
			msg ="회원 정보가 삭제되었습니다.";
		
		}else if (path.equals("/schedule_create.do")) { // 스케쥴 생성 

			if(request.getSession().getAttribute("login_U_index")!= null) {
			controller = new Schedule_create();
			controller.execu(request, response);
			
			msg ="스케쥴 생성 성공!";
			}else {
			msg ="로그인 해주세요.";	
			}
		}
		
		
		
		
		
//		 else if (path.equals("/write_view.do")) { // insert
//
//			url = "/board/write.jsp";
//			request.getRequestDispatcher(url).forward(request, response);
//
//		} else if (path.equals("/write.do")) { // detail
//
//			controller = new BWrite();
//			controller.execu(request, response);
//			msg = "글쓰기에 성공했습니다.";
//			url = "list.do";
//
//		} else if (path.equals("/detail.do")) { // delete
//			
//			// 조회수를 먼저 올린다음에
//			// 상세보기로 넘어 가야 함
//			// location.href
//			
//			controller = new BHit();
//			controller.execu(request, response);
//			
//			controller = new BDetail();
//			controller.execu(request, response);
//			url = "board/detail.jsp";
//			request.getRequestDispatcher(url).forward(request, response);
//
//		} else if (path.equals("/edit_view.do")) { // update
//
//			controller = new BDetail();
//			controller.execu(request, response);
//			
//			url = "board/edit.jsp";
//			request.getRequestDispatcher(url).forward(request, response);
//
//		} else if (path.equals("/edit.do")) { // update
//
//			controller = new BEdit();
//			controller.execu(request, response);
//
//			msg = "수정 성공";
//			url = "list.do";
//			
//		} else if (path.equals("/delete_view.do")) { // delete
//
//			url = "board/delete.jsp";
//			request.getRequestDispatcher(url).forward(request, response);
//
//		} else if (path.equals("/delete.do")) { // delete
//
//			controller = new BDelete();
//			controller.execu(request, response);
//
//			msg = "삭제 성공";
//			url = "list.do";
//		}
//
		out.print("<script>alert('" + msg + "'); location.href='" + url + "';</script>");
	}

}
