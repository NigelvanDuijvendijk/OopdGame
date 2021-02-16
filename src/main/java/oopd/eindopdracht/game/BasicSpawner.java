package oopd.eindopdracht.game;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public abstract class BasicSpawner implements IAlarmListener {
	private float spawnsPerSecond;
	private Random random;
	private TutorialWorld world;
	
	public BasicSpawner(float spawnsPerSecond,TutorialWorld world )  {
		this.spawnsPerSecond = spawnsPerSecond;
		this.world = world;
		random = new Random();
	}
	
	public float getSpawnsPerSecond() {
		return spawnsPerSecond;
	}

	public void startAlarm() {
	    Alarm alarm = new Alarm("New alarm", 1 / spawnsPerSecond);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	public void destroyAlarms() {
			
	}
	public Random getRandom() {
		return random;
	}

	public TutorialWorld getWorld() {
		return world;
	}
	
	public void printYourType() {
		System.out.println("im a basic type!");
	}	
}
