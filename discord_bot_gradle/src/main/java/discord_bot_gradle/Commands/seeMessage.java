package discord_bot_gradle.Commands;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class seeMessage extends Command {

	public seeMessage(BDcontroller bd) {
		super(bd);
	}

	@Override
	public void execute(MessageReceivedEvent event) {
		String[] m= splitMessage(event);
		long name= new Long(m[3].substring(3, m[3].length()-1)).longValue();
		System.out.println(name);
		String s= bd.selectMessage(name);
		System.out.println("Consuklta: "+ s);
		EmbedMessage eb= new EmbedMessage("-_-",s);
		event.getChannel().sendMessage(eb.get().build()).queue();
		
	}

	@Override
	public String help() {
		return "-**ls message** [name]: allows you to see the mesage set for the name you entered\n" + ">  Example: `bday ls message @Jimmy`\n";
	}

}
