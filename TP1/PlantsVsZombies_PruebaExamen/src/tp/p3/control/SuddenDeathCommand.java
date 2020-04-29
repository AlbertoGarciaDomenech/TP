package tp.p3.control;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class SuddenDeathCommand extends Command {

		private int x, y;
		
		private int cost = 100;
		
		private static String commandText = "suddendeath";
		private static String commandTextMsg = "[Su]ddenDeath <x> <y>";
		private static String helpTextMsg = "Kills zombie in position x, y.";

		public SuddenDeathCommand() {
			super(commandText, commandTextMsg, helpTextMsg);
		}

		public SuddenDeathCommand(int x, int y) {
			super(commandText, commandTextMsg, helpTextMsg);
			this.x = x;
			this.y = y;
		}

		public Command parse(String[] commandWords) throws CommandParseException {
			
			if (commandWords[0].equals(this.commandName) || (commandWords[0].equals(commandName.substring(0, 2)))) {
				
					if (commandWords.length == 3) {
						try {
							return new SuddenDeathCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
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
				if(game.isZombie(x, y)){
			
					if(!game.suddenDeath(x,y, cost)) return false;
				}
				else throw new CommandExecuteException("You can´t kill a plant like that! What are you, vegan?");
				
			}
			else {
				throw new CommandExecuteException("The tile is empty");
			}
			return true;
		}


}
