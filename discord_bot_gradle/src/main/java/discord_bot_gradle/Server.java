package discord_bot_gradle;

public class Server {
	private long id;
	private long channel;
	
	public Server(long server, long channel) {
		id=server;
		this.channel=channel;
	}
	public long getId() {
		return id;
	}
	public long getChannel() {
		return channel;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setChannel(long channel) {
		this.channel = channel;
	}
}
