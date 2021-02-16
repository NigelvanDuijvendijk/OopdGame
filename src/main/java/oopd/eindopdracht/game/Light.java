package oopd.eindopdracht.game;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;
import oopd.eindopdracht.game.tiles.FloorTile;
import processing.core.PVector;

/**
 * object that manages everything that has to do with the Lights
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class Light extends SpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {
	  private GameWorld world;
	  private Player player;
	  private GameOver gameover;
//      private float gravity = 0.2f;
      private boolean playerDead = false;
      
	    public Light(GameWorld world, Player player) {
	        super(new Sprite(GameWorld.MEDIA_URL.concat("light-small.gif")));
	        this.world = world;
	        this.player = player;
//	        setGravity(gravity);
	    } 
	    
	    @Override
	    public void update() {
	     if(getDistanceFrom(player) < 50) {
	    	 if(playerDead == false) {
		    	world.deadSound.cue(0);
		    	world.deadSound.play();
		    	world.deleteGameObject(player);
		    	gameover = new GameOver(world);
		    	playerDead = true;
		    	world.gameState = 2;
		    	gameover.triggerGameOver();
	    	 }
	     }
	        
	    }

	    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
	        PVector vector;
	        for (CollidedTile ct : collidedTiles) {
	            if (ct.getTile() instanceof FloorTile) {
	                try {					
//	                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());
//	                    setY(vector.y - getHeight());
	                } catch (TileNotFoundException e) {
	                    e.printStackTrace();
	                }
	            }
	        } 
	      
	    }

		@Override
		public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
			for (GameObject go: collidedGameObjects) {
				if (go instanceof DarkPower) {	
			    	world.deleteGameObject(this);
			    	world.lightCount--;
			    	System.out.println(world.lightCount);
			    	if(world.lightCount == 0 && world.level == 2) {
			    		gameover = new GameOver(world);
			    		gameover.gameoverActions(true);
			    	}else if(world.lightCount == 0 && world.level == 1){
			    		world.changeLevel(2);
			    	}
				}
			}
		}

}
