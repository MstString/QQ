package xju.software.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import xju.software.user.User;

public class QQServer_Action {
	private static final long serialVersionUID = 1L;
	private int type; 
	private String fromAcceptName;
	private String fromNickName;
	private String toAcceptName;
	private String message;
	private long size;
	private String fileName;
	private String Ip;
	private int  port;
	private User user;
	private Date sendTime;
	private SimpleDateFormat timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ConcurrentHashMap<String, User> onlineUsers = new ConcurrentHashMap<String, User>();
	
	public final static int TYPE_REGIST = 0;
	public final static int TYPE_LOGIN  = 1;
	public final static int TYPE_LOGOUT = 2;
	public final static int TYPE_TALK = 3;
	
	public final static int TYPE_LOADIMG = 5;
	public final static int TYPE_FILE_REFUSE  = 6;
	public final static int TYPE_FILE_ACCEPT  = 7;
	public final static int TYPE_FILE_REQUEST = 8;
	public final static int TYPE_UPDATE = 9;
	public final static int TYPE_AD = 10;
	
	public final static int TYPE_FAIL = -1;
	
	

	public QQServer_Action() {
		super();
	}
	public QQServer_Action(int type, String fromAcceptName,
			String toAcceptName, String msg, long size, String fileName,User user) {
		super();
		this.type = type;
		this.fromAcceptName = fromAcceptName;
		this.toAcceptName = toAcceptName;
		this.message = msg;
		this.size = size;
		this.fileName = fileName;
		this.user = user;
	}
	public QQServer_Action(int type, String fromAcceptName,
			String toAcceptName, long size, String fileName) 
	{
		super();
		this.type = type;
		this.fromAcceptName = fromAcceptName;
		this.toAcceptName = toAcceptName;
		this.size = size;
		this.fileName = fileName;
	}
	
	public QQServer_Action(int type, String message, User user) {
		super();
		this.type = type;
		this.message = message;
		this.user = user;
	}
	public QQServer_Action(int type , User user) {
		super();
		this.type = type;
		this.user = user;
	}
	
	public QQServer_Action(int type, String fromAcceptName,String fromNickName,
			String toAcceptName, String message) {
		super();
		this.type = type;
		this.fromAcceptName = fromAcceptName;
		this.fromNickName = fromNickName;
		this.toAcceptName = toAcceptName;
		this.message = message;
	}
	
	public String toTalkString()
	{
		return this.getFromNickName()+"("+this.getFromAcceptName()+")"+" "+timeNow.format(sendTime)+"\n"+this.getMessage();
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFromAcceptName() {
		return fromAcceptName;
	}
	public void setFromAcceptName(String fromAcceptName) {
		this.fromAcceptName = fromAcceptName;
	}
	public String getFromNickName() {
		return fromNickName;
	}
	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}
	public String getToAcceptName() {
		return toAcceptName;
	}
	public void setToAcceptName(String toAcceptName) {
		this.toAcceptName = toAcceptName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public SimpleDateFormat getTimeNow() {
		return timeNow;
	}
	public void setTimeNow(SimpleDateFormat timeNow) {
		this.timeNow = timeNow;
	}
	public ConcurrentHashMap<String, User> getOnlineUsers() {
		return onlineUsers;
	}
	public void setOnlineUsers(ConcurrentHashMap<String, User> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}
	public static void main(String[] args) {
		
	}
}
