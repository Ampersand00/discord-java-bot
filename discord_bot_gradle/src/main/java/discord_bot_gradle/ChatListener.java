package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter{
	
private CommandController cController=CommandController.getInstance();


	


public void onMessageReceived(MessageReceivedEvent event) {
	
	String msg=event.getMessage().getContentRaw();
	System.out.println(msg);

	if( msg.startsWith("bday")) {
		String[] commandSplited=msg.split(" ");
		String command="";
		if(commandSplited.length>3)
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
			EmbedMessage eb= new EmbedMessage("GOT YOU!","Foolish! You tried to blow me up, now fear my punishment...");
			event.getChannel().sendMessage(eb.get().build()).queue();
		}else 
		//System.out.println("comando a buscar "+f.substring(1));
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
}
