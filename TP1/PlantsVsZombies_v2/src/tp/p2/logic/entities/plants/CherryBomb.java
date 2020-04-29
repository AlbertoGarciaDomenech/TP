package tp.p2.logic.entities.plants;

import tp.p2.logic.Game;

public class CherryBomb extends Plant {
	
	public CherryBomb() {
		initialise();
	}
	
	public CherryBomb(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialise();
	}
	
	private void initialise() {
		cost = 50;
		maxHealth = 2;
		health = maxHealth;
		cycle = 0;
		freq = 1;
		damage = 10; 
		symbol = "C";
		name = "cherrybomb";
		infoName = "[C]herrybomb";
	}
	
	public Plant parse(String name, int x, int y, Game game) {
		if(name.equals(this.name) || (name.equals(this.symbol.toLowerCase()))) {
			return new CherryBomb(x, y, game);
		}
		else {
			return null;
		}
	}
	
	private void selfdestroy() {
		health = 0;
	}
	
	public void update() {
		if(cycle == 2) {
			game.explode(x, y, damage);
			selfdestroy();
		}
		this.cycle++;
	}	
}
