package com.nuc.jdbc;
/*
 * ����Ա(�ֿ����Ա,ϵͳ����Ա)�ڵ������ʱ�����ݿ��������
 * ��IdΪtrueʱ��ϵͳ����Ա,Ϊfalseʱ�ǲֿ����Ա
 */

import java.sql.SQLException;
import java.util.HashMap;

public class jdbcAdminLogin {
	private static HashMap<String,String> admin = new HashMap<String,String>();
	/*
	 * ���������жϹ���Ա�������
	 * flag����0ʱ,��ʾ����ʧ��
	 * flag����1ʱ����ʾ��ϵͳ����Ա��ݵ���
	 * flag�Ż�-1ʱ,��ʾ�Բֿ����Ա��ݵ���
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

		//�ڶ��׶�
		admin.clear();//���admin�����õ�ӳ��
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
