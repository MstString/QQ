package xju.software.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import xju.software.action.QQClient_Action;
import xju.software.action.QQServer_Action;
import xju.software.tools.ImageTool;
import xju.software.tools.MyMouseListener;
import xju.software.user.User;

public class MainFrame extends JFrame implements ActionListener{
	
	public MainFrame $this_MainFrame;
	public JTree Main_tree;
	public JScrollPane Main_scrollpane;
	public JPanel Main_panel;
	public Socket socket;
	public ObjectInputStream ois = null;
	public ObjectOutputStream oos = null;
	public User loginuser;
	//�����û�
	private ConcurrentHashMap<String, User> onlineUsers = new ConcurrentHashMap<String, User>();
	//��Ϣ�б���û�򿪶Ի���ʱ�����û����͵���Ϣ�������Ϣ������
	private LinkedHashMap<String, LinkedList<QQServer_Action>> msgMap = new LinkedHashMap<String, LinkedList<QQServer_Action>>();
	//�Ի����б�
	private ConcurrentHashMap<String, ChatFrame> chatFrameMap = new ConcurrentHashMap<String, ChatFrame>();
	//�������нڵ��б�
	private ConcurrentHashMap<String, DefaultMutableTreeNode> userNodeMap = new ConcurrentHashMap<String, DefaultMutableTreeNode>();
	
	public MainFrame(){
		Container contentPane = this.getContentPane();  
		MyPanel Main_mp = new MyPanel();
		this.setTitle("QQ");
		// �� System exit �����˳�Ӧ�ó���(JFrame��ʹ��)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 330, 600);
		this.setBackground(new Color(51, 153, 255));// ������
		this.getContentPane().add(Main_mp);
	}
	
	public MainFrame(User loginuser, Socket socket, ObjectInputStream ois, ObjectOutputStream oos, ConcurrentHashMap<String, User> users) {

//		Container contentPane = this.getContentPane();  
//		this.setTitle("QQ");
//		// �� System exit �����˳�Ӧ�ó���(JFrame��ʹ��)
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setBounds(100, 100, 330, 600);
//		this.setBackground(new Color(51, 153, 255));// ������
//		this.getContentPane().add(Main_mp);
		this.setTitle(loginuser.getNickname());
		this.setUndecorated(true);
		this.setBounds(100, 100, 280, 600);
		MyMouseListener draglistener = new MyMouseListener(this);
		this.addMouseMotionListener(draglistener);
		this.addMouseListener(draglistener);
		
		this.loginuser = loginuser;
		this.socket = socket;
		this.ois = ois;
		this.oos = oos;
	}
		
	class MyPanel extends JPanel{
		public MyPanel(){}
		
		public void paintComponent(Graphics g){
			int x = 20;
			int y = 20;
			ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/src/xju/software/image/sunset.png");
			g.drawImage(icon.getImage(), x, y, 64, 64, this);
		}
	}
	
	public void checkUserImage(User user){
		if (user.getUserimage() != null && !new File(ImageTool.clientImagePath(user)).exists()){
			QQClient_Action client = new QQClient_Action();
			client.setType(QQClient_Action.TYPE_LOADIMG);
			client.setUsername(user.getUsername());
			sendMessage(client);
		}
	}

//	������Ϣ��������
	public void sendMessage(Object o){
		try {
			ois.reset();
			oos.writeObject(o);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void LoadImage(){
		new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
//				����ָ��this���߻ᱨ��
				checkUserImage($this_MainFrame.loginuser);
				for (User user : onlineUsers.values()){
					checkUserImage(user);
				}
			}
			
		}).start();;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);;
	}

}
