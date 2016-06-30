package xju.software.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QQSQL {
	
	private static String database_path = "C:/Users/String/Desktop/TermQQ/lib/QQ.db";
	
	public QQSQL(){
		
	}
	
	public void SQLConnection_Register(String username, String nickname, String password){
		String sql = "insert into QQUsers values";
		try {
			Class.forName("org.sqlite.JDBC");
			 try {
				Connection conn = DriverManager.getConnection("jdbc:sqlite:" + database_path);
				Statement st = conn.createStatement();
				st.executeUpdate(sql + "( '" + username + "', '" + nickname + "', '" + password + "')");
				//关闭
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String SQLConnection_Login(String username){
		String sql = "select * from QQUsers where username=";
		String password = "";
		try {
			Class.forName("org.sqlite.JDBC");
			 try {
				Connection conn = DriverManager.getConnection("jdbc:sqlite:" + database_path);
				Statement st = conn.createStatement();
				//SQL语句一定要绝对匹配！
				ResultSet rs = st.executeQuery(sql+"'"+username+"';");
				while(rs.next()){
					password = rs.getString("password");
				}
				//关闭
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public static void main(String[] args) {
//		System.out.println(new QQSQL().SQLConnection_Login("String"));
	}
}
