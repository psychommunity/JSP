package com.mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.entity.UserEntity;

/**
 * @author DAHLIA
 * 
 */
public class UserDB {

	static final String QUERY_ADD = "insert into users(id, password, username) values (?, ?, ?)";

	// 데이터베이스 연결관련 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;

	// JDBC 드라이버 로드 메소드
	public UserDB() {

		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 데이터베이스 연결 메소드
	public void connect() {
		try {
			con = ds.getConnection();
			System.out.println("connected...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 데이터베이스 연결 헤제 메소드
	public void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
				System.out.println("closed...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 파라미터로 넘어온 사용자 아이디의 정보를 가져온다.
	 * 
	 * @param uId
	 * @return
	 */
	public UserEntity getUserInfo(String uId) {

		connect();
		String SQL = "select id, password, username from users where id = ?";

		UserEntity userInfo = new UserEntity();

		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, uId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userInfo.setUserId(rs.getString("id"));
			userInfo.setUserPwd(rs.getString("password"));
			userInfo.setUserName(rs.getString("username"));

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return userInfo;
	}

	/**
	 * Method addUser
	 * 
	 * @param user
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean addUser(UserEntity user) throws SQLException {

		boolean success = false;
		connect();
		try {
			pstmt = con.prepareStatement(QUERY_ADD);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserName());
			pstmt.executeUpdate();
			success = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return success;
		} finally {
			disconnect();
		}
		return success;
	}

}
