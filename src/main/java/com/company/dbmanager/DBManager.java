package com.company.dbmanager;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	public static Connection conn;

	public Connection getConnection() {
		try {
			// 1. server.xml
			Context initContext = new InitialContext();

			// 2. 환경설정
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			// 3. DataSource - jdbc/mbasic
//			DataSource db = (DataSource) envContext.lookup("jdbc/easy_planner");
			DataSource db = (DataSource) envContext.lookup("jdbc/jwpdev");
			// 4. Connection
			conn = db.getConnection();
			
			if (conn != null) {
				System.out.println(".... DBMANAGER 연동성공!");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return conn;

	}

}
