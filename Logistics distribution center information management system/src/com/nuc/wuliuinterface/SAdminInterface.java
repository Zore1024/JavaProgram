package com.nuc.wuliuinterface;
/*
 * ϵͳ����Ա��������
 * �����������employee(ְ����),Admin(����Ա��),Warehouse(�ֿ��)
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

	private JButton jbdelete1, jbadd1, jbsave1, jbexit1; //Admin(����Ա��)
	private JButton jbdelete2, jbadd2, jbsave2, jbexit2; //Warehouse(�ֿ��)
	private JButton jbdelete3, jbadd3, jbsave3, jbexit3; //Employee(ְ����)
	private JTabbedPane jtp;
	private JPanel jp1, jp2, jp3;
	private DefaultTableModel tableModel, tableModel2, tableModel3;		// Ĭ����ʾ�ı��
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
		//�������
		jtp = new JTabbedPane();
		jbdelete1 = new JButton("ɾ��");
		jbadd1 = new JButton("����");
		jbsave1 = new JButton("����");
		jbexit1 = new JButton("�˳�");
		
		jbdelete2 = new JButton("ɾ��");
		jbadd2 = new JButton("����");
		jbsave2 = new JButton("����");
		jbexit2 = new JButton("�˳�");
		
		jbdelete3 = new JButton("ɾ��");
		jbadd3 = new JButton("����");
		jbsave3 = new JButton("����");
		jbexit3 = new JButton("�˳�");
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jlphoto1 = new JLabel(new ImageIcon("photo/xp.png"));
		jlphoto2 = new JLabel(new ImageIcon("photo/xp.png"));
		jlphoto3 = new JLabel(new ImageIcon("photo/xp.png"));
		
		//�������
		jbdelete1.setFont(new Font("����", Font.PLAIN, 20));
		jbdelete1.setForeground(Color.BLUE);
		jbadd1.setFont(new Font("����", Font.PLAIN, 20));
		jbadd1.setForeground(Color.BLUE);
		jbsave1.setFont(new Font("����", Font.PLAIN, 20));
		jbsave1.setForeground(Color.BLUE);
		jbexit1.setFont(new Font("����", Font.PLAIN, 20));
		jbexit1.setForeground(Color.BLUE);
		
		jbdelete2.setFont(new Font("����", Font.PLAIN, 20));
		jbdelete2.setForeground(Color.BLUE);
		jbadd2.setFont(new Font("����", Font.PLAIN, 20));
		jbadd2.setForeground(Color.BLUE);
		jbsave2.setFont(new Font("����", Font.PLAIN, 20));
		jbsave2.setForeground(Color.BLUE);
		jbexit2.setFont(new Font("����", Font.PLAIN, 20));
		jbexit2.setForeground(Color.BLUE);
		
		jbdelete3.setFont(new Font("����", Font.PLAIN, 20));
		jbdelete3.setForeground(Color.BLUE);
		jbadd3.setFont(new Font("����", Font.PLAIN, 20));
		jbadd3.setForeground(Color.BLUE);
		jbsave3.setFont(new Font("����", Font.PLAIN, 20));
		jbsave3.setForeground(Color.BLUE);
		jbexit3.setFont(new Font("����", Font.PLAIN, 20));
		jbexit3.setForeground(Color.BLUE);
		
		jlphoto1.setBounds(0, 0, 900, 560);
		jlphoto2.setBounds(0, 0, 900, 560);
		jlphoto3.setBounds(0, 0, 900, 560);
		
		jtp.setFont(new Font("����", Font.PLAIN, 20));
		jtp.setForeground(Color.BLACK);
		//���������С��λ��
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
		
		//ȡ�����ݿ�ı�ĸ�������
		Vector rowData = SAdminTable.getRows();
		//ȡ�����ݿ�ı�ı�ͷ����
		Vector columnNames = SAdminTable.getHead();
		//�½����
		tableModel = new DefaultTableModel(rowData, columnNames);	
		table1 = new JTable(tableModel);
		table1.getTableHeader().setFont(new Font("����", Font.PLAIN, 20));
		table1.getTableHeader().setForeground(Color.RED);
		table1.setFont(new Font("����", Font.PLAIN, 30));
		table1.setForeground(Color.BLACK);
		table1.setRowHeight(50);
		jsp1 = new JScrollPane(table1);
		jsp1.setBounds(0, 40, 900, 560);
		//͸������
		jsp1.getViewport().setOpaque(false);//��JScrollPane����Ϊ͸��
		jsp1.setOpaque(false);
		table1.setOpaque(false);//��JTable����Ϊ͸��
		jsp1.setViewportView(table1);
		jsp1.setColumnHeaderView(table1.getTableHeader());//����ͷ����HeaderView���֣�
		jsp1.getColumnHeader().setOpaque(false);//��ȡ��ͷ����������Ϊ͸��
		//��Ⱦ������͸��
		DefaultTableCellRenderer render1 = new DefaultTableCellRenderer(); 
		render1.setOpaque(false);
		table1.setDefaultRenderer(Object.class, render1);
		
		SAdminTable.setStr("Warehouse");//ת�����ֿ��
		rowData = SAdminTable.getRows();
		columnNames = SAdminTable.getHead();
		tableModel2 = new DefaultTableModel(rowData, columnNames);	
		table2 = new JTable(tableModel2);
		table2.getTableHeader().setFont(new Font("����", Font.PLAIN, 20));
		table2.getTableHeader().setForeground(Color.RED);
		table2.setFont(new Font("����", Font.PLAIN, 30));
		table2.setForeground(Color.BLACK);
		table2.setRowHeight(50);
		jsp2 = new JScrollPane(table2);
		jsp2.setBounds(0, 40, 900, 560);
		//͸������
		jsp2.getViewport().setOpaque(false);//��JScrollPane����Ϊ͸��
		jsp2.setOpaque(false);
		table2.setOpaque(false);//��JTable����Ϊ͸��
		jsp2.setViewportView(table2);
		jsp2.setColumnHeaderView(table2.getTableHeader());//����ͷ����HeaderView���֣�
		jsp2.getColumnHeader().setOpaque(false);//��ȡ��ͷ����������Ϊ͸��
		//��Ⱦ������͸��
		DefaultTableCellRenderer render2 = new DefaultTableCellRenderer(); 
		render2.setOpaque(false);
		table2.setDefaultRenderer(Object.class, render2);
		
		SAdminTable.setStr("Employee");//ת�����ֿ��
		rowData = SAdminTable.getRows();
		columnNames = SAdminTable.getHead();
		tableModel3 = new DefaultTableModel(rowData, columnNames);	
		table3 = new JTable(tableModel3);
		table3.getTableHeader().setFont(new Font("����", Font.BOLD, 20));
		table3.getTableHeader().setForeground(Color.RED);
		table3.setFont(new Font("����", Font.BOLD, 20));
		table3.setForeground(Color.BLACK);
		table3.setRowHeight(40);
		jsp3 = new JScrollPane(table3);
		jsp3.setBounds(0, 40, 900, 560);//����ʹ��jsp.add()�������
		//͸������
		jsp3.getViewport().setOpaque(false);//��JScrollPane����Ϊ͸��
		jsp3.setOpaque(false);
		table3.setOpaque(false);//��JTable����Ϊ͸��
		jsp3.setViewportView(table3);
		jsp3.setColumnHeaderView(table3.getTableHeader());//����ͷ����HeaderView���֣�
		jsp3.getColumnHeader().setOpaque(false);//��ȡ��ͷ����������Ϊ͸��
		//��Ⱦ������͸��
		DefaultTableCellRenderer render3 = new DefaultTableCellRenderer(); 
		render3.setOpaque(false);
		table3.setDefaultRenderer(Object.class, render3);
		//�������
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
		
		jtp.add(jp1, "����Ա��");
		jtp.add(jp2, "�ֿ��");
		jtp.add(jp3, "ְ����");
		jp1.setLayout(null);
		jp2.setLayout(null);
		jp3.setLayout(null);
		this.add(jtp);
		//��������
		this.setTitle("ϵͳ����Ա��������");
		setBounds(screenSize.width/2 - 350, screenSize.height/2 - 250, 900, 600);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//���岻�ɸı��С
		
		//��������
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
	//Admin�������
	class SAdminActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO �Զ����ɵķ������
			if (arg0.getSource().equals(jbadd1)) {
				// ����һ�пհ�����
				tableModel.addRow(new Vector());
			}
			else if (arg0.getSource().equals(jbdelete1)) {
				// ɾ��ָ����
				int rowcount = table1.getSelectedRow();
				if(rowcount >= 0){
					tableModel.removeRow(rowcount);
				}
			}
			else if (arg0.getSource().equals(jbsave1)) {
				JOptionPane.showMessageDialog(SAdminInterface.this, "ȷ�ϱ�����Ϣ");
				
				int column = table1.getColumnCount();		//��ñ������
				int row = table1.getRowCount();		//��ñ������
				
				// value�����ű���е���������
				String[][] value = new String[row][column];
				
				for(int i = 0; i < row; i++){
					for(int j = 0; j < column; j++){
						value[i][j] = (String) table1.getValueAt(i, j);
						//table.getValueAt(i, j).toString()�ᱨnullpointerexception
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
				JOptionPane.showMessageDialog(SAdminInterface.this, "Admin����ɹ���");
				
			}
			else if (arg0.getSource().equals(jbexit1)) {
				SAdminInterface.this.dispose();
				new Login();
			}
		}
		
	}
	//Warehouse�������
	class SWarehouseActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO �Զ����ɵķ������
			if (arg0.getSource().equals(jbadd2)) {
				// ����һ�пհ�����
				tableModel2.addRow(new Vector());
			}
			else if (arg0.getSource().equals(jbdelete2)) {
				// ɾ��ָ����
				int rowcount = table2.getSelectedRow();
				if(rowcount >= 0){
					tableModel2.removeRow(rowcount);
				}
			}
			else if (arg0.getSource().equals(jbsave2)) {
				JOptionPane.showMessageDialog(SAdminInterface.this, "ȷ�ϱ�����Ϣ");
				
				int column = table2.getColumnCount();		//��ñ������
				int row = table2.getRowCount();		//��ñ������
				
				// value�����ű���е���������
				String[][] value = new String[row][column];
				
				for(int i = 0; i < row; i++){
					for(int j = 0; j < column; j++){
						value[i][j] = (String) table2.getValueAt(i, j);
						//table.getValueAt(i, j).toString()�ᱨnullpointerexception
					}
				}
				
				try {
					//�ر�����������Լ��
					jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Operate NOCHECK CONSTRAINT ALL");
					jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Employee NOCHECK CONSTRAINT ALL");
					//ɾWarehouse��
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
				JOptionPane.showMessageDialog(SAdminInterface.this, "Warehouse����ɹ���");
			}
			else if (arg0.getSource().equals(jbexit2)) {
				SAdminInterface.this.dispose();
				new Login();
			}
		}
		WnumberCount c = new WnumberCount();//ȷ��Warehouse����Wnumber�����۸�
	}
	
	//Employeee�������
		class SEmployeehouseActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				if (arg0.getSource().equals(jbadd3)) {
					// ����һ�пհ�����
					tableModel3.addRow(new Vector());
				}
				else if (arg0.getSource().equals(jbdelete3)) {
					// ɾ��ָ����
					int rowcount = table3.getSelectedRow();
					if(rowcount >= 0){
						tableModel3.removeRow(rowcount);
					}
				}
				else if (arg0.getSource().equals(jbsave3)) {
					JOptionPane.showMessageDialog(SAdminInterface.this, "ȷ�ϱ�����Ϣ");
					
					int column = table3.getColumnCount();		//��ñ������
					int row = table3.getRowCount();		//��ñ������
					
					// value�����ű���е���������
					String[][] value = new String[row][column];
					
					for(int i = 0; i < row; i++){
						for(int j = 0; j < column; j++){
							value[i][j] = (String) table3.getValueAt(i, j);
							//table.getValueAt(i, j).toString()�ᱨnullpointerexception
						}
					}
					
					try {
						//�ر�����������Լ��
						jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Warehouse NOCHECK CONSTRAINT ALL");
						jdbcDrive.jdbcExecuteUpdate("ALTER TABLE Admin NOCHECK CONSTRAINT ALL");
						//ɾWarehouse��
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
					JOptionPane.showMessageDialog(SAdminInterface.this, "Employee����ɹ���");
					
				}
				else if (arg0.getSource().equals(jbexit3)) {
					SAdminInterface.this.dispose();
					new Login();
				}
			}
			
		}
}
