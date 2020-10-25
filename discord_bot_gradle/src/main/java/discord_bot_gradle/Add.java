package discord_bot_gradle;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.util.Calendar;
//import java.util.Date;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Add extends Command{
	
	public void execute(MessageReceivedEvent event) {
		
		String msg=event.getMessage().getContentRaw();
		String[] m=msg.split(" ");
		BDcontroller bd=BDcontroller.getInstance();
		//String num="";
		/*Calendar c=Calendar.getInstance()*/;
		LocalDate current= LocalDate.now();
		int date=bd.convertDateToInt(m[2]);
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
		
		String uuid=event.getAuthor().getId();
		bd.insert(uuid, current.getYear()%4==0?date+1:date);
		
	}

	public String help() {
		return "-add Add a new birthday to the database.Please enter the name and the date following the example format  \n"+"\tExample: bday add mm-dd";
	}
}
