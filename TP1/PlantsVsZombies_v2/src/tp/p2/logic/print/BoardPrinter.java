package tp.p2.logic.print;

import tp.p2.logic.Game;

public abstract class BoardPrinter implements GamePrinter{
	
	public static final String space = " ";
	
	public BoardPrinter(Game game) {	
		encodeGame(game);
	}
	
	protected abstract void encodeGame(Game game);
	
	public void printGame(Game game) {
		encodeGame(game);
		System.out.println(this.toString());
	}
	
}
