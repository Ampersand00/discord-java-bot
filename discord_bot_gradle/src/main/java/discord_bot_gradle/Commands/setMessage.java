package discord_bot_gradle.Commands;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class setMessage extends Command{
	public setMessage(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	
	public void execute(MessageReceivedEvent event) {
		String msg=event.getMessage().getContentRaw();
		String[] m=msg.split(" ");
		String name= m[3].substring(1);
		System.out.println(name);
		String message="";
		
		for(int i=4;i<m.length;i++) {
			message+=m[i];
		}
		bd.setMessage(name,message);
		EmbedMessage eb= new EmbedMessage("Done!"," ");
		event.getChannel().sendMessage(eb.get().build()).queue();
		
	}

	@Override
	public String help() {
		
		return "-**set mesagge** [message]: Sets the custom message you wanna tell someone in their birthday. You can set it for you or someonelse, if it's your just type like it's said at the begining but if you want to write it for other person type `bday set message [person] [message]`.\nIf is not specified this field will be empty when printed\n";
	}

}
