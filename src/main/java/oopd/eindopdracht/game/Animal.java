package oopd.eindopdracht.game;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
/**
 * Object that intialises animals with sprites and a speed
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public abstract class Animal extends SpriteObject {

	/**
     * initialises a object with a sprite and speed.
     * @param Sprite the sprite that belongs to the object
     */
	public Animal(Sprite sprite, int speed) {
		super(sprite);
        setDirectionSpeed(270, speed);
	}

	@Override
	public void update() {
		
	}
	
	abstract void ifTouched();
}
