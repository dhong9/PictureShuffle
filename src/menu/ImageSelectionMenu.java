package menu;

import controller.State;
import menu.button.Button;
import menu.button.ImgButton;
import processing.core.PApplet;

public class ImageSelectionMenu extends Menu {
	
	private ImgButton hotAirBtn, springFlowersBtn, summitBtn;
	private Button backBtn;
	private String imageName;
	float x, y, width;
	
	private State destination;
	
	public ImageSelectionMenu(PApplet canvas, float x, float y, float width) {
		super(canvas, "Select an Image", x, y, width);
		this.destination = State.ImageSelectionMenu;
		
		this.imageName = "";
		this.x = x;
		this.y = y;
		this.width = width;
		
		float padding = width / 16;
		float btnX = x + 3 * width / 16;
		float btnY = width / 2;
		this.hotAirBtn = new ImgButton(canvas, "hot-air", btnX, btnY, width / 4);
		this.springFlowersBtn = new ImgButton(canvas, "spring-flowers", 
				btnX + padding + hotAirBtn.getWidth(), btnY, width / 4);
		this.summitBtn = new ImgButton(canvas, "summit",
				btnX + 2 * padding + hotAirBtn.getWidth() + springFlowersBtn.getWidth(),
				btnY, width / 4);
		
		float btnWidth = 3 * width / 8;
		float btnHeight = width / 8;
		float centerX = x + width / 2, centerY = y + width / 2;
		this.backBtn = new Button(canvas, "Back", centerX, centerY + width / 4,
				btnWidth, btnHeight);
	}
	
	public void draw() {
		super.drawBackground();
		super.drawText();
		hotAirBtn.draw();
		springFlowersBtn.draw();
		summitBtn.draw();
		backBtn.draw();
	}
	
	public void mouseClicked() {
		imageName = hotAirBtn.isMouseInside() ? "hot-air" : 
			springFlowersBtn.isMouseInside() ? "spring-flowers" : 
			summitBtn.isMouseInside() ? "summit" : "";
		if (!imageName.isEmpty()) {
			destination = State.Game;
		} else if (backBtn.isMouseInside()) {
			destination = State.MainMenu;
		}
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public State getDestination() {
		return destination;
	}

}
