// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.logic.entities;

import tp.p1.logic.Game;

public class Peashooter {
	private int x, y;
	private int health = 3;
	private int cycle;
	
	private static int cost = 50;
	private static int damage = 1;
	
	private Game game;
	
	public Peashooter(int coor_x, int coor_y, Game currentGame) {
		x = coor_x;
		y = coor_y;
		cycle = 0;
		game = currentGame;
	}
	
	public static int getCost() {
		return cost;
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
	
	public boolean positionIs(int coor_x, int coor_y) {
		return (x == coor_x && y == coor_y);
	}
	
	public void takeDamage(int damage_taken) {
		health -= damage_taken;
	}
	
	public void shoot() {
		game.shoot(x, damage);
	}
	
	public static String info() {
		return ("[P]eashooter Cost: " + cost + " suncoins Harm: " + damage + "\n");
	}
	
	public String toString() {
		return "P [" + health + "]";
	}
	
}
