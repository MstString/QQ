package xju.software.frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import xju.software.tools.MyMouseListener;
import xju.software.user.User;

public class MainFrame extends JFrame implements MouseListener{
	private JTextArea Main_textArea_write;
	private JTextArea Main_textArea_show;
	private JLabel Main_label_conn;
	private JLabel Main_label_send;
	private JLabel Main_label_close_down;
	private JLabel Main_Label_name;
	private JScrollPane scrollpane_show;
	private JLabel Main_label_minisize;
	private JLabel Main_label_close;
//	private JScrollPane scrollPane_write;
	
	private DefaultListModel listModel;
	private boolean isConnected = false;
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private MessageThread messageThread;// 负责接收消息的线程
	private Map<String, User> onLineUsers = new HashMap<String, User>();// 所有在线用户
	
	private String Username;
	private MainFrame mainframe;
	
	public MainFrame(int port, String IP, String Username){
		this.setTitle("聊天界面");
		this.setBounds(200, 200, 580, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		MyMouseListener draglistener = new MyMouseListener(this);
		this.addMouseMotionListener(draglistener);
		this.addMouseListener(draglistener);
		this.Username = Username;
		
		MyPanel Main_mp = new MyPanel();
		Main_mp.setLayout(null);
		getContentPane().add(Main_mp, BorderLayout.CENTER);
		
		Main_Label_name = new JLabel(Username);
		Main_Label_name.setOpaque(false);
		Main_Label_name.setFont(new Font("Courier New", Font.PLAIN, 25));
		Main_Label_name.setBounds(14, 5, 580, 40);
		Main_mp.add(Main_Label_name);
		
		Main_textArea_show = new JTextArea();
		Main_textArea_show.setBounds(5, 85, 580, 280);
		Main_textArea_show.setOpaque(false);
		Main_textArea_show.setEnabled(false);
		Main_mp.add(Main_textArea_show);
		
		Main_textArea_write = new JTextArea();
//		scrollPane_write = new JScrollPane();
		Main_textArea_write.setBounds(5, 395, 580, 75);
//		scrollPane_write.setBounds(0, 368, 580, 132);
//		scrollPane_write.setViewportView(Main_textArea_write);
//		scrollPane_write.setOpaque(false);
//		设置透明
		Main_textArea_write.setOpaque(false);
//		选择不加入scrollPane_write
		Main_mp.add(Main_textArea_write);
		
		Main_label_minisize = new JLabel();
		Main_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_1.png")));
		Main_label_minisize.setBounds(515, 0, 29, 19);
		Main_label_minisize.addMouseListener(this);
		Main_mp.add(Main_label_minisize);
		
		Main_label_close = new JLabel();
		Main_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_1.png")));
		Main_label_close.setBounds(547, 0, 38, 19);
		Main_label_close.addMouseListener(this);
		Main_mp.add(Main_label_close);
		
		Main_label_conn = new JLabel("连接");
		Main_label_conn.setOpaque(false);
		Main_label_conn.setBounds(515, 375, 85, 20);
		Main_label_conn.setFont(new Font("SansSerif", Font.PLAIN, 15));
		Main_label_conn.addMouseListener(this);
		Main_mp.add(Main_label_conn);
		
		Main_label_send = new JLabel();
		Main_label_send.setOpaque(false);
		Main_label_send.setBounds(490, 472, 85, 25);
		Main_label_send.addMouseListener(this);
		Main_mp.add(Main_label_send);
		
		Main_label_close_down = new JLabel();
		Main_label_close_down.setOpaque(false);
		Main_label_close_down.setBounds(419, 472, 69, 25);
		Main_label_close_down.addMouseListener(this);
		Main_mp.add(Main_label_close_down);
	}

	class MyPanel extends JPanel{
		public MyPanel(){}
		@Override
		public void paintComponent(Graphics g){
			int x = 0;
			int y = 0;
			ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/src/xju/software/image/QQChat.png");
			g.drawImage(icon.getImage(), x, y, getSize().width, getSize().height, this);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Main_label_conn){
			if (isConnected) {
				JOptionPane.showMessageDialog(this, "已处于连接上状态，不要重复连接!",
						"错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			boolean flag = connectServer(8081, "127.0.0.1");
			if (flag == false) {
				try {
					throw new Exception("与服务器连接失败!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == Main_label_send){
			send();
		}
		if (e.getSource() == Main_label_minisize){
			//最小化窗体
			this.setExtendedState(JFrame.ICONIFIED);
		}
		
		if (e.getSource() == Main_label_close){
			if (isConnected) {
				closeConnection();// 关闭连接
			}
			System.exit(0);// 退出程序
		}
		if (e.getSource() == Main_label_close_down){
			if (!isConnected) {
				JOptionPane.showMessageDialog(this, "已处于断开状态，不要重复断开!",
						"错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				boolean flag = closeConnection();// 断开连接
				if (flag == false) {
					throw new Exception("断开连接发生异常！");
				}
				JOptionPane.showMessageDialog(this, "成功断开!");
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(this, exc.getMessage(),
						"错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Main_label_minisize){
			Main_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_2.png")));
		}
		if (e.getSource() == Main_label_close){
			Main_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_2.png")));
		}
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Main_label_minisize){
			Main_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_1.png")));
		}
		if (e.getSource() == Main_label_close){
			Main_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_1.png")));
		}
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Main_label_minisize){
			Main_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_3.png")));
			
		}
		if (e.getSource() == Main_label_close){
			Main_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_3.png")));
		}
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Main_label_minisize){
			Main_label_minisize.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/login_minsize_2.png")));
		}
		if (e.getSource() == Main_label_close){
			Main_label_close.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/Login_close_2.png")));
		}
	}
	
	// 执行发送
	public void send() {
		String message = Main_textArea_write.getText().trim();
		if (message == null || message.equals("")) {
			JOptionPane.showMessageDialog(this, "消息不能为空！", "错误",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		sendMessage(Username + "@" + "ALL" + "@" + message);
//		System.out.println(Username);
		Main_textArea_write.setText(null);
	}
	

	/**
	 * 连接服务器
	 * 
	 * @param port
	 * @param hostIp
	 * @param name
	 */
	public boolean connectServer(int port, String hostIp) {
		// 连接服务器
		try {
			socket = new Socket(hostIp, port);// 根据端口号和服务器ip建立连接
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			// 发送客户端用户基本信息(用户名和ip地址)
			sendMessage(Username + "@" + socket.getLocalAddress().toString());
			// 开启接收消息的线程
			messageThread = new MessageThread(reader, Main_textArea_show);
			messageThread.start();
			isConnected = true;// 已经连接上了
			return true;
		} catch (Exception e) {
			Main_textArea_show.setFont(new Font("SansSerif", Font.PLAIN, 15));
			Main_textArea_show.append("与端口号为：" + port + "    IP地址为：" + hostIp
					+ "   的服务器连接失败!" + "\r\n");
			isConnected = false;// 未连接上
			return false;
		}
	}
	
	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		writer.println(message);
		writer.flush();
	}

	
	/**
	 * 客户端主动关闭连接
	 */
	public synchronized boolean closeConnection() {
		try {
			sendMessage("CLOSE");// 发送断开连接命令给服务器
			messageThread.stop();// 停止接受消息线程
			// 释放资源
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
			if (socket != null) {
				socket.close();
			}
			isConnected = false;
			return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			isConnected = true;
			return false;
		}
	}

	// 不断接收消息的线程
	class MessageThread extends Thread {
		private BufferedReader reader;
		private JTextArea textArea;

		// 接收消息线程的构造方法
		public MessageThread(BufferedReader reader, JTextArea textArea) {
			this.reader = reader;
			this.textArea = textArea;
		}

		// 被动的关闭连接
		public synchronized void closeCon() throws Exception {
			// 清空用户列表
			listModel.removeAllElements();
			// 被动的关闭连接释放资源
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
			if (socket != null) {
				socket.close();
			}
			isConnected = false;// 修改状态为断开
		}

		public void run() {
			String message = "";
			while (true) {
				try {
					message = reader.readLine();
					StringTokenizer stringTokenizer = new StringTokenizer(
							message, "/@");
					String command = stringTokenizer.nextToken();// 命令
					if (command.equals("CLOSE"))// 服务器已关闭命令
					{
						textArea.append("服务器已关闭!\r\n");
						closeCon();// 被动的关闭连接
						return;// 结束线程
					} else if (command.equals("ADD")) {// 有用户上线更新在线列表
						String username = "";
						String userIp = "";
						if ((username = stringTokenizer.nextToken()) != null
								&& (userIp = stringTokenizer.nextToken()) != null) {
							User user = new User(username, userIp);
							onLineUsers.put(username, user);
							listModel.addElement(username);
						}
					} else if (command.equals("DELETE")) {// 有用户下线更新在线列表
						String username = stringTokenizer.nextToken();
						User user = (User) onLineUsers.get(username);
						onLineUsers.remove(user);
						listModel.removeElement(username);
					} else if (command.equals("USERLIST")) {// 加载在线用户列表
						int size = Integer
								.parseInt(stringTokenizer.nextToken());
						String username = null;
						String userIp = null;
						for (int i = 0; i < size; i++) {
							username = stringTokenizer.nextToken();
							userIp = stringTokenizer.nextToken();
							User user = new User(username, userIp);
							onLineUsers.put(username, user);
							listModel.addElement(username);
						}
					} else if (command.equals("MAX")) {// 人数已达上限
						textArea.append(stringTokenizer.nextToken()
								+ stringTokenizer.nextToken() + "\r\n");
						closeCon();// 被动的关闭连接
						JOptionPane.showMessageDialog(mainframe, "服务器缓冲区已满！", "错误",
								JOptionPane.ERROR_MESSAGE);
						return;// 结束线程
					} else {// 普通消息
						textArea.append(message + "\r\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
//		new MainFrame(8081, "127.0.0.1", "String").setVisible(true);
	}
}
