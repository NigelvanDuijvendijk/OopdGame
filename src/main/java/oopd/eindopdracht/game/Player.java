package oopd.eindopdracht.game;

import java.util.List;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;
import oopd.eindopdracht.game.tiles.FloorTile;
import processing.core.PVector;

/**
 * object that manages everything that has to do with the player such as movement and collisions
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects, IAlarmListener {
	
    private TutorialWorld world;
	private DarkPower darkpower;
	private Firefly firefly;
	private GameOver gameover;
	private DarkPower DarkPowerColided;
	
	/**
     * The sound for the player jump action
     */
	private final Sound jumpSound;

	/**
     * The speed at which the player moves
     */
	private int speed = 3;
	/**
     * The height at which the player can jump
     */
	private int jumpHight = 15;
	/**
     * Boolean that keeps track of if the player can place a dark power
     */
	
	private boolean placingAllowed = true;
	/**
     * Boolean that keeps track of if the player is allowed to jump
     */
	private boolean jumpingAllowed = true;
	/**
     * The angle at which the player hits the darkpower
     */
	private String colideAngleDarkPower;
	/**
     * The amount of gravity the player gets. Higher means you fall faster.
     */
    private float gravity = 0.4f;
    /**
     * The direction the player is facing
     */
    private String playerDirection;
    /**
     * The standard tilesize of the game
     */
    private int tileSize = 40;
 
    public Player(TutorialWorld world, Sound jumpSound, DarkPower darkpower) {
        super(new Sprite(TutorialWorld.MEDIA_URL.concat("vampier_animated.png")),2);
        setCurrentFrameIndex(1);
        this.world = world;
        this.jumpSound = jumpSound;
        this.darkpower= darkpower;
        setFriction(0.05f);
        setGravity(gravity);
    }

    @Override
    /**
     * checks if the player hits the sides of the screen and stops the movement
     */
    public void update() { 
        if (getX() <= tileSize) {
            setxSpeed(0);
            setX(tileSize);
        }
        if (getY() <= tileSize) {
            setySpeed(0); 
            setY(tileSize);
        }
        if (getX() >= world.width - getWidth() - tileSize) {
            setxSpeed(0);
            setX(world.width - getWidth() - tileSize);
        }
        if (getY() >= world.height - getHeight() - tileSize) {
            setySpeed(0);
            setY(world.height - getHeight() - tileSize);
        }
    }
    
    @Override
    /**
     * Manages the movement of the player and the placing, moving and deleting of darkpower
     */
    public void keyPressed(int keyCode, char key) {
        if (keyCode == world.LEFT) {
          setDirectionSpeed(270, speed);
          playerDirection = "left";
          setCurrentFrameIndex(0);
          
          }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            playerDirection = "right";
            setCurrentFrameIndex(1);
          }
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
          }
        if (keyCode == world.UP) {
        	if(jumpingAllowed) {
        		jumpingAllowed = false;
        		setDirectionSpeed(360, jumpHight);
	        	startAlarm("jumped");
		    	world.jumpSound.cue(0);
		    	world.jumpSound.play();

        	}
          } 
        if(keyCode == 32) {
        	if(placingAllowed) {
	        	if(playerDirection == "left") {
		        	darkpower.spawnDarkPower(this.x, this.y, "left");
	        	}else if(playerDirection == "right") {
		        	darkpower.spawnDarkPower(this.x, this.y, "right");
	        	}
	        	placingAllowed = false;
	        	startAlarm("darkpower placed");
	        	world.darkPowerSound.cue(0);
		    	world.darkPowerSound.play();
        	}
        }
		if(keyCode == 68) {
			if(colideAngleDarkPower == "right" || colideAngleDarkPower == "left") {
	        	world.deleteGameObject(DarkPowerColided);
        		colideAngleDarkPower = "none";
			}
        }
        if(keyCode == 69) {
        	if(colideAngleDarkPower == "right") {
        		world.darkSlideSound.cue(0);
		    	world.darkSlideSound.play();
        		DarkPowerColided.setDirectionSpeed(270, 5);
        		colideAngleDarkPower = "none";
        	}
        	if(colideAngleDarkPower == "left") {
        		world.darkSlideSound.cue(0);
		    	world.darkSlideSound.play();
        		DarkPowerColided.setDirectionSpeed(90, 5);
        		colideAngleDarkPower = "none";
        	}
        }
        if(keyCode == 66) {
        	world.addBatSpawner();
        	System.out.println("voeg een spawner toe!");
        	
        }
        if(keyCode == 82) {
        	world.removeAllBats();
        }
    }
    
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof FloorTile) {
                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                    setY(vector.y - getHeight());
            }
        }
      
    }
    
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go: collidedGameObjects) {
			if (go instanceof DarkPower) {		

				if(getAngleFrom(go) > 200 ) {
	                colideAngleDarkPower = "right";
	                this.setX(this.prevX +0.2f);
	                DarkPowerColided = (DarkPower) go;
				}
				else if(getAngleFrom(go) < 200 ) {
					 colideAngleDarkPower = "left";
	                this.setX(this.prevX -0.2f);
	                DarkPowerColided = (DarkPower) go;
				}
				else if(getAngleFrom(go) < 180 && getAngleFrom(go) > 130 ) {
	                this.setY(this.prevY -0.2f);
				}
			}
			if (go instanceof Firefly) {	
				
				gameover = new GameOver(world);
				gameover.gameoverActions();
				
				firefly = new Firefly(world);
				firefly.touched();
			}
		}
	}
    
    /**
     * will start a alarm
     * @param alarmName the alarm that should be started. The alarm has to be added in the function first.
     */
    private void startAlarm(String alarmName) {
		if(alarmName == "darkpower placed") {
		    Alarm alarm = new Alarm("darkpower placed", 5 );
		    alarm.addTarget(this);
		    alarm.start();
		}
		if(alarmName == "jumped") {
			Alarm alarm = new Alarm("jumped", 5 );
		    alarm.addTarget(this);
		    alarm.start();
		}
	}

	
	@Override
	public void triggerAlarm(String alarmName) {
		if(alarmName == "darkpower placed") { 
			placingAllowed = true;
		    startAlarm("darkpower placed");
		}
		if(alarmName == "jumped") {
			jumpingAllowed = true;
			startAlarm("jumped");
		}
	}

}