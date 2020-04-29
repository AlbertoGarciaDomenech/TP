package tp.p3.logic.entities.plants;

import tp.p3.logic.Game;
import tp.p3.logic.entities.GameObject;

public class Cactus extends Plant {
	
	public Cactus() {
		super();
		initialize();
	}
	
	public Cactus(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialize();
	}
	
	private void initialize() {
		cost = 70;
		maxHealth = 7;
		health = maxHealth;
		cycle = 0;
		freq = 1;
		damage = 1;
		symbol = "K";
		name = "kactus";
		infoName = "[K]actus";
	}
	
	public GameObject getThisObject() {
		return new Cactus();
	}
	
	public void update() {
		//Nothing
	}
	
	public void takeDamage(int damage_taken) {
		//Returns attack(with 1 point of damage) before receiving any damage
		game.cactusReturnAttack(x, y, damage);
		if(!shielded) {
			if(health >= damage_taken)
				health -= damage_taken;
			else
				health = 0;
		}
		else {
			setShield(false);
		}
	}	
	
	
}
