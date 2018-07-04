package com.nuc.wuliuinterface;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

import com.nuc.jdbc.jdbcDrive;

public class SAdminTable {
	public static String str = "Admin";
	public static void setStr(String s) {
		str = s;
	}
	//�õ����ݿ������
	public static Vector getRows(){
		Vector rows = null;
		Vector columnHeads = null;
		String SQL = "SELECT* FROM " + str;
		try {
			jdbcDrive.jdbcExecuteQuery(SQL);
			if(jdbcDrive.resultset.wasNull()) {
				JOptionPane.showMessageDialog(null, str + "���������޼�¼");
			}
			ResultSetMetaData rsmd = jdbcDrive.resultset.getMetaData();
			rows = new Vector();

			while(jdbcDrive.resultset.next()){
				rows.addElement(getNextRow(jdbcDrive.resultset, rsmd));
			}


		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}
		return rows;
	}

	// �õ����ݿ��ͷ
		public static Vector getHead(){

			Vector columnHeads = null;
			String SQL = "SELECT* FROM " + str;

			try {
				jdbcDrive.jdbcExecuteQuery(SQL);

				//boolean moreRecords = jdbcDrive.resultset.next();
				if(!jdbcDrive.resultset.next())
					JOptionPane.showMessageDialog(null, str + "���������޼�¼");

				columnHeads = new Vector();
				ResultSetMetaData rsmd = jdbcDrive.resultset.getMetaData();
				for(int i = 1; i <= rsmd.getColumnCount(); i++)
					columnHeads.addElement(rsmd.getColumnName(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return columnHeads;
		}

	// �õ����ݿ�����һ������
	private static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException{
		Vector currentRow = new Vector();
		for(int i = 1; i <= rsmd.getColumnCount(); i++){
			currentRow.addElement(rs.getString(i));
			}
		return currentRow;
		}
}
