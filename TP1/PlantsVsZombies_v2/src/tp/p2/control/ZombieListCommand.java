package tp.p2.control;

import tp.p2.logic.Game;
import tp.p2.logic.factories.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand{
	
	private static String commandText = "zombielist";
	private static String commandTextMsg = "[Z]ombie list";
	private static String helpTextMsg = "print the list of available zombies.";
	
	public ZombieListCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public NoParamsCommand parse(String[] commandWords, Controller controller) {
		if(commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandLetter)) {
			return new ZombieListCommand();
		}
		else {
			return null;
		}
	}
	
	public void execute(Game game, Controller controller) {
		controller.setNoPrintGameState();
		System.out.println(ZombieFactory.listOfAvailableZombies());
	}
	
}
