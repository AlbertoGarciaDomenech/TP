package tp.p3.logic.entities.zombies;

import tp.p3.logic.Game;
import tp.p3.logic.entities.GameObject;

public class ZMage extends Zombie{
	
	public ZMage() {
		initialize();
	}
	
	public ZMage(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialize();
	}
	
	private void initialize() {
		maxHealth = 5;
		health = maxHealth;
		cycle = 0;
		freq = 3;
		damage = 1;
		symbol = "M";
		name = "zmage";
		infoName = "zombie [M]age";
	}
	
	public GameObject getThisObject() {
		return new ZMage();
	}
	
	@Override
	public void update() {
		if(cycle%freq == 0 && game.canMageRage()) {
			game.mageRage(damage);
		}
	}
	
}
