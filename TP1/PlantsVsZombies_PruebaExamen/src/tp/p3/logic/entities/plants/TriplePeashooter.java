package tp.p3.logic.entities.plants;

import tp.p3.logic.Game;
import tp.p3.logic.entities.GameObject;

public class TriplePeashooter extends Plant {
	
	public TriplePeashooter() {
		super();
		initialize();
	}
	
	public TriplePeashooter(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialize();
	}
	
	private void initialize() {
		cost = 200;
		maxHealth = 3;
		health = maxHealth;
		cycle = 0;
		freq = 1;
		damage = 1;
		symbol = "t";
		name = "triplepeashooter";
		infoName = "[T]riplepeashooter";
	}
	
	public GameObject getThisObject() {
		return new TriplePeashooter();
	}
	
	public void update() {
		shoot();
		this.cycle++;
	}
	
	public void shoot() {
		game.tripleShoot(x, damage);
//		game.shoot(x,damage);
//		game.shoot(x-1,damage);
//		game.shoot(x+1,damage);
	}
	
}
