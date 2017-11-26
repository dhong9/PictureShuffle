package game;

import static helpers.Helper.randInt;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Game {
	
	private PApplet canvas;
	private PImage image;
	private Board board;
	
	public Game(PApplet canvas) {
		this.canvas = canvas;
		
		int subdivisions = 4;
		int scrambles = 100;
		this.image = canvas.loadImage("../res/hot-air.png");
		this.board = new Board(canvas, image, subdivisions);
		
		ArrayList<int[]> goodTiles = new ArrayList<int[]>();
		for (int i = 1; i <= scrambles; i++) {
			goodTiles = board.getGoodTiles();
			int[] cell = goodTiles.get(randInt(0, goodTiles.size()));
			board.moveTile(cell[0], cell[1]);
		}
	}
	
	public void draw() {
		canvas.background(255);
		board.draw();
	}
	
	public void mouseClicked() {
		board.mouseClicked();
	}

}
