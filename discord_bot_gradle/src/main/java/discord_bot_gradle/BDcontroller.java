package discord_bot_gradle;

import java.sql.*;

import java.util.ArrayList;

public class BDcontroller {

	private Connection con = null;

	public BDcontroller(String url, String user, String password) {

		try {
			con = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("You aren't connected to dedatabase");
		}
	}

	public void insertBday(long id, int num, String message, long server) {
		try {

			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO users(name,day,message,server) VALUES(?,?,?,?);");
			stmt.setLong(1, id);
			stmt.setInt(2, num);
			stmt.setString(3, message);
			stmt.setLong(4, server);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(long user,long server) {
			if(areNmembersOfGuild(server)) {
				remove(user,server);
			}
			else {
				remove(user,server);
				updateServers(server);
			}
	}

	private void updateServers(long server) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("DELETE FROM servers WHERE id=?;");
			stmt.setLong(1, server);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	private void remove(long user, long server) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("DELETE FROM users WHERE name=? AND server=?;");
			stmt.setLong(1, user);
			stmt.setLong(2, server);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private boolean areNmembersOfGuild(long server) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT COUNT(name) FROM users WHERE server=?;");
			stmt.setLong(1, server);
			ResultSet resul=stmt.executeQuery();
			while(resul.next()) {
				if(resul.getInt(1)>1) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void update(long user, int newDay) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE users SET day=? WHERE name=?;");
			stmt.setInt(1, newDay);
			stmt.setLong(2, user);
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

	public void setMessage(long name, String message,long server) {
		PreparedStatement stmt;
		
		try {
			System.out.println("[BDcontroler: setMessage] "+message);
			System.out.println("[BDcontroler: setMessage] server "+ server);
			System.out.println("[BDcontroler: setMessage] name  "+ name);
			stmt = con.prepareStatement("UPDATE users SET message=? WHERE server=? AND name=?;");
			stmt.setString(1, message);
			stmt.setLong(2, server);
			stmt.setLong(3, name);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Bday> all(long idServer) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(
					"SELECT name,day,message,server,channel FROM users INNER JOIN servers ON users.server=servers.id WHERE server=?;");
			stmt.setLong(1, idServer);
			ArrayList<Bday> result = new ArrayList<>();
			ResultSet data = stmt.executeQuery();
			while (data.next()) {
				result.add(new Bday().initializeStar(data));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public void insertServers(long server, long channel) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("INSERT INTO servers VALUES (?,?);");
			stmt.setLong(1, server);
			stmt.setLong(2, channel);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String selectMessage(long name,long server) {
		System.out.println("[BDcontroller : selectMessage] llamada recibida ");
		System.out.println(name);
		String result = "";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT message FROM users WHERE name=? AND server=?");
			stmt.setLong(1, name);
			stmt.setLong(2, server);
			ResultSet resultQuery = stmt.executeQuery();
			System.out.println("queryjhañk");
			System.out.println("ResultDO consulta antes"+result);
			while(resultQuery.next()) {
				result += resultQuery.getString(1);
			}
			System.out.println("ResultDO consulta"+result);
			return result;
		} catch (SQLException e) {

		}
		return "";
	}

	public boolean hasBday(long name,long server) {
		boolean hasBday=false;
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM users WHERE name=? AND server=?");
			stmt.setLong(1, name);
			stmt.setLong(2, server);
			ResultSet resultQuery = stmt.executeQuery();
			
			while(resultQuery.next()) {
				if(resultQuery.getInt(1)==1) {
					hasBday=true;
				}
			}
			
			return hasBday;
		} catch (SQLException e) {

		}
		return hasBday;
	}

	/*
	 * public void insertNtoN(long idServer, String nameUser) { int
	 * idUser=this.getId(nameUser); if(idUser>=0) { PreparedStatement stmt; try {
	 * stmt= con.prepareStatement("INSERT INTO is_in VALUES (?,?);");
	 * stmt.setInt(0,idUser); stmt.setLong(1, idServer); stmt.executeUpdate(); }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	/*
	 * private int getId(String nameUser) { PreparedStatement stmt; try { //debería
	 * filtrar por usuario que esté en x servidor??? stmt=
	 * con.prepareStatement("SELECT id FROM users WHERE name=?;"); stmt.setString(0,
	 * nameUser); ResultSet result= stmt.executeQuery(); return result.getInt(0); }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return -1; }
	 */
}
