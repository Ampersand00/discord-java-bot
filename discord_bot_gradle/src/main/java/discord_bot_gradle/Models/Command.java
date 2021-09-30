package discord_bot_gradle.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import discord_bot_gradle.Controllers.BDcontroller;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

	protected BDcontroller bd;

	public Command(BDcontroller bd) {
		this.bd = bd;
	}

	public abstract void execute(MessageReceivedEvent event);

	public abstract String help();

	public static int convertDateToInt(String dateString) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int year = Year.now().getValue();
			Date date = dateFormat.parse(year + "-" + dateString);
			c.setTime(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.get(Calendar.DAY_OF_YEAR);
	}

	public static String convertInttoDate(int bdFormatDay) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, bdFormatDay);
		System.out.println(c.getTime());
		String result = Integer.toString(c.getTime().getDate()) + "-" + Integer.toString(c.getTime().getMonth() + 1);
		System.out.println("Int to date: " + result);
		return result;
	}

	public String[] splitMessage(MessageReceivedEvent event) {
		String msg = event.getMessage().getContentRaw();
		return msg.split(" ");
	}

}
