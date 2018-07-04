package com.nuc.wuliuinterface;

import java.sql.SQLException;
import java.util.HashMap;

import com.nuc.jdbc.jdbcDrive;

/*
 * 计算系统中Warehouse表中Wnumber属性
 */
public class WnumberCount {
	HashMap<String,String> wnumber = new HashMap<String,String>();
	public WnumberCount() {
		String SQL1 = "SELECT Wno, COUNT(Gno) 仓库货物数量 FROM Operate GROUP BY Wno";
		//Wno, Wnumber都为int
		try {
			jdbcDrive.jdbcExecuteQuery(SQL1);
			while(jdbcDrive.resultset.next()) {
				wnumber.put(jdbcDrive.resultset.getString(1).trim(), jdbcDrive.resultset.getString(2).trim());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}
		try {
			for(String keys:wnumber.keySet()){
			jdbcDrive.jdbcExecuteUpdate("UPDATE Warehouse SET Wnumber = " + wnumber.get(keys) + "WHERE Wno = " + keys);
			System.out.println(keys);
			System.out.println(wnumber.get(keys));
			System.out.println("aaa");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}
		}
	
	public static void main(String[] args) {
		new WnumberCount();
	}
}
