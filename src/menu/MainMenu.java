package menu;

import static helpers.Helper.randInt;
import static helpers.Helper.sleep;
import static helpers.Processing.lightenImage;

import java.util.ArrayList;

import game.Board;
import processing.core.PApplet;
import processing.core.PImage;

public class MainMenu extends Menu {
	
	private static final int NUMIMAGES = 2;
	private PImage[] images;
	private Board background;
	private ArrayList<int[]> goodTiles;
	
	public MainMenu(PApplet canvas) {
		super(canvas, "Picture\nShuffle");
		
		// Populate list of images with available images
		images = new PImage[NUMIMAGES];
		images[0] = canvas.loadImage("../res/hot-air.png");
		images[1] = canvas.loadImage("../res/spring-flowers.png");
		PImage image = images[randInt(0, NUMIMAGES)];
		image = lightenImage(canvas, image, 100);
		background = new Board(canvas, image, 4);
		goodTiles = new ArrayList<int[]>();
	}
	
	public void draw() {
		background.draw();
		sleep(1000);
		goodTiles = background.getGoodTiles();
		int[] cell = goodTiles.get(randInt(0, goodTiles.size()));
		background.moveTile(cell[0], cell[1]);
		
		super.draw();
	}

}
