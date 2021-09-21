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

public class Main extends ListenerAdapter {

	public static void main(String[] args) throws LoginException {
		Map<String, String> env = System.getenv();
		String token = env.get("BOT_TOKEN");
		JDABuilder builder = JDABuilder.createDefault(token);
		builder.addEventListeners(new ChatListener(
				new CommandController(new BDcontroller("jdbc:mariadb://localhost:3306/bot", "root", ""))));
		JDA jda = builder.build();
		try {
			jda.awaitReady();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

		Notifier srv = new Notifier(jda, new BDcontroller("jdbc:mariadb://localhost:3306/bot", "root", ""));
		try {
			srv.run();
			ses.scheduleAtFixedRate(srv, 1, 24, TimeUnit.HOURS);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
