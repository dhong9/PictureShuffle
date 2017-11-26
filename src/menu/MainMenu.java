package menu;

import static helpers.Helper.randInt;

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
		background = new Board(canvas, images[randInt(0, NUMIMAGES)], 4);
		goodTiles = background.getGoodTiles();
	}
	
	public void draw() {
		background.draw();
		super.draw();
	}

}
