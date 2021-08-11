package discord_bot_gradle;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



//import java.util.*;
//TODO apañar setChannel y setMessages (teniendo en cuenta que en la base de datos se guardan esos valores)
//TODO hacer comando para mostrar todos los cumples de un server concreto 
//TODO apañar los comandos que llaman a la base de datos

public  abstract class Command {
	
	
	public abstract void execute(MessageReceivedEvent event);
	public abstract String help();
	
	
	

}
