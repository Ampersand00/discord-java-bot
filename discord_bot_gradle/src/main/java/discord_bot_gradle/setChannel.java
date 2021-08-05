package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setChannel extends Command {
	//protected Service srv = new Service("general");
	
	@Override
	public void execute(MessageReceivedEvent event) {
		String msg=event.getMessage().getContentRaw();
		String[] m=msg.split(" ");
		Service.setChannel(m[2]);
		EmbedMessage eb= new EmbedMessage("Done!"," ");
		event.getChannel().sendMessage(eb.get().build()).queue();
	}

	@Override
	public String help() {
		
		return "-**set channel** [name], allows you to set in which channel should the bot send the notifications";
	}

}
