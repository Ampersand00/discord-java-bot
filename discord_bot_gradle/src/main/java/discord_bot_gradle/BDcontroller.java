package discord_bot_gradle;
import java.sql.*;

public class BDcontroller {

	 private Connection con=null;
	 private static BDcontroller instancia=null;
	
	private BDcontroller() {
		
		String url="jdbc:sqlite:/home/ubuntu/test.db";
		try {
			con = DriverManager.getConnection(url);
			
		}catch(SQLException e) {
			 System.out.println(e.getMessage());
		}
	}
	public static BDcontroller getInstance() {
		if(instancia==null) {
			instancia=new BDcontroller();
		}
		return instancia;
		
	}
	
	public void insert(String name, int num) {
		try {
			
			Integer n=new Integer(num);
			System.out.println(n);
			PreparedStatement stmt=con.prepareStatement("INSERT INTO t1(name,num) VALUES(?,?);");
			stmt.setString(1, name);
			stmt.setInt(2, num);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
