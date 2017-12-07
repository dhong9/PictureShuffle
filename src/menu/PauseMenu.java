package menu;

import controller.State;
import menu.button.Button;
import processing.core.PApplet;

public class PauseMenu extends Menu {
	
	private Button backBtn, homeBtn, quitBtn;
	
	private State destination;
	
	public PauseMenu(PApplet canvas, float x, float y, float width) {
		super(canvas, "Paused", x, y, width);
		this.destination = State.PauseMenu;
		
		// Button setup
		float btnWidth = 3 * width / 8;
		float btnHeight = width / 8;
		float centerX = x + width / 2, centerY = y + width / 2;
		float padding = width / 16;
		this.backBtn = new Button(canvas, "Back", centerX, centerY, btnWidth, btnHeight);
		this.homeBtn = new Button(canvas, "Home", centerX, 
				centerY + btnHeight + padding, btnWidth, btnHeight);
		this.quitBtn = new Button(canvas, "Quit", centerX, 
				centerY + 2 * (btnHeight + padding), btnWidth, btnHeight);
	}
	
	public void draw() {
		super.drawBackground();
		backBtn.draw();
		homeBtn.draw();
		quitBtn.draw();
		super.drawText();
	}
	
	public void mouseClicked() {
		if (backBtn.isMouseInside()) {
			destination = State.Game;
		} else if (homeBtn.isMouseInside()) {
			destination = State.MainMenu;
		} else if (quitBtn.isMouseInside()) {
			System.exit(0);
		}
	}
	
	public State getDestination() {
		return destination;
	}
}
