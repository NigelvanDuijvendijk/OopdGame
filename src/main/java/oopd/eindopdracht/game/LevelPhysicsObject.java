package oopd.eindopdracht.game;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import oopd.eindopdracht.game.tiles.FloorTile;
import processing.core.PVector;

/**
 * object that manages everything that has to do with the LevelPhysicsObject which are placed by the player
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class LevelPhysicsObject extends PhysicsObject implements ICollidableWithGameObjects, ICollidableWithTiles {
   
	private GameOver gameover;
	private GameWorld world;
    
    public LevelPhysicsObject(GameWorld world, float gravity, boolean collide, int speed, String sprite) {        
        super(world, gravity, collide, speed,  "physObject.png");
        this.world = world;
    }
   
    public void ifTouched() {
    	gameover = new GameOver(world);
		gameover.gameoverActions(false);
    }

} 