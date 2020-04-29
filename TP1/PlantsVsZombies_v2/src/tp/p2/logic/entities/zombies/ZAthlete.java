package tp.p2.logic.entities.zombies;

import tp.p2.logic.Game;

public class ZAthlete extends Zombie{
	
	public ZAthlete() {
		initialise();
	}
	
	public ZAthlete(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialise();
	}
	
	private void initialise() {
		maxHealth = 2;
		health = maxHealth;
		cycle = 0;
		freq = 1;
		damage = 1;
		symbol = "X";
		name = "athlete";
		infoName = "Zombie athlete[X]";
	}
	

	public Zombie parse(String name, int x, int y, Game game) {
		if(name.equals(this.name)) {
			return new ZAthlete(x, y, game);
		}
		else {
			return null;
		}
	}
	
	@Override
	public void update() {
			if(!game.isEmpty(x, y-1)) {
				if(game.isPlant(x, y-1)) {
					game.zombieAttacks(x, y-1,damage);
				}
				// else { theres a zombie, dont move }
			}
			else {
				if(!(this.cycle == 0)) {
				move();		
			}
				else
					this.cycle++;
		}
	}
}
