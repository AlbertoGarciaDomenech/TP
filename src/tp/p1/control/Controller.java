// Tecnologia de la Programacion, Practica_1 (PlantasVsZombies)
// Alumnos: Alberto García Doménech y Pablo Daurell Marina

package tp.p1.control;

import java.util.Scanner;

import tp.p1.logic.Game;

public class Controller {
	private Game game;
	private Scanner in = new Scanner(System.in);

	public Controller(Game current_game) {
		game = current_game;
	}
	
	public void run() {
		
		while(!game.isFinished()) {
			game.update();
			game.draw();
			if(!game.isFinished()) {
				boolean updated = false;
				while(!updated) {
					String comando = in.nextLine();
					updated = userCommand(comando);
				}
			}
			game.computerAction(); 
		}
		
		game.winner();
	}
	
	public boolean userCommand(String comando) {
		
		boolean updated = false;
		String[] comandos = comando.split("\\s+"); // Divide el string ignorando todos los espacios
		
		if((comandos[0].toLowerCase().equals("add") || comandos[0].toLowerCase().equals("a")) && (comandos.length > 3)) { // Add Plant
			
			int x = Integer.parseInt(comandos[2]);
			int y = Integer.parseInt(comandos[3]);
			
			if((x <= game.getRows() -1  && x >= 0) && ( y <= game.getCols() -1 && y >= 0)) {
				if(game.isEmpty(x, y)) // Check if selected tile is empty
				{			
					if(comandos[1].toLowerCase().equals("sunflower") || comandos[1].toLowerCase().equals("s")) {
						updated = game.addSunflower(x, y);
						if(!updated)System.out.println("You need more suncoins for this plant!");
					}
					
					else if(comandos[1].toLowerCase().equals("peashooter") || comandos[1].toLowerCase().equals("p")) {
						updated = game.addPeashooter(x, y);
						if(!updated)System.out.println("You need more suncoins for this plant!");
					}
					
					else
						System.out.println("Invalid plant");
				}
				else
					System.out.println("Invalid position");
			}
			else
				System.out.println("Invalid position");
		}
		else if(comandos[0].toLowerCase().equals("reset") || comandos[0].toLowerCase().equals("r")) { // Reset Game
			 game.resetGame();
			 updated = true;
		}
		else if(comandos[0].toLowerCase().equals("list") || comandos[0].toLowerCase().equals("l")){ 
			System.out.println(game.plantsinfo());
			updated = false;
;		}
		else if(comandos[0].toLowerCase().equals("none") || comandos[0].toLowerCase().equals("n") || comandos[0].equals("")) { 
			// No action, skip
			updated = true;
		}
		else if(comandos[0].toLowerCase().equals("exit") || comandos[0].toLowerCase().equals("e")){ 
			game.endGame();
			System.out.println("GAME OVER");
			updated = true;
		}
		else if(comandos[0].toLowerCase().equals("help") || comandos[0].toLowerCase().equals("h")) { 
			System.out.println(game.help());
			updated = false;
		}
		else 
		{
			System.err.println("Comando no reconocido.");
			updated = false;
		}	
		if(!updated)System.out.println("Comando > ");
		return updated;
	}
	
}