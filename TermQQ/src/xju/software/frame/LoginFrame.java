package xju.software.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;

import xju.software.action.QQSQL;


public class LoginFrame extends JFrame implements MouseListener{
	
	public JPanel Login_panel;
	public JLabel Login_label_minisize;
	public JLabel Login_label_close;
	public JLabel Login_label_photo;
	public JTextField Login_textfield_username;
	public JPasswordField Login_passwordfield_password;
	public JLabel Login_label_button;
	public JLabel Login_label_register;
	public JLabel Login_label_findpassword;
	public JCheckBox Login_checkbox_rememberpassword;
	public JCheckBox Login_checkbox_autologin;
	public JLabel Login_label_add;
	public JLabel Login_label_setting;
	public int flag = 0;
	
	/*
	 * Made By LiZhengYang
	 * 2016~6~25
	 */
	public LoginFrame(){
		this.setTitle("QQ");
		this.setBounds(100, 100, 354, 272);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyPanel Login_mp = new MyPanel();
		this.getContentPane().add(Login_mp);
		this.setUndecorated(true);//设置无边框
		this.setVisible(true); 
		//JPanel中默认为FlowLayout
		Login_mp.setLayout(null);
		setContentPane(Login_mp);
		//给面板添加边框，边框添加 放大缩小功能
		Login_mp.setBorder(new Border(Color.WHITE, 0, this));

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		Login_mp.add(tabbedPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);

		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		
		Login_textfield_username = new JTextField();
		Login_textfield_username.setText("");
		Login_textfield_username.setBounds(108, 132, 160, 24);
		Login_textfield_username.setBorder(new EmptyBorder(0,0,0,0));
		this.add(Login_textfield_username);
		
		Login_label_minisize = new JLabel("");
		Login_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_1.png")));
		Login_label_minisize.setBounds(286, 0, 29, 19);
		Login_label_minisize.addMouseListener(this);
		this.add(Login_label_minisize);
		
		Login_label_close = new JLabel("");
		Login_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_1.png")));
		Login_label_close.setBounds(316, 0, 38, 19);
		Login_label_close.addMouseListener(this);
		this.add(Login_label_close);
		
		Login_passwordfield_password = new JPasswordField();
		Login_passwordfield_password.setText("");
		Login_passwordfield_password.setEchoChar('●');
		Login_passwordfield_password.setBounds(108, 168, 158, 22);
		Login_passwordfield_password.setBorder(new EmptyBorder(0,0,0,0));
		this.add(Login_passwordfield_password);
		
		Login_label_register = new JLabel("注册账号");
		Login_label_register.setFont(new Font("SansSerif", Font.PLAIN, 13));
		Login_label_register.setForeground(new Color(0, 51, 255));
		Login_label_register.setBounds(288, 132, 55, 18);
		Login_label_register.addMouseListener(this);
		this.add(Login_label_register);
		
		Login_label_findpassword = new JLabel("找回密码");
		Login_label_findpassword.setFont(new Font("SansSerif", Font.PLAIN, 13));
		Login_label_findpassword.setForeground(new Color(0, 51, 255));
		Login_label_findpassword.setBounds(288, 167, 55, 18);
		this.add(Login_label_findpassword);
		
		Login_label_photo = new JLabel("");
		Login_label_photo.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/sunset.png")));
		Login_label_photo.setBounds(18, 127, 81, 81);
		this.add(Login_label_photo);
		
		Login_checkbox_rememberpassword = new JCheckBox();
		Login_checkbox_rememberpassword.setText("记住密码");
		Login_checkbox_rememberpassword.setBounds(106, 198, 81, 18);
		this.add(Login_checkbox_rememberpassword);
		
		Login_checkbox_autologin = new JCheckBox();
		Login_checkbox_autologin.setText("自动登陆");
		Login_checkbox_autologin.setBounds(237, 198, 81, 18);
		this.add(Login_checkbox_autologin);
		
		Login_label_button = new JLabel("");
		Login_label_button.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/button_login_1.png")));
		Login_label_button.setBorder(new Border(Color.WHITE, 0, this));
		Login_label_button.setBounds(150, 235, 69, 22);
		Login_label_button.addMouseListener(this);
		this.add(Login_label_button);
		
		Login_label_add = new JLabel("");
		Login_label_add.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_add_1.png")));
		Login_label_add.setBounds(14, 235, 67, 21);
		Login_label_add.addMouseListener(this);
		this.add(Login_label_add);
		
		Login_label_setting = new JLabel("");
		Login_label_setting.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_setting_1.png")));
		Login_label_setting.setBounds(275, 235, 67, 21);
		Login_label_setting.addMouseListener(this);
		this.add(Login_label_setting);
	}

	
	class MyPanel extends JPanel{
		public MyPanel(){}
		@Override
		public void paintComponent(Graphics g){
			int x = 0;
			int y = 0;
			ImageIcon icon = new ImageIcon("C:/Users/String/Desktop/TermQQ/src/xju/software/image/QQ_Login.png");
			g.drawImage(icon.getImage(), x, y, getSize().width, getSize().height, this);
		}
	}
	
	class Border extends LineBorder implements MouseInputListener {
		private static final long serialVersionUID = 1L;
		private JFrame frame;
		private int delta;
		private Point sp;
		private Point cp;
		private int width;
		private int height;

		private boolean top, bottom, left, right, topLeft, topRight,
				bottomLeft, bottomRight;

		public Border(Color color, int delta, JFrame frame) {
			super(color, delta);
			this.delta = delta;
			this.frame = frame;

			addMouseMotionListener(this);
			addMouseListener(this);
		}
		
		public void mouseDragged(MouseEvent e) {
			Point dp = e.getLocationOnScreen();
			// 拖动时的组件原点
			int ox = dp.x - cp.x;
			int oy = dp.y - cp.y;

			// 静止的 原点
			int x = sp.x - cp.x;
			int y = sp.y - cp.y;

			int h = height;
			int w = width;

			if (top) {
				ox = x;
				h = height + (-dp.y + sp.y);
			} else if (bottom) {
				oy = y;
				ox = x;
				h = height + (dp.y - sp.y);
			} else if (left) {
				oy = y;
				w = width + (-dp.x + sp.x);
			} else if (right) {
				oy = y;
				ox = x;
				w = width + (dp.x - sp.x);
			} else if (topLeft) {
				h = height + (-dp.y + sp.y);
				w = width + (-dp.x + sp.x);
			} else if (topRight) {
				ox = x;
				h = height + (-dp.y + sp.y);
				w = width + (dp.x - sp.x);
			} else if (bottomLeft) {
				oy = y;
				h = height + (-dp.y + sp.y);
				w = width + (dp.x - sp.x);
			} else if (bottomRight) {
				ox = x;
				oy = y;
				h = height + (dp.y - sp.y);
				w = width + (+dp.x - sp.x);
			}
			frame.setLocation(ox, oy);
			frame.setSize(w, h);
		}

		public void mouseMoved(MouseEvent arg0) {
			sp = arg0.getLocationOnScreen();
			cp = arg0.getPoint();
			width = frame.getWidth();
			height = frame.getHeight();

			top = cp.x > delta && cp.x < width - delta && cp.y <= delta;
			bottom = cp.x > delta && cp.x < width - delta
					&& cp.y >= height - delta;
			left = cp.x <= delta && cp.y > delta && cp.y < height - delta;
			right = cp.x >= width - delta && cp.y > delta
					&& cp.y < height - delta;

			topLeft = cp.x <= delta && cp.y <= delta;
			topRight = cp.x >= width - delta && cp.y <= delta;

			bottomLeft = cp.x <= delta && cp.y >= height - delta;
			bottomRight = cp.x >= width - delta && cp.y >= height - delta;

			if (top) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
				return;
			} else if (bottom) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
			} else if (left) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
			} else if (right) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
			} else if (topLeft) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
			} else if (topRight) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
			} else if (bottomLeft) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
			} else if (bottomRight) {
				frame.setCursor(Cursor
						.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
			}
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//实现对在面板上按钮的操作的响应
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String username = Login_textfield_username.getText();
		System.out.println(username);
		String password = new String(Login_passwordfield_password.getPassword());
		QQSQL qqsql = new QQSQL();
		
		if (e.getSource() == Login_label_register){
			dispose();
			new RegisterFrame().setVisible(true);
		}
		
		if (e.getSource() == Login_label_button){
			if (username.equals("") || password.equals("")){
				JOptionPane.showMessageDialog(this, "用户名和密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			else if(password.equals(qqsql.SQLConnection_Login("String"))){
				dispose();
				new MainFrame().setVisible(true);;
			}
			else {
				if (qqsql.SQLConnection_Login(username).equals("")){
					JOptionPane.showMessageDialog(this, "用户不存在, 请重新注册！ ", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		if (e.getSource() == Login_label_minisize){
			//最小化窗体
			this.setExtendedState(JFrame.ICONIFIED);
		}
		
		if (e.getSource() == Login_label_close){
			System.exit(0);
		}
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == Login_label_minisize){
			Login_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_2.png")));
		}
		if (e.getSource() == Login_label_close){
			Login_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_2.png")));
		}
		if (e.getSource() == Login_label_add){
			Login_label_add.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_add_2.png")));
		}
		if (e.getSource() == Login_label_button){
			Login_label_button.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/button_login_2.png")));
		}
		if (e.getSource() == Login_label_setting){
			Login_label_setting.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_setting_2.png")));
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Login_label_minisize){
			Login_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_1.png")));
		}
		if (e.getSource() == Login_label_close){
			Login_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_1.png")));
		}
		if (e.getSource() == Login_label_add){
			Login_label_add.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_add_1.png")));
		}
		if (e.getSource() == Login_label_button){
			Login_label_button.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/button_login_1.png")));
		}
		if (e.getSource() == Login_label_setting){
			Login_label_setting.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_setting_1.png")));
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Login_label_minisize){
			Login_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_3.png")));
			
		}
		if (e.getSource() == Login_label_close){
			Login_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_3.png")));
		}
		if (e.getSource() == Login_label_add){
			Login_label_add.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_add_3.png")));
		}
		if (e.getSource() == Login_label_button){
			Login_label_button.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/button_login_3.png")));
		}
		if (e.getSource() == Login_label_setting){
			Login_label_setting.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_setting_3.png")));
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Login_label_minisize){
			Login_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_2.png")));
		}
		if (e.getSource() == Login_label_close){
			Login_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_2.png")));
		}
		if (e.getSource() == Login_label_add){
			Login_label_add.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_add_2.png")));
		}
		if (e.getSource() == Login_label_button){
			Login_label_button.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/button_login_2.png")));
		}
		if (e.getSource() == Login_label_setting){
			Login_label_setting.setIcon(new ImageIcon(LoginFrame.class.getResource("../image/login_setting_2.png")));
		}
	}
	
}
