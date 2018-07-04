package com.nuc.wuliuinterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.nuc.jdbc.jdbcAdminLogin;
import com.nuc.jdbc.jdbcUserLogin;
/*
 * 物流中心信息管理系统登入界面
 */
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;//eclispe加的
	private JLabel jluser, jlpass, jlremarks;
	private JTextField usertext;
	private JPasswordField passwordtext;
	private JButton loginbutton, exitbutton, clean1, clean2, jbview;
	@SuppressWarnings("rawtypes")//eclipse加的
	private JComboBox jcombobox;
	private static LoginPhotoPanel loginphotoPanel;

	private Toolkit toolKit;
	private Dimension screenSize = null;

	public static String[] jc= {"选择登入身份", "普通用户", "仓库管理员", "系统管理员"};
	
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


	@SuppressWarnings({ "unchecked", "rawtypes" })//eclispe加的
	private void init(){

		jluser = new JLabel("用户名");
		usertext = new JTextField();
		//设置组件的字体样式和颜色
		jluser.setFont(new Font("楷体", Font.PLAIN, 18));
		jluser.setForeground(Color.BLACK);

		jlpass = new JLabel("密 码");
		passwordtext = new JPasswordField();
		//设置组件的字体样式和颜色
		jlpass.setFont(new Font("楷体", Font.PLAIN, 18));
		jlpass.setForeground(Color.BLACK);
		//下拉框
		jcombobox = new JComboBox(jc);
		jcombobox.setFont(new Font("楷体", Font.PLAIN, 16));
		jcombobox.setForeground(Color.BLUE);

		clean1 = new JButton(new ImageIcon("photo/clean2.png"));
		clean2 = new JButton(new ImageIcon("photo/clean2.png"));
		jbview = new JButton(new ImageIcon("photo/view.png"));

		loginbutton = new JButton("登 入");
		exitbutton = new JButton("退 出");
		//设置按钮组件的字体样式和颜色
		loginbutton.setFont(new Font("楷体", Font.PLAIN, 16));
		loginbutton.setForeground(Color.BLUE);
		exitbutton.setFont(new Font("楷体", Font.PLAIN, 16));
		exitbutton.setForeground(Color.BLUE);

		jlremarks = new JLabel("注:普通用户登入名和密码都为收件人收件号");
		jlremarks.setFont(new Font("楷体", Font.PLAIN, 12));
		jlremarks.setForeground(Color.BLACK);


		//设置组件位置和大小
		jluser.setBounds(100, 60, 150, 18);
		loginphotoPanel.add(jluser);;//用户名标签
		usertext.setBounds(160, 60, 221, 18);
		loginphotoPanel.add(usertext);//用户文本框
		clean1.setBounds(400, 60, 65, 18);
		loginphotoPanel.add(clean1);//清除按钮

		jlpass.setBounds(100, 120, 150, 18);
		loginphotoPanel.add(jlpass);//密码标签
		passwordtext.setBounds(160, 120, 200, 18);
		loginphotoPanel.add(passwordtext);//密码文本框
		jbview.setBounds(360, 120, 21, 18);
		loginphotoPanel.add(jbview);
		clean2.setBounds(400, 120, 65, 18);
		loginphotoPanel.add(clean2);//清除按钮

		loginbutton.setBounds(160, 160, 80, 20);
		loginphotoPanel.add(loginbutton);//登入按钮

		exitbutton.setBounds(260, 160, 80, 20);
		loginphotoPanel.add(exitbutton);//退出按钮

		jcombobox.setBounds(160, 200, 130, 20);
		loginphotoPanel.add(jcombobox);//下拉框

		jlremarks.setBounds(120, 240, 350, 20);
		loginphotoPanel.add(jlremarks);//注释标签

		this.add(loginphotoPanel);

		//窗体设置
		this.setTitle("欢迎进入中北物流信息管理系统");
		setBounds(screenSize.width/2-loginphotoPanel.getWidth()/2
     			,screenSize.height/2-loginphotoPanel.getHeight()/2
     			,loginphotoPanel.getWidth(),loginphotoPanel.getHeight());
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//设置窗体大小不可变

		//事件监听建立
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
		 * flag等于0时,表示以普通用户身份登入
		 * flag等于1时，表示以系统管理员身份登入
		 * flag等于-1时,表示以仓库管理员身份登入
		 */
		public int flag = 2;
		public String Str_select = "";
		public boolean view = true;

		public void actionPerformed(ActionEvent e) {
			//按钮事件监听判断
			if (e.getSource() == loginbutton){

				if (flag == 2) {
					JOptionPane.showMessageDialog(Login.this, "请选择身份登入");
				}
				String pass = new String(passwordtext.getPassword());
				if (0 == flag) {
					if (jdbcUserLogin.judge(usertext.getText(), pass)) {
						Login.this.dispose();//关闭登入Login界面，释放系统资源
						new UserInterface();
					}
					else {
							//用户名和密码不匹配
							JOptionPane.showMessageDialog(Login.this, "登入失败！不是合法的用户名或密码");
					}
					}

				if (1 == flag) {
					if (jdbcAdminLogin.judge(usertext.getText(), pass, true) == 1) {
						Login.this.dispose();//关闭登入Login界面，释放系统资源
						new SAdminInterface();

					}else {
						//系统管理员用户名和密码不匹配
						JOptionPane.showMessageDialog(Login.this, "系统管理员登入失败！不是合法的用户名或密码");
					}
				}

				if (-1 == flag) {//仓库管理员
					if (jdbcAdminLogin.judge(usertext.getText(), pass, false) == -1) {
						Login.this.dispose();///关闭登入Login界面，释放系统资源
						new WareAdminInterface();
						//WareAdminInterface.user = usertext.getText();
					}else {
						//仓库管理员用户名和密码不匹配
						JOptionPane.showMessageDialog(Login.this, "仓库管理员登入失败！不是合法的用户名或密码");
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
			@SuppressWarnings("rawtypes")//eclipse加的
			JComboBox cb = (JComboBox)e.getSource();
			Str_select = cb.getSelectedItem().toString();
			if (Str_select.equals("普通用户")){
				flag = 0;
			}
			else if (Str_select.equals("系统管理员")) {
				flag = 1;
			}
			else if (Str_select.equals("仓库管理员")) {
				flag = -1;
			}else if (Str_select.equals("选择登入身份")) {
				flag = 2;
			}
			}
		}

	}

}
