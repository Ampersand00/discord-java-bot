package discord_bot_gradle.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bday {
	private long name;
	private int date;
	private String message;
	//private Server server;
	private long server;
	private long channel;
	public Bday() {
		
	}
	public Bday(long name, int day, String message, long server, long channel) {
		this.name=name;
		this.date=day;
		this.message=message;
		this.server=server;
		this.channel=channel;
	}
	public long getName() {
		return name;
	}
	public String getMessage() {
		return message;
	}
	public int getDate() {
		return date;
	}
	public long getServer() { 
		return server;
	}
	public long getChannel() {
		return channel;
	}

	public void setName(long name) {
		this.name = name;
	}
	
	public void setDate(int date) {
		this.date = date;
	}
	public void setMessage(String message) {
		this.message=message;
	}
	public void setServer(long server) {
		this.server=server;
	}
	public void setChannel(long channel) {
		this.channel=channel;
	}
	public Bday initializeStar(ResultSet data) throws SQLException {
		name= data.getLong(1);
		date=data.getInt(2);
		message= data.getString(3);
		server=data.getLong(4);
		channel=data.getLong(5);
		return this;
	}
	
	
	

}
