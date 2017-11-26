package main;

import controller.StateManager;
import processing.core.PApplet;

public class Main extends PApplet {
	
	private StateManager stateManager;
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void setup() {
		stateManager = new StateManager(this);
	}
	
	public void settings() {
		if (displayWidth < displayHeight) {
			size(3 * displayWidth / 4, 3 * displayWidth / 4);
		} else {
			size(3 * displayHeight / 4, 3 * displayHeight / 4);
		}
	}
	
	public void draw() {
		stateManager.draw();
	}
}
