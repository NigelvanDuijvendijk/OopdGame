package oopd.eindopdracht.game;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

/**
 * object that manages everything that has to do with spawning of bats such as timers
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class BatSpawner extends BasicSpawner implements IAlarmListener {

	private Bat bat;
	/**
     * the speed at which the bats spawn
     */
	
	
	/**
	 * Initialises the bat spawner.
	 * @param world the world that the bats should be spawned in
	 * @param batsPerSecond the time in which the bats should be spawned
	 */
	public BatSpawner(TutorialWorld world, float batsPerSecond) {
		super(batsPerSecond, world);		
	}
	
	/**
	 * the alarm that makes the bats spawn in the given time
	 */
	public void startAlarm() {
	    Alarm alarm = new Alarm("New Bat", 1 / getSpawnsPerSecond());
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	@Override
	public void triggerAlarm(String alarmName) {
	    bat = new Bat(getWorld()); 
	    getWorld().addGameObject(bat, getWorld().width, getRandom().nextInt(getWorld().height));
	    startAlarm();
	}
	@Override
	public void printYourType() {
		System.out.println("im a bat type!");			
	}
}