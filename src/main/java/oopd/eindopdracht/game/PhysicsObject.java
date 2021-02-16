package oopd.eindopdracht.game;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import oopd.eindopdracht.game.tiles.FloorTile;
import processing.core.PVector;

public class PhysicsObject extends SpriteObject {

	public GameWorld world;
    protected Boolean CollidedWithSelf;
    protected Boolean CollideBool;
    private float gravity;
    private final int speed;

    
    public PhysicsObject(GameWorld world, float gravity, boolean collide, int speed2, String sprite) {
		super(new Sprite(GameWorld.MEDIA_URL.concat(sprite)));
        this.world = world;
        this.CollideBool = collide;
        setGravity(gravity);
        setFriction(0.05f);
        this.speed = speed2;
    }
    
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go: collidedGameObjects) {
			if (go instanceof PhysicsObject) {	
                    float vector = this.getY();
	                setY(vector - 3.8f);
	                setX(this.getX());
	                CollidedWithSelf = true;   
			}
			if (go instanceof DarkPower) {	
				world.deleteGameObject(this);
			}
			if (go instanceof LevelPhysicsObject) {
				if(this instanceof DarkPower) {
					world.deleteGameObject(this);
				}
			}
		}
	}
    
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof FloorTile) {
                try {					
                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                    setY(vector.y - getHeight());
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
      
    }
    
    @Override
    public void update() {
        if (getX() + getWidth() <= 0) {
            setX(world.width);
        }   
    }
	
}
