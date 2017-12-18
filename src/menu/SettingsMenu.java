package menu;

import controller.State;
import menu.button.Button;
import menu.options.Option;
import processing.core.PApplet;

public class SettingsMenu extends Menu {
	
	private Option volume, shuffles;
	private Button backBtn;
	private State destination;
	
	public SettingsMenu(PApplet canvas, float x, float y, float width) {
		super(canvas, "Settings", x, y, width);
		
		float centerX = x + width / 2;
		float optionWidth = width / 20;
		this.volume = new Option(canvas, "Volume", centerX, 
				y + (3 * width / 8), optionWidth);
		this.shuffles = new Option(canvas, "Shuffle Moves", centerX,
				y + (width / 2), optionWidth);
		
		this.backBtn = new Button(canvas, "Back", centerX,
				y + (3 * width / 4), 3 * width / 8, width / 8);
		this.destination = State.SettingsMenu;
	}
	
	public void draw() {
		super.drawBackground();
		super.drawText();
		volume.draw();
		shuffles.draw();
		backBtn.draw();
	}
	
	public void mousePressed() {
		volume.mousePressed();
		shuffles.mousePressed();
	}
	
	public void mouseClicked() {
		if (backBtn.isMouseInside()) {
			destination = State.MainMenu;
		}
	}
	
	public State getDestination() {
		return destination;
	}
	
	public float getVolume() {
		return volume.getValue();
	}

}
