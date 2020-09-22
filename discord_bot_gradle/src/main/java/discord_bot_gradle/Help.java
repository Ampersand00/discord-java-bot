package discord_bot_gradle;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command{
	
	public void execute(MessageReceivedEvent event) {
		
		
		
		//MessageChannel channel=event.getChannel();//se puede borrar
		
	}
	
	public String help() {
		String content="Try to help you out";
		
		return content;
	}

}
