package com.company.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.Schedule_dao;
import com.company.dto.Schedule_dto;
import com.google.gson.Gson;

/**
 * Servlet implementation class Get_UserAllSchedule
 */
@WebServlet("/Get_UserAllSchedule")
public class Get_UserAllSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Get_UserAllSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		action( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		action( request, response);

	}

	protected void action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		Schedule_dao dao = new Schedule_dao();
		Schedule_dto dto = new Schedule_dto();
		dto.setU_index(Integer.parseInt(request.getParameter("login_U_index")));

		// Gson 객체 생성
		Gson gson = new Gson();

		// ArrayList를 JSON 형식의 문자열로 변환
		String jsonString = gson.toJson(dao.all_schedule_read(dto));

		// 결과 출력
		out.print(jsonString);
	}

}
