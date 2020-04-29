// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.logic;

public class SuncoinManager {
	private int sunCoins = 50;

	public void setCoins(int value) {
		sunCoins = value;
	}

	public int getCoins() {
		return sunCoins;
	}

	public void addCoins(int coins) {
		sunCoins += coins;
	}

	public void useCoins(int coins) {
		sunCoins -= coins;
	}
}
