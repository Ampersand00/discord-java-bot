package discord_bot_gradle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bday {
	private String name;
	private int date;
	
	public Bday() {
		
	}
	public Bday(String name, int day) {
		this.name=name;
		this.date=day;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	public Bday initializeStar(ResultSet data) throws SQLException {
		date=data.getInt(2);
		name= data.getString(1);
		return this;
	}
	
	
	

}
