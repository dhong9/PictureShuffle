package game;

import processing.core.PApplet;
import processing.core.PImage;

public class Tile {
	
	private PApplet canvas;
	private PImage image;
	private float x, y, width, height;
	
	public Tile(PApplet canvas, PImage image, float x, float y, float width, float height) {
		this.canvas = canvas;
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw() {
		canvas.image(image, x, y, width, height);
	}
	
	public boolean isMouseInside() {
		return (canvas.mouseX > x &&
				canvas.mouseX < x + width &&
				canvas.mouseY > y &&
				canvas.mouseY < y + height);
	}
	
	public void translate(int newX, int newY) {
		x += newX * width;
		y += newY * height;
	}

}
