package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setMessage extends setChannel{
	@Override
	public void execute(MessageReceivedEvent event) {
		String msg=event.getMessage().getContentRaw();
		String[] m=msg.split(" ");
		Service.setMessage(m[2]);
		EmbedMessage eb= new EmbedMessage("Done!"," ");
		event.getChannel().sendMessage(eb.get().build()).queue();
		
	}

	@Override
	public String help() {
		
		return "-**set mesagge** [message]: Sets the notification message, you can write whatever you can, but remember that all the names (those whose bdays are) will be at the end\n";
	}

}
