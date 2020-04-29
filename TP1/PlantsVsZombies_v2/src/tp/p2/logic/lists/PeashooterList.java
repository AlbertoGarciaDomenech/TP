// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p2.logic.lists;

import tp.p2.logic.Game;
import tp.p2.logic.entities.plants.Peashooter;

public class PeashooterList {
	
	private int cont;
	private Peashooter[] peashooters;
	
	public PeashooterList(int numPlantas){
		peashooters = new Peashooter[numPlantas];
		cont = 0;
	}
	
	public int getSize() {
		return cont;
	}
	
	public void add(int coor_x, int coor_y, Game currentGame) {
		Peashooter peashooter = new Peashooter(coor_x, coor_y,currentGame);
		peashooters[cont] = peashooter;
		cont++;
		
	}
	
	private void remove(int pos) {
		while(pos < cont) {
			peashooters[pos] = peashooters[pos + 1];
			pos++;
		}
		cont--;
	}
	
	public void clean() { // Elimina a las plantas muertas
		for(int i = 0; i < cont; i++) {
			if(peashooters[i].getHealth() <= 0) {
				remove(i);
			}
		}
	}
	
	public void update() {
		for(int i = 0; i < cont; i++) {
			peashooters[i].shoot(); // Cada planta dispara
		}
	}
	
	public boolean isPeashooter(int coor_x, int coor_y) { // Dadas dos coordenadas busca si existe algun peashooter en ellas
		int i = 0;
		while(i < cont && !(peashooters[i].getPosX() == coor_x && peashooters[i].getPosY() == coor_y)) {
			i++;
		}
		return i < cont;
	}
	
	public Peashooter getPeashooter(int x, int y){	// Dadas dos coordenadas, devuelve el peashooter de la lista que coincida con estas
		int i = 0;
		
		while((i < cont) && !(peashooters[i].positionIs(x, y)))
			i++;
		
		return peashooters[i];
	}
	
	public String getString(int i, int j) {
		if(isPeashooter(i,j)) {
			return getPeashooter(i, j).toString();
		}
		else {
			return " ";
		}
	}
	public void getAttacked(int x, int y, int damage) {
		getPeashooter(x, y).takeDamage(damage);
	}
	
//	public void add(Peashooter peashooter) {
//	peashooters[cont] = peashooter;
//	cont++;
//}
}
