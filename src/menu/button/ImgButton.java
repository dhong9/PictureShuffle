package menu.button;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class ImgButton extends Button {
	
	private PImage image;
	
	public ImgButton(PApplet canvas, String name, float x, float y, float width) {
		super(canvas, name, x, y, width, width);
		this.image = canvas.loadImage("../res/images/" + name + ".png");
	}
	
	public void draw() {
		canvas.image(image, x - width / 2, y - width / 2, width, height);
		if (isMouseInside()) {
			canvas.stroke(255, 255, 0);
			canvas.strokeWeight(5);
			canvas.noFill();
			canvas.rectMode(PConstants.CENTER);
			canvas.rect(x, y, width, width);
		}
	}
	
	public float getWidth() {
		return width;
	}
	
}
