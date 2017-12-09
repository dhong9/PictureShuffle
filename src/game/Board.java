package game;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Board {
	
	private PApplet canvas;
	private float x, y, width;
	private PImage[][] subimages; // Tiles that will show up on the board
	private int subdivisions;
	private HashMap<Integer, Tile> map = new HashMap<Integer, Tile>();
	private ArrayList<int[]> goodTiles;
	
	private int[][] solution, board;
	
	/**
	 * Constructor to instantiate Board object
	 * @param canvas
	 * @param x
	 * @param y
	 * @param width
	 * @param image
	 * @param subdivisions
	 */
	public Board(PApplet canvas, float x, float y, float width, 
			PImage image, int subdivisions) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		this.width = width;
		this.subdivisions = subdivisions;
		this.subimages = new PImage[subdivisions][subdivisions];
		subdivide(image);
		
		this.solution = new int[subdivisions][subdivisions]; // Solved board
		this.board = new int[subdivisions][subdivisions]; // Scrambled board
		buildSolution();
		initBoard();
		initMap();
		
		// Keep list of movable tiles (only applicable for shuffling board)
		this.goodTiles = new ArrayList<int[]>();
		int[] pair1 = {subdivisions - 1, subdivisions - 2};
		int[] pair2 = {subdivisions - 2, subdivisions - 1};
		goodTiles.add(pair1);
		goodTiles.add(pair2);
	}
	
	/**
	 * Draws the board
	 */
	public void draw() {
		canvas.fill(255); // White background
		canvas.noStroke();
		canvas.rectMode(PConstants.CORNER);
		canvas.rect(x, y, width, width);
		
		// Loop for drawing individual tiles to their appropriate location
		for (int k : map.keySet()) {
			map.get(k).draw();
		}
	}
	
	/**
	 * Slice an image into a grid
	 * @param source
	 */
	private void subdivide(PImage source) {
		// Dimensions of each resulting tile
		int w = source.width / subdivisions, h = source.height / subdivisions;
		
		// Populate list of subdivided image tiles
		for (int i = 0; i < subimages.length; i++) {
			for (int j = 0; j < subimages[i].length; j++) {
				subimages[i][j] = source.get(i * w, j * h, w, h);
			}
		}
	}
	
	/**
	 * Define the 'solved' board as grid numbered from 1 to n-1. In this case: <br/>
	 * {1 2 3 4 <br/>
	 *  5 6 7 8 <br/>
	 *  9 10 11 12 <br/>
	 *  13 14 15 0} <br/>
	 * The bottom right corner is 0 because no tile starts there.
	 */
	private void buildSolution() {
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[i].length; j++) {
				solution[i][j] = ((i * solution[i].length) + 1) + j;
			}
		}
		solution[solution.length - 1][solution[0].length - 1] = 0;
	}
	
	/**
	 * Start the board as 'solved'
	 */
	private void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = solution[i][j];
			}
		}
	}
	
	/**
	 * Match each tile to their corresponding id.
	 * For example, the top-left tile would have an id of 1.
	 */
	private void initMap() {
		float w = width / subdivisions;
		for (int i = 0; i < subimages.length; i++) {
			for (int j = 0; j < subimages[i].length; j++) {
				if (board[i][j] != 0) {
					map.put(board[i][j], new Tile(canvas, subimages[i][j],
							x + i * w, y + j * w, w, w));
				}
			}
		}
	}
	
	/**
	 * Moves the selected tile to an adjacent slot if it is open
	 * @param x
	 * @param y
	 */
	public void moveTile(int x, int y) {
		// If the tile is a corner tile...
		if (x == 0 && y == 0) {
			// Check top left tile
			if (board[x + 1][y] == 0) {
				// Tile movement
				board[x + 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x + 1][y]).translate(1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x, y + 1};
				goodTiles.add(pair);
				return;
			}
			if (board[x][y + 1] == 0) {
				// Tile movement
				board[x][y + 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y + 1]).translate(0, 1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x + 1, y};
				goodTiles.add(pair);
				return;
			}
		}
		else if (x == board[x].length - 1 && y == 0) {
			// Check top right tile
			if (board[x - 1][y] == 0) {
				// Tile movement
				board[x - 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x - 1][y]).translate(-1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x, y + 1};
				goodTiles.add(pair);
				return;
			}
			if (board[x][y + 1] == 0) {
				// Tile movement
				board[x][y + 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y + 1]).translate(0, 1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x - 1, y};
				goodTiles.add(pair);
				return;
			}
		}
		else if (x == 0 && y == board.length - 1) {
			// Check bottom left tile
			if (board[x + 1][y] == 0) {
				// Tile movement
				board[x + 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x + 1][y]).translate(1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x, y - 1};
				goodTiles.add(pair);
				return;
			}
			if (board[x][y - 1] == 0) {
				// Tile movement
				board[x][y - 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y - 1]).translate(0, -1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x + 1, y};
				goodTiles.add(pair);
				return;
			}
		}
		else if (x == board.length - 1 && y == board[x].length - 1) {
			// Check bottom right tile
			if (board[x - 1][y] == 0) {
				// Tile movement
				board[x - 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x - 1][y]).translate(-1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x, y - 1};
				goodTiles.add(pair);
				return;
			}
			if (board[x][y - 1] == 0) {
				// Tile movement
				board[x][y - 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y - 1]).translate(0, -1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair = {x - 1, y};
				goodTiles.add(pair);
				return;
			}
		}
		
		// If the tile is a regular edge tile...
		else if (x == 0) {
			// Check left edge
			if (board[x + 1][y] == 0) {
				// Tile movement
				board[x + 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x + 1][y]).translate(1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x, y - 1};
				int[] pair2 = {x, y + 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x][y - 1] == 0) {
				// Tile movement
				board[x][y - 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y - 1]).translate(0, -1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x + 1, y};
				int[] pair2 = {x, y + 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x][y + 1] == 0) {
				// Tile movement
				board[x][y + 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y + 1]).translate(0, 1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x + 1, y};
				int[] pair2 = {x, y + 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
		}
		else if (x == board.length - 1) {
			// Check right edge
			if (board[x - 1][y] == 0) {
				// Tile movement
				board[x - 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x - 1][y]).translate(-1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x, y + 1};
				int[] pair2 = {x, y - 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x][y - 1] == 0) {
				// Tile movement
				board[x][y - 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y - 1]).translate(0, -1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x, y + 1};
				int[] pair2 = {x - 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x][y + 1] == 0) {
				// Tile movement
				board[x][y + 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y + 1]).translate(0, 1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x - 1, y};
				int[] pair2 = {x, y - 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
		}
		else if (y == 0) {
			// Check top edge
			if (board[x - 1][y] == 0) {
				// Tile movement
				board[x - 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x - 1][y]).translate(-1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x, y + 1};
				int[] pair2 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x + 1][y] == 0) {
				// Tile movement
				board[x + 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x + 1][y]).translate(1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x - 1, y};
				int[] pair2 = {x, y + 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x][y + 1] == 0) {
				// Tile movement
				board[x][y + 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y + 1]).translate(0, 1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x - 1, y};
				int[] pair2 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
		}
		else if (y == board[x].length - 1) {
			// Check bottom edge
			if (board[x - 1][y] == 0) {
				// Tile movement
				board[x - 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x - 1][y]).translate(-1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x, y - 1};
				int[] pair2 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x + 1][y] == 0) {
				// Tile movement
				board[x + 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x + 1][y]).translate(1, 0);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x - 1, y};
				int[] pair2 = {x, y - 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
			if (board[x][y - 1] == 0) {
				// Tile movement
				board[x][y - 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y - 1]).translate(0, -1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x - 1, y};
				int[] pair2 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				return;
			}
		}
		
		// Otherwise, it is a regular tile.
		else if (board[x - 1][y] == 0) {
			// Tile movement
			board[x - 1][y] = board[x][y];
			board[x][y] = 0;
			map.get(board[x - 1][y]).translate(-1, 0);
			
			// Update list of movable tiles
			goodTiles.clear();
			int[] pair1 = {x, y - 1};
			int[] pair2 = {x + 1, y};
			int[] pair3 = {x, y + 1};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			return;
		}
		else if (board[x + 1][y] == 0) {
			// Tile movement
			board[x + 1][y] = board[x][y];
			board[x][y] = 0;
			map.get(board[x + 1][y]).translate(1, 0);
			
			// Update list of movable tiles
			goodTiles.clear();
			int[] pair1 = {x - 1, y};
			int[] pair2 = {x, y - 1};
			int[] pair3 = {x, y + 1};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			return;
		}
		else if (board[x][y - 1] == 0) {
			// Tile movement
			board[x][y - 1] = board[x][y];
			board[x][y] = 0;
			map.get(board[x][y - 1]).translate(0, -1);
			
			// Update list of movable tiles
			goodTiles.clear();
			int[] pair1 = {x - 1, y};
			int[] pair2 = {x + 1, y};
			int[] pair3 = {x, y + 1};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			return;
		}
		else if (board[x][y + 1] == 0) {
			// Tile movement
			board[x][y + 1] = board[x][y];
			board[x][y] = 0;
			map.get(board[x][y + 1]).translate(0, 1);
			
			// Update list of movable tiles
			goodTiles.clear();
			int[] pair1 = {x - 1, y};
			int[] pair2 = {x, y - 1};
			int[] pair3 = {x + 1, y};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			return;
		}
	}
	
	/**
	 * @return list of movable tiles
	 */
	public ArrayList<int[]> getGoodTiles() {
		return goodTiles;
	}
	
	/**
	 * Mouse click event handler
	 */
	public void mouseClicked() {
		int w = (int) (width / subdivisions);
		if (canvas.mouseX > x &&
			canvas.mouseX < x + width &&
			canvas.mouseY > y &&
			canvas.mouseY < y + width) {
			moveTile((int)Math.floor((canvas.mouseX - x) / w), (int)Math.floor((canvas.mouseY - y) / w));
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int[] i : board) {
			for (int j : i) {
				str += String.valueOf(j) + " ";
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * @return the integer array representation of the board's current state
	 */
	public int[][] getBoard() {
		return board;
	}
	
	/**
	 * @return the integer array representation of the board's unscrambled
	 * (solved) state
	 */
	public int[][] getSolution() {
		return solution;
	}
	
}
