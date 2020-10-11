package discord_bot_gradle;
import java.sql.*;


public class BDcontroller {

	 private Connection con=null;
	 private static BDcontroller instancia=null;
	
	private BDcontroller() {
		
		String url="jdbc:mariadb://localhost:3306/bot";
		try {
			con = DriverManager.getConnection(url,"root","root");
			
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
	
	public void insert(String id, int num) {
		try {
			/*System.out.println("BDController");
			System.out.println(id+"\n"+num);
			System.out.println("aPS");*/
			PreparedStatement stmt=con.prepareStatement("INSERT INTO bday(name,day) VALUES(?,?);");
			//System.out.println("dPS");
			stmt.setString(1, id);
			stmt.setInt(2, num);
			//System.out.println("sigue siendo antes");
			stmt.executeUpdate();
			//System.out.println("noo falla el execute");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void delete(String user) {
		try {
			PreparedStatement stmt=con.prepareStatement("DELETE FROM bday WHERE name=?;");
			stmt.setString(1,user);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(String user,String newDate /*int(?) newdate*/) {
		try {
			PreparedStatement stmt=con.prepareStatement("UPDATE bday SET day=? WHERE name=?;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
