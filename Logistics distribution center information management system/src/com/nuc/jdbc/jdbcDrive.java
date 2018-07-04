package com.nuc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcDrive {
	public static Connection connection;
	public static Statement statement;
	public static ResultSet resultset;
	
	public static void jdbcConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	System.out.println("数据库驱动加载成功");
		}catch(Exception e) {
			System.out.println("JDBC驱动加载失败");
		}
		try {
	    	connection = DriverManager.getConnection(jdbc.dbURL, jdbc.userName, jdbc.userPassword);
	    	System.out.println("数据库连接成功");
		}catch(Exception e) {
			System.out.println("SQL Server 连接失败");
		}
	}
	
	public static void jdbcConnectionClose() {
		try {
			connection.close();
			System.out.println("数据库连接关闭成功");
		}catch(SQLException e) {
			System.out.println("数据库连接关闭失败");
		}
	}
	
	public static void jdbcExecuteUpdate(String s) throws SQLException{
		jdbcConnection();
		statement = connection.createStatement();
		statement.executeUpdate(s);
	}
	
	public static void jdbcExecuteQuery(String s) throws SQLException{
		jdbcConnection();
		statement = connection.createStatement();
		resultset = statement.executeQuery(s);
	}
	
	public static void main(String[] args) {
		//jdbcConnection();
	}
}
