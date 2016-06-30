package xju.software.action;

import java.io.Serializable;
import java.util.Date;

import xju.software.user.User;

public class QQClient_Action implements Serializable{
	
	private int type;
	private Long size;
	private String username;
	private String nickname;
	private String password;
	private String filename;
	private String message;
	// 发送消息的对象好友的名称
	private String friendname;
	private User user;
	private String Ip;
	private int port;
	private Date sendTime;
	
	public final static int TYPE_REGIST = 0; //注册
	public final static int TYPE_LOGIN = 1;  //登陆
	public final static int TYPE_TALK  = 2;  //谈话
	public final static int TYPE_LOGOUT = 4;  //登出
	public final static int TYPE_LOADIMG = 5;  //加载图片
	public final static int TYPE_FILE_REFUSE  = 6; //拒绝文件
	public final static int TYPE_FILE_ACCEPT  = 7; //接收文件
	public final static int TYPE_FILE_REQUEST = 8; //发送文件请求
	public final static int TYPE_UPDATE = 9;       //用户更新 
	
	public QQClient_Action(){
		
	}
	
	public QQClient_Action(int type, String username, String password){
		this.type = type;
		this.username = username;
		this.password = password;
	}
	
	public QQClient_Action(int type, String username, String password, String message, String friendname){
		this.type = type;
		this.username = username;
		this.password = password;
		this.message = message;
		this.friendname = friendname;
	}
	
	public QQClient_Action(int type, String username, String password, String filename, String friendname, Long size){
		this.type = type;
		this.username = username;
		this.password = password;
		this.filename = filename;
		this.friendname = friendname;
		this.size = size;
	}
	
	public QQClient_Action(int type, String username, String password, String nickname, String friendname, String message){
		this.type = type;
		this.username = username;
		this.password = password;
		this.friendname = friendname;
		this.message = message;
	}
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public QQClient_Action(int type, User user){
		this.type = type;
		this.user = user;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFriendname() {
		return friendname;
	}

	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIp() {
		return Ip;
	}

	public void setIp(String ip) {
		Ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public static void main(String[] args) {
		
	}
}
