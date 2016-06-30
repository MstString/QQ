package xju.software.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainFrame extends JFrame {
	public JFrame Main_Frame = new JFrame();
	// 标签栏容器(选项卡)
	private JTabbedPane Main_TabbedPane;
	// 头像
	public JLabel Main_label_photo;
	// 状态栏
	public JComboBox Main_combobox_status;
	// 用户名
	public JLabel Main_Label_username;
	// 个新签名
	public JLabel Main_Label_personalizedsignature;
	// 设置
	public JLabel Main_label_setting;
	// 设置面板
	public JPanel Main_panel_setting;
	//最近聊天
	public JPanel Main__panel_now;
	// 用户信息面板
	public JPanel Main_panel_userinformation;
	// 好友面板
	public JPanel Main_panel_friends;
	// 好友列表
	public JPanel Main_panel_friendslist;
	// 好友列表关系树
	public JTree Main_tree;
	// 好友滚动列表
	private JScrollPane Main_scrollpane;
	// 好友列表弹出式菜单
	private JPopupMenu Main_popuMenu_friendslist;
	// 发送消息的侧栏
	private JMenuItem Main_menuitem_sendmessage;
	// 查看消息的侧栏
	private JMenuItem Main_menuitem_viewmessage;
	// 发送文件的侧栏
	private JMenuItem Main_menuitem_sendfile;
	// 删除好友的侧栏
	private JMenuItem Main_menuitem_dropfriend;

	public MainFrame() {
		MyPanel Main_mp = new MyPanel();
		Container contentPane = this.getContentPane();  
		this.setTitle("QQ");
		// 用 System exit 方法退出应用程序(JFrame中使用)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 330, 600);
		this.setBackground(new Color(51, 153, 255));// 红绿蓝
		this.getContentPane().add(Main_mp);
	}
		
	
	class MyPanel extends JPanel{
		public MyPanel(){}
		
		public void paintComponent(Graphics g){
			int x = 20;
			int y = 20;
			ImageIcon icon = new ImageIcon("C:/Users/Rjxy/Desktop/TermQQ/src/xju/software/image/sunset.png");
			g.drawImage(icon.getImage(), x, y, 64, 64, this);
		}
	}
	
	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);;
	}
	
	
}
