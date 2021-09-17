package discord_bot_gradle;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

//import net.dv8tion.jda.api.AccountType;

public class Main extends ListenerAdapter{


	
	public static void main(String[] args) throws LoginException{
		//JDABuilder builder=new JDABuilder(AccountType.BOT);
		Map<String, String> env = System.getenv();
		String token=env.get("BOT_TOKEN");
		JDABuilder builder= JDABuilder.createDefault(token);
		//builder.setToken(token);
		builder.addEventListeners(new ChatListener(new CommandController(new BDcontroller("jdbc:mariadb://localhost:3306/bot","root",""))));
		JDA jda=builder.build();
		try {
			jda.awaitReady();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
      
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
	    
        //int target=15;
        //int delay=getHoursUntilTarget(target);
        
        //ses.scheduleAtFixedRate(task1, delay, 24, TimeUnit.HOURS); 
        System.out.println("Main.......");
        Notifier srv = new Notifier(jda,new BDcontroller("jdbc:mariadb://localhost:3306/bot","root","")); 
		try {
        srv.run();
        ses.scheduleAtFixedRate(srv, 1, 24, TimeUnit.HOURS);
                //break;
        	   
        	  //ses.scheduleAtFixedRate(new Service(jda), 10, 5, TimeUnit.SECONDS);
            
   

    


	
	//@Override
	/*public void onMessageReceived(MessageReceivedEvent event) {
		System.out.println("recibido");
		if(event.getAuthor().isBot())
			return;
		else {
		if(event.getMessage().getContentRaw().equals("!ping"))
			event.getChannel().sendMessage("ÑO").queue();
		}
	}*/


	
 }catch (Exception e) {
	 e.printStackTrace();
 }
	/*private static  int getHoursUntilTarget(int targetHour) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    System.out.println("hora act: "+hour);
	    return hour < targetHour ? targetHour - hour : targetHour - hour + 24;
	}*/

 }
}
