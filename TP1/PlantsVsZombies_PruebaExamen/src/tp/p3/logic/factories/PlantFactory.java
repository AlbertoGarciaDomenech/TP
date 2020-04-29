package tp.p3.logic.factories;

import tp.p3.logic.entities.plants.*;

public class PlantFactory {
	
	private static Plant[] availablePlants = {
		new Sunflower(),
		new Peashooter(),
		new CherryBomb(),
		new Wallnut(),
		new Cactus(),
		new TriplePeashooter(),
		new LaserPlant()
	};
	
	public static Plant getPlant(String plantName) {
		Plant plant = null;
		int i = 0;
		
		while(plant == null && i < availablePlants.length) {
			plant = (Plant)availablePlants[i].parse(plantName);
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
