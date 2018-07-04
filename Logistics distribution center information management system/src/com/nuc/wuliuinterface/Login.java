package com.nuc.wuliuinterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.nuc.jdbc.jdbcAdminLogin;
import com.nuc.jdbc.jdbcUserLogin;
/*
 * ����������Ϣ����ϵͳ�������
 */
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;//eclispe�ӵ�
	private JLabel jluser, jlpass, jlremarks;
	private JTextField usertext;
	private JPasswordField passwordtext;
	private JButton loginbutton, exitbutton, clean1, clean2, jbview;
	@SuppressWarnings("rawtypes")//eclipse�ӵ�
	private JComboBox jcombobox;
	private static LoginPhotoPanel loginphotoPanel;

	private Toolkit toolKit;
	private Dimension screenSize = null;

	public static String[] jc= {"ѡ��������", "��ͨ�û�", "�ֿ����Ա", "ϵͳ����Ա"};
	
	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		loginphotoPanel = new LoginPhotoPanel();
		loginphotoPanel.setLayout(null);
		init();
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })//eclispe�ӵ�
	private void init(){

		jluser = new JLabel("�û���");
		usertext = new JTextField();
		//���������������ʽ����ɫ
		jluser.setFont(new Font("����", Font.PLAIN, 18));
		jluser.setForeground(Color.BLACK);

		jlpass = new JLabel("�� ��");
		passwordtext = new JPasswordField();
		//���������������ʽ����ɫ
		jlpass.setFont(new Font("����", Font.PLAIN, 18));
		jlpass.setForeground(Color.BLACK);
		//������
		jcombobox = new JComboBox(jc);
		jcombobox.setFont(new Font("����", Font.PLAIN, 16));
		jcombobox.setForeground(Color.BLUE);

		clean1 = new JButton(new ImageIcon("photo/clean2.png"));
		clean2 = new JButton(new ImageIcon("photo/clean2.png"));
		jbview = new JButton(new ImageIcon("photo/view.png"));

		loginbutton = new JButton("�� ��");
		exitbutton = new JButton("�� ��");
		//���ð�ť�����������ʽ����ɫ
		loginbutton.setFont(new Font("����", Font.PLAIN, 16));
		loginbutton.setForeground(Color.BLUE);
		exitbutton.setFont(new Font("����", Font.PLAIN, 16));
		exitbutton.setForeground(Color.BLUE);

		jlremarks = new JLabel("ע:��ͨ�û������������붼Ϊ�ռ����ռ���");
		jlremarks.setFont(new Font("����", Font.PLAIN, 12));
		jlremarks.setForeground(Color.BLACK);


		//�������λ�úʹ�С
		jluser.setBounds(100, 60, 150, 18);
		loginphotoPanel.add(jluser);;//�û�����ǩ
		usertext.setBounds(160, 60, 221, 18);
		loginphotoPanel.add(usertext);//�û��ı���
		clean1.setBounds(400, 60, 65, 18);
		loginphotoPanel.add(clean1);//�����ť

		jlpass.setBounds(100, 120, 150, 18);
		loginphotoPanel.add(jlpass);//�����ǩ
		passwordtext.setBounds(160, 120, 200, 18);
		loginphotoPanel.add(passwordtext);//�����ı���
		jbview.setBounds(360, 120, 21, 18);
		loginphotoPanel.add(jbview);
		clean2.setBounds(400, 120, 65, 18);
		loginphotoPanel.add(clean2);//�����ť

		loginbutton.setBounds(160, 160, 80, 20);
		loginphotoPanel.add(loginbutton);//���밴ť

		exitbutton.setBounds(260, 160, 80, 20);
		loginphotoPanel.add(exitbutton);//�˳���ť

		jcombobox.setBounds(160, 200, 130, 20);
		loginphotoPanel.add(jcombobox);//������

		jlremarks.setBounds(120, 240, 350, 20);
		loginphotoPanel.add(jlremarks);//ע�ͱ�ǩ

		this.add(loginphotoPanel);

		//��������
		this.setTitle("��ӭ�����б�������Ϣ����ϵͳ");
		setBounds(screenSize.width/2-loginphotoPanel.getWidth()/2
     			,screenSize.height/2-loginphotoPanel.getHeight()/2
     			,loginphotoPanel.getWidth(),loginphotoPanel.getHeight());
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//���ô����С���ɱ�

		//�¼���������
		LoginActionListener listener = new LoginActionListener();
		jcombobox.addItemListener(listener);
		loginbutton.addActionListener(listener);
		exitbutton.addActionListener(listener);
		usertext.addActionListener(listener);
		passwordtext.addActionListener(listener);
		clean1.addActionListener(listener);
		clean2.addActionListener(listener);
		jbview.addActionListener(listener);
	}

	public class LoginActionListener implements ActionListener, ItemListener{
		/*
		 * flag����0ʱ,��ʾ����ͨ�û���ݵ���
		 * flag����1ʱ����ʾ��ϵͳ����Ա��ݵ���
		 * flag����-1ʱ,��ʾ�Բֿ����Ա��ݵ���
		 */
		public int flag = 2;
		public String Str_select = "";
		public boolean view = true;

		public void actionPerformed(ActionEvent e) {
			//��ť�¼������ж�
			if (e.getSource() == loginbutton){

				if (flag == 2) {
					JOptionPane.showMessageDialog(Login.this, "��ѡ����ݵ���");
				}
				String pass = new String(passwordtext.getPassword());
				if (0 == flag) {
					if (jdbcUserLogin.judge(usertext.getText(), pass)) {
						Login.this.dispose();//�رյ���Login���棬�ͷ�ϵͳ��Դ
						new UserInterface();
					}
					else {
							//�û��������벻ƥ��
							JOptionPane.showMessageDialog(Login.this, "����ʧ�ܣ����ǺϷ����û���������");
					}
					}

				if (1 == flag) {
					if (jdbcAdminLogin.judge(usertext.getText(), pass, true) == 1) {
						Login.this.dispose();//�رյ���Login���棬�ͷ�ϵͳ��Դ
						new SAdminInterface();

					}else {
						//ϵͳ����Ա�û��������벻ƥ��
						JOptionPane.showMessageDialog(Login.this, "ϵͳ����Ա����ʧ�ܣ����ǺϷ����û���������");
					}
				}

				if (-1 == flag) {//�ֿ����Ա
					if (jdbcAdminLogin.judge(usertext.getText(), pass, false) == -1) {
						Login.this.dispose();///�رյ���Login���棬�ͷ�ϵͳ��Դ
						new WareAdminInterface();
						//WareAdminInterface.user = usertext.getText();
					}else {
						//�ֿ����Ա�û��������벻ƥ��
						JOptionPane.showMessageDialog(Login.this, "�ֿ����Ա����ʧ�ܣ����ǺϷ����û���������");
					}
				}
				}

			else if (e.getSource() == exitbutton) {
				Login.this.dispose();
			}
			else if (e.getSource() == clean1){
				usertext.setText("");
			}
			else if (e.getSource() == clean2){
				passwordtext.setText("");
			}
			else if (e.getSource() == jbview) {
				view = ! view;
				if (!view) {
					passwordtext.setEchoChar((char)0);
				}
				else if (view) {
					passwordtext.setEchoChar('*');
				}
			}
		}

		public void itemStateChanged(ItemEvent e) {

			if (e.getStateChange() == ItemEvent.SELECTED){
			@SuppressWarnings("rawtypes")//eclipse�ӵ�
			JComboBox cb = (JComboBox)e.getSource();
			Str_select = cb.getSelectedItem().toString();
			if (Str_select.equals("��ͨ�û�")){
				flag = 0;
			}
			else if (Str_select.equals("ϵͳ����Ա")) {
				flag = 1;
			}
			else if (Str_select.equals("�ֿ����Ա")) {
				flag = -1;
			}else if (Str_select.equals("ѡ��������")) {
				flag = 2;
			}
			}
		}

	}

}
