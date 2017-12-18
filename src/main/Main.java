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
		//fullScreen();
		size(displayWidth / 2, displayHeight / 2);
	}
	
	public void draw() {
		stateManager.draw();
	}
	
	public void mouseClicked() {
		stateManager.mouseClicked();
	}
	
	public void mousePressed() {
		stateManager.mousePressed();
	}
	
	public void keyPressed() {
		stateManager.keyPressed();
	}
}
