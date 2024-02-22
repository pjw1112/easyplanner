package com.company.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Get_loginUserId
 */
@WebServlet("/id_find2")
public class Id_find2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Id_find2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		action( request, response);
	}
	
	protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String email4number = (String)session.getAttribute("email4number");
		String input4number = request.getParameter("number");
		
		System.out.println("email4number : " + email4number);
		System.out.println("input4number : " + input4number);
		System.out.println("일치 여부 : " + (input4number==email4number));

		if (  email4number != null) {

			if (input4number != null) {

				if (input4number.trim().equals(  email4number)) {

					session.removeAttribute("email4number");
					

					System.out.println("...............All Email verification SuCCESS");

					out.print("2000");

				} else {
					System.out.println("...............Email verification FAIL : CAUSE input4number != email4number ");
					out.print("2001");
				}

			} else {
				System.out.println("...............Email verification FAIL : CAUSE input4number = null ");
				out.print("2001");
			}

		} else {
			System.out.println("...............Email verification FAIL : CAUSE email4number = null");
			out.print("2001");

		}
		
		
		
		
	}

}
