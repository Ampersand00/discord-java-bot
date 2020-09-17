package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Delete extends Command {

	public void execute(MessageReceivedEvent event) {
		
	}
	
	public String help() {
		return "-rm	removes the birthday of the person you specify.\n "+"\tExample: bday rm mario sanchez\n ";
	}
}
