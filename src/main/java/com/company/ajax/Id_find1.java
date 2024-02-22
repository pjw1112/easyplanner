package com.company.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Get_loginUserId
 */
@WebServlet("/id_find1")
public class Id_find1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Id_find1() {
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
		
		System.out.println("...............비번 찾기 이메일 전송 시작");
		
		String user_id = request.getParameter("email_f");
		
		System.out.println(user_id);
		// 1. 보내는 쪽
		String host = "smtp.naver.com";
		String user = "bananasyndro@naver.com"; // 본인 naver 아이디 (이메일형식)
		String password = "Ogsily121@"; // 본인 naver 아이디 비밀번호

		// 2. 받는 사람
		String to = user_id;
		/* String to = "bananasyndro@naver.com"; */
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getDefaultInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(user, password);
			}

		});

		MimeMessage message = new MimeMessage(session);
		try {
			Random random = new Random();

			int randomNumber = random.nextInt(10000);

			String formattedNumber = String.format("%04d", randomNumber);
			System.out.println("4자리 난수 : " + formattedNumber);

			request.getSession().setAttribute("email4number", formattedNumber);

			message.setFrom(new InternetAddress(user)); // 보내는 사람
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// 받는사람

			message.setSubject("이지 플래너 아이디 찾기 서비스 입니다.");
			message.setContent(""

					+ "<h3>인증번호를 확인해주세요</h3><br>" + "<p>[ " + formattedNumber + " ]</p><br><br><br>" + "</div>",
					"text/html; charset=euc-kr");

			Transport.send(message);
			System.out.println("........... Successfully ......................");

			
			out.print("<script>alert('" + user_id + "로 인증번호 메일을 전송했습니다.');</script>");
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("2001");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("2001");
		}

		
		
	}

}
