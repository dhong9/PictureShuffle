package game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
	
	private PImage image;
	private Board board;
	
	public static void main(String[] args) {
		PApplet.main("game.Main");
	}
	
	public void setup() {
		int subdivisions = 4;
		int scrambles = 100;
		image = loadImage("../res/hot-air.png");
		board = new Board(this, image, subdivisions);
		
		for (int i = 1; i <= scrambles; i++) {
			ArrayList<int[]> goodTiles = board.getGoodTiles();
			int[] cell = goodTiles.get(randInt(0, goodTiles.size()));
			board.moveTile(cell[0], cell[1]);
		}
		
	}
	
	public void settings() {
		if (displayWidth < displayHeight) {
			size(3 * displayWidth / 4, 3 * displayWidth / 4);
		} else {
			size(3 * displayHeight / 4, 3 * displayHeight / 4);
		}
	}
	
	public void draw() {
		background(255);
		board.draw();
	}
	
	public void mouseClicked() {
		board.mouseClicked();
	}
	
	private static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max); // +1 to make max inclusive
	}
	
}
