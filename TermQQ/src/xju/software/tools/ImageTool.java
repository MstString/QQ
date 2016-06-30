package xju.software.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import xju.software.user.User;
import xju.software.user.UserImage;

public class ImageTool {
	public static Properties properties;
	public static final long Max_Image_Size = 1024*1024;
	public static final String[] Image_Suffix = {"JPG","GIF","PNG"};
	public static String Image_Suffix_Info = "";
	//��������ת��Ϊ��Ҫ�ĸ�ʽ
	public static SimpleDateFormat Needdata = new SimpleDateFormat("yyMMdd_HHmmss"); 
	
	// ֻ��ִ��һ��
	static
	{
		for (String suffix : Image_Suffix) 
		{
			Image_Suffix_Info = Image_Suffix_Info + (suffix+",");
		}
		if (Image_Suffix.length > 0){
			Image_Suffix_Info = Image_Suffix_Info.substring(0, Image_Suffix_Info.length()-1);
		}
		else {
			Image_Suffix_Info = Image_Suffix_Info;
		}
		
		//��ȡ�����ļ�
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("lib/setting.properties");
			properties = new Properties();
			try {
				properties.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//�ж��Ƿ������������ļ��ĺ�׺��
	public static boolean isAllowUserImg(String fileName)
	{
		//"."ռһ���ַ�����ȡ��׺��
		String imagesuffix = fileName.substring(fileName.lastIndexOf(".")+1);
		for (String suffix : Image_Suffix) 
		{
			//���Դ�Сд
			if(suffix.equalsIgnoreCase(imagesuffix))
				return true;
		}
		return false;
	}
	
	public static String allowUserImgInfo()
	{
		return Image_Suffix_Info;
	}
	
	public static UserImage transFileToUserImage(String userName ,File image)throws Exception
	{
		String fileName = image.getName();
		String imageName = userName + "_" + Needdata.format(new Date()) + fileName.substring(fileName.indexOf("."));
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(image);
			byte[] bytes = new byte[(int)image.length()];
			fis.read(bytes);
			return new UserImage(imageName, bytes);
		} catch (Exception e) {
			throw e;
		} finally
		{
			if(fis != null)
			fis.close();
		}
	}
	
	public static UserImage getUserImage(String imageName, File image)throws Exception
	{
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(image);
			byte[] bytes = new byte[(int)image.length()];
			fi.read(bytes);
			return new UserImage(imageName, bytes);
		} catch (Exception e) {
			throw e;
		} finally
		{
			if(fi != null)fi.close();
		}
	}
	
	public static boolean transUserImageToFile(String dirPath, UserImage userImage)throws Exception
	{
		FileOutputStream fos = null;
		File img = null;
		try {
			File dir = new File(dirPath);
			if(!dir.exists())
				dir.mkdirs();
			img = new File(dirPath + userImage.getImagename());
			fos =  new FileOutputStream(img);
			fos.write(userImage.getImagedata());
			fos.flush();
			return img.exists();
		} catch (Exception e) {
			throw e;
		} finally {
			if(fos!= null)fos.close();
		}
	}
	
	public static Image getImageByPath(String path)throws Exception
	{
		return new ImageIcon(path).getImage();
	}
	
	public static String getProperty(String key, String defaultValue)
	{
		String value = properties.getProperty(key);
		if (value == null){
			return defaultValue;
		}
		else 
			return value;
	}
	
	// ��ȡ�ͻ���ͼƬ�ļ���
	public static String clientImageDir(String username)
	{
		return  System.getProperty("user.dir")+"/" + getProperty("Client_UserImages_path", "") + username + "/";
	}
	
	// ��ȡ�ͻ���ͼƬ��ŵľ���·��
	public static String clientImagePath(User user)
	{
		return clientImageDir(user.getUsername()) + user.getUserimage().getImagename();
	}
	// ��ȡ�����ͼƬ�ļ���
	public static String serverImageDir(String username)
	{
		return System.getProperty("user.dir")+"/" + getProperty("Server_UserImages_path", "") + username + "/";
	}
	//��ȡ�����ͼƬ��ŵľ���·��
	public static String serverImagePath(User user)
	{
		return serverImageDir(user.getUsername())+user.getUserimage().getImagename();
	}
	

	/**
     * ��СͼƬ��ָ����С
     * @param srcImagePath ��ȡͼƬ·��
     * @param toImagePath д��ͼƬ·��
     * @param widthRatio	��С�����
     * @param heightRatio	 ��С���߶�
     * @throws IOException
     */
    public static void reduceImageByRatio(String srcImagePath, String toImagePath, int widthRatio, int heightRatio) throws IOException
    {
    	FileOutputStream out = null;
    	try{
    		//�����ļ�  
            File file = new File(srcImagePath);  
            // ����Image����  
            BufferedImage src = javax.imageio.ImageIO.read(file);  
            // ��С�߳� 
            BufferedImage tag = new BufferedImage(widthRatio, heightRatio, BufferedImage.TYPE_INT_RGB);  
            // ���� ��С  ���ͼƬ 
            tag.getGraphics().drawImage(src, 0, 0,widthRatio, heightRatio, null);  
            out = new FileOutputStream(toImagePath);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(out != null){
                out.close();  
    		}
    	}
    }
    
	public static void main(String[] args) {
		try {
			reduceImageByRatio("C:/Users/String/Pictures/butterfly.jpg", System.getProperty("user.dir")+"/lib/Upload/QQ_64.png", 64, 64);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
