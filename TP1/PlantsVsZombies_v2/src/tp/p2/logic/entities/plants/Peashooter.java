package tp.p2.logic.entities.plants;

import tp.p2.logic.Game;

public class Peashooter extends Plant {
	
	public Peashooter() {
		initialise();
	}
	
	public Peashooter(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialise();
	}
	
	private void initialise() {
		cost = 50;
		maxHealth = 5;
		health = maxHealth;
		cycle = 0;
		freq = 1;
		damage = 1;
		symbol = "P";
		name = "peashooter";
		infoName = "[P]eashooter";
	}
	
	public Plant parse(String name, int x, int y, Game game) {
		if(name.equals(this.name) || (name.equals(this.symbol.toLowerCase()))) {
			return new Peashooter(x, y, game);
		}
		else {
			return null;
		}
	}
	
	public void update() {
		shoot();
		this.cycle++;
	}
	
	public void shoot() {
		game.shoot(x, damage);
	}
	
}
