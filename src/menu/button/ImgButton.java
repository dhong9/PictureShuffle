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
		canvas.image(image, x, y, width, height);
	}

}
