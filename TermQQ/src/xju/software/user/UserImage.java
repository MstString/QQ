package xju.software.user;

import java.io.Serializable;

public class UserImage implements Serializable{
	private static final long serialVersionUID = 1L;
	private String imagename;
	private byte[] imagedata;
	
	public UserImage(){
		
	}
	
	public UserImage(String imagename, byte[] imagedata){
		this.imagename = imagename;
		this.imagedata = imagedata;
	}
	
	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public byte[] getImagedata() {
		return imagedata;
	}

	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}

	public boolean Equals(Object obj){
		UserImage o = (UserImage)obj;
		if (obj == null){
			return false;
		}
		if (!(obj instanceof UserImage))
			return false;
		return this.getImagename().equals(o.getImagename());
	}
	public static void main(String[] args) {
		
	}
}
