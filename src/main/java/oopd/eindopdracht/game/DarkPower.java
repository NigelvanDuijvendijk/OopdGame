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
 * object that manages everything that has to do with the darkpower which are placed by the player
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class DarkPower extends PhysicsObject implements ICollidableWithGameObjects, ICollidableWithTiles {
   
//	private GameWorld world;
//    protected Boolean CollidedWithSelf;
//    /**
//     * The amount of gravity darkpower has. Higher means it drops faster.
//     */
//    private float gravity = 0.2f;
//    /**
//     * Speed at which the darpower slides
//     */
//    private final int speed = 3;
//    /**
//     * distance from the player at which the darkpower spawns
//     */
    private final int darkPowerDistance = 80;

    
    public DarkPower(GameWorld world, float gravity, boolean collide, int speed, String sprite) {        
        super(world, gravity, collide, speed, "darkPower.png");
    }
    

    
    /**
     * will spawn a darkpower block
     * @param playerX the X coordinate of the player
     * @param playerY the Y coordinate of the player
     * @param direction the direction that the darkpower should be placed in. Either Left or Right.
     */
    public void spawnDarkPower(float playerX, float playerY, String direction) {
    	DarkPower darkpower = new DarkPower(world, 0.2f, true, 3, "darkPower.png");
    	if(direction == "left") {
    		world.addGameObject(darkpower, playerX - darkPowerDistance, playerY - darkPowerDistance);
    	}else if(direction == "right") {
      	    world.addGameObject(darkpower, playerX + darkPowerDistance, playerY + darkPowerDistance);
    	}
    }

    

}