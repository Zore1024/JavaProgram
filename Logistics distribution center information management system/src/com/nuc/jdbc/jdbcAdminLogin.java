package com.nuc.jdbc;
/*
 * 管理员(仓库管理员,系统管理员)在登入界面时对数据库进行连接
 * 当Id为true时是系统管理员,为false时是仓库管理员
 */

import java.sql.SQLException;
import java.util.HashMap;

public class jdbcAdminLogin {
	private static HashMap<String,String> admin = new HashMap<String,String>();
	/*
	 * 函数作用判断管理员登入身份
	 * flag返回0时,表示登入失败
	 * flag返回1时，表示以系统管理员身份登入
	 * flag放回-1时,表示以仓库管理员身份登入
	*/
	public static int judge(String user,String pass, boolean Id){
		String SQL1 = "SELECT* FROM Admin WHERE Id = 1";
		String SQL2 = "SELECT* FROM Admin WHERE Id = 0";
		int flag = 2;

		if (Id == true) {
		try {
			jdbcDrive.jdbcConnection();
			jdbcDrive.statement = jdbcDrive.connection.createStatement();
			jdbcDrive.resultset = jdbcDrive.statement.executeQuery(SQL1);
		    while (jdbcDrive.resultset.next()) {
		    	admin.put(jdbcDrive.resultset.getString(1).trim(), jdbcDrive.resultset.getString(2).trim());
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}

			if (admin.containsKey(user)){
				if (admin.get(user).equals(pass)){
					return flag = 1;
				}
			}else{
				flag = 0;
		}
			}else {

			}

		//第二阶段
		admin.clear();//清除admin中所用的映射
		if (Id == false) {
		try {
			jdbcDrive.jdbcConnection();
			jdbcDrive.statement = jdbcDrive.connection.createStatement();
			jdbcDrive.resultset = jdbcDrive.statement.executeQuery(SQL2);
		    while (jdbcDrive.resultset.next()) {
		    	admin.put(jdbcDrive.resultset.getString(1).trim(), jdbcDrive.resultset.getString(2).trim());
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}
		if (admin.containsKey(user)){
			if (admin.get(user).equals(pass)){
				return flag = -1;
				}
			}else{
				flag = 0;
			}
		}

		return flag;
	}

}
