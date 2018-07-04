package com.nuc.wuliuinterface;
/**
 * �ֿ����Ա���С����
 * ���Ӷ���ֻ��Goods��Operate��
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import com.nuc.jdbc.jdbcDrive;

public class WAdminAdd extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
	private JTextField jbGno, jbGname, jbGaddress, jbGsender, jbGstele, jbGreceiver, jbGrtele;
	private JLabel jlphoto;
	private JButton jbadd, jbexit, jbclear;
	private JComboBox jcWno;
	public static String[] str = null;
	private static HashMap<String,String> ware = new HashMap<String,String>();

	private Dimension screenSize = null;
	private Toolkit toolKit = null;

	public static void main(String[] args){
		new WAdminAdd();
	}
	public WAdminAdd(){
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		String SQL1 = "SELECT Wno, Wname FROM Warehouse";
		try{
			//��ò�ѯ���������
			jdbcDrive.jdbcExecuteQuery(SQL1);
			int rowCount = 0;
			while (jdbcDrive.resultset.next()){
				rowCount++;
			}
			jdbcDrive.jdbcConnectionClose();
			str = new String[rowCount + 1];
			str[0] = "ѡ��洢�ֿ�";
			int i = 1;

			jdbcDrive.jdbcExecuteQuery(SQL1);
			while (jdbcDrive.resultset.next()){
				//��ֵ
				ware.put(jdbcDrive.resultset.getString(2).trim(), jdbcDrive.resultset.getString(1).trim());
				str[i] = jdbcDrive.resultset.getString(2);
				i++;
			}
		}catch(SQLException e) {
			System.out.println("�ֿ��ѯ������");//����̨�Լ�����
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}

		init();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })//eclipse�ӵ�
	public void init(){
		//�������
		jl1 = new JLabel("������");
		jl2 = new JLabel("������");
		jl3 = new JLabel("����Ҫ��������һվ");
		jl4 = new JLabel("����������");
		jl5 = new JLabel("�����˵ĵ���ϵ�绰");
		jl6 = new JLabel("�ռ�������");
		jl7 = new JLabel("�ռ��˵ĵ���ϵ�绰");
		jbGno = new JTextField();
		jbGname = new JTextField();
		jbGaddress = new JTextField();
		jbGsender = new JTextField();
		jbGstele = new JTextField();
		jbGreceiver = new JTextField();
		jbGrtele = new JTextField();
		jbadd = new JButton("���");
		jbexit = new JButton("����");
		jbclear = new JButton("���");
		jcWno = new JComboBox(str);
		jlphoto = new JLabel(new ImageIcon("photo/xp3.png"));

		//�������һЩ����
		jl1.setFont(new Font("����", Font.PLAIN, 20));
		jl1.setForeground(Color.BLACK);
		jl2.setFont(new Font("����", Font.PLAIN, 20));
		jl2.setForeground(Color.BLACK);
		jl3.setFont(new Font("����", Font.PLAIN, 20));
		jl3.setForeground(Color.BLACK);
		jl4.setFont(new Font("����", Font.PLAIN, 20));
		jl4.setForeground(Color.BLACK);
		jl5.setFont(new Font("����", Font.PLAIN, 20));
		jl5.setForeground(Color.BLACK);
		jl6.setFont(new Font("����", Font.PLAIN, 20));
		jl6.setForeground(Color.BLACK);
		jl7.setFont(new Font("����", Font.PLAIN, 20));
		jl7.setForeground(Color.BLACK);

		jbGno.setFont(new Font("����", Font.PLAIN, 20));
		jbGname.setFont(new Font("����", Font.PLAIN, 20));
		jbGaddress.setFont(new Font("����", Font.PLAIN, 20));
		jbGsender.setFont(new Font("����", Font.PLAIN, 20));
		jbGstele.setFont(new Font("����", Font.PLAIN, 20));
		jbGreceiver.setFont(new Font("����", Font.PLAIN, 20));
		jbGrtele.setFont(new Font("����", Font.PLAIN, 20));

		jbadd.setFont(new Font("����", Font.PLAIN, 20));
		jbadd.setForeground(Color.ORANGE);
		jbexit.setFont(new Font("����", Font.PLAIN, 20));
		jbexit.setForeground(Color.ORANGE);
		jbclear.setFont(new Font("����", Font.PLAIN, 20));
		jbclear.setForeground(Color.ORANGE);

		jcWno.setFont(new Font("����", Font.PLAIN, 16));
		jcWno.setForeground(Color.BLUE);

		//�������λ�úʹ�С
		jbadd.setBounds(70, 30, 100, 30);
		jbclear.setBounds(250, 30, 100, 30);
		jbexit.setBounds(430, 30, 100, 30);
		jl1.setBounds(70, 100, 180, 30);
		jbGno.setBounds(270, 100, 200, 30);
		jl2.setBounds(70, 150, 180, 30);
		jbGname.setBounds(270, 150, 200, 30);
		jl3.setBounds(70, 200, 180, 30);
		jbGaddress.setBounds(270, 200, 200, 30);
		jl4.setBounds(70, 250, 180, 30);
		jbGsender.setBounds(270, 250, 200, 30);
		jl5.setBounds(70, 300, 180, 30);
		jbGstele.setBounds(270, 300, 200, 30);
		jl6.setBounds(70, 350, 180, 30);
		jbGreceiver.setBounds(270, 350, 200, 30);
		jl7.setBounds(70, 400, 180, 30);
		jbGrtele.setBounds(270, 400, 200, 30);
		jcWno.setBounds(270, 450, 200, 30);
		jlphoto.setBounds(0, 0, 600, 600);
		//�����
		this.setLayout(null);
		this.add(jbadd);
		this.add(jbclear);
		this.add(jbexit);
		this.add(jl1);
		this.add(jbGno);
		this.add(jl2);
		this.add(jbGname);
		this.add(jl3);
		this.add(jbGaddress);
		this.add(jl4);
		this.add(jbGsender);
		this.add(jl5);
		this.add(jbGstele);
		this.add(jl6);
		this.add(jbGreceiver);
		this.add(jl7);
		this.add(jbGrtele);
		this.add(jcWno);
		this.add(jlphoto);

		//��������
		this.setTitle("���С����");
		setBounds(screenSize.width/2 - 300, screenSize.height/2 - 300, 600, 600);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		//��ť����
		ButtonListener listener = new ButtonListener();
		jcWno.addItemListener(listener);
		jbadd.addActionListener(listener);
		jbexit.addActionListener(listener);
		jbclear.addActionListener(listener);
	}
	//������
	class ButtonListener implements ActionListener, ItemListener{
        String wno = null;
        String wname = null;
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO �Զ����ɵķ������
			if (arg0.getStateChange() == ItemEvent.SELECTED){
				@SuppressWarnings("rawtypes")//eclipse�ӵ�
				JComboBox cb = (JComboBox)arg0.getSource();
				wname = cb.getSelectedItem().toString();
			}

			for(HashMap.Entry<String, String> entry: ware.entrySet()){
				if (entry.getKey().equals(wname)){
					wno = entry.getValue();
					System.out.println(wno);
					wname = null; //�����һ��ѡ��Ӱ��
				}
			}
			/*
			if (ware.containsKey(wname)){
				wno = ware.get(wname);
				System.out.println(wno);
				wname = null;
			}
			*/
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String gno = jbGno.getText();
			String gname = jbGname.getText();
			String gaddress = jbGaddress.getText();
			String gsender = jbGsender.getText();
			String gstele = jbGstele.getText();
			String greceiver = jbGreceiver.getText();
			String grtele = jbGrtele.getText();
			// TODO �Զ����ɵķ������
			if (arg0.getSource() == jbadd){
				String SQL2 = "INSERT INTO Goods VALUES (" + gno + ", '" + gname + "', '" +
				gaddress + "', '" + gsender + "', '" + gstele + "', '" + greceiver + "', '" + grtele + "')";
				try{
					jdbcDrive.jdbcExecuteUpdate(SQL2);

					jdbcDrive.jdbcExecuteUpdate("UPDATE Warehouse SET Wnumber = Wnumber + 1 WHERE Wno = " + wno);
					WareAdminInterface.jtext.setText("���Ϊ" + gno + "�Ļ���ɹ���⣡\n\n");

				}catch(SQLException e){
					JOptionPane.showMessageDialog(WAdminAdd.this, "���ʧ��!");
				}finally{
					WareAdminInterface.wdisplay();
					jdbcDrive.jdbcConnectionClose();
				}
				try {
					java.util.Date date = new java.util.Date();          // ��ȡһ��Date����
		            Timestamp timeStamp = new Timestamp(date.getTime()); //   ������ʱ��ת��Ϊ���ݿ��е�timestamp����
					SQL2 = "INSERT INTO Operate VALUES (" + gno + ", " + wno + ", '" + timeStamp + "', NULL, '�����'"  + ")";
					jdbcDrive.jdbcExecuteUpdate(SQL2);
				}catch(SQLException e){
					System.out.println("����Operate��ʧ��");
					e.printStackTrace();
				}finally{
					jdbcDrive.jdbcConnectionClose();
				}
			}
			else if (arg0.getSource() == jbexit){
				WAdminAdd.this.dispose();
			}
			else if (arg0.getSource() == jbclear) {
				jbGno.setText("");
				jbGname.setText("");
				jbGaddress.setText("");
				jbGsender.setText("");
				jbGstele.setText("");
				jbGreceiver.setText("");
				jbGrtele.setText("");
			}

		}

	}

}
