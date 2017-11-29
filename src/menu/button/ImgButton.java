package menu.button;

import processing.core.PApplet;
import processing.core.PImage;

public class ImgButton extends Button {
	
	private PImage image;
	
	public ImgButton(PApplet canvas, String name, float x, float y) {
		super(canvas, name, x, y, canvas.width / 4, canvas.height / 4);
		this.image = canvas.loadImage("../res/" + name + ".png");
	}
	
	public void draw() {
		canvas.image(image, x - width / 2, y - width / 2, width, height);
		if (isMouseInside()) {
			canvas.stroke(255, 255, 0);
			canvas.strokeWeight(5);
			canvas.noFill();
			canvas.rect(x, y, canvas.width / 4, canvas.height / 4);
		}
	}
	
	public float getWidth() {
		return width;
	}
	
}
