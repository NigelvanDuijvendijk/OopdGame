package oopd.eindopdracht.game;

public class PumpkinSpawner extends BasicSpawner{

	public PumpkinSpawner(float spawnsPerSecond, TutorialWorld world) {
		super(spawnsPerSecond, world);
		
	}

	@Override
	public void triggerAlarm(String alarmName) {
		// TODO Auto-generated method stub
		firefly = new Firefly(getWorld()); 
	    getWorld().addGameObject(firefly, getWorld().width, getRandom().nextInt(getWorld().height));
	    startAlarm();
	}

	
	
}
