package oopd.eindopdracht.game;


import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;

/**
 * object that manages the fireflys. This object is used by the fireflySpawner
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class Firefly extends Animal {
	/**
	 * the speed at which the firefly moves
	 */
	private static int speed = 2;

	/**
	 * initialises the Firefly object
	 * @param world the world the firefly should be added in
	 */
	    public Firefly(TutorialWorld world) {
	        super(new Sprite(TutorialWorld.MEDIA_URL.concat("firefly.png")), speed);  
	    } 
	    
	    public void touched() {
	    	System.out.println("Firefly touched");
	    }
}
