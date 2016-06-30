package xju.software.user;

import java.io.Serializable;

public class User{
		private String username;
		private String Ip;
		private UserImage userimage;

		public User(){
			
		}

		public User(String username, String Ip) {
			this.username = username;
			this.Ip = Ip;
		}

		public UserImage getUserimage() {
			return userimage;
		}

		public void setUserimage(UserImage userimage) {
			this.userimage = userimage;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getIp() {
			return Ip;
		}

		public void setIp(String ip) {
			Ip = ip;
		}

	}

