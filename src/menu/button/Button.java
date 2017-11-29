package menu.button;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button {
	
	protected PApplet canvas;
	protected String name;
	protected float x, y, width, height;
	
	public Button(PApplet canvas, String name, float x, float y, float width, float height) {
		this.canvas = canvas;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		canvas.rectMode(PConstants.CENTER);
		canvas.textAlign(PConstants.CENTER, PConstants.CENTER);
	}
	
	public void draw() {
		canvas.strokeWeight(1);
		
		canvas.fill(isMouseInside() ? canvas.color(0, 255, 0) : 255);
		canvas.rect(x, y, width, height, 10);
		
		canvas.textFont(canvas.createFont("Arial", height / 2));
		canvas.fill(0);
		canvas.text(name, x, y);
	}
	
	public boolean isMouseInside() {
		return canvas.mouseX > (x - width / 2) &&
				canvas.mouseX < (x + width / 2) &&
				canvas.mouseY > (y - height / 2) &&
				canvas.mouseY < (y + height / 2);
	}

}
