package oopd.eindopdracht.game;


import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;

/**
 * object that manages the bats. This object is used by the batspawner
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class Bat extends Animal {
	/**
	 * the speed at which the bat moves
	 */
	private static int speed = 8;
	/**
	 * initialises the bat object
	 * @param world the world the bat should be added in
	 */
	    public Bat(TutorialWorld world) {
	        super(new Sprite(TutorialWorld.MEDIA_URL.concat("bat.gif")), speed);  
	    } 
	    
	    public void touched() {
	    	System.out.println("Bat touched");
	    }
}
