package menu;

import menu.button.ImgButton;
import processing.core.PApplet;

public class ImageSelectionMenu extends Menu {
	
	private ImgButton hotAirBtn, springFlowersBtn;
	private String imageName;
	
	public ImageSelectionMenu(PApplet canvas) {
		super(canvas, "Select an Image");
		this.imageName = "";
		
		float padding = canvas.width / 16;
		float x = 3 * canvas.width / 16;
		float y = canvas.height / 2;
		this.hotAirBtn = new ImgButton(canvas, "hot-air", x, y);
		this.springFlowersBtn = new ImgButton(canvas, "spring-flowers", 
				x + padding + hotAirBtn.getWidth(), y);
	}
	
	@Override
	public void draw() {
		canvas.background(255);
		super.draw();
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
