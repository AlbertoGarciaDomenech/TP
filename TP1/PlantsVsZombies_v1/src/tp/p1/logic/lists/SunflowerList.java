// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.logic.lists;

import tp.p1.logic.Game;
import tp.p1.logic.entities.Sunflower;

public class SunflowerList {
	private int cont;
	private Sunflower[] sunflowers;
	
	public SunflowerList(int numPlantas){
		sunflowers = new Sunflower[numPlantas];
		cont = 0;
	}
	
	public int getSize() {
		return cont;
	}

	public void add(int coor_x, int coor_y, Game currentGame) {
		Sunflower sunflower = new Sunflower(coor_x, coor_y,currentGame);
		sunflowers[cont] = sunflower;
		cont++;
		
	}
	
	private void remove(int pos) {
		while(pos < cont - 1) {
			sunflowers[pos] = sunflowers[pos + 1];
			pos++;
		}
		cont--;
	}
	
	public void clean() { // Elimina a las plantas muertas
		for(int i = 0; i < cont; i++) {
			if(sunflowers[i].getHealth() <= 0) {
				remove(i);
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < cont; i++) {
			sunflowers[i].generateSun();
		}
	}
	
	public boolean isSunflower(int coor_x, int coor_y) { // Devuelve si en cierta posicion hay un sunflower
		int i = 0;
		while(i < cont && !(sunflowers[i].getPosX() == coor_x && sunflowers[i].getPosY() == coor_y)) {
			i++;
		}
		return i < cont;
	}
	
	public Sunflower getSunflower(int x, int y){	// Dadas dos coordenadas, devuelve el sunflower de la lista que coincida con estas
		int i = 0;

		while((i < cont) && !(sunflowers[i].positionIs(x, y)))
			i++;
		
		return sunflowers[i];
	}
	
	public String getString(int i, int j) {
		if(isSunflower(i, j)) {
			return getSunflower(i, j).toString();
		}
		else return " ";	
	}
	public void getAttacked(int x, int y, int damage) {
		getSunflower(x, y).takeDamage(damage);
	}
}
