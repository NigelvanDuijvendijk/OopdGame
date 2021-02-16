package oopd.eindopdracht.game;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

/**
 * object that manages everything that has to do with spawning of fireflys such as timers
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class FireflySpawner implements IAlarmListener {
	private Random random;
	private GameWorld world;
	private Player player;
	private Firefly firefly;
	/**
     * The speed at which the flys spawn
     */
	private float flysPerSecond;
	
	/**
	 * Initialises the firefly spawner.
	 * @param world the world that the fireflys should be spawned in
	 * @param fireflysPerSecond the time in which the fireflys should be spawned
	 */
	public FireflySpawner(GameWorld world, float flysPerSecond) {
		this.world = world;
		random = new Random();
		this.flysPerSecond = flysPerSecond;
		startAlarm();
	}
	
	/**
	 * the alarm that makes the fireflys spawn in the given time
	 */
	private void startAlarm() {
	    Alarm alarm = new Alarm("New Firefly", 1 / flysPerSecond);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	@Override
	public void triggerAlarm(String alarmName) {
		firefly = new Firefly(world); 
	    world.addGameObject(firefly, world.width, random.nextInt(world.height));
	    startAlarm();
	}
}