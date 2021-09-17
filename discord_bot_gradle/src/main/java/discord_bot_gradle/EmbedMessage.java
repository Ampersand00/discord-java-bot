package discord_bot_gradle;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;

public class EmbedMessage {
	private EmbedBuilder eb=new EmbedBuilder();
	//private String title;
	//private String description;
	
	public EmbedMessage(String tittle, String description) {
		eb.setTitle(tittle);
		eb.setDescription(description);
		eb.setColor(Color.red);
	}
	
	public EmbedBuilder get() {
		return eb;
	}

}
