package discord_bot_gradle;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter {

	private CommandController cController;

	public ChatListener(CommandController commandController) {
		cController = commandController;
	}

	public void onMessageReceived(MessageReceivedEvent event) {

		String msg = event.getMessage().getContentRaw();

		if (msg.startsWith("bday")) {
			String[] commandSplited = msg.split(" ");
			String command = "";
			if (commandSplited.length > 3)
				command += commandSplited[1] + " " + commandSplited[2];
			else
				command += commandSplited[1];

			Command c = cController.searchCommand(command);
			if (c == null) {
				EmbedMessage eb = new EmbedMessage("GOT YOU!",
						"ARE YOU CRAZY??? DO YOU WANNA KILL ME?!? Null Pointer Exception is very dangerous, only the chosen ones can deal with it and it's clearly not you so now fear my punishment... (and please write the command in the right way)");
				event.getChannel().sendMessage(eb.get().build()).queue();
			} else
				c.execute(event);

		}

	}

	public void onGuildJoin(GuildJoinEvent event) {
		cController.getBD().insertServers(event.getGuild().getIdLong(),
				event.getGuild().getDefaultChannel().getIdLong());
		event.getGuild().getDefaultChannel().sendMessage(
				new EmbedMessage("Manoli's bdays has joined the server!", "Your lord has arrived, now start worship me")
						.get().build())
				.queue();
	}

}
