// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.logic.entities;

import tp.p1.logic.Game;

public class Sunflower {
	private int x, y;
	private int health = 1;
	private int cycle;
	
	private static int cost = 20;
	private static int coins = 10;
	private static int damage = 0;
	
	private Game game;
	
	public Sunflower(int coor_x, int coor_y, Game currentGame) {
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
	
	public void generateSun() {
		cycle++;
		if((cycle % 2) == 0) {
			game.addCoins(coins);
		}
	}
	
	public static String info() {
		return ("[S]unflower Cost: " + cost + " suncoins Harm: " + damage + "\n");
	}
	
	public String toString() {
		return "S [" + health + "]";
	}
}
