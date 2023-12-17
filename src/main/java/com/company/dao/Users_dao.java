package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.company.dbmanager.DBManager;
import com.company.dto.Users_dto;

public class Users_dao {

	public Users_dto user_read(Users_dto dto_input) {
		Users_dto dto_output = null;
		
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		String sql = "select * from users where u_id=? and u_pass=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto_input.getU_id());
			pstmt.setString(2, dto_input.getU_pass());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				System.out.println("rest.next()안쪽 진입");
				dto_output = new Users_dto();
				dto_output.setU_index( rset.getInt("u_index"));
				dto_output.setU_id( rset.getString("u_id"));
				dto_output.setU_pass( rset.getString("u_pass"));
				dto_output.setU_email( rset.getString("u_email"));
				dto_output.setU_birth( rset.getString("u_birth"));
				dto_output.setU_join_date( rset.getString("u_join_date"));
				dto_output.setU_grade( rset.getInt("u_grade"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return dto_output;
	}

	public int user_create(Users_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		String sql1 = "select * from users where u_id=?";
		String sql2 = "insert into users (u_id, u_pass, u_email, u_birth, u_join_date, u_grade) values (?,?,?,?,?,?)";

		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, dto_input.getU_id());
			rset = pstmt.executeQuery();
			
			if(!rset.next()) {
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, dto_input.getU_id());
			pstmt.setString(2, dto_input.getU_pass());
			pstmt.setString(3, dto_input.getU_email());
			pstmt.setString(4, dto_input.getU_birth());
			pstmt.setString(5, dto_input.getU_join_date());
			pstmt.setInt(6, dto_input.getU_grade());
			
			if (pstmt.executeUpdate() > 0) {
				System.out.println("Users_dao > user_create > insert 쿼리 문 성공");
				result_int = 1;
			}else {
				System.out.println("Users_dao > user_create > insert 쿼리 문 실패");
			}
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return result_int;
	}

	public int user_update(Users_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		
		String sql = "update users set u_email=?, u=birth=? where u_id=? and u_pass=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto_input.getU_email());
			pstmt.setString(2, dto_input.getU_birth());
			pstmt.setString(3, dto_input.getU_id());
			pstmt.setString(4, dto_input.getU_pass());
			if (pstmt.executeUpdate() > 0) {
				result_int = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return result_int;
	}
	
	

	public int user_delete(Users_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		String sql = "delete from users where u_id=? and u_pass=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto_input.getU_id());
			pstmt.setString(2, dto_input.getU_pass());

			if (pstmt.executeUpdate() > 0) {
				result_int = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return result_int;
	}
}
