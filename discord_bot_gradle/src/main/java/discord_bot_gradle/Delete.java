package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Delete extends Command {

	public void execute(MessageReceivedEvent event) {
		
		BDcontroller bd=BDcontroller.getInstance();
		String user=event.getAuthor().getId();
		bd.delete(user);
		
	}
	
	public String help() {
		return "-rm	removes your birthday. Just type \"bday rm me\" ";
	}
}
