package menu;

import processing.core.PApplet;
import processing.core.PConstants;

public class Menu {
	
	protected PApplet canvas;
	private String name;
	
	public Menu(PApplet canvas, String name) {
		this.canvas = canvas;
		this.name = name;
		canvas.textAlign(PConstants.CENTER, PConstants.CENTER);
	}
	
	public void draw() {
		canvas.textFont(canvas.createFont("Arial", canvas.width / 10));
		canvas.fill(0);
		canvas.text(name, canvas.width / 2, canvas.height / 4);
	}

}
