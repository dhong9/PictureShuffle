package game;

import processing.core.PApplet;
import processing.core.PImage;

public class Tile {
	
	private PApplet canvas;
	private PImage image;
	private float x, y, width, height;
	
	/**
	 * General Tile constructor
	 * @param canvas
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Tile(PApplet canvas, PImage image, float x, float y, float width, float height) {
		this.canvas = canvas;
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Draws the individual board tile
	 */
	public void draw() {
		canvas.image(image, x, y, width, height);
	}
	
	/**
	 * @return if the mouse is inside the tile or not
	 */
	public boolean isMouseInside() {
		return (canvas.mouseX > x &&
				canvas.mouseX < x + width &&
				canvas.mouseY > y &&
				canvas.mouseY < y + height);
	}
	
	/**
	 * Move the tile on the screen
	 * @param newX horizontal distance to move
	 * @param newY vertical distance to move
	 */
	public void translate(int newX, int newY) {
		x += newX * width;
		y += newY * height;
	}

}
