package discord_bot_gradle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



//import java.util.*;
//TODO apañar setChannel y setMessages (teniendo en cuenta que en la base de datos se guardan esos valores)
//TODO hacer comando para mostrar todos los cumples de un server concreto 
//TODO apañar los comandos que llaman a la base de datos

public  abstract class Command {
	
	protected BDcontroller bd;
	
	public Command(BDcontroller bd) {
		this.bd=bd;
	}
	
	public abstract void execute(MessageReceivedEvent event);
	public abstract String help();
	
	public static int convertDateToInt(String dateString) {
		Calendar c=Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int year=Year.now().getValue();
			Date date= dateFormat.parse(year+"-"+dateString);
			c.setTime(date);
			
			
		}catch(ParseException e) {
				 e.printStackTrace();
			 }
		return c.get(Calendar.DAY_OF_YEAR);
	}
	
	public static String convertInttoDate(int bdFormatDay) {
		Calendar c=Calendar.getInstance();
		//c.set(Calendar.YEAR, Calendar.YEAR);
		//System.out.println(bdFormatDay);
		c.set(Calendar.DAY_OF_YEAR, bdFormatDay);
		System.out.println(c.getTime());
		//System.out.println("day: "+c.getTime().getDate());
		//System.out.println("month: "+ (c.getTime().getMonth()+1));
		String result =Integer.toString(c.getTime().getDate())+"-" +Integer.toString(c.getTime().getMonth()+1);
		System.out.println("Int to date: "+result);
		return result;
	}
	
	

}
