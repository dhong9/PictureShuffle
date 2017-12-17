package menu.options;

import menu.button.arrowButton.ArrowButton;
import menu.button.arrowButton.Direction;
import processing.core.PApplet;
import processing.core.PConstants;

public class Option {
	
	private PApplet canvas;
	private int value;
	private String name;
	private float x, y, size;
	private ArrowButton leftArrowBtn, rightArrowBtn;
	
	public Option(PApplet canvas, String name, float x, float y, float size) {
		this.canvas = canvas;
		this.name = name;
		this.x = x;
		this.y = y;
		this.value = 100;
		this.size = size;
		this.leftArrowBtn = new ArrowButton(canvas, x - 2 * size, y + size / 2, 
				size / 2, size, Direction.Left);
		this.rightArrowBtn = new ArrowButton(canvas, x + 2 * size, y + size / 2, 
				size / 2, size,Direction.Right);
	}
	
	public void draw() {
		canvas.textFont(canvas.createFont("Arial", size));
		canvas.fill(0);
		
		canvas.textAlign(PConstants.CENTER, PConstants.BOTTOM);
		canvas.text(name, x, y);
		
		canvas.textAlign(PConstants.CENTER, PConstants.TOP);
		canvas.text(String.valueOf(value), x, y);
		leftArrowBtn.draw();
		rightArrowBtn.draw();
	}
	
	public void mousePressed() {
		if (leftArrowBtn.isMouseInside() && value > 0) {
			value--;
		} else if (rightArrowBtn.isMouseInside() && value < 100) {
			value++;
		}
	}

}
