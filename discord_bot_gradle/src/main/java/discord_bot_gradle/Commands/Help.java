package discord_bot_gradle.Commands;

import java.awt.Color;
import java.util.HashMap;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.CommandController;
import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command {

	public Help(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(MessageReceivedEvent event) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(new Color(160, 32, 240));
		String output = "To run a command type `bday [command] [parameters]`\n";
		CommandController c = new CommandController(bd);
		HashMap<String, Command> h = c.getCommands();

		for (Command com : h.values()) {
			output += com.help();
		}
		
		eb.setDescription(output);
		eb.setFooter("If you don't specify the channel it will be set to the default channel.");
		event.getChannel().sendMessage(eb.build()).queue();
	}

	@Override
	public String help() {
		return "\n-**help**: Guess what.\n ";
	}

}
