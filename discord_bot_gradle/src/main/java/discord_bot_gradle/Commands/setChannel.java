package discord_bot_gradle.Commands;

import discord_bot_gradle.Controllers.BDcontroller;
import discord_bot_gradle.Models.Command;
import discord_bot_gradle.Models.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setChannel extends Command {
	
	
	public setChannel(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(MessageReceivedEvent event) {
		String[] m= splitMessage(event);
		String channel = m[3];
		Long longChannel=event.getGuild().getTextChannelsByName(channel, true).get(0).getIdLong();
		Long guild = event.getGuild().getIdLong();
		bd.setChanel(longChannel,guild);
		EmbedMessage eb= new EmbedMessage("Done!"," ");
		event.getChannel().sendMessage(eb.get().build()).queue();
	}

	@Override
	public String help() {
		
		return "-**set channel** [name]: allows you to set in which channel should the bot send the notifications\n" + ">  Example: `bday set channel general`\n";
	}

}
