package discord_bot_gradle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
//import java.util.List;
import java.util.TimeZone;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class Notifier implements Runnable {

	 private BDcontroller bd;
	 private Map<String, String> env = System.getenv();
	 //private  final String channelDefault = env.get("DEFAULT_CHANNEL");
	 //private  final String messageDefault;
	 //private String channel;
	 //private String message;
	 private JDA jda;
	 
	 
	 public Notifier(JDA jda, BDcontroller bd) throws LoginException {
		 this.jda=jda;
		 this.bd=bd;
		 
		 
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
		
		return Command.convertDateToInt(month+"-"+day);
	}

	@Override
	public void run(){
		
		String finishLine= env.get("SECOND_PART_MESSAGE");
		try {
			jda.awaitReady();
			final int cTime=currentTime();
			System.out.println("Entra en el run");
			ArrayList<Bday> users=  bd.searchBdays(cTime);
			ArrayList<Server> servers=bd.getServers();
			System.out.println("entre en el check");
			if (users.isEmpty())System.out.println("No es el cumplke de nadie ");
		    if(!users.isEmpty()){
		    	System.out.println("entra ");
				/*List<TextChannel> channels = jda.getTextChannelsByName(this.channel, true);
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
					
					
				 }*/
				//poner uno con toda la lista y luego los mensajes personalizados 
				for(Server server: servers) {
					System.out.println("b uscando los nombres de todos ");
					TextChannel ch;
				
					ch=jda.getTextChannelById(server.getChannel());
					System.out.println("channel encontrado");
					System.out.println("Line "+ finishLine);
					//hay que añadir el primer mensaje 
					//enviar embed
					System.out.println(getNamesByServer(server.getId(), users));
					System.out.println(ch);
					//ch.sendMessage(getNamesByServer(server.getId(), users)+finishLine).queue();
					//if(ch!=null)
					ch.sendMessage(new EmbedMessage("Piñatas",getNamesByServer(server.getId(), users)+finishLine).get().build()).queue();
				}
				
				for(Bday u:users) {
					Guild gd= jda.getGuildById(u.getServer());
					//TextChannel ch=jda.getTextChannelById(u.getServer().getChannel());
					if(!u.getMessage().isBlank() || !u.getMessage().isEmpty())
						gd.getTextChannelById(u.getChannel()).sendMessage(u.getMessage()).queue();
				}
				
			}
			
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
  }
	
	private String getNamesByServer(Long server, ArrayList<Bday> users) {
		System.out.println("[Notifier: getNameByServer] llamdaa recibidfa");
		String result= env.get("FIRST_PART_MESSAGE");
		/*String names=start;*/int i=0;
		/*for(Bday u : users) {
			if(u.getServer()==server.longValue()) {
				names+= "<@"+u.getName()+">, ";
			}
		}*/
		while(users.get(i).getServer()!=server.longValue()) i++;
		System.out.println("enc server");
		while(i< users.size() && users.get(i).getServer()==server.longValue()) {
			result+= "<@"+users.get(i).getName()+">, ";
			i++;
		}
		System.out.println("vistos lus osuarios");
		//System.out.println(names.substring(0, names.length()-2));
		result.substring(0, result.length()-2);
		return result;
	}
} 

