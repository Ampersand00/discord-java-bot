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
		System.out.println(Integer.parseInt(m[1]));
		long id=event.getAuthor().getIdLong();
		bd.insert(id, Integer.parseInt(m[1]));
	
		event.getChannel().sendMessage("Hey "+event.getAuthor().getAsMention() +"ist your birthday").queue();
		
		
		
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
