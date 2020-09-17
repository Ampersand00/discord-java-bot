package discord_bot_gradle;

import net.dv8tion.jda.api.JDABuilder;
//import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

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
