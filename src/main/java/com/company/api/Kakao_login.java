package com.company.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Kakao_login
 */
@WebServlet("/Kakao_login")
public class Kakao_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kakao_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String code = request.getParameter("code");
		String error = request.getParameter("error");
		String error_description = request.getParameter("error_description");
		
		System.out.println("code : "+code);
		System.out.println("error : " +error);
		System.out.println("error_description : "+error_description);
		
		// 인가 코드 받기
		//////////////////////////////////////////////////////////////////////
		// 토큰 받기 
		
		String urlapi = "https://kauth.kakao.com/oauth/token";

		String parameter ="?grant_type=authorization_code" +
				"&client_id=5311dcef4df09cce6029d72479addf8c" +
				"&redirect_uri=http://localhost:8080/easy_planner/Kakao_login" +
				"&code="+code;
		
		urlapi+= parameter;
		URL url = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;

		url = new URL(urlapi);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true); //서버한테 전달
		conn.setDoOutput(true);
				
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(parameter);
		out.close();
		
		
		
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
		System.out.println("로긴서블릿1페이지 결과 : "+result);
		// JSON 문자열을 JsonElement로 파싱
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(result);
        // JsonElement를 JsonObject로 변환
        if (jsonElement.isJsonObject()) {
            JsonObject j_ob = jsonElement.getAsJsonObject();
            
            String access_token = j_ob.get("access_token").toString();
            
            request.setAttribute("access_token", access_token);
            request.getSession().setAttribute("access_token", access_token);
           
            request.getRequestDispatcher("/Kakao_login2").forward(request, response);
             
        }
        
         //   JsonObject 내의 모든 키-값 출력
         //   for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
         //      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
         //   }
         //   } else {
         //   System.out.println("Given JSON is not an object.");
         //   }

        br.close();
		conn.disconnect();

	
		
		//request.getRequestDispatcher("success.jsp").forward(request, response);
		

		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
