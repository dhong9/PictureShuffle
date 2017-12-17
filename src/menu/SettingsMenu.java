package menu;

import menu.options.Option;
import processing.core.PApplet;

public class SettingsMenu extends Menu {
	
	private Option volume, shuffles;
	
	public SettingsMenu(PApplet canvas, float x, float y, float width) {
		super(canvas, "Settings", x, y, width);
		this.volume = new Option(canvas, "Volume", x + width / 2, 
				y + (3 * width / 8), width / 20);
		this.shuffles = new Option(canvas, "Shuffle Moves", x + width / 2,
				y + (4 * width / 8), width / 20);
	}
	
	public void draw() {
		super.drawBackground();
		super.drawText();
		volume.draw();
		shuffles.draw();
	}
	
	public void mousePressed() {
		volume.mousePressed();
		shuffles.mousePressed();
	}

}
