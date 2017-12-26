package game;

import static helpers.Processing.rand;

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
	
	/**
	 * Constructor to instantiate Game object
	 * @param canvas
	 * @param x
	 * @param y
	 * @param width
	 * @param imageName
	 */
	public Game(PApplet canvas, float x, float y, float width, String imageName) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		this.width = width;
		
		int subdivisions = 4; // Number of rows/columns on grid
		int scrambles = 100; // How many moves for scrambling
		this.image = canvas.loadImage("res/images/" + imageName + ".png");
		this.board = new Board(canvas, x, y, width, image, subdivisions);
		
		this.destination = State.Game; // Default destination state to self
		
		// Store list of movable tiles for scrambling
		ArrayList<int[]> goodTiles = new ArrayList<int[]>();
		for (int i = 1; i <= scrambles; i++) {
			goodTiles = board.getGoodTiles();
			int[] cell = goodTiles.get(rand(canvas, goodTiles.size()));
			board.moveTile(cell[0], cell[1]);
		}
	}
	
	/**
	 * Draws the entire game panel
	 */
	public void draw() {
		canvas.background(0); // Void region color
		canvas.fill(255); // Actual game background
		canvas.rectMode(PConstants.CORNER);
		canvas.noStroke();
		canvas.rect(x, y, width, width);
		
		board.draw();
	}
	
	/**
	 * Mouse click event handler
	 */
	public void mouseClicked() {
		board.mouseClicked(); // Update the board
		
		// If the board is solved, then display the win screen
		if (Arrays.deepEquals(board.getBoard(), board.getSolution())) {
			destination = State.WinMenu;
		}
	}
	
	/**
	 * Key pressed event handler
	 */
	public void keyPressed() {
		// Press 'P' to pause the game
		if (canvas.key == 'p') {
			destination = State.PauseMenu;
		}
	}
	
	/**
	 * @return destination of the game screen
	 */
	public State getDestination() {
		return destination;
	}

}
