package com.nuc.jdbc;
/*
 * 普通用户在登入界面时对数据库进行连接
 */

import java.sql.SQLException;
import java.util.HashMap;

public class jdbcUserLogin {
	private static HashMap<String,String> people = new HashMap<String,String>();
	private static HashMap<String,String> people2 = new HashMap<String,String>();
	
	public static boolean judge(String user,String pass){
		String SQL = "SELECT Grtele, Gstele FROM Goods";
		boolean flag = false;
		try {
			jdbcDrive.jdbcConnection();
			jdbcDrive.statement = jdbcDrive.connection.createStatement();
			jdbcDrive.resultset = jdbcDrive.statement.executeQuery(SQL);
		    while (jdbcDrive.resultset.next()) {
		    	people.put(jdbcDrive.resultset.getString(1).trim(), jdbcDrive.resultset.getString(1).trim());
		    	people2.put(jdbcDrive.resultset.getString(2).trim(), jdbcDrive.resultset.getString(2).trim());
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}
		if (people.containsKey(user)){
			if (people.get(user).equals(pass)){
				flag = true;
			}
		}else{
			flag = false;
		}
		if (people2.containsKey(user)){
			if (people2.get(user).equals(pass)){
				flag = true;
			}
		}else{
			flag = false;
		}
		return flag;
	}

}

