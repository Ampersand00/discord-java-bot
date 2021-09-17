package discord_bot_gradle;


import discord_bot_gradle.Commands.setMessage;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter{
	
private CommandController cController;

public ChatListener(CommandController commandController) {
	cController=commandController;
}

	


public void onMessageReceived(MessageReceivedEvent event) {
	
	String msg=event.getMessage().getContentRaw();
	System.out.println(msg);

	if( msg.startsWith("bday")) {
		event.getChannel().sendMessage(new EmbedMessage("Test", "He @everyone KAJGFAHJGFALFGHLJGHLADJFG").get().build()).queue();
		String[] commandSplited=msg.split(" ");
		String command="";
		if (commandSplited.length>3)
			command+= commandSplited[1]+ " " +commandSplited[2];
		else command+=commandSplited[1];
		/*for(int i=0; i<command.length;i++) {
			System.out.println(command[i]);
		}*/
		
		//String f=command[0];
		//f.substring(1);
		//System.out.println("entra en el if del chatList..."/*+ command.toString()*/);
		Command c=cController.searchCommand(command);
		if(c==null) {
			EmbedMessage eb= new EmbedMessage("GOT YOU!","ARE YOU CRAZY??? DO YOU WANNA KILL ME?!? Null Pointer Exception is very dangerous, only the chosen ones can deal with it and it's clearly not you so now fear my punishment... (and please write the command in the right way)");
			event.getChannel().sendMessage(eb.get().build()).queue();
		}else 
		//System.out.println("comando a buscar "+f.substring(1));
			if(c instanceof setMessage && commandSplited.length==5) {
				String m="";
				for(int i=4;i<commandSplited.length;i++) {
					m+= commandSplited[i]+" ";
				}
				c.bd.setMessage(commandSplited[3],m);
			}
			c.execute(event);
		//String command=msg[1]; //podria ser un substract la primera posicion si hay varios comandos
		/*switch(command) {
		case "ping": {
			event.getChannel().sendMessage("ÑO").queue();
			break;
		}
		case "mario":{
			event.getChannel().sendMessage("Luigi").queue();
			break;
		}
		}*/
		
		//prueba con hashmap
		
	/*	for(String x : hsmp.keySet()) {
			if(x.equals(command)) {
				hsmp.get(command);
			}
				
			System.out.println(hsmp);		
		}*/
		
	}
	
}
public void onGuildJoin(GuildJoinEvent event) {
	cController.getBD().insertServers(event.getGuild().getIdLong(),event.getGuild().getDefaultChannel().getIdLong());
	event.getGuild().getDefaultChannel().sendMessage(new EmbedMessage("Manoli's bdays has joined the server!","Your lord has arrived, now start worship me").get().build()).queue();
}


}
