package menu.button.arrowButton;

import menu.button.Button;
import processing.core.PApplet;
import processing.core.PConstants;

public class ArrowButton extends Button {
	
	private Direction direction;
	
	public ArrowButton(PApplet canvas, float x, float y, float width, float height,
			Direction direction) {
		super(canvas, "", x, y, width, height);
		this.direction = direction;
	}
	
	public void draw() {
		canvas.rectMode(PConstants.CENTER);
		canvas.noStroke();
		
		canvas.fill(canvas.color(0, 255, 0));
		if (isMouseInside()) {
			canvas.fill(canvas.color(0, 100, 0));
		}
		canvas.rect(x, y, width, height, 2);
		
		canvas.fill(255);
		if (direction == Direction.Left) {
			canvas.triangle(x + width / 4, y - height / 4, x + width / 4, y + height / 4, 
				x - width / 4, y);
		} else {
			canvas.triangle(x - width / 4, y - height / 4, x - width / 4, y + height / 4, 
					x + width / 4, y);
		}
		
	}

}
