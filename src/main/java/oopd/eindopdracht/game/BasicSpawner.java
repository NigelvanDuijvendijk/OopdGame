package oopd.eindopdracht.game;

import nl.han.ica.oopg.alarm.Alarm;

public abstract class BasicSpawner{
	private float spawnRate;
	
	public BasicSpawner(float spawnRate) {
		this.spawnRate = spawnRate;
	}
	
	
	float getSpawnRate() {
		return spawnRate;
	}
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
