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
	    	System.out.println("���ݿ��������سɹ�");
		}catch(Exception e) {
			System.out.println("JDBC��������ʧ��");
		}
		try {
	    	connection = DriverManager.getConnection(jdbc.dbURL, jdbc.userName, jdbc.userPassword);
	    	System.out.println("���ݿ����ӳɹ�");
		}catch(Exception e) {
			System.out.println("SQL Server ����ʧ��");
		}
	}
	
	public static void jdbcConnectionClose() {
		try {
			connection.close();
			System.out.println("���ݿ����ӹرճɹ�");
		}catch(SQLException e) {
			System.out.println("���ݿ����ӹر�ʧ��");
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
