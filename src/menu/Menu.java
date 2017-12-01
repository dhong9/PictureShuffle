package menu;

import processing.core.PApplet;
import processing.core.PConstants;

public class Menu {
	
	protected PApplet canvas;
	private String name;
	private float x, y, width;
	
	public Menu(PApplet canvas, String name, float x, float y, float width) {
		this.canvas = canvas;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		canvas.textAlign(PConstants.CENTER, PConstants.CENTER);
	}
	
	public void drawBackground() {
		canvas.background(0); // Background color of void region
		canvas.fill(255); // Background color of game-play region
		canvas.noStroke();
		canvas.rectMode(PConstants.CORNER);
		canvas.rect(x, y, width, width);
	}
	
	public void drawText() {
		canvas.textAlign(PConstants.CENTER, PConstants.CENTER);
		canvas.textFont(canvas.createFont("Arial", width / 10));
		canvas.fill(0); // Text color
		canvas.text(name, x + width / 2, y + width / 4);
	}

}
