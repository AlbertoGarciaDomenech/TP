package tp.p2.util;

import tp.p2.control.*;

public class CommandParser {
	
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new NoneCommand(),
	};
	
	public static Command parseCommand(String commandWord, Controller controller) {
		// INVOCA AL METODO parse() DE CADA SUBCLASE DE COMMAND
	}
	
	public static String commandHelp() {
		// INVOCA AL METODO helpText() DE CADA SUBCLASE DE COMMAND
	}
}
