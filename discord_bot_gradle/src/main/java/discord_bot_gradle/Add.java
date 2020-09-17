package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Add extends Command{
	
	public void execute(MessageReceivedEvent event) {
		
	}

	public String help() {
		return "-add Add a new birthday to the database.Please enter the name and the date following the example format  \n"+"\tExample: bday add Emilia 11 september.It also works if you specify the moth with numbers instead of words ";
	}
}
