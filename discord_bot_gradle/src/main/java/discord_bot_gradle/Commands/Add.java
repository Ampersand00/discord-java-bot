package discord_bot_gradle.Commands;



import java.time.LocalDate;

import discord_bot_gradle.BDcontroller;
import discord_bot_gradle.Command;
import discord_bot_gradle.EmbedMessage;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Add extends Command{
	
	public Add(BDcontroller bd) {
		super(bd);
		// TODO Auto-generated constructor stub
	}

	public void execute(MessageReceivedEvent event) {
		
		String msg=event.getMessage().getContentRaw();
		String[] m=msg.split(" ");
		String message="";
		for(int i=3;i<m.length;i++) {
			message+=m[i];
		}
		//String num="";
		/*Calendar c=Calendar.getInstance()*/;
		LocalDate current= LocalDate.now();
		int date=super.convertDateToInt(m[2]);
		//num=m[2];
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		/*try {
			Date date= dateFormat.parse("2011-"+m[2]);
			System.out.println("2011-"+m[2]);
			System.out.println(date.toString());
			c.setTime(date);
			System.out.println(c.get(Calendar.DAY_OF_YEAR));
			
		}catch(ParseException e) {
				 e.printStackTrace();
			 }*/
		
		String uuid=Long.toString(event.getAuthor().getIdLong());
		System.out.println("[Add : execute] "+uuid);
		
		bd.insertBday(uuid, current.getYear()%4==0?date+1:date,message,event.getGuild().getIdLong());
		//TODO apañar esto 
		//bd.insertServers(event.getGuild().getIdLong(),event.getJDA().getGuildsByName("general", true));
		EmbedMessage eb= new EmbedMessage("Your birthday was stored succesfully!","Apparently I'm still working");
		event.getChannel().sendMessage(eb.get().build()).queue();

		
	}

	public String help() {
		return "-**add:** Add a new birthday to the database.Please enter the name and the date following the example format.  \n"+">  Example: `bday add **MM-DD**`";
	}
}
