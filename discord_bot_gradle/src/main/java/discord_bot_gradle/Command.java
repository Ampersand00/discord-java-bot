package discord_bot_gradle;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



//import java.util.*;

public  abstract class Command {
	
	
	public abstract void execute(MessageReceivedEvent event);
	public abstract String help();
	
	
	

}
