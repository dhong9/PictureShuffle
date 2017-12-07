package menu;

import controller.State;
import menu.button.Button;
import processing.core.PApplet;
import processing.core.PImage;

public class WinMenu extends Menu {
	
	private PImage image;
	private Button playAgainBtn, homeBtn, quitBtn;
	private State destination;
	
	public WinMenu(PApplet canvas, float x, float y, float width, String imageName) {
		super(canvas, "You Win!", x, y, width);
		this.destination = State.WinMenu;
		
		// Button setup
		float btnWidth = 3 * width / 8;
		float btnHeight = width / 8;
		float centerX = x + width / 2, centerY = y + width / 2;
		float padding = width / 16;
		this.playAgainBtn = new Button(canvas, "Play", centerX, centerY, btnWidth, btnHeight);
		this.homeBtn = new Button(canvas, "Home", centerX, 
				centerY + btnHeight + padding, btnWidth, btnHeight);
		this.quitBtn = new Button(canvas, "Quit", centerX, 
				centerY + 2 * (btnHeight + padding), btnWidth, btnHeight);
		
		this.image = canvas.loadImage("../res/images/" + imageName + ".png");
	}
	
	public void draw() {
		super.drawBackground();
		canvas.image(image, x, y, width, width);
		playAgainBtn.draw();
		homeBtn.draw();
		quitBtn.draw();
		super.drawText();
	}
	
	public void mouseClicked() {
		if (playAgainBtn.isMouseInside()) {
			destination = State.ImageSelectionMenu;
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
