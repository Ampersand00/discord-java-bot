package discord_bot_gradle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
//import java.util.List;
import java.util.TimeZone;

import javax.security.auth.login.LoginException;

import discord_bot_gradle.Controllers.BDcontroller;
import discord_bot_gradle.Models.Bday;
import discord_bot_gradle.Models.Command;
import discord_bot_gradle.Models.EmbedMessage;
import discord_bot_gradle.Models.Server;
import net.dv8tion.jda.api.JDA;
//import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class Notifier implements Runnable {

	private BDcontroller bd;
	private Map<String, String> env = System.getenv();
	private JDA jda;

	public Notifier(JDA jda, BDcontroller bd) throws LoginException {
		this.jda = jda;
		this.bd = bd;

	}

	private int currentTime() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		int currentMonth = LocalDate.now().getMonthValue();
		int currentDay = LocalDate.now().getDayOfMonth();
		String month = Integer.toString(currentMonth);
		String day = Integer.toString(currentDay);
		if (currentMonth < 10)
			month = "0" + month;
		if (currentDay < 10)
			day = "0" + day;

		return Command.convertDateToInt(month + "-" + day);
	}

	@Override
	public void run() {

		String finishLine = env.get("SECOND_PART_MESSAGE");
		try {
			jda.awaitReady();
			final int cTime = currentTime();
			ArrayList<Bday> users = bd.searchBdays(cTime);
			ArrayList<Server> servers = bd.getServers();
			if (!users.isEmpty()) {
				for (Server server : servers) {
					TextChannel ch;
					ch = jda.getTextChannelById(server.getChannel());
					for(int i=0;i<users.size();i++) {
						if(users.get(i).getServer()== server.getId()) {
						//	if (!users.get(i).getMessage().isBlank() || !users.get(i).getMessage().isEmpty()) {
								ch.sendMessage(
										new EmbedMessage("Piñatas", getNamesByServer(server.getId(), users) + finishLine + "\n")
												.get().build())
										.queue();
						//	}
							
						}
					}
					
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getNamesByServer(Long server, ArrayList<Bday> users) {
		String result = env.get("FIRST_PART_MESSAGE");
		int i = 0;
		if (!users.isEmpty()) {
			if (users.size() == 1 && users.get(0).getServer() != server.longValue())
				return "";
			while (users.get(i).getServer() != server.longValue())
				i++;
			while (i < users.size() && users.get(i).getServer() == server.longValue()) {
				result += "<@" + users.get(i).getName() + ">, ";
				i++;
			}
			result = result.substring(0, result.length() - 2);
		}

		return result;
	}
}
