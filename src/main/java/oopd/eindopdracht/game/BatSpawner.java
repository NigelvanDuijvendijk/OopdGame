package oopd.eindopdracht.game;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

/**
 * object that manages everything that has to do with spawning of bats such as timers
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class BatSpawner extends BasicSpawner implements IAlarmListener {
	private TutorialWorld world;
	private Bat bat;
	private Random random;
	/**
     * the speed at which the bats spawn
     */
	private float batsPerSecond;
	
	/**
	 * Initialises the bat spawner.
	 * @param world the world that the bats should be spawned in
	 * @param batsPerSecond the time in which the bats should be spawned
	 */
	public BatSpawner(TutorialWorld world, float batsPerSecond) {
		super(batsPerSecond);
		random = new Random();
		this.world = world;
		startAlarm();
	}
	
	/**
	 * the alarm that makes the bats spawn in the given time
	 */
	public void startAlarm() {
	    Alarm alarm = new Alarm("New Bat", 1 / batsPerSecond);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	@Override
	public void triggerAlarm(String alarmName) {
	    bat = new Bat(world); 
	    world.addGameObject(bat, world.width, random.nextInt(world.height));
	    startAlarm();
	   
	}
}