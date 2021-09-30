package discord_bot_gradle.Commands;

import discord_bot_gradle.Controllers.BDcontroller;
import discord_bot_gradle.Models.Command;
import discord_bot_gradle.Models.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setMessage extends Command {
	public setMessage(BDcontroller bd) {
		super(bd);
	}

	public void execute(MessageReceivedEvent event) {
		String[] m = splitMessage(event);
		String name;
		long nameLong;
		String userMessage = "";
		//if (m.length >= 5 && m[3].matches("[0-9]* ")) { 
			name=m[3].substring(3, m[3].length()-1);
			System.out.println(name);
			nameLong = new Long(name).longValue();
			System.out.println(nameLong);
			
			for (int i = 4; i < m.length; i++) {
				userMessage += m[i] + " ";
			}
		//} 
			EmbedMessage eb;
		if(bd.hasBday(nameLong,event.getGuild().getIdLong())) {
			bd.setMessage(nameLong, userMessage,event.getGuild().getIdLong());
			eb = new EmbedMessage("Done!", " ");
		}else {
			eb=new EmbedMessage("Sorry", "You can not set a message if the person is not in the database, so please death threat your friends to save their birthday");
		}
		
		
		event.getChannel().sendMessage(eb.get().build()).queue();

	}

	@Override
	public String help() {

		return "-**set mesagge** [person][message]: Sets the custom message you wanna tell someone in their birthday. You can set it for you or someonelse.\nIf is not specified this field will be empty when printed\n";
	}

}
