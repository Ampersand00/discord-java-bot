package discord_bot_gradle;

import java.util.HashMap;
//sugerenia: hacer esto singletone tambien 

public class CommandController {
	
	private HashMap<String,Command> hsmp= new HashMap<>();
	private static CommandController instancia=null;
	
	private CommandController() {
		Add a=new Add();
		Mod m=new Mod();
		Delete d=new Delete();
		Help h=new Help();
		
		hsmp.put("add",a);
		hsmp.put("chg",m);
		hsmp.put("rm",d);
		hsmp.put("help",h);
	}
	
	public static CommandController getInstance() {
		if(instancia==null) {
			instancia=new CommandController();
		}
		return instancia;
	}
	
	public HashMap getCommands() {
		return hsmp;
	}

	public Command searchCommand(String args) {
		 if(hsmp.containsKey(args)) {
			return hsmp.get(args);
		}else return null;		
	}
	
	

	
	
}
