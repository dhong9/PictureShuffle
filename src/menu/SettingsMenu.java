package menu;

import processing.core.PApplet;

public class SettingsMenu extends Menu {
	
	public SettingsMenu(PApplet canvas, float x, float y, float width) {
		super(canvas, "Settings", x, y, width);
	}
	
	public void draw() {
		super.drawBackground();
		super.drawText();
	}

}
