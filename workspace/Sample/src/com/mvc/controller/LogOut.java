package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}



	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logOutProcess(request, response);
	}

	private void logOutProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String viewPage = "logout.jsp"; // 로그아웃 처리후 이동할 페이지
		// getSession(false)는 세션 객체가 있으면 존재하는 세션을 반환하고 없으면 null을 반환한다.
		HttpSession session = request.getSession(false);
		// 세션에 로그인 정보가 남아있을경우
		if (session != null) {
			session.invalidate();
		}

		RequestDispatcher view = request.getRequestDispatcher(viewPage);
		view.forward(request, response);

	}

}
