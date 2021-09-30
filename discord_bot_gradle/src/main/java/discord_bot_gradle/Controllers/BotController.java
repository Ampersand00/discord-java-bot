package discord_bot_gradle.Controllers;

import discord_bot_gradle.Notifier;

public class BotController {
	private CommandController commandC;
	private Notifier notifier;
	private BDcontroller bd;

	public BotController(CommandController commandController, Notifier notifier, BDcontroller bd ) {
		this.commandC=commandController;
		this.notifier = notifier;
		this.bd=bd;
	}
}
