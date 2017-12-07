package game;

import static helpers.Helper.randInt;

import java.util.ArrayList;
import java.util.Arrays;

import controller.State;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Game {
	
	private PApplet canvas;
	private PImage image;
	private Board board;
	private float x, y, width;
	
	private State destination;
	
	public Game(PApplet canvas, float x, float y, float width, String imageName) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		this.width = width;
		
		int subdivisions = 4;
		int scrambles = 100;
		this.image = canvas.loadImage("../res/images/" + imageName + ".png");
		this.board = new Board(canvas, x, y, width, image, subdivisions);
		
		this.destination = State.Game;
		
		ArrayList<int[]> goodTiles = new ArrayList<int[]>();
		for (int i = 1; i <= scrambles; i++) {
			goodTiles = board.getGoodTiles();
			int[] cell = goodTiles.get(randInt(0, goodTiles.size()));
			board.moveTile(cell[0], cell[1]);
		}
	}
	
	public void draw() {
		canvas.background(0); // Void region color
		canvas.fill(255); // Actual game background
		canvas.rectMode(PConstants.CORNER);
		canvas.noStroke();
		canvas.rect(x, y, width, width);
		
		board.draw();
	}
	
	public void mouseClicked() {
		board.mouseClicked();
		if (Arrays.deepEquals(board.getBoard(), board.getSolution())) {
			destination = State.WinMenu;
		}
	}
	
	public void keyPressed() {
		if (canvas.key == 'p') {
			destination = State.PauseMenu;
		}
	}
	
	public State getDestination() {
		return destination;
	}

}
