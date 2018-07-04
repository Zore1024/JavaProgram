package com.nuc.wuliuinterface;
/**
 * 仓库管理员入库小界面
 * 增加对象只有Goods和Operate表
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
			//获得查询结果总行数
			jdbcDrive.jdbcExecuteQuery(SQL1);
			int rowCount = 0;
			while (jdbcDrive.resultset.next()){
				rowCount++;
			}
			jdbcDrive.jdbcConnectionClose();
			str = new String[rowCount + 1];
			str[0] = "选择存储仓库";
			int i = 1;

			jdbcDrive.jdbcExecuteQuery(SQL1);
			while (jdbcDrive.resultset.next()){
				//键值
				ware.put(jdbcDrive.resultset.getString(2).trim(), jdbcDrive.resultset.getString(1).trim());
				str[i] = jdbcDrive.resultset.getString(2);
				i++;
			}
		}catch(SQLException e) {
			System.out.println("仓库查询有问题");//控制台自己看的
		}finally{
			jdbcDrive.jdbcConnectionClose();
		}

		init();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })//eclipse加的
	public void init(){
		//创建组件
		jl1 = new JLabel("货物编号");
		jl2 = new JLabel("货物名");
		jl3 = new JLabel("货物要发往的下一站");
		jl4 = new JLabel("发件人姓名");
		jl5 = new JLabel("发件人的的联系电话");
		jl6 = new JLabel("收件人姓名");
		jl7 = new JLabel("收件人的的联系电话");
		jbGno = new JTextField();
		jbGname = new JTextField();
		jbGaddress = new JTextField();
		jbGsender = new JTextField();
		jbGstele = new JTextField();
		jbGreceiver = new JTextField();
		jbGrtele = new JTextField();
		jbadd = new JButton("入库");
		jbexit = new JButton("返回");
		jbclear = new JButton("清除");
		jcWno = new JComboBox(str);
		jlphoto = new JLabel(new ImageIcon("photo/xp3.png"));

		//设置组件一些参数
		jl1.setFont(new Font("楷体", Font.PLAIN, 20));
		jl1.setForeground(Color.BLACK);
		jl2.setFont(new Font("楷体", Font.PLAIN, 20));
		jl2.setForeground(Color.BLACK);
		jl3.setFont(new Font("楷体", Font.PLAIN, 20));
		jl3.setForeground(Color.BLACK);
		jl4.setFont(new Font("楷体", Font.PLAIN, 20));
		jl4.setForeground(Color.BLACK);
		jl5.setFont(new Font("楷体", Font.PLAIN, 20));
		jl5.setForeground(Color.BLACK);
		jl6.setFont(new Font("楷体", Font.PLAIN, 20));
		jl6.setForeground(Color.BLACK);
		jl7.setFont(new Font("楷体", Font.PLAIN, 20));
		jl7.setForeground(Color.BLACK);

		jbGno.setFont(new Font("楷体", Font.PLAIN, 20));
		jbGname.setFont(new Font("楷体", Font.PLAIN, 20));
		jbGaddress.setFont(new Font("楷体", Font.PLAIN, 20));
		jbGsender.setFont(new Font("楷体", Font.PLAIN, 20));
		jbGstele.setFont(new Font("楷体", Font.PLAIN, 20));
		jbGreceiver.setFont(new Font("楷体", Font.PLAIN, 20));
		jbGrtele.setFont(new Font("楷体", Font.PLAIN, 20));

		jbadd.setFont(new Font("楷体", Font.PLAIN, 20));
		jbadd.setForeground(Color.ORANGE);
		jbexit.setFont(new Font("楷体", Font.PLAIN, 20));
		jbexit.setForeground(Color.ORANGE);
		jbclear.setFont(new Font("楷体", Font.PLAIN, 20));
		jbclear.setForeground(Color.ORANGE);

		jcWno.setFont(new Font("楷体", Font.PLAIN, 16));
		jcWno.setForeground(Color.BLUE);

		//设置组件位置和大小
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
		//加组件
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

		//窗口设置
		this.setTitle("入库小窗口");
		setBounds(screenSize.width/2 - 300, screenSize.height/2 - 300, 600, 600);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		//按钮监听
		ButtonListener listener = new ButtonListener();
		jcWno.addItemListener(listener);
		jbadd.addActionListener(listener);
		jbexit.addActionListener(listener);
		jbclear.addActionListener(listener);
	}
	//监听类
	class ButtonListener implements ActionListener, ItemListener{
        String wno = null;
        String wname = null;
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getStateChange() == ItemEvent.SELECTED){
				@SuppressWarnings("rawtypes")//eclipse加的
				JComboBox cb = (JComboBox)arg0.getSource();
				wname = cb.getSelectedItem().toString();
			}

			for(HashMap.Entry<String, String> entry: ware.entrySet()){
				if (entry.getKey().equals(wname)){
					wno = entry.getValue();
					System.out.println(wno);
					wname = null; //清除上一次选择影响
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
			// TODO 自动生成的方法存根
			if (arg0.getSource() == jbadd){
				String SQL2 = "INSERT INTO Goods VALUES (" + gno + ", '" + gname + "', '" +
				gaddress + "', '" + gsender + "', '" + gstele + "', '" + greceiver + "', '" + grtele + "')";
				try{
					jdbcDrive.jdbcExecuteUpdate(SQL2);

					jdbcDrive.jdbcExecuteUpdate("UPDATE Warehouse SET Wnumber = Wnumber + 1 WHERE Wno = " + wno);
					WareAdminInterface.jtext.setText("编号为" + gno + "的货物成功入库！\n\n");

				}catch(SQLException e){
					JOptionPane.showMessageDialog(WAdminAdd.this, "入库失败!");
				}finally{
					WareAdminInterface.wdisplay();
					jdbcDrive.jdbcConnectionClose();
				}
				try {
					java.util.Date date = new java.util.Date();          // 获取一个Date对象
		            Timestamp timeStamp = new Timestamp(date.getTime()); //   讲日期时间转换为数据库中的timestamp类型
					SQL2 = "INSERT INTO Operate VALUES (" + gno + ", " + wno + ", '" + timeStamp + "', NULL, '已入库'"  + ")";
					jdbcDrive.jdbcExecuteUpdate(SQL2);
				}catch(SQLException e){
					System.out.println("插入Operate表失败");
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
