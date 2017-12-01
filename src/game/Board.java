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
	
	public Board(PApplet canvas, float x, float y, float width, 
			PImage image, int subdivisions) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		this.width = width;
		this.subdivisions = subdivisions;
		this.subimages = new PImage[subdivisions][subdivisions];
		subdivide(image);
		
		this.solution = new int[subdivisions][subdivisions];
		this.board = new int[subdivisions][subdivisions];
		buildSolution();
		initBoard();
		initMap();
		
		this.goodTiles = new ArrayList<int[]>();
		int[] pair1 = {subdivisions - 1, subdivisions - 2};
		int[] pair2 = {subdivisions - 2, subdivisions - 1};
		goodTiles.add(pair1);
		goodTiles.add(pair2);
	}
	
	public void draw() {
		canvas.fill(255);
		canvas.noStroke();
		canvas.rectMode(PConstants.CORNER);
		canvas.rect(x, y, width, width);
		for (int k : map.keySet()) {
			map.get(k).draw();
		}
	}
	
	private void subdivide(PImage source) {
		int w = source.width / subdivisions, h = source.height / subdivisions;
		for (int i = 0; i < subimages.length; i++) {
			for (int j = 0; j < subimages[i].length; j++) {
				subimages[i][j] = source.get(i * w, j * h, w, h);
			}
		}
	}
	
	private void buildSolution() {
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[i].length; j++) {
				solution[i][j] = ((i * solution[i].length) + 1) + j;
			}
		}
		solution[solution.length - 1][solution[0].length - 1] = 0;
	}
	
	private void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = solution[i][j];
			}
		}
	}
	
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
		else if (x == board[x].length - 1 && y == 0) {
			// Check top right tile
			if (board[x - 1][y] == 0) {
				// Tile movement
				board[x - 1][y] = board[x][y];
				board[x][y] = 0;
				map.get(board[x - 1][y]).translate(-1, 0);
				
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
				int[] pair2 = {x, y + 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
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
				int[] pair1 = {x, y - 1};
				int[] pair2 = {x + 1, y};
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
				int[] pair1 = {x, y - 1};
				int[] pair2 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
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
				int[] pair2 = {x, y - 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
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
				int[] pair2 = {x + 1, y};
				int[] pair3 = {x, y + 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
				return;
			}
			if (board[x][y - 1] == 0) {
				// Tile movement
				board[x][y - 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y - 1]).translate(0, -1);
				
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
			if (board[x][y + 1] == 0) {
				// Tile movement
				board[x][y + 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y + 1]).translate(0, 1);
				
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
				int[] pair2 = {x - 1, y};
				int[] pair3 = {x, y - 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
				int[] pair3 = {x, y - 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
				return;
			}
			if (board[x][y + 1] == 0) {
				// Tile movement
				board[x][y + 1] = board[x][y];
				board[x][y] = 0;
				map.get(board[x][y + 1]).translate(0, 1);
				
				// Update list of movable tiles
				goodTiles.clear();
				int[] pair1 = {x, y + 1};
				int[] pair2 = {x - 1, y};
				int[] pair3 = {x, y - 1};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
				int[] pair1 = {x - 1, y};
				int[] pair2 = {x, y + 1};
				int[] pair3 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
				int[] pair3 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
				int[] pair2 = {x, y + 1};
				int[] pair3 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
				int[] pair1 = {x - 1, y};
				int[] pair2 = {x, y - 1};
				int[] pair3 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
				int[] pair3 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
				int[] pair2 = {x, y - 1};
				int[] pair3 = {x + 1, y};
				goodTiles.add(pair1);
				goodTiles.add(pair2);
				goodTiles.add(pair3);
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
			int[] pair1 = {x - 1, y};
			int[] pair2 = {x, y - 1};
			int[] pair3 = {x + 1, y};
			int[] pair4 = {x, y + 1};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			goodTiles.add(pair4);
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
			int[] pair3 = {x + 1, y};
			int[] pair4 = {x, y + 1};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			goodTiles.add(pair4);
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
			int[] pair2 = {x, y - 1};
			int[] pair3 = {x + 1, y};
			int[] pair4 = {x, y + 1};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			goodTiles.add(pair4);
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
			int[] pair4 = {x, y + 1};
			goodTiles.add(pair1);
			goodTiles.add(pair2);
			goodTiles.add(pair3);
			goodTiles.add(pair4);
			return;
		}
	}
	
	public ArrayList<int[]> getGoodTiles() {
		return goodTiles;
	}
	
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
	
}
