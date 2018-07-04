package com.nuc.wuliuinterface;
/*
 * ��ͨ�û���������
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.nuc.jdbc.jdbcDrive;

public class UserInterface extends JFrame{

	private static final long serialVersionUID = 1L;//eclipse�ӵ�

	//��������
	private JLabel jlbackphoto;

	//�ϲ�����
	private JTextArea jtextarea;
	private JScrollPane jsp;

	//�в�����
	private JTabbedPane jtp;
	private JPanel jp1, jp2;
	private JLabel jl1, jl2;
	private JTextField jtext1, jtext2;
	private JButton querybutton, exitbutton;

	private Toolkit toolKit = null;
	private Dimension screenSize = null;
	protected static ImageIcon icon = new ImageIcon("photo/zb3.png");
	private Font f;//���������ı�����

	public void init() {
		//�������
		jlbackphoto = new JLabel(new ImageIcon("photo/zb3.png"));
		jtextarea = new JTextArea();
		//����JTextArea�������ͺʹ�С
		f = new Font("����", 0, 30);
		jtextarea.setFont(f);
		//
		jsp = new JScrollPane(jtextarea);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jl1 = new JLabel("�� �� �� ��:", JLabel.CENTER);
		jtext1 = new JTextField();
		jl2 = new JLabel("��  ��  ��:", JLabel.CENTER);
		jtext2 = new JTextField();
		jl1.setFont(new Font("����", Font.PLAIN, 28));
		jl1.setForeground(Color.BLUE);
		jl2.setFont(new Font("����", Font.PLAIN, 28));
		jl2.setForeground(Color.BLUE);

		//����JTextField����
		f = new Font("����", 0, 30);
		jtext1.setFont(f);
		jtext2.setFont(f);

		querybutton = new JButton("*��  ѯ*");
		exitbutton = new JButton("*ȡ  ��*");
		querybutton.setFont(new Font("����", Font.PLAIN, 35));
		querybutton.setForeground(Color.BLACK);
		exitbutton.setFont(new Font("����", Font.PLAIN, 35));
		exitbutton.setForeground(Color.BLACK);

		jp1 = new JPanel(new GridLayout(3,2, 10, 10));
		jtp = new JTabbedPane();
		jp2 = new JPanel();

		//������
		jtp.add("������ѯ", jp1);
		jtp.add("����", jp2);
		jtp.setFont(new Font("����", Font.PLAIN, 25));
		jtp.setForeground(Color.BLACK);

		//�����
		jp1.add(jl1);
		jp1.add(jtext1);
		jp1.add(jl2);
		jp1.add(jtext2);
		jp1.add(querybutton);
		jp1.add(exitbutton);

		this.setLayout(null);
		jlbackphoto.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		this.add(jlbackphoto);

		jtp.setBounds(0, icon.getIconHeight(), icon.getIconWidth(), 200);
		this.add(jtp);

		jsp.setBounds(0, icon.getIconHeight()+200, icon.getIconWidth()-15, 300);
		this.add(jsp);


		//���ô�������
		this.setTitle("��ӭ�����б�������ɢ������Ϣ��ѯϵͳ");
		setBounds(screenSize.width/2-icon.getIconWidth()/2
				, screenSize.height/2-icon.getIconHeight()-150
				, icon.getIconWidth(), icon.getIconHeight()+500
				);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(UserInterface.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//���岻�ɸı��С

		//���¼�����
		UserInterfaceLisntener listener1 = new UserInterfaceLisntener();
		querybutton.addActionListener(listener1);
		exitbutton.addActionListener(listener1);

		TabbedListener listener2 = new TabbedListener();
		jtp.addChangeListener(listener2);
	}

	//���캯��
	public UserInterface() {
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		init();
	}
	public static void main(String[] agrs) {
		new UserInterface();
	}
	//�����¼�����
	class UserInterfaceLisntener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO �Զ����ɵķ������
			if (arg0.getSource() == querybutton) {
				querybutton.setForeground(Color.RED);
				String Gno = jtext1.getText();
				String Grtele = jtext2.getText();
				//��User_Select��ͼ�ϲ�ѯ
				String SQL = "SELECT* FROM User_query WHERE Gno = " + Gno + "AND Grtele = " + Grtele;
				try {
					jdbcDrive.jdbcExecuteQuery(SQL);
					//����jtextarea����
					while (jdbcDrive.resultset.next()) {
				    	jtextarea.append("������Ϊ" + jdbcDrive.resultset.getString(1) + "\n��ѯ�������:\n");
				    	jtextarea.append("���ʱ��:" + jdbcDrive.resultset.getString(3) + "\n");
				    	jtextarea.append("����ʱ��:" + jdbcDrive.resultset.getString(4) + "\n");
				    	jtextarea.append("����״̬:" + jdbcDrive.resultset.getString(5) + "\n");
				    }
				}catch (SQLException e) {
					if (Gno.equals("") && Grtele.equals("")) {
						JOptionPane.showMessageDialog(UserInterface.this, "�����ʱ���ռ����ֻ��Ų�Ϊ��");
					}
					else if (Gno.equals("")) {
						JOptionPane.showMessageDialog(UserInterface.this, "�����ʱ಻Ϊ��");
					}
					else if (Grtele.equals("")) {
						JOptionPane.showMessageDialog(UserInterface.this, "�ռ����ֻ��Ų�Ϊ��");
					}
				}finally{
					jdbcDrive.jdbcConnectionClose();
				}
			}
			else if (arg0.getSource() == exitbutton) {
				exitbutton.setForeground(Color.RED);
				UserInterface.this.dispose();
			}
		}

	}

	//ѡ��ļ���
	class TabbedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO �Զ����ɵķ������
			JTabbedPane jtp2 = (JTabbedPane)arg0.getSource();
			int selectedIndex = jtp2.getSelectedIndex();
			if (selectedIndex == 1) {
				//��ӡѡ�
				System.out.println("��0");
				/*
				try {
					String SQL = "SELECT* FROM Goods";
					jdbcDrive.jdbcExecuteQuery(SQL);
					while (jdbcDrive.resultset.next()) {
				    }
				}catch (SQLException e) {

				}
				*/
			}
			else if (selectedIndex == 2) {
				//����ѡ�
			System.out.println("��2");
			}
			else if (selectedIndex == 0) {
				//nothing ��ѯѡ�
				}
		}

	}

}
