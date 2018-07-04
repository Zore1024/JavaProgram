package com.nuc.wuliuinterface;
/*
 * 普通用户操作界面
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

	private static final long serialVersionUID = 1L;//eclipse加的

	//北部区域
	private JLabel jlbackphoto;

	//南部区域
	private JTextArea jtextarea;
	private JScrollPane jsp;

	//中部区域
	private JTabbedPane jtp;
	private JPanel jp1, jp2;
	private JLabel jl1, jl2;
	private JTextField jtext1, jtext2;
	private JButton querybutton, exitbutton;

	private Toolkit toolKit = null;
	private Dimension screenSize = null;
	protected static ImageIcon icon = new ImageIcon("photo/zb3.png");
	private Font f;//用来设置文本字体

	public void init() {
		//创建组件
		jlbackphoto = new JLabel(new ImageIcon("photo/zb3.png"));
		jtextarea = new JTextArea();
		//设置JTextArea字体类型和大小
		f = new Font("楷体", 0, 30);
		jtextarea.setFont(f);
		//
		jsp = new JScrollPane(jtextarea);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jl1 = new JLabel("快 递 编 码:", JLabel.CENTER);
		jtext1 = new JTextField();
		jl2 = new JLabel("手  机  号:", JLabel.CENTER);
		jtext2 = new JTextField();
		jl1.setFont(new Font("楷体", Font.PLAIN, 28));
		jl1.setForeground(Color.BLUE);
		jl2.setFont(new Font("楷体", Font.PLAIN, 28));
		jl2.setForeground(Color.BLUE);

		//设置JTextField字体
		f = new Font("楷体", 0, 30);
		jtext1.setFont(f);
		jtext2.setFont(f);

		querybutton = new JButton("*查  询*");
		exitbutton = new JButton("*取  消*");
		querybutton.setFont(new Font("楷体", Font.PLAIN, 35));
		querybutton.setForeground(Color.BLACK);
		exitbutton.setFont(new Font("楷体", Font.PLAIN, 35));
		exitbutton.setForeground(Color.BLACK);

		jp1 = new JPanel(new GridLayout(3,2, 10, 10));
		jtp = new JTabbedPane();
		jp2 = new JPanel();

		//加容器
		jtp.add("物流查询", jp1);
		jtp.add("其他", jp2);
		jtp.setFont(new Font("楷体", Font.PLAIN, 25));
		jtp.setForeground(Color.BLACK);

		//加组件
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


		//设置窗体属性
		this.setTitle("欢迎进入中北物流集散中心信息查询系统");
		setBounds(screenSize.width/2-icon.getIconWidth()/2
				, screenSize.height/2-icon.getIconHeight()-150
				, icon.getIconWidth(), icon.getIconHeight()+500
				);
		this.setIconImage(new ImageIcon("photo/zbh.png").getImage());
		this.setDefaultCloseOperation(UserInterface.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);//窗体不可改变大小

		//对事件监听
		UserInterfaceLisntener listener1 = new UserInterfaceLisntener();
		querybutton.addActionListener(listener1);
		exitbutton.addActionListener(listener1);

		TabbedListener listener2 = new TabbedListener();
		jtp.addChangeListener(listener2);
	}

	//构造函数
	public UserInterface() {
		toolKit = Toolkit.getDefaultToolkit();
		screenSize = toolKit.getScreenSize();
		init();
	}
	public static void main(String[] agrs) {
		new UserInterface();
	}
	//建立事件监听
	class UserInterfaceLisntener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			if (arg0.getSource() == querybutton) {
				querybutton.setForeground(Color.RED);
				String Gno = jtext1.getText();
				String Grtele = jtext2.getText();
				//在User_Select视图上查询
				String SQL = "SELECT* FROM User_query WHERE Gno = " + Gno + "AND Grtele = " + Grtele;
				try {
					jdbcDrive.jdbcExecuteQuery(SQL);
					//设置jtextarea内容
					while (jdbcDrive.resultset.next()) {
				    	jtextarea.append("货物编号为" + jdbcDrive.resultset.getString(1) + "\n查询结果如下:\n");
				    	jtextarea.append("入库时间:" + jdbcDrive.resultset.getString(3) + "\n");
				    	jtextarea.append("出库时间:" + jdbcDrive.resultset.getString(4) + "\n");
				    	jtextarea.append("货物状态:" + jdbcDrive.resultset.getString(5) + "\n");
				    }
				}catch (SQLException e) {
					if (Gno.equals("") && Grtele.equals("")) {
						JOptionPane.showMessageDialog(UserInterface.this, "货物邮编和收件人手机号不为空");
					}
					else if (Gno.equals("")) {
						JOptionPane.showMessageDialog(UserInterface.this, "货物邮编不为空");
					}
					else if (Grtele.equals("")) {
						JOptionPane.showMessageDialog(UserInterface.this, "收件人手机号不为空");
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

	//选项卡的监听
	class TabbedListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO 自动生成的方法存根
			JTabbedPane jtp2 = (JTabbedPane)arg0.getSource();
			int selectedIndex = jtp2.getSelectedIndex();
			if (selectedIndex == 1) {
				//打印选项卡
				System.out.println("是0");
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
				//帮助选项卡
			System.out.println("是2");
			}
			else if (selectedIndex == 0) {
				//nothing 查询选项卡
				}
		}

	}

}
