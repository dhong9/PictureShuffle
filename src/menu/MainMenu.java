package menu;

import static helpers.Helper.randInt;
import static helpers.Processing.lightenImage;

import java.util.ArrayList;

import controller.State;
import game.Board;
import menu.button.Button;
import processing.core.PApplet;
import processing.core.PImage;

public class MainMenu extends Menu {
	
	private static final int NUMIMAGES = 2;
	private PImage[] images;
	private Board background;
	private ArrayList<int[]> goodTiles;
	
	private Button playBtn, settingsBtn, quitBtn;
	private float btnWidth, btnHeight;
	
	private State destination;
	
	private int time, wait;
	
	public MainMenu(PApplet canvas, float x, float y, float width) {
		super(canvas, "Picture\nShuffle", x, y, width);
		this.destination = State.MainMenu;
		
		// Button setup
		this.btnWidth = 3 * width / 8;
		this.btnHeight = width / 8;
		float centerX = x + width / 2, centerY = y + width / 2;
		float padding = width / 16;
		this.playBtn = new Button(canvas, "Play", centerX, centerY, btnWidth, btnHeight);
		this.settingsBtn = new Button(canvas, "Settings", centerX, 
				centerY + btnHeight + padding, btnWidth, btnHeight);
		this.quitBtn = new Button(canvas, "Quit", centerX, 
				centerY + 2 * (btnHeight + padding), btnWidth, btnHeight);
		
		// Populate list of images with available images
		images = new PImage[NUMIMAGES];
		images[0] = canvas.loadImage("../res/images/hot-air.png");
		images[1] = canvas.loadImage("../res/images/spring-flowers.png");
		PImage image = images[randInt(0, NUMIMAGES)];
		image = lightenImage(canvas, image, 100);
		background = new Board(canvas, x, y, width, image, 4);
		goodTiles = new ArrayList<int[]>();
		
		this.time = canvas.millis();
		this.wait = 1000;
	}
	
	public void draw() {
		super.drawBackground();
		background.draw();
		playBtn.draw();
		settingsBtn.draw();
		quitBtn.draw();
		super.drawText();
		
		if (canvas.millis() - time >= wait) {
			goodTiles = background.getGoodTiles();
			int[] cell = goodTiles.get(randInt(0, goodTiles.size()));
			background.moveTile(cell[0], cell[1]);
			time = canvas.millis();
		}
	}
	
	public State getDestination() {
		return destination;
	}
	
	public void mouseClicked() {
		if (playBtn.isMouseInside()) {
			destination = State.ImageSelectionMenu;
		} else if (settingsBtn.isMouseInside()) {
			
		} else if (quitBtn.isMouseInside()) {
			System.exit(0);
		}
	}

}
