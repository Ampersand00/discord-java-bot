package discord_bot_gradle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public class Service implements Runnable {

	 private BDcontroller bd;
	 private  static final String channelDefault = "general";
	 private  static final String messageDefault ="Hey @everyone your memory sucks, you forgot that it's";
	 private String channel;
	 private String message;
	 private JDA jda;
	 
	 public Service(JDA jda, BDcontroller bd) throws LoginException {
		 this(jda,bd,channelDefault,messageDefault);
		 
	 }

	 public Service(JDA jda,BDcontroller bd,String channel, String message) {
		 this.jda=jda;
		 this.bd=bd;
		 this.channel=channel;
		 this.message=message;
		
	 }
	 public  void setChannel(String channel) {
		 this.channel=channel;
	 }
	 public  void setMessage(String message) {
		 this.message=message;
	 }
	 
	 public String getMessage() {
		 return message;
	 }
	 public String getChannel() {
		 return channel;
	 }

	private int currentTime() {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		int currentMonth= LocalDate.now().getMonthValue();
		int currentDay = LocalDate.now().getDayOfMonth();
		//System.out.println("Month: "+ currentMonth);
		//System.out.println("Day: "+ currentDay);
		String month= Integer.toString(currentMonth);
		String day=Integer.toString(currentDay);
		//System.out.println("--------------------------");
		if(currentMonth<10)	month = "0"+month;
		if(currentDay<10) day= "0"+day;
		//System.out.println("With zeros: day:"+day+" month:"+month);
		
		return bd.convertDateToInt(month+"-"+day);
	}

	@Override
	public void run(){
		try {
			jda.awaitReady();
			final int cTime=currentTime();
			System.out.println("Entra en el run");
			ArrayList<Bday> users=  bd.searchBdays(cTime);
			System.out.println("entre en el check");
		    if(!users.isEmpty()){
				List<TextChannel> channels = jda.getTextChannelsByName(this.channel, true);
				String dates="";
				System.out.println("Que pasa aqui??");
				System.out.println(this.channel);
				if(!channels.isEmpty()) {
						TextChannel ch = channels.get(0);
						System.out.println("For channels");
						for(Bday bday: users) {
							System.out.println("pilló algo");
							dates+= "<@"+ bday.getName() + ">, ";
						}
			           ch.sendMessage(this.message+dates+" birthday(s)!!!!!!").queue();
					
					
				 }
			}
			
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
  }
} 

