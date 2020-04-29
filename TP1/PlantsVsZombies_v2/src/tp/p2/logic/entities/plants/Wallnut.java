package tp.p2.logic.entities.plants;

import tp.p2.logic.Game;

public class Wallnut extends Plant {
	
	public Wallnut() {
		initialise();
	}
	
	public Wallnut(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialise();
	}
	
	private void initialise() {
		cost = 50;
		maxHealth = 10;
		health = maxHealth;
		cycle = 0;
		freq = 1;
		damage = 0;
		symbol = "N";
		name = "wallnut";
		infoName = "Wall[N]ut";
	}
	
	public Plant parse(String name, int x, int y, Game game) {
		if(name.equals(this.name) || (name.equals(this.symbol.toLowerCase()))) {
			return new Wallnut(x, y, game);
		}
		else {
			return null;
		}
	}
	
	public void update() {
		//Nothing
	}
	
	
}
