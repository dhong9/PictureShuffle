package menu;

import menu.button.Button;
import menu.button.ImgButton;
import processing.core.PApplet;

public class ImageSelectionMenu extends Menu {
	
	private ImgButton hotAirBtn, springFlowersBtn;
	private Button backBtn;
	private String imageName;
	float x, y, width;
	
	public ImageSelectionMenu(PApplet canvas, float x, float y, float width) {
		super(canvas, "Select an Image", x, y, width);
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
		backBtn.draw();
	}
	
	public void mouseClicked() {
		imageName = hotAirBtn.isMouseInside() ? "hot-air" : 
			springFlowersBtn.isMouseInside() ? "spring-flowers" : "";
	}
	
	public String getImageName() {
		return imageName;
	}

}
