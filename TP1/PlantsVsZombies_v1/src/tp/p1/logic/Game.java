// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.logic;

import tp.p1.logic.lists.Board;
import tp.p1.logic.entities.*;


public class Game {
	
	private int cycleCounter;
	private boolean gameFinished;
	private Level level;
	private long seed;
	
	private Board board;
	private SuncoinManager suncoinManager;
	private ZombieManager zombieManager;
	
	public Game(Level new_level, long new_seed) {
	//	cycleCounter = 1;
		gameFinished = false;
		
		level = new_level;
		seed = new_seed;
		
		board = new Board(level.getNumZombies());
		suncoinManager = new SuncoinManager();
		zombieManager = new ZombieManager(level, seed);
	}
	
	public void update(){
		board.update();
	}
	
	public boolean addSunflower(int x, int y) {
		boolean added = false;
		
		if(suncoinManager.getCoins() >= Sunflower.getCost()) {
			added = true;
			board.addSunflower(x, y, this);
			suncoinManager.useCoins(Sunflower.getCost());
		}
		
		return added;
	}
	
	public boolean addPeashooter(int x, int y) {
		boolean added = false;
		
		if(suncoinManager.getCoins() >= Peashooter.getCost()) {
			added = true;
			board.addPeashooter(x, y, this);
			suncoinManager.useCoins(Peashooter.getCost());
		}
		
		return added;
	}

	public void addZombie(int x, int y) {
		if(isEmpty(x, y)){
			if(zombieManager.isZombieAdded()) {
				board.addZombie(x, y, this);
			}
		}
	}
	
	public void draw() {
		System.out.println(this);
	}
	
	public void endGame() {
		gameFinished = true;
	}

	public void computerAction() {
		addZombie((cycleCounter % (board.getRows() - 1)), (board.getCols() - 1));
		cycleCounter++;
	}
	
	public void shoot(int row, int damage) {
		if(!board.emptyRow(row)) 
		{
			int y = board.firstZombie(row);
			board.takeZombieDamage(row,y, damage);
		}
	}
	
	public int getCycle() {
		return cycleCounter;
	}
	
	public boolean isFinished() {
		if(board.areZombiesTerminated() && zombieManager.getNumZom() == 0) { // User Wins
			gameFinished = true;
		}
		else if(!board.emptyCol(0)) {
			gameFinished = true;
		}
		return gameFinished;
	}
	
	public void resetGame() {
		
		cycleCounter = 0;
		gameFinished = false;
		
		board = new Board(level.getNumZombies());
		suncoinManager = new SuncoinManager();
		zombieManager = new ZombieManager(level, seed);
	}
	
	public String plantsinfo() {
		return (Sunflower.info() + Peashooter.info());
	}
	
	public String toString(){
		int cycle = getCycle();
		int suncoins = suncoinManager.getCoins();
		int remZombies = zombieManager.getNumZom();
		
        GamePrinter gamePrinter = new GamePrinter(this, board.getRows(), board.getCols());
        return "Number of cycles: " + cycle + "\r\nSun coins: " + suncoins +  "\r\nRemaining zombies: "
        		+ remZombies + gamePrinter + "Command > ";
    }
	
	public String help() {
		return "Add <plant> <x> <y>: Adds a plant in position x, y.\r\n" + 
				"List: Prints the list of available plants.\r\n" + 
				"Reset: Starts a new game.\r\n" + 
				"Help: Prints this help message.\r\n" + 
				"Exit: Terminates the program.\r\n" + 
				"[none]: Skips cycle.";
	}
	
	public void winner() {
		if(board.areZombiesTerminated() && zombieManager.getNumZom() == 0)
			System.out.println("PLAYER WINS");
		else if(!board.emptyCol(0))
			System.out.println("ZOMBIES WIN");	
	}
	
	public String getString(int coor_x, int coor_y) {
		return board.getString(coor_x, coor_y);
	}
	
	public boolean isZombie(int coor_x, int coor_y) {
		return board.isZombie(coor_x, coor_y);
	}
	
	public boolean isPeashooter(int coor_x, int coor_y) {
		return board.isPeashooter(coor_x, coor_y);
	}
	
	public boolean isSunflower(int coor_x, int coor_y) {
		return board.isSunflower(coor_x, coor_y);
	}
	public void addCoins(int num) {
		suncoinManager.addCoins(num);
	}
	
	public void doDamage(int damage) {
		
	}
	public int getCols() {
		return board.getCols();
	}
	public int getRows() {
		return board.getRows();
	}
	
	public boolean isEmpty(int coor_x, int coor_y) {
		return board.isEmpty(coor_x, coor_y);
	}
	
	public boolean canZombieAttack(int x, int y) {
		return board.canZombieAttack(x, y);
	}
	
	public void zombieAttacks(int x, int y, int damage) {
		board.ZombieAttacks(x, y, damage);
	}
}
