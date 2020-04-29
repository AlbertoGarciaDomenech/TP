package tp.p2.control;

import tp.p2.logic.Game;
import tp.p2.logic.factories.PlantFactory;

public class ListCommand extends NoParamsCommand{
	
	private static String commandText = "list";
	private static String commandTextMsg = "[L]ist";
	private static String helpTextMsg = "print the list of available plants.";
	
	public ListCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public NoParamsCommand parse(String[] commandWords, Controller controller) {
		if(commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandLetter)) {
			return new ListCommand();
		}
		else {
			return null;
		}
	}
	
	public void execute(Game game, Controller controller) {
		controller.setNoPrintGameState();
		System.out.println(PlantFactory.listOfAvailablePlants());
	}
}
