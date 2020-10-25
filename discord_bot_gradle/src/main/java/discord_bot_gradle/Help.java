package discord_bot_gradle;

import java.util.HashMap;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command{

	
	@Override
	public void execute(MessageReceivedEvent event) {
		EmbedBuilder eb=new EmbedBuilder();
		String output="";
		CommandController c=CommandController.getInstance();
		HashMap<String,Command> h= c.getCommands();
		
		for(Command com : h.values()) {
				output+=com.help();	
		}
		eb.setDescription(output);
		event.getChannel().sendMessage(eb.build()).queue();
	}

	@Override
	public String help() {
		return "\n-help Guess the porpouse of this command.\n ";
	}

}
