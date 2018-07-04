package com.nuc.wuliuinterface;
/*
 * 仓库管理员更新小界面
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.nuc.jdbc.jdbcDrive;

public class WAdminModify extends JFrame{
	private Dimension screenSize = null;
	private Toolkit toolKit = null;

	private JLabel jl1, jl2, jl3, jlphoto;
	private JTextField jtext1, jtext2, jtext3;
	private JButton jbmodify, jbexit, jbclear;
	private JTabbedPane jtp;
	private JPanel jp1, jp2;
	private JTextArea jtextarea;
	private JScrollPane jsp;

	public static void main(String[] args){
		new WAdminModify();
	}
	public WAdminModify(){
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		init();
	}
	public void init(){
		//创建组件
		jl1 = new JLabel("需更改的属性:");
		jl2 = new JLabel("更改值:");
		jl3 = new JLabel("货物编码");
		jtext1 = new JTextField();
		jtext2 = new JTextField();
		jtext3 = new JTextField();
		jtextarea = new JTextArea();
		jbmodify = new JButton("更新");
		jbexit = new JButton("返回");
		jbclear = new JButton("清除");
		jtp = new JTabbedPane();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jsp = new JScrollPane(jtextarea);
		jlphoto = new JLabel(new ImageIcon("photo/xp4.png"));

		//设置组件
		jl1.setFont(new Font("楷体", Font.PLAIN, 20));
		jl1.setForeground(Color.BLACK);
		jl2.setFont(new Font("楷体", Font.PLAIN, 20));
		jl2.setForeground(Color.BLACK);
		jl3.setFont(new Font("楷体", Font.PLAIN, 20));
		jl3.setForeground(Color.BLACK);
		jtext1.setFont(new Font("楷体", Font.PLAIN, 20));
		jtext1.setForeground(Color.BLUE);
		jtext2.setFont(new Font("楷体", Font.PLAIN, 20));
		jtext2.setForeground(Color.BLUE);
		jtext3.setFont(new Font("楷体", Font.PLAIN, 20));
		jtext3.setForeground(Color.BLUE);
		jbmodify.setFont(new Font("楷体", Font.PLAIN, 20));
		jbmodify.setForeground(Color.ORANGE);
		jbexit.setFont(new Font("楷体", Font.PLAIN, 20));
		jbexit.setForeground(Color.ORANGE);
		jbclear.setFont(new Font("楷体", Font.PLAIN, 20));
		jbclear.setForeground(Color.ORANGE);

		jtp.setFont(new Font("楷体", Font.PLAIN, 25));
		jtp.setForeground(Color.BLACK);
		jtextarea.setFont(new Font("楷体", Font.PLAIN, 28));
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		//设置组件位置和大小
		jtp.setBounds(0, 0, 500, 400);
		jl1.setBounds(70, 60, 150, 25);
		jtext1.setBounds(230, 60, 200, 25);
		jl2.setBounds(70, 120, 150, 25);
		jtext2.setBounds(230, 120, 200, 25);
		jl3.setBounds(70, 180, 150, 25);
		jtext3.setBounds(230, 180, 200, 25);
		jbmodify.setBounds(70, 230, 100, 30);
		jbclear.setBounds(200, 230, 100, 30);
		jbexit.setBounds(330, 230, 100, 30);
		jsp.setBounds(0, 0, 500-15, 400);
		jlphoto.setBounds(0, 0,  500, 400);

		//加入组件
		jp1.add(jl1);
		jp1.add(jtext1);
		jp1.add(jl2);
		jp1.add(jtext2);
		jp1.add(jbmodify);
		jp1.add(jbclear);
		jp1.add(jbexit);
		jp1.add(jl3);
		jp1.add(jtext3);
		jp1.add(jlphoto);
		jp2.add(jsp);
		jp1.setLayout(null);
		jp2.setLayout(null);
		this.setLayout(null);
		jtp.add("更新", jp1);
		jtp.add("帮助", jp2);
		this.add(jtp);
		//窗体设置
		this.setTitle("删除小界面");
		setBounds(screenSize.width/2 - 250, screenSize.height/2 - 150, 500, 400);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//窗体不可改变大小

		//建立监听
		ButtonListener listener = new ButtonListener();
		jbmodify.addActionListener(listener);
		jbclear.addActionListener(listener);
		jbexit.addActionListener(listener);

		//jtextarea设置文字
		jtextarea.setText("更新小贴士:\n");
		jtextarea.append("属性名和其对应的编码\n");
		jtextarea.append("货物名       \t01\n");
		jtextarea.append("转发的下一站 \t02\n");
		jtextarea.append("收件人的姓名\t03\n");
		jtextarea.append("收件人的联系电话\t04\n");
		jtextarea.append("发件人的姓名\t05\n");
		jtextarea.append("发件人的联系电话\t06\n");
		jtextarea.append("货物状态 \t07\n");
		jtextarea.append("注意:货物状态只有已入库和已出库\n\n");

		jtextarea.setEditable(false);//不可编辑
	}

	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getSource().equals(jbexit)) {
				WAdminModify.this.dispose();
			}
			else if (arg0.getSource().equals(jbclear)) {
				jtext1.setText("");
				jtext2.setText("");
			}
			else if (arg0.getSource().equals(jbmodify)) {
				String gno = jtext3.getText();
				String value = jtext2.getText();
				String temp = jtext1.getText();
				String SQL = null;
				String SQL1 = null;
				if (temp.equals("货物名")||temp.equals("01")) {
					SQL = "UPDATE Goods SET Gname = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("转发的下一站")||temp.equals("02")) {
					SQL = "UPDATE Goods SET Gaddress = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("收件人的姓名")||temp.equals("03")) {
					SQL = "UPDATE Goods SET Greceiver = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("收件人的联系电话")||temp.equals("04")) {
					SQL = "UPDATE Goods SET Grtele = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("发件人的姓名")||temp.equals("05")) {
					SQL = "UPDATE Goods SET Gsender = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("发件人的联系电话")||temp.equals("06")) {
					SQL = "UPDATE Goods SET Gstele = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("货物状态")||temp.equals("07")) {
					java.util.Date date = new java.util.Date();          // 获取一个Date对象
		            Timestamp timeStamp = new Timestamp(date.getTime()); //   讲日期时间转换为数据库中的timestamp类型

					SQL = "UPDATE Operate SET Condition = '" + value + "' WHERE Gno = " + gno;
					SQL1 = "UPDATE Operate SET Outputdate = " + timeStamp + "WHERE Gno = " + gno;
				}
				System.out.println(SQL);
				try{
					jdbcDrive.jdbcExecuteUpdate(SQL);
					jdbcDrive.jdbcExecuteUpdate(SQL1);

				}catch(SQLException e) {
						JOptionPane.showMessageDialog(WAdminModify.this, "入库失败! 货物编码不为空");
				}finally{
					WareAdminInterface.wdisplay();
					jdbcDrive.jdbcConnectionClose();
				}

			}
		}

	}
}
