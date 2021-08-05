package discord_bot_gradle;

import java.awt.Color;
import java.util.HashMap;

import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command{

	
	@Override
	public void execute(MessageReceivedEvent event) {
		EmbedBuilder eb=new EmbedBuilder();
		eb.setColor(Color.red);
		String output="";
		CommandController c=CommandController.getInstance();
		HashMap<String,Command> h= c.getCommands();
		
		for(Command com : h.values()) {
				output+=com.help();	
		}
		output+="\nIf you don't specify the channel it will be set to *general* by default.";
		eb.addField("Commands:",output,false);
		event.getChannel().sendMessage(eb.build()).queue();
	}

	@Override
	public String help() {
		return "\n-**help**: Guess what.\n ";
	}

}
