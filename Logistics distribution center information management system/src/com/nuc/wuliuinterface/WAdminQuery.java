package com.nuc.wuliuinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

import com.nuc.jdbc.jdbcDrive;

/*
 * 仓库管理员界面的查询类
 * 仓库管理员查询操作小界面
 */
public class WAdminQuery extends JFrame {

	private static final long serialVersionUID = 1L;//eclipse加的
	private JButton jbquery, jbexit;
	private JRadioButton jrb1, jrb2, jrb3;
	private ButtonGroup group;
	private JPanel jpl;

	private String SQL = null;
	private Dimension screenSize = null;
	private Toolkit toolKit = null;
	public static void main(String[] args) {
		new WAdminQuery();
	}

	public WAdminQuery() {
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		init();
	}
	public void init() {
		//建立组件
		jrb1 = new JRadioButton("按仓库编号分组");
		jrb2 = new JRadioButton("按手机人电话分组");
		jrb3 = new JRadioButton("按转发下一站分组");
		group = new ButtonGroup();
		jbquery = new JButton("查询");
		jbexit = new JButton("返回");
		jpl = new JPanel();
		//组件设置
		jbquery.setFont(new Font("楷体", Font.PLAIN, 16));
		jbquery.setForeground(Color.BLUE);
		jbexit.setFont(new Font("楷体", Font.PLAIN, 16));
		jbexit.setForeground(Color.BLUE);

		//组件加入
		this.setLayout(new FlowLayout());
		group.add(jrb1);
		group.add(jrb2);
		group.add(jrb3);
		this.add(jrb1);
		this.add(jrb2);
		this.add(jrb3);
		jpl.add(jbquery);
		jpl.add(jbexit);
		this.add(jpl);

		this.setTitle("查询小界面");
		setBounds(screenSize.width/2 - 150, screenSize.height/2 - 150, 250, 200);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WareAdminInterface.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//窗体不可改变大小

		//事件监听
		QueryListener listener = new QueryListener();
		jrb1.addActionListener(listener);
		jrb2.addActionListener(listener);
		jrb3.addActionListener(listener);
		jbquery.addActionListener(listener);
		jbexit.addActionListener(listener);
	}
	//监听类
	class QueryListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getSource().equals(jrb1)) {
				SQL = "SELECT* FROM Wadmin_Operate ORDER BY Wno ASC";
			}
			else if(arg0.getSource().equals(jrb2)) {
				SQL = "SELECT* FROM Wadmin_Operate ORDER BY Grtele ASC";
			}
			else if(arg0.getSource().equals(jrb3)) {
				SQL = "SELECT* FROM Wadmin_Operate ORDER BY Gaddress ASC";
			}
			else if (arg0.getSource().equals(jbquery)) {
				WareAdminInterface.jtext.setText("查询后:\n\n");
				try {
					jdbcDrive.jdbcExecuteQuery(SQL);
					//设置jtext内容
					while (jdbcDrive.resultset.next()) {
						WareAdminInterface.jtext.append("货物编号为: " + jdbcDrive.resultset.getString(1));
						WareAdminInterface.jtext.append(" 仓库号: " + jdbcDrive.resultset.getString(2));
						WareAdminInterface.jtext.append(" 货物下一站地址: " + jdbcDrive.resultset.getString(3));
						WareAdminInterface.jtext.append(" 入库时间: " + jdbcDrive.resultset.getString(4));
						WareAdminInterface.jtext.append(" 出库时间: " + jdbcDrive.resultset.getString(5));
						WareAdminInterface.jtext.append(" 货物状态: " + jdbcDrive.resultset.getString(6) + "\n");
						WareAdminInterface.jtext.append("发件人电话: " + jdbcDrive.resultset.getString(7));
						WareAdminInterface.jtext.append(" 收件人电话: " + jdbcDrive.resultset.getString(8));
						WareAdminInterface.jtext.append(" 发件人姓名: " + jdbcDrive.resultset.getString(9));
						WareAdminInterface.jtext.append(" 收件人电话: " + jdbcDrive.resultset.getString(10));
						WareAdminInterface.jtext.append(" 货物名: " + jdbcDrive.resultset.getString(11) + "\n\n");
				    }
				}catch(SQLException e) {
					System.out.println("仓库管理员操作异常");
				}finally{
					WareAdminInterface.wdisplay();
					jdbcDrive.jdbcConnectionClose();
				}
				WAdminQuery.this.dispose();
			}
			else if (arg0.getSource().equals(jbexit)){
				WAdminQuery.this.dispose();
			}
		}

	}
}

