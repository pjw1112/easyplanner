package com.company.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.ajax.Action;
import com.company.ajax.Schedule_create_ajax;
import com.company.ajax.Schedule_delete_ajax;
import com.company.ajax.Schedule_update_ajax;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Schedule_Control
 */
@WebServlet("/Schedule_Control")
public class Schedule_Control extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Schedule_Control() {
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
		action(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		action(request, response);
	}

	protected void action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String Schedule_data = (String) request.getParameter("Schedule_call_data");
		System.out.println("아작스에게 받은 원시String : "+Schedule_data );
		JsonParser parser = new JsonParser();
		JsonObject job = (JsonObject) parser.parse(Schedule_data);
		String type = job.get("type").getAsString();

		System.out.println(job);
		System.out.println(type);
		
		Action controller;

		if (type.equals("insert")) {
			controller = new Schedule_create_ajax();
			int result = controller.execu(request, response);
			if (result == 1) {
				out.print("ok");
			} else {
				out.print("아작스 요청 - 스케쥴 생성 실패...");
			}

		} else if (type.equals("delete")) {

			controller = new Schedule_delete_ajax();
			int result = controller.execu(request, response);
			if (result == 1) {
				out.print("ok");
			} else {
				out.print("아작스 요청 - 스케쥴 삭제 실패...");
			}

			
		} else if (type.equals("update")) {
			controller = new Schedule_update_ajax();
			int result = controller.execu(request, response);
			if (result == 1) {
				out.print("ok");
			} else {
				out.print("아작스 요청 - 스케쥴 수정 실패...");
			}

			
		} else {
			System.out.println("type 값 리딩 실패");
		}

	}
}
