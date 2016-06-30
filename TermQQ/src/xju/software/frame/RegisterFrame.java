package xju.software.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import xju.software.action.QQSQL;
import xju.software.tools.ImageTool;
import xju.software.user.User;

public class RegisterFrame extends JFrame implements MouseListener {

	public JTextField Register_textfield_username;
	public JTextField Register_textfield_nickname;
	public JPasswordField Register_passwordfield_password;
	public JPasswordField Register_passwordfield_verifypassword;
	public JLabel Register_label_uploadtext;
	public JLabel Register_label_upload;
	public JLabel Register_label_username;
	public JLabel Register_label_nickname;
	public JLabel Register_label_password;
	public JLabel Register_label_verifpassword;
	public String username;
	public String nickname;
	public String password;
	public String verify_password;
	public JButton Register_button_upload;
	public JButton Register_button_register;
	public JButton Register_button_back;
	
	
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	public User user = new User();

	public RegisterFrame() {
		// MyPanel Register_mp = new MyPanel();
		JPanel Register_mp = new JPanel();
		Container contentPane = this.getContentPane();
		this.setTitle("QQע��");
		this.setBounds(150, 150, 300, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		contentPane.add(Register_mp);

		Register_textfield_username = new JTextField();
		Register_label_username = new JLabel("�ʺ�:");
		Register_textfield_username.setBounds(96, 30, 160, 24);
		Register_label_username.setBounds(45, 33, 55, 18);
		Register_label_username.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Register_label_username.setForeground(new Color(0, 0, 0));
		this.add(Register_textfield_username);
		this.add(Register_label_username);

		Register_textfield_nickname = new JTextField();
		Register_label_nickname = new JLabel("�ǳ�:");
		Register_textfield_nickname.setBounds(96, 66, 160, 24);
		Register_label_nickname.setBounds(45, 69, 55, 18);
		Register_label_nickname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.add(Register_textfield_nickname);
		this.add(Register_label_nickname);

		Register_passwordfield_password = new JPasswordField();
		Register_label_password = new JLabel("����:");
		Register_passwordfield_password.setBounds(96, 102, 160, 24);
		Register_label_password.setBounds(45, 105, 55, 18);
		Register_passwordfield_password.setEchoChar('��');
		Register_label_password.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.add(Register_passwordfield_password);
		this.add(Register_label_password);

		Register_passwordfield_verifypassword = new JPasswordField();
		Register_label_verifpassword = new JLabel("�ظ�:");
		Register_passwordfield_verifypassword.setBounds(96, 138, 160, 24);
		Register_label_verifpassword.setBounds(45, 141, 55, 18);
		Register_passwordfield_verifypassword.setEchoChar('��');
		Register_label_verifpassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.add(Register_passwordfield_verifypassword);
		this.add(Register_label_verifpassword);

		Register_label_upload = new JLabel();
		Register_label_upload.setBounds(60, 178, 64, 64);
		Register_label_upload.setIcon(new ImageIcon(LoginFrame.class.getResource("../Image/upload.png")));
		this.add(Register_label_upload);
		
		Register_button_upload = new JButton("�ϴ�ͷ��");
		Register_button_upload.setBounds(150, 200, 120, 25);
		Register_button_upload.addMouseListener(this);
		this.add(Register_button_upload);
		
		Register_button_register = new JButton("ע��");
		Register_button_register.setBounds(180, 270, 60, 25);
		Register_button_register.addMouseListener(this);
		this.add(Register_button_register);
		
		Register_button_back = new JButton("����");
		Register_button_back.setBounds(60, 270, 60, 25);
		Register_button_back.addMouseListener(this);
		this.add(Register_button_back);
		
		// ͼ�����Ҫ
		contentPane.add(Register_mp);
		Register_mp.setBackground(new Color(51, 153, 255));

	}

	// class MyPanel extends JPanel{
	// public MyPanel(){}
	//
	// public void paintComponent(Graphics g){
	// int x = 118;
	// int y = 20;
	// ImageIcon icon = new
	// ImageIcon("C:/Users/String/Desktop/TermQQ/src/xju/software/image/upload.png");
	// g.drawImage(icon.getImage(), x, y, 64, 64, this);
	// }
	// }

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Register_button_register) {
			// TODO Auto-generated method stub
			QQSQL qqsql = new QQSQL();
			String username = Register_textfield_username.getText();
			String nickname = Register_textfield_nickname.getText();
			String password = Register_passwordfield_password.getText();
			String verifypassword = Register_passwordfield_verifypassword.getText();
			if (username.equals("")) {
				JOptionPane.showMessageDialog(Register_button_register, "�˺Ų���Ϊ�գ�  ", "����", JOptionPane.ERROR_MESSAGE);
			} else if (nickname.equals("")) {
				JOptionPane.showMessageDialog(Register_button_register, "�ǳƲ���Ϊ�գ�  ", "����", JOptionPane.ERROR_MESSAGE);
			} else if (password.equals("")) {
				JOptionPane.showMessageDialog(Register_button_register, "���벻��Ϊ�գ�  ", "����", JOptionPane.ERROR_MESSAGE);
			} else if (verifypassword.equals("")) {
				JOptionPane.showMessageDialog(Register_button_register, "���벻��Ϊ�գ�  ", "����", JOptionPane.ERROR_MESSAGE);
			} else if (!password.equals(verifypassword)) {
				JOptionPane.showMessageDialog(Register_button_register, "�������벻ͬ, ���������룡  ", "����", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					user.setUsername(username);
					user.setNickname(nickname);
					user.setPassword(password);
					try {
						//��ͼƬ����·��
						ImageTool.transUserImageToFile(ImageTool.getProperty("Client_UserImages_path", "") + user.getUsername() + "/", user.getUserimage());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Thread.sleep(100);
					if (qqsql.SQLConnection_Login(username).equals("")){
					qqsql.SQLConnection_Register(username, nickname, password);
					dispose();
					JOptionPane.showMessageDialog(Register_button_register, "��ϲ��ע��ɹ�, �����µ�½��  ", "ע��",
							JOptionPane.DEFAULT_OPTION);
					new LoginFrame().setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(Register_button_register, "�Բ����û����Ѵ���, ������ע�ᣡ  ", "����",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		if (e.getSource() == Register_button_back){
			dispose();
			new LoginFrame().setVisible(true);
		}
		
		if (e.getSource() == Register_button_upload) {

			if (Register_textfield_username.getText().equals("")){
				JOptionPane.showMessageDialog(Register_button_upload, "�뽫��Ϣ��д������  ", "����", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return ImageTool.allowUserImgInfo();
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						}
						return ImageTool.isAllowUserImg(f.getName());
					}
				});
				if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
					try {
						File image = chooser.getSelectedFile();
						if (!ImageTool.isAllowUserImg(image.getName())) {
							JOptionPane.showMessageDialog(this, "��ѡ��ͼƬ", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
							return;
						}
						// �Ȱ�ͼƬ�ϴ���Upload�ļ��У���ͼƬ��С��ָ����С��Ȼ����С���ͼƬ���ֽڱ������û��У�ɾ��uploadĿ¼�µ�ͼƬ
						String tempPath = System.getProperty("user.dir") + "/lib/Upload/"
								+ Register_textfield_username.getText().trim() + "_" + System.currentTimeMillis()
								+ image.getName().substring(image.getName().lastIndexOf("."));
//						System.out.println(tempPath);
//						System.out.println(image.getPath());
						//(ѡ���ͼƬ·��, �ϴ����ļ�·��)
						ImageTool.reduceImageByRatio(image.getPath(), tempPath, 64, 64);
						File tempImg = new File(tempPath);
						if (tempImg.exists()) {
							user.setUserimage(ImageTool.transFileToUserImage(Register_textfield_username.getText().trim(), tempImg));
							Register_label_upload.setIcon(new ImageIcon(ImageTool.getImageByPath(tempPath)));
							tempImg.delete();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					user.setUserimage(null); 
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new RegisterFrame().setVisible(true);
		// System.out.println(System.getProperty("users.dir"));
	}
}
