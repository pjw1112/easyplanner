package com.company.api;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.Users_dao;
import com.company.dto.Users_dto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Kakao_login
 */
@WebServlet("/Kakao_login2")
public class Kakao_login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kakao_login2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿2진입");
		
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String access_token = (String)request.getAttribute("access_token");
		
		//////////////////////////////////////////////////////////////////////
		// 사용자 정보 받기
		
		String urlapi = "https://kapi.kakao.com/v2/user/me";
		URL url = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;

		url = new URL(urlapi);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization","Bearer "+ access_token);
		conn.setRequestProperty("Content-type", "x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true); //서버한테 전달
		conn.setDoOutput(true);
		
		if (conn.getResponseCode() == 200) {
			System.out.println("정상실행");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			System.out.println("비정상실행");
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		
		String line = "";
		StringBuffer buffer = new StringBuffer();
		
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		
		String result =  buffer.toString();
		System.out.println("===========================");
		System.out.println("로긴서블릿2페이지 결과 : "+result);
        JsonParser parser = new JsonParser();
        
        JsonObject obj = (JsonObject)parser.parse(result);
		
        if(obj != null) {//카카오 로긴 성공 > 코드, 아이디 모두 수령 완료
        	
        String kakao_id = obj.get("id").getAsString();
        String kakao_email = obj.get("kakao_account").getAsJsonObject().get("email").getAsString();
        String birthday = obj.get("kakao_account").getAsJsonObject().get("birthday").getAsString();
        birthday = "2000-"+birthday.substring(0, 2)+"-"+birthday.substring(2);
        
        Users_dao dao = new Users_dao();
        Users_dto dto = new Users_dto();
        
        dto.setU_kakaoid(kakao_id);
        dto = dao.user_read_byKakao(dto);
        
        if(dto == null) { //카카오 로긴 처음 하는거라서 아이디 생성
        	
        	// 가입 시간 얻기
    		LocalDateTime currentDateTime = LocalDateTime.now();
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    		String formattedDateTime = currentDateTime.format(formatter);
    		
    		dto = new Users_dto();
    		dto.setU_id(kakao_id);
    		dto.setU_pass("1234");
    		dto.setU_email(kakao_email);
    		dto.setU_birth(birthday);
    		dto.setU_join_date(formattedDateTime);
    		dto.setU_grade(4); // 디폴트 회원 등급
    		dto.setU_kakaoid(kakao_id);

    		if (dao.user_create_byKakao(dto) < 0) {
    			System.out.println("dao.user_create_byKakao(dto) 실패");
    		  //out.print("<script>alert('회원가입 실패. 이미 존재하는 아이디'); location.href='view.do';</script>"); 
    			return;
    		}
    	 }//end 카카오 로긴 처음 하는거라서 아이디 생성
        dto = new Users_dto();
        dto.setU_kakaoid(kakao_id);
        
        dto = dao.user_read_byKakao(dto);
        
        request.getSession().setAttribute("login_id", dto.getU_id());
        request.getSession().setAttribute("login_U_index", dto.getU_index());
        
        
        
        }
		br.close();
		conn.disconnect();

	
		
		//request.getRequestDispatcher("view.do").forward(request, response);
		out.print("<script>alert('카카오로 로그인 성공'); location.href='view.do';</script>");

		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
