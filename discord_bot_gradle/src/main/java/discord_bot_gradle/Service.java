package discord_bot_gradle;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class Service implements Runnable {

	 private BDcontroller bd=BDcontroller.getInstance();
	 private  static String channel = "general";
	 private static String message ="Hey @everyone your memory sucks, you forgot that it's";
	 
	 public Service() {
		 this.run();
	 }

	 public Service(String channel, String message) {
		 Service.channel=channel;
		 Service.message=message;
	 }
	 public static void setChannel(String channel) {
		 Service.channel=channel;
	 }
	 public static void setMessage(String message) {
		 Service.message=message;
	 }
	
	private ArrayList<String> check(){
		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		int currentMonth= LocalDate.now().getMonthValue();
		int currentDay = Calendar.DAY_OF_MONTH;
		//System.out.println("Month: "+ currentMonth);
		//System.out.println("Day: "+ currentDay);
		String month= Integer.toString(currentMonth);
		String day=Integer.toString(currentDay);
		//System.out.println("--------------------------");
		if(currentMonth<10)	month = "0"+month;
		if(currentDay<10) day= "0"+day;
		//System.out.println("With zeros: day:"+day+" month:"+month);
		
		int cTime= bd.convertDateToInt(month+"-"+day);
		ResultSet resul= bd.search();
		ArrayList<String> names= new ArrayList<>();
		try {
			//falta modificarlo para que diga varios usuario que cumplen el mismo día!!!!
			while(resul.next()) {
				int day_=resul.getInt(2);
				String user= resul.getString(1);
				if(day_==cTime) {
					names.add(user);
				} 
			}
			return names;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return null;
 }

	@Override
	public void run(){
		String token="NzQzNDg4OTAwOTMyMDQyODcz.XzVZ_w.4_hFIV0WyqG6NnN2fLiLFCdCKME";
		JDABuilder builder= JDABuilder.createDefault(token);
		JDA jda;
		try {
			jda = builder.build();		
			jda.awaitReady();
			System.out.println("Entra en el run");
			ArrayList<String> users= this.check();
			System.out.println("entre en el check");
		    if(users!=null){
			List<TextChannel> channels = jda.getTextChannelsByName(Service.channel, true);
			String dates="";
			System.out.println("Que pasa aqui??");
			System.out.println(Service.channel);
			if(!channels.isEmpty()) {
				for(TextChannel ch : channels)
				{
					System.out.println("For channels");
					for(String name: users) {
						System.out.println("pilló algo");
						dates+= "<@"+ name + ">, ";
					}
		           ch.sendMessage(Service.message+dates+" birthday(s)!!!!!!").queue();
				}
				
			  }
			}
			
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
  }
} 

