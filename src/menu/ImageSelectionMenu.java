package menu;

import menu.button.ImgButton;
import processing.core.PApplet;

public class ImageSelectionMenu extends Menu {
	
	private ImgButton hotAirBtn, springFlowersBtn;
	
	public ImageSelectionMenu(PApplet canvas) {
		super(canvas, "Select an Image");
		this.hotAirBtn = new ImgButton(canvas, "hot-air", canvas.width / 8, 3 * canvas.height / 8);
		this.springFlowersBtn = new ImgButton(canvas, "spring-flowers", 
				canvas.width / 2, 3 * canvas.height / 8);
	}
	
	@Override
	public void draw() {
		canvas.background(255);
		super.draw();
		hotAirBtn.draw();
		springFlowersBtn.draw();
	}

}
