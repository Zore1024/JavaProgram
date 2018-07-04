package com.nuc.wuliuinterface;
/*
 * �ֿ����Ա����С����
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
		//�������
		jl1 = new JLabel("����ĵ�����:");
		jl2 = new JLabel("����ֵ:");
		jl3 = new JLabel("�������");
		jtext1 = new JTextField();
		jtext2 = new JTextField();
		jtext3 = new JTextField();
		jtextarea = new JTextArea();
		jbmodify = new JButton("����");
		jbexit = new JButton("����");
		jbclear = new JButton("���");
		jtp = new JTabbedPane();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jsp = new JScrollPane(jtextarea);
		jlphoto = new JLabel(new ImageIcon("photo/xp4.png"));

		//�������
		jl1.setFont(new Font("����", Font.PLAIN, 20));
		jl1.setForeground(Color.BLACK);
		jl2.setFont(new Font("����", Font.PLAIN, 20));
		jl2.setForeground(Color.BLACK);
		jl3.setFont(new Font("����", Font.PLAIN, 20));
		jl3.setForeground(Color.BLACK);
		jtext1.setFont(new Font("����", Font.PLAIN, 20));
		jtext1.setForeground(Color.BLUE);
		jtext2.setFont(new Font("����", Font.PLAIN, 20));
		jtext2.setForeground(Color.BLUE);
		jtext3.setFont(new Font("����", Font.PLAIN, 20));
		jtext3.setForeground(Color.BLUE);
		jbmodify.setFont(new Font("����", Font.PLAIN, 20));
		jbmodify.setForeground(Color.ORANGE);
		jbexit.setFont(new Font("����", Font.PLAIN, 20));
		jbexit.setForeground(Color.ORANGE);
		jbclear.setFont(new Font("����", Font.PLAIN, 20));
		jbclear.setForeground(Color.ORANGE);

		jtp.setFont(new Font("����", Font.PLAIN, 25));
		jtp.setForeground(Color.BLACK);
		jtextarea.setFont(new Font("����", Font.PLAIN, 28));
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		//�������λ�úʹ�С
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

		//�������
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
		jtp.add("����", jp1);
		jtp.add("����", jp2);
		this.add(jtp);
		//��������
		this.setTitle("ɾ��С����");
		setBounds(screenSize.width/2 - 250, screenSize.height/2 - 150, 500, 400);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//���岻�ɸı��С

		//��������
		ButtonListener listener = new ButtonListener();
		jbmodify.addActionListener(listener);
		jbclear.addActionListener(listener);
		jbexit.addActionListener(listener);

		//jtextarea��������
		jtextarea.setText("����С��ʿ:\n");
		jtextarea.append("�����������Ӧ�ı���\n");
		jtextarea.append("������       \t01\n");
		jtextarea.append("ת������һվ \t02\n");
		jtextarea.append("�ռ��˵�����\t03\n");
		jtextarea.append("�ռ��˵���ϵ�绰\t04\n");
		jtextarea.append("�����˵�����\t05\n");
		jtextarea.append("�����˵���ϵ�绰\t06\n");
		jtextarea.append("����״̬ \t07\n");
		jtextarea.append("ע��:����״ֻ̬���������ѳ���\n\n");

		jtextarea.setEditable(false);//���ɱ༭
	}

	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO �Զ����ɵķ������
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
				if (temp.equals("������")||temp.equals("01")) {
					SQL = "UPDATE Goods SET Gname = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("ת������һվ")||temp.equals("02")) {
					SQL = "UPDATE Goods SET Gaddress = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("�ռ��˵�����")||temp.equals("03")) {
					SQL = "UPDATE Goods SET Greceiver = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("�ռ��˵���ϵ�绰")||temp.equals("04")) {
					SQL = "UPDATE Goods SET Grtele = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("�����˵�����")||temp.equals("05")) {
					SQL = "UPDATE Goods SET Gsender = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("�����˵���ϵ�绰")||temp.equals("06")) {
					SQL = "UPDATE Goods SET Gstele = '" + value + "' WHERE Gno = " + gno;
				}
				else if (temp.equals("����״̬")||temp.equals("07")) {
					java.util.Date date = new java.util.Date();          // ��ȡһ��Date����
		            Timestamp timeStamp = new Timestamp(date.getTime()); //   ������ʱ��ת��Ϊ���ݿ��е�timestamp����

					SQL = "UPDATE Operate SET Condition = '" + value + "' WHERE Gno = " + gno;
					SQL1 = "UPDATE Operate SET Outputdate = " + timeStamp + "WHERE Gno = " + gno;
				}
				System.out.println(SQL);
				try{
					jdbcDrive.jdbcExecuteUpdate(SQL);
					jdbcDrive.jdbcExecuteUpdate(SQL1);

				}catch(SQLException e) {
						JOptionPane.showMessageDialog(WAdminModify.this, "���ʧ��! ������벻Ϊ��");
				}finally{
					WareAdminInterface.wdisplay();
					jdbcDrive.jdbcConnectionClose();
				}

			}
		}

	}
}
