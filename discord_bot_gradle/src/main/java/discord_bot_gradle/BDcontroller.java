package discord_bot_gradle;

import java.sql.*;

import java.util.ArrayList;

//TODO rediseñar ka base de datos par que guarde el canal y server donde tiene que lanzar las notificaciones 
//TODO configurar para que la conexión a la bd sea parametrizable (pasarle la uri de conexión)

public class BDcontroller {

	private Connection con = null;

	public BDcontroller(String url, String user, String password) {

		// String url="jdbc:mariadb://localhost:3306/bot";
		try {
			con = DriverManager.getConnection(url, user, password);
			// TODO construir la base de datos con el código en vez de en el docker
			// PreparedStament stmt = con.prepareStatement("CREATE IF NOT EXIST ");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("no estas conectado");
		}
	}

	public void insertBday(String id, int num, String message, long server) {
		try {

			PreparedStatement stmt = con.prepareStatement("INSERT INTO users(name,day,message,server) VALUES(?,?,?,?);");
			stmt.setString(1, id);
			stmt.setInt(2, num);
			stmt.setString(3, message);
			stmt.setLong(4, server);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(String user) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM users WHERE name=?;");
			stmt.setString(1, user);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(String user, int newDay) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE users SET day=? WHERE name=?;");
			stmt.setInt(1, newDay);
			stmt.setString(2, user);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Bday> searchBdays(int cTime) {
		PreparedStatement stmt;
		ArrayList<Bday> bdayList = new ArrayList<>();
		try {
			stmt = con.prepareStatement(
					"SELECT name,day,message,server,channel FROM users INNER JOIN servers ON users.server= servers.id WHERE day=? GROUP BY servers.id;");
			stmt.setInt(1, cTime);
			ResultSet resul = stmt.executeQuery();
			try {

				while (resul.next()) {
					System.out.println(resul.toString());
					bdayList.add(new Bday().initializeStar(resul));
				}
				return bdayList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ArrayList<>();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
		// TODO Auto-generated method stub
	}

	public ArrayList<Server> getServers() {
		PreparedStatement stmt;
		ArrayList<Server> servers = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM servers;");
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				servers.add(new Server(result.getLong(1), result.getLong(2)));

			}
			return servers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Server>();
	}

	public void setChanel(Long channel, Long guildId) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("UPDATE servers SET channel=? WHERE id=?;");
			stmt.setLong(1, channel);
			stmt.setLong(2, guildId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setMessage(String name, String message) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("UPDATE users SET message=? WHERE name=?;");
			stmt.setString(1, message);
			stmt.setString(2, name);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Bday> all(long idServer) {
		PreparedStatement stmt;
		try {
			System.out.println("bd caaaaaaaaaaaaaall");
			System.out.println(idServer);
			stmt = con.prepareStatement("SELECT name,day,message,server,channel FROM users INNER JOIN servers ON users.server=servers.id WHERE server=?;");
			stmt.setLong(1, idServer);
			ArrayList<Bday> result = new ArrayList<>();
			ResultSet data = stmt.executeQuery();
			while (data.next()) {
				System.out.println("recorrer query");
				System.out.println(data.getRow()+ "\n");
				result.add(new Bday().initializeStar(data));
			}
			/*for(Bday b : result) {
				System.out.println(b.toString()+ "\n");
			}*/
			System.out.println(result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public void insertServers(long server, long channel) {
		PreparedStatement stmt;
		try {
			stmt= con.prepareStatement("INSERT INTO servers VALUES (?,?);");
			stmt.setLong(1, server);
			stmt.setLong(2, channel);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void insertNtoN(long idServer, String nameUser) {
		 int idUser=this.getId(nameUser);
		 if(idUser>=0) {
			 PreparedStatement stmt;
				try {
					stmt= con.prepareStatement("INSERT INTO is_in VALUES (?,?);");
					stmt.setInt(0,idUser);
					stmt.setLong(1, idServer);
					stmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		 }
				 
		
	}*/

	/*private int getId(String nameUser) {
		PreparedStatement stmt;
		try {
			//debería filtrar por usuario que esté en x servidor???
			stmt= con.prepareStatement("SELECT id FROM users WHERE name=?;");
			stmt.setString(0, nameUser);
			ResultSet result= stmt.executeQuery();
			return result.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}*/
}
