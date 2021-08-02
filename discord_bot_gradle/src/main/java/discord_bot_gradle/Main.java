package discord_bot_gradle;

	import net.dv8tion.jda.api.JDABuilder;
//import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

//import net.dv8tion.jda.api.AccountType;

public class Main extends ListenerAdapter{

	public static void main(String[] args) throws LoginException{
		//JDABuilder builder=new JDABuilder(AccountType.BOT);
		String token="NzQzNDg4OTAwOTMyMDQyODcz.XzVZ_w.4_hFIV0WyqG6NnN2fLiLFCdCKME";
		JDABuilder builder= JDABuilder.createDefault(token);
		//builder.setToken(token);
		builder.addEventListeners(new ChatListener());
		builder.build();


	        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		int i=0;
	        Runnable task1 = () -> {
			i++;
	            System.out.println("Checkin table...: " + i);
		    BDcontroller bd=BDcontroller.getInstance();
		    String user=bd.check();
		   if(user){
			List<TextChannel> channels = jda.getTextChannelsByName("general", true);
			for(TextChannel ch : channels)
			{
	    //sendMessage(ch, "message");
	   ch.sendMessage("Hey <@everyone> your brains sucks and you forgot that it's <@"+user+">");
			}

		}
        };

        // init Delay = 5, repeat the task every 1 second
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 5, 1, TimeUnit.SECONDS);

        while (true) {
            Thread.sleep(1000);
            if (i== 5) {
                System.out.println("Count is 5, cancel the scheduledFuture!");
                scheduledFuture.cancel(true);
                ses.shutdown();
                break;
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
