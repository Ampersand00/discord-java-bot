package discord_bot_gradle.Commands;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setChannel extends Command {
	
	
	public setChannel(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(MessageReceivedEvent event) {
		String msg=event.getMessage().getContentRaw();
		String[] m=msg.split(" ");
		String channel = m[3];
		Long longChannel= new Long(channel);
		Long guild = event.getGuild().getIdLong();
		bd.setChanel(longChannel,guild);
		//srv.setChannel(m[2]);
		EmbedMessage eb= new EmbedMessage("Done!"," ");
		event.getChannel().sendMessage(eb.get().build()).queue();
	}

	@Override
	public String help() {
		
		return "-**set channel** [name]: allows you to set in which channel should the bot send the notifications";
	}

}
