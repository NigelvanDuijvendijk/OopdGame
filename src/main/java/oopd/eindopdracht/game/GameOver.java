package oopd.eindopdracht.game;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.userinput.IMouseInput;
import processing.core.PGraphics;


/**
 * object that manages everything that has to do with the gameover screen
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class GameOver  {
/**
 * standard text size for gameover screen
 */
 public final int STANDARDTEXTSIZE = 40;
 /**
  * amount of tiles in the y axis
  */
 public final int TILESYCOUNT = 16;
 /**
  * amount of tiles in the x axis
  */
 public final int TILESXCOUNT = 26;
	
 private TutorialWorld world;
 private TextObject gameoverText;
 private TextObject restart;
 private Light light;
 private Player player;
 private Bat bat;
 private Firefly firefly;
 /**
  * if the player has won true
  */
 public Boolean won = false;
 
 /**
  * tilemap that stores the windows change
  */
 public int gameOverTilesMap[][] = {
         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0,0},
         {0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0,0},
         {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,0},
         {0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, 0},
         {0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, 0},
         {0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, 0, 0, 0},
         {0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
 };

	public GameOver(TutorialWorld world) {
		this.world = world;
	}
	
	/**
	 * triggers the functions for Gameover
	 */
	public void triggerGameOver() {
    	changeScreen(gameOverTilesMap, true);
		gameOverText();
	}
	
	/**
	 * Triggers the game to restart
	 */
	public void triggerReplay() {
    	changeScreen(gameOverTilesMap, false);
		world.gameState = 1;
		world.deleteAllGameOBjects();
	}
	
	/**
	 * makes the screen change animation
	 * @param tilemap tilemap that needs to be used
	 * @param add if true the tilemap will fill if false the tilemap will be emptied
	 */
	public void changeScreen(int[][] tilemap, Boolean add){
		world.initializeTileMap(tilemap);
		if(add) {
			for(int i = 0; i < TILESYCOUNT; i++ ) {
		    		for(int j = 0; j < TILESXCOUNT; j++) {
		    			tilemap[i][j] = 0;
		    			world.initializeTileMap(tilemap);
		    		}		
		    	}	
		}
		if(!add) {
			for(int i = TILESYCOUNT; i > TILESYCOUNT; i-- ) {
	    		for(int j = TILESXCOUNT; j > TILESXCOUNT; j--) {
	    			tilemap[i][j] = 1;
	    			world.initializeTileMap(tilemap);
	    		}		
	    	}	
		}
	}

	/**
	 * initialises the text for the gameover screen it checks if the player has won or lost
	 */
	public void gameOverText(){
		String gameText = "";
		light = new Light(world, player);
		if(!won) {
			gameText = "Game over!";
		}
		
		if(won){
			gameText = "Het kasteel is weer veilig!";
		}
		gameoverText = new TextObject(gameText, STANDARDTEXTSIZE);
		gameoverText.setForeColor(255, 255, 255, 255);
		world.addGameObject(gameoverText, 400, 100, 100);
		
		restart = new TextObject("Opnieuw spelen", STANDARDTEXTSIZE);		
		restart.setForeColor(255, 255, 255, 255);
		world.addGameObject(restart, 350, 250, 100);
	}
	
	/**
     * triggers the game to go to the gameover screen
     */
	public void gameoverActions() {
		System.out.println("Game over"); 
		world.gameState = 2;
    	won = true;
		triggerGameOver();
	}

	

}