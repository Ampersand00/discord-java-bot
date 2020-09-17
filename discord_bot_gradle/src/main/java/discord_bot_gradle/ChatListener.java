package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter{
	
private commandController cController=new commandController();


	


public void onMessageReceived(MessageReceivedEvent event) {
	
	String msg=event.getMessage().getContentRaw();
	System.out.println(msg);

	if( msg.charAt(0)=='!') {
		String[] command=msg.split(" ");
		
		
		/*for(int i=0; i<command.length;i++) {
			System.out.println(command[i]);
		}*/
		
		String f=command[0];
		//f.substring(1);
		//System.out.println("entra en el if del chatList..."/*+ command.toString()*/);
		Command c=cController.searchCommand(f.substring(1));
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
