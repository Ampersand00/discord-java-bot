package discord_bot_gradle.Commands;

import java.time.LocalDate;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Add extends Command {

	public Add(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	public void execute(MessageReceivedEvent event) {

		String[] m = splitMessage(event);
		String message = "";
		for (int i = 3; i < m.length; i++) {
			message += m[i];
		}

		LocalDate current = LocalDate.now();
		int date = super.convertDateToInt(m[2]);

		long uuid = event.getAuthor().getIdLong();
		System.out.println("[Add : execute] " + uuid);

		bd.insertBday(uuid, current.getYear() % 4 == 0 ? date + 1 : date, message, event.getGuild().getIdLong());

		EmbedMessage eb = new EmbedMessage("Your birthday was stored succesfully!", "Apparently I'm still working");
		event.getChannel().sendMessage(eb.get().build()).queue();

	}

	public String help() {
		return "-**add:** Add a new birthday to the database. Please enter the name and the date following the example format.  \n"
				+ ">  Example: `bday add **MM-DD**`";
	}
}
