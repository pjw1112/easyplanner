package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.Schedule_dto;

public class Schedule_dao {

	public ArrayList<Schedule_dto>  user_read(Schedule_dto dto_input) {
		ArrayList<Schedule_dto> list = null;
		
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		String sql = "select * from Schedule where U_index = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto_input.getU_index());
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list = new ArrayList<Schedule_dto>();
				
				list.add(new Schedule_dto(
						rset.getInt("S_index"),
						rset.getString("start_date"),
						rset.getString("end_date"),
						rset.getString("content"),
						rset.getString("create_date"),
						rset.getString("ip"),
						rset.getInt("U_index")
						
						));
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

		return list;
	}

	public int schedule_create(Schedule_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		
		String sql = "insert into schedule (start_date, end_date, content, create_date, ip, u_index) values (?,?,?,?,?,?)";

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto_input.getStart_date());
			pstmt.setString(2, dto_input.getEnd_date());
			pstmt.setString(3, dto_input.getContent());
			pstmt.setString(4, dto_input.getCreate_date());
			pstmt.setString(5, dto_input.getIp());
			pstmt.setInt(6, dto_input.getU_index());
				
			if (pstmt.executeUpdate() > 0) {
				System.out.println("Schedule_dao > schedule_create > insert 쿼리 문 성공");
				result_int = 1;
			}else {
				System.out.println("Schedule_dao > schedule_create > insert 쿼리 문 실패");
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

	public int schedule_update(Schedule_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		
		String sql = "update Schedule set start_date = ?, end_date = ?, content = ? where s_index=?";

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto_input.getStart_date());
			pstmt.setString(2, dto_input.getEnd_date());
			pstmt.setString(3, dto_input.getContent());
			pstmt.setInt(4, dto_input.getU_index());
				
			if (pstmt.executeUpdate() > 0) {
				System.out.println("Schedule_dao > schedule_update > update 쿼리 문 성공");
				result_int = 1;
			}else {
				System.out.println("Schedule_dao > schedule_update > update 쿼리 문 실패");
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
	
	

	public int schedule_delete(Schedule_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		
		String sql = "delete from Schedule where s_index=?";

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto_input.getS_index());
				
			if (pstmt.executeUpdate() > 0) {
				System.out.println("Schedule_dao > schedule_delete > delete 쿼리 문 성공");
				result_int = 1;
			}else {
				System.out.println("Schedule_dao > schedule_delete > delete 쿼리 문 실패");
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
