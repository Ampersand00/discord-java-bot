package discord_bot_gradle;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class mario_command extends Command{
	
	public void execute(MessageReceivedEvent event) {
		
		event.getChannel().sendMessage("Luigi").queue();
		String msg=event.getMessage().getContentRaw();
		//System.out.println("mario c:"+ msg);
		String[] m=msg.split(" ");
		//System.out.println("mariosis");
		/*for(int i=0;i<m.length;i++)
			System.out.println(m[i]);*/
		BDcontroller bd=BDcontroller.getInstance();
		System.out.println(m[1]+"\n"+Integer.parseInt(m[2]));
		bd.insert(m[1], Integer.parseInt(m[2]));
		
	}
	
	

}
