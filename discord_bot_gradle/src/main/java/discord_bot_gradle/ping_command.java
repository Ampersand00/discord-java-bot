package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ping_command extends Command {

	public void execute(MessageReceivedEvent event) {
		event.getChannel().sendMessage("funciona2").queue();
		
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}
}
