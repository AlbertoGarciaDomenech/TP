package tp.p2.logic.factories;

import tp.p2.logic.entities.plants.*;
import tp.p2.logic.Game;

public class PlantFactory {
	
	private static Plant[] availablePlants = {
		new Sunflower(),
		new Peashooter(),
		new CherryBomb(),
		new Wallnut()
	};
	
	public static Plant getPlant(String plantName, int x, int y, Game game) {
		Plant plant = null;
		int i = 0;
		
		while(plant == null && i < availablePlants.length) {
			plant = availablePlants[i].parse(plantName, x, y, game);
			i++;
		}
		
		return plant;
	}
	
	public static String listOfAvailablePlants() {
		String info = "";
		for(Plant plant : availablePlants) {
			info += plant.getInfo();
		}
		return info;
	}
	
}
