package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Delete extends Command {

	public void execute(MessageReceivedEvent event) {
		
		BDcontroller bd=BDcontroller.getInstance();
		String user=event.getAuthor().getId();
		bd.delete(user);
		EmbedMessage eb= new EmbedMessage("Now you dont exist anymore","Such a shame, I loved sharing all of your data with facebook ;_;");
		event.getChannel().sendMessage(eb.get().build()).queue();
				
	}
	
	public String help() {
		return "-**rm:**	Removes your birthday. Just type `bday rm me`\n ";
	}
}
