package tp.p2.logic.entities.plants;

import tp.p2.logic.Game;

public class Sunflower extends Plant {
	
	private static int coins = 10;
	
	public Sunflower() {
		initialise();
	}
	
	public Sunflower(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialise();
	}
	
	private void initialise() {
		cost = 20;
		maxHealth = 2;
		health = maxHealth;
		cycle = 0;
		freq = 2;
		damage = 0;
		symbol = "S";
		name = "sunflower";
		infoName = "[S]unflower";
	}
	
	public Plant parse(String name, int x, int y, Game game) {
		if(name.equals(this.name) || (name.equals(this.symbol.toLowerCase()))) {
			return new Sunflower(x, y, game);
		}
		else {
			return null;
		}
	}
	
	public void update() {
		generateSun();
		this.cycle++;
	}
	
	public void generateSun() {
		if((cycle % 2) == 0) {
			game.addCoins(coins);
		}
	}
	
}
