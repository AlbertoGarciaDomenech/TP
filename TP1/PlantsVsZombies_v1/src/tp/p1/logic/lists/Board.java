// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.logic.lists;

import tp.p1.logic.Game;

public class Board {
	
	private PeashooterList peashooterList;
	private SunflowerList sunflowerList; 
	private ZombieList zombieList;
	
	static private final int ROWS = 4;
	static private final int COLS = 8;
	static private final int MAX_PLANTS = ROWS * COLS;
	
	public Board(int numZombies) {
		peashooterList = new PeashooterList(MAX_PLANTS);
		sunflowerList = new SunflowerList(MAX_PLANTS); 
		zombieList = new ZombieList(numZombies);
	}
	
	public void update() {
		sunflowerList.update();
		peashooterList.update();
		zombieList.update();
		cleanBoard();
	}

	public void cleanBoard() {
		sunflowerList.clean();
		peashooterList.clean();
		zombieList.clean();
	}
	
	public int getRows() {
		return ROWS;
	}
	
	public int getCols() {
		return COLS;
	}
	
	public boolean isEmpty(int x, int y) {
		boolean empty = true;
		
		if(sunflowerList.isSunflower(x, y))
			empty = false;
		else if(peashooterList.isPeashooter(x, y))
			empty = false;
		else if(zombieList.isZombie(x, y))
			empty = false;
		
		return empty;
	}
	
	public boolean isZombie(int coor_x, int coor_y) {
		return zombieList.isZombie(coor_x, coor_y);
	}
	public boolean isPeashooter(int coor_x, int coor_y) {
		return peashooterList.isPeashooter(coor_x, coor_y);
	}
	public boolean isSunflower(int coor_x, int coor_y) {
		return sunflowerList.isSunflower(coor_x, coor_y);
	}
	public void addPeashooter(int coor_x, int coor_y, Game currentGame) {
		peashooterList.add(coor_x, coor_y, currentGame);
	}
	public void addSunflower(int coor_x, int coor_y, Game currentGame) {
		sunflowerList.add(coor_x, coor_y, currentGame);
	}
	public void addZombie(int coor_x, int coor_y, Game currentGame) {
		zombieList.add(coor_x, coor_y, currentGame);
	}
	public boolean emptyRow(int row) {
		return zombieList.emptyRow(row);
	}
	public int firstZombie(int row) {
		return zombieList.checkFirstZombie(row);
	}
	public void takeZombieDamage(int row, int y, int damage) {
		zombieList.getZombie(row, y).takeDamage(damage);
	}
	public boolean areZombiesTerminated() {
		return (zombieList.getSize() == 0);
	}
	public boolean emptyCol(int col) {
		return zombieList.emptyCol(col);
	}
	
	public String getString(int coor_x, int coor_y) {
		return (peashooterList.getString(coor_x, coor_y) + sunflowerList.getString(coor_x, coor_y) + zombieList.getString(coor_x, coor_y));
	}
	
	public boolean canZombieAttack(int x, int y) {
		return (peashooterList.isPeashooter(x, y) || sunflowerList.isSunflower(x, y));
	}
	
	public void ZombieAttacks(int x, int y, int damage) {
		if(peashooterList.isPeashooter(x, y))
			peashooterList.getAttacked(x, y, damage);
		else
			sunflowerList.getAttacked(x, y, damage);
	}
}