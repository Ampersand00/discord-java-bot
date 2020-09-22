package discord_bot_gradle;
import java.sql.*;

public class BDcontroller {

	 private Connection con=null;
	 private static BDcontroller instancia=null;
	
	private BDcontroller() {
		
		String url="jdbc:sqlite:/home/ubuntu/tablasBot.db";
		try {
			con = DriverManager.getConnection(url);
			
		}catch(SQLException e) {
			 System.out.println(e.getMessage());
			 System.out.println("no estas conectado");
		}
	}
	public static BDcontroller getInstance() {
		if(instancia==null) {
			instancia=new BDcontroller();
		}
		return instancia;
		
	}
	
	public void insert(long id, int num) {
		try {
			/*System.out.println("BDController");
			System.out.println(id+"\n"+num);
			System.out.println("aPS");*/
			PreparedStatement stmt=con.prepareStatement("INSERT INTO enero(uuid,day) VALUES(?,?);");
			//System.out.println("dPS");
			stmt.setLong(1, id);
			stmt.setInt(2, num);
			//System.out.println("sigue siendo antes");
			stmt.executeUpdate();
			//System.out.println("noo falla el execute");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
