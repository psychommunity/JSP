package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.entity.UserEntity;
import com.mvc.model.UserDB;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		loginProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		loginProcess(request, response);
	}

	private void loginProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDB userDB = null; // model class
		UserEntity userInfo = null; // entity class
		String viewPage = ""; // 로그인 처리 후 이동할 view page

		// 로그인 양식이 있는 페이지(view)에서 파라미터로 넘어온 아이디와 패스워드를 변수에 할당.
		String uId = request.getParameter("userId").trim();
		String uPwd = request.getParameter("userPwd").trim();

		// 해당 아이디의 정보를 가져오기 위해 model x클래스의 getUserInfo() 호출.
		userDB = new UserDB();
		userInfo = userDB.getUserInfo(uId);

		// 사용자가 입력한 아이디와 패스워드가 실제 database table에 저장된 아이디,패스워드가 일치하는지 파악
		if (uId.equals(userInfo.getUserId())
				&& uPwd.equals(userInfo.getUserPwd())) {

			// 참고로..getSession()은 기존에 세션이 존재하면 기존세션은 반환하고 새로운 세션을 만든다.
			// 로그인통과시 세션객체에 사용자 정보를 담는다.
			HttpSession session = request.getSession();

			session.setAttribute("userId", userInfo.getUserId());
			session.setAttribute("userNm", userInfo.getUserName());

			// 로그인 성공시 main.jsp로 이동
			viewPage = "main.jsp";

			// 새 세션 만들지않고 소멸되면 메인으로 이동..
			response.sendRedirect("main.jsp");
		} else {
			// 로그인 실패시 초기 페이지로 이동.
			// 이동시 로그인정보가 잘못되었다는 메세지를request 에 담아서 페이지로 이동.
			request.setAttribute("FAILMSG", "로그인정보를 잘못 입력하셨습니다");

			viewPage = "login.jsp";

			// 이게 필요한가???
			RequestDispatcher view = request.getRequestDispatcher(viewPage);
			view.forward(request, response);
		}

		/*
		 * RequestDispatcher view = request.getRequestDispatcher(viewPage);
		 * view.forward(request, response);
		 */

	}

}
