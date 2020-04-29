package tp.p2.logic.entities.zombies;

import tp.p2.logic.Game;

public class ZBucketHead extends Zombie{
	
	public ZBucketHead() {
		initialise();
	}
	
	public ZBucketHead(int coor_x, int coor_y, Game currentGame) {
		super(coor_x, coor_y, currentGame);
		initialise();
	}
	
	private void initialise() {
		maxHealth = 8;
		health = maxHealth;
		cycle = 0;
		freq = 4;
		damage = 1;
		symbol = "W";
		name = "buckethead";
		infoName = "Zombie buckethead[W]";
	}
	
	public Zombie parse(String name, int x, int y, Game game) {
		if(name.equals(this.name)) {
			return new ZBucketHead(x, y, game);
		}
		else {
			return null;
		}
	}
	
}
