package oopd.eindopdracht.game;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

/**
 * object that manages everything that has to do with spawning of fireflys such as timers
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class FireflySpawner extends BasicSpawner implements IAlarmListener {	
	private Firefly firefly;
	private TutorialWorld world;
	private Random random;
	/**
     * The speed at which the flys spawn
     */
	
	/**
	 * Initialises the firefly spawner.
	 * @param world the world that the fireflys should be spawned in
	 * @param fireflysPerSecond the time in which the fireflys should be spawned
	 */
	public FireflySpawner(TutorialWorld world, float flysPerSecond) {
		super(flysPerSecond);	
		random = new Random();
		this.world = world;
		startAlarm();
	}
	
	/**
	 * the alarm that makes the fireflys spawn in the given time
	 */
	
	public void startAlarm() {
	    Alarm alarm = new Alarm("New Firefly", 1 / getSpawnRate());
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	public void triggerAlarm(String alarmName) {
		firefly = new Firefly(world); 
	    world.addGameObject(firefly, world.getWorldWidth(), random.nextInt(world.getWorldHeight()));
	    startAlarm();
	}
}