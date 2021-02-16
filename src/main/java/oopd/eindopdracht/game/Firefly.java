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
	private GameOver gameover;
	private GameWorld world;

	/**
	 * initialises the Firefly object
	 * @param world the world the firefly should be added in
	 */
    public Firefly(GameWorld world) {
        super(new Sprite(GameWorld.MEDIA_URL.concat("firefly.png")), speed); 
        this.world = world;
    } 
    
	@Override
    public void ifTouched() {
    	gameover = new GameOver(world);
		gameover.gameoverActions(false);
		
		new Firefly(world);
    }
}
