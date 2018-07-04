package com.nuc.wuliuinterface;
/*
 * 仓库管理员的删除小界面
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.nuc.jdbc.jdbcDrive;

public class WAdminDelete extends JFrame{

	private static final long serialVersionUID = 1L;//eclipse加的
	private JLabel jlb, jlbphoto;
	private JTextField jtext;
	private JButton jbselect, jbexit;

	private Dimension screenSize = null;
	private Toolkit toolKit = null;
	public static void main(String[] args) {
		new WAdminDelete();
	}
	public WAdminDelete() {
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		init();
	}
	public void init() {
		//创建组件
		jlb = new JLabel("删除的货物编号", JLabel.CENTER);
		jtext = new JTextField();
		jbselect = new JButton("确认");
		jbexit = new JButton("返回");
		jlbphoto = new JLabel(new ImageIcon("photo/kejilou.png"));
		//设置字体样式颜色
		jlb.setFont(new Font("楷体", Font.PLAIN, 20));
		jlb.setForeground(Color.BLACK);
		jtext.setFont(new Font("楷体", Font.PLAIN, 20));
		jtext.setForeground(Color.BLACK);
		jbselect.setForeground(Color.BLUE);
		jbselect.setFont(new Font("楷体", Font.PLAIN, 20));
		jbexit.setForeground(Color.BLUE);
		jbexit.setFont(new Font("楷体", Font.PLAIN, 20));
		//设置组件位置
		jlb.setBounds(70, 30, 150, 30);
		jtext.setBounds(240, 30, 200, 28);
		jbselect.setBounds(70, 85, 150, 30);
		jbexit.setBounds(290, 85, 150, 30);
		jlbphoto.setBounds(0, 0, 500, 160);
		//加组件
		this.setLayout(null);
		this.add(jlb);
		this.add(jtext);
		this.add(jbselect);
		this.add(jbexit);
		this.add(jlbphoto);
		//窗体设置
		this.setTitle("删除小界面");
		setBounds(screenSize.width/2 - 250, screenSize.height/2 - 150, 500, 160);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//窗体不可改变大小

		//建立监听
		DeleteListener listener = new DeleteListener();
		jbselect.addActionListener(listener);
		jbexit.addActionListener(listener);
	}
	class DeleteListener implements ActionListener{
		String wno = null;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getSource().equals(jbselect)) {
				String Gno = jtext.getText();
				String SQL1 = "DELETE FROM Operate WHERE Gno = " + Gno ; //先删除关联的Operate中的Gno
				String SQL2 = "DELETE FROM Goods WHERE Gno = " + Gno;
				String SQL = "SELECT Gno FROM Goods Where Gno = " + Gno;
				try {
					jdbcDrive.jdbcExecuteQuery(SQL);
					if (jdbcDrive.resultset.next()) {//判断数据库中是不是有要删除的数据

						jdbcDrive.jdbcExecuteQuery("SELECT Wno FROM Operate WHERE Gno = " + Gno);
						if (jdbcDrive.resultset.next()) {
							wno = jdbcDrive.resultset.getString(1);
						}
						System.out.println(wno);

						jdbcDrive.jdbcExecuteUpdate(SQL1);
						jdbcDrive.jdbcExecuteUpdate(SQL2);
						WareAdminInterface.jtext.setText("成功删除编码Gno为" + Gno + "的货物\n\n");
						jdbcDrive.jdbcExecuteUpdate("UPDATE Warehouse SET Wnumber = Wnumber - 1 WHERE Wno = " + wno);
						WareAdminInterface.wdisplay();
					}else {
						WareAdminInterface.jtext.append("删除失败，数据库没有对应的货物编码\n");
						JOptionPane.showMessageDialog(WAdminDelete.this, "删除失败，数据库没有对应的货物编码");
					}

				}catch(SQLException e) {
					JOptionPane.showMessageDialog(WAdminDelete.this, "货物邮编不为空");
				}finally{
					jdbcDrive.jdbcConnectionClose();
				}
				WAdminDelete.this.dispose();
			}else if(arg0.getSource().equals(jbexit)) {
				WAdminDelete.this.dispose();
			}
		}

	}
}
