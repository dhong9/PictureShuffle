package menu;

import menu.button.ImgButton;
import processing.core.PApplet;

public class ImageSelectionMenu extends Menu {
	
	private ImgButton hotAirBtn, springFlowersBtn;
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
	}
	
	public void draw() {
		super.drawBackground();
		super.drawText();
		hotAirBtn.draw();
		springFlowersBtn.draw();
	}
	
	public void mouseClicked() {
		imageName = hotAirBtn.isMouseInside() ? "hot-air" : 
			springFlowersBtn.isMouseInside() ? "spring-flowers" : "";
	}
	
	public String getImageName() {
		return imageName;
	}

}
