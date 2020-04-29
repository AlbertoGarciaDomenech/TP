package tp.p3.control;

import tp.p3.logic.Game;

public class MoreSuncoins extends NoParamsCommand{

		private static String commandText = "moresuncoins";
		private static String commandTextMsg = "[M]oreSuncoins";
		private static String helpTextMsg = "Adds 100 suncoins to game.";
		
		public MoreSuncoins() {
			super(commandText, commandTextMsg, helpTextMsg);
		}
		
		public NoParamsCommand getThisCommand() {
			return new MoreSuncoins();
		}
		
		public boolean execute(Game game) {
			game.addCoins(100);
			return false;
		}
	}
