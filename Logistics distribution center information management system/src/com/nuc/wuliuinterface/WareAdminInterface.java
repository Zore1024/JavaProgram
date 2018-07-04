package com.nuc.wuliuinterface;
/*
 * 仓库管理员的操作界面
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.nuc.jdbc.jdbcDrive;

public class WareAdminInterface extends JFrame {

	private static final long serialVersionUID = 1L;//eclipse加的

	private JLabel jlb;
	private JToolBar jtoolbar;
	private JButton jbquery, jbadd, jbdelete, jbmodify, jbexit;
	private JScrollPane jsp;
	public static JTextArea jtext;

	private Dimension screenSize = null;
	private Toolkit toolKit = null;
	private Font f;
	protected static ImageIcon icon = new ImageIcon("photo/zb4.png");
	//显示时间
	public static Date date = new Date();
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//public static String user;//employee中的Eno和admin中user对应

	public void init() {
		//创建组件
		jlb = new JLabel(new ImageIcon("photo/zb4.png"));
		jbquery = new JButton(new ImageIcon("photo/select.png"));
		jbadd = new JButton(new ImageIcon("photo/add.png"));
		jbdelete = new JButton(new ImageIcon("photo/delete.png"));
		jbmodify = new JButton(new ImageIcon("photo/modify.png"));
		jbexit = new JButton("退出");
		jtext = new JTextArea();
		jtoolbar = new JToolBar();
		jsp = new JScrollPane(jtext);

		//设置组件
		f = new Font("楷体", 0, 30);
		jtext.setFont(f);
		jbquery.setToolTipText("查询");
		jbadd.setToolTipText("增加");
		jbdelete.setToolTipText("删除");
		jbmodify.setToolTipText("修改");
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jbexit.setFont(new Font("楷体", Font.PLAIN, 50));

		//加组件
		jtoolbar.add(jbquery);
		jtoolbar.add(jbadd);
		jtoolbar.add(jbdelete);
		jtoolbar.add(jbmodify);
		jtoolbar.add(jbexit);
		this.setLayout(null);

		jlb.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		this.add(jlb);

		jtoolbar.setBounds(0, icon.getIconHeight(), icon.getIconWidth(), 72);
		this.add(jtoolbar, BorderLayout.NORTH);

		jsp.setBounds(0, icon.getIconHeight()+72, icon.getIconWidth()-15, 490);
		this.add(jsp);

		//设置窗体属性
		this.setTitle("中北物流集散中心仓库管理员操作系统");
		setBounds(screenSize.width/2-icon.getIconWidth()/2
				, screenSize.height/2-icon.getIconHeight()-200
				, icon.getIconWidth(), icon.getIconHeight()+600
				);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WareAdminInterface.EXIT_ON_CLOSE);
		this.setVisible(true);
		//this.setResizable(false);//窗体不可改变大小

		//事件监听
		WAdminLinstener listener = new WAdminLinstener();
		jbquery.addActionListener(listener);
		jbadd.addActionListener(listener);
		jbdelete.addActionListener(listener);
		jbmodify.addActionListener(listener);
		jbexit.addActionListener(listener);
	}

	/*
	 * 显示每个仓库里的数据
	 */
	public static void wdisplay() {
		String SQL = "SELECT Wno, Wname, Wnumber FROM Warehouse";
		date = new Date();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			jdbcDrive.jdbcExecuteQuery(SQL);
			jtext.append(sdf.format(date) + "\n");
			jtext.append("货物信息显示台:\n");
			while (jdbcDrive.resultset.next()) {
				jtext.append("仓库编号: " + jdbcDrive.resultset.getString(1));
				jtext.append("\n仓库名: " + jdbcDrive.resultset.getString(2));
				jtext.append("\n货物数量: " + jdbcDrive.resultset.getString(3));
				jtext.append("\n\n");
			}
		}catch (SQLException e) {

		}finally{
			jdbcDrive.jdbcConnectionClose();
		}
	}


	public static void main(String[] args) {
		new WareAdminInterface();
	}
	public WareAdminInterface() {
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		init();
		wdisplay();
	}

	//事件监听类
	class WAdminLinstener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getSource().equals(jbquery)) {
				new WAdminQuery();
			}
			else if (arg0.getSource().equals(jbexit)) {
				WareAdminInterface.this.dispose();
				new Login();
			}
			else if (arg0.getSource().equals(jbdelete)) {
				new WAdminDelete();
			}
			else if (arg0.getSource().equals(jbadd)) {
				new WAdminAdd();
			}
			else if (arg0.getSource().equals(jbmodify)) {
				new WAdminModify();
			}
		}

	}

}
