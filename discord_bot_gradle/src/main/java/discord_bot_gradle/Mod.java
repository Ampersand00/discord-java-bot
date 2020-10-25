package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



public class Mod extends Command{
	
	public void execute(MessageReceivedEvent event) {
		BDcontroller bd=BDcontroller.getInstance();
		String user=event.getAuthor().getId();
		String content = event.getMessage().getContentRaw();
		String newDate=content.substring(9);
		bd.update(user,newDate);
	}
//en vez de una funcion podria ser un atributo final string
	public String help() {
		return "-chg Update your birthday date with the given new date.Remenber the format is MM-DD\n"+"\tExample: bday chg 11-31\n";
	}

}
