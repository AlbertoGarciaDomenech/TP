// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1;

import tp.p1.logic.Game;
import tp.p1.logic.Level;
import tp.p1.control.Controller;

public class PlantsVsZombies {
	
	public static void main(String[] args) {
		
		if(args.length == 1 || args.length == 2) {
			
			Level level = null;
			long seed = System.currentTimeMillis();
			
			if(args[0].equals("EASY")) level = Level.EASY;
			else if(args[0].equals("HARD")) level = Level.HARD;
			else if(args[0].equals("INSANE")) level = Level.INSANE;
			
			if(level != null) {
				
				if(args.length == 2) seed = Integer.parseInt(args[1]);
				
				Game game = new Game(level, seed);
				Controller controller = new Controller(game);
				
				System.out.println("Random seed used: " + seed);
				controller.run();
			}
	    }
	    else {
	        System.err.println("Usage: PlantsVsZombies <level> [<seed>]"); 
	        // Mensaje de error estandar (UNIX), <level>: Obligatorio, [<seed>]: Opcional
	    }
	}
	
}
