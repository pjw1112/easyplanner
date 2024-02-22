package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.Schedule_dto;

public class Schedule_dao {

	public ArrayList<Schedule_dto>  all_schedule_read(Schedule_dto dto_input) {
		ArrayList<Schedule_dto> list = null;
		
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		String sql = "select * from schedule where U_index = ? order by start_date asc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto_input.getU_index());
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Schedule_dto>();
			
			while (rset.next()) {
				
				
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
		
		String sql = "update schedule set start_date = ?, end_date = ?, content = ? where s_index=?";

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto_input.getStart_date());
			pstmt.setString(2, dto_input.getEnd_date());
			pstmt.setString(3, dto_input.getContent());
			pstmt.setInt(4, dto_input.getS_index());
				
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
	
	public int schedule_updateAll_ajax(Schedule_dto dto_input, String update_date) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		
		//String start_date_before = dto_input.getStart_date();
		//String end_date_before = dto_input.getEnd_date();
		
		String sql ="select * from schedule where u_index=? and start_date like ?";
		try {
			System.out.println("////////////////// 1111");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto_input.getU_index());
			pstmt.setString(2, dto_input.getStart_date().split("T")[0]+"%");
			System.out.println(dto_input.getU_index());
			System.out.println(dto_input.getStart_date().split("T")[0]);
			rset= pstmt.executeQuery();
			while(rset.next()) {
				System.out.println("////////////////// 2222");
				sql = "update schedule set start_date = ?, end_date = ? where s_index=?";
				pstmt = conn.prepareStatement(sql);
				
				
				String start_date_before = rset.getString("start_date");
		        String end_date_before = rset.getString("end_date");
		        String offset = update_date.split("T")[0]+"T"+rset.getString("start_date").split("T")[1];
		        
		        // 문자열을 LocalDateTime 객체로 파싱
		        LocalDateTime sdb = LocalDateTime.parse(start_date_before, DateTimeFormatter.ISO_DATE_TIME);
		        LocalDateTime edb = LocalDateTime.parse(end_date_before, DateTimeFormatter.ISO_DATE_TIME);
		        LocalDateTime ofs = LocalDateTime.parse(offset, DateTimeFormatter.ISO_DATE_TIME);

		        // 두 날짜 사이의 차이 계산
		        Duration duration = Duration.between(sdb, ofs);

		        // 새로운 문자열로
		        String start_date_after = sdb.plus(duration).format(DateTimeFormatter.ISO_DATE_TIME);
		        String end_date_after = edb.plus(duration).format(DateTimeFormatter.ISO_DATE_TIME);
				
		        
				pstmt.setString(1, start_date_after);
				pstmt.setString(2, end_date_after);
				pstmt.setInt(3, rset.getInt("s_index"));
				System.out.println("////////////////// 3333");
				int cnt=1;
				if (pstmt.executeUpdate() > 0) {
					System.out.println("AJAX updateALL 쿼리 내부 성공 "+(cnt++)+"번째");
					result_int = 1;
				}else {
					System.out.println("AJAX updateALL 쿼리 내부 실패 ");
					result_int = -1;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public int schedule_update_ajax(Schedule_dto dto_input, String update_date, int order) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		System.out.println("....................1");
		String sql1 = "SELECT * FROM schedule WHERE u_index = ? AND start_date LIKE ? ORDER BY start_date ASC LIMIT 1 OFFSET ?";
		String sql2 = "update schedule set start_date = ?, end_date = ?, content =? where s_index=?";
		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, dto_input.getU_index());
			pstmt.setString(2, dto_input.getStart_date().split("T")[0]+"%");
			pstmt.setInt(3, order-1); 
			
			rset = pstmt.executeQuery();
			if (rset.next()) {
				
				pstmt = conn.prepareStatement(sql2);
				System.out.println("....................2");				
				String start_date_before = rset.getString("start_date");
		        String end_date_before = rset.getString("end_date");
		        String offset = update_date.split("T")[0]+"T"+rset.getString("start_date").split("T")[1];
		        
		        // 문자열을 LocalDateTime 객체로 파싱
		        LocalDateTime sdb = LocalDateTime.parse(start_date_before, DateTimeFormatter.ISO_DATE_TIME);
		        LocalDateTime edb = LocalDateTime.parse(end_date_before, DateTimeFormatter.ISO_DATE_TIME);
		        LocalDateTime ofs = LocalDateTime.parse(offset, DateTimeFormatter.ISO_DATE_TIME);

		        // 두 날짜 사이의 차이 계산
		        Duration duration = Duration.between(sdb, ofs);

		        // 새로운 문자열로
		        String start_date_after = sdb.plus(duration).format(DateTimeFormatter.ISO_DATE_TIME);
		        String end_date_after = edb.plus(duration).format(DateTimeFormatter.ISO_DATE_TIME);
				
		        String content = (dto_input.getContent().equals("0"))? rset.getString("content") : dto_input.getContent() ;
		        
				pstmt.setString(1, start_date_after);
				pstmt.setString(2, end_date_after);
				pstmt.setString(3, content);
				pstmt.setInt(4, rset.getInt("s_index"));
				System.out.println("....................3");
				
				if (pstmt.executeUpdate() > 0) {
					System.out.println("AJAX updateALL 쿼리 내부 성공 ");
					result_int = 1;
				}else {
					System.out.println("AJAX updateALL 쿼리 내부 실패 ");
					result_int = -1;
				}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		String sql = "delete from schedule where s_index=?";

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
	
	
	
	
	public int schedule_delete_ajax(Schedule_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		
		String sql1 = "SELECT s_index FROM schedule WHERE u_index = ? AND start_date LIKE ? ORDER BY start_date ASC LIMIT 1 OFFSET ?";
		String sql2 = "DELETE FROM schedule WHERE s_index = ?";

		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, dto_input.getU_index());
			pstmt.setString(2, dto_input.getStart_date().split("T")[0]+"%");
			pstmt.setInt(3, dto_input.getS_index()-1); // 유저 인덱스 아님 offset 부분 (아작스 요청이라서 일반적인 요청과는 다른(몇번째항목인지) 변수를 하나 받아야되는데 코드 추가하기 귀찮아서 그냥 임시로 u_index에 담아서 씀 (schedule_delete_ajax에서 담아서 넘김)
				
			rset = pstmt.executeQuery();
			if (rset.next()) {
				System.out.println("ajax_delete query1_삭제 대상 s_index read 성공");
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, rset.getInt("S_index"));
				
				if (pstmt.executeUpdate() > 0) {
					System.out.println("AJAX delete 쿼리 최종 성공");
					result_int = 1;
				}else {
					System.out.println("AJAX delete 쿼리 최종 실패");
				}
				
			}else {
				System.out.println("ajax_delete query1_삭제 대상 s_index 가 없음");
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
	
	public int schedule_deleteALL_ajax(Schedule_dto dto_input) {
		int result_int = -1;

		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		conn = db.getConnection();
		
		String sql = "delete FROM schedule WHERE u_index = ? AND start_date LIKE ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto_input.getU_index());
			pstmt.setString(2, dto_input.getStart_date().split("T")[0]+"%");
			
				
			if (pstmt.executeUpdate() > 0) {
					System.out.println("AJAX deleteALL 쿼리 최종 성공");
					result_int = 1;
				}else {
					System.out.println("AJAX deleteALL 쿼리 최종 실패");
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
