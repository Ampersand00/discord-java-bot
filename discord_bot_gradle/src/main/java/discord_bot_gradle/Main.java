package discord_bot_gradle;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
//import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

//import net.dv8tion.jda.api.AccountType;

public class Main extends ListenerAdapter{

	private static int i=0;
	public static void main(String[] args) throws LoginException{
		//JDABuilder builder=new JDABuilder(AccountType.BOT);
		String token="NzQzNDg4OTAwOTMyMDQyODcz.XzVZ_w.4_hFIV0WyqG6NnN2fLiLFCdCKME";
		JDABuilder builder= JDABuilder.createDefault(token);
		//builder.setToken(token);
		builder.addEventListeners(new ChatListener());
		JDA jda=builder.build();
		try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
	        
	        Runnable task1 = () -> {
	        
			i=1;
	           // System.out.println("Checkin table...: " + i);
		    BDcontroller bd=BDcontroller.getInstance();
		    String user=bd.check();
		    if(user!=null){
			List<TextChannel> channels = jda.getTextChannelsByName("pruebas-bot", true);
			for(TextChannel ch : channels)
			{
				//sendMessage(ch, "Hey <@everyone> your brains sucks and you forgot that it's <@\"+user+\">");
			   System.out.println("ha pillado el cumple");
	           ch.sendMessage("Hey @everyone your brains sucks and you forgot that it's <@"+user+">").queue();
			}

		}
        };
        //Calendar calendar = Calendar.getInstance();
        //int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int target=16;
        int delay=getHoursUntilTarget(target);
        System.out.println(delay);

        // init Delay = 5, repeat the task every 1 second
        //ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 5, 1, TimeUnit.SECONDS);
        //ses.scheduleAtFixedRate(task1, delay, 24, TimeUnit.HOURS);
        while (true) {
            try {
            	
            		Thread.sleep(1000);
           
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           if(i==1) {
               // scheduledFuture.cancel(true);
               // ses.shutdown();
        	   ses.scheduleAtFixedRate(task1, delay, 24, TimeUnit.HOURS);
                //break;
            }
        }

    


	
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


	
 }
	private static  int getHoursUntilTarget(int targetHour) {
	    Calendar calendar = Calendar.getInstance();
	    int hour = calendar.get(Calendar.HOUR_OF_DAY)+1;
	    System.out.println("hora act: "+hour);
	    return hour < targetHour ? targetHour - hour : targetHour - hour + 24;
	}

}
