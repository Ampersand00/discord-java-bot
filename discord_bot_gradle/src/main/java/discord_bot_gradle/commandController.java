package discord_bot_gradle;

import java.util.HashMap;
//sugerenia: hacer esto singletone tambien 

public class commandController {
	
	private HashMap<String,Command> hsmp= new HashMap<>();
	
	public commandController() {
		mario_command m=new mario_command();
		ping_command p=new ping_command();
		Add a=new Add();
		hsmp.put("mario",m);
		hsmp.put("ping",p);
		hsmp.put("add",a);
	}

	public Command searchCommand(String args) {
		if(hsmp.containsKey(args)) {
			return hsmp.get(args);
		}else return null;
			
	}
}
