package tp.p3.logic.entities.zombies;

import tp.p3.logic.Game;
import tp.p3.logic.entities.GameObject;

public class ZAcrobat extends Zombie{
	
	private boolean jumped = false;
	
	public ZAcrobat() {
		initialize();
	}
	
	public ZAcrobat(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialize();
	}
	
	private void initialize() {
		maxHealth = 5;
		health = maxHealth;
		cycle = 0;
		freq = 2;
		damage = 1;
		symbol = "A";
		name = "zacrobat";
		infoName = "zombie [A]crobat";
	}
	
	public GameObject getThisObject() {
		return new ZAcrobat();
	}
	@Override
	public void update() {
		if(!game.isEmpty(x, y-1)) {
			if(game.isPlant(x, y-1)) {
				if(!game.isEmpty(x, y-2) || jumped) {
				game.zombieAttacks(x, y-1,damage);
				}
				else{
					this.y = this.y-2;
					jumped = true;
				}
			}
			// else { theres a zombie, dont move }
		}
		else {
			move();		
		}
	}
	
}
