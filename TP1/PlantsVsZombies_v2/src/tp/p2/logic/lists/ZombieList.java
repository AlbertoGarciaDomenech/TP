// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p2.logic.lists;

import tp.p2.logic.Game;
import tp.p2.logic.entities.zombies.Zombie;

public class ZombieList {
	private int cont; 
	private Zombie[] zombies;
	
	public ZombieList(int numZombies){
		zombies = new Zombie[numZombies];
		cont = 0;
	}
	
	public int getSize() {
		return cont;
	}
	

	public void add(int coor_x, int coor_y, Game currentGame) {
		Zombie zombie = new Zombie(coor_x, coor_y,currentGame);
		zombies[cont] = zombie;
		cont++;
		
	}
	
	private void remove(int pos) {
		while(pos < cont) {
			zombies[pos] = zombies[pos + 1];
			pos++;
		}
		cont--;
	}
	
	public void clean() { // Elimina a los zombies muertos
		for(int i = 0; i < cont; i++) {
			if(zombies[i].getHealth() <= 0) {
				remove(i);
			}
		}
	}
	
	public void update() {
		for(int i = 0; i < cont; i++) {
			zombies[i].update();
		}
	}
	
	public Zombie getZombie(int x, int y){	// Dadas dos coordenadas, devuelve el zombie de la lista que coincida con estas
		int i = 0;
		
		while((i < cont) && !(zombies[i].positionIs(x, y)))
			i++;
		
		return zombies[i];
	}
	
	public boolean emptyRow(int row) { // Comprueba si la fila tiene algun zombie
		int i = 0;
		
		while((i < cont) && (zombies[i].getPosX() != row))
			i++;
		
		return i == cont;
	}
	
	public boolean emptyCol(int col) { // Comprueba si la columna tiene algun zombie
		int i = 0;
		
		while((i < cont) && (zombies[i].getPosY() != col))
			i++;
		
		return i == cont;
	}
	
	public int checkFirstZombie(int row) { // Dada una fila(X) devuelve la coord y del primer zombie en esa fila
		int y = -1;
		
		for(int i = 0; i < cont; i++) {
			
			if((zombies[i].getPosX() == row) && (y == -1)) {
				y = zombies[i].getPosY();
			}
			else if((zombies[i].getPosX() == row) && (y != -1)) {
				if(zombies[i].getPosY() < y)
					y = zombies[i].getPosY();
			}
		}
		
		return y;
	}
	
	public boolean isZombie(int coor_x, int coor_y) { // Devuelve si en una coordenada dada hay un zombie
		int i = 0;
		while(i < cont && !(zombies[i].getPosX() == coor_x && zombies[i].getPosY() == coor_y)) {
			i++;
		}
		return i < cont;
	}
	
	public String getString(int i, int j) {
		if(isZombie(i, j)) {
			return getZombie(i, j).toString();
		}
		else 
			return " ";	
	}
}
