package oopd.eindopdracht.game;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class PhysicsObject extends SpriteObject {

	private GameWorld world;
    protected Boolean CollidedWithSelf;
    
    public PhysicsObject(GameWorld world, boolean collidedWithSelf, float gravity, boolean collide) {
        super(new Sprite(GameWorld.MEDIA_URL.concat("darkPower.png")));
        this.world = world;
        this.CollidedWithSelf = collide;
        setGravity(gravity);
        setFriction(0.05f);

    }
	
}
