package discord_bot_gradle.Commands;

import java.util.ArrayList;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Bday;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SelectAll extends Command {

	public SelectAll(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(MessageReceivedEvent event) {
		System.out.println("[SelectAll::execute] Llamada recibida");
		ArrayList<Bday> bdays = bd.all(event.getGuild().getIdLong());
		String data = "";
		for (Bday x : bdays) {
			data += "<@" + x.getName() + "> :" + super.convertInttoDate(x.getDate());
		}

		event.getChannel().sendMessage(new EmbedMessage("List", data).get().build()).queue();

	}

	@Override
	public String help() {
		return "-**all**: shows all the birthdays of this server.The day format is the same as when you add a birthday\n";
	}

}
