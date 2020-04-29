package tp.p3.control;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class DeleteCommand extends Command{


	private int x, y;

	private static String commandText = "delete";
	private static String commandTextMsg = "[D]elete <x> <y>";
	private static String helpTextMsg = "deletes plant in position x, y.";

	public DeleteCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public DeleteCommand(int x, int y) {
		super(commandText, commandTextMsg, helpTextMsg);
		this.x = x;
		this.y = y;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		
		if (commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandLetter)) {
			
				if (commandWords.length == 3) {
					try {
						return new DeleteCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
					} catch (NumberFormatException ex) {
						throw new CommandParseException("Invalid argument for " + this.commandName + " command, number expected: " + commandTextMsg);
					}
				}
				else
					throw new CommandParseException("Incorrect number of arguments for " + this.commandName + " command: " + commandTextMsg);
		} 
		else
			return null;
	}

	public boolean execute(Game game) throws CommandExecuteException{
		
		if (!game.isEmpty(x, y)) {
			if(game.isPlant(x, y)){
		
				game.delete(x,y);
			}
			else throw new CommandExecuteException("You can´t delete a zombie");
			
		}
		else throw new CommandExecuteException("The tile is empty");
		return true;
	}
}
