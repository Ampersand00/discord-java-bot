package discord_bot_gradle.Commands;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



public class Mod extends Command{
	
	public Mod(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}
	public void execute(MessageReceivedEvent event) {
		
		long user=event.getAuthor().getIdLong();
		String content = event.getMessage().getContentRaw();
		String newDate=content.substring(9);
		int nday= Command.convertDateToInt(newDate);
		bd.update(user,nday);
		EmbedMessage eb= new EmbedMessage("Hurray!!","The police can't catch you now");
		event.getChannel().sendMessage(eb.get().build()).queue();
		
	}

	public String help() {
		return "-**chg** Update your birthday date with the given new date.Remenber the format is *MM-DD*\n"+">  Example: `bday chg 11-31`\n";
	}

}
