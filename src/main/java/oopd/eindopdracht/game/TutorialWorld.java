package oopd.eindopdracht.game;

import nl.han.ica.oopg.dashboard.Dashboard;
import java.util.ArrayList;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import oopd.eindopdracht.game.tiles.*;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PApplet;

/**
 * The main world of the game where everything gets added in.
 *
 * @author Nigel van Duijvendijk
 * @version 1.0
 */
public class TutorialWorld extends GameEngine {
	private Player player;
	private DarkPower darkpower;
	private BatSpawner batSpawner;
	private FireflySpawner fireflySpawner;
	private GameOver gameover;
	private Light light;
	private Light lightTwo;
	protected Dashboard dashboardStart;

	private TextObject headText;
	private TextObject startText;
	private TextObject startTextPijlen;
	private TextObject startTextDelete;
	private TextObject startTextPlaats;
	private TextObject startTextDuw;
	public TextObject timerText;
	public Animal[] dier;




	private ArrayList<BasicSpawner> spawners = new ArrayList<BasicSpawner>();

	/**
	 * Keeps record of the amount of lights in the level if its 0 the game will end
	 */
	public int lightCount = 2;
	/**
	 * The standard world width
	 */
	private int worldWidth = 1012;
	/**
	 * The standard world height
	 */
	private int worldHeight = 976;
	/**
	 * Keeps record of the gamestate the game is in 0 is start screen, 1 is
	 * gamescreen, 2 is gameover screen
	 */
	public int gameState = 0;
	/**
	 * the standard textsize for the menus
	 */
	public final int STANDARDTEXTSIZE = 40;
	/**
	 * the standard text size for smaller text on the menus
	 */
	public final int STANDARDTEXTSIZESMALL = 20;
	/**
	 * the tilemap for the main level
	 */
	public int mainTilesMap[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0 },
			{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0 },
			{ 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, 0 },
			{ 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, 0 },
			{ 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, 0, 0, 0 },
			{ 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

	};
	/**
	 * the location where all the media is saved
	 */
	public static String MEDIA_URL = "src/main/java/oopd/eindopdracht/game/media/";

	/**
	 * the background music for the game
	 */
	private Sound backgroundMusic;
	/**
	 * the player jumpsound
	 */
	public Sound jumpSound;
	/**
	 * the player dead sound
	 */
	public Sound deadSound;
	/**
	 * the sound of placing a darkpower
	 */
	public Sound darkPowerSound;
	/**
	 * the sound of moving a darkpower
	 */
	public Sound darkSlideSound;

	/**
	 * this will manage the state the game is in 0 for start screen, 1 for play
	 * screen, 2 for gameover screen
	 */

	public static void main(String[] args) {
		TutorialWorld tw = new TutorialWorld();
		tw.runSketch();

	}

	@Override
	public void setupGame() {

		createStartDashboard(worldWidth, worldHeight);

		initializeSound();

		View view = new View(worldWidth, worldHeight);

		setView(view);
		size(worldWidth, worldHeight);

		view.setBackground(loadImage(TutorialWorld.MEDIA_URL.concat("background.gif")));
		initializeTileMap(mainTilesMap);

	}

	@Override
	public void update() {
		if (gameState == 2) {
			deleteGameObject(player);
		}
	}

	/**
	 * functions that initialises all the items which are needed to start the game
	 */
	private void initializeObjects() {
		darkpower = new DarkPower(this, false);

		player = new Player(this, jumpSound, darkpower);
		this.addGameObject(player, 290, 420);

		light = new Light(this, player);
		addGameObject(light, 80, 550);

		lightTwo = new Light(this, player);
		addGameObject(lightTwo, 800, 510);

		batSpawner = new BatSpawner(this, 0.2F);

		fireflySpawner = new FireflySpawner(this, 0.3F);

		spawners.add(fireflySpawner);
		spawners.add(batSpawner);
	}

	/**
	 * will initilize all the sounds that are used in the game
	 */
	private void initializeSound() {
		backgroundMusic = new Sound(this, MEDIA_URL.concat("bg.mp3"));
		backgroundMusic.loop(-1);
		jumpSound = new Sound(this, MEDIA_URL.concat("jump_01.mp3"));
		deadSound = new Sound(this, MEDIA_URL.concat("dead.mp3"));
		darkPowerSound = new Sound(this, MEDIA_URL.concat("darkPower.mp3"));
		darkSlideSound = new Sound(this, MEDIA_URL.concat("darkSlide.mp3"));

	}

	/**
	 * initilizes a tilemap
	 *
	 * @param tileMapNew the tilemap that needs to be initialized
	 */
	protected void initializeTileMap(int[][] tileMapNew) {
		Sprite floorSprite = new Sprite(this.MEDIA_URL.concat("tile.gif"));
		TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);

		TileType[] tileTypes = { floorTileType };
		int tileSize = 40;
		int tilesMap[][] = tileMapNew;
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

	/**
	 * creates the dashboard for the start screen this will also add all the text to
	 * the screen
	 *
	 * @param worldWidth  width of the dashboard
	 * @param worldHeight height of the dashboard
	 */
	public void createStartDashboard(int worldWidth, int worldHeight) {
		dashboardStart = new Dashboard(0, 0, worldWidth, worldHeight);
		headText = new TextObject("Dark and Light", STANDARDTEXTSIZE);
		startText = new TextObject("Druk op je muis om het spel te starten", STANDARDTEXTSIZE);
		startTextPijlen = new TextObject("Pijltjes = lopen", STANDARDTEXTSIZESMALL);
		startTextDelete = new TextObject("D = delete darkpower", STANDARDTEXTSIZESMALL);
		startTextPlaats = new TextObject("Spatie = darkpower plaatsen", STANDARDTEXTSIZESMALL);
		startTextDuw = new TextObject("E = darkpower duwen", STANDARDTEXTSIZESMALL);

		headText.setForeColor(255, 255, 255, 255);
		startText.setForeColor(255, 255, 255, 255);
		startTextPijlen.setForeColor(255, 255, 255, 255);
		startTextDelete.setForeColor(255, 255, 255, 255);
		startTextPlaats.setForeColor(255, 255, 255, 255);
		startTextDuw.setForeColor(255, 255, 255, 255);

		dashboardStart.addGameObject(headText, 350, 100, 100);
		dashboardStart.addGameObject(startText, 100, 300, 100);
		dashboardStart.addGameObject(startTextPijlen, 400, 550, 100);
		dashboardStart.addGameObject(startTextDelete, 370, 500, 100);
		dashboardStart.addGameObject(startTextPlaats, 340, 450, 100);
		dashboardStart.addGameObject(startTextDuw, 370, 400, 100);

	    addDashboard(dashboardStart);
     }

    /**
     * Will delete all the text from the dashboard.
     * This will be triggered when a player presses a button in the start screen
     */
    public void deleteStartDashboard(){
    	 dashboardStart.deleteGameObject(headText);
         dashboardStart.deleteGameObject(startText);
         dashboardStart.deleteGameObject(startTextPijlen);
         dashboardStart.deleteGameObject(startTextDelete);
         dashboardStart.deleteGameObject(startTextPlaats);
         dashboardStart.deleteGameObject(startTextDuw);
    }

    public void mousePressed() {
	    	if(gameState == 0) {
	    		initializeObjects();
	            gameState = 1;
	            deleteStartDashboard();
	    	}
	    	if(gameState == 2) {
	    		gameover = new GameOver(this);
	    		gameover.triggerReplay();
	    		initializeObjects();
	    	}
    }

	public int getWorldWidth() {
		return worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

 }
