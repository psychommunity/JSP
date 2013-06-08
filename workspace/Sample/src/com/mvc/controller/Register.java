package com.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			register(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// TODO Auto-generated method stub
		UserDB userDB = null; // model class
		UserEntity userInfo = null; // entity class
		String viewPage = ""; // 로그인 처리 후 이동할 view page

		userDB = new UserDB();
		boolean flag = false;

		userInfo = new UserEntity();
		userInfo.setUserId(request.getParameter("id"));
		userInfo.setUserName(request.getParameter("name"));
		userInfo.setUserPwd(request.getParameter("password"));

		if ((request.getParameter("id").equals(""))
				|| (request.getParameter("name").equals(""))
				|| (request.getParameter("password").equals(""))) {
			request.setAttribute("FAILMSG", "회원가입 정보를 잘못 입력하셨습니다");
			viewPage = "form.jsp";

		} else {

			flag = userDB.addUser(userInfo);
			System.out.println(flag);

			// 로그인 성공시 main.jsp로 이동
			viewPage = "main.jsp";
		}

		RequestDispatcher view = request.getRequestDispatcher(viewPage);
		view.forward(request, response);
	}

}
