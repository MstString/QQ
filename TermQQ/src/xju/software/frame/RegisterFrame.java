package xju.software.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import xju.software.action.QQSQL;

public class RegisterFrame extends JFrame{
	
	public JTextField Register_textfield_username;
	public JTextField Register_textfield_nickname;
	public JPasswordField Register_passwordfield_password;
	public JPasswordField Register_passwordfield_verifypassword;
	public JLabel Register_label_username;
	public JLabel Register_label_nickname;
	public JLabel Register_label_password;
	public JLabel Register_label_verifpassword;
	public String username;
	public String nickname;
	public String password;
	public String verify_password;
	public JButton Register_Button;
	
	public RegisterFrame(){
		MyPanel Register_mp = new MyPanel();
		Container contentPane = this.getContentPane();  
		this.setTitle("QQ×¢²á");
		this.setBounds(150, 150, 300, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(new Color(51, 153, 255));// ºìÂÌÀ¶
		this.setResizable(false);
		contentPane.add(Register_mp);
		
		Register_textfield_username = new JTextField();
		Register_label_username = new JLabel("ÕÊºÅ:");
		Register_textfield_username.setBounds(96, 120, 160, 24);
		Register_label_username.setBounds(45, 123, 55, 18);
		Register_label_username.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Register_label_username.setForeground(new Color(0, 0, 0));
		this.add(Register_textfield_username);
		this.add(Register_label_username);
		
		Register_textfield_nickname = new JTextField();
		Register_label_nickname = new JLabel("êÇ³Æ:");
		Register_textfield_nickname.setBounds(96, 156, 160, 24);
		Register_label_nickname.setBounds(45, 159, 55, 18);
		Register_label_nickname.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.add(Register_textfield_nickname);
		this.add(Register_label_nickname);
		
		Register_passwordfield_password = new JPasswordField();
		Register_label_password = new JLabel("ÃÜÂë:");
		Register_passwordfield_password.setBounds(96, 192, 160, 24);
		Register_label_password.setBounds(45, 195, 55, 18);
		Register_passwordfield_password.setEchoChar('¡ñ');
		Register_label_password.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.add(Register_passwordfield_password);
		this.add(Register_label_password);
		
		Register_passwordfield_verifypassword = new JPasswordField();
		Register_label_verifpassword = new JLabel("ÖØ¸´:");
		Register_passwordfield_verifypassword.setBounds(96, 228, 160, 24);
		Register_label_verifpassword.setBounds(45, 231, 55, 18);
		Register_passwordfield_verifypassword.setEchoChar('¡ñ');
		Register_label_verifpassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		this.add(Register_passwordfield_verifypassword);
		this.add(Register_label_verifpassword);
		
		Register_Button = new JButton("×¢²á");
		Register_Button.setBounds(125, 280, 60, 25);
		this.add(Register_Button);
		contentPane.add(Register_mp);
		
		Register_Button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QQSQL qqsql = new QQSQL();
				String username = Register_textfield_username.getText();
				String nickname = Register_textfield_nickname.getText();
				String password = Register_passwordfield_password.getText();
				String verifypassword = Register_passwordfield_verifypassword.getText();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (username.equals("")){
					JOptionPane.showMessageDialog(Register_Button, "ÕËºÅ²»ÄÜÎª¿Õ£¡  ", "´íÎó", JOptionPane.ERROR_MESSAGE);
				}
				else if (nickname.equals("")){
					JOptionPane.showMessageDialog(Register_Button, "êÇ³Æ²»ÄÜÎª¿Õ£¡  ", "´íÎó", JOptionPane.ERROR_MESSAGE);
				}
				else if (password.equals("")){
					JOptionPane.showMessageDialog(Register_Button, "ÃÜÂë²»ÄÜÎª¿Õ£¡  ", "´íÎó", JOptionPane.ERROR_MESSAGE);
				}
				else if (verifypassword.equals("")){
					JOptionPane.showMessageDialog(Register_Button, "ÃÜÂë²»ÄÜÎª¿Õ£¡  ", "´íÎó", JOptionPane.ERROR_MESSAGE);
				}
				else if (!password.equals(verifypassword)){
					JOptionPane.showMessageDialog(Register_Button, "Á½´ÎÃÜÂë²»Í¬, ÇëÖØÐÂÊäÈë£¡  ", "´íÎó", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						Thread.sleep(100);
						qqsql.SQLConnection_Register(username, nickname, password);
						dispose();
						JOptionPane.showMessageDialog(Register_Button, "¹§Ï²Äú×¢²á³É¹¦, ÇëÖØÐÂµÇÂ½£¡  ", "×¢²á", JOptionPane.DEFAULT_OPTION);
						new LoginFrame().setVisible(true);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
	}
	
	class MyPanel extends JPanel{
		public MyPanel(){}
		
		public void paintComponent(Graphics g){
			int x = 118;
			int y = 20;
			ImageIcon icon = new ImageIcon("C:/Users/String/Desktop/TermQQ/src/xju/software/image/QQ_64.png");
			g.drawImage(icon.getImage(), x, y, 64, 64, this);
		}
	}
	
	public static void main(String[] args) {
		new RegisterFrame().setVisible(true);
	}
}
