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
 * �ֿ����Ա����Ĳ�ѯ��
 * �ֿ����Ա��ѯ����С����
 */
public class WAdminQuery extends JFrame {

	private static final long serialVersionUID = 1L;//eclipse�ӵ�
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
		//�������
		jrb1 = new JRadioButton("���ֿ��ŷ���");
		jrb2 = new JRadioButton("���ֻ��˵绰����");
		jrb3 = new JRadioButton("��ת����һվ����");
		group = new ButtonGroup();
		jbquery = new JButton("��ѯ");
		jbexit = new JButton("����");
		jpl = new JPanel();
		//�������
		jbquery.setFont(new Font("����", Font.PLAIN, 16));
		jbquery.setForeground(Color.BLUE);
		jbexit.setFont(new Font("����", Font.PLAIN, 16));
		jbexit.setForeground(Color.BLUE);

		//�������
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

		this.setTitle("��ѯС����");
		setBounds(screenSize.width/2 - 150, screenSize.height/2 - 150, 250, 200);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WareAdminInterface.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//���岻�ɸı��С

		//�¼�����
		QueryListener listener = new QueryListener();
		jrb1.addActionListener(listener);
		jrb2.addActionListener(listener);
		jrb3.addActionListener(listener);
		jbquery.addActionListener(listener);
		jbexit.addActionListener(listener);
	}
	//������
	class QueryListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO �Զ����ɵķ������
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
				WareAdminInterface.jtext.setText("��ѯ��:\n\n");
				try {
					jdbcDrive.jdbcExecuteQuery(SQL);
					//����jtext����
					while (jdbcDrive.resultset.next()) {
						WareAdminInterface.jtext.append("������Ϊ: " + jdbcDrive.resultset.getString(1));
						WareAdminInterface.jtext.append(" �ֿ��: " + jdbcDrive.resultset.getString(2));
						WareAdminInterface.jtext.append(" ������һվ��ַ: " + jdbcDrive.resultset.getString(3));
						WareAdminInterface.jtext.append(" ���ʱ��: " + jdbcDrive.resultset.getString(4));
						WareAdminInterface.jtext.append(" ����ʱ��: " + jdbcDrive.resultset.getString(5));
						WareAdminInterface.jtext.append(" ����״̬: " + jdbcDrive.resultset.getString(6) + "\n");
						WareAdminInterface.jtext.append("�����˵绰: " + jdbcDrive.resultset.getString(7));
						WareAdminInterface.jtext.append(" �ռ��˵绰: " + jdbcDrive.resultset.getString(8));
						WareAdminInterface.jtext.append(" ����������: " + jdbcDrive.resultset.getString(9));
						WareAdminInterface.jtext.append(" �ռ��˵绰: " + jdbcDrive.resultset.getString(10));
						WareAdminInterface.jtext.append(" ������: " + jdbcDrive.resultset.getString(11) + "\n\n");
				    }
				}catch(SQLException e) {
					System.out.println("�ֿ����Ա�����쳣");
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

