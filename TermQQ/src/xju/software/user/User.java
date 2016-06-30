package xju.software.user;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String nickname;
	private String Ip;
	private String sign;
	private int status; // 0 ¿Îœﬂ  1 …œœﬂ
	private UserImage userimage;
	
	public User() {
		super();
	}
	public User(String username, String password, String nickname, String Ip,
			String sign,int status) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.Ip = Ip;
		this.sign = sign;
		this.status = status;
	}
	
	
	public User(String username, String password, String nickname) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getstatus() {
		return status;
	}
	public void setstatus(int status) {
		this.status = status;
	}
	public UserImage getUserimage() {
		return userimage;
	}
	public void setUserimage(UserImage userimage) {
		this.userimage = userimage;
	}
	public static void main(String[] args) {
	}

}
