package tp.p3.control;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class ShieldCommand extends Command {
	private int x, y;

	private int cost = 100;

	private static String commandText = "shield";
	private static String commandTextMsg = "[Sh]ield <x> <y>";
	private static String helpTextMsg = "Gives shield to entity in position x, y.";

	public ShieldCommand() {
			super(commandText, commandTextMsg, helpTextMsg);
		}

	public ShieldCommand(int x, int y) {
			super(commandText, commandTextMsg, helpTextMsg);
			this.x = x;
			this.y = y;
		}

	public Command parse(String[] commandWords) throws CommandParseException {

		if (commandWords[0].equals(this.commandName) || (commandWords[0].equals(commandName.substring(0, 2)))) {

			if (commandWords.length == 3) {
				try {
					return new ShieldCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
				} catch (NumberFormatException ex) {
					throw new CommandParseException("Invalid argument for " + this.commandName
							+ " command, number expected: " + commandTextMsg);
				}
			} else
				throw new CommandParseException(
						"Incorrect number of arguments for " + this.commandName + " command: " + commandTextMsg);
		} else
			return null;
	}

	public boolean execute(Game game) throws CommandExecuteException {

		if (!game.isEmpty(x, y)) {
			if (game.isPlant(x, y)) {
				game.setShield(x,y,cost);
			} else
				throw new CommandExecuteException("You can´t give a zombie a shield.What are you, a communist?");

		} else {
			throw new CommandExecuteException("The tile is empty");
		}
		return true;
	}

}
