package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



public class Mod extends Command{
	
	public void execute(MessageReceivedEvent event) {
		
	}
//en vez de una funcion podria ser un atributo final string
	public String help() {
		return "-chg Modify the birthday of the given person.Enter the new name or the new date.\n"+"\tExample:";
	}

}
