package com.nuc.wuliuinterface;
/*
 * 系统管理员操作界面
 * 操作对象表有employee(职工表),Admin(管理员表),Warehouse(仓库表)
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.nuc.jdbc.jdbcDrive;

public class SAdminInterface extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize = null;
	private Toolkit toolKit = null;

	private JButton jbdelete1, jbadd1, jbsave1, jbexit1; //Admin(管理员表)
	private JButton jbdelete2, jbadd2, jbsave2, jbexit2; //Warehouse(仓库表)
	private JButton jbdelete3, jbadd3, jbsave3, jbexit3; //Employee(职工表)
	private JTabbedPane jtp;
	private JPanel jp1, jp2, jp3;
	private DefaultTableModel tableModel, tableModel2, tableModel3;		// 默认显示的表格
	private JTable table1, table2, table3;
	private JScrollPane jsp1, jsp2, jsp3;
	private JLabel jlphoto1, jlphoto2, jlphoto3;

	public static void main(String[] args){
		new SAdminInterface();
	}
	public SAdminInterface(){
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		init();
	}
	public void init(){
		//建立组件
		jtp = new JTabbedPane();
		jbdelete1 = new JButton("删除");
		jbadd1 = new JButton("增加");
		jbsave1 = new JButton("保存");
		jbexit1 = new JButton("退出");
		
		jbdelete2 = new JButton("删除");
		jbadd2 = new JButton("增加");
		jbsave2 = new JButton("保存");
		jbexit2 = new JButton("退出");
		
		jbdelete3 = new JButton("删除");
		jbadd3 = new JButton("增加");
		jbsave3 = new JButton("保存");
		jbexit3 = new JButton("退出");
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jlphoto1 = new JLabel(new ImageIcon("photo/xp.png"));
		jlphoto2 = new JLabel(new ImageIcon("photo/xp.png"));
		jlphoto3 = new JLabel(new ImageIcon("photo/xp.png"));
		
		//设置组件
		jbdelete1.setFont(new Font("楷体", Font.PLAIN, 20));
		jbdelete1.setForeground(Color.BLUE);
		jbadd1.setFont(new Font("楷体", Font.PLAIN, 20));
		jbadd1.setForeground(Color.BLUE);
		jbsave1.setFont(new Font("楷体", Font.PLAIN, 20));
		jbsave1.setForeground(Color.BLUE);
		jbexit1.setFont(new Font("楷体", Font.PLAIN, 20));
		jbexit1.setForeground(Color.BLUE);
		
		jbdelete2.setFont(new Font("楷体", Font.PLAIN, 20));
		jbdelete2.setForeground(Color.BLUE);
		jbadd2.setFont(new Font("楷体", Font.PLAIN, 20));
		jbadd2.setForeground(Color.BLUE);
		jbsave2.setFont(new Font("楷体", Font.PLAIN, 20));
		jbsave2.setForeground(Color.BLUE);
		jbexit2.setFont(new Font("楷体", Font.PLAIN, 20));
		jbexit2.setForeground(Color.BLUE);
		
		jbdelete3.setFont(new Font("楷体", Font.PLAIN, 20));
		jbdelete3.setForeground(Color.BLUE);
		jbadd3.setFont(new Font("楷体", Font.PLAIN, 20));
		jbadd3.setForeground(Color.BLUE);
		jbsave3.setFont(new Font("楷体", Font.PLAIN, 20));
		jbsave3.setForeground(Color.BLUE);
		jbexit3.setFont(new Font("楷体", Font.PLAIN, 20));
		jbexit3.setForeground(Color.BLUE);
		
		jlphoto1.setBounds(0, 0, 900, 560);
		jlphoto2.setBounds(0, 0, 900, 560);
		jlphoto3.setBounds(0, 0, 900, 560);
		
		jtp.setFont(new Font("楷体", Font.PLAIN, 20));
		jtp.setForeground(Color.BLACK);
		//设置组件大小和位置
		jbadd1.setBounds(50, 5, 100, 30);
		jbdelete1.setBounds(250, 5, 100, 30);
		jbsave1.setBounds(550, 5, 100, 30);
		jbexit1.setBounds(750, 5, 100, 30);
		
		jbadd2.setBounds(50, 5, 100, 30);
		jbdelete2.setBounds(250, 5, 100, 30);
		jbsave2.setBounds(550, 5, 100, 30);
		jbexit2.setBounds(750, 5, 100, 30);
		
		jbadd3.setBounds(50, 5, 100, 30);
		jbdelete3.setBounds(250, 5, 100, 30);
		jbsave3.setBounds(550, 5, 100, 30);
		jbexit3.setBounds(750, 5, 100, 30);
		
		//取得数据库的表的各行数据
		Vector rowData = SAdminTable.getRows();
		//取得数据库的表的表头数据
		Vector columnNames = SAdminTable.getHead();
		//新建表格
		tableModel = new DefaultTableModel(rowData, columnNames);	
		table1 = new JTable(tableModel);
		table1.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 20));
		table1.getTableHeader().setForeground(Color.RED);
		table1.setFont(new Font("楷体", Font.PLAIN, 30));
		table1.setForeground(Color.BLACK);
		table1.setRowHeight(50);
		jsp1 = new JScrollPane(table1);
		jsp1.setBounds(0, 40, 900, 560);
		//透明设置
		jsp1.getViewport().setOpaque(false);//将JScrollPane设置为透明
		jsp1.setOpaque(false);
		table1.setOpaque(false);//将JTable设置为透明
		jsp1.setViewportView(table1);
		jsp1.setColumnHeaderView(table1.getTableHeader());//设置头部（HeaderView部分）
		jsp1.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
		//渲染器设置透明
		DefaultTableCellRenderer render1 = new DefaultTableCellRenderer(); 
		render1.setOpaque(false);
		table1.setDefaultRenderer(Object.class, render1);
		
		SAdminTable.setStr("Warehouse");//转换到仓库表
		rowData = SAdminTable.getRows();
		columnNames = SAdminTable.getHead();
		tableModel2 = new DefaultTableModel(rowData, columnNames);	
		table2 = new JTable(tableModel2);
		table2.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 20));
		table2.getTableHeader().setForeground(Color.RED);
		table2.setFont(new Font("楷体", Font.PLAIN, 30));
		table2.setForeground(Color.BLACK);
		table2.setRowHeight(50);
		jsp2 = new JScrollPane(table2);
		jsp2.setBounds(0, 40, 900, 560);
		//透明设置
		jsp2.getViewport().setOpaque(false);//将JScrollPane设置为透明
		jsp2.setOpaque(false);
		table2.setOpaque(false);//将JTable设置为透明
		jsp2.setViewportView(table2);
		jsp2.setColumnHeaderView(table2.getTableHeader());//设置头部（HeaderView部分）
		jsp2.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
		//渲染器设置透明
		DefaultTableCellRenderer render2 = new DefaultTableCellRenderer(); 
		render2.setOpaque(false);
		table2.setDefaultRenderer(Object.class, render2);
		
		SAdminTable.setStr("Employee");//转换到仓库表
		rowData = SAdminTable.getRows();
		columnNames = SAdminTable.getHead();
		tableModel3 = new DefaultTableModel(rowData, columnNames);	
		table3 = new JTable(tableModel3);
		table3.getTableHeader().setFont(new Font("楷体", Font.BOLD, 20));
		table3.getTableHeader().setForeground(Color.RED);
		table3.setFont(new Font("楷体", Font.BOLD, 20));
		table3.setForeground(Color.BLACK);
		table3.setRowHeight(40);
		jsp3 = new JScrollPane(table3);
		jsp3.setBounds(0, 40, 900, 560);//不能使用jsp.add()进行添加
		//透明设置
		jsp3.getViewport().setOpaque(false);//将JScrollPane设置为透明
		jsp3.setOpaque(false);
		table3.setOpaque(false);//将JTable设置为透明
		jsp3.setViewportView(table3);
		jsp3.setColumnHeaderView(table3.getTableHeader());//设置头部（HeaderView部分）
		jsp3.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
		//渲染器设置透明
		DefaultTableCellRenderer render3 = new DefaultTableCellRenderer(); 
		render3.setOpaque(false);
		table3.setDefaultRenderer(Object.class, render3);
		//加入组件
		jp1.add(jsp1);
		jp1.add(jbadd1);
		jp1.add(jbdelete1);
		jp1.add(jbsave1);
		jp1.add(jbexit1);
		jp1.add(jlphoto1);
		
		jp2.add(jsp2);
		jp2.add(jbadd2);
		jp2.add(jbdelete2);
		jp2.add(jbsave2);
		jp2.add(jbexit2);
		jp2.add(jlphoto2);
		
		jp3.add(jsp3);
		jp3.add(jbadd3);
		jp3.add(jbdelete3);
		jp3.add(jbsave3);
		jp3.add(jbexit3);
		jp3.add(jlphoto3);
		
		jtp.add(jp1, "管理员表");
		jtp.add(jp2, "仓库表");
		jtp.add(jp3, "职工表");
		jp1.setLayout(null);
		jp2.setLayout(null);
		jp3.setLayout(null);
		this.add(jtp);
		//窗体设置
		this.setTitle("系统管理员操作界面");
		setBounds(screenSize.width/2 - 350, screenSize.height/2 - 250, 900, 600);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//窗体不可改变大小
		
		//建立监听
		SAdminActionListener listener1 = new SAdminActionListener();
		jbadd1.addActionListener(listener1);
		jbdelete1.addActionListener(listener1);
		jbsave1.addActionListener(listener1);
		jbexit1.addActionListener(listener1);	
		
		SWarehouseActionListener listener2 = new SWarehouseActionListener();
		jbadd2.addActionListener(listener2);
		jbdelete2.addActionListener(listener2);
		jbsave2.addActionListener(listener2);
		jbexit2.addActionListener(listener2);
		
		SEmployeehouseActionListener listener3 = new SEmployeehouseActionListener();
		jbadd3.addActionListener(listener3);
		jbdelete3.addActionListener(listener3);
		jbsave3.addActionListener(listener3);
		jbexit3.addActionListener(listener3);
	}
	//Admin表监听类
	class SAdminActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getSource().equals(jbadd1)) {
				// 增加一行空白区域
				tableModel.addRow(new Vector());
			}
			else if (arg0.getSource().equals(jbdelete1)) {
				// 删除指定行
				int rowcount = table1.getSelectedRow();
				if(rowcount >= 0){
					tableModel.removeRow(rowcount);
				}
			}
			else if (arg0.getSource().equals(jbsave1)) {
				JOptionPane.showMessageDialog(SAdminInterface.this, "确认保存信息");
				
				int column = table1.getColumnCount();		//获得表格列数
				int row = table1.getRowCount();		//获得表格行数
				
				// value数组存放表格中的所有数据
				String[][] value = new String[row][column];
				
				for(int i = 0; i < row; i++){
					for(int j = 0; j < column; j++){
						value[i][j] = (String) table1.getValueAt(i, j);
						//table.getValueAt(i, j).toString()会报nullpointerexception
					}
				}
				
				try {
					
					jdbcDrive.jdbcExecuteUpdate("DELETE FROM Admin");
					for(int i = 0; i < row; i++){
						jdbcDrive.jdbcExecuteUpdate("INSERT INTO Admin VALUES(" + value[i][0] + ",'" + value[i][1] 
							+ "', " + value[i][2] + ")");
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(SAdminInterface.this, "Admin表保存成功！");
				
			}
			else if (arg0.getSource().equals(jbexit1)) {
				SAdminInterface.this.dispose();
				new Login();
			}
		}
		
	}
	//Warehouse表监听类
	class SWarehouseActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getSource().equals(jbadd2)) {
				// 增加一行空白区域
				tableModel2.addRow(new Vector());
			}
			else if (arg0.getSource().equals(jbdelete2)) {
				// 删除指定行
				int rowcount = table2.getSelectedRow();
				if(rowcount >= 0){
					tableModel2.removeRow(rowcount);
				}
			}
			else if (arg0.getSource().equals(jbsave2)) {
				JOptionPane.showMessageDialog(SAdminInterface.this, "确认保存信息");
				
				int column = table2.getColumnCount();		//获得表格列数
				int row = table2.getRowCount();		//获得表格行数
				
				// value数组存放表格中的所有数据
				String[][] value = new String[row][column];
				
				for(int i = 0; i < row; i++){
					for(int j = 0; j < column; j++){
						value[i][j] = (String) table2.getValueAt(i, j);
						//table.getValueAt(i, j).toString()会报nullpointerexception
					}
				}
				
				try {
					//关闭相关联的外键约束
					jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Operate NOCHECK CONSTRAINT ALL");
					jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Employee NOCHECK CONSTRAINT ALL");
					//删Warehouse表
					jdbcDrive.jdbcExecuteUpdate("DELETE FROM Warehouse");
					for(int i = 0; i < row; i++){
						jdbcDrive.jdbcExecuteUpdate("INSERT INTO Warehouse VALUES(" + value[i][0] + ", '" + value[i][1] 
							+ "', '" + value[i][2] + "', " + value[i][3] +  "')");
					}
					jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Operate CHECK CONSTRAINT ALL");
					jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Employee CHECK CONSTRAINT ALL");
				}catch(SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(SAdminInterface.this, "Warehouse表保存成功！");
			}
			else if (arg0.getSource().equals(jbexit2)) {
				SAdminInterface.this.dispose();
				new Login();
			}
		}
		WnumberCount c = new WnumberCount();//确保Warehouse表中Wnumber不被篡改
	}
	
	//Employeee表监听类
		class SEmployeehouseActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if (arg0.getSource().equals(jbadd3)) {
					// 增加一行空白区域
					tableModel3.addRow(new Vector());
				}
				else if (arg0.getSource().equals(jbdelete3)) {
					// 删除指定行
					int rowcount = table3.getSelectedRow();
					if(rowcount >= 0){
						tableModel3.removeRow(rowcount);
					}
				}
				else if (arg0.getSource().equals(jbsave3)) {
					JOptionPane.showMessageDialog(SAdminInterface.this, "确认保存信息");
					
					int column = table3.getColumnCount();		//获得表格列数
					int row = table3.getRowCount();		//获得表格行数
					
					// value数组存放表格中的所有数据
					String[][] value = new String[row][column];
					
					for(int i = 0; i < row; i++){
						for(int j = 0; j < column; j++){
							value[i][j] = (String) table3.getValueAt(i, j);
							//table.getValueAt(i, j).toString()会报nullpointerexception
						}
					}
					
					try {
						//关闭相关联的外键约束
						jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Warehouse NOCHECK CONSTRAINT ALL");
						jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Admin NOCHECK CONSTRAINT ALL");
						//删Warehouse表
						jdbcDrive.jdbcExecuteUpdate("DELETE FROM Employee");
						for(int i = 0; i < row; i++){
							jdbcDrive.jdbcExecuteUpdate("INSERT INTO Employee VALUES(" + value[i][0] + ", '" + value[i][1] 
								+ "', '" + value[i][2] + "', " + value[i][3] + ", '" + value[i][4] + "', " + value[i][5] + ", " 
									+ value[i][6]  + ")");
						}
						jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Warehouse CHECK CONSTRAINT ALL");
						jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Admin CHECK CONSTRAINT ALL");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(SAdminInterface.this, "Employee表保存成功！");
					
				}
				else if (arg0.getSource().equals(jbexit3)) {
					SAdminInterface.this.dispose();
					new Login();
				}
			}
			
		}
}
