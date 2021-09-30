package discord_bot_gradle.Models;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;

public class EmbedMessage {
	private EmbedBuilder eb=new EmbedBuilder();
	
	public EmbedMessage(String tittle, String description) {
		
		eb.setTitle(tittle);
		eb.setDescription(description);
		eb.setColor(new Color(160,32,240));
	}
	
	public EmbedBuilder get() {
		return eb;
	}

}
