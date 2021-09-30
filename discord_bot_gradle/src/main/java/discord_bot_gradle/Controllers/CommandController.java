package discord_bot_gradle.Controllers;

import java.util.HashMap;

import discord_bot_gradle.Commands.Add;
import discord_bot_gradle.Commands.Delete;
import discord_bot_gradle.Commands.Help;
import discord_bot_gradle.Commands.Mod;
import discord_bot_gradle.Commands.SelectAll;
import discord_bot_gradle.Commands.seeMessage;
import discord_bot_gradle.Commands.setChannel;
import discord_bot_gradle.Commands.setMessage;
import discord_bot_gradle.Models.Command;


public class CommandController {
	
	private HashMap<String,Command> hsmp= new HashMap<>();
	private BDcontroller bd;
	
	public CommandController(BDcontroller bd) {
		Add a=new Add(bd);
		Mod m=new Mod(bd);
		Delete d=new Delete(bd);
		Help h=new Help(bd);
		setMessage sm=new setMessage(bd);
		setChannel sc= new setChannel(bd);
		SelectAll sa = new SelectAll(bd);
		seeMessage lsm = new seeMessage(bd);
		this.bd=bd;
		
		hsmp.put("add",a);
		hsmp.put("chg",m);
		hsmp.put("rm",d);
		hsmp.put("help",h);
		hsmp.put("set channel", sc);
		hsmp.put("set message", sm);
		hsmp.put("list", sa);
		hsmp.put("show", lsm);
	}
	
	public HashMap<String,Command> getCommands() {
		return hsmp;
	}

	public Command searchCommand(String args) {
		 if(hsmp.containsKey(args)) {
			return hsmp.get(args);
		}else return null;		
	}
	
	public BDcontroller getBD() {
		return bd;
	}

	
	
}
