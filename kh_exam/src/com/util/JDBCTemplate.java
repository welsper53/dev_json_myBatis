package com.util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import mvc.kh.DBConnectionMgr;

public class JDBCTemplate {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL    = "jdbc:oracle:thin:@128.168.25.30:1521:XE";
	public static final String USER   = "student";
	public static final String PWD     = "student";
	private static JDBCTemplate jdbcT = null;
	
	public JDBCTemplate() {
	}
	public static JDBCTemplate getInstance() {
		if(jdbcT == null) {
			jdbcT = new JDBCTemplate();
		}
		return jdbcT;
	}

	public static Connection getConnect() {
		Connection con = null;
		Properties prop = new Properties();

		try {
			String currentPath = JDBCTemplate.class.getResource("./").getPath();
			prop.load(new BufferedReader(new FileReader(currentPath + "DRIVER.properties")));
			Class.forName(prop.getProperty("DRIVER"));
			con = DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("USER"),
					prop.getProperty("PWD"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void close(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection con) {
		try {
			if (con != null && !con.isClosed()) { 
				con.commit(); 
			}
		} catch (SQLException e) { 
			e.printStackTrace(); 
			}
		}

	public static void rollback(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
