package discord_bot_gradle.Commands;

import discord_bot_gradle.Controllers.BDcontroller;
import discord_bot_gradle.Models.Command;
import discord_bot_gradle.Models.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class seeMessage extends Command {

	public seeMessage(BDcontroller bd) {
		super(bd);
	}

	@Override
	public void execute(MessageReceivedEvent event) {
		String[] m= splitMessage(event);
		long name= new Long(m[2].substring(3, m[2].length()-1)).longValue();
		System.out.println(name);
		String s= bd.selectMessage(name,event.getGuild().getIdLong());
		if(s==null|| s.isEmpty()|| s.isBlank()) {
			s+= "<@"+name+">"+ "has no message, does anybody care about them?";
		}
		EmbedMessage eb= new EmbedMessage("-_-",s);
		event.getChannel().sendMessage(eb.get().build()).queue();
		
	}

	@Override
	public String help() {
		return "-**show** [name]: allows you to see the message set for the name you entered\n" + ">  Example: `bday show @Jimmy`\n";
	}

}
