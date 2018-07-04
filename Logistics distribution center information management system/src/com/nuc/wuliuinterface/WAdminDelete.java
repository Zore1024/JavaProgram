package com.nuc.wuliuinterface;
/*
 * �ֿ����Ա��ɾ��С����
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.nuc.jdbc.jdbcDrive;

public class WAdminDelete extends JFrame{

	private static final long serialVersionUID = 1L;//eclipse�ӵ�
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
		//�������
		jlb = new JLabel("ɾ���Ļ�����", JLabel.CENTER);
		jtext = new JTextField();
		jbselect = new JButton("ȷ��");
		jbexit = new JButton("����");
		jlbphoto = new JLabel(new ImageIcon("photo/kejilou.png"));
		//����������ʽ��ɫ
		jlb.setFont(new Font("����", Font.PLAIN, 20));
		jlb.setForeground(Color.BLACK);
		jtext.setFont(new Font("����", Font.PLAIN, 20));
		jtext.setForeground(Color.BLACK);
		jbselect.setForeground(Color.BLUE);
		jbselect.setFont(new Font("����", Font.PLAIN, 20));
		jbexit.setForeground(Color.BLUE);
		jbexit.setFont(new Font("����", Font.PLAIN, 20));
		//�������λ��
		jlb.setBounds(70, 30, 150, 30);
		jtext.setBounds(240, 30, 200, 28);
		jbselect.setBounds(70, 85, 150, 30);
		jbexit.setBounds(290, 85, 150, 30);
		jlbphoto.setBounds(0, 0, 500, 160);
		//�����
		this.setLayout(null);
		this.add(jlb);
		this.add(jtext);
		this.add(jbselect);
		this.add(jbexit);
		this.add(jlbphoto);
		//��������
		this.setTitle("ɾ��С����");
		setBounds(screenSize.width/2 - 250, screenSize.height/2 - 150, 500, 160);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(WAdminDelete.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//���岻�ɸı��С

		//��������
		DeleteListener listener = new DeleteListener();
		jbselect.addActionListener(listener);
		jbexit.addActionListener(listener);
	}
	class DeleteListener implements ActionListener{
		String wno = null;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO �Զ����ɵķ������
			if (arg0.getSource().equals(jbselect)) {
				String Gno = jtext.getText();
				String SQL1 = "DELETE FROM Operate WHERE Gno = " + Gno ; //��ɾ��������Operate�е�Gno
				String SQL2 = "DELETE FROM Goods WHERE Gno = " + Gno;
				String SQL = "SELECT Gno FROM Goods Where Gno = " + Gno;
				try {
					jdbcDrive.jdbcExecuteQuery(SQL);
					if (jdbcDrive.resultset.next()) {//�ж����ݿ����ǲ�����Ҫɾ��������

						jdbcDrive.jdbcExecuteQuery("SELECT Wno FROM Operate WHERE Gno = " + Gno);
						if (jdbcDrive.resultset.next()) {
							wno = jdbcDrive.resultset.getString(1);
						}
						System.out.println(wno);

						jdbcDrive.jdbcExecuteUpdate(SQL1);
						jdbcDrive.jdbcExecuteUpdate(SQL2);
						WareAdminInterface.jtext.setText("�ɹ�ɾ������GnoΪ" + Gno + "�Ļ���\n\n");
						jdbcDrive.jdbcExecuteUpdate("UPDATE Warehouse SET Wnumber = Wnumber - 1 WHERE Wno = " + wno);
						WareAdminInterface.wdisplay();
					}else {
						WareAdminInterface.jtext.append("ɾ��ʧ�ܣ����ݿ�û�ж�Ӧ�Ļ������\n");
						JOptionPane.showMessageDialog(WAdminDelete.this, "ɾ��ʧ�ܣ����ݿ�û�ж�Ӧ�Ļ������");
					}

				}catch(SQLException e) {
					JOptionPane.showMessageDialog(WAdminDelete.this, "�����ʱ಻Ϊ��");
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
