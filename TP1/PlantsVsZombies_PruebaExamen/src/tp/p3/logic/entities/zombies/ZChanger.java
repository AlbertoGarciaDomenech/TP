package tp.p3.logic.entities.zombies;

import tp.p3.logic.Game;
import tp.p3.logic.entities.GameObject;

public class ZChanger extends Zombie{
	
	public ZChanger() {
		initialize();
	}
	
	public ZChanger(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialize();
	}
	
	private void initialize() {
		maxHealth = 3;
		health = maxHealth;
		cycle = 0;
		freq = 3;
		damage = 1;
		symbol = "G";
		name = "zchanger";
		infoName = "zombie chan[G]er";
	}
	
	public GameObject getThisObject() {
		return new ZChanger();
	}
	@Override
	public void update() {
		if(!game.isEmpty(x, y-1)) {
			if(game.isPlant(x, y-1)) {
				if(!game.isEmpty(x-1, y) && !game.isEmpty(x+1, y)) {
				game.zombieAttacks(x, y-1,damage);
				}
				else if (game.isEmpty(x-1, y) && x > 0) {
					this.x = x-1;
				}
				else if(game.isEmpty(x+1, y) && x < 3) {
					this.x = x+1;
					}
			}
		}
		else {
			move();		
		}
	}
	
}
