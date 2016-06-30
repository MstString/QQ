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
	// ��ǩ������(ѡ�)
	private JTabbedPane Main_TabbedPane;
	// ͷ��
	public JLabel Main_label_photo;
	// ״̬��
	public JComboBox Main_combobox_status;
	// �û���
	public JLabel Main_Label_username;
	// ����ǩ��
	public JLabel Main_Label_personalizedsignature;
	// ����
	public JLabel Main_label_setting;
	// �������
	public JPanel Main_panel_setting;
	//�������
	public JPanel Main__panel_now;
	// �û���Ϣ���
	public JPanel Main_panel_userinformation;
	// �������
	public JPanel Main_panel_friends;
	// �����б�
	public JPanel Main_panel_friendslist;
	// �����б��ϵ��
	public JTree Main_tree;
	// ���ѹ����б�
	private JScrollPane Main_scrollpane;
	// �����б���ʽ�˵�
	private JPopupMenu Main_popuMenu_friendslist;
	// ������Ϣ�Ĳ���
	private JMenuItem Main_menuitem_sendmessage;
	// �鿴��Ϣ�Ĳ���
	private JMenuItem Main_menuitem_viewmessage;
	// �����ļ��Ĳ���
	private JMenuItem Main_menuitem_sendfile;
	// ɾ�����ѵĲ���
	private JMenuItem Main_menuitem_dropfriend;

	public MainFrame() {
		MyPanel Main_mp = new MyPanel();
		Container contentPane = this.getContentPane();  
		this.setTitle("QQ");
		// �� System exit �����˳�Ӧ�ó���(JFrame��ʹ��)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 330, 600);
		this.setBackground(new Color(51, 153, 255));// ������
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
