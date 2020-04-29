// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.logic.entities;

import tp.p1.logic.Game;

public class Zombie {
	private int x, y;
	private int health = 5;
	private int cycle;
	
	private static int damage = 1;
	
	private Game game;
	
	public Zombie(int coor_x, int coor_y, Game currentGame) {
		x = coor_x;
		y = coor_y;
		cycle = 0;
		game = currentGame;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getPosX() {
		return x;
	}
	
	public int getPosY() {
		return y;
	}
	
	public void takeDamage(int damage_taken) {
		health -= damage_taken;
	}
	
	
	private void attack() {
		game.zombieAttacks(x, y-1,damage);
	}
	
	private void move() {
		if(cycle % 2 == 0) {
			if(game.isEmpty(x, y-1)) {
				y -= 1;
			}
		}
	}
	
	public void update() {
		cycle++;
		
		if(game.canZombieAttack(x, y-1)) {
			attack();
		}
		else
			move();
	}
	
	public String toString() {
		return "Z [" + health + "]";
	}
	
	public boolean positionIs(int coor_x, int coor_y) { // Devuelve si una posicion dada corresponde con la del zombie en cuestion
		return (x == coor_x && y == coor_y);
	}
}
