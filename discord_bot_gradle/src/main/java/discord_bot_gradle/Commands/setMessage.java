package discord_bot_gradle.Commands;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setMessage extends Command {
	public setMessage(BDcontroller bd) {
		super(bd);
	}

	public void execute(MessageReceivedEvent event) {
		String[] m = splitMessage(event);
		long name;
		String userMessage = "";
		if (m.length >= 5 && m[3].matches("[0-9]* ")) { // message for someone else
			name = new Long(m[3]).longValue();
			for (int i = 4; i < m.length; i++) {
				userMessage += m[i] + " ";
			}
		} else { // message for the author
			name = event.getAuthor().getIdLong();
			for (int j = 3; j < m.length; j++) {
				userMessage += m[j] + " ";
			}
		}
		bd.setMessage(name, userMessage);
		EmbedMessage eb = new EmbedMessage("Done!", " ");
		event.getChannel().sendMessage(eb.get().build()).queue();

	}

	@Override
	public String help() {

		return "-**set mesagge** [message]: Sets the custom message you wanna tell someone in their birthday. You can set it for you or someonelse, if it's for you just type like it's said at the begining but if you want to write it for other person type `bday set message [person] [message]`.\nIf is not specified this field will be empty when printed\n";
	}

}
