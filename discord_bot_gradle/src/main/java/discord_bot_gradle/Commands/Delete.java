package discord_bot_gradle.Commands;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Delete extends Command {

	public Delete(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	public void execute(MessageReceivedEvent event) {
		
		
		long user=event.getAuthor().getIdLong();
		bd.delete(user,event.getGuild().getIdLong());
		EmbedMessage eb= new EmbedMessage("Now you dont exist anymore","Such a shame, I loved sharing all of your data with facebook ;_;");
		event.getChannel().sendMessage(eb.get().build()).queue();
				
	}
	
	public String help() {
		return "-**rm:**	Removes your birthday. Just type `bday rm`\n ";
	}
}
