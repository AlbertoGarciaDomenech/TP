package tp.p3.logic.entities.plants;

import tp.p3.logic.Game;
import tp.p3.logic.entities.GameObject;

public class LaserPlant extends Plant {
	
	public LaserPlant() {
		super();
		initialize();
	}
	
	public LaserPlant(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialize();
	}
	
	private void initialize() {
		cost = 150;
		maxHealth = 5;
		health = maxHealth;
		cycle = 0;
		freq = 1;
		damage = 1;
		symbol = "L";
		name = "laserplant";
		infoName = "[L]aserPlant";
	}
	
	public GameObject getThisObject() {
		return new LaserPlant();
	}
	
	public void update() {
		laser();
		this.cycle++;
	}
	
	public void laser() {
		game.laser(x, y, damage);
	}
	
}
